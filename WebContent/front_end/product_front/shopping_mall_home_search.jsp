<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.coach.model.CoaVO"%>
<%@page import="java.util.stream.Collectors"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:useBean id="listProduct_ByCompositeQuery" scope="request" type="java.util.List<ProductVO>" />  --%>
	<%
	List<ProductVO> list =  (List) session.getAttribute("listProduct_ByCompositeQuery");
	
		List<ProductVO> list3 = new ArrayList<ProductVO>();
			list3 = list.stream()
			.filter(p -> p.getPro_sta().equals("0"))
			.collect(Collectors.toList());
			Collections.reverse(list3);
			
			pageContext.setAttribute("list3", list3);
				%>
				
				<jsp:useBean id="product_imageSvc" scope="page" class="com.product_image.model.Product_ImageService"/>
				<!DOCTYPE HTML>
				<html>
					<head>
						<title>購物商城</title>
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
						<!--sweetalert-->
						<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
						<!--sweetalert-->
						<!--web-fonts-->
						<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
						<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
						<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
						<!--//web-fonts-->
						<link href="<%=request.getContextPath()%>/front_end/css/galleryeffect.css" rel="stylesheet" type="text/css" media="all" />
						<link href="<%=request.getContextPath()%>/front_end/css/style.css" rel="stylesheet" type="text/css" media="all" />
						<!--JS for animate-->
						<link href="<%=request.getContextPath()%>/front_end/css/animate.css" rel="stylesheet" type="text/css" media="all">
						<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
						<script>
							new WOW().init();
						</script>
						<!--//end-animate-->
						<!--CSS-->
						<style type="text/css">
						.banner1{
						height: 100px;
						}
						body{
						text-align: center;
						background:url("<%=request.getContextPath()%>/front_end/css_group/images/bg1.png");
						background-size:cover;
						background-repeat:no-repeat;
						background-attachment:fixed;
						background-position:center;
						}
						.proboxbase{
						background-color: rgba(0, 0, 0, 0.2);
						position: relative;
						width:100%;
						height:100%;
						}
						.probox1{
						height:800px;
						display: inline-block;
						margin: 1px;
						width:100%;
						}
						.probox2{
						overflow: hidden;
						margin:0px;
						margin-bottom: 5px;
						margin-top: 5px;
						float: left;
						height: 385px;
						}
						.class-text{
						width: 100%;
						}
						.proimg1{
						height:220px;
						width: 100%;
						}
						.pagesbtn1{
						margin: 1px;
						}
						.pagesbtn2{
						margin: 1px;
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
	<li><a href="<%=request.getContextPath()%>/front_end/product_front/Product_Cart.jsp" title="購物車連結"><img src="<%=request.getContextPath()%>/front_end/product_front/product_front_images/icon_Cart (2).png" alt="購物車" border="0"></a></li>
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
					<!---/banner--->
					<!---ShooppingBox頭--->
					<div class="trainers-section wow bounceIn animated container" data-wow-delay="0.4s" style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<div class="proboxbase">
							<div class="pagesbtn1">
								<div class="errorMsgs" >
									<%-- 錯誤表列 --%>
									<c:if test="${not empty errorMsgs}">
									<font>請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
										</c:forEach>
									</ul>
									</c:if>
								</div>
								<div><%@ include file="pages/page1_search.file" %></div>
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
									<nav class="navbar navbar-default">
										<div class="container-fluid">
											<!-- Brand and toggle get grouped for better mobile display -->
											<div class="navbar-header">
												<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
												<span class="sr-only">Toggle navigation</span>
												<span class="icon-bar"></span>
												<span class="icon-bar"></span>
												<span class="icon-bar"></span>
												</button>
												<a class="navbar-brand boxbrand" href="#">Just強身</a>
											</div>
											<!-- Collect the nav links, forms, and other content for toggling -->
											<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
												<ul class="nav navbar-nav">
													<li class="active"><a href="#"><span class="sr-only">(current)</span></a></li>
													<li><a href="#"></a></li>
<!-- 													<li class="dropdown"> -->
<!-- 														<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a> -->
<!-- 														<ul class="dropdown-menu"> -->
<!-- 															<li><a href="#">Action</a></li> -->
<!-- 															<li><a href="#">Another action</a></li> -->
<!-- 															<li><a href="#">Something else here</a></li> -->
<!-- 															<li role="separator" class="divider"></li> -->
<!-- 															<li><a href="#">Separated link</a></li> -->
<!-- 															<li role="separator" class="divider"></li> -->
<!-- 															<li><a href="#">One more separated link</a></li> -->
<!-- 														</ul> -->
<!-- 													</li> -->
												</ul>
												<form class="navbar-form navbar-left">
													<div class="form-group">
														<input type="text" class="form-control" placeholder="Search" name="pro_nam">
														<input type="hidden" name="pro_sta" value="0">
														<input type="hidden" name="action" value="listProduct_ByCompositeQuery">
													</div>
													<button type="submit" class="btn btn-default">搜尋</button>
												</form>
												<ul class="nav navbar-nav navbar-right">
													<li><a href="#"></a></li>
<!-- 													<li class="dropdown"> -->
<!-- 														<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a> -->
<!-- 														<ul class="dropdown-menu"> -->
<!-- 															<li><a href="#">Action</a></li> -->
<!-- 															<li><a href="#">Another action</a></li> -->
<!-- 															<li><a href="#">Something else here</a></li> -->
<!-- 															<li role="separator" class="divider"></li> -->
<!-- 															<li><a href="#">Separated link</a></li> -->
<!-- 														</ul> -->
<!-- 													</li> -->
												</ul>
												</div><!-- /.navbar-collapse -->
												</div><!-- /.container-fluid -->
											</nav>
										</form>
										
									</div>
									<div class="probox1">
										<c:forEach var="productVO" items="${list3}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<c:if test="${productVO.pro_sta==0}">
										<!--商品視窗-->
										<div class="col-md-4 class-left probox2">
											<!--商品圖片-->
											
											<c:forEach var="product_imageVO" items="${product_imageSvc.all}">
											<c:if test="${product_imageVO.pro_no == productVO.pro_no}">
											<img src="<%=request.getContextPath()%>/Reader_Image?pro_img_no=${product_imageVO.pro_img_no}" class="img-responsive proimg1" alt="商品圖片"  title="300px"/>
											</c:if>
											</c:forEach>
											<!--/商品圖片-->
											<div class="class-text  hvr-bounce-to-bottom">
												<form action="<%=request.getContextPath()%>/front_end/product/product.do" method="POST">
													<input type="hidden" name="pro_no" value="${productVO.pro_no}">
													<input type="hidden" name="action" value="getOne_For_Display_Front">
													<button type="submit" class="btn btn-primary btn-lg"><h4>${productVO.pro_nam}</h4></button>
												</form>
												<p><h1>售價:${productVO.pro_pri}</h1></p>
												<div id="btn_cart">
													<input type="hidden" class="${productVO.pro_no}" size="1" value="1" name="pro_quantity">
													<button class="btn btn-primary btn-lg btncart send" id="send" pro_quantity="1" prono="${productVO.pro_no}">加入購物車</button>
												</div>
												<!-- 												</form> -->
											</div>
										</div>
										<!--/商品視窗-->
										</c:if>
										</c:forEach>
										<div class="clearfix"></div>
									</div>
									<div class="pagesbtn2">
										<%@ include file="pages/page2_search.file" %>
									</div>
								</div>
							</div>
							<!---/ShooppingBox尾--->
							<%-- 				<jsp:include page="<%=request.getContextPath()%>/front_end/product_front/Product_Cart.jsp" flush="true" /> --%>
							<!-- //gallery -->
							<!---footer--->
							<div class="footer-section">
								<div class="container">
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
							<!--Js範圍-->
							<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
							<script>
							
							$('button[class*="send"]').click(function(){
								
								var prono = $(this).attr('prono');
								var select = "input[class=\""+prono+"\"]";
								var quantity =$(select).val();
								
							$.ajax({
								url:"<%=request.getContextPath()%>/front_end/shopping_product/shopping_product.do",
								type:"post",
								data:{
									action : "ADD",
									pro_quantity : quantity,
									pro_no : prono
								},
								success:function(){
									Swal.fire(
											"\""+ quantity +"\"個商品加入購物車成功!",
											"Thank you",
											'success'
											)
								}
							});
							});
							
							// 							$(".btncart").click(function(){
								// 								Swal.fire(
								// 								'Good job!',
								// 								'You clicked the button!',
								// 								'success'
								// 								)
								// 								});
							
							</script>
						</body>
					</html>