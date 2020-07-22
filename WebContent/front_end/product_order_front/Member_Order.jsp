<%@page import="java.util.stream.Collectors"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.order_details.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.coach.model.CoaVO"%>
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
Product_OrderService product_orderSvc = new Product_OrderService();
List<Product_OrderVO> list = product_orderSvc.getAll();
	
	List<Product_OrderVO> list2 = new ArrayList<Product_OrderVO>();
		
		list2 = list.stream()
		.filter(p -> p.getMem_id().equals(memVO.getMem_id()))
		.collect(Collectors.toList());
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
				a {
  color: #f5f5f5;
  text-decoration: none;
}
				@font-face {
	font-family: 'test';
	src: url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
}

body {
	background:url("<%=request.getContextPath()%>/front_end/images/bgG5.png");
	background-size:cover;
	background-repeat:no-repeat;
	background-attachment:fixed;
	background-position:center;
	font-family:cursive;
	color:#fb514ede;
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
				/* **********************頭像************************************ */
.memPic{
	display: inline-block;
	font-family:'test';
	position:fixed;
	right:30px;
	top:20px;
	width:70px;
	height:70px;
	color:rgb(220,220,220);
	z-index:1155;
}
.memPic>a>img{
	width:100%;
	height: 100%;
	border-radius:50%;
	z-index:1156;
}
.memPic>span{
	width: 50%;
    font-size: 2em;
	z-index:1156;
}
.memInfo{
	font-family:'test';
	display: inline-block;
	position:fixed;
	right:30px;
	top:120px;
	width:230px;
	height:115px;
	background:rgba(20,20,20,0.8);
	color:rgb(220,220,220);
	border-radius:10px;
	z-index:1155;
	text-align: center;
	transition:0.3s;
	opacity:0;
}

.box{
	position:fixed;
	top: 89;
    right: 50;
	display:inline-block;
	border-right: 13px solid rgba(0,0,0,0);
	border-left: 13px solid rgba(0,0,0,0);
	border-bottom: 25px solid rgba(20,20,20,0.8);
	display:inline-block;
	width:30px;
	height:30px;
    background-color:rgba(0,0,0,0);
    transition:0.3s;
    border-radius:0px;
    z-index:1150;
    
}
.memText h1,.memText h2{
	font-family:'test';
	margin-top:12px;
}
/* ***********************頭像*********************************** */
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
				<!---header--->
			<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
					<div class="navbar-header wow fadeInLeft animated animated" data-wow-delay="0.4s">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						</button>
			<h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img  src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s "style=" margin-top: 0%;margin-right: -15%;">
					<li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/crs/crsEntrance.jsp"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp" ><span data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
<!-- ***************************************頭像登出********************************************** -->
				<%	

CoaVO coaVO = (CoaVO) session.getAttribute("coaVO");
			if(memVO!=null || coaVO!=null){
 	    	%>
					<li><a class="nav-in"herf="#">
			<span data-letters="登出">
					<form action="<%=request.getContextPath()%>/mem.do" method="POST">
						<input type="submit" value="" style="position: absolute;background-color: rgb(0,0,0,0); border-color: rgb(0,0,0,0);z-index: 2;width: 100%;height: 100%;">  
						<input type="hidden" name="action" value="signout"><b>登出</b> 
					</form> 
			</span>
						</a>
					</li>
			<%}%>
<!-- 	******************登入************************************************************************ -->
		<%if(memVO==null&&coaVO==null){ %>
	<li><a href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp"><span data-letters="登入"><b>登入</b></span></a></li>
	<%} %>
<!-- 	******************結束************************************************************************ -->

          </ul>
        </div>
      </div>
    </nav>
    <% 	if(memVO!=null){%>
    	<div class="memPic">
		
		<a href="<%=request.getContextPath()%>/front_end/mem/MemManagePersonal.jsp" style="width:100%;height:100%;">
			<c:if test="${memVO.mem_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${memVO.mem_id}"> 
			</c:if>
			<c:if test="${memVO.mem_pic==null}">
				<c:if test='${memVO.mem_gender.equals("男")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
				</c:if>
				<c:if test='${memVO.mem_gender.equals("女")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
				</c:if>
			</c:if>
		</a>
	</div>
<!-- ---------------詳細------------------ -->
	<div class="memInfo">
		<div class="memText">
			<h1>歡迎! <%=memVO.getMem_name()%></h1>
			<h2>點數/<%=memVO.getMem_point() %></h2>
		</div>
	</div>
	<div class="memInfo box"></div>
     <%}else if(coaVO!=null){ %>    <%--*************************************************************** --%>
    <div class="memPic">
		<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}">
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${coaVO.coa_id}"> 
			</c:if>
			<c:if test="${memVO.mem_pic==null}">
				<c:if test='${coaVO.coa_gender.equals("男")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
				</c:if>
				<c:if test='${coaVO.coa_gender.equals("女")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
				</c:if>
			</c:if>
		</a>
	</div>
<!-- ---------------詳細------------------ -->
	<div class="memInfo">
		<div class="memText">
			<h1>歡迎! <%=coaVO.getCoa_name()%></h1>
			<h2>點數/99999</h2>
		</div>
	</div>
	<div class="memInfo box"></div>
    <%}%>
