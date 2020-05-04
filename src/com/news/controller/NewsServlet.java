package com.news.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.news.model.NewsService;
import com.news.model.NewsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String newsid = req.getParameter("newsid");
				System.out.println(newsid);
				if (newsid == null || (newsid.trim()).length() == 0) {
					errorMsgs.add("請輸入消息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/SelectNews.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				

				/*************************** 2.開始查詢資料 *****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsid);

				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/SelectNews.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/news/ListOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/SelectNews.jsp");
				failureView.forward(req, res);
			}
		}
//      新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String news_id = req.getParameter("newsid");
				String newsidReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (news_id == null || news_id.trim().length() == 0) {
					errorMsgs.add("編號: 請勿空白");
				} else if (!news_id.trim().matches(newsidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
				}
				String title = req.getParameter("title").trim();
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題: 請勿空白");

				} else if (!title.trim().matches(titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}

				java.sql.Timestamp date = null;
				try {
					date = java.sql.Timestamp.valueOf(req.getParameter("date").trim());
				} catch (IllegalArgumentException e) {
					date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Part part=req.getPart("file");
				String header = part.getHeader("content-disposition");
				System.out.println(header);
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
				System.out.println("filename=" + filename);
				if(filename.isEmpty()) {
					errorMsgs.add("未選擇圖片");
				}
				byte[] pic = getPictureByteArray(req.getPart("file").getInputStream());

				NewsVO newsVO = new NewsVO();
				newsVO.setNews_id(news_id);
				newsVO.setNews_title(title);
				newsVO.setNews_info(content);
				newsVO.setNews_time(date);
				newsVO.setNews_pic(pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/AddNews.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				NewsService newsSvc = new NewsService();
				newsSvc.addNews(news_id, title, content, pic, date);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/news/ListAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/AddNews.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String news_id = req.getParameter("newsid");
				
				/*************************** 2.開始查詢資料 ****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(news_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/news/update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/ListAllNews.jsp");
				failureView.forward(req, res);
			}
		}
		//修改
		if ("update".equals(action)) { // 來自updateEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String news_id = req.getParameter("newsid");

				String newsidReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (news_id == null || news_id.trim().length() == 0) {
					errorMsgs.add("編號: 請勿空白");
				} else if (!news_id.trim().matches(newsidReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("編號:數字和_ , 且長度必需在2到10之間");
				}
				String title = req.getParameter("title").trim();
				String titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("標題: 請勿空白");

				} else if (!title.trim().matches(titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}

				java.sql.Timestamp date = null;
				try {
					date = java.sql.Timestamp.valueOf(req.getParameter("date").trim());
				} catch (IllegalArgumentException e) {
					date = new java.sql.Timestamp(System.currentTimeMillis());
					
				}
				byte[] pic=null;
				Part part=req.getPart("file");
				
				String filename = getFileNameFromPart(part);
				if (filename!= null && part.getContentType()!=null) {
					InputStream in = part.getInputStream();
					pic = new byte[in.available()];
					in.read(pic);
					in.close();
				}else {
					pic=null;
				}
//				byte[] pic = getPictureByteArray(req.getPart("file").getInputStream());
				NewsVO newsVO = new NewsVO();		
				newsVO.setNews_id(news_id);
				newsVO.setNews_title(title);
				newsVO.setNews_info(content);
				newsVO.setNews_time(date);
				newsVO.setNews_pic(null);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/update.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/
				NewsService newsSvc = new NewsService();
				
				if(pic !=null) {
					System.out.println(pic);
					newsSvc.updateNews(news_id, title, content, pic, date);
				}else {
					
					newsSvc.update_nopicture(news_id, title, content, date);
					System.out.println(pic);
				}
				
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("newsVO", newsVO);
				String url = "/back_end/news/ListAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/update.jsp");
				failureView.forward(req, res);
			}
		}
//      刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String newsid = req.getParameter("newsid");

				/*************************** 2.開始刪除資料 ***************************************/
				NewsService newsSvc = new NewsService();
				newsSvc.deleteNews(newsid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/news/ListAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/news/ListAllNews.jsp");
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

		return baos.toByteArray();
	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
}
