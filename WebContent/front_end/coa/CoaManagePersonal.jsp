<%@page import="com.crs.model.CrsService"%>
<%@page import="com.res.model.ResVO"%>
<%@page import="java.util.List"%>
<%@page import="com.res.model.ResService"%>
<%@page import="com.crs.model.CrsVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	ResService resSvc = new ResService();
	List<ResVO> list = resSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%
//     CoaVO CoaloginVO = (CoaVO) request.getAttribute("loginVO");
//     Object CoaAccount = session.getAttribute("loginVO");                  // 從 session內取出 (key) account的值
//     if (CoaAccount == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//       response.sendRedirect(request.getContextPath()+"/Index_Login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
//       return;
//     }
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
	background-color:white;
	overflow: hidden;
	float: left;
	margin-left: 20%;
}

p span {
	color: #F00;
}

p {
	margin: 0px;
	font-weight: 500;
	line-height: 2;
	color: #333;
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

/* ********************************************************* */
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
<!-- ***************************************登出********************************************** -->
<%	
MemVO memVO = (MemVO) session.getAttribute("memVO");
if(memVO!=null || coaVO!=null){
%><li><a class="nav-in"herf="#">
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
				<img src="<%=request.getContextPath()%>/mem/DBGifReaderMem?mem_id=${memVO.mem_id}"> 
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
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=<%=coaVO.getCoa_id()%>"> 
			</c:if>
			<c:if test="${coaVO.coa_pic==null}">
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
<!-- ******************************************************************************************* -->
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

<!-- ***************************************個人檔案************************************************ -->
	<form METHOD="post" name="form1" enctype="multipart/form-data"  id="coachProfil">
	<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />
		<table class="table table-hover table-dark" style="font-size:25px;font-weight: bold;">
			<tbody>
				<tr>
					<th scope="row">教練姓名</th>
					<td>${coaVO.coa_name}</td>
				</tr>
				<tr>
					<th scope="row">大頭照</th>
					<td><img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=<%=coaVO.getCoa_id()%>" style="width:300px;heigh:300px;" /></td>
				</tr>
				<tr>
					<th scope="row">教練專長</th>
					<td>${coaVO.expert}</td>
				</tr>
				<tr>
					<th scope="row">影片介紹</th>
					<td><iframe src="${coaVO.coa_video}"></iframe></td>
				</tr>
				<tr>
					<th scope="row">健身職照</th>
					<td><img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach?coa_id=<%=coaVO.getCoa_id()%>" /></td>
				</tr>
				<tr>
					
					<th scope="row">自我介紹</th>
					<td>${coaVO.coa_intro}</td>
				</tr>
			</tbody>
			
		</table>
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonalUpdate.jsp"  style="font-size:25px;font-weight: bold; color:black">修改新增</a>
	</form>

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
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>

<script>
	var loadFile = function(event){""
		var output0 = document.getElementById('output0');
		output0.src = URL.createObjectURL(event.target.files[0]);
		};
			
		for(var i = 1; i <=30 ; i++){
			$(".limit").append('<option class="scrollbar" value="'+i+'">'+i+'</option>');
		}
</script>		
	
</html>