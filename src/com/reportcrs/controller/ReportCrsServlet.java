package com.reportcrs.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crs.model.CrsService;
import com.crs.model.CrsVO;

import com.reportcrs.model.CourseReportService;
import com.reportcrs.model.CourseReportVO;

/**
 * Servlet implementation class ReportcrsServlet
 */
@WebServlet("/ReportcrsServlet")
public class ReportCrsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		//查詢一筆資料
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String crsid =req.getParameter("crsid");
			System.out.println(crsid);
			if (crsid == null || crsid.trim().length() == 0) {
				errorMsgs.add("請輸入課程編號");
			} 
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			CourseReportService repcrsSvc=new CourseReportService();
			CourseReportVO reportcrsVO= repcrsSvc.getOneCrs1(crsid);
			if(reportcrsVO ==null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("reportcrsVO", reportcrsVO);
			String url= "/back_end/reportcourse/ListAllCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
			failureView.forward(req, res);
			}
		}	
		
		if ("getOne_For_crsname".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String crsid =req.getParameter("crsname");
			System.out.println(crsid);
			if (crsid == null || crsid.trim().length() == 0) {
				errorMsgs.add("請輸入課程編號");
			} 
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			CourseReportService repcrsSvc=new CourseReportService();
			CourseReportVO reportcrsVO= repcrsSvc.getOneCrs1(crsid);
			if(reportcrsVO ==null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("reportcrsVO", reportcrsVO);
			String url= "/back_end/reportcourse/ListAllCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/SelectReportCourse.jsp");
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
			
			/*************************** 2.開始查詢資料 ****************************************/
			CourseReportService repcrsSvc=new CourseReportService();
			String stat=repcrsSvc.getOneCrs(rep_id).getRep_status();
			if(stat.equals("1"))  //更改狀態
				stat="0";
			else{
				stat="1";
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			repcrsSvc.updateCourseReportStatus(rep_id, stat);
			String url= "/back_end/reportcourse/ListAllCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher( "/back_end/reportcourse/ListAllCourse.jsp");
			failureView.forward(req, res);
		}
}
//		if ("update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/*************************** 1.接收請求參數 ****************************************/	
//				String rep_id =req.getParameter("rep_id");
//				System.out.println(rep_id);
//				String rep_idReg = "^[(a-zA-Z0-9_)]{2,10}$";
//				if (rep_id == null || rep_id.trim().length() == 0) {
//					errorMsgs.add("編號: 請勿空白");
//				} else if (!rep_id.trim().matches(rep_idReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
//				}
//				String crsid =req.getParameter("crsid");
//				System.out.println(crsid);
//				String crsidReg = "^[(a-zA-Z0-9_)]{2,10}$";
//				if (crsid == null || crsid.trim().length() == 0) {
//					errorMsgs.add("編號: 請勿空白");
//
//				} else if (!crsid.trim().matches(crsidReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
//				}
//				String content = req.getParameter("content").trim();
//				if (content == null || content.trim().length() == 0) {
//					errorMsgs.add("內容請勿空白");
//				}
//				String rep_sta=req.getParameter("rep_sta");
//				CourseReportVO  reportcrsVO=new CourseReportVO();
//				reportcrsVO.setCrs_repid(rep_id);
//				reportcrsVO.setCrs_no(crsid);
//				reportcrsVO.setRep_content(content);
//				reportcrsVO.setRep_status(rep_sta);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("reportcrsVO", reportcrsVO); // 含有輸入格式錯誤的empVO物件,也存入req
//
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/updateReportCrs.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				/*************************** 2.開始修改資料 ***************************************/
//				CourseReportService repcrsSvc=new CourseReportService();
//				reportcrsVO=repcrsSvc.updateCourseReport(rep_id, crsid, content, rep_sta);
//				System.out.println(repcrsSvc.updateCourseReport(rep_id, crsid, content, rep_sta));
//				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
//				req.setAttribute("reportcrsVO", reportcrsVO);
//				String url= "/back_end/reportcourse/ListAllCourse.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/updateReportCrs.jsp");
//				failureView.forward(req, res);
//			}
//}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String repid=req.getParameter("rep_id");
				/*************************** 2.開始刪除資料 ***************************************/
				CourseReportService repcrsSvc=new CourseReportService();
				 repcrsSvc.deleteCourseReport(repid);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				 String url= "/back_end/reportcourse/ListAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/reportcourse/AddReportCourse.jsp");
					failureView.forward(req, res);
				}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs );
			String BF_url=req.getParameter("front_end");
			try {
				
				String crsid=req.getParameter("crsid");
				System.out.println(crsid);
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				CourseReportVO  reportcrsVO=new CourseReportVO();
				 reportcrsVO.setCrs_no(crsid);
				 reportcrsVO.setRep_content(content);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("reportcrsVO", reportcrsVO); // 含有輸入格式錯誤的empVO物件,也存入req
////					
//					RequestDispatcher failureView1 = req.getRequestDispatcher("/front_end/ReportCourse/AddReportCourse.jsp");
//					failureView1.forward(req, res);
//					return;
//				}
				/*2.開始新增資料 */
				CourseReportService repcrsSvc=new CourseReportService();
				reportcrsVO=repcrsSvc.addCourseReport(crsid, content);//使用方法多載service 
				System.out.println(reportcrsVO);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("reportcrsVO", reportcrsVO);
//				String url =BF_url+"/ReportCourse/AddReportCourse.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				// 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
				/*****************************************************/
				
			} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher(BF_url+"/ReportCourse/AddReportCourse.jsp");
					failureView.forward(req, res);
			}
	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}

}
