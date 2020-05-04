package com.forum.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.forum.model.ForumVO;
import com.forum_message.model.*;

import redis.clients.jedis.Jedis;

import com.forum.model.ForumService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ForumServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
/*閱讀文章+留言**********************************************/		
		if ("getOne_For_DisplaylistMsg_byForum".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			Jedis jedis = new Jedis("localhost", 6379);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String forum_id = req.getParameter("forum_id");
				String mem_id = req.getParameter("mem_id");
				System.out.println("收藏"+mem_id);
				/***************************2.開始查詢資料*****************************************/
				ForumService forumSvc = new ForumService();
				
				//遊覽數+1 
				Integer hit = forumSvc.getOneHit(forum_id);
					if(!jedis.sismember("hit:"+forum_id + mem_id , "1")){
						jedis.sadd("hit:"+forum_id+mem_id,"1" );
						jedis.expire("hit:"+forum_id+mem_id, 86400);
						hit++;
						forumSvc.setOneHit(forum_id, hit);
						req.setAttribute("hit_life", null);
					}else {
						Long hit_life= jedis.ttl("hit:"+forum_id+mem_id);
						req.setAttribute("hit_life", hit_life);
					}
				//查詢文章
				ForumVO forumVO = forumSvc.getOneForum(forum_id);
				//文章列出留言用
				Set<Forum_messageVO> set= forumSvc.getMsgByForum(forum_id); 
				//查詢我的收藏
				List<String> list = jedis.lrange("favoriteForum:"+mem_id, 0, -1 );
				

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				//文章內容
				req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
				//我的收藏list
				req.setAttribute("favoriteForum", list);
				//文章列出留言用
				req.setAttribute("listMsg_ByForum", set);
				
				
				String url = "/front_end/forum/listOneForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
//				System.out.println(errorMsgs);
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
		
	/*編輯文章1********************************************/			
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String forum_id =req.getParameter("forum_id");
				
				/***************************2.開始查詢資料****************************************/
				ForumService forumSvc = new ForumService();
				ForumVO forumVO = forumSvc.getOneForum(forum_id);			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/forum/update_forum_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
				failureView.forward(req, res);
			}
		}

/*編輯文章2********************************************/		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			Jedis jedis = new Jedis("localhost", 6379);
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String forum_id = req.getParameter("forum_id");
				String forum_cls_id = req.getParameter("forum_cls_id");
				String forum_title = req.getParameter("forum_title").trim();
				String forum_info = req.getParameter("forum_info2");
				String mem_id = req.getParameter("mem_id");
				
				Part part = req.getPart("forum_pic");
				InputStream in = part.getInputStream();
				byte[] forum_pic = new byte[in.available()];
				in.read(forum_pic);
				in.close();
//				String FORUM_TITLE_Rule = "{2,20}$";
//				if (FORUM_TITLE == null || FORUM_TITLE.trim().length() == 0) {
//					errorMsgs.add("文章標題: 請勿空白");
//				} else if(!FORUM_TITLE.trim().matches(FORUM_TITLE_Rule)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("文章標題: 長度必需在2到20之間");
//	            }
				
				if (forum_info == null || forum_info.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				} else if(forum_info.trim().length() <= 5) {
					errorMsgs.add("文章內容過於短少,請大於5字");
				}
				

				ForumVO forumVO = new ForumVO();
				forumVO.setForum_cls_id(forum_cls_id);
				forumVO.setForum_title(forum_title);
				forumVO.setForum_info(forum_info);
				forumVO.setForum_pic(forum_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/update_forum_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ForumService ForumSvc = new ForumService();
				ForumSvc.updateForum(forum_cls_id,forum_title,forum_info,forum_id,forum_pic);
				forumVO = ForumSvc.getOneForum(forum_id);
				//文章列出留言用
				ForumService forumSvc2 = new ForumService();
				Set<Forum_messageVO> set= forumSvc2.getMsgByForum(forum_id); 
				
				List<String> list = jedis.lrange( "favoriteForum:"+mem_id, 0, -1 ); 
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				//文章列出留言用
				req.setAttribute("favoriteForum", list);
				req.setAttribute("listMsg_ByForum", set);
				req.setAttribute("forumVO", forumVO); // 資料庫update成功後,正確的的empVO物件,存入req
				//遊覽次數
				Long hit_life= jedis.ttl("hit:"+forum_id+mem_id);
				req.setAttribute("hit_life", hit_life);
				String url = "/front_end/forum/listOneForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum/update_forum_input.jsp");
				failureView.forward(req, res);
			}finally {
				if (jedis != null) {
					jedis.close();

				}
			}
		}
/*新增文章*/
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_id = req.getParameter("mem_id");
//				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_id == null || mem_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!FORUM_ID.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String forum_cls_id = req.getParameter("forum_cls_id");
				if (forum_cls_id == null || forum_cls_id.trim().length() == 0) {
					errorMsgs.add("類別請勿空白");
				}
				String forum_title = req.getParameter("forum_title");
				if (forum_title == null || forum_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String forum_info = req.getParameter("forum_info");
				if (forum_info == null || forum_info.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				Part part = req.getPart("forum_pic");
				InputStream in = part.getInputStream();
				byte[] forum_pic = new byte[in.available()];
				in.read(forum_pic);
				in.close();
				
				ForumVO forumVO = new ForumVO();
				forumVO.setMem_id(mem_id);
				forumVO.setForum_cls_id(forum_cls_id);
				forumVO.setForum_title(forum_title);
				forumVO.setForum_info(forum_info);
				forumVO.setForum_pic(forum_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forum_addVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/forum/addForum.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ForumService forumSvc = new ForumService();
				forumVO = forumSvc.addForum(mem_id, forum_cls_id, forum_title, forum_info, forum_pic);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/forum/listAllForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum/addForum.jsp");
				failureView.forward(req, res);
			}
		}
//前台使用者刪除文章
		if ("deleteForum".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String forum_id = new String(req.getParameter("forum_id"));
				/***************************2.開始查詢資料****************************************/
				ForumService forumSvc = new ForumService();
				forumSvc.updateForum("0",forum_id );
					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("forumVO", forumSvc);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/forum/listAllForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
				failureView.forward(req, res);
			}
		}
