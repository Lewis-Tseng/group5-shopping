package com.order_details.controller;

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

import com.order_details.model.Order_DetailsService;
import com.order_details.model.Order_DetailsVO;
import com.product_order.model.Product_OrderService;
import com.product_order.model.Product_OrderVO;



public class Order_DetailsServlet extends HttpServlet {

	   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String ord_no = new String(req.getParameter("ord_no"));				
				String pro_no = new String(req.getParameter("pro_no"));		
				/***************************2.開始查詢資料****************************************/
				Order_DetailsService order_detailsSvc = new Order_DetailsService();
				Order_DetailsVO order_detailsVO = order_detailsSvc.getOneOrder_Details(ord_no, pro_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("order_detailsVO", order_detailsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/order_details/update_order_details_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
          
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ord_no = new String(req.getParameter("ord_no"));
				
				String pro_no = req.getParameter("pro_no");
				if (pro_no == null || pro_no.trim().length() == 0) {
					errorMsgs.add("商品類別名稱: 請勿空白");
				}
				
				Integer quantity = null;
				try {
					quantity = new Integer(req.getParameter("quantity").trim());
					
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("數量請填數字");
				}
				
				Integer uni_pri = null;
				try {
					uni_pri = new Integer(req.getParameter("uni_pri").trim());
					
				} catch (NumberFormatException e) {
					uni_pri = 0;
					errorMsgs.add("價格請填數字");
				}
				

				Order_DetailsVO order_detailsVO = new Order_DetailsVO();
				order_detailsVO.setOrd_no(ord_no);
				order_detailsVO.setPro_no(pro_no);
				order_detailsVO.setQuantity(quantity);
				order_detailsVO.setUni_pri(uni_pri);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detailsVO", order_detailsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order_details/update_order_details_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Order_DetailsService order_detailsSvc = new Order_DetailsService();
				order_detailsVO = order_detailsSvc.updateOrder_Details(ord_no, pro_no, quantity, uni_pri);
				//轉跳回update_product_order_input.jsp頁面時，需要重新req.setAttribute("product_orderVO", product_orderVO);
				//不然頁面抓不到product_orderVO，會出現空值例外
				Product_OrderService product_orderSvc = new Product_OrderService();
				Product_OrderVO product_orderVO = product_orderSvc.getOneProduct_Order(ord_no);
			    /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("order_detailsVO", order_detailsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("product_orderVO", product_orderVO);
				String url = "/back_end/product_order/update_product_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order_details/update_order_details_input.jsp");
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
				String ord_no = req.getParameter("ord_no");
				if (ord_no == null || ord_no.trim().length() == 0) {
					errorMsgs.add("訂單編號: 請勿空白");
				}
				
				String pro_no = req.getParameter("pro_no");
				if (pro_no == null || pro_no.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				}
                
				Integer quantity = null;
				try {
					quantity = new Integer(req.getParameter("quantity").trim());
					
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("數量請填數字");
				}
				
				Integer uni_pri = null;
				try {
					uni_pri = new Integer(req.getParameter("uni_pri").trim());
					
				} catch (NumberFormatException e) {
					uni_pri = 0;
					errorMsgs.add("價格請填數字");
				}
				
				Order_DetailsVO order_detailsVO = new Order_DetailsVO();
				order_detailsVO.setOrd_no(ord_no);
				order_detailsVO.setPro_no(pro_no);
				order_detailsVO.setQuantity(quantity);
				order_detailsVO.setUni_pri(uni_pri);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detailsVO", order_detailsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order_details/addOrder_Details.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Order_DetailsService order_detailsSvc = new Order_DetailsService();
				order_detailsVO = order_detailsSvc.addOrder_Details(ord_no, pro_no, quantity, uni_pri);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product_order/listAllProduct_Order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/order_details/addOrder_Details.jsp");
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
				String ord_no = req.getParameter("ord_no");
				
				String pro_no = req.getParameter("pro_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Order_DetailsService order_detailsSvc = new Order_DetailsService();
				order_detailsSvc.deleteOrder_Details(ord_no, pro_no);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product_order/listAllProduct_Order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_order/listAllProduct_Order.jsp");
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
