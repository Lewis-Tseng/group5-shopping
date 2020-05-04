<%@page import="com.res.model.ResVO"%>
<%@page import="com.res.model.ResService"%>
<%@page import="java.util.List"%>
<%@page import="com.crs.model.CrsVO"%>
<%@page import="com.crs.model.CrsService"%>
<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.crs_detail.model.CrsDetailVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.crs_detail.model.CrsDetailService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
//     MemVO loginVO = (MemVO) request.getAttribute("loginVO");
//     Object account = session.getAttribute("loginVO");                  // 從 session內取出 (key) account的值
//     if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//       response.sendRedirect(request.getContextPath()+"/MemLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
//       return;
//     }
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>一周課表</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
<!---css--->
<link
	href="<%=request.getContextPath()%>/front_end/login/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/login/css/style.css"
	rel='stylesheet' type='text/css' />
<!---js--->
<link href="<%=request.getContextPath()%>/front_end/css/animate.css"
	rel="stylesheet" type="text/css" media="all">
<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
<!-- ------------------------------------------------------------------------- -->
<!-- 安裝日曆的JS&CSS -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/js/moment.min.js"></script>
<!-- FullCalendar v3.8.1 -->
<link
	href="<%=request.getContextPath()%>/front_end/css/fullcalendar.min.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/front_end/css/fullcalendar.print.css"
	rel="stylesheet" media="print">
</script>
<script
	src="<%=request.getContextPath()%>/front_end/js/fullcalendar.min.js"></script>
<!-- ------------------------------------------------------------------------- -->
<style type="text/css">
@font-face {
	font-family: 'test';
	src:
		url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
}
/* 連結文字  */
a {
  color: #f5f5f5;
  text-decoration: none;
}
/* 背景圖 */
body {
	background:url("<%=request.getContextPath()%>/front_end/images/bgG5.png");
	background-size:cover;
	background-repeat:no-repeat;
	background-attachment:fixed;
	background-position:center;
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
	margin-top:5%;
}

