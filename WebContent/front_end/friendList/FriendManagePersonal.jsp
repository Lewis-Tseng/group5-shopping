<%@page import="com.coach.model.CoaVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.mem.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.friendList.model.*"%>

<%  
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	// 查詢誰想加我好友
	FriendListService FriendListSvc2 = new FriendListService();
    List<FriendListVO> list2 = FriendListSvc2.getMyAll(memVO.getMem_id());
    pageContext.setAttribute("list2",list2);
%>  		
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />

<html>
		<head>
			<title><title>${memVO.mem_name}的好友列表 </title></title>
			<!---css--->
			<link href="<%=request.getContextPath()%>/front_end/login/css/bootstrap.css" rel='stylesheet' type='text/css' />
			<link href="<%=request.getContextPath()%>/front_end/login/css/style.css" rel='stylesheet' type='text/css' />
			  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script><script  src="./script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/dudu/css/chat_new2_styleJI.css">
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
	font-family: 'test';
	
}
a {
  color: #f5f5f5;
  text-decoration: none;
}
form>b {
	font-size: 20px;
}

#mem {
	float: left;
}

#point {
	float: left;
}

#class {
	float: left;
}

#activity {
	float: left;
}

#article {
	float: left;
}

#wrapper {
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

 table { 
/* 	width: 800px; */
 	background-color: white; 
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
 	font-size: 20px; 
 } 

/* table, th, td { 
/* 	border: 1px solid #CCCCFF; */
/* } */

 th, td { 
 	padding: 5px; 
 	text-align: center; 
} 


.copy-section{
    margin-top:-90%

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
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 8px;
    line-height: 1.42857143;
    vertical-align: middle;
    border-top: 1px solid #ddd;
}

/* ***********************頭像*********************************** */
</style>
<!-- 聊天室CSS開始 -->
<style>
/* ==================================================================================== */
.chatBtn img{
	position: fixed;
    right: 15;
    bottom: 80;
    width: 80;
    z-index: 2;
}
.containerChat{
	opacity:0;
	display:none;
	transition:all 0.5s ease 0s;
	z-index:4;
	bottom:-270;
}
.chatCloseBtn{
	position: absolute;
    top: 5;
    right: 10;
    z-index: 5;
    font-size: 2em;
    background: none;
    border-color: rgba(0,0,0,0);
    font-family:'test';
}

.people::-webkit-scrollbar {
	 width: 0 !important 
}

.people {
	overflow: scroll;
    overflow-x: hidden;
    height: 100%;
}

.containerChat .left {
	border:none;
}
.containerChat .left .people {
	border:none;
}

.containerChat .right {
	bottom: 100;
	background-color:rgba(0,0,0,0);
	transition: 0.5s;
}

.containerChat .right .chat {
	border:none;
	justify-content: normal;
	overflow:srcoll;
	overflow-x: hidden;
	height: calc(100% -90px);
}
.chat-message-counter {
	border:none;
	z-index:5;
}
.containerChat .left .people .person .name {
	font-family:'微軟正黑體';
	font-size: 15px;
	color: rgb(230,230,230);
}
.containerChat .right .top span .name {
	font-family:'微軟正黑體';
}
/* ==================================================================================== */
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
		<body onload="connectChat();" onunload="disconnectChat();">
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
						<h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
					<li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li ><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
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
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=<%=memVO.getMem_id()%>"> 
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
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=<%=memVO.getMem_id() %>"> 
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
						</ul>
					</div><!--/.nav-collapse -->
				</div>
			</div>
			
			<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
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
<!-- 以下為好友列表 -->
	<div class="col-md-8 map-middle wow fadeInRight animated animated" data-wow-delay="0.4s" style="margin-left:120px ;font-family:微軟正黑體 " >

		<table class="table table-striped table-dark">
<!-- 		  <thead> -->
		  	
		    <tr>
		      <th scope="col" style="width:120px"></th>
		      <th scope="col" style="width:180px">頭像</th>
		      <th scope="col" style="width:250px">姓名</th>
		      <th scope="col" style="width:150px">性別</th>
		      <th scope="col" style="width:300px">操作</th>
		    </tr>
