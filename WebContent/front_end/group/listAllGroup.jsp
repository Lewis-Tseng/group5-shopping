<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.gro.group.groupVO"%>
<%@page import="com.gro.group.groupDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    groupDAO dao = new groupDAO();
    List<groupVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
    
    MemVO memVO = (MemVO) session.getAttribute("memVO");
    
    String loginMem=null;
	if(session.getAttribute("memVO")!=null){
		loginMem = memVO.getMem_name();
	};
%>

<jsp:useBean id="groclassSvc" scope="page" class="com.gro.type.groclassService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>Just強身</title>
<!---css--->
<link href="<%=request.getContextPath()%>/front_end/css_group/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/css_group/css/style.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/css_group/css/infoCard.css" rel='stylesheet' type='text/css' />
<!---css--->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!---js--->
<script src="<%=request.getContextPath()%>/front_end/css_group/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/css_group/js/bootstrap.js"></script>
<!---js--->
<!--web-fonts-->
<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css_group/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/css_group/js/wow.min.js"></script>
		<script>
			new WOW().init();
		</script>
	<!--//end-animate-->
	<style type="text/css">
		@font-face{
  		font-family:'test';
  		src:url('SentyTEA-20190904.ttf');
		}
		
		.navbar-nav{
			margin: 10;
		}
		
		#backgroundVideo{
			position: fixed;
  			right: 0;
  			bottom: 0;
  			min-width: 100%;
  			min-height: 100%;
		}

		.aboutGroup{
			width: 90%;
		}
		
		table#table-1 {
    		text-align: center;
 		 }
		table#table-1 h4 {
    		color: red;
    		display: block;
    		margin-bottom: 1px;
  		}
  		h4 {
    		color: blue;
    		display: inline;
  		}
  		
  		table {
			width: 100%;
			margin-top: 5px;
			margin-bottom: 5px;
  		}

  		th, td {
    		color:rgb(255,255,255);
  		}
  		input {
  			color:rgb(25,25,25);
  		}
  		
  		
  		.about-right .group_top{
  			margin-top:2%;
  		}
  		.about-right .group_info{
			width:73%;
			height:160px;
			text-align:left;
  		}
  		.about-right p{
  			float:none;
  			width:20%;
  			margin-left:25%;
  			height:50px;
  			border-radius: 15px;
  			text-align:center;
  		}
  		.about-right img {
  			width:23%;
  			border-radius: 15px;
  		}


  		.group_list>td {
  			width:10%;
  		}

		.card .text{
			width: auto;
			border-radius: 0;
			background-color: rgba(0,0,0,0);
			padding: 0;
			font-size: 1em;
			color: rgb(255,255,255);
			text-align: left;
			position: relative;
			top:10;
		}
		
		
		.about-right {
			width:33%;
			margin-bottom: 1.3em;
		}
		
		.wrapper::before{
			content: '';
  			position: absolute;
  			top:0; right:0; bottom:0; left:0;
  			background-color: hsla(0,0%,100%,.3);
  			filter: blur(30px);
  			z-index: -1;
  			margin: -20px;
		}
		
		.example-2 .header .date {
			background-color: rgba(25,25,25,0.4);
 			padding: 0 10px 0 10px;
  			border-radius: 10px;
		}
		
		.example-2 .menu-contents span {
			background-color: rgba(25,25,25,0.4);
 			padding: 0 10px 0 10px;
  			border-radius: 10px;
		}
		
		.groInfoDiv {
			width: 100%;
			height: 110px;
			overflow:hidden;
			text-overflow:ellipsis;
			word-wrap:break-word;
			font-size:20px;
			font-family:'test';
		}
		
		input[type="submit"]{
			position: relative;
			display: block;
			color: #FFF;
			margin: 0 auto;
			background: rgba(0,0,0,0);
			font-size: 14px;
			text-align: center;
			font-style: normal;
			width: 100%;
			border: 1px solid rgba(0,0,0,0);
			border-width: 1px 1px 3px;
			font-family:'test';

		}
		
		
		body {
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/bgG5.png");
			background-size:cover;
			background-repeat:no-repeat;
			background-attachment:fixed;
			background-position:center;
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
	top:119px;
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
         <h1 ><a class="navbar-brand" href="index.html" style="font-family: test,微軟正黑體;"><img src="<%=request.getContextPath()%>/front_end/css_group/images/G5_LOGO_v2.0.png"/><p style="float:left; font-size: 1em; margin-top: 0.8em;">Just強身</p></a></h1>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
			  <li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
			 <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"><b>健康量測</b></span></a> </li>
             <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/res/CoachPage.jsp"><span data-letters="瀏覽教練"><b>瀏覽教練</b></span></a></li>
				<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp"><span data-letters="揪團運動">揪團運動</span></a></li>
				<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp"><span data-letters="討論區">討論區</span></a></li>
				<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城">購物商城</span></a></li>
<!-- ***************************************頭像登出********************************************** -->
				<%	

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
	<div class="memInfo" style="box-shadow: 5px 5px 5px rgba(40,40,40,0.5);">
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
	<div class="memInfo" style="box-shadow: 5px 5px 5px rgba(40,40,40,0.5);">
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
					<h4 style="color: rgb(70,70,70); font-family: test,微軟正黑體;font-size:50px;margin-left: 30%;">瀏覽揪團</h4>
				</div>
			</div>
		<!---about-->
			<div class="about-section w3-layouts">

				<div class="container aboutGroup">
					<div class="about-grids">
						<div class="col-md-4 about-grid1 wow fadeInLeft animated animated" data-wow-delay="0.4s">
							<div class="latest-top">
								<h4 style="color: rgb(255,255,255);font-family: test,微軟正黑體;"><b>揪團功能</b></h4>

								<ul class="lb-album">
									<li class="grid">
										<a href="<%=request.getContextPath()%>/front_end/group/listAllGroup.jsp">
											<figure class="effect-apollo">
												<div><b style="font-family: test">Group</b><img src="<%=request.getContextPath()%>/front_end/css_group/images/btn4.png"></div>
												<figcaption></figcaption>
											</figure>
										</a>
									</li>
								</ul>
								
								<ul class="lb-album">
									<li class="grid">
										<a href="<%=request.getContextPath()%>/front_end/group/addGroup.jsp" style="line-height:25px;" >
											<figure class="effect-apollo">
												<div><b style="font-family: test;">Create</b><img src="<%=request.getContextPath()%>/front_end/css_group/images/btn1.png"></div>
												<figcaption></figcaption>
											</figure>
										</a>
									</li>
								</ul>
								
								<ul class="lb-album">
									<li class="grid">
										<a href="<%=request.getContextPath()%>/front_end/group/char_select.jsp" >
											<figure class="effect-apollo">
												<div><b style="font-family: test">Management</b><img src="<%=request.getContextPath()%>/front_end/css_group/images/btn2.png"></div>
												<figcaption></figcaption>
											</figure>
										</a>
									</li>
								</ul>
								
								<ul class="lb-album">
									<li class="grid">
										<a href="<%=request.getContextPath()%>/front_end/group/char_select_History.jsp" >
											<figure class="effect-apollo">
												<div><b style="font-family: test">History</b><img src="<%=request.getContextPath()%>/front_end/css_group/images/btn3.png"></div>
												<figcaption></figcaption>
											</figure>
										</a>
									</li>
								</ul>

							</div>
							
						</div>
						<div class="col-md-8 about-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
<!-- -------------------------------------------------------------------瀏覽揪團位置-------------------------------------------- -->
							
							
							<div class="about-bottom">

								
						<table>
							
							<%@ include file="page1.file"%>
							<c:forEach var="groupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<c:if test="${groupVO.gro_stat != -1}">
								<div class="about-right">
										

									<div class="row">
										<div class="example-2 card">
											<div class="wrapper"
												style="background:url(<c:if test="${groupVO.gro_logo_pic != null}">
												<%=request.getContextPath()%>/DBGifReaderGro?gro_id=${groupVO.gro_id}
											</c:if>
											<c:if test="${groupVO.gro_logo_pic == null}">
												<%=request.getContextPath()%>/front_end/css_group/images/G5_LOGO_v2.0.png
											</c:if>);background-size: cover;background-position:center;">
												<div class="header">
													<div class="date">
														<span class="day" style="font-family:'test'">${groupVO.gro_stime}</span>
													</div>
													<ul class="menu-contents">
														<li><span style="font-family:'test';font-size:20px">團長/${memberSvc.getOneMem(groupVO.mem_id).mem_name}</span></li>
													</ul>
												</div>
												<div class="data">
													<div class="contents">
														<h1 class="title"
															style="padding-bottom: 10px; text-align: center;">
															<span class="day">${groupVO.gro_name}</span>
														</h1>
														
														<div class="groInfoDiv">
															${groupVO.gro_info}
														</div> 
														
														<FORM METHOD="post" ACTION="group.do">
															<input type="hidden" name="gro_id" value="${groupVO.gro_id}">
															<input type="hidden" name="action" value="getOne_For_Display"> 
															<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
															<input type="hidden" name="BF_url" value="/front_end"> 
															<a href="#" class="button" style="font-family:'test';">
															<input type="submit" value="Read more"></input></a>
														</FORM>
														
	
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
							</c:forEach>
						</table>

							