#aaa {
	width: 60%;
	display: inline-block;
	margin-left: 10%;
	color:#ff1010;
	font-size: 24px;
}
#Lession{
margin-left: 38%;
}
.fc-unthemed .fc-content, .fc-unthemed .fc-divider, .fc-unthemed .fc-list-heading td,
	.fc-unthemed .fc-list-view, .fc-unthemed .fc-popover, .fc-unthemed .fc-row,
	.fc-unthemed tbody, .fc-unthemed td, .fc-unthemed th, .fc-unthemed thead
	{
	border-color: #ccc;
}
.fc-unthemed .fc-popover {
	background-color: #f5c600;
	border-width: 2px;
	border-style: solid;
}
.fc-event, .fc-event-dot {
	background-color: #68afffa6;
}
.fc-day-number{
	color:black;
	font-weight:1000;
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
/* ***********************頭像*********************************** */
</style>
<script>
new WOW().init();
</script>
</head>
<>
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
		<h1> <a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img  src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
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

	<!-- 側邊攔開始-->
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
	<!-- 側邊欄結束 END -->
<div>

 
</div>

<script></script>
 
<div id="aaa">
		<div id="calendar" name="calendar"></div>
</div>
<!-- calender END -->
<!--copy-->
<div class="footer-section">
	<div class="copy-section wow fadeInDownBig animated animated"
		data-wow-delay="0.4s">
		<div class="container">
			<div class="social-icons">
				<a  href="https://www.facebook.com/"><i class="icon"></i></a> <a
					href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a> <a
					href="https://www.google.com.tw/webhp?tab=rw"><i class="icon2"></i></a>
				<a  href="https://www.instagram.com/?hl=zh-tw"><i class="icon3"></i></a>
			</div>
			<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期 
			<a href="#https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a>
				- Collect from <a href="#https://www.tibame.com/" title="資策會"
					target="_blank">中壢資策會第71期出來必是精品</a>
			</p>
		</div>
	</div>
</div>
</>
<jsp:useBean id="crsDetailSvc" scope="page" class="com.crs_detail.model.CrsDetailService" />
<jsp:useBean id="crsSvc" scope="page" class="com.crs.model.CrsService" />
<script>
	/*$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});                    不*/
</script>
<script type="text/javascript">
$( "#calendar" ).fullCalendar({
// 參數設定[註1]
header: { // 頂部排版
left: "prev,next today", // 左邊放置上一頁、下一頁和今天
center: "title", // 中間放置標題
right: "month,basicWeek,basicDay" // 右邊放置月、周、天
},
defaultDate: "2019-11-12", // 起始日期
weekends: true, // 顯示星期六跟星期日
editable: true,  // 啟動拖曳調整日期
events: [
	<%CrsDetailService DetailSvc=new CrsDetailService();
		List<CrsDetailVO> CrsDetailList=DetailSvc.findByCrsNOAll(memVO.getMem_id());
		int i=0;
		String color[]= {"#009FCC","#FFA488","#00AA88","#FFFF33","#BBFFEE","#FF5511","#9999FF","#FFA488","#BB5500"};
		outer:
			while(i<CrsDetailList.size()){
			CrsService crsSvc1=new CrsService();
			List<CrsVO> crslist=crsSvc1.getAll();
			for(int j=0;j<crslist.size();j++){
				if(CrsDetailList.get(i).getCrs_no().equals(crslist.get(j).getCrs_no())){
		%>
				{
					title:'<%=crslist.get(j).getCrs_name()%>',
					start:'<%=crslist.get(j).getStart_date()%>',
					end:  '<%=crslist.get(j).getEnd_date()%>',		
					backgroundColor: '<%=color[i]%>'
				},
					<%j=0;i++;continue outer;}
				}
		}%>
		
		
				<%
					ResService resSvc= new ResService();	
					List<ResVO> resList = resSvc.getResDayMem(memVO.getMem_id());
				%>	
				
				<%for(int r =0; r<resList.size(); r++) {	%>
					<%if(resList.get(r).getRes_status().equals("1")){%>
					{
						title:'預約教練',
						start:'<%= resList.get(r).getRes_day()%>',
						backgroundColor:'<%=color[r]%>'
					},
					<%}%>
				<%}%>
			  
]

// { // 事件(包含開始時間)
// 	<c:forEach var="crsVO" items="${crsSvc.all}">	 
// 	<c:forEach var="crsDetailVO" items="${crsDetailSvc.all}" begin="2" end="2">	
// 	<c:if test="${memVO.mem_id ==crsDetailVO.mem_id }">
// 		<c:if test="${crsDetailVO.crs_no == crsVO.crs_no}">
// 			title:'${crsDetailVO.crs_name}',
// 			start: '${crsVO.start_date}',
// 			end: '${crsVO.end_time}'
// 		</c:if>	
// 	</c:if>
// 	</c:forEach>
// 	</c:forEach>
// },
// { // 事件(包含跨日開始時間、結束時間)

// <c:forEach var="crsVO" items="${crsSvc.all}">	 
// <c:forEach var="crsDetailVO" items="${crsDetailSvc.all}" begin="3" end="3">	
// <c:if test="${memVO.mem_id ==crsDetailVO.mem_id }">
// 	<c:if test="${crsDetailVO.crs_no == crsVO.crs_no}">
// 		title:'${crsDetailVO.crs_name}',
// 		start: '${crsVO.start_date}',
// 		end: '${crsVO.end_date}'
// 	</c:if>
// </c:if>
// </c:forEach>
// </c:forEach>
// },
// { // 事件(包含開始時間、結束時間)
// 	<c:forEach var="crsVO" items="${crsSvc.all}">	 
// 	<c:forEach var="crsDetailVO" items="${crsDetailSvc.all}" begin="4" end="4">	
// 	<c:if test="${memVO.mem_id ==crsDetailVO.mem_id }">
// 		<c:if test="${crsDetailVO.crs_no == crsVO.crs_no}">
// 			title:'${crsDetailVO.crs_name}',
// 			start: '${crsVO.start_date}',
// 			end: '${crsVO.end_date}'
// 		</c:if>	
// 	</c:if>			
// 	</c:forEach>
// 	</c:forEach>
// },
// { // 事件(設定連結)
// 	<c:forEach var="crsVO" items="${crsSvc.all}">	
// 	<c:forEach var="crsDetailVO" items="${crsDetailSvc.all}" begin="5" end="5">	
// 	<c:if test="${memVO.mem_id ==crsDetailVO.mem_id }">
// 		<c:if test="${crsDetailVO.crs_no == crsVO.crs_no}">
// 			title:'${crsDetailVO.crs_name}',
// 			start: '${crsVO.start_date}',
// 			end: '${crsVO.end_date}'
// 			</c:if>	
// 	</c:if>			
// 	</c:forEach>
// 	</c:forEach>
// }

});

	
</script>
</html>





