<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.coach.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
	CoaService coaSvc = new CoaService();
	List<CoaVO> list = coaSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Trainers</title>
	<!---css--->
	<link href="<%=request.getContextPath()%>/front_end/css/bootstrap_poan.css" rel='stylesheet' type='text/css' />
	<link href="<%=request.getContextPath()%>/front_end/css/style_poan.css" rel='stylesheet' type='text/css' />
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
	<link href="<%=request.getContextPath()%>/front_end/css/galleryeffect_poan.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=request.getContextPath()%>/front_end/css/style_poan.css" rel="stylesheet" type="text/css" media="all" />
	<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css/animate_poan.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>
	<!--//end-animate-->

	
	<style type="text/css">
	
		@font-face {
			font-family: 'test';
			src: url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
		}
		
		body{
			height:100%;
		}

		button{
			font-family: "test";
			font-size: 20px;
		}
		.wm-secondary{
			background-color:#b0b0b0;
			font-family: "test";
			font-size: 20px;
		}
		.wm-secondary:hover{
			background-color:#ababab;
		}
		.wm-secondary:active{
			height:38px;
		}
		.wm-secondary-outline{
			background-color:transparent;
			border: 2px solid #b0b0b0;
			border-radius: 10px;
			color:black;
			text-shadow:none;
		}
		.wm-secondary-outline:hover{
			background-color:#b0b0b0;
			color:white;
		}
		.wm-secondary-outline:active{
			height:38px;
		}
		.dark {
			background:#2C3E50;
		}
		.dark:checked {
			background:#34495E;
		}
		.nav-search {
			/*position:absolute;*/
			right:14px;
			top:13px;
			width: 300px;
			background:rgba(0, 0, 0, 0.1);
			-webkit-border-radius:6px;
			-moz-border-radius:6px;
			border-radius:6px;
			border:none;
			padding:8px;
			padding-left:15px;
			outline:none;
			transition: all .3s ease-in-out;
			-moz-transition: all .3s ease-in-out;
			-webkit-transition: all .3s ease-in-out;
			margin-left: 50px;
		}

		.nav-search:focus {
			background:#fff;
			font-weight:600;
			transition: all .3s ease-in-out;
			-moz-transition: all .3s ease-in-out;
			-webkit-transition: all .3s ease-in-out;
		}
		li {
			list-style:none;
			color:#fff;
			font-weight:600;
			display:inline-block;
		}

		li > a, a:visited {
			padding:15px;
			color:#fff;
			text-decoration:none;
			transition: all .3s ease-in-out;
			-moz-transition: all .3s ease-in-out;
			-webkit-transition: all .3s ease-in-out;
		}

		li > a:hover {
			background:rgba(0, 0, 0, 0.1);
			transition: all .3s ease-in-out;
			-moz-transition: all .3s ease-in-out;
			-webkit-transition: all .3s ease-in-out;
		}

		.nav-search::-webkit-input-placeholder {
			color: #FFF;
		}

		.nav-search:-moz-placeholder { 
			color: #FFF;
		}

		.nav-search::-moz-placeholder {
			color: #FFF;
		}

		.nav-search:-ms-input-placeholder {  
			color: #FFF;
		}
		.dark{
			position:relative;
			width:900px;
			margin:30px auto;
			margin-bottom:40px;	  
			border-radius:6px;
			padding:3px;
			text-align:left;
		}
		.choose_type{
			background-color:#60768b8f;
			color: #fff;
			font-size: inherit;
			padding: .5em;
			padding-right: 2.5em;	
			border: 0;
			margin: -10px;
			margin-top: 2px;
			border-radius: 3px;
			text-indent: 0.01px;
			-webkit-appearance: button; /* hide default arrow in chrome OSX */
		}
		.choose_type {
			position: relative;
			display: inline-block;
			vertical-align: middle;
			margin: 10px; /* demo only */
			font-family: "test";
		}
		.choose_type select{
			text-align: center;
		}
		#search_btn{
			border-radius: 2px;
			background-color:#60768b8f;
			color: white
		}
		.lb-overlay:target img {
    		width: 25%;
  			height: 45%;
		}
		
		#nextPage{
			text-align: center;
		}   
		
		#nextPage1{
			color:white;
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
         <h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img src="<%=request.getContextPath()%>/front_end/images/G5.png" />Just強身</a></h1>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
                   <li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
                    </li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"><b>健康量測</b></span></a> </li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/crs/CrsPage.jsp"><span data-letters="瀏覽課程"><b>瀏覽課程</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp"><span data-letters="揪團運動"><b>揪團運動</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp"><span data-letters="討論區"><b>討論區</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城"><b>購物商城</b></span></a></li>
  <!-- ***************************************頭像登出********************************************** -->
				<%	
				MemVO memVO = (MemVO) session.getAttribute("memVO");
