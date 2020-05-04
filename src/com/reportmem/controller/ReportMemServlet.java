package com.reportmem.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reportcoa.model.ReportCoaService;
import com.reportcoa.model.ReportCoaVO;
import com.reportmem.model.ReportMemService;
import com.reportmem.model.ReportMemVO;

/**
 * Servlet implementation class ReportMem
 */
@WebServlet("/ReportMemServlet")
public class ReportMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mem_id =req.getParameter("memid");
			
			if (mem_id == null || mem_id.trim().length() == 0) {
				errorMsgs.add("請輸入教練編號");
			} 
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/SelectReportCoach.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ReportMemService repmemSvc=new ReportMemService();
			ReportMemVO reportmemVO=repmemSvc.getOneReportmem1(mem_id);
			if(reportmemVO ==null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcoach/SelectReportCoach.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("reportmemVO", reportmemVO);
			String url= "/back_end/reportmember/ListAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportmember/SelectReportMember.jsp");
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
				ReportMemService repmemSvc=new ReportMemService();
				repmemSvc.deleteReportmem(repid);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url="/back_end/reportmember/ListAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportmember/ListAllMember.jsp");
					failureView.forward(req, res);
				}
				
		}
				//查詢修改的教練編號
				if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				try {
					/*************************** 1.接收請求參數 ****************************************/	
					String rep_id =req.getParameter("rep_id");
					System.out.println(rep_id);
					/*************************** 2.開始查詢資料 ****************************************/
					ReportMemService repmemSvc=new ReportMemService();
					ReportMemVO  reportmemVO=repmemSvc.getOneReportmem(rep_id);
					System.out.println(reportmemVO);
					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("reportmemVO", reportmemVO);
					
					String url = "/back_end/reportmember/update.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher( "/back_end/reportmember/ListAllMember.jsp");
					failureView.forward(req, res);
				}
		}
				if ("update".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try {
						/*************************** 1.接收請求參數 ****************************************/	
						String rep_id =req.getParameter("rep_id");
						System.out.println("11111111111111111111111111");
						System.out.println(rep_id);
						String rep_idReg = "^[(a-zA-Z0-9_)]{2,10}$";
						if (rep_id == null || rep_id.trim().length() == 0) {
							errorMsgs.add("編號: 請勿空白");
						} else if (!rep_id.trim().matches(rep_idReg)) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
						}
						String memid =req.getParameter("memid");
						String memidReg = "^[(a-zA-Z0-9_)]{2,10}$";
						if (memid == null || memid.trim().length() == 0) {
							errorMsgs.add("編號: 請勿空白");

						} else if (!memid.trim().matches(memidReg)) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
						}
						String content = req.getParameter("content").trim();
						if (content == null || content.trim().length() == 0) {
							errorMsgs.add("內容請勿空白");
						}
						String rep_sta=req.getParameter("rep_sta");
						ReportMemVO reportmemVO=new ReportMemVO();
						reportmemVO.setRep_id(rep_id);
						reportmemVO.setMem_id(memid);
						reportmemVO.setRep_cot(content);
						reportmemVO.setRep_sta(rep_sta);
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("reportmemVO", reportmemVO); // 含有輸入格式錯誤的empVO物件,也存入req

							RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportmember/update.jsp");
							failureView.forward(req, res);
							return;
						}
						/*************************** 2.開始修改資料 ***************************************/
						ReportMemService repmemSvc=new ReportMemService();
						reportmemVO=repmemSvc.updateReportmem(rep_id, memid, content, rep_sta);
						System.out.println(	repmemSvc.updateReportmem(rep_id, memid, content, rep_sta));
						/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
						req.setAttribute("reportmemVO", reportmemVO);
						String url="/back_end/reportmember/ListAllMember.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
						successView.forward(req, res);
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportmember/ListAllMember.jsp");
						failureView.forward(req, res);
					}
		}
				if("insert".equals(action)) {
					List<String> errorMsgs=new LinkedList<String>();
					req.setAttribute("errorMsgs",errorMsgs );
					String BF_url=req.getParameter("front_end");
					try {
						
						String mem_id =req.getParameter("mem_id");
						System.out.println("mem_id="+mem_id);
						
						String content = req.getParameter("content").trim();
						
						if (content == null || content.trim().length() == 0) {
							errorMsgs.add("內容請勿空白");
						}
						String rep_sta=req.getParameter("rep_sta");
						
						ReportMemVO reportmemVO=new ReportMemVO();
						reportmemVO.setMem_id(mem_id);
						reportmemVO.setRep_cot(content);
						reportmemVO.setRep_sta("0");
	
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("reportmemVO", reportmemVO); // 含有輸入格式錯誤的empVO物件,也存入req							
							RequestDispatcher failureView = req.getRequestDispatcher(BF_url+"/ReportMember/AddReportMember.jsp");
							failureView.forward(req, res);
							return;
						}
						/*2.開始新增資料 */
						ReportMemService repmemSvc=new ReportMemService();
						reportmemVO=repmemSvc.addReportmem(mem_id, content, rep_sta);//使用方法多載service 
						System.out.println(reportmemVO);
						
						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						req.setAttribute("reportmemVO", reportmemVO);
//						String url =BF_url+"/ReportMember/AddReportMember.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url);
//						// 新增成功後轉交listAllEmp.jsp
//						successView.forward(req, res);
						/*****************************************************/
						
					} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher(BF_url+"/ReportMember/AddReportMember.jsp");
							failureView.forward(req, res);
					}
			
				}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
