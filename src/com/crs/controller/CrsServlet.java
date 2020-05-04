package com.crs.controller;

import java.io.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.crs.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CrsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action) || "CrsIndex".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("crs_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String crs_no = null;
				try {
					crs_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("課程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CrsService crsSvc = new CrsService();
				CrsVO crsVO = crsSvc.getOneCrs(crs_no);
				if (crsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("crsVO", crsVO); // 資料庫取出的crsVO物件,存入req
				String url = null; // "/front_end/crs/listOneCrs.jsp"

				if ("getOne_For_Display".equals(action)) {
					url = "/front_end/crs/listOneCrs.jsp";
				} else if ("CrsIndex".equals(action)) {
					url = "/front_end/crs/reservationCrs.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCrs.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String crs_no = new String(req.getParameter("crs_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				CrsService crsSvc = new CrsService();
				CrsVO crsVO = crsSvc.getOneCrs(crs_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("crsVO", crsVO); // 資料庫取出的crsVO物件,存入req
				String url = "/crs/update_crs_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_crs_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/crs/listAllCrs.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_crs_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String crs_no = new String(req.getParameter("crs_no").trim());

				String coa_id = req.getParameter("coa_id");

				String crs_type_no = req.getParameter("crs_type_no");
				String crs_name = req.getParameter("crs_name");
				System.out.println(crs_type_no);
				System.out.println(crs_name);
				String crs_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (crs_name == null || crs_name.trim().length() == 0) {
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!crs_name.trim().matches(crs_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱: 只能是中文字，且長度必需在2到之10間");
				}

				String crs_content = req.getParameter("crs_content");
				if (crs_content == null || crs_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}

				String week_crs = req.getParameter("week_crs");
				if (week_crs == null || week_crs.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				byte[] crs_img = getPictureByteArray(req.getPart("file").getInputStream());

				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
				} catch (IllegalArgumentException e) {
					start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}

				Integer crs_fee = null;
				try {
					crs_fee = new Integer(req.getParameter("crs_fee").trim());
				} catch (NumberFormatException e) {
					crs_fee = 0;
					errorMsgs.add("課程價格請填數字.");
				}

				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp
							.valueOf("1111-11-11 " + req.getParameter("start_time").trim() + ":00");
				} catch (IllegalArgumentException e) {
					start_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始時間!");
				}

				java.sql.Timestamp end_time = null;
				try {
					end_time = java.sql.Timestamp.valueOf("1111-11-11 " + req.getParameter("end_time").trim() + ":00");
				} catch (IllegalArgumentException e) {
					end_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束時間!");
				}

				CrsVO crsVO = new CrsVO();
				crsVO.setCrs_no(crs_no);
				crsVO.setCoa_id(coa_id);
				crsVO.setCrs_type_no(crs_type_no);
				crsVO.setCrs_name(crs_name);
				crsVO.setCrs_fee(crs_fee);
				crsVO.setCrs_content(crs_content);
				crsVO.setStart_date(start_date);
				crsVO.setEnd_date(end_date);
				crsVO.setWeek_crs(week_crs);
				crsVO.setStart_time(start_time);
				crsVO.setEnd_time(end_time);
				crsVO.setCrs_img(crs_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("crsVO", crsVO); // 含有輸入格式錯誤的crsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/crs/update_crs_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CrsService crsSvc = new CrsService();
				crsVO = crsSvc.updateCrs(crs_no, coa_id, crs_type_no, crs_name, week_crs, crs_content, crs_fee,
						start_date, end_date, start_time, end_time, crs_img);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("crsVO", crsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/crs/listOneCrs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/crs/update_crs_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addCrs.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String coa_id = req.getParameter("coa_id");

				String crs_type_no = req.getParameter("crs_type_no");
				String crs_name = req.getParameter("crs_name");
				String crs_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z)]{3,30}$";
				if (crs_name == null || crs_name.trim().length() == 0) {
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!crs_name.trim().matches(crs_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱: 長度必需在3到10之間");
				}

				String crs_content = req.getParameter("crs_content");
				if (crs_content == null || crs_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}

				String[] week_crsArray = req.getParameterValues("week_crs");
				String week_crs = "";
				String week_crsValues[] = { "0", "0", "0", "0", "0", "0", "0" };

				if (week_crsArray == null) {
					errorMsgs.add("請勾選每周上課日");
				} else {

					for (int i = 0; i < week_crsArray.length; i++) {
						int j = Integer.parseInt(week_crsArray[i]);
						week_crsValues[j] = "1";
					}

					for (int i = 0; i < 7; i++) {
						week_crs += week_crsValues[i];
					}
				}
				
				Integer limit =new Integer(req.getParameter("limit"));

				byte[] crs_img = getPictureByteArray(req.getPart("file").getInputStream());

				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date").trim());
				} catch (IllegalArgumentException e) {
					start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}

				Integer crs_fee = null;
				try {
					crs_fee = new Integer(req.getParameter("crs_fee").trim());
				} catch (NumberFormatException e) {
					crs_fee = 0;
					errorMsgs.add("課程價格請填數字.");
				}

				java.sql.Timestamp start_time = null;
				try {
					start_time = java.sql.Timestamp
							.valueOf("1111-11-11 " + req.getParameter("start_time").trim() + ":00");
				} catch (IllegalArgumentException e) {
					start_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入開始時間!");
				}

				java.sql.Timestamp end_time = null;
				try {
					end_time = java.sql.Timestamp.valueOf("1111-11-11 " + req.getParameter("end_time").trim() + ":00");
				} catch (IllegalArgumentException e) {
					end_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束時間!");
				}



				CrsVO crsVO = new CrsVO();

				crsVO.setCoa_id(coa_id);
				crsVO.setCrs_type_no(crs_type_no);
				crsVO.setCrs_name(crs_name);
				crsVO.setCrs_fee(crs_fee);
				crsVO.setCrs_content(crs_content);
				crsVO.setStart_date(start_date);
				crsVO.setEnd_date(end_date);
				crsVO.setStart_time(start_time);
				crsVO.setEnd_time(end_time);
				crsVO.setLimit(limit);
				crsVO.setCrs_img(crs_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("crsVO", crsVO); // 含有輸入格式錯誤的crsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/crs/addCrs.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CrsService crsSvc = new CrsService();
				crsVO = crsSvc.addCrs(coa_id, crs_type_no, crs_name, week_crs, crs_content, crs_fee, start_date,
						end_date, start_time, end_time, limit, crs_img);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/crs/addCrs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/crs/addCrs.jsp");
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
				String crs_no = new String(req.getParameter("crs_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				CrsService crsSvc = new CrsService();
				crsSvc.deleteCrs(crs_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/crs/listAllCrs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/crs/listAllCrs.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public static byte[] getPictureByteArray(InputStream path) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = path.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		path.close();

		return baos.toByteArray();
	}
}
