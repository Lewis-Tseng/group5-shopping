package com.product.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.order_details.model.Order_DetailsVO;
import com.order_details.model.Order_Details_ProductVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_image.model.Product_ImageService;
import com.product_image.model.Product_ImageVO;
import com.product_order.model.Product_OrderService;
import com.product_order.model.Product_OrderVO;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
        
		/*********************後台程式碼***************************/
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("pro_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String pro_no = null;
				try {
					pro_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pro_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/select_Product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/listOneProduct.jsp";
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
				String pro_no = new String(req.getParameter("pro_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pro_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/listAllProduct.jsp");
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
				String pro_no = new String(req.getParameter("pro_no").trim());
				
				String pro_nam = req.getParameter("pro_nam");
				if (pro_nam == null || pro_nam.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} 


				String cat_no = req.getParameter("cat_no").trim();
				if (cat_no == null || cat_no.trim().length() == 0) {
					errorMsgs.add("類別編號請勿空白");
				}

				// CLOB
				String pro_con = req.getParameter("pro_con").trim(); 
				if (pro_con  == null ) {
					errorMsgs.add("商品介紹文字檔請勿空白");
				}

				// 價格
				Integer pro_pri = null;
				try {
					pro_pri = new Integer(req.getParameter("pro_pri").trim());
				} catch (NumberFormatException e) {
					pro_pri = 0;
					errorMsgs.add("售價請填數字");
				}

				// 狀態
				String pro_sta = req.getParameter("pro_sta").trim();
				if (cat_no == null || cat_no.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}

				// 商品庫存數
				Integer pro_sto = null;
				try {
					pro_sto = new Integer(req.getParameter("pro_sto").trim());
				} catch (NumberFormatException e) {
					pro_sto = 0;
					errorMsgs.add("商品庫存請填數字.");
				}

				ProductVO productVO = new ProductVO();
				productVO.setPro_no(pro_no);
				productVO.setCat_no(cat_no);
				productVO.setPro_nam(pro_nam);
				productVO.setPro_con(pro_con);
				productVO.setPro_pri(pro_pri);
				productVO.setPro_sta(pro_sta);
				productVO.setPro_sto(pro_sto);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(pro_no, cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/update_product_input.jsp");
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
				String pro_nam = req.getParameter("pro_nam");
				if (pro_nam == null || pro_nam.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}


				String cat_no = req.getParameter("cat_no").trim();
				if (cat_no == null || cat_no.trim().length() == 0) {
					errorMsgs.add("類別編號請勿空白");
				}
				
				// CLOB
				String pro_con = req.getParameter("pro_con").trim(); 
				if (pro_con  == null || pro_con.trim().length() == 0 ) {
					errorMsgs.add("商品介紹文字檔請勿空白");
				}

				// 價格
				Integer pro_pri = null;
				try {
					pro_pri = new Integer(req.getParameter("pro_pri").trim());
				} catch (NumberFormatException e) {
					pro_pri = 0;
					errorMsgs.add("售價請填數字");
				}

				// 狀態
				String pro_sta = req.getParameter("pro_sta").trim();
				if (pro_sta == null || pro_sta.trim().length() == 0) {
					errorMsgs.add("商品狀態請勿空白");
				}

				// 商品庫存數
				Integer pro_sto = null;
				try {
					pro_sto = new Integer(req.getParameter("pro_sto").trim());
				} catch (NumberFormatException e) {
					pro_sto = 0;
					errorMsgs.add("商品庫存請填數字.");
				}
				
				ProductVO productVO = new ProductVO();
				productVO.setCat_no(cat_no);
				productVO.setPro_nam(pro_nam);
				productVO.setPro_con(pro_con);
				productVO.setPro_pri(pro_pri);
				productVO.setPro_sta(pro_sta);
				productVO.setPro_sto(pro_sto);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
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
				String pro_no = new String(req.getParameter("pro_no").trim());

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(pro_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		/*********************後台程式碼***************************/
		
		/*********************前台程式碼***************************/
		if ("getOne_For_Display_Front".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("pro_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product_front/shopping_mall_home.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String pro_no = null;
				try {
					pro_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product_front/shopping_mall_home.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pro_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product_front/shopping_mall_home.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/product_front/shopping_mall_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product_front/shopping_mall_home.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("listProduct_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){//第一次空值
		      //因為原本取出的Map資料Java不能動,所以用new一個新的HashMap存回去,把舊Map的限制行為洗掉 
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);//處理過過水後  
					map = map1;//存回去
				} 
				
				/***************************2.開始複合查詢***************************************/
				ProductService productSvc = new ProductService();
				List<ProductVO> list  = productSvc.getAll_CompositeQuery(map);
				//過濾出狀態是0的上架商品
				list = list.stream()
						.filter(p -> p.getPro_sta().equals("0"))
						.collect(Collectors.toList());
				        Collections.reverse(list);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				session.setAttribute("listProduct_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/product_front/shopping_mall_home_search.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
				PrintWriter out = new PrintWriter(System.out);
				out.write("查無資料");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/product_front/shopping_mall_home.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
	}/*dopost_end*/
	
	
	
	
	
	
	
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
