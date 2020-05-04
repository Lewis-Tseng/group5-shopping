package com.reportcoa.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.administrator.controller.MailService;
import com.coach.model.CoaService;
import com.coach.model.CoaVO;
import com.reportcoa.model.ReportCoaService;
import com.reportcoa.model.ReportCoaVO;
import com.reportcrs.model.CourseReportService;

@WebServlet("/ReportCoaServlet")
public class ReportCoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
		
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs );
			String BF_url=req.getParameter("front_end");
			try {
				
				String coa_id =req.getParameter("coaid");
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				} 
	

				
				ReportCoaVO reportcoaVO=new ReportCoaVO();
				reportcoaVO.setCoa_id(coa_id);
				reportcoaVO.setRep_cot(content);
				

				/*2.開始新增資料 */
				ReportCoaService repcoaSvc=new ReportCoaService();
				reportcoaVO=repcoaSvc.addReportcoa(coa_id, content);//使用方法多載service 
				System.out.println(reportcoaVO);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("reportcoaVO", reportcoaVO);
//				String url =BF_url+"/ReportCoach/AddReportCoach.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
				/*****************************************************/
				
			} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher(BF_url+"ReportCoach/AddReportCoach.jsp");
//					failureView.forward(req, res);
			}
	
		}
		
		//查詢一筆資料
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String coa_id =req.getParameter("coachid");
			System.out.println(coa_id);
			if (coa_id == null || coa_id.trim().length() == 0) {
				errorMsgs.add("請輸入教練編號");
			} 
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/SelectReportCoach.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ReportCoaService repcoaSvc=new ReportCoaService();
			ReportCoaVO reportcoaVO=repcoaSvc.getOneReportmem1(coa_id);
			if(reportcoaVO ==null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/SelectReportCoach.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("reportcoaVO", reportcoaVO);
			String url= "/back_end/reportcoach/ListOneCoach.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/SelectReportCoach.jsp");
			failureView.forward(req, res);
		}
}		
	
			if("delete".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/*************************** 1.接收請求參數 ***************************************/
					String repid=req.getParameter("rep_id");
					/*************************** 2.開始刪除資料 ***************************************/
					ReportCoaService repcoaSvc=new ReportCoaService();
					repcoaSvc.deleteReportcoa(repid);
					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url="/back_end/reportcoach/ListAllCoach.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/ListAllCoach.jsp");
						failureView.forward(req, res);
					}
			}
			//查詢修改的檢舉課程編號
			if ("ChangeStatus".equals(action)) { // 來自listAllEmp.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/	
				
				String rep_id =req.getParameter("rep_id");
				String coa_id= req.getParameter("coach_id");
				System.out.println("coa_id="+coa_id);
				CoaService coaSvc=new CoaService(); 
				List<CoaVO> list=coaSvc.getAll();
				for(CoaVO id:list) {
					if(id.getCoa_id().equals(coa_id)) {
						//寄驗證信開始	
						String email=id.getCoa_email();
						String subject = "檢舉警告";
						String name = id.getCoa_name();
						String report = "你已經被檢舉了，請注意您的行為是否不妥?如果再有下次扣你薪水，Just強身公司關心你";
						String messageText = "Hello!" + name  + "\n" + report;
						MailService mailService = new MailService();
						mailService.sendMail(email, subject, messageText);
//						寄驗證信結束
						System.out.println("成功寄出");
					}
				}
				
				/*************************** 2.開始查詢資料 ****************************************/
				ReportCoaService repcoaSvc=new ReportCoaService();
				String stat=repcoaSvc.getOneReportmem(rep_id).getRep_sta();
				if(stat.equals("1"))  //更改狀態
					stat="0";
				else{
					stat="1";
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				repcoaSvc.updateReportcoaStatus(rep_id, stat);
				String url= "/back_end/reportcoach/ListAllCoach.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher( "/back_end/reportcoach/ListAllCoach.jsp");
				failureView.forward(req, res);
			}
	}
//			if ("update".equals(action)) {
//				List<String> errorMsgs = new LinkedList<String>();
//				req.setAttribute("errorMsgs", errorMsgs);
//				try {
//					/*************************** 1.接收請求參數 ****************************************/	
//					String rep_id =req.getParameter("rep_id");
//					System.out.println(rep_id);
//					String rep_idReg = "^[(a-zA-Z0-9_)]{2,10}$";
//					if (rep_id == null || rep_id.trim().length() == 0) {
//						errorMsgs.add("編號: 請勿空白");
//					} else if (!rep_id.trim().matches(rep_idReg)) { // 以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
//					}
//					String coachid =req.getParameter("coachid");
//					String coa_idReg = "^[(a-zA-Z0-9_)]{2,10}$";
//					if (coachid == null || coachid.trim().length() == 0) {
//						errorMsgs.add("編號: 請勿空白");
//
//					} else if (!coachid.trim().matches(coa_idReg)) { // 以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
//					}
//					String content = req.getParameter("content").trim();
//					if (content == null || content.trim().length() == 0) {
//						errorMsgs.add("內容請勿空白");
//					}
//					String rep_sta=req.getParameter("rep_sta");
//					ReportCoaVO reportcoaVO=new ReportCoaVO();
//					reportcoaVO.setRep_id(rep_id);
//					reportcoaVO.setCoa_id(coachid);
//					reportcoaVO.setRep_cot(content);
//					reportcoaVO.setRep_sta(rep_sta);
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("reportcoaVO", reportcoaVO); // 含有輸入格式錯誤的empVO物件,也存入req
//
//						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/AddReportCoach.jsp");
//						failureView.forward(req, res);
//						return;
//					}
//					/*************************** 2.開始修改資料 ***************************************/
//					ReportCoaService repcoaSvc=new ReportCoaService();
//					reportcoaVO=repcoaSvc.updateReportcoa(rep_id,coachid, content, rep_sta);
//					System.out.println(repcoaSvc.updateReportcoa(rep_id,coachid, content, rep_sta));
//					/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
//					req.setAttribute("reportcoaVO", reportcoaVO);
//					String url="/back_end/reportcoach/ListAllCoach.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//					successView.forward(req, res);
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/ListAllCoach.jsp");
//					failureView.forward(req, res);
//				}
//	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
