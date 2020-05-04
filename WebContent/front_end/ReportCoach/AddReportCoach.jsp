<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.reportcoa.model.ReportCoaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ReportCoaVO reportcoaVO = (ReportCoaVO) request.getAttribute("reportcoaVO");
%>

<!DOCTYPE HTML>
<html>
<head>
<title>個人帳號管理</title>
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
<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
<!--css-->
<style type="text/css">
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

form>b {
	font-size: 20px;
}
a {
  color: #f5f5f5;
  text-decoration: none;
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

#cccc {
	font-size: 25px;
	box-sizing: border-box;
	text-align: center;
	width: 53%;
    height: 60%;
    margin-left: 35%;
    margin-right: 0%;
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
<link href="<%=request.getContextPath()%>/front_end/css/animate.css"
	rel="stylesheet" type="text/css" media="all">
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
			<div class="navbar-header wow fadeInLeft animated animated"
				data-wow-delay="0.4s">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
	<h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img  src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
			</div>
			<div id="navbar" class="navbar-collapse collapse" style="margin-right: -5%;">
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

	<!---banner--->
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
	<!-- Sidebar END -->

		<!-- Begin Page Content -->
		<div class="container-fluid">
			<jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />
			<div>
			<table id="cccc">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<tr><th style="color:#c3585e">檢舉功能：檢舉教練</th></tr>
				<tr><th style="color:#c3585e">教練名字:</th></tr>
				<tr><th>
				<select size="1" name="coaid" id="select">
						<c:forEach var="coaVO" items="${coaSvc.all}">
							<option value="${coaVO.coa_id}"
								${(coaVO.coa_id==coaVO.coa_id)? 'selected':'' }>${coaVO.coa_name}
						</c:forEach>
				</select>
				</th> 
				</tr> 
				<tr style="color:#c3585e"><th>輸入檢舉內容</h3></tr>
				<tr>
				<th>
				<textarea id="content" name="content" value="<%=(reportcoaVO == null) ? "" : reportcoaVO.getRep_cot()%>" style="width: 750px; height: 350px;"></textarea>
				<br>
				<button id="Insert">送出新增</button>
				</th>
				</tr> 
				
			</table>
		</div>
	</div>
	

			<!--copy-->
			<div class="footer-section">
				<div class="copy-section wow fadeInDownBig animated animated"
					data-wow-delay="0.4s">
					<div class="container">
						<div class="social-icons">
							<a href="https://www.facebook.com/"><i class="icon"></i></a> <a
								href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a>
							<a href="https://www.google.com.tw/webhp?tab=rw"><i
								class="icon2"></i></a> <a href="https://www.instagram.com/?hl=zh-tw"><i
								class="icon3"></i></a>
						</div>
						<p>
							Copyright &copy; 2019.JAVA雲端服務技術養成班第71期 <a
								href="#https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a>
							- Collect from <a href="#https://www.tibame.com/" title="資策會"
								target="_blank">中壢資策會第71期出來必是精品</a>
						</p>
					</div>
				</div>
			</div>
		
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script type="text/javascript">

$('#Insert').click(function(){
	$.ajax({
		url:'reportcoa.do',
		type:'Post',
		data:{
			action:'insert',
		 	coaid:$("#select").val(),
			content:$("#content").val()
		},
		 success: function (){
			 ReportEvent();
			 $("#content").val('');
		 },error:function(){
			 alert('fail');
		 }
	})
	
});

function ReportEvent(content){
	var content =Swal.fire({
		  icon: 'error',
		  title: '檢舉',
		  text: '確定要檢舉嗎?'
		 
		})
	return content;
}


</script>
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
</html>