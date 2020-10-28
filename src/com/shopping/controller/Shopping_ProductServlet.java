package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order_details.model.Order_DetailsVO;
import com.order_details.model.Order_Details_ProductVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_order.model.Product_OrderService;
import com.product_order.model.Product_OrderVO;

public class Shopping_ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	 @SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		@SuppressWarnings("unchecked")
		List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		int oldbuylist = 0;//判斷要跳回購物車頁面或是商品首頁用
		if(buylist != null ) {
			oldbuylist = buylist.size();
		} 
		
		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
                /*購物車內修改數量 抓到index數值*/
				String add = req.getParameter("add");
				if(add != null) {
				int ad = Integer.parseInt(add);
				
				Integer pro_quantity_new = null;
				try {
					pro_quantity_new = new Integer(req.getParameter("pro_quantity_new").trim());
				} catch (NumberFormatException e) {
					pro_quantity_new = 0;
					errorMsgs.add("售價請填數字");
				}
				
				Order_Details_ProductVO od_productVO_change = (Order_Details_ProductVO) buylist.get(ad);
				od_productVO_change.setPro_quantity(pro_quantity_new);
				buylist.remove(ad);
				buylist.add(ad, od_productVO_change);
				
				session.setAttribute("shoppingcart", buylist);
				String url = "/front_end/product_front/Product_Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
				}
				/*購物車內修改數量 抓到index數值*/
				
				
				
				Integer pro_no = null;
				try {
					pro_no = new Integer(req.getParameter("pro_no").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入正確編號");
				}
				

				
				Integer pro_quantity = null;	
				try {
					pro_quantity = new Integer(req.getParameter("pro_quantity").trim());
					String quantity_check = String.valueOf(pro_quantity);					
					String pro_quantityReg = "^[(0-9)]{1,2}$";
					if (quantity_check == null || quantity_check.trim().length() == 0) {
						errorMsgs.add("加入購物車數量請勿空白");
					} else if(!quantity_check.trim().matches(pro_quantityReg) || pro_quantity == 0) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("購物車數量錯誤，每次輸入購買量只能為1~99");
		            }
				} catch (NumberFormatException e) {
					pro_quantity = 1;
					errorMsgs.add("數量請填數字");
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/product_front/Product_Cart.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
				
				// 取得後來新增的書籍
				ProductService productSvc = new ProductService();
				ProductVO aproductVO = productSvc.getOneProduct(pro_no);
				Order_Details_ProductVO od_productVO = new Order_Details_ProductVO(aproductVO, pro_quantity);
				
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(od_productVO);
				} else { // 如果此列表包含指定的元素，則返回true。
					if (buylist.contains(od_productVO)) { // 返回指定元素在此列表中首次出現的索引；如果此列表不包含該元素，則返回-1。
						Order_Details_ProductVO innerod_productVO = (Order_Details_ProductVO) buylist
								.get(buylist.indexOf(od_productVO));
						innerod_productVO
								.setPro_quantity(innerod_productVO.getPro_quantity() + od_productVO.getPro_quantity());
					} else {
						buylist.add(od_productVO);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			
			int removebulist = buylist.size();
			if(oldbuylist > removebulist) {
				String url = "/front_end/product_front/Product_Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			}
			else {
			String url = "/front_end/product_front/Product_Cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			}
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			Integer total = 0;
			Integer pro_total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Order_Details_ProductVO order = (Order_Details_ProductVO) buylist.get(i);
				Integer price = order.getPro_pri();
				Integer quantity = order.getPro_quantity();
				total += (price * quantity);
				pro_total += quantity;
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String proAllqua = String.valueOf(pro_total);
			req.setAttribute("proAllqua", proAllqua);
			String url = "/front_end/product_order_front/Shopping_Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}


	}

}
