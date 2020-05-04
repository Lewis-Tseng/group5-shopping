package com.product_category.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.product_category.model.Product_CategoryService;
import com.product_category.model.Product_CategoryVO;
import com.product_order.model.Product_OrderService;
import com.product_order.model.Product_OrderVO;

public class Product_CategoryServlet extends HttpServlet {

   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("pro_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入產品編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/select_Product.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				String pro_no = null;
//				try {
//					pro_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("產品編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/select_Product.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				ProductService productSvc = new ProductService();
//				ProductVO productVO = productSvc.getOneProduct(pro_no);
//				if (productVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/select_Product.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
//				String url = "/product/listOneProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/select_Product.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cat_no = new String(req.getParameter("cat_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Product_CategoryService product_categorySvc = new Product_CategoryService();
				Product_CategoryVO product_categoryVO = product_categorySvc.getOneProduct_Category(cat_no); 				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("product_categoryVO", product_categoryVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product_category/update_product_category_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/listAllProduct_Category.jsp");
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
				String cat_no = new String(req.getParameter("cat_no"));
				
				String cat_nam = req.getParameter("cat_nam");
				if (cat_nam == null || cat_nam.trim().length() == 0) {
					errorMsgs.add("商品類別名稱: 請勿空白");
				}

				Product_CategoryVO product_categoryVO = new Product_CategoryVO();
				product_categoryVO.setCat_no(cat_no);
				product_categoryVO.setCat_nam(cat_nam);
                	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/update_product_category_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Product_CategoryService product_categorySvc = new Product_CategoryService();
				product_categoryVO = product_categorySvc.updateProduct_Category(cat_no, cat_nam);	
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_categoryVO", product_categoryVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/product_category/listAllProduct_Category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/update_product_category_input.jsp");
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
				String cat_nam = req.getParameter("cat_nam");
				if (cat_nam == null || cat_nam.trim().length() == 0) {
					errorMsgs.add("商品類別名稱: 請勿空白");
				}

				Product_CategoryVO product_categoryVO = new Product_CategoryVO();
				product_categoryVO.setCat_nam(cat_nam);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/addProduct_Category.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Product_CategoryService product_categorySvc = new Product_CategoryService();
				product_categoryVO = product_categorySvc.addProduct_Category(cat_nam);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product_category/listAllProduct_Category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/addProduct_Category.jsp");
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
				String cat_no = req.getParameter("cat_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Product_CategoryService product_categorySvc = new Product_CategoryService();
				product_categorySvc.deleteProduct_Category(cat_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product_category/listAllProduct_Category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/listAllProduct_Category.jsp");
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
