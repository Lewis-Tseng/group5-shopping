package com.crs_detail.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coach.model.CoaService;
import com.coach.model.CoaVO;
import com.crs.model.CrsService;
import com.crs.model.CrsVO;
import com.crs_detail.model.CrsDetailService;
import com.crs_detail.model.CrsDetailVO;
import com.deposit.model.DepService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.res.model.ResService;
import com.res.model.ResVO;

public class CrsDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自reservation.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String crs_no = req.getParameter("crs_no");
				String mem_id = req.getParameter("mem_id");
				String crs_name = req.getParameter("crs_name");
				Integer crs_fee = new Integer(req.getParameter("crs_fee"));
				Integer mem_point = new Integer(req.getParameter("mem_point"));
				Integer cost = mem_point - crs_fee;	

				if(mem_point - crs_fee > 0 ) {
					/******************2.課程付款(取得會員點數及課程費)並更新資料庫************************/			
					MemService memSvc = new MemService(); 			
					memSvc.updatePoint(mem_id, cost);
					
					MemVO memVO = new MemVO();
					memVO = memSvc.getOneMem(mem_id);
					
					/******************3.新增課程費用到教練Deposit**************************/
					String noMem = null;
					String coa_id = req.getParameter("coa_id");
					String dep_sta = "0";
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.sql.Timestamp dep_day = java.sql.Timestamp.valueOf(df.format(new Date()));
					
					DepService depService = new DepService();
					depService.addDep(noMem, coa_id, crs_fee, dep_day, dep_sta);
					
					/******************4.課程付款(取得教練點數及課程費)並更新資料庫***********************/
					
					CoaService coaSvc = new CoaService();
					CoaVO coaVO = new CoaVO();
					coaVO =coaSvc.getCoaPoint(coa_id);
					Integer coa_point = coaVO.getCoa_point();
					Integer newPoint =  coa_point+ crs_fee;
					coaSvc.updateCoapoint(coa_id, newPoint);
					
					/******************5.新增會員報名課程**********************************************/
					CrsDetailVO crsDetailVO = new CrsDetailVO();
	
					crsDetailVO.setCrs_no(crs_no);
					crsDetailVO.setMem_id(mem_id);
					crsDetailVO.setCrs_name(crs_name);
					crsDetailVO.setCrs_fee(crs_fee);
	
					CrsDetailService crsDetailSvc = new CrsDetailService();
					crsDetailVO = crsDetailSvc.addCrsDetail(crs_no, mem_id, crs_name, crs_fee);
					
					/******************6.取出已報名人數並+1更新報名人數****************************/
					CrsVO  crsVO = new CrsVO();
					CrsService crsService = new CrsService ();
					
					crsVO = crsService.findReserved(crs_no);
					Integer reserved = crsVO.getReserved()+1;
					crsService.updateReserved(crs_no, reserved);	
					
					/******************7.重新將session送出去****************************/
					HttpSession session = req.getSession();
					session.setAttribute("memVO",memVO);
					
				}			
				/*************************** 8.完成所有程式,準備轉交(Send the Success view) ***********/
				String url = "/front_end/crs/CrsPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllCrs.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String res_no = new String(req.getParameter("res_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				CrsService crsSvc = new CrsService();
				crsSvc.deleteCrs(res_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
	}

}
