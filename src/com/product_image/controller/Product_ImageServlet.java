package com.product_image.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductVO;
import com.product_image.model.Product_ImageService;
import com.product_image.model.Product_ImageVO;

@MultipartConfig(fileSizeThreshold = 5000 * 5000, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Product_ImageServlet extends HttpServlet {

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");

			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					String str = req.getParameter("pro_img_no");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入商品圖片編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					Integer pro_img_no = null;
					try {
						pro_img_no = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("商品圖片編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 2.開始查詢資料 *****************************************/
					Product_ImageService product_imageSvc = new Product_ImageService();
					Product_ImageVO product_imageVO = product_imageSvc.getOneProduct_Image(pro_img_no);
					if (product_imageVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("product_imageVO", product_imageVO); // 資料庫取出的empVO物件,存入req
					String url = "/back_end/product_image/listOneProduct_Image.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
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
					Integer  pro_img_no = new Integer(req.getParameter("pro_img_no"));
					
					/*************************** 2.開始查詢資料 ****************************************/
					Product_ImageService product_imageSvc = new Product_ImageService();
					Product_ImageVO product_imageVO = product_imageSvc.getOneProduct_Image(pro_img_no);

					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("product_imageVO", product_imageVO); // 資料庫取出的empVO物件,存入req
					String url = "/back_end/product_image/update_product_image_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/listAllProduct_Image.jsp");
					failureView.forward(req, res);
				}
			}

			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					Integer pro_img_no = new Integer(req.getParameter("pro_img_no").trim());

					Integer pro_no = null;
					try{
						pro_no = new Integer(req.getParameter("pro_no").trim());				
					} catch(NumberFormatException e) {
						errorMsgs.add("商品編號請勿空白");
					}

				    String img_nam = req.getParameter("img_nam");
				    if (img_nam == null || img_nam.trim().length() == 0) {
					      errorMsgs.add("商品圖片名稱請勿空白");
				    }
				    
					// BLOB
					Part part = req.getPart("img");
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					byte[] img = buf;
					if(buf.length == 0) {
						errorMsgs.add("圖片檔案請勿空白");
					}
					
					Product_ImageVO product_imageVO = new Product_ImageVO();
					product_imageVO.setPro_img_no(pro_img_no);
					ProductVO productVO = new ProductVO();
					productVO.setPro_no(pro_no);
					product_imageVO.setProductVO(productVO);
					product_imageVO.setImg_nam(img_nam);
					product_imageVO.setImg(img);
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("product_imageVO", product_imageVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/update_product_image_input.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}

					/*************************** 2.開始修改資料 *****************************************/
					Product_ImageService product_imageSvc = new Product_ImageService();
					product_imageVO = product_imageSvc.updateProduct_Image(pro_img_no, pro_no, img, img_nam);
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					req.setAttribute("product_imageVO", product_imageVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back_end/product_image/listAllProduct_Image.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/update_product_image_input.jsp");
					failureView.forward(req, res);
				}
			}

			
			
			
			
			if ("insert".equals(action)) { // 來自addEmp.jsp的請求
	          
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
					
					Integer pro_no = null;
					try {
						pro_no = new Integer(req.getParameter("pro_no").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("商品編號: 請勿空白");
					}
        
					String img_nam = req.getParameter("img_nam").trim();
					if (img_nam == null || img_nam.trim().length() == 0) {
						errorMsgs.add("圖片名稱請勿空白");
					}
					
					//BLOB
					Part part = req.getPart("img");
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					byte[] img = buf;
					if(buf.length == 0) {
						errorMsgs.add("圖片檔案請勿空白");
					}
	
					Product_ImageVO product_imageVO = new Product_ImageVO();
					product_imageVO.setImg_nam(img_nam);
					product_imageVO.setImg(img);
					ProductVO productVO = new ProductVO();
					productVO.setPro_no(pro_no);
					product_imageVO.setProductVO(productVO);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("product_imageVO", product_imageVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/addProduct_Image.jsp");
						failureView.forward(req, res);
						return;
					}

					/*************************** 2.開始新增資料 ***************************************/
					Product_ImageService product_imageSvc = new Product_ImageService();
					product_imageVO = product_imageSvc.addProduct_Image(pro_no, img, img_nam);
					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					String url = "/back_end/product_image/listAllProduct_Image.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
					successView.forward(req, res);
					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/addProduct_Image.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			
			
			

			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ***************************************/
					Integer pro_img_no = new Integer(req.getParameter("pro_img_no").trim());

					/*************************** 2.開始刪除資料 ***************************************/
					Product_ImageService productSvc = new Product_ImageService();
					productSvc.deleteProduct_Image(pro_img_no);

					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url = "/back_end/product_image/listAllProduct_Image.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_image/listAllProduct_Image.jsp");
					failureView.forward(req, res);
				}
			}
		}
		
		public static String readString(Reader inputStream) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(inputStream);
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			br.close();

			return sb.toString();
		}
	


}
