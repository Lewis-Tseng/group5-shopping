package com.deposit.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.coach.model.CoaService;
import com.coach.model.CoaVO;
import com.deposit.model.DepService;
import com.deposit.model.DepVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


public class DepServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
if("insert_dep".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
		try {
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)(session.getAttribute("memVO"));
			
			String mem_id = memVO.getMem_id();
		
			
			Integer point = new Integer(memVO.getMem_point());
			//String mem_no = req.getParameter("mem_no");
			System.out.println(mem_id);
			
			String str = req.getParameter("dep_money");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入儲值金額");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
				failureView.forward(req,  res);
				return;
			}
			Integer dep_moneyStr = null;
			Integer dep_money = null;
			try {
				dep_moneyStr = new Integer(str);
			} catch (Exception e) {
				errorMsgs.add("請輸入正確金額");
			}
			try {
				dep_money = new Integer(str)+point;
			} catch (Exception e) {
				errorMsgs.add("請輸入正確金額");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
				failureView.forward(req,  res);
				return;
			}	
			MemService memSvc = new MemService();
			
			try{
				memSvc.updatePoint(mem_id, dep_money);
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
				failureView.forward(req, res);
			}
			DepService depSvc = new DepService();
			DepVO depVO = new DepVO();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.sql.Timestamp dep_day = java.sql.Timestamp.valueOf(df.format(new Date()));
			depVO = depSvc.addDep2(mem_id, dep_moneyStr,dep_day);
			memVO.setMem_point(dep_money);
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/mem/MemManagePersonal.jsp");
			successView.forward(req, res);
		}	
		catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
			failureView.forward(req, res);
		}
}
if("apply_DepCoa".equals(action)) {
	
	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
System.out.println("143543654");
	
try {
	HttpSession session = req.getSession();
	CoaVO coaVO = (CoaVO)(session.getAttribute("coaVO"));
	String dep_id = req.getParameter("dep_id");
	
	DepService depSvc = new DepService();
	DepVO depVO = depSvc.getOneDep(dep_id);
	
	String coa_id = coaVO.getCoa_id();
	
	if(coa_id.equals(depVO.getCoa_id())) {
		String dep_sta=req.getParameter("dep_sta");
		depSvc.updateSta(dep_id, dep_sta);
		
	}
    
	
	
}	
catch (Exception e) {
	errorMsgs.add(e.getMessage());
	RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
	failureView.forward(req, res);
}
}

if("apply_DepCoa_back".equals(action)) {
	
	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
System.out.println("143543654");
	
try {
//	HttpSession session = req.getSession();
//	CoaVO coaVO = (CoaVO)(session.getAttribute("coaVO"));
//	
//	String coa_id = coaVO.getCoa_id();
//
//	
//	Integer Coapoint = new Integer(coaVO.getCoa_point());
	
	String dep_id = req.getParameter("dep_id");
	
	DepService depSvc = new DepService();
	DepVO depVO = depSvc.getOneDep(dep_id);
	CoaService coaSvc = new CoaService();
	List<CoaVO> list = coaSvc.getAll();
	Integer pointNew = null;
	for(CoaVO coaVO : list) {
	if(depVO.getCoa_id().equals(coaVO.getCoa_id())) {
		
		Integer point = new Integer(coaVO.getCoa_point());
		Integer dep_money = new Integer(depVO.getDep_money());

		pointNew =point - dep_money;
		coaSvc.updateCoapoint(coaVO.getCoa_id(), pointNew);
		String dep_sta=req.getParameter("dep_sta");
		depSvc.updateSta(dep_id, dep_sta);	
	}
	}
	

	

		
    
	
	
}	
catch (Exception e) {
	errorMsgs.add(e.getMessage());
	RequestDispatcher failureView = req.getRequestDispatcher("/front_end/dep/MemManagePersonalDep.jsp");
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
				String str = req.getParameter("dep_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入儲值編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/dep/Depositcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String dep_id = null;
				try {
					dep_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("儲值編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/dep/Depositcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DepService depSvc = new DepService();
				DepVO depVO = depSvc.getOneDep(dep_id);
				if (depVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/dep/Depositcheck_back.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depVO", depVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/dep/Listonedep_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/dep/Depositcheck_back.jsp");
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
				String dep_id = new String(req.getParameter("dep_id"));
				
				/***************************2.開始查詢資料****************************************/
				DepService depSvc = new DepService();
				DepVO depVO = depSvc.getOneDep(dep_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("depVO", depVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/dep/Update_dep_input_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/dep/Listalldep_back.jsp");
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
				String dep_id = new String(req.getParameter("dep_id").trim());
				
				
				String mem_id= req.getParameter("mem_id").trim();
				
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String coa_id = req.getParameter("coa_id").trim();
				
				if (coa_id == null || coa_id.trim().length() == 0) {
					errorMsgs.add("教練編號請勿空白");
				}
				
				
				Integer dep_money = null;
				try {
					dep_money = new Integer(req.getParameter("dep_money").trim());
				} catch (NumberFormatException e) {
					dep_money = 0;
					errorMsgs.add("儲值金額請填數字.");
				}

				
				java.sql.Timestamp dep_day = null;
				try {
					dep_day = java.sql.Timestamp.valueOf(req.getParameter("dep_day").trim());
				} catch (IllegalArgumentException e) {
					dep_day=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				
				String dep_sta = req.getParameter("dep_sta").trim();
				if (dep_sta == null || dep_sta.trim().length() == 0) {
					errorMsgs.add("儲值狀態");
				}

				DepVO depVO = new DepVO();
				depVO.setDep_id(dep_id);
				depVO.setMem_id(mem_id);
				depVO.setCoa_id(coa_id);
				depVO.setDep_money(dep_money);
				depVO.setDep_day(dep_day);
				depVO.setDep_sta(dep_sta);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("depVO", depVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/dep/Update_dep_input_back.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料*****************************************/
				DepService depSvc = new DepService();
				depVO = depSvc.updateDep(dep_id,mem_id, coa_id, dep_money, dep_day, dep_sta);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depVO", depVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/dep/Listonedep_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/dep/Update_dep_input_back.jsp");
				failureView.forward(req, res);
			}
		}

       
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String dep_id = new String(req.getParameter("dep_id"));
				
				/***************************2.�}�l�R�����***************************************/
				DepService depSvc = new DepService();
				depSvc.deleteDep(dep_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/dep/Listalldep_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/dep/Listalldep_back.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

