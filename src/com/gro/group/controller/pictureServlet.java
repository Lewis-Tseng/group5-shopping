package com.gro.group.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.gro.group.groupDAO;
import com.gro.group.groupService;
import com.gro.group.groupVO;
import com.gro.joingroup.joingroupService;
import com.gro.joingroup.joingroupVO;
import com.gro.pic.pictureService;
import com.gro.pic.pictureVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class pictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String BF_url = req.getParameter("BF_url");


		if ("getPics_By_Gro".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String gro_id = new String(req.getParameter("gro_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				pictureService pictureSvc = new pictureService();
				Set<pictureVO> set = pictureSvc.getAllPics(gro_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listPics_ByGro", set);    // 資料庫取出的set物件,存入request
		
				String url = null;
				
					url = BF_url+"/group_picture/listPicsByGro.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

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
				String mem_id = req.getParameter("mem_id");
				String gro_id = req.getParameter("gro_id");		
				String url = req.getParameter("requestURL");

				Part part = req.getPart("gro_pic");
				InputStream in = part.getInputStream();
				byte[] gro_pic = new byte[in.available()];
				in.read(gro_pic);
				in.close();
				
				
				pictureVO pictureVO = new pictureVO();
				pictureVO.setGro_id(gro_id);
				pictureVO.setGro_pic(gro_pic);
				
				groupService groupSvc = new groupService();
				groupVO groupVO = new groupVO();
				groupVO = groupSvc.getOneGro(gro_id);
				
				req.setAttribute("groupVO", groupVO);
				req.setAttribute("mem_id", mem_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pictureVO", pictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				pictureService pictureSvc = new pictureService();
				pictureVO = pictureSvc.addPic(gro_id,gro_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(BF_url+"/group_picture/addPic.jsp");
//				failureView.forward(req, res);
//			}
		}
				
		
		if ("delete_A".equals(action) || "delete_B".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String gro_pid = new String(req.getParameter("gro_pid"));
				
				/***************************2.開始刪除資料***************************************/
				pictureService pictureSvc = new pictureService();
				pictureSvc.deletePic(gro_pid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				
				String url=null;
				if("delete_A".equals(action)) {
					url = BF_url+"/group_picture/listAllGroupPic.jsp";
				}else if("delete_B".equals(action)) {
					url = BF_url+"/group_picture/listPicsByGro.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group_picture/listAllGroupPic.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
