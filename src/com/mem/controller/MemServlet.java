package com.mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.coach.model.CoaVO;
import com.mem.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
if("forgetPsw".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
		try {
			String mem_email = new String(req.getParameter("mem_email").trim());
		
			System.out.println("mem_email");
			String psw = passWord("password");
			
			String to = mem_email;
			String subject = "密碼通知";
			String passRandom = psw;
			String messageText = "Hello! 請謹記此密碼了 " + passRandom + "      "+"不要再忘記了^^";
			
			MemVO memVO = new MemVO();
			memVO.setMem_email(mem_email);
			memVO.setMem_psw(psw);
			
			MemMailService memmailService = new MemMailService();
			memmailService.sendMail(to, subject, messageText);
			MemService memSvc = new MemService();
			memSvc.updatePsw(mem_email, psw);
			
			req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/front_end/mem/MemLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}	
		catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/mem/MemLogin.jsp");
			failureView.forward(req, res);
		}
}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_id = null;
				try {
					mem_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/select_page.jsp");
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
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/listAllMem.jsp");
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
				String mem_id = new String(req.getParameter("mem_id").trim());
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_gender == null || mem_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				
				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String pos_code = req.getParameter("pos_code").trim();
				if (pos_code == null || pos_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Part part = req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				
				String mem_sta = req.getParameter("mem_sta").trim();
				if (mem_sta == null || mem_sta.trim().length() == 0) {
					errorMsgs.add("會員狀態");
				}
				
				Integer mem_point = null;
				try {
					mem_point = new Integer(req.getParameter("mem_point").trim());
				} catch (NumberFormatException e) {
					mem_point = 0;
					errorMsgs.add("儲值點數請填數字.");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_email(mem_email);
				memVO.setMem_psw(mem_psw);
				memVO.setPhone(phone);
				memVO.setAddress(address);
				memVO.setPos_code(pos_code);
				memVO.setBirthday(birthday);
				memVO.setMem_pic(buf);
				memVO.setMem_sta(mem_sta);
				memVO.setMem_point(mem_point);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_name, mem_gender, mem_email, mem_psw,phone, address,pos_code,birthday,buf,mem_sta,mem_point);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front_end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/update_mem_input.jsp");
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
				
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_gender == null || mem_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				
				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String pos_code = req.getParameter("pos_code").trim();
				if (pos_code == null || pos_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號請勿空白");
				}
				System.out.print("xxxxxxxxxxxxxxxxxxxxx");
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
         
				Part part = req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				if (buf == null) {
					errorMsgs.add("圖片請勿空白");
				}
				
				
				String mem_sta = req.getParameter("mem_sta").trim();
				if (mem_sta == null || mem_sta.trim().length() == 0) {
					errorMsgs.add("會員狀態");
				}
				
				Integer mem_point = null;
				try {
					mem_point = new Integer(req.getParameter("mem_point").trim());
				} catch (NumberFormatException e) {
					mem_point = 0;
					errorMsgs.add("儲值點數請填數字.");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_email(mem_email);
				memVO.setMem_psw(mem_psw);
				memVO.setPhone(phone);
				memVO.setAddress(address);
				memVO.setPos_code(pos_code);
				memVO.setBirthday(birthday);
				memVO.setMem_pic(buf);
				memVO.setMem_sta(mem_sta);
				memVO.setMem_point(mem_point);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(mem_name, mem_gender, mem_email, mem_psw,phone, address,pos_code,birthday,buf,mem_sta,mem_point);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
        
		if ("insert_reg".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_gender == null || mem_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				String mem_email = req.getParameter("mem_email").trim();
				String mem_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員信箱: 請勿空白");
				} else if (!mem_email.trim().matches(mem_emailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員信箱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_psw = req.getParameter("mem_psw").trim();
				String mem_pswReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_psw.trim().matches(mem_pswReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_psw2 = req.getParameter("mem_psw2").trim();
				if (mem_psw2 == null || mem_psw2.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_email(mem_email);
				if (mem_psw.equals(mem_psw2)) {
					memVO.setMem_psw(mem_psw);

				} else {
					memVO.setMem_psw(mem_psw);
					errorMsgs.add("密碼不一致");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/mem/registrator.jsp");
					failureView.forward(req, res);
					return;
				}
				  String to = mem_email;
			      
			      String subject = "密碼通知";
			      
			      String member_name = mem_name;
			      
			      String messageText = "Hello! " + member_name + "恭喜你註冊成功!! 你現在已經是JUST健身的會員了 快來跟我們一起運動吧^^ " + " (帳號已經啟用)"+"              "+
					      "回首頁"+"35.194.167.164:8081/DA103G5/front_end/frod_end_Index/Index_Login.jsp";  
			       
			      MemMailService mailService = new MemMailService();
			      mailService.sendMail(to, subject, messageText);
				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addmemreg(mem_name, mem_gender, mem_email, mem_psw);
				/***************************

				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/front_end/mem/MemLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);
 
				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/mem/registrator.jsp");
//				failureView.forward(req, res);
//			}
		}
		

		if ("update_mem_personal".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_email = new String(req.getParameter("mem_email").trim());
			
				String mem_name = req.getParameter("mem_name");			
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String phone = req.getParameter("phone").trim();
				System.out.println(phone);
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String pos_code = req.getParameter("pos_code").trim();
				if (pos_code == null || pos_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Part part = req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] mem_pic = new byte[in.available()];
				in.read(mem_pic);
				in.close();
				
				

				MemVO memVO = new MemVO();
				memVO.setMem_email(mem_email);
				memVO.setMem_name(mem_name);
				memVO.setPhone(phone);
				memVO.setAddress(address);
				memVO.setPos_code(pos_code);
				memVO.setBirthday(birthday);
				memVO.setMem_pic(mem_pic);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/mem/MemManagePersonalUpdate.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.UpdateMemPer(mem_email,mem_name,phone, address,pos_code,birthday,mem_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front_end/mem/MemManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/MemManagePersonalUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		if ("login".equals(action)) { // �Ӧ�listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	        String mem_psw =null;
	        MemVO loginVO = new MemVO();
			try {
				/********************************************************************/
				
				String mem_useremail = req.getParameter("mem_useremail");
				String mem_userpsw = req.getParameter("mem_userpsw");
				System.out.println(mem_useremail);
				System.out.println(mem_userpsw);
				loginVO.setMem_email(mem_useremail);
				loginVO.setMem_psw(mem_userpsw);				
				/*****************************************************************/
				MemService memSvc = new MemService();
				mem_psw = memSvc.getMemPsw(mem_useremail);
				MemVO memVO = memSvc.getMemPsw2(mem_useremail);
				System.out.println("54546546646");
				System.out.println("mem_psw="+mem_psw);
				System.out.println("mem_userpsw="+mem_userpsw);
				if(mem_psw.equals(mem_userpsw)) {
					HttpSession session = req.getSession();
		            session.setAttribute("loginVO", loginVO);
		            session.setAttribute("memVO", memVO);
		            session.setAttribute("coaVO",null);
		            System.out.println("loginVO"+loginVO.getMem_email());
		            System.out.println("memVO"+ memVO.getMem_id());
		         try {                                                        
		        	 String location = (String) session.getAttribute("location");
		        	 System.out.println("Location1 = " + location);
		    
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
			session.removeAttribute("memVO");
			System.out.println("已移除");
//			
			String url = "/front_end/frod_end_Index/Index_Login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.�}�l�R�����***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		//------------------------------back_end----------------------------------
		if ("getOne_For_Display_back".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/mem/MemberSearch_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_id = null;
				try {
					mem_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/mem/MemberSearch_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/mem/MemberSearch_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/mem/Listonemem_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/MemberSearch_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update_back".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/mem/Update_mem_input_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/Listonemem_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update_back".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = new String(req.getParameter("mem_id").trim());
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_gender = req.getParameter("mem_gender").trim();
				if (mem_gender == null || mem_gender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				
				String mem_psw = req.getParameter("mem_psw").trim();
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String pos_code = req.getParameter("pos_code").trim();
				if (pos_code == null || pos_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Part part = req.getPart("mem_pic");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				
				String mem_sta = req.getParameter("mem_sta").trim();
				if (mem_sta == null || mem_sta.trim().length() == 0) {
					errorMsgs.add("會員狀態");
				}
				
				Integer mem_point = null;
				try {
					mem_point = new Integer(req.getParameter("mem_point").trim());
				} catch (NumberFormatException e) {
					mem_point = 0;
					errorMsgs.add("儲值點數請填數字.");
				}

				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_name(mem_name);
				memVO.setMem_gender(mem_gender);
				memVO.setMem_email(mem_email);
				memVO.setMem_psw(mem_psw);
				memVO.setPhone(phone);
				memVO.setAddress(address);
				memVO.setPos_code(pos_code);
				memVO.setBirthday(birthday);
				memVO.setMem_pic(buf);
				memVO.setMem_sta(mem_sta);
				memVO.setMem_point(mem_point);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/mem/Update_mem_input_back.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(mem_id, mem_name, mem_gender, mem_email, mem_psw,phone, address,pos_code,birthday,buf,mem_sta,mem_point);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/mem/Listonemem_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/Update_mem_input_back.jsp");
				failureView.forward(req, res);
			}
		}

//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//        	
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				
//				
//				String mem_name = req.getParameter("mem_name");
//				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String mem_gender = req.getParameter("mem_gender").trim();
//				if (mem_gender == null || mem_gender.trim().length() == 0) {
//					errorMsgs.add("性別請勿空白");
//				}
//				
//				String mem_email = req.getParameter("mem_email").trim();
//				if (mem_email == null || mem_email.trim().length() == 0) {
//					errorMsgs.add("會員帳號請勿空白");
//				}
//				
//				String mem_psw = req.getParameter("mem_psw").trim();
//				if (mem_psw == null || mem_psw.trim().length() == 0) {
//					errorMsgs.add("會員密碼請勿空白");
//				}
//				
//				String phone = req.getParameter("phone").trim();
//				if (phone == null || phone.trim().length() == 0) {
//					errorMsgs.add("電話請勿空白");
//				}
//				
//				String address = req.getParameter("address").trim();
//				if (address == null || address.trim().length() == 0) {
//					errorMsgs.add("地址請勿空白");
//				}
//				
//				String pos_code = req.getParameter("pos_code").trim();
//				if (pos_code == null || pos_code.trim().length() == 0) {
//					errorMsgs.add("郵遞區號請勿空白");
//				}
//				System.out.print("xxxxxxxxxxxxxxxxxxxxx");
//				java.sql.Date birthday = null;
//				try {
//					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
//				} catch (IllegalArgumentException e) {
//					birthday=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//         
//				Part part = req.getPart("mem_pic");
//				InputStream in = part.getInputStream();
//				byte[] buf = new byte[in.available()];
//				in.read(buf);
//				in.close();
//				if (buf == null) {
//					errorMsgs.add("圖片請勿空白");
//				}
//				
//				
//				String mem_sta = req.getParameter("mem_sta").trim();
//				if (mem_sta == null || mem_sta.trim().length() == 0) {
//					errorMsgs.add("會員狀態");
//				}
//
//				MemVO memVO = new MemVO();
//				memVO.setMem_name(mem_name);
//				memVO.setMem_gender(mem_gender);
//				memVO.setMem_email(mem_email);
//				memVO.setMem_psw(mem_psw);
//				memVO.setPhone(phone);
//				memVO.setAddress(address);
//				memVO.setPos_code(pos_code);
//				memVO.setBirthday(birthday);
//				memVO.setMem_pic(buf);
//				memVO.setMem_sta(mem_sta);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/mem/addMem.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				MemService memSvc = new MemService();
//				memVO = memSvc.addMem(mem_name, mem_gender, mem_email, mem_psw,phone, address,pos_code,birthday,buf,mem_sta);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/front_end/mem/listAllMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/mem/addMem.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete_back".equals(action)) { // �Ӧ�listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.�}�l�R�����***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/mem/Listallmem_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mem/Listallmem_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	public static String passWord(String password) {
		Integer num = 0;
		num = (int) (Math.random() * 14578652) + 1;
		String psw = Integer.toString(num);

		return psw;
	}
}