<!-- -------------------------------------------------------------------瀏覽揪團位置-------------------------------------------- -->
								<div class="clearfix"></div>
							</div>
							
						</div>
							<div class="clearfix"></div>
								<nav>
					
								<%if (pageNumber > 1) {%>
									<ul class="pagination pagination-lg" style="margin:3% 23% 0% 50%;">
										<%if (whichPage != 1){ %>
										<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
										<%}%>
										
										<%for (int i = 1; i <= pageNumber; i++) {%>
											<%if(i==whichPage) { %>
												<li><a href="<%=request.getRequestURI()%>?whichPage=<%=i%>" style="cursor: pointer;pointer-events: none;color:#E74C3C;" aria-label="Previous"><span aria-hidden="true"><%=i%></span></a></li>
											<%} else {%>
												<li><a href="<%=request.getRequestURI()%>?whichPage=<%=i%>" aria-label="Previous"><span aria-hidden="true"><%=i%></span></a></li>
											<%}%>
										<%}%>
										
										<%if (whichPage < pageNumber){ %>
										<li><a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>" aria-label="Next"><span aria-hidden="true">»</span></a></li>
										<%}%>
						 			 </ul>
						 			 
						 		<%}%>
						 		</nav>




						 		<div class="clearfix"></div>


							</div>
						</div>
					</div>

					
			
		<!---about-->	

	<!--copy-->
	<div class="copy-section wow fadeInUpBig animated animated" data-wow-delay="0.4s">
		<div class="container">
				<div class="social-icons">
					<a href="#"><i class="icon"></i></a>
					<a href="#"><i class="icon1"></i></a>
					<a href="#"><i class="icon2"></i></a>
					<a href="#"><i class="icon3"></i></a>
				</div>
				<p style="font-family:'test'">Copyright &copy; 2016.Company name All rights reserved.More Templates <a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會出來必是精品</a></p>
		</div>
	</div>
	<!--copy-->
</body>
</html>