package com.product_order.controller;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.order_details.model.Order_DetailsService;
import com.order_details.model.Order_DetailsVO;
import com.order_details.model.Order_Details_ProductVO;
import com.product.model.ProductVO;
import com.product_order.model.Product_OrderService;
import com.product_order.model.Product_OrderVO;

public class Product_OrderServlet extends HttpServlet {
	
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			/*後台訂單管理*/
			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/			
					
					Integer ord_no = null;
					try {
						ord_no = new Integer(req.getParameter("ord_no").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("訂單編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/product/select_Product.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					Product_OrderVO product_orderVO = product_orderSvc.getOneProduct_Order(ord_no);
					if (product_orderVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/product/select_Product.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("product_orderVO", product_orderVO); // 資料庫取出的empVO物件,存入req
					String url = "/back_end/product_order/listOneProduct_Order.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/select_Product.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer ord_no = new Integer(req.getParameter("ord_no"));
					
					/***************************2.開始查詢資料****************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					Product_OrderVO product_orderVO = product_orderSvc.getOneProduct_Order(ord_no);	
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("product_orderVO", product_orderVO); // 資料庫取出的empVO物件,存入req
					String url = "/back_end/product_order/update_product_order_input.jsp";
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
				
				String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer ord_no = new Integer(req.getParameter("ord_no").trim());
					
					String mem_id = req.getParameter("mem_id");
					String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (mem_id == null || mem_id.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
					} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					java.sql.Date ord_dat = null;
					try {
						ord_dat = java.sql.Date.valueOf(req.getParameter("ord_dat").trim());
					} catch (IllegalArgumentException e) {
						ord_dat=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					Integer ord_amo = null;
					try {
						ord_amo = new Integer(req.getParameter("ord_amo").trim());
					} catch (NumberFormatException e) {
						ord_amo = 0;
						errorMsgs.add("訂單總金額請填數字.");
					}

					Integer pro_qua = null;
					try {
						pro_qua = new Integer(req.getParameter("pro_qua").trim());
					} catch (NumberFormatException e) {
						pro_qua = 0;
						errorMsgs.add("商品數量請填數字.");
					}

					String ord_sta = new String(req.getParameter("ord_sta").trim());
					if (ord_sta == null || (ord_sta.trim()).length() == 0) {
						errorMsgs.add("請輸入訂單狀態");
					}
					
					String pay_met = new String(req.getParameter("pay_met").trim());
					if (pay_met == null || (pay_met.trim()).length() == 0) {
						errorMsgs.add("請輸入付款方式");
					}
					
					String del_add = new String(req.getParameter("del_add").trim());
					if (del_add == null || (del_add.trim()).length() == 0) {
						errorMsgs.add("請輸入地址方式");
					}
					
					Product_OrderVO product_orderVO = new Product_OrderVO();
					product_orderVO.setOrd_no(ord_no);
					product_orderVO.setMem_id(mem_id);
					product_orderVO.setOrd_dat(ord_dat);
					product_orderVO.setOrd_amo(ord_amo);
					product_orderVO.setPro_qua(pro_qua);
					product_orderVO.setOrd_sta(ord_sta);
					product_orderVO.setPay_met(pay_met);
					product_orderVO.setDel_add(del_add);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/product_order/update_product_order_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					product_orderVO = product_orderSvc.updateProduct_Order(ord_no, mem_id, ord_dat, ord_amo, pro_qua, ord_sta, pay_met, del_add);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/				
					//後台子查詢(查Order_Details)
					Order_DetailsService order_detailsSvc = new Order_DetailsService();
					if(requestURL.equals("/back_end/order_details/listProduct_Order_ByOrder_Details.jsp") || requestURL.equals("/back_end/order_details/listAllOrder_Details.jsp"))
						req.setAttribute("listEmps_ByDeptno",order_detailsSvc.getAllOrder_DetailsByOrd_no(ord_no)); // 資料庫取出的list物件,存入request
					
					//後台複合查詢
					if(requestURL.equals("/back_end/product_order/listProduct_Orders_ByCompositeQuery.jsp")){
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						List<Product_OrderVO> list  = product_orderSvc.getAll(map);
						req.setAttribute("listProduct_Orders_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
					}
	                               ///back_end/order_details/update_order_details_input.jsp"
					if(requestURL.equals("/back_end/product_order/update_product_order_input.jsp")) {
						requestURL = "/back_end/product_order/listOneProduct_Order.jsp";
						req.setAttribute("product_orderVO", product_orderVO);
					}
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product_order/update_product_order_input.jsp");
					failureView.forward(req, res);
				}
			}

	        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
   
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String mem_id = req.getParameter("mem_id");
					String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{7,7}$";
					if (mem_id == null || mem_id.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
					} else if(!mem_id.trim().matches(mem_idReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					java.sql.Date ord_dat = null;
					try {
						ord_dat = java.sql.Date.valueOf(req.getParameter("ord_dat").trim());
					} catch (IllegalArgumentException e) {
						ord_dat=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					Integer ord_amo = null;
					try {
						ord_amo = new Integer(req.getParameter("ord_amo").trim());
					} catch (NumberFormatException e) {
						ord_amo = 0;
						errorMsgs.add("訂單總金額請填數字.");
					}

					Integer pro_qua = null;
					try {
						pro_qua = new Integer(req.getParameter("pro_qua").trim());
					} catch (NumberFormatException e) {
						pro_qua = 0;
						errorMsgs.add("商品數量請填數字.");
					}

					String ord_sta = new String(req.getParameter("ord_sta").trim());
					if (ord_sta == null || (ord_sta.trim()).length() == 0) {
						errorMsgs.add("請輸入訂單狀態");
					}
					
					String pay_met = new String(req.getParameter("pay_met").trim());
					if (pay_met == null || (pay_met.trim()).length() == 0) {
						errorMsgs.add("請輸入付款方式");
					}
					
					String del_add = new String(req.getParameter("del_add").trim());
					if (del_add == null || (del_add.trim()).length() == 0) {
						errorMsgs.add("請輸入地址方式");
					}
				
					Product_OrderVO product_orderVO = new Product_OrderVO();
					product_orderVO.setMem_id(mem_id);
					product_orderVO.setOrd_dat(ord_dat);
					product_orderVO.setOrd_amo(ord_amo);
					product_orderVO.setPro_qua(pro_qua);
					product_orderVO.setOrd_sta(ord_sta);
					product_orderVO.setPay_met(pay_met);
					product_orderVO.setDel_add(del_add);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/product_order/addProduct_Order.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					product_orderVO = product_orderSvc.addProduct_Order(mem_id, ord_dat, ord_amo, pro_qua, ord_sta, pay_met, del_add);
				
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back_end/product_order/listAllProduct_Order.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product_order/addProduct_Order.jsp");
					failureView.forward(req, res);
				}
			}
			
	       
			if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEsmps_ByDeptno.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

				try {
					/***************************1.接收請求參數***************************************/
					Integer ord_no = new Integer(req.getParameter("ord_no"));
					
					/***************************2.開始刪除資料***************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					Product_OrderVO product_orderVO = product_orderSvc.getOneProduct_Order(ord_no);
					product_orderSvc.deleteProduct_Order(ord_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					Order_DetailsService order_detailsSvc = new Order_DetailsService();
					if(requestURL.equals("/back_end/order_details/listProduct_Order_ByOrder_Details.jsp") || requestURL.equals("/back_end/order_details/listAllOrder_Details.jsp"))
						req.setAttribute("listProduct_Order_ByOrder_Details",order_detailsSvc.getAllOrder_DetailsByOrd_no(product_orderVO.getOrd_no())); // 資料庫取出的list物件,存入request
					
					if(requestURL.equals("/back_end/product_order/listProduct_Orders_ByCompositeQuery.jsp")){
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						List<Product_OrderVO> list  = product_orderSvc.getAll(map);
						req.setAttribute("listProduct_Orders_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
					}
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
			
			if ("listProduct_Orders_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
					Product_OrderService product_orderSvc = new Product_OrderService();
					List<Product_OrderVO> list  = product_orderSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listProduct_Orders_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/back_end/product_order/listProduct_Orders_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/select_Product.jsp");
					failureView.forward(req, res);
				}
			}
			/*****************************後台訂單管理end************************************************/
			
			
			/*****************************前台訂單管理************************************************/
			
			
		    /*結帳產生訂單*/
			if ("payment".equals(action)) {
				
				HttpSession session = req.getSession();
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				@SuppressWarnings("unchecked")
				List<Order_Details_ProductVO> od_buylist = (Vector<Order_Details_ProductVO>) session
						.getAttribute("shoppingcart");

				try {
					/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

					/* 開始新增訂單明細取值且加入List */
					List<Order_DetailsVO> od_colist = new ArrayList<Order_DetailsVO>();

					Integer total = 0;
					Integer pro_total = 0;

					if (od_buylist.size() != 0) {
						for (Order_Details_ProductVO odpVO : od_buylist) {
							/* 從購物車商品取值給訂單明細用 */
							Order_DetailsVO order_detailsVO = new Order_DetailsVO();
							//更改為下面三行(Hibernate版)
							ProductVO productVO = new ProductVO();
							productVO.setPro_no(odpVO.getPro_no());
							order_detailsVO.setProductVO(productVO);
							order_detailsVO.setQuantity(odpVO.getPro_quantity());
							order_detailsVO.setUni_pri(odpVO.getPro_pri());
							od_colist.add(order_detailsVO);
							/* 從購物車商品取值給訂單總金額和總數量用 */
							Integer price = odpVO.getPro_pri();
							Integer quantity = odpVO.getPro_quantity();
							total += (price * quantity);
							pro_total += quantity;
						}
					} else {
						errorMsgs.add("購物車沒有東西");
					}
					/* 訂單明細新增完成 */

					/* 訂單從jsp網頁取值且新增 */
					String mem_id = req.getParameter("mem_id");
					String mem_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{7,7}$";
					if (mem_id == null || mem_id.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
					} else if (!mem_id.trim().matches(mem_idReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					/* 取得訂單產生的日期 新增至訂單 */
					java.sql.Date ord_dat = null;
					try {
						ord_dat = new java.sql.Date(System.currentTimeMillis());
					} catch (IllegalArgumentException e) {
						ord_dat = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					/* 商品總金額 */
					Integer ord_amo = null;
					try {
						ord_amo = new Integer(total);
					} catch (NumberFormatException e) {
						ord_amo = 0;
						errorMsgs.add("訂單總金額請填數字.");
					}
					/* 商品總數量 */
					Integer pro_qua = null;
					try {
						pro_qua = new Integer(pro_total);
					} catch (NumberFormatException e) {
						pro_qua = 0;
						errorMsgs.add("商品數量請填數字.");
					}
					/* 訂單狀態目前都是已付款 */
					String paid = "0";
					String ord_sta = new String(paid);
					if (ord_sta == null || (ord_sta.trim()).length() == 0) {
						errorMsgs.add("請輸入訂單狀態");
					}

					String pay_met = new String(req.getParameter("pay_met").trim());
					if (pay_met == null || (pay_met.trim()).length() == 0) {
						errorMsgs.add("請輸入付款方式");
					}

					String del_add = new String(req.getParameter("del_add").trim());
					if (del_add == null || (del_add.trim()).length() == 0) {
						errorMsgs.add("請輸入地址");
					}
					
//					/*扣除點數*/
//					MemService memSvc = new MemService();
//					MemVO memVO = (MemVO) session.getAttribute("memVO");
//					String mem_id1 = memVO.getMem_id();
//					Integer mem_point = memVO.getMem_point();
//					Integer mem_point_Deduction = 0;
//					if(mem_point != 0 && mem_point >= ord_amo) {
//					    mem_point_Deduction = (mem_point - ord_amo);
//					    memSvc.updatePoint(mem_id1, mem_point_Deduction);
//					    memVO = memSvc.getOneMem(mem_id1);
//					    //重新set新的memVO session 為了讓頁面顯示正確的點數
//					    session.setAttribute("memVO", memVO);	
//					    System.out.println(mem_point + " 點扣掉  " + ord_amo + " 點，剩餘點數="+ mem_point_Deduction); 
//					    System.out.println("剩餘點數" + memVO.getMem_point());
//					} else {
//						errorMsgs.add("點數不足，請先儲值");
//					}					
//					/*扣除點數*/
										
					Product_OrderVO product_orderVO = new Product_OrderVO();
					product_orderVO.setMem_id(mem_id);
					product_orderVO.setOrd_dat(ord_dat);
					product_orderVO.setOrd_amo(ord_amo);
					product_orderVO.setPro_qua(pro_qua);
					product_orderVO.setOrd_sta(ord_sta); 
					product_orderVO.setPay_met(pay_met);
					product_orderVO.setDel_add(del_add);
					//改成傳入set
					Set<Order_DetailsVO> od_buyset = new LinkedHashSet<Order_DetailsVO>();
					od_buyset = od_colist.stream().collect(Collectors.toSet());
					product_orderVO.setOrder_detailss(od_buyset);
					
					
           			// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
						session.setAttribute("shoppingcart", od_buylist);
						String amount = String.valueOf(total);
						req.setAttribute("amount", amount);
						String proAllqua = String.valueOf(pro_total);
						req.setAttribute("proAllqua", proAllqua);
//						res.sendRedirect("http:/localhost:8081/G5_Shopping_ver4.1/front_end/product_order_front/Shopping_Checkout.jsp");
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/product_order_front/Shopping_Checkout.jsp");
						failureView.forward(req, res);
						return;
					}
					/* 清空購物車List */
					od_buylist.removeAll(od_buylist);
//					session.removeAttribute("shoppingcart");
					/*************************** 2.開始新增訂單資料 ***************************************/
					Product_OrderService product_orderSvc = new Product_OrderService();
					product_orderSvc.insertShopping_Order(product_orderVO);
					/*************************** 2.開始新增訂單資料 ***************************************/
					req.setAttribute("product_orderVO", product_orderVO);
                    /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//					res.sendRedirect("/front_end/product_front/shopping_mall_home.jsp");				
					String url = "/front_end/product_order_front/Member_Order.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
//					res.sendRedirect("/front_end/product_order_front/Shopping_Checkout.jsp");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/product_order_front/Shopping_Checkout.jsp");
					failureView.forward(req, res);
				} 
				
			}/*結帳產生訂單end*/
			
			/*會員訂單複合查詢*/
			if ("listMember_Orders_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
						session.setAttribute("map",map1);//處理過過水後  s
						map = map1;//存回去
					} 		
					/***************************2.開始複合查詢***************************************/					
					Product_OrderService product_orderSvc = new Product_OrderService();
					List<Product_OrderVO> list  = product_orderSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("listMember_Orders_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/front_end/product_order_front/Member_ListOrder.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/product_order_front/Member_ListOrder.jsp");
					failureView.forward(req, res);
				}
			}/*會員訂單複合查詢end*/
			
			
			
			
			
			
			
			
		}/*dopost_end*/
		
	

}