<!-- ****************************************頭像*************************************************** -->
			<!---/header--->
					<!-- 側邊攔開始-->
				<div id="wrapper">
					<div class="sidebar">
						<div class="content" style="margin-top: 8%;">
							<div class="col-md-4 about-grid1 wow fadeInLeft animated animated" data-wow-delay="0.4s" id="content1">
								<div class="latest-top" >
								<h4><img src="<%=request.getContextPath()%>/front_end/images/face-24px.svg" id="mem">我的帳戶</h4>
									<div class="latest-class">
										<div class="latest-right">
											<h5>個人檔案</h5>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
								<div class="latest-top" >
										<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#memorder"
											aria-expanded="true" aria-controls="memorder">
											<h4><img src="<%=request.getContextPath()%>/front_end/images/list-24px.svg" id="order">我的訂單</h4>
											<div id="memorder" class="collapse"
												aria-labelledby="headingpoint" data-parent="#accordionSidebar">
												<div class="bg-white py-2 collapse-inner rounded">
													<a class="collapse-item" href="<%=request.getContextPath()%>/front_end/product_order_front/Member_ListOrder.jsp">訂單查詢</a><br>
													<a class="collapse-item" href="<%=request.getContextPath()%>/front_end/product_order_front/Member_Order.jsp">全部訂單</a><br>
												</div> 
										</div>
									</div>
								<div class="latest-top">
								<a class="nav-link collapsed" href="<%=request.getContextPath()%>/front_end/dep/MemManagePersonalDep.jsp"data-toggle="collapse" data-target="#collapsepoint"
								aria-expanded="true" aria-controls="collapsepoint" >
									<h4><img src="<%=request.getContextPath()%>/front_end/images/payment-24px.svg" id="point" >點數儲值</h4></a>

								</div>
								<div class="latest-top">
								<ul>
									<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseActivity"
										aria-expanded="true" aria-controls="collapseActivity">
										<h4><img src="<%=request.getContextPath()%>/front_end/images/date_range-24px.svg" id="Activity">活動紀錄</h4></a>
										<div id="collapseActivity" class="collapse"
										aria-labelledby="headingActivity" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="">揪團紀錄</a><br>
											<a class="collapse-item" href="<%=request.getContextPath()%>/front_end/mem/CrsWeek.jsp">一周課表</a><br> 
										</div>			
									</ul>
									<div class="clearfix"></div>
								</div>
			
								<div class="latest-top">
									<ul>
									<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseArticle" 
										aria-expanded="true" aria-controls="collapseArticle">
									<h4><img src="<%=request.getContextPath()%>/front_end/images/import_contacts-24px.svg" id="Article">文章管理</h4></a>
										<div id="collapseArticle" class="collapse"
										aria-labelledby="headingArticle" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="">新增文章</a><br>
										</div>			
									</ul>
								</div>
								<div class="latest-top" >
								<h4><img src="<%=request.getContextPath()%>/front_end/images/face-24px.svg" id="order">好友管理</h4>
									
								</div>
								<div class="latest-top">
									<ul>
									<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseReport" 
										aria-expanded="true" aria-controls="collapseReport">
										<h4><img src="<%=request.getContextPath()%>/front_end/images/warning.png" id="report">檢舉管理</h4></a>
										<div id="collapseReport" class="collapse"
										aria-labelledby="headingReport" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
										<a class="collapse-item"
										href="<%=request.getContextPath()%>/front_end/ReportCoach/AddReportCoach.jsp">檢舉教練</a><br>
										<a class="collapse-item"
										href="<%=request.getContextPath()%>/front_end/ReportMember/AddReportMember.jsp">檢舉會員</a><br>
										<a	class="collapse-item" href="<%=request.getContextPath()%>/front_end/ReportCourse/AddReportCourse.jsp">檢舉課程</a><br>
								
										</div>			
									</ul>
										</div>
									</div>	
								</div>
							<div>	
						</div>
					</div>
				</div>
							<!-- 側邊欄結束 END -->
							<div class="container">
								
								<div class="pagesbtn1"><%@ include file="pages/page1.file" %></div>
								<div class="col-12 tablebase">
									<c:forEach var="product_orderVO" items="${list2}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
													<c:if test="${product_orderVO.ord_no==order_detailsVO.product_orderVO.ord_no}">
													<tr><td>
														${order_detailsVO.product_orderVO.ord_no}【${productSvc.getOneProduct(order_detailsVO.productVO.pro_no).pro_nam}】
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
									<div class="pagesbtn2"><%@ include file="pages/page2.file" %></div>
								</div>
								<!-- calender END -->
								<!--copy-->
								
								<div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
									<div class="container">
										<div class="social-icons">
											<a href="https://www.facebook.com/"><i class="icon"></i></a>
											<a href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a>
											<a href="https://www.google.com.tw/webhp?tab=rw"><i class="icon2"></i></a>
											<a href="https://www.instagram.com/?hl=zh-tw"><i class="icon3"></i></a>
										</div>
										<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期
											<a href="#https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="#https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
										</div>
									</div>
									
									<!--copy-->
									<!-- 頭像Js -->
									<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
<!-- /頭像Js -->
								</body>
							</html>