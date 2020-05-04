package com.res.controller;

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
import com.res.model.ResMailService;
import com.res.model.ResService;
import com.res.model.ResVO;

public class ResServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addRes.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String coa_id = req.getParameter("coa_id");
//				String mem_id = req.getParameter("mem_id");
				String coa_id = req.getParameter("coa_id");
				String mem_id = req.getParameter("mem_id");
				java.sql.Date res_day = null;
				res_day = java.sql.Date.valueOf(req.getParameter("res_day").trim());

				if (res_day == null) {
					errorMsgs.add("請選擇預約日期");
				}
				String res_status = "0";

				ResVO resVO = new ResVO();

				resVO.setCoa_id(coa_id);

				resVO.setMem_id(mem_id);

				resVO.setRes_day(res_day);

				resVO.setRes_status(res_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resVO", resVO); // 含有輸入格式錯誤的resVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/res/addRes.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ResService resSvc = new ResService();
				resVO = resSvc.addRes(mem_id, coa_id, res_status, res_day);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/res/CoachPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/res/addRes.jsp");
				failureView.forward(req, res);
			}
		}

		if ("sendEmail".equals(action)) { // 來自resConfirm.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String to = req.getParameter("mem_email");
				System.out.println(to);
				String subject = "預約拒絕通知信";
				String memberName = req.getParameter("memberName");
				String denyReason = req.getParameter("denyReason");
				String messageText = "親愛的 " + memberName + " 先生/小姐 您好:\n\n您的預約已被拒絕，拒絕原因如下: \n" + denyReason;
				
				ResMailService resMailService = new ResMailService();
				resMailService.sendMail(to, subject, messageText);

				String res_no = req.getParameter("res_no");
				String res_status = "2";

				ResVO resVO = new ResVO();
				resVO.setRes_status(res_status);
				resVO.setRes_no(res_no);

				ResService resSvc = new ResService();
				resVO = resSvc.updateStatus(res_no, res_status);
				/***************************
				 * 3.發送信件完成,準備轉交原畫面(Send the Success view)
				 ***********/
				String url = "/front_end/res/resConfirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/res/resConfirm.jsp");
				failureView.forward(req, res);
			}
		}

		if ("resAccept".equals(action)) { // 來自resConfirm.jsp的請求
			System.out.println("我有近來");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String res_no = req.getParameter("res_no");
				String res_status = "1";
				System.out.println(res_no);
				ResVO resVO = new ResVO();
				resVO.setRes_status(res_status);
				resVO.setRes_no(res_no);

				ResService resSvc = new ResService();
				resVO = resSvc.updateStatus(res_no, res_status);

				/***************************
				 * 3.發送信件完成,準備轉交原畫面(Send the Success view)
				 ***********/
				String url = "/front_end/res/resConfirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/res/resConfirm.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