//前台使用者收藏文章
				if ("addFavorite".equals(action)) { // 來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					Jedis jedis = new Jedis("localhost", 6379);
					
					try {
						/***************************1.接收請求參 數****************************************/
						String forum_id = new String(req.getParameter("forum_id"));
						String mem_id = new String(req.getParameter("mem_id")); ;
						/***************************2.開始新增資料****************************************/
						jedis.rpush("favoriteForum:"+mem_id, forum_id);
						
						/***************************3.開始查詢資料*****************************************/
						List<String> list = jedis.lrange( "favoriteForum:"+mem_id, 0, -1 ); 
						ForumService forumSvc = new ForumService();
						ForumVO forumVO = forumSvc.getOneForum(forum_id);
						//文章列出留言用
						Set<Forum_messageVO> set= forumSvc.getMsgByForum(forum_id); 
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("favoriteForum", list);
						//文章列出留言用
						req.setAttribute("listMsg_ByForum", set);
						//遊覽次數
						Long hit_life= jedis.ttl("hit:"+forum_id+mem_id);
						req.setAttribute("hit_life", hit_life);
						String url = "/front_end/forum/listOneForum.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
						failureView.forward(req, res);
					}finally {
						if (jedis != null) {
							jedis.close();

						}
					}
				}		
//前台使用者刪除收藏文章 在閱讀一篇文章時
				if ("deleteFavorite".equals(action)) { // 來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					Jedis jedis = new Jedis("localhost", 6379);
					
					try {
						/***************************1.接收請求參 數****************************************/
						String forum_id = new String(req.getParameter("forum_id"));
						String mem_id = new String(req.getParameter("mem_id")); ;
						/***************************2.開始刪除資料****************************************/
						jedis.lrem("favoriteForum:"+mem_id,0,forum_id);  
						
						System.out.println(forum_id);
						System.out.println(mem_id);
						/***************************3.開始查詢資料*****************************************/
						List<String> list = jedis.lrange( "favoriteForum:"+mem_id, 0, -1 ); 
						ForumService forumSvc = new ForumService();
						ForumVO forumVO = forumSvc.getOneForum(forum_id);
						//文章列出留言用
						Set<Forum_messageVO> set= forumSvc.getMsgByForum(forum_id); 
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("favoriteForum", list);
						//文章列出留言用
						req.setAttribute("listMsg_ByForum", set);
						//遊覽次數
						Long hit_life= jedis.ttl("hit:"+forum_id+mem_id);
						req.setAttribute("hit_life", hit_life);
						String url = "/front_end/forum/listOneForum.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
						failureView.forward(req, res);
					}finally {
						if (jedis != null) {
							jedis.close();

						}
					}
				}				
//前台使用者刪除收藏文章 在討論區首頁時
				if ("deleteFavorite2".equals(action)) { // 來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					Jedis jedis = new Jedis("localhost", 6379);
					
					try {
						/***************************1.接收請求參 數****************************************/
						String forum_id = new String(req.getParameter("forum_id"));
						String mem_id = new String(req.getParameter("mem_id")); ;
						/***************************2.開始刪除資料****************************************/
						jedis.lrem("favoriteForum:"+mem_id,0,forum_id);  
						
						System.out.println(forum_id);
						System.out.println(mem_id);
						/***************************3.開始查詢資料*****************************************/
						List<String> list = jedis.lrange( "favoriteForum:"+mem_id, 0, -1 ); 
						ForumService forumSvc = new ForumService();
						ForumVO forumVO = forumSvc.getOneForum(forum_id);
						//文章列出留言用
						Set<Forum_messageVO> set= forumSvc.getMsgByForum(forum_id); 
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("forumVO", forumVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("favoriteForum", list);
						//文章列出留言用
						req.setAttribute("listMsg_ByForum", set);
						String url = "/front_end/forum/listAllForum.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
						failureView.forward(req, res);
					}finally {
						if (jedis != null) {
							jedis.close();

						}
					}
				}						
/* 刪除文章 */		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String forum_id = req.getParameter("forum_id");
				
				/***************************2.開始刪除資料***************************************/
				ForumService ForumSvc = new ForumService();
				ForumSvc.deleteForum(forum_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/forum/listAllForum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/forum/listAllForum.jsp");
				failureView.forward(req, res);
			}
		}
		
/*後台上下架文章*/	
		if ("ChangeStat".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String forum_id = new String(req.getParameter("forum_id"));
				
					
				/***************************2.開始查詢資料****************************************/
				ForumService forumSvc = new ForumService();
				
				String stat=forumSvc.getOneForum(forum_id).getForum_stat();
				
					if(stat.equals("1"))  //更改狀態
						stat="0";
					else{
						stat="1";
					}
				forumSvc.updateForum(stat,forum_id );
					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("forumVO", forumSvc);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/forum/listAllForum(back).jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/forum/listAllForum.jsp");
				failureView.forward(req, res);
			}
		}
//爬蟲
		if ("gymNumber".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Map<String,Map> xxx = gymServlet.gymNum();
				req.setAttribute("GYMmap", xxx);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String url = "/front_end/forum/listAllGym.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/forum/listAllForum.jsp");
				failureView.forward(req, res);
			}
		}		
		

	}
}
