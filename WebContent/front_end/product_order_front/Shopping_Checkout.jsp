<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.order_details.model.*" %>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.coach.model.CoaVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Product_OrderVO product_orderVO = (Product_OrderVO) request.getAttribute("product_orderVO");
%>
<%
MemVO loginVO = (MemVO) request.getAttribute("loginVO");
Object account = session.getAttribute("loginVO");                  // 從 session內取出 (key) account的值
if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
response.sendRedirect(request.getContextPath()+"/front_end/mem/MemLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
return;
}
%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商品結帳 - ${memVO.mem_name}</title>
		<!---css--->
		<link href="<%=request.getContextPath()%>/front_end/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<link href="<%=request.getContextPath()%>/front_end/css/style.css" rel='stylesheet' type='text/css' />
		<!---css--->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!---js--->
		<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
		<!---js--->
		<!--web-fonts-->
		<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
		<!--//web-fonts-->
		<!--JS for animate-->
		<link href="<%=request.getContextPath()%>/front_end/css/animate.css" rel="stylesheet" type="text/css" media="all">
		<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
		<script>
			new WOW().init();
		</script>
		<!--//end-animate-->
		
		<!--CSS-->
<style type="text/css">
			body{
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/bg1.png");
			background-size:cover;
			background-repeat:no-repeat;
			background-attachment:fixed;
			background-position:center;
			}
			.banner1{
					height: 100px;
						}
			.proimg1{
				height: 500px;
				width: 500px;
			}
			#pronam1{
				color: #FFFFFF;
			}
			.checkbasebox{
				background-color: rgba(0, 0, 0, 0.2);
				color: #FFFFFF;
			}
			.pantable{
				color: #000000;
			}
			.pagesbtn1{
		margin: 0;
		text-align: center;
		padding: 1em;
		}
			.table1{
		width: 100%;
		font-size: 17px;
		}
		.table1 tr th{
		width: 20%;
		}
		.table2{
			font-size: 17px;
		}
		.orderdiv1{
			padding: 1em;
		}
		.amountdiv{
			display: inline-block;
			color: #FFFFFF;
			font-size: 20px;
		}
		input[type="submit"] {
		outline: none;
		border: none;
		width: 100%;
		background: #1E1E1E;
		color: #fff;
		font-size: 1.2em;
		padding: 9px 0px;
		display: block;
		margin-top: 1em;
		text-transform: uppercase;
		transition: .5s all;
		-webkit-transition: .5s all;
		-moz-transition: .5s all;
		-o-transition: .5s all;
		-ms-transition: .5s all;
		}
		input[type="submit"]:hover{
		background: rgb(37,122,128);
		}
		.tablehead{
		font-size: 20px;
		}
		.erroMsgs{
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
MemVO memVO = (MemVO) session.getAttribute("memVO");
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
<li><a href="<%=request.getContextPath()%>/front_end/product_front/Product_Cart.jsp" title="購物車連結"><img src="<%=request.getContextPath()%>/front_end/product_order_front/product_order_front_images/icon_Cart (2).png" alt="購物車" border="0"></a></li>
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
<!---banner--->
<div class="container banner1">
	
</div>
<!---banner--->
<div>
	
</div>
<!---class--->
<div class="class">
	<div class="container checkbasebox">
		<div class="class-grids w3l">
			<!--商品主欄位-->
			<div class="col-md-12 class-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
				<div class="class-top">
					<div class="col-md-12 ">
						<div class="pagesbtn1 col">
							<font size="+3">商品結帳</font>
						</div>
						<!--購物車內容-->
						<div class="panel panel-default pantable">
							<!-- Default panel contents -->
							<div class="panel-heading tablehead">Just強身</div>
							<table class="table table-responsive table1" >
								<thead>
									<tr>
										<th>商品類別</th>
										<th>商品名稱</th>
										<th>商品編號</th>
										<th>單價</th>
										<th>數量</th>
										<th></th>
									</tr>
								</thead>
								<%  @SuppressWarnings("unchecked")
								Vector<Order_Details_ProductVO> od_buylist = (Vector<Order_Details_ProductVO>) session.getAttribute("shoppingcart");
								String amount =  (String) request.getAttribute("amount");
								String proAllqua =  (String) request.getAttribute("proAllqua");
								%>
								<%	for (int i = 0; i <  od_buylist.size(); i++) {
								Order_Details_ProductVO order_details = od_buylist.get(i);
								%>
								<%
								Product_CategoryService product_categorySvc = new Product_CategoryService();
								Product_CategoryVO product_categoryVO = product_categorySvc.getOneProduct_Category(order_details.getCat_no());
								%>
								<tbody>
									<tr>
										<td><%=product_categoryVO.getCat_nam()%></td>
										<td><%=order_details.getPro_nam()%></td>
										<td><%=order_details.getPro_no()%></td>
										<td><%=order_details.getPro_pri()%></td>
										<td><%=order_details.getPro_quantity()%> </td><td></td>
									</tr>
								</tbody>
								<%}%>
							</table>
						</div>
						<div class="amountdiv">
							總金額：$<%=amount%>
						</div>
						<div class="clearfix"></div>
						<!--/購物車內容-->
					</div>
					<!--訂單內容-->
					<form name="shoppingForm" action="<%=request.getContextPath()%>/front_end/product_order/product_order.do" method="POST">
						<input type="hidden" name="shoppingcart" value="<%=od_buylist%>">
						<div class="orderdiv1 col-md-8">
							<table class="table2">
								<tr>
									<td>會員姓名:</td>
									<td><input type="hidden" name="mem_id" value="${memVO.mem_id}">${memVO.mem_name}</td>
								</tr>
								<tr>
									<td>會員信箱:</td>
									<td>${memVO.mem_email}</td>
								</tr>
								<tr>
									<td>訂單總金額:</td>
									<td>&#12288;$<%=amount%></td>
								</tr>
								
								<tr>
									<td>商品總數量:  </td>
									<td>&#12288;<%=proAllqua%></td>
								</tr>
								<tr>
									<td>付款方式:</td>
									<td><input type="radio" name="pay_met"
										value="點數" checked="true" />點數
										<input type="radio" name="pay_met"
									value="信用卡"  />信用卡</td>
								</tr>
								<tr>
									<td>配送地址:</td>
									<td><input type="TEXT" name="del_add" size="45" style="color:rgb(0,0,0)"
									value="<%= (product_orderVO==null)? memVO.getAddress() : product_orderVO.getDel_add()%>" /></td>
								</tr>
							</table>
							<br>
							<input type="hidden" name="action" value="payment">
							<input type="submit" value="結帳"></FORM>
						</div>
						<!--/訂單內容-->
					</div>
					<div class="col-md-3 erroMsgs">
							<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
							</c:if>
					</div>
					</div>
				<!--/商品主欄位-->
				<!--側邊欄位-->
				
				<!--/側邊欄位-->
			</div>
		</div>
	</div>
</div>
<!---class--->
<!--信用卡-->
<!--/信用卡-->
<!---footer--->
<div class="footer-section">
	<div class="container preload">
		<div class="footer-grids">
			<div class="col-md-3 footer-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
				<h4>About</h4>
				<p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy hendrerit mauris. Praesent vestibulummolestie lacus.</p>
			</div>
			<div class="col-md-3 footer-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
				<h4>Categories</h4>
				<ul>
					<li>Beauty</li>
					<li>Diet & Fitness</li>
					<li>Lifestyle</li>
					<li>Help Desk</li>
					<li>Pregnancy</li>
					<li>Performance Metrics</li>
				</ul>
			</div>
			<div class="col-md-3 footer-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
				<h4>Work</h4>
				<ul>
					<li>Customer Support</li>
					<li>Platinum Support</li>
					<li>Gold Support</li>
					<li>Training</li>
					<li>Workshops</li>
					<li>Online Training</li>
				</ul>
			</div>
			<div class="col-md-3 footer-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
				<h4>Contact</h4>
				<p>7801 Marmora Road</p>
				<p>Glasgow, DO5 98GR.</p>
				<p>Freephone: +1 800 558 8990</p>
				<p>Telephone: +1 659 803 9035</p>
				<p>FAX: + 1 314 889 9898</p>
				<a href="mailto:example@mail.com"> example@mail.com</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!---footer--->
<!--copy-->
<div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
	<div class="container">
		<div class="social-icons">
			<a href="#"><i class="icon"></i></a>
			<a href="#"><i class="icon1"></i></a>
			<a href="#"><i class="icon2"></i></a>
			<a href="#"><i class="icon3"></i></a>
		</div>
	</div>
</div>
<!--copy-->
<!--信用卡CSS和Js-->

<!--/信用卡CSS和Js-->
</body>
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
</html>