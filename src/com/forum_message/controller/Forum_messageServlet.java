package com.forum_message.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.forum_message.model.Forum_messageVO;

import redis.clients.jedis.Jedis;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;
import com.forum_message.model.Forum_messageService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Forum_messageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
///*閱讀文章**********************************************/		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String forum_id = req.getParameter("forum_id");
//				/***************************2.開始查詢資料*****************************************/
//				ForumService forumSvc = new ForumService();
//				ForumVO forumVO = forumSvc.getOneForum(forum_id);
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
//				String url = "/front_end/forum/listOneForum.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
/*編輯留言1********************************************/			
		if ("getOneMsg_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String forum_msg_id =req.getParameter("forum_msg_id");
				
				/***************************2.開始查詢資料****************************************/
				Forum_messageService forum_messageSvc = new Forum_messageService();
				Forum_messageVO forum_messageVO = forum_messageSvc.getOneForum_message(forum_msg_id);			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("forum_messageVO", forum_messageVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/forum_message/update_forum_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum_message/listAllForum_message.jsp");
				failureView.forward(req, res);
			}
		}

/*編輯留言2****************************************************************************************/		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String forum_msg_id = req.getParameter("forum_msg_id");
				String forum_msg_info = req.getParameter("forum_msg_info");
				
				Part part = req.getPart("forum_msg_pic");
				InputStream in = part.getInputStream();
				byte[] forum_msg_pic = new byte[in.available()];
				in.read(forum_msg_pic);
				in.close();
				
				Forum_messageVO forum_messageVO = new Forum_messageVO();
				forum_messageVO.setForum_msg_info(forum_msg_info);
				forum_messageVO.setForum_msg_pic(forum_msg_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forum_messageVO", forum_messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum_message/update_forum_message_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Forum_messageService forum_messageSvc = new Forum_messageService();
				forum_messageSvc.updateForum_message(forum_msg_info,forum_msg_pic, forum_msg_id);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front_end/forum/listAllForum_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum_message/update_forum_message_input.jsp");
				failureView.forward(req, res);
			}
		}
		
/*新增留言*****************************************************************************************/
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Jedis jedis = new Jedis("localhost", 6379);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_id = req.getParameter("mem_id");
//				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!FORUM_ID.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String forum_id = req.getParameter("forum_id");
				if (forum_id == null || forum_id.trim().length() == 0) {
					errorMsgs.add("請選擇文章");
				}
				String forum_msg_info = req.getParameter("forum_msg_info");
				if (forum_msg_info == null || forum_msg_info.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				Part part = req.getPart("forum_msg_pic");
				InputStream in = part.getInputStream();
				byte[] forum_msg_pic = new byte[in.available()];
				in.read(forum_msg_pic);
				in.close();
	
				Forum_messageVO forum_messageVO = new Forum_messageVO();
				forum_messageVO.setMem_id(mem_id);
				forum_messageVO.setForum_id(forum_id);
				forum_messageVO.setForum_msg_info(forum_msg_info);
				forum_messageVO.setForum_msg_pic(forum_msg_pic);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forum_messageVO", forum_messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/forum_message/addForum_message.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Forum_messageService forum_messageSvc = new Forum_messageService();
				forum_messageSvc.addForum_message(mem_id, forum_id, forum_msg_info, forum_msg_pic);
				List<String> list = jedis.lrange( "favoriteForum:"+mem_id, 0, -1 );
				/***************************讀出原本頁面的文章+留言***************************************/
				//列出單篇文章
				ForumService forumSvc = new ForumService();
				ForumVO forumVO = forumSvc.getOneForum(forum_id);
				//文章列出留言用
				Set<Forum_messageVO> set= forumSvc.getMsgByForum(forum_id);
				//列出單篇文章
				req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("favoriteForum", list);
				//文章列出留言用
				req.setAttribute("listMsg_ByForum", set);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/forum/listOneForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum_message/addForum_message.jsp");
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
		
///* 刪除文章 */		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String forum_id = req.getParameter("forum_id");
//				
//				/***************************2.開始刪除資料***************************************/
//				ForumService ForumSvc = new ForumService();
//				ForumSvc.deleteForum(forum_id);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front_end/forum/listAllForum.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
/*前台使用者刪除留言(實際只有更改 上下架狀態)*/	
		if ("ChangeStat".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String forum_msg_id = req.getParameter("forum_msg_id");
				
					
				/***************************2.開始查詢資料****************************************/
				Forum_messageService forum_messageSvc = new Forum_messageService();
	
					String forum_msg_stat="0";  //下架留言
						
//				memSvc.getOneMember(ForumVO.MEM_ID).mem_name
				forum_messageSvc.updateForum_message(forum_msg_stat,forum_msg_id );
					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("forumVO", forumSvc);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/forum_message/listAllForum_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum_message/listAllForum_message.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