<!-- 		  </thead> -->
<!-- 		  <tbody> -->
		  
		<!-- 列出好友 -->
		<%int i =1; %>
		  <c:forEach var="friendListVO2" items="${list2}">
		  <c:if test="${fn:contains(friendListVO2.friend_stat,2)}">
		    <tr>
		      <td><%=i %></td><% i++; %>
		      <td><img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${friendListVO2.his_id}" style="display:block ;width:85px; height:85px; left:30px;top:5px;border-radius:50%" alt=""></td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_gender}</td>
		      <td>		<div class="alert alert-warning" role="alert"     style="background-color: rgba(112,173,239);border-color: rgba(112,173,239);">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/friendList/friendList.do" style="margin-bottom: 0px;">
											<input type="submit" value="刪除好友" style="
																						    background: rgba(0,0,0,0);
																						    border: none;
																						    color: white;
																						">
											<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
											<input type="hidden" name="my_id" value="${memVO.mem_id}">
											<input type="hidden" name="action" value="delete">
										</FORM>
					  </div>
			 </td>
		    </tr>
		 </c:if>
		 
		 <c:if test="${fn:contains(friendListVO2.friend_stat,1)}">
		    <tr>
		      <td><%=i %></td><% i++; %>
		      <td><img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${friendListVO2.his_id}" style="display:block ;width:85px; height:85px; left:30px;top:5px;border-radius:50%" alt=""></td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_gender}</td>
		      <td>		<div class="alert alert-warning" role="alert"     style="background-color: rgba(112,173,239);border-color: rgba(112,173,239);">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/friendList/friendList.do" style="margin-bottom: 0px;">
											<input type="submit" value="確認加入好友" style="
																						    background: rgba(0,0,0,0);
																						    border: none;
																						    color: white;
																						">
											<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
											<input type="hidden" name="my_id" value="${memVO.mem_id}">
											<input type="hidden" name="action" value="confirm">
										</FORM>
					  </div>
			 </td>
		    </tr>
		 </c:if>
		 
		 	 <c:if test="${fn:contains(friendListVO2.friend_stat,0)}">
		    <tr>
		      <td><%=i %></td><% i++; %>
		      <td><img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${friendListVO2.his_id}" style="display:block ;width:85px; height:85px; left:30px;top:5px;border-radius:50%" alt=""></td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
		      <td>${memSvc.getOneMem(friendListVO2.his_id).mem_gender}</td>
		      <td>					<div class="alert alert-warning" role="alert"     style="background-color: rgba(112,173,239);border-color: rgba(112,173,239);">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/friendList/friendList.do" style="margin-bottom: 0px;">
											<input type="submit" value="取消邀請" style="
																						    background: rgba(0,0,0,0);
																						    border: none;
																						    color: white;
																						">
											<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
											<input type="hidden" name="my_id" value="${memVO.mem_id}">
											<input type="hidden" name="action" value="delete">
										</FORM>
					  				</div>
			 </td>
		    </tr>
		 </c:if>
		 
		 </c:forEach>
		 
<!-- 		  </tbody> -->
		</table>	   			
		
		</div>
<!-- 以上為好友列表 -->
		       <div class="clearfix"></div>
					
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
				
<!-- 聊天室開始 -->

<div class="containerChat" style="background-color: rgba(0,0,0,0.2);box-shadow: 0px 0px 20px -3px black;">
        <button class="chatCloseBtn">close</button>
        <div class="left" style="background: rgba(243,243,243,0.9);">
            <div class="top-du" >
            	<div style="background: rgba(40,40,40,0.2);">
            	<c:if test="${memVO.mem_pic!=null}">
             		<img class="offline" src="/DA103G5/DBGifReaderMem?mem_id=${memVO.mem_id}" style="position: fixed;width:75px;top:5px;height: 75px;border-radius: 50%;margin: 11 0;margin-left: 20;" alt="">
             	</c:if>
             	<c:if test="${memVO.mem_pic==null}">
             		<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("男")}'>
						<img src="/DA103G5/front_end/css_group/images/men.png" style="width: 75px;top:5px;border-radius: 50%;margin: 11 0;margin-left: 20;height: 75px;" class="online">
					</c:if>
					<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("女")}'>
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png" style="width: 75px;top:5px;border-radius: 50%;height: 75px;margin: 11 0;margin-left: 20;">
					</c:if>
             	</c:if>
             <span style="position: absolute;right:100px;top:25px;font-size:30px;font-family:'微軟正黑體';">${memVO.mem_name}</span>
				</div>
            </div>
           <ul class="people" style="background: rgba(20,20,20,0.9);font-family:'微軟正黑體';height: 504;">
				<c:forEach var="friendListVO2" items="${list2}" begin="0" >
				<c:if test="${(friendListVO2.his_id != memVO.mem_id) &&(friendListVO2.friend_stat==2)}">
					<li class="person" data-chat="default" style="display: none;"></li>
	                <li class="person" data-chat="${memSvc.getOneMem(friendListVO2.his_id).mem_name}" >
						<span class="chat-message-counter" >1</span>
						<c:if test="${memberSvc.getOneMem(friendListVO2.his_id).mem_pic!=null}">
								<img class="offline" src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${friendListVO2.his_id}" alt="" />
							</c:if> 
							<c:if test="${memberSvc.getOneMem(friendListVO2.his_id).mem_pic==null}">
								<c:if test='${memberSvc.getOneMem(friendListVO2.his_id).mem_gender.equals("男")}'>
									<img class="offline" src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
								</c:if>
								<c:if test='${memberSvc.getOneMem(friendListVO2.his_id).mem_gender.equals("女")}'>
									<img class="offline" src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
								</c:if>
							</c:if>
	                    <span class="name">${memSvc.getOneMem(friendListVO2.his_id).mem_name}</span>
	                    <span class="on-offLine">離線</span>
	                    <span class="preview" style="margin-top: 10px"">I was wondering...</span>
	                </li>
	            </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="right" style="float: right;bottom:0;background:rgba(50,50,50,1);font-family:'微軟正黑體';"">
            <div class="top" style="background: rgba(255,255,255,0.9);"><span>To: <span class="name"></span></span></div>
            <div class="chat" id="default" data-chat="default" style="display: none;"></div>
            <c:forEach var="friendListVO2" items="${list2}" >
            <c:if test="${friendListVO2.his_id != memVO.mem_id }">
