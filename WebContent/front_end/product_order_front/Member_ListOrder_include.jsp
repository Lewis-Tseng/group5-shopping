<%@page import="java.util.stream.Collectors"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.order_details.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%Product_OrderVO product_orderVO = (Product_OrderVO) request.getAttribute("product_orderVO");%> --%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%
MemVO loginVO = (MemVO) request.getAttribute("loginVO");
Object account = session.getAttribute("loginVO");                  // 從 session內取出 (key) account的值
if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
response.sendRedirect(request.getContextPath()+"/front_end/mem//MemLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
return;
}
%>
<%

	
	List<Product_OrderVO> list2 = (ArrayList<Product_OrderVO>) request.getAttribute("listMember_Orders_ByCompositeQuery");
	
		Collections.reverse(list2);
		
		pageContext.setAttribute("list2",list2);
		%>
		
		<%-- <jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_OrderService" /> --%>
		<jsp:useBean id="order_detailsSvc" scope="page" class="com.order_details.model.Order_DetailsService" />
		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
		<!DOCTYPE HTML>
		<html>
			<head>
				<title>會員訂單 - ${memVO.mem_name }</title>
				<!---css--->
				<link href="<%=request.getContextPath()%>/front_end/login/css/bootstrap.css" rel='stylesheet' type='text/css' />
				<link href="<%=request.getContextPath()%>/front_end/login/css/style.css" rel='stylesheet' type='text/css' />
				<!---css--->
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<meta name="keywords" content="" />
				<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
				<!---js--->
				<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
				<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
				<!--css-->
				<link rel="stylesheet" type="text/css" href="css/calender_lession1.css">
				<style type="text/css">
					@font-face{
						font-family: 'test';
						src:url('SentyTEA-20190904.ttf');
					}
					body{
						background-color:#f5f5dc;
						font-family: 'test';
						text-align: center;
					}
					form>b{
						font-size: 20px;
					}
					#mem{
						float: left;
					}
					#order{
						float: left;
					}
					#point{
						float: left;
					}
					#class{
						float: left;
					}
					#activity{
						float: left;
					}
					#article{
						float: left;
					}
					#wrapper{
						width: 100%;
						font-size: 20px;
					}
					.footer-section {
						padding: 0;
						margin-top: 100%;
					}
					
					#content1 {
						padding: 0;
						width: 240px;
						font-size: 20px;
					}
					table{
						border:1px solid #grey;
						border-collapse: collapse;/*邊框縫隙*/
						width:100%;
						height:20px auto;
						
					}
				table caption{
				font-size: 40px;
				text-align: center;
				font-weight:bolder;
				letter-spacing: 5px;
				background-color: #666;
				border-top-left-radius: 3px;
				border-top-right-radius: 3px;
				border-bottom-left-radius: 3px;
				border-bottom-right-radius: 3px;
				color: white;
				height:50px ;
				line-height:50px;
				}
				table td,table th{/*兩個都用同樣的外觀，用逗點隔開就好，不用重複寫*/
				/*border:1px solid #696;*/
				text-align: center;
				font-size: 20px;
				
				}
				table th{
				background-color: #BFB6B3;
				color:black;
				}
				table td{
				background-color: #E8E1DE;
				color:black;
				}
				.pagesbtn1{
					margin: 1px;
					}
					.pagesbtn2{
					margin: 1px;
					}
					.tablebase{
						overflow: auto;
					}
				
				</style>
				<!---js--->
				<!--web-fonts-->
				<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
				<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
				<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
				<!--//web-fonts-->
				<!--JS for animate-->
				<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
				<script src="js/wow.min.js"></script>
				<script>
					new WOW().init();
				</script>
				<!--//end-animate-->
			</head>
			<body>
				
							<div class="container">	
<%-- 								<div class="pagesbtn1"><%@ include file="pages/page1.file" %></div> --%>
               <div class="pagesbtn1"><font size="+2" color=red>總共有：<%=list2.size()%> 筆訂單</font></div>
								<div class="col-12 tablebase">
									<c:forEach var="product_orderVO" items="${list2}" >
									<c:if test="${product_orderVO.mem_id == memVO.mem_id}">
									<table class="table-responsive">
										<caption>訂單編號 - ${product_orderVO.ord_no}</caption>
										<thead>
											<tr>
												<th>會員編號</th>
												<th>訂單日期</th>
												<th>訂單總金額</th>
												<th>商品總數量</th>
												<th>訂單狀態</th>
												<th>付款方式</th>
											</thead>
											<tbody>
												<tr>
													<td>${product_orderVO.mem_id}</td>
													<td>${product_orderVO.ord_dat}</td>
													<td>${product_orderVO.ord_amo}</td>
													<td>${product_orderVO.pro_qua}</td>
													<td><c:if test="${product_orderVO.ord_sta == 0}">已付款</c:if>
													<c:if test="${product_orderVO.ord_sta == 1}">已完成訂單</c:if></td>
													<td>${product_orderVO.pay_met}</td>
												</tr>
											</tbody>
										</table>
										<table >
											<thead class="thead-light">
												<tr>
													<th scope="col">配送地址</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${product_orderVO.del_add}</td>
												</tr>
											</tbody>
										</table>
										<table >
											<thead class="thead-light">
												<tr>
													<th scope="col">訂單明細</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<c:forEach var="order_detailsVO" items="${order_detailsSvc.all}">
													<c:if test="${product_orderVO.ord_no==order_detailsVO.ord_no}">
													<tr><td>
														${order_detailsVO.ord_no}【${productSvc.getOneProduct(order_detailsVO.pro_no).pro_nam}】
														- 【${order_detailsVO.quantity}】 - 【${order_detailsVO.uni_pri}】
													</td><tr>
													</c:if>
													</c:forEach>
												</tr>
											</tbody>
										</table>
										<br>
										</c:if>
										</c:forEach>
									</div>
<%-- 									<div class="pagesbtn2"><%@ include file="pages/page2.file" %></div> --%>
								</div>
								</body>
							</html>