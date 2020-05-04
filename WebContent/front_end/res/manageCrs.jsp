<%@page import="com.crs.model.CrsService"%>
<%@page import="com.res.model.ResVO"%>
<%@page import="java.util.List"%>
<%@page import="com.res.model.ResService"%>
<%@page import="com.crs.model.CrsVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	ResService resSvc = new ResService();
	List<ResVO> list = resSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	CrsService crsSvc = new CrsService();
	List<CrsVO> listt = crsSvc.getAll();
	pageContext.setAttribute("listt", listt);
%>

<%
    CoaVO coaVO = (CoaVO) session.getAttribute("coaVO");
%>
<html>
<head>
<title>教練管理課程</title>
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
<style type="text/css">
a {
	color: #f5f5f5;
	text-decoration: none;
}

@font-face {
	font-family: 'test';
	src:
		url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
}

body {
	background:
	url("<%=request.getContextPath()%>/front_end/images/bgG5.png");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	font-family: 'test';
	
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


th, td {
	text-align: center;
}
.copy-section{
    margin-top:-90%

}
form h1 {
	font-size: 36px;
	background: #F6AA93 none repeat scroll 0% 0%;
	color: rgb(255, 255, 255);
	padding: 22px 25px;
	border-radius: 5px 5px 0px 0px;
	margin: auto;
	text-shadow: none;  
}
#coachProfil {
	border-radius: 5px;
	max-width: 700px;
	width: 100%;
	margin: 0 auto;
	background-color: #00000000;;
	overflow: hidden;
	float: left;
	margin-left: 20%;
}

p span {
	color: #F00;
}

p {
    margin: 0px;
    font-weight: bold;
    line-height: 2;
    color: #333;
    font-size: 25px;
}

h1 {
	text-align: center;
	color: #666;
	text-shadow: 1px 1px 0px #FFF;
	/*margin:50px 0px 0px 0px*/
}

input {
	border-radius: 0px 5px 5px 0px;
	border: 1px solid #eee;
	width: 100%;
	height: 40px;
	font-size: 25px;
	font-weight: bold;
}

a {
	text-decoration: inherit
}

textarea {
	border-radius: 0px 5px 5px 0px;
	border: 1px solid #EEE;
	margin: 0;
	float: left;
	padding: 0px 5px;
	resize: none;
	width: 100%;
    height: 36%;
}

.form-group  {
	overflow: hidden;
	clear: both;
}

.latest-top{
	cursor: pointer;
}

.icon-case {
	width: 35px;
	float: left;
	border-radius: 5px 0px 0px 5px;
	background: #eeeeee;
	height: 42px;
	position: relative;
	text-align: center;
	line-height: 40px;
}

h3{
	font-family:"test";
}

i {
	color: #555;
}

.contentform {
	padding: 40px 30px;
}

.bouton-contact {
	background-color: #81BDA4;
	color: #FFF;
	text-align: center;
	width: 100%;
	border: 0;
	border-radius: 0px 0px 5px 5px;
	cursor: pointer;
	margin-top: 40px;
	font-size: 18px;
}

.leftcontact {
	width: 49.5%;
	float: left;
	border-right: 1px dotted #CCC;
	box-sizing: border-box;
	padding: 0px 15px 0px 0px;
}

.rightcontact {
	width: 49.5%;
	float: right;
	box-sizing: border-box;
	padding: 0px 0px 0px 30px;
}

.validation {
	display: none;
	margin: 0 0 10px;
	font-weight: 400;
	font-size: 13px;
	color: #DE5959;
}

#sendmessage {
	border: 1px solid #fff;
	display: none;
	text-align: center;
	margin: 10px 0;
	font-weight: 600;
	margin-bottom: 30px;
	background-color: #EBF6E0;
	color: #5F9025;
	border: 1px solid #B3DC82;
	padding: 13px 40px 13px 18px;
	border-radius: 3px;
	box-shadow: 0px 1px 1px 0px rgba(0, 0, 0, 0.03);
}

#sendmessage.show, .show {
	display: block;
}

  
:hover + label:after {
    background: #f35429;
  }
 
label:after {
    content: '';
    margin-right: 10px;
    display: inline-block;
    vertical-align: text-top;
    width: 20px;
    height: 20px;
    background: white;
    margin-left: 1%;
}
:hover + label:after {
    background: #f35429;
 }
 
 .copy-section {
    padding: 2em 0;
    text-align: center;
    background: #1E1E1E;
    margin-top: 200px;
}

#secondTr td {
    font-size: 40px;
    font-weight: bold;
    height: 90px;
    margin-right: 200px;
    padding-right: 20px;

#firstTr td{
	font-size:45px;
	font-weight: bold;
}

