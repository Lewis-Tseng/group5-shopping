<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<html>
<head>

<title>會員註冊</title>
<!---css--->
<link
	href="<%=request.getContextPath()%>/front_end/login/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/login/css/style.css"
	rel='stylesheet' type='text/css' />
<!---css--->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!---js--->
<script
	src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
<!---js--->
<!--web-fonts-->
<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!--//web-fonts-->
<!--JS for animate-->
<link
	href="<%=request.getContextPath()%>/front_end/login/css/animate.css"
	rel="stylesheet" type="text/css" media="all">
<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<style type="text/css">
@font-face {
	font-family: 'ggyy';
	src: url(SentyTEA-20190904.ttf);
}

#videobg {
	position: fixed;
	right: 0;
	bottom: 0;
	min-width: 100%;
	min-height: 100%;
	z-index: -1;
}

.btn-ul:hover {
	color: #E74C3C;
}
</style>
</head>
<body>
	<!---header--->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header wow fadeInLeft animated animated"
				data-wow-delay="0.4s">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<h1>
					<a class="navbar-brand" href="首頁.html"><img
						src="<%=request.getContextPath()%>/front_end/images/G5.png" />Just強身</a>
				</h1>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul
					class="nav navbar-nav navbar-right wow fadeInRight animated animated"
					data-wow-delay="0.4s">
					<li class="active"><a class="nav-in" href="首頁.html"><span
							data-letters="首頁"><b>首頁</b></span></a></li>
					<li><a class="nav-in" href="健康量測.html"><span
							data-letters="健康量測"><b>健康量測</b></span></a></li>
					<li><a class="nav-in" href="課程&教練.html"><span
							data-letters="課程&教練"><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="瀏覽揪團.html"><span
							data-letters="揪團運動"><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="文章討論區首頁.html"><span
							data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="購物商城.html"><span
							data-letters="購物商城"><b>購物商城</b></span></a></li>
					<li><a class="nav-in" href="會員登入.html"><span
							data-letters="登入"><b>登入</b></span></a></li>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<!---banner--->
	<div class="banner wow fadeInDownBig animated animated"
		data-wow-delay="0.4s">
		<div class="container">
			<h2 style="color: rgb(50, 50, 50)">會員註冊</h2>
		</div>
	</div>
	<div class="contact w3l">
		<div class="map wow fadeInUpBig animated animated"
			data-wow-delay="0.4s">
			<VIDEO
				src="<%=request.getContextPath()%>/front_end/images/Pexels Videos 1449878.mp4"
				autoplay muted loop id="videobg"></VIDEO>
		</div>
		<div class="map-grids w3-agile">
			<div class="col-md-8 map-middle wow fadeInRight animated animated"
				data-wow-delay="0.4s">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<form action="mem.do" method="post" name="form1">
					<input type="text" name="mem_name" class="mem_name"
						placeholder="會員姓名" required="" size="45"
						value="<%=(memVO == null) ? "吳永志" : memVO.getMem_name()%>">

					<fieldset>

						<label for="radio-1">男</label> <input type="radio"
							name="mem_gender" value="男" checked="true"> <label
							for="radio-2">女</label> <input type="radio" name="mem_gender"
							value="女">
					</fieldset>

					<input type="text" name="mem_email" class="email"
						placeholder="會員帳號" required="" size="50"
						value="<%=(memVO == null) ? "100" : memVO.getMem_email()%>">
					<input type="text" name="mem_psw" placeholder="輸入8~9個英文數字為密碼"
						required=""> <input type="text" name="mem_psw"
						placeholder="再輸入一次你的密碼" required=""
						value="<%=(memVO == null) ? "100" : memVO.getMem_psw()%>">


					<ul class="btn-ul" style="float: left;">
						<li class="btn-li">

							<figure class="figure-apollo"
								style="width: 357px; font-family: 'ggyy'">
								<b>Register</b>
								<br>
								<input type="hidden" name="action" value="insert">
								<input type="submit" value="註冊"
									style="background-color: rgba(100, 100, 100, 0.5);">
								<figcaption></figcaption>
							</figure>

						</li>
					</ul>

				</form>
			</div>
			<div class="col-md-4 map-left wow fadeInLeft animated animated"
				data-wow-delay="0.4s">
				<ul>
					<li><img
						src="<%=request.getContextPath()%>/front_end/images/G5.png"
						style="width: 100%">
				</ul>

			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //map -->

	<div class="copy-section wow fadeInDownBig animated animated"
		data-wow-delay="0.4s">
		<div class="container">
			<div class="social-icons">
				<a href="https://www.facebook.com/"><i class="icon"></i></a> <a
					href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a> <a
					href="https://www.google.com.tw/webhp?tab=rw"><i class="icon2"></i></a>
				<a href="https://www.instagram.com/?hl=zh-tw"><i class="icon3"></i></a>
			</div>
			<p>
				Copyright &copy; 2019.JAVA雲端服務技術養成班第71期 <a
					href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a>
				- Collect from <a href="https://www.tibame.com/" title="資策會"
					target="_blank">中壢資策會第71期出來必是精品</a>
			</p>
		</div>
	</div>

</body>
</html>