package com.friendList.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.friendList.model.*;

import redis.clients.jedis.Jedis;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class FriendListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
		/*送出交友邀請*/
        if ("add".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Jedis jedis = new Jedis("localhost", 6379);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String my_id = req.getParameter("my_id");
				String his_id = req.getParameter("his_id");
				/***************************2.開始新增資料***************************************/
				jedis.hset("friendList:"+my_id, his_id, "0");
				jedis.hset("friendList:"+his_id, my_id, "1");
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/friendList/FriendManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friendList/FriendManagePersonal.jsp");
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
		/*接受交友邀請*/
        if ("confirm".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Jedis jedis = new Jedis("localhost", 6379);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String my_id = req.getParameter("my_id");
				String his_id = req.getParameter("his_id");
				/***************************2.開始新增***************************************/
				
				jedis.hset("friendList:"+my_id,his_id,"2");
				jedis.hset("friendList:"+his_id,my_id,"2");
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/friendList/FriendManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friendList/FriendManagePersonal.jsp");
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
        /*拒絕交友邀請*/
        if ("confirmNo".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Jedis jedis = new Jedis("localhost", 6379);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String my_id = req.getParameter("my_id");
				String his_id = req.getParameter("his_id");
				/***************************2.開始新增***************************************/
				
				jedis.hset("friendList:"+my_id,his_id,"3");
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/friendList/FriendManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friendList/FriendManagePersonal.jsp");
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
       
		/*刪除好友 和 等待太久放棄交友邀請*/
        if ("delete".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			Jedis jedis = new Jedis("localhost", 6379);
			
			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String my_id = req.getParameter("my_id");
				String his_id = req.getParameter("his_id");
				
				
				/***************************2.刪除好友****************************************/
				//刪除好友
				jedis.hdel("friendList:"+my_id,his_id);
				jedis.hdel("friendList:"+his_id,my_id);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/friendList/FriendManagePersonal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
			
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/friendList/FriendManagePersonal.jsp");
				failureView.forward(req, res);
				
			} finally {
				if (jedis != null) {
					jedis.close();

				}
			}
			
		}
        
        
        
        
        
        
        
	}
}
