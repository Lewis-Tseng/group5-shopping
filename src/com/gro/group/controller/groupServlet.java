package com.gro.group.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.gro.group.groupService;
import com.gro.group.groupVO;
import com.gro.joingroup.joingroupService;
import com.gro.joingroup.joingroupVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class groupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String BF_url = req.getParameter("BF_url");
		


		if ("getOne_For_Display".equals(action) || "getOne_For_Display_Create".equals(action) || "getOne_For_Display_Join".equals(action) || "getOne_For_Display_HJoin".equals(action) || "getOne_For_Display_HCreate".equals(action)) { // 來自select_page.jsp的請求
			
			String url = req.getParameter("requestURL");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("gro_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				};
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String gro_id = null;
				try {
					gro_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("揪團編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				groupService groupSvc = new groupService();
				groupVO groupVO = groupSvc.getOneGro(gro_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				joingroupService joingroupSvc = new joingroupService();
				Set<joingroupVO> groMember = joingroupSvc.getAllMems(gro_id);
				
				req.setAttribute("groMember", groMember);
				req.setAttribute("groupVO", groupVO); // 資料庫取出的empVO物件,存入req
				
				
				String url_success=null;
				if("getOne_For_Display".equals(action)) {
					String mem_id = req.getParameter("mem_id");
					req.setAttribute("mem_id", mem_id);
					url_success = BF_url+"/group/listOneGroup.jsp";
					
				}else if("getOne_For_Display_Create".equals(action)) {
					String mem_id = req.getParameter("mem_id");
					req.setAttribute("mem_id", mem_id);
					url_success = BF_url+"/group/listOneGroup_Create.jsp";

				}else if("getOne_For_Display_Join".equals(action)) {
					String mem_id = req.getParameter("mem_id");
					req.setAttribute("mem_id", mem_id);
					url_success = BF_url+"/group/listOneGroup_join.jsp";
					
				}else if("getOne_For_Display_HJoin".equals(action)) {
					String mem_id = req.getParameter("mem_id");
					req.setAttribute("mem_id", mem_id);
					url_success = BF_url+"/group/listOneGroup_History_Join.jsp";
					
				}else if("getOne_For_Display_HCreate".equals(action)) {
					String mem_id = req.getParameter("mem_id");
					req.setAttribute("mem_id", mem_id);
					url_success = BF_url+"/group/listOneGroup_History_Create.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url_success); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(url);
//				failureView.forward(req, res);
//			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String gro_name = req.getParameter("gro_name");				
				if (gro_name == null || gro_name.trim().length() == 0) {
					errorMsgs.add("揪團名稱: 請勿空白");
				}

				String gro_cid = req.getParameter("gro_cid").trim();
				if (gro_cid == null || gro_cid.trim().length() == 0) {
					errorMsgs.add("類別請勿空白");
				}
				
				String mem_id = req.getParameter("mem_id").trim();
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("建團人請勿空白");
				}
				
				String gro_info = req.getParameter("gro_info").trim();
				if (gro_info == null || gro_info.trim().length() == 0) {
					errorMsgs.add("揪團介紹請勿空白");
				}
				
//				java.sql.Date gro_sig_stime = null;
//				try {
//					gro_sig_stime = java.sql.Date.valueOf(req.getParameter("gro_sig_stime").trim());
//				} catch (IllegalArgumentException e) {
//					gro_sig_stime=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				java.sql.Date gro_sig_ftime = null;
				try {
					gro_sig_ftime = java.sql.Date.valueOf(req.getParameter("gro_sig_ftime").trim());
				} catch (IllegalArgumentException e) {
					gro_sig_ftime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date gro_stime = null;
				try {
					gro_stime = java.sql.Date.valueOf(req.getParameter("gro_stime").trim());
				} catch (IllegalArgumentException e) {
					gro_stime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date gro_ftime = null;
				try {
					gro_ftime = java.sql.Date.valueOf(req.getParameter("gro_ftime").trim());
				} catch (IllegalArgumentException e) {
					gro_ftime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer gro_mnum_min = null;
				try {
					gro_mnum_min = new Integer(req.getParameter("gro_mnum_min").trim());
				} catch (NumberFormatException e) {
					gro_mnum_min = 0;
					errorMsgs.add("人數請填數字.");
				}
				
				Integer gro_mnum_max = null;
				try {
					gro_mnum_max = new Integer(req.getParameter("gro_mnum_max").trim());
				} catch (NumberFormatException e) {
					gro_mnum_max = 0;
					errorMsgs.add("人數請填數字.");
				}
				
				Part part = req.getPart("gro_logo_pic");
				InputStream in = part.getInputStream();
				byte[] gro_logo_pic = new byte[in.available()];
				in.read(gro_logo_pic);
				in.close();
				

				groupVO groupVO = new groupVO();
				groupVO.setGro_cid(gro_cid);
				groupVO.setMem_id(mem_id);
				groupVO.setGro_name(gro_name);
//				groupVO.setGro_sig_stime(gro_sig_stime);
				groupVO.setGro_sig_ftime(gro_sig_ftime);
				groupVO.setGro_stime(gro_stime);
				groupVO.setGro_ftime(gro_ftime);
				groupVO.setGro_mnum_min(gro_mnum_min);
				groupVO.setGro_mnum_max(gro_mnum_max);
				groupVO.setGro_info(gro_info);
				groupVO.setGro_logo_pic(gro_logo_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group/addGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				groupService groupSvc = new groupService();
				groupVO = groupSvc.addGro(gro_cid,mem_id,gro_name,gro_sig_ftime,gro_stime,gro_ftime,gro_mnum_min,gro_mnum_max,gro_info,gro_logo_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = BF_url+"/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group/addGroup.jsp");
				failureView.forward(req, res);
			}
		}
//2019/10/30
		if ("list_create".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_id = new String(req.getParameter("mem_id"));


				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("mem_id", mem_id);    // 資料庫取出的set物件,存入request
		
				String url = null;
				
					url = BF_url+"/group/listAllGroup_Create.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
//2019/10/31
		if ("list_join".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_id = new String(req.getParameter("mem_id"));
				
				groupService groupSvc = new groupService();
				joingroupService groupjoinSvc = new joingroupService();
				
				Set<joingroupVO> joingroupVO =groupjoinSvc.getAllGros(mem_id);
				List<groupVO> joinGroInfo = new ArrayList<groupVO>();
				
				for (joingroupVO aGro_id:joingroupVO) {				
					joinGroInfo.add(groupSvc.getOneGro(aGro_id.getGro_id()));
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("groupVO", joinGroInfo);    // 資料庫取出的set物件,存入request
		
				String url = null;
				
					url = BF_url+"/group/listAllGroup_Join.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
//		------------------------------------------------------------------------------------------------------------------------
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String gro_id = new String(req.getParameter("gro_id"));
				
				/***************************2.開始查詢資料****************************************/
				groupService groupSvc = new groupService();
				groupVO groupVO = groupSvc.getOneGro(gro_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groupVO", groupVO);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("mem_id", groupVO.getMem_id());
				String url = BF_url+"/group/updateGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group/listAllGroup_Create");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_Create".equals(action) || "delete_HCreate".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String gro_id = new String(req.getParameter("gro_id"));
				
				/***************************2.開始刪除資料***************************************/
				groupService groupSvc = new groupService();
				groupVO groupVO = new groupVO();
				groupVO = groupSvc.getOneGro(gro_id);
				req.setAttribute("mem_id", groupVO.getMem_id());		
				
				groupSvc.deleteGro(gro_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				String url_success=null;
				if("delete_Create".equals(action)) {
					url_success = BF_url+"/group/listAllGroup_Create.jsp";
					
				}else if("delete_HCreate".equals(action)) {
					url_success = BF_url+"/group/listAllGroup_Create_History.jsp";
					
				}
				RequestDispatcher successView = req.getRequestDispatcher(url_success);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group/listAllGroup_Create.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("0");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String gro_id = new String(req.getParameter("gro_id").trim());
				
				String gro_name = req.getParameter("gro_name");				
				if (gro_name == null || gro_name.trim().length() == 0) {
					errorMsgs.add("揪團名稱: 請勿空白");
				}
				
				String gro_cid = req.getParameter("gro_cid").trim();
				if (gro_cid == null || gro_cid.trim().length() == 0) {
					errorMsgs.add("類別請勿空白");
				}
				
				String mem_id = req.getParameter("mem_id").trim();
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("建團人請勿空白");
				}
				
				String gro_info = req.getParameter("gro_info").trim();
				if (gro_info == null || gro_info.trim().length() == 0) {
					errorMsgs.add("揪團介紹請勿空白");
				}
				
				java.sql.Date gro_sig_ftime = null;
				try {
					gro_sig_ftime = java.sql.Date.valueOf(req.getParameter("gro_sig_ftime").trim());
				} catch (IllegalArgumentException e) {
					gro_sig_ftime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date gro_stime = null;
				try {
					gro_stime = java.sql.Date.valueOf(req.getParameter("gro_stime").trim());
				} catch (IllegalArgumentException e) {
					gro_stime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date gro_ftime = null;
				try {
					gro_ftime = java.sql.Date.valueOf(req.getParameter("gro_ftime").trim());
				} catch (IllegalArgumentException e) {
					gro_ftime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer gro_mnum_min = null;
				try {
					gro_mnum_min = new Integer(req.getParameter("gro_mnum_min").trim());
				} catch (NumberFormatException e) {
					gro_mnum_min = 0;
					errorMsgs.add("人數請填數字.");
				}
				
				Integer gro_mnum_max = null;
				try {
					gro_mnum_max = new Integer(req.getParameter("gro_mnum_max").trim());
				} catch (NumberFormatException e) {
					gro_mnum_max = 0;
					errorMsgs.add("人數請填數字.");
				}
				
				Part part = req.getPart("gro_logo_pic");
				InputStream in = part.getInputStream();
				byte[] gro_logo_pic = new byte[in.available()];
				in.read(gro_logo_pic);
				in.close();
				
				Integer gro_mnum = new Integer(req.getParameter("gro_mnum"));

				groupVO groupVO = new groupVO();
				groupVO.setGro_id(gro_id);
				groupVO.setGro_cid(gro_cid);
				groupVO.setMem_id(mem_id);
				groupVO.setGro_name(gro_name);
				groupVO.setGro_sig_ftime(gro_sig_ftime);
				groupVO.setGro_stime(gro_stime);
				groupVO.setGro_ftime(gro_ftime);
				groupVO.setGro_mnum(gro_mnum);
				groupVO.setGro_mnum_min(gro_mnum_min);
				groupVO.setGro_mnum_max(gro_mnum_max);
				groupVO.setGro_info(gro_info);
				groupVO.setGro_logo_pic(gro_logo_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(BF_url+"/group/updateGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				groupService groupSvc = new groupService();
				groupSvc.updateGro(gro_id,gro_cid,mem_id,gro_name,gro_sig_ftime,gro_stime,gro_ftime,gro_mnum,gro_mnum_min,gro_mnum_max,gro_info,gro_logo_pic);
				
				groupVO = groupSvc.getOneGro(gro_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groupVO", groupVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("mem_id", groupVO.getMem_id());
				String url = BF_url+"/group/listOneGroup_Create.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(BF_url+"/group/updateGroup.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
