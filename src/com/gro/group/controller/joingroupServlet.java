package com.gro.group.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.gro.group.groupService;
import com.gro.group.groupVO;
import com.gro.joingroup.joingroupService;
import com.gro.joingroup.joingroupVO;
import com.gro.type.groclassService;
import com.gro.type.groclassVO;


public class joingroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String BF_url = req.getParameter("BF_url");
		String requestURL = req.getParameter("requestURL");

		
		
		if ("getGros_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_id = new String(req.getParameter("mem_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				joingroupService joingroupSvc = new joingroupService();
				Set<joingroupVO> set = joingroupSvc.getAllGros(mem_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGros_ByMem", set);    // 資料庫取出的set物件,存入request
		
				String url = null;
				
					url = BF_url+"/group_join/listGrosByMem.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getMems_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String gro_id = new String(req.getParameter("gro_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				joingroupService joingroupSvc = new joingroupService();
				Set<joingroupVO> set = joingroupSvc.getAllMems(gro_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMems_ByGro", set);    // 資料庫取出的set物件,存入request
		
				String url = null;
				
					url = BF_url+"/group_join/listMemsByGro.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		

		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String gro_id = req.getParameter("gro_id");				
				if (gro_id == null || gro_id.trim().length() == 0) {
					errorMsgs.add("揪團編號: 請勿空白");
				}

				String mem_id = req.getParameter("mem_id").trim();
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("參團會員:請勿空白");
				}
				
				

				joingroupVO joingroupVO = new joingroupVO();
				Integer gro_mnum = null;
				Integer gro_mnum_min =null;
				
				joingroupVO.setGro_id(gro_id);
				joingroupVO.setMem_id(mem_id);
				
				groupService groupSvc = new groupService();
				groupVO groupVO = groupSvc.getOneGro(gro_id);
				
				req.setAttribute("groupVO", groupVO);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group/listOneGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				joingroupService joingroupSvc = new joingroupService();
				
				//group目前人數更動
				gro_mnum = joingroupSvc.getMnum(gro_id)+1;
				joingroupSvc.updateMnum(gro_mnum,gro_id);
				//group揪團狀態更動
				gro_mnum_min = joingroupSvc.getMnumMin(gro_id);
				if(gro_mnum<gro_mnum_min) {
					joingroupSvc.updateStat(0,gro_id);
				}else {
					joingroupSvc.updateStat(1,gro_id);
				}
				
				joingroupSvc.addMem(gro_id,mem_id);
				
				//取得當前揪團資訊
				groupVO = groupSvc.getOneGro(gro_id);
				req.setAttribute("groupVO", groupVO);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = BF_url+"/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(BF_url+"/group/addJoinGroup.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("add_Member".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String gro_id = req.getParameter("gro_id");				

				/***************************2.開始新增資料***************************************/
				
				
				//取得當前揪團資訊
				groupService groupSvc = new groupService();
				groupVO groupVO = new groupVO();
				groupVO = groupSvc.getOneGro(gro_id);
				req.setAttribute("groupVO", groupVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = BF_url+"/group/addJoinGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group/lisOneGroup.jsp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		------------------------------------------------------------------------------------------------------------------------
		
		
		if ("delete_A".equals(action) || "delete_B".equals(action) || "delete_C".equals(action) || "delete_Join".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Integer gro_mnum = null;
			Integer gro_mnum_min =null;
	
			try {
				/***************************1.接收請求參數***************************************/
				String gro_id = new String(req.getParameter("gro_id"));
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.開始刪除資料***************************************/
				joingroupService joingroupSvc = new joingroupService();
				groupService groupSvc = new groupService();
				//group目前人數更動
				gro_mnum = joingroupSvc.getMnum(gro_id)-1;
				joingroupSvc.updateMnum(gro_mnum,gro_id);
				//group揪團狀態更動
				gro_mnum_min = joingroupSvc.getMnumMin(gro_id);
				if(gro_mnum<gro_mnum_min) {
					joingroupSvc.updateStat(0,gro_id);
				}else {
					joingroupSvc.updateStat(1,gro_id);
				}
				//刪除成員
				joingroupSvc.deleteGroMem(gro_id,mem_id);
				
				Set<joingroupVO> setMem= joingroupSvc.getAllMems(gro_id);
				Set<joingroupVO> setGro= joingroupSvc.getAllGros(mem_id);
				groupVO groupVO = groupSvc.getOneGro(gro_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/		
				req.setAttribute("listMems_ByGro", setMem);
				req.setAttribute("listGros_ByMem", setGro);
				req.setAttribute("groupVO", groupVO);
				
				String url=requestURL;
				if("delete_Join".equals(action)) {
					req.setAttribute("mem_id", mem_id);
					RequestDispatcher successView = req.getRequestDispatcher(BF_url+"/group/listAllGroup_Join.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}else {
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				if("delete_Join".equals(action)) {
					String url=requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}else {
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group_join/listAllJoinGroup.jsp");
					failureView.forward(req, res);
				}
				
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String gro_id = new String(req.getParameter("gro_id"));
				String mem_id = new String(req.getParameter("mem_id"));
				/***************************2.開始查詢資料****************************************/
				joingroupService joingroupSvc = new joingroupService();
				joingroupVO joingroupVO = joingroupSvc.getOneStar(gro_id,mem_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("joingroupVO", joingroupVO);         // 資料庫取出的empVO物件,存入req
				String url = BF_url+"/group_join/update_join_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_join/listAllJoinGroup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String url = new String(req.getParameter("requestURL"));
				String gro_id = new String(req.getParameter("gro_id"));
				String mem_id = new String(req.getParameter("mem_id"));
				
				Integer gro_star = null;
				try {
					gro_star = new Integer(req.getParameter("stars").trim());
				} catch (NumberFormatException e) {
					gro_star = 0;
					errorMsgs.add("評價至少1星");
				}
				
				groupService groupSvc = new groupService();
				groupVO groupVO = new groupVO();
				
				groupVO = groupSvc.getOneGro(gro_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mem_id", mem_id);
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				joingroupService joingroupSvc = new joingroupService();
				joingroupSvc.updateStar(gro_id,mem_id,gro_star);
				joingroupSvc.updateAstar(gro_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mem_id", mem_id);
				req.setAttribute("groupVO", groupVO); // 資料庫update成功後,正確的的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(BF_url+"/group_join/update_join_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}
}
