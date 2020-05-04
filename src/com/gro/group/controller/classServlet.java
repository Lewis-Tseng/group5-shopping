package com.gro.group.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.gro.group.groupDAO;
import com.gro.group.groupService;
import com.gro.group.groupVO;
import com.gro.report.reportService;
import com.gro.report.reportVO;
import com.gro.type.groclassService;
import com.gro.type.groclassVO;

public class classServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String BF_url = req.getParameter("BF_url");


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("gro_cid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String gro_cid = null;
				try {
					gro_cid = new String(str);
				} catch (Exception e) {
					errorMsgs.add("揪團類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				groclassService groclassSvc = new groclassService();
				groclassVO groclassVO = groclassSvc.getOneClass(gro_cid);
				if (groclassVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groclassVO", groclassVO); // 資料庫取出的empVO物件,存入req
				String url = BF_url+"/group_type/listOneGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_type/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String gro_cname = req.getParameter("gro_cname");				
				if (gro_cname == null || gro_cname.trim().length() == 0) {
					errorMsgs.add("揪團類別名稱: 請勿空白");
				}



				groclassVO groclassVO = new groclassVO();
				groclassVO.setGro_cname(gro_cname);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groclassVO", groclassVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_type/addGroupClass.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				groclassService groclassSvc = new groclassService();
				groclassVO = groclassSvc.addClass(gro_cname);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = BF_url+"/group_type/listAllGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_type/addGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		------------------------------------------------------------------------------------------------------------------------
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String gro_cid = new String(req.getParameter("gro_cid"));
				
				/***************************2.開始刪除資料***************************************/
				groclassService groclassSvc = new groclassService();
				groclassSvc.deleteClass(gro_cid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = BF_url+"/group_type/listAllGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_type/listAllGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String gro_cid = new String(req.getParameter("gro_cid"));
				/***************************2.開始查詢資料****************************************/
				groclassService groclassSvc = new groclassService();
				groclassVO groclassVO = groclassSvc.getOneClass(gro_cid);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groclassVO", groclassVO);         // 資料庫取出的empVO物件,存入req
				String url = BF_url+"/group_type/update_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_type/listAllGroupClass.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String gro_cid = new String(req.getParameter("gro_cid").trim());
				
				String gro_cname = req.getParameter("gro_cname");				
				if (gro_cname == null || gro_cname.trim().length() == 0) {
					errorMsgs.add("揪團類別名稱: 請勿空白");
				}

				
				groclassVO groclassVO = new groclassVO();
				groclassVO.setGro_cid(gro_cid);
				groclassVO.setGro_cname(gro_cname);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groclassVO", groclassVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_type/update_class_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				groclassService groclassSvc = new groclassService();
				groclassVO = groclassSvc.updateClass(gro_cname,gro_cid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groclassVO", groclassVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = BF_url+"/group_type/listOneGroupClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_type/update_class_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