<!--             ================================= -->
            <div class="chat" id="${memSvc.getOneMem(friendListVO2.his_id).mem_name}" data-chat="${memSvc.getOneMem(friendListVO2.his_id).mem_name}"> 
<!--             ================================= -->
                <div class="conversation-start" style="top:10px">
                    <span>開始聊天吧</span>
                </div>
            </div> 
            </c:if>
            </c:forEach> 
        </div>

        <div class="right" style="height: 100; background-color: rgba(255,255,255,0.7);float:right;">
        	<div class="write">
                <a href="javascript:;" class="write-link attach"></a>
                <a href="javascript:;" class="write-link smiley"></a>
                <input type="text" id="inputplace" placeholder="快發送你的第一句話吧..." />
                <a href="javascript:submit();" class="write-link send"></a>
            </div>
        </div>
    </div>

    <div class="chatBtn"><img src="<%=request.getContextPath()%>/front_end/css_group/images/chatroomIcon.png"></div>

<!-- 聊天室結束 -->




				
				
			</body>
			
			
<script type="text/javascript">
var noSeeNum= 1;
document.querySelector('.chat[data-chat=default]').classList.add('active-chat');
document.querySelector('.person[data-chat=default]').classList.add('active');
let friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .person'),
  name: '' },
chat = {
  container: document.querySelector('.containerChat .right'),
  current: "",
  person: "",
  name: document.querySelector('.containerChat .right .top .name') };
friends.all.forEach(function(f) {
  f.addEventListener('mousedown', function(){
    f.classList.contains('active') || setAciveChat(f);
  });
});
function setAciveChat(f) {
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  friends.name = f.querySelector('.name').innerText;
  chat.name.innerHTML = friends.name;
  $('.person.active .chat-message-counter').fadeOut(50,'swing');
  $('.person.active .chat-message-counter').html("1");
  noSeeNum= 1;
}

