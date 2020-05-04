<%@page import="com.reportcoa.model.ReportCoaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ReportCoaVO reportcoaVO=(ReportCoaVO)request.getAttribute("reportcoaVO");
%>
		<!DOCTYPE HTML>
		<html>
		<head>
			<title>個人帳號管理</title>
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
			<style type="text/css">
				@font-face{
					font-family: 'test'; 
					src:url('SentyTEA-20190904.ttf');
				}
				body{
					background-color:#f5f5dc;
					font-family: 'test'; 
				}


				form>b{
					font-size: 20px;
				}
				#mem{
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

			</style>
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
		</head>
		<body>
			<!---header--->
			<div class="navbar navbar-default navbar-fixed-top"style="position:static;">
				<div class="container">
					<div class="navbar-header wow fadeInLeft animated animated" data-wow-delay="0.4s">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a class="navbar-brand" href="首頁.html"><img src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
							<li class="active"><a class="nav-in" href="首頁.html"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li ><a class="nav-in" href="健康量測.html"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
					<li><a class="nav-in" href="課程&教練.html"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="瀏覽揪團.html" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="文章討論區首頁.html" ><span data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="購物商城.html"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
					<li><a class="nav-in" ><span data-letters="登出" ><b>登出</b></span></a></li>
						</ul>
					</div><!--/.nav-collapse -->
				</div>
			</div>
			<!---banner--->
			<div id="wrapper">
					<div class="sidebar">
						<div class="content">
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
								<div class="latest-top">
								<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapsepoint"
								aria-expanded="true" aria-controls="collapsepoint">
									<h4><img src="<%=request.getContextPath()%>/front_end/images/payment-24px.svg" id="point">點數儲值</h4></a>
									<div id="collapsepoint" class="collapse"
										aria-labelledby="headingpoint" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="">aaaaaaaaaaaaa</a><br>
											<a class="collapse-item" href="">vvvvvvvvvvvvv</a><br> 
										</div>			
									</ul>
								</div>	
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
											<a class="collapse-item" href="">一周課表</a><br> 
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
											<a class="collapse-item" href="">MMMMMM</a><br>
											<a class="collapse-item" href="">LLLLLL</a><br> 
										</div>			
									</ul>
								</div>
								<div class="latest-top">
									<ul>
									<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseReport" 
										aria-expanded="true" aria-controls="collapseReport">
										<h4><img src="<%=request.getContextPath()%>/front_end/images/warning.png" id="report">檢舉管理</h4></a>
										<div id="collapseReport" class="collapse"
										aria-labelledby="headingReport" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="AddReportCoach.jsp">檢舉教練</a><br>
											<a class="collapse-item" href="">檢舉會員</a><br> 
											<a class="collapse-item" href="">檢舉文章</a><br>
											<a class="collapse-item" href="">檢舉揪團</a><br> 
											<a class="collapse-item" href="">檢舉課程</a><br>
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
		<!-- Sidebar END -->
		
			<!-- calender END -->
	

			<!--copy-->
		<div class="footer-section">
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
	</div>
				<!--copy-->
			</body>
			</html>