CoaVO coaVO = (CoaVO) session.getAttribute("coaVO");
			if(memVO!=null || coaVO!=null){
 	    	%>
					<li><a class="nav-in"herf="#">
			<span data-letters="登出">
					<form action="mem.do" method="POST">
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
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=<%=memVO.getMem_id() %>"> 
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
		<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=<%=coaVO.getCoa_id() %>">
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/coa/DBGifReaderMem?mem_id=${coaVO.coa_id}"> 
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
	<!---banner--->
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>

	<div class="banner wow fadeInDownBig animated animated" data-wow-delay="0.4s">
		<div class="container">
			<h2 style="color: rgb(50,50,50)">教練瀏覽</h2>
		</div>
	</div>
	
	  <%-- <nav class="dark">	
		<select class="choose_type">
			<option>所有教練</option>
			<option>在線教練</option>
		</select>
		<select class="choose_type" >
			<option >健身項目</option>
			<option >瑜珈</option>
			<option >搏擊</option>
			<option >重量訓練</option>	
			<option >有氧舞蹈</option>
			<option >健身項目</option>				
			<option >健身球</option>					
		</select>
		<select class="choose_type" >
			<option >教練性別</option>
			<option >男教練</option>
			<option >女教練</option>				
		</select>
		<!-- <li><a href="#">Joey</a></li> -->
		<input class="nav-search" type="text" placeholder="Search..."/>
		<button id="search_btn">送出</button>
	</nav> --%>
	<div class="trainers-section wow bounceIn animated" data-wow-delay="0.4s" style="visibility: visible; height:1000px; -webkit-animation-delay: 0.4s; margin-top: 5%; margin-bottom: 5%;">
		<div class="container">
			<section>		
			<%@ include file="pages/page1.file"%> 
				<ul class="lb-album">
				<%int idIndex = 0; %>
				<c:forEach var="coaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<li class="grid">
						<a href="<%="#"+idIndex%>">
							<figure class="effect-apollo">
								<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}" alt=""  height="270" width="300" onerror="nofind()" >
								<figcaption>
								</figcaption>			
							</figure>
						</a>
						<div class="lb-overlay" id="<%=idIndex%>">
							<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}" alt="" onerror="nofind()"  />
							<div class="gal-info">							
								<h3 style=" font-family:test;">${coaVO.coa_name}</h3>
								<div style="height:50%; overflow:hidden;">
									<p style="height:60px; font-family:test; font-size:15px;">${coaVO.coa_intro}</p>
								</div>	
								<Form method="post" ACTION="<%=request.getContextPath()%>/front_end/res/coa.do">
								<a id="deleteUnderline" href="#" onclick="this.parentNode.submit(); return false;">
								<button class="button wm-secondary-outline">查看教練詳情</button>
								<input type="submit" value="送出" style="display: none"> 
								<input type="hidden" name="coa_id" value="${coaVO.coa_id}"> 
								<input type="hidden" name="action" value="ResIndex">
								</a>
								</Form>								
							</div>
							<a href="#" class="lb-close" style=" font-family:test; font-size:25px;">Close</a>
						</div>
					</li>
					<%idIndex++;%>
				</c:forEach>
				</ul>
			<%@ include file="pages/page2.file"%>	
			</section>
		</div>
	</div>
	<!-- //gallery -->
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
					<a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
				</div>
			</div>
		</div>
		<!--copy-->
		
		<script type="text/javascript">
		function nofind(){
			  var img=event.srcElement;
			  img.src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png";
			  img.onerror=null; //控制不要一直觸發錯誤
		}
		
		
	</script>
	</body>
	</html>