select[multiple], select[size] {
    height: 4%;
}

select{
	font-size:20px;
	font-weight:bold;
}
.fc-unthemed .fc-content, .fc-unthemed .fc-divider, .fc-unthemed .fc-list-heading td, .fc-unthemed .fc-list-view, .fc-unthemed .fc-popover, .fc-unthemed .fc-row, .fc-unthemed tbody, .fc-unthemed td, .fc-unthemed th, .fc-unthemed thead {
   		border-color: #ccc;
   		text-align:left;
	}
	.fc-toolbar.fc-header-toolbar {
	    margin-bottom: 1em;
	    height: 10%;
	}
	
	.fc button, .fc table, body .fc {
	    font-size: 26px;
	    background-color: white;
	}
	.fc-unthemed td.fc-today {
	    background: #ccc;
	}
	
	#calendar{
		margin-top:8%;
		border-radius:1%;
		padding: 20px 30px;
	}
	
	.fc .fc-toolbar>*>:first-child {
		font-weight: bold;
	}
	
	.fc-view-container *, .fc-view-container :after, .fc-view-container :before {
		font-weight:bold;
	    color: black;
	}
	
	.fc-scroller {
	   overflow-y: hidden !important;
	}
	#calendarDiv{
		width: 60%;
	    margin-left: 25%;
	}
	
	#tableDiv {
		width: 100%;
    	margin-left: 15%;
	}

	#resComfirm{
		width:80%;
		background-color:white;'
	}
	
	#cap {
	    color: black;
	    background: #F6AA93 none repeat scroll 0% 0%;
	    height: 75px;
	    padding-top: 2px;
	    font-size: 60px;
	    font-weight: bold;
	    border-radius: 5px 5px 0px 0px;
	}
	
	#firstTr{
		background-color: #ccc;
	    height: 50px; 
	    font-size: 50px;
	}
	
	.modal-title {
	    margin: 0;
	    line-height: 1.42857143;
	    text-align: center;
	    font-size: 45px;
	    font-weight: bold;
	}
	
	input[type="checkbox"]{
	width: 80%;
	}
	
	#sendButton {
		font-family: "微軟正黑體";
		width: 100%;
		background: #CC6666;
		border-radius: 5px;
		border: 0;
		cursor: pointer;
		color: white;
		font-size: 24px;
		padding-top: 10px;
		padding-bottom: 10px;
		transition: all 0.3s;
		margin-top: -4px;
		font-weight: 700;
		height:6%;
	}
	
	#sendButton:hover {
		background: #CC4949;
	}
	
	#modalText {
		width: 100%;
		height: 200px;
		border: 2px solid #CCC;
		resize: none;
	}
	
	#myModalLabel {
		font-family: "微軟正黑體";
		font-weight: bold;
	}
	
	.modal-body {
		height: 90%;
	}
	
	.modal-header {
	    min-height: 16.42857143px;
	    padding: 15px;
	    background-color: #F6AA93;
	    border-bottom: 1px solid #e5e5e5;
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
	position:absolute;
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

/* ********************************************************** */

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
				<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
					<li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/crs/crsEntrance.jsp"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp" ><span data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
<!-- ***************************************頭像登出********************************************** -->
				<%	
MemVO memVO = (MemVO) session.getAttribute("memVO");

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
	<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
<!---banner--->
		<div id="wrapper" style="margin-top: 8%;">
			<div class="sidebar">
				<div class="content">
					<div class="col-md-4 about-grid1 wow fadeInLeft animated animated" data-wow-delay="0.4s" id="content1">
						<div class="latest-top" >
							<h4><img src="<%=request.getContextPath()%>/front_end/images/face-24px.svg" id="mem">教練帳戶</h4>
							<div class="latest-class">
								<div class="latest-right">
									<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp"><h5 style="color:white ; font-family: 'test' ;" id="coachShow">教練檔案</h5></a>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
								
						<div class="latest-top">
							<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapsepoint" aria-expanded="true" aria-controls="collapsepoint">
								<h4><img src="<%=request.getContextPath()%>/front_end/images/payment-24px.svg" id="point">點數兌現</h4>
							</a>
							<div id="collapsepoint" class="collapse" aria-labelledby="headingpoint" data-parent="#accordionSidebar">
								<div class="bg-white py-2 collapse-inner rounded">
									<a class="collapse-item" href="">教練點數</a><br>
									<a class="collapse-item" href="">申請紀錄</a><br> 
								</div>							
							</div>	
						</div>
						
						<div class="latest-top">
							<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseActivity" aria-expanded="true" aria-controls="collapseActivity">
								<h4><img src="<%=request.getContextPath()%>/front_end/images/date_range-24px.svg" id="Activity">管理課程</h4>
							</a>
							<div id="collapseActivity" class="collapse" aria-labelledby="headingActivity" data-parent="#accordionSidebar">
								<div class="bg-white py-2 collapse-inner rounded">
									<a class="collapse-item" id="addCrs1" href="<%=request.getContextPath()%>/front_end/crs/addCrs.jsp">新增課程</a><br>
			 						<a class="collapse-item" id="resConfirmForm" href="<%=request.getContextPath()%>/front_end/res/resConfirm.jsp">預約管理</a><br> 
			 						<a class="collapse-item" id="resCalendar" href="<%=request.getContextPath()%>/front_end/res/CoaWeek.jsp">行事曆</a><br> 
								</div>
							</div>			
							<div class="clearfix"></div>
						</div>
								
						<div class="latest-top">
							<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseArticle"  aria-expanded="true" aria-controls="collapseArticle">
								<h4><img src="<%=request.getContextPath()%>/front_end/images/import_contacts-24px.svg" id="Article">文章管理</h4>
							</a>
							<div id="collapseArticle" class="collapse" aria-labelledby="headingArticle" data-parent="#accordionSidebar">
								<div class="bg-white py-2 collapse-inner rounded">
									<a class="collapse-item" href="">新增文章</a><br> 
								</div>			
							</div> 
						</div>
					</div>
				</div>
			</div>	
		</div>	

<!--課程列表 -->	
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
		<% CrsVO crsVO = (CrsVO) request.getAttribute("crsVO"); %>
		<div id="tableDiv" >
			<table id="resComfirm">
				<caption id="cap">課程列表</caption>
				<tr id ="firstTr">
					<th>課程名稱</th>
					<th>課程費用</th>
					<th>課程起始日</th>
					<th>課程結束日</th>
					<th>人數限制</th>
					<th> 修改</th>
				</tr>
				<c:forEach var="crsVO" items="${listt}">
				<tr id="secondTr">
					<td>${crsVO.crs_name}</td>
					<td>${crsVO.crs_fee}</td>
					<td>${crsVO.start_date}</td>
					<td>${crsVO.end_date}</td>
					<td>${crsVO.limit}</td>
					<td><input type="button" class="updateCrs" value="修改"></td>
				</c:forEach>
				</tr>
			</table>
			<div id="noRes" style="display: none">暫無預約</div>
		</div>
<!--課程列表結束 -->

<!--課程修改燈箱 -->	
	<jsp:useBean id="crsTypeSvc" scope="page" class="com.crs_type.model.CrsTypeService" />
	<form METHOD="post" ACTION="crs.do" name="form" enctype="multipart/form-data"  >	
		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="myModalLabel" style="font-family:"test";>課程修改:</h3>
					</div>
	
					<div class="modal-body">
						<div>
							<form METHOD="post" ACTION="crs.do" name="form1" enctype="multipart/form-data"  >		
								<div class="contentform">
									<div class="leftcontact">
										<%-- 錯誤表列 --%>
										<c:if test="${not empty errorMsgs}">
											<font style="color: red">請修正以下錯誤:</font>
											<ul>
												<c:forEach var="message" items="${errorMsgs}">
													<li style="color: red">${message}</li>
												</c:forEach>
											</ul>
										</c:if>
				
										<div class="form-group">
											<p>教練</p>
											<input  id="coaName" type="TEXT"  value="${(crsVO.coa_id == coaVO.coa_id)?'selected':'' }${coaVO.coa_name}" readonly/>
											<input type="hidden" name="coa_id" value="${coaVO.coa_id}">
										</div>
				
										<div class="form-group">
											<p>
												課程種類 <span>*</span>
											</p>
											<select size="1" name="crs_type_no">
												<c:forEach var="crsTypeVO" items="${crsTypeSvc.all}">
													<option value="${crsTypeVO.crs_type_no}"
														${(crsVO.crs_type_no == crsTypeVO.crs_type_no)?'selected':'' }>${crsTypeVO.crs_type_name}
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<p>
												課程名稱 <span>*</span>
											</p>
											<input type="TEXT" name="crs_name" size="45" value="" />
										</div>
										<div class="form-group">
											<p>
												課程價格 <span>*</span>
											</p>
											<input type="TEXT" name="crs_fee" size="45" value="" />
										</div>
										<table id="poanTable">
											<tr>
												<p> 每周上課日 <span>*</span> </p>
												<td>
													
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="0">
													<label for="styled-checkbox-1">一</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="1">
													<label for="styled-checkbox-1">二</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="2">
													<label for="styled-checkbox-1">三</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="3">
													<label for="styled-checkbox-1">四</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="4">
													<label for="styled-checkbox-1">五</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="5">
													<label for="styled-checkbox-1">六</label>
												</td>
												<td>
													<input type="checkbox" class="styled-checkbox" name="week_crs" value="6">
													<label for="styled-checkbox-1">日</label>
												</td>
											</tr>
										</table>
										<div class="form-group">
											<p>圖片上傳</p>
											<input name="file" id="file" type="file"onchange="loadFile(event)"><br>
											<img id="output0" width="300" height="200" />
										</div>
									</div>
								<div class="rightcontact">
				 						<div>
											<p>
												人數上限 <span>*</span>
											</p>
											<select class="limit" name="limit"></select>
										</div>
				
										<div>
											<p>
												開始日期 <span>*</span>
											</p>
											<input name="start_date" id="f_date1" type="text">
										</div>
				
										<div class="form-group">
											<p>
												結束日期 <span>*</span>
											</p>
											<input name="end_date" id="f_date2" type="text">
										</div>
										<div class="form-group">
											<p>
												上課時間 <span>*</span>
											</p>
											<input name="start_time" id="f_date3" type="text">
										</div>
				
										<div class="form-group">
											<p>
												下課時間 <span>*</span>
											</p>
											<input name="end_time" id="f_date4" type="text">
										</div>
				
										<div class="form-group">
											<p>
												課程內容 <span>*</span>
											</p>
											<textarea name="crs_content" ></textarea>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
	
					<div class="modal-footer">
						<input type="hidden" name="action" value="update">					
						<input id="sendButton" type="submit" value="送出">
					</div>
				</div>
			</div>
		</div>
	</form>
<!--課程修改燈箱結束 -->				

	<div class="clearfix"></div>
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
</body>

<%
	java.sql.Date start_date = null;
	try {
		start_date = crsVO.getStart_date();
	} catch (Exception e) {
		start_date = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Date end_date = null;
	try {
		end_date = crsVO.getStart_date();
	} catch (Exception e) {
		end_date = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Timestamp start_time = null;
	try {
		start_time = crsVO.getStart_time();
	} catch (Exception e) {
		start_time = new java.sql.Timestamp(System.currentTimeMillis());
	}

	java.sql.Timestamp end_time = null;
	try {
		end_time = crsVO.getEnd_time();
	} catch (Exception e) {
		end_time = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<!--dataTimePicker-->
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/front_end/datetimepicker/jquery.datetimepicker.css" />
	<script
		src="<%=request.getContextPath()%>/front_end/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/datetimepicker/jquery.datetimepicker.full.js"></script>
			

<style>
	.xdsoft_datetimepicker .xdsoft_datepicker {
		width: 300px; /* width:  300px; */
	}

	.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
		height: 151px; /* height:  151px; */
	}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
	   theme: 'dark',              //theme: 'dark',
	    timepicker:false,       //timepicker:true,
	    //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	    format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=start_date%>', // value:   new Date(),
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   minDate: '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
	   theme: 'dark',              //theme: 'dark',
	    timepicker:false,       //timepicker:true,
	    //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	    format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=end_date%>', // value:   new Date(),
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   minDate:'-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
	$.datetimepicker.setLocale('zh');
	$('#f_date3').datetimepicker({
	   theme: 'dark',              //theme: 'dark',
	   datepicker:false,
	   //timepicker:true,       //timepicker:true,
	    //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	    format:'H:i',         //format:'Y-m-d H:i:s',
		   value: '<%=start_time%>', // value:   new Date(),
	   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	   //startDate:	            '2017/07/10',  // 起始日
	   //minDate:               '-1970-01-01', // 去除今日(不含)之前
	   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	
	$.datetimepicker.setLocale('zh');
	$('#f_date4').datetimepicker({
	   theme: 'dark',              //theme: 'dark',
	   datepicker:false,
	    //timepicker:true,       //timepicker:true,
	    // step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	    format:'H:i',         //format:'Y-m-d H:i:s',
	   	 value: '<%=end_time%> ', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

</script>

<!--燈箱-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
	
	/*開啟修改燈箱*/
	$("#basicModal").modal({show: false}); 
	$(".updateCrs").click(function(){
		$("#basicModal").modal({show: true}); 		
	});
	
	/*迴圈跑人數上限select*/
	for(var i = 1; i <=30 ; i++){
		$(".limit").append('<option class="scrollbar" value="'+i+'">'+i+'</option>');
	}
	
	<!--更改resBlock每行的顏色-->
	$("#resComfirm tr:even").css("background-color", "white");
	$("#resComfirm tr:odd").css("background-color", "#ccc");
</script>

</html>