var MyPointChat = "/MyEchoServer" +"/" + '${memVO.mem_name}' + "/" + "阿昆";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURLChat = "ws://" + window.location.host + webCtx + MyPointChat;
var webSocketChat;
function connectChat() {
	webSocketChat = new WebSocket(endPointURLChat);
	
	webSocketChat.onopen = function(event) {
		$('.top-du img').removeClass('offline');
		$('.top-du img').addClass('online');
		console.log("聊天室開啟");
	};
	webSocketChat.onmessage = function(event) {
		var jsonObj;
		var getFrom;
		var message2;
		var whoMessage;
		var noSeeFromSocket;
//===============================================================id要加
		if($('.active-chat').attr("id")!="default"){
			var fuck = $('.active-chat').attr("id");
			console.log(fuck);
			var chatArea = document.getElementById(fuck);
			chatArea.scrollTop = chatArea.scrollHeight;
		}
//===============================================================
		console.log(event.data);
		
		if(event.data.includes('*')){
			var cutList; 
			cutList=(event.data).split("*");
			console.log("上線名單: " + cutList);
				for(var i=0; i<(cutList.length)-1 ; i++){
					$('.person[data-chat='+cutList[i]+'] img').removeClass('offline');
					$('.person[data-chat='+cutList[i]+'] img').addClass('online');
					$('.person[data-chat='+cutList[i]+'] .on-offLine').html('上線中');
				}
		}else if(event.data.includes('#')){
			var whoOffline;
			whoOffline = (event.data).split("#");
			console.log(whoOffline[0] +"下線拉");
			$('.person[data-chat='+whoOffline[0]+'] img').removeClass('online');
			$('.person[data-chat='+whoOffline[0]+'] img').addClass('offline');
			$('.person[data-chat='+whoOffline[0]+'] .on-offLine').html('離線')
		}else if(!(event.data).includes('{')){
			var whoOnline;
			whoOnline = event.data;
			console.log(event.data+"上線拉");
			$('.person[data-chat='+whoOnline+'] img').removeClass('offline');
			$('.person[data-chat='+whoOnline+'] img').addClass('online');
			$('.person[data-chat='+whoOnline+'] .on-offLine').html('上線中');
		}else{
	        jsonObj = JSON.parse(event.data);
	        getFrom = jsonObj.userName;
	        message2 = jsonObj.message;
	        noSeeFromSocket = jsonObj.noSee;
	        whoMessage = getFrom + ": " + message2 + "\r\n";
	        console.log("伺服器回傳的未讀次數:" + noSeeFromSocket);
				if( getFrom != $('.person.active .name').text() ){
					if( ( ($('.chat[data-chat='+getFrom+'] .conversation-start:contains("未讀訊息")')).text() )=="" ){
						 $('.chat[data-chat='+getFrom+'] .conversation-start').html('<span>以下為尚未閱讀的訊息</span>');
					}
					$('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo( $('.chat[data-chat='+getFrom+']') );
					$('.person[data-chat='+getFrom+'] .preview').html('<span>'+getFrom+': </span>'+message2);
					$('.person[data-chat='+getFrom+'] .chat-message-counter').fadeIn(300, 'swing');
						if($('.person[data-chat='+getFrom+'] .chat-message-counter').html()==1 && noSeeNum== 1){
							noSeeNum++;
							console.log("是1的:" + noSeeNum);
							noSeeJson= {"me" : '${memVO.mem_name}', "him" : getFrom, "noSeeNum" : noSeeNum.toString() };
							webSocketChat.send(JSON.stringify(noSeeJson));
						}else{
							
					   		$('.person[data-chat='+getFrom+'] .chat-message-counter').html(noSeeFromSocket);
					   		noSeeFromSocket++ ;
							console.log("不是1的:" + noSeeFromSocket);
							noSeeJson= {"me" : '${memVO.mem_name}', "him" : getFrom, "noSeeNum" : noSeeFromSocket.toString() };
							webSocketChat.send(JSON.stringify(noSeeJson));
						}
				}else{
			        $('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat.active-chat'));
			    	$('.person.active .preview').html('<span>'+getFrom+': </span>'+message2);
			    	$('.chat[data-chat='+getFrom+'] .conversation-start').html('');
			    	
				}
		}
	};
	
	webSocketChat.onclose = function(event) {
		$('.top-du img').removeClass('online');
		$('.top-du img').addClass('offline');
	};
	
};
	function submit() {
		newMessage();
	};
	
	$(window).on('keydown', function(e) {
	  if (e.which == 13) {
//===============================================================id要加
			if($('.active-chat').attr("id")!="default"){
				var fuck = $('.active-chat').attr("id");
				console.log(fuck);
				var chatArea = document.getElementById(fuck);
				chatArea.scrollTop = chatArea.scrollHeight;
			}
//===============================================================
		  newMessage();
	    return false;
	  }
	});
function newMessage() {
	message = $(".write input").val();
	if($.trim(message) == '') {
		return false;
	}
	jsonObj= {"userName" : '${memVO.mem_name}', "message" : message, "hisName" : $('.person.active .name').text() };
	webSocketChat.send(JSON.stringify(jsonObj));
	$('<div class="bubble me"><p>'+message+'</p></div>').appendTo($('.chat.active-chat'));
	$('.person.active .preview').html('<span>我: </span>' + message);
	$('.write input').val(null);
};

function disconnectChat () {
	webSocketChat.close();
	console.log("聊天室離開");
};
</script>

<script type="text/javascript">
 	$('img').on('click', function() {
 		$('.wrapper').fadeToggle(300, 'swing');
 	});
 	$('.chat-close').on('click', function(e) {
 		e.preventDefault();
 		$('#live-chat').fadeOut(300)});
</script>

<script>
		$('.chatBtn').click(function(){
        		$('.containerChat').css("display","initial");
        		if($('.containerChat').css("display")=="block"){
        			$('.containerChat').css("opacity","1");
        		}
        		
        	});
        	$('.chatCloseBtn').click(function(){
        		
        		if($('.containerChat').css("display")=="block"){
        			$('.containerChat').css("opacity","0");
        		}
        		setTimeout(function(){
        			$('.containerChat').css("display","none");
        		},500);
        		
        	});
</script>			
			
			
			</html>