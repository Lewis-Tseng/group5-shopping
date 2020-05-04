package com.coach.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.coach.model.*;
 
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class CoachServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action) || "ResIndex".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("coa_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入教練編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coa/Coachcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String coa_id = null;
				try {
					coa_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("教練編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coa/Coachcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coa_id);
				if (coaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coa/Coachcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coaVO", coaVO); // 資料庫取出的empVO物件,存入req
				String url = null;
				if ("getOne_For_Display".equals(action)) {
					url = "/back_end/coa/Listonecoa_back.jsp";
				} else if ("ResIndex".equals(action)) {
					url = "/front_end/res/addRes.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa/Coachcheck_back.jsp");
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
				String coa_id = new String(req.getParameter("coa_id"));
				
				/***************************2.開始查詢資料****************************************/
				CoaService coaSvc = new CoaService();
				CoaVO coaVO = coaSvc.getOneCoa(coa_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("coaVO", coaVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/coa/Update_coa_input_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa/Listallcoach_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String coa_id = new String(req.getParameter("coa_id").trim());
				
				String coa_name = req.getParameter("coa_name");
				String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coa_name == null || coa_name.trim().length() == 0) {
					errorMsgs.add("教練姓名: 請勿空白");
				} else if(!coa_name.trim().matches(coa_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String coa_gender = req.getParameter("coa_gender").trim();
				if (coa_gender == null || coa_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String coa_email = req.getParameter("coa_email").trim();
				if (coa_email == null || coa_email.trim().length() == 0) {
					errorMsgs.add("教練帳號請勿空白");
				}
				
				String coa_psw = req.getParameter("coa_psw").trim();
				if (coa_psw == null || coa_psw.trim().length() == 0) {
					errorMsgs.add("教練密碼請勿空白");
				}
				
				String expert = req.getParameter("expert").trim();
				if (expert == null || expert.trim().length() == 0) {
					errorMsgs.add("專長請勿空白");
				}
				
				Part part = req.getPart("license");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				
				Part part2 = req.getPart("coa_pic");
				InputStream in2 = part2.getInputStream();
				byte[] buf2 = new byte[in2.available()];
				in2.read(buf2);
				in2.close();
				
				String coa_sta = req.getParameter("coa_sta").trim();
				if (coa_sta == null || coa_sta.trim().length() == 0) {
					errorMsgs.add("教練狀態");
				}
				
				String coa_intro = req.getParameter("coa_intro").trim();
				if (coa_intro == null || coa_intro.trim().length() == 0) {
					errorMsgs.add("教練自我介紹請勿空白");
				}
				
				String coa_video = req.getParameter("coa_video").trim();
				if (coa_video == null || coa_video.trim().length() == 0) {
					errorMsgs.add("教練影片請勿空白");
				}
				
				Integer coa_point = null;
				try {
					coa_point = new Integer(req.getParameter("coa_point").trim());
				} catch (NumberFormatException e) {
					coa_point = 0;
					errorMsgs.add("儲值點數請填數字.");
				}

				CoaVO coaVO = new CoaVO();
				coaVO.setCoa_id(coa_id);
				coaVO.setCoa_name(coa_name);
				coaVO.setCoa_gender(coa_gender);
				coaVO.setCoa_email(coa_email);
				coaVO.setCoa_psw(coa_psw);
				coaVO.setExpert(expert);
				coaVO.setLicense(buf);
				coaVO.setCoa_pic(buf2);
				coaVO.setCoa_sta(coa_sta);
				coaVO.setCoa_intro(coa_intro);
				coaVO.setCoa_video(coa_video);
				coaVO.setCoa_point(coa_point);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coaVO", coaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coa/Update_coa_input_back.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				CoaService coaSvc = new CoaService();
				coaSvc.updateCoa(coa_id, coa_name, coa_gender, coa_email, coa_psw,expert,buf,buf2,coa_sta,coa_intro,coa_video,coa_point);
				coaVO = coaSvc.getOneCoa(coa_id);
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coaVO", coaVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/coa/Listonecoa_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa/Update_coa_input_back.jsp");
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
				
				
				String coa_name = req.getParameter("coa_name");
				String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coa_name == null || coa_name.trim().length() == 0) {
					errorMsgs.add("教練姓名: 請勿空白");
				} else if(!coa_name.trim().matches(coa_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String coa_gender = req.getParameter("coa_gender").trim();
				if (coa_gender == null || coa_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String coa_email = req.getParameter("coa_email").trim();
				if (coa_email == null || coa_email.trim().length() == 0) {
					errorMsgs.add("教練帳號請勿空白");
				}
				
				String coa_psw = req.getParameter("coa_psw").trim();
				if (coa_psw == null || coa_psw.trim().length() == 0) {
					errorMsgs.add("教練密碼請勿空白");
				}
				
				String expert = req.getParameter("expert").trim();
				if (expert == null || expert.trim().length() == 0) {
					errorMsgs.add("專長請勿空白");
				}
				
				Part part = req.getPart("license");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				
				Part part2 = req.getPart("coa_pic");
				InputStream in2 = part2.getInputStream();
				byte[] buf2 = new byte[in2.available()];
				in2.read(buf2);
				in2.close();
				
				String coa_sta = req.getParameter("coa_sta").trim();
				if (coa_sta == null || coa_sta.trim().length() == 0) {
					errorMsgs.add("會員狀態");
				}
				
				String coa_intro = req.getParameter("coa_intro").trim();
				if (coa_intro == null || coa_intro.trim().length() == 0) {
					errorMsgs.add("教練自我介紹請勿空白");
				}
				
				String coa_video = req.getParameter("coa_video").trim();
				if (coa_video == null || coa_video.trim().length() == 0) {
					errorMsgs.add("教練影片請勿空白");
				}
				
				Integer coa_point = null;
				try {
					coa_point = new Integer(req.getParameter("coa_point").trim());
				} catch (NumberFormatException e) {
					coa_point = 0;
					errorMsgs.add("儲值點數請填數字.");
				}

				CoaVO coaVO = new CoaVO();
				
				coaVO.setCoa_name(coa_name);
				coaVO.setCoa_gender(coa_gender);
				coaVO.setCoa_email(coa_email);
				coaVO.setCoa_psw(coa_psw);
				coaVO.setExpert(expert);
				coaVO.setLicense(buf);
				coaVO.setCoa_pic(buf2);
				coaVO.setCoa_sta(coa_sta);
				coaVO.setCoa_intro(coa_intro);
				coaVO.setCoa_video(coa_video);
				coaVO.setCoa_point(coa_point);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coaVO", coaVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coa/addCoa.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				CoaService coaSvc = new CoaService();
				coaVO = coaSvc.addCoa( coa_name, coa_gender, coa_email, coa_psw,expert,buf,buf2,coa_sta,coa_intro,coa_video,coa_point);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back_end/coa/listAllCoa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa/addCoa.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert_reg_coa".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String coa_name = req.getParameter("coa_name");
				String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coa_name == null || coa_name.trim().length() == 0) {
					errorMsgs.add("教練姓名: 請勿空白");
				} else if (!coa_name.trim().matches(coa_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String coa_gender = req.getParameter("coa_gender").trim();
				if (coa_gender == null || coa_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				String coa_email = req.getParameter("coa_email").trim();
				String coa_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (coa_email == null || coa_email.trim().length() == 0) {
					errorMsgs.add("教練信箱: 請勿空白");
				} else if (!coa_email.trim().matches(coa_emailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練信箱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String coa_psw = req.getParameter("coa_psw").trim();
				String coa_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coa_psw == null || coa_psw.trim().length() == 0) {
					errorMsgs.add("教練姓名: 請勿空白");
				} else if (!coa_psw.trim().matches(coa_pswReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String coa_psw2 = req.getParameter("coa_psw2").trim();
				if (coa_psw2 == null || coa_psw2.trim().length() == 0) {
					errorMsgs.add("教練密碼請勿空白");
				}
				
				Part part = req.getPart("license");
				InputStream in = part.getInputStream();
				byte[] license = new byte[in.available()];
				in.read(license);
				in.close();

				CoaVO coaVO = new CoaVO();
				coaVO.setCoa_name(coa_name);
				coaVO.setCoa_gender(coa_gender);
				coaVO.setCoa_email(coa_email);				
				coaVO.setLicense(license);
				
				
				if (coa_psw.equals(coa_psw2)) {
					coaVO.setCoa_psw(coa_psw);

				} else {
					coaVO.setCoa_psw(coa_psw);
					errorMsgs.add("密碼不一致");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coaVO", coaVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/coa/Registrator_Coa.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				CoaService coaSvc = new CoaService();
				coaVO = coaSvc.addcoareg(coa_name, coa_gender, coa_email, coa_psw,license,"0");

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/front_end/mem/MemLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);
 
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/coa/Registrator_Coa.jsp");
				failureView.forward(req, res);
			}
		}
        	if ("update_coa_personal".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String coa_email = req.getParameter("coa_email").trim();
				
				String coa_name = req.getParameter("coa_name");
				String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (coa_name == null || coa_name.trim().length() == 0) {
					errorMsgs.add("教練姓名: 請勿空白");
				} else if(!coa_name.trim().matches(coa_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("教練姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String expert = req.getParameter("expert").trim();
				if (expert == null || expert.trim().length() == 0) {
					errorMsgs.add("專長請勿空白");
				}
				byte[] pic=null;
				byte[] pic2=null;
				Part part=req.getPart("license");
				
				String filename = getFileNameFromPart(part);
				if (filename!= null && part.getContentType()!=null) {
					InputStream in = part.getInputStream();
					pic = new byte[in.available()];
					in.read(pic);
					in.close();
				}else {
					pic=null;
				}
				
				Part part2=req.getPart("coa_pic");
				
				String filename2 = getFileNameFromPart(part2);
				if (filename2!= null && part2.getContentType()!=null) {
					InputStream in2 = part2.getInputStream();
					pic2 = new byte[in2.available()];
					in2.read(pic2);
					in2.close();
				}else {
					pic2=null;
				}
				
				
				
				String coa_intro = req.getParameter("coa_intro").trim();
				if (coa_intro == null || coa_intro.trim().length() == 0) {
					errorMsgs.add("教練自我介紹請勿空白");
				}
				
				String coa_video = req.getParameter("coa_video").trim();
				if (coa_video == null || coa_video.trim().length() == 0) {
					errorMsgs.add("教練影片請勿空白");
				}
				
				

				CoaVO coaVO = new CoaVO();
				coaVO.setCoa_email(coa_email);
				coaVO.setCoa_name(coa_name);
				coaVO.setExpert(expert);
				coaVO.setLicense(pic);
				coaVO.setCoa_pic(pic2);
				coaVO.setCoa_intro(coa_intro);
				coaVO.setCoa_video(coa_video);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("coaVO", coaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/coa/CoaManagePersonalUpdate.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				CoaService coaSvc = new CoaService();
				coaVO = coaSvc.UpdateCoaPer(coa_email,coa_name,expert,pic,pic2,coa_intro,coa_video);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("coaVO", coaVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front_end/coa/CoaManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/coa/CoaManagePersonalUpdate.jsp");
				failureView.forward(req, res);
			}
		}
        	if ("login".equals(action)) { // �Ӧ�listAllMem.jsp

    			List<String> errorMsgs = new LinkedList<String>();
    			// Store this set in the request scope, in case we need to
    			// send the ErrorPage view.
    			req.setAttribute("errorMsgs", errorMsgs);
    	        String coa_psw =null;
    	        CoaVO loginVO = new CoaVO();
    			try {
    				/***************************1.�����ШD�Ѽ�***************************************/
    				String coa_useremail = req.getParameter("coa_useremail");
    				String coa_userpsw = req.getParameter("coa_userpsw");
    				loginVO.setCoa_email(coa_useremail);
    				loginVO.setCoa_psw(coa_userpsw);
    				System.out.println(coa_useremail);
    				System.out.println(coa_userpsw);

    				/***************************2.�}�l�R�����***************************************/
    				CoaService coaSvc = new CoaService();
    				coa_psw = coaSvc.getCoaPsw(coa_useremail);
    				CoaVO coaVO = coaSvc.getCoaPsw2(coa_useremail);
    				System.out.println("54546546646");
    				System.out.println("coa_psw="+coa_psw);
    				System.out.println("coa_userpsw="+coa_userpsw);
    				if(coa_psw.equals(coa_userpsw)) {
    					HttpSession session = req.getSession();
    		            session.setAttribute("loginVO", loginVO);
    		            session.setAttribute("coaVO",coaVO);
    		            session.setAttribute("memVO", null);
    		            System.out.println("coaVO"+coaVO.getCoa_id());
        				System.out.println("loginVO"+ loginVO.getCoa_email());
    		           
    					try {                                                        
    				         String location = (String) session.getAttribute("location");
    				         if (location != null) {
    				           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
    				           res.sendRedirect(location);            
    				           return;
    				         }
    				       }catch (Exception ignored) { 
    				    	   
    				       }
    					String url = "/front_end/frod_end_Index/Index_Login.jsp";
    					RequestDispatcher SuccessView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
    					SuccessView.forward(req, res);
    				}else {
    					req.setAttribute("loginVO", loginVO);
    					errorMsgs.add("密碼錯誤");
    					String url = "/front_end/mem/MemLogin.jsp";
    					RequestDispatcher failureView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
    					failureView.forward(req, res);
    				}
    				
    				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
    				
    				
    				/***************************��L�i�઺���~�B�z**********************************/
    			} catch (Exception e) {    				
    				errorMsgs.add("此帳號不存在");
    				RequestDispatcher failureView = req
    						.getRequestDispatcher("/front_end/mem/MemLogin.jsp");
    				failureView.forward(req, res);
    			}
    		}
		
        	if ("signout".equals(action)) { 
    			HttpSession session=req.getSession();
    			System.out.println(session.toString());
    			session.removeAttribute("coaVO");
    			System.out.println("已移除");    			
    			String url = "/front_end/frod_end_Index/Index_Login.jsp";
    			RequestDispatcher successView = req.getRequestDispatcher(url);
    			successView.forward(req, res);
    		}
        	
        	
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String coa_id = new String(req.getParameter("coa_id"));
				
				/***************************2.�}�l�R�����***************************************/
				CoaService coaSvc = new CoaService();
				coaSvc.deleteCoa(coa_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/coa/Listallcoach_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa/Listallcoach_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
