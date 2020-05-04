<%@page import="java.util.List"%>
<%@page import="com.friendList.model.*"%>
<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="java.util.Set"%>
<%@page import="com.gro.pic.pictureService"%>
<%@page import="com.gro.pic.pictureVO"%>
<%@page import="com.gro.joingroup.joingroupService"%>
<%@page import="com.gro.report.reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gro.group.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pictureVO pictureVO = (pictureVO) request.getAttribute("pictureVO");
	
	joingroupService joingroupSvc = new joingroupService();
	groupVO groupVO = (groupVO) request.getAttribute("groupVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	String mem_id = (String)request.getAttribute("mem_id");
	
	String gro_id = groupVO.getGro_id();
	
	pictureService pictureSvc = new pictureService();
	Set<pictureVO> set = pictureSvc.getAllPics(gro_id);
    pageContext.setAttribute("pic",set);
    
    String loginMem=null;
	if(session.getAttribute("memVO")!=null){
		loginMem = memVO.getMem_name();
	};
	
	FriendListService FriendListSvc2 = new FriendListService();
	List<FriendListVO> list2 = FriendListSvc2.getMyAll(memVO.getMem_id());
	pageContext.setAttribute("list2",list2);

%>

<jsp:useBean id="groclassSvc" scope="page" class="com.gro.type.groclassService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />


<html>
<head>
<title>Just強身</title>
<!---css--->
<link href="<%=request.getContextPath()%>/front_end/css_group/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/css_group/css/style.css" rel='stylesheet' type='text/css' />
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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/oneGroupInfo.css">
	<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css'>
	<script src="<%=request.getContextPath()%>/front_end/css_group/js/wow.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/dialogs.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/star.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/groPic.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/memTable.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/dudu/css/chat_new2_styleJI.css">
	
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
		.about-bottom{
			background:url(images/rex.png);
			background-size: cover;
			border-radius: 30px;
		}
		.about-right{
			float:none;
			width: 100%;
		}
		.about-right>h1{
			font-family: 'test';
			color: rgb(255,255,255); 
			width: 100%;

		}
		.about-right img{
			border-radius: 30px;
			width: 30%;
			margin: 30px 30px 30px 23px;
		}

		.about-groupName{
			width: 60%;
			height: 50%;
			float: left;
		}

		.about-groupName h1{
			width: 100%;
			margin:25% auto;
			font-family: 'test';
			font-size: 60px;
			color: rgb(230,230,230);
			text-align: center;
		}
		.about-right p {
			background-color: rgba(180,180,180,0.6);
			float: left;
			width: 50%;
			height: 20%;
			padding: .5em;
			overflow: hidden;
			font-size: 1.2em;
			color: rgb(0,0,0);
			border-radius: 15px;
		}
		.about-bottom>.clearfix{
			padding-bottom: 50px;
		}

		.joinBtn{
			font-family: 'test';
			position: relative;
    		display: block;
    		padding: 19px 39px 18px 39px;
    		color: #FFF;
    		margin: 0 10%;
    		background: rgb(94,138,188);
    		font-size: 18px;
    		text-align: center;
    		font-style: normal;
    		width: 80%;
    		border: 1px solid rgb(94,138,188);
    		border-width: 1px 1px 3px;
    		margin-bottom: 10px;
    		border-radius: 15px;
		}

		.about-group-p{
			width: 20%;

		}

		.groupinfo{
			float: left;
		}
		
		.banner {
			padding-bottom: 0;
		}
		
		.resume {
			margin-top:0;
			box-shadow: 0 0 20px -3px rgba(25,25,25,0.7);
		}
		
		.resume .base .profile .info .name {
			color:rgb(255,255,255);
			font-family:'test';
		}
		
		.resume .base .profile .info {
			font-family:'test';
		}
		
		.resume .func h3 {
			margin: 10px 0 20px 0;
		}
		
		body {
			font-family:'微軟正黑體';
		}
		
		.button button {
			font-family:'test';
			color:rgba(40,40,40,0.9);
			border-radius:0px 10px 3px 0px;
			top:220px;
		}
		
		
		input[type="submit"],input[type="button"]{
			position: absolute;
    		-webkit-transform: translate(-50%, -50%);
    		-moz-transform: translate(-50%, -50%);
    		-ms-transform: translate(-50%, -50%);
    		-o-transform: translate(-50%, -50%);
    		transform: translate(-50%, -50%);
    		font-family: 'test';
    		background-color: rgba(238,238,238,0.8);
    		border: 0;
    		color: rgba(40,40,40,0.9);
    		text-transform: uppercase;
    		font-size: 28px;
    		letter-spacing: 1px;
    		width: 80px;
    		height: 60px;
    		overflow: hidden;
    		outline: 0;
    		-webkit-transition: all 0.4s;
    		-moz-transition: all 0.4s;
    		-o-transition: all 0.4s;
    		transition: all 0.4s;
    		visibility: visible;
    		opacity: 1;
   			font-weight: bold;
    		box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.6);
    		top: 60px;
    		right: -120px;
    		z-index: -1;
    		border-radius: 0px 10px 3px 0px;
		}
		
		input[type="submit"]:hover,input[type="button"]:hover{
			cursor: pointer;
  			background-color: rgba(229,215,121,1);
  			color: #ffffff;
		}
		
		.quitBtn button {
			font-family:'test';
			color:rgba(40,40,40,0.9);
			border-radius:0px 10px 3px 0px;
			position: absolute;
  			-webkit-transform: translate(-50%, -50%);
  			-moz-transform: translate(-50%, -50%);
  			-ms-transform: translate(-50%, -50%);
  			-o-transform: translate(-50%, -50%);
  			transform: translate(-50%, -50%);
  			background-color: rgba(238,238,238,0.8); 
  			border: 0;
  			text-transform: uppercase;
  			font-size: 28px;
  			letter-spacing: 1px;
  			width: 80px;
  			height:60px;
  			overflow: hidden;
  			outline: 0;
  			-webkit-transition: all 0.4s;
  			-moz-transition: all 0.4s;
  			-o-transition: all 0.4s;
  			transition: all 0.4s;
  			visibility: visible;
  			opacity: 1;
  			font-weight: bold;
  			box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.6);
  			top:220px;
  			right:-120px;
  			z-index:-1;
		}
		
		.quitBtn button:hover{
			cursor: pointer;
  			background-color: rgba(255,86,95,0.9);
  			color: #ffffff;
		}
		
		.star button {
			font-family:'test';
			color:rgba(40,40,40,0.9);
			border-radius:0px 10px 3px 0px;
			position: absolute;
  			-webkit-transform: translate(-50%, -50%);
  			-moz-transform: translate(-50%, -50%);
  			-ms-transform: translate(-50%, -50%);
  			-o-transform: translate(-50%, -50%);
  			transform: translate(-50%, -50%);
  			background-color: rgba(238,238,238,0.8); 
  			border: 0;
  			text-transform: uppercase;
  			font-size: 28px;
  			letter-spacing: 1px;
  			width: 80px;
  			height:60px;
  			overflow: hidden;
  			outline: 0;
  			-webkit-transition: all 0.4s;
  			-moz-transition: all 0.4s;
  			-o-transition: all 0.4s;
  			transition: all 0.4s;
  			visibility: visible;
  			opacity: 1;
  			font-weight: bold;
  			box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.6);
  			top:60px;
  			right:-120px;
  			z-index:-1;
		}
		
		.uploadPic button {
			font-family:'test';
			color:rgba(40,40,40,0.9);
			border-radius:0px 10px 3px 0px;
			position: absolute;
  			-webkit-transform: translate(-50%, -50%);
  			-moz-transform: translate(-50%, -50%);
  			-ms-transform: translate(-50%, -50%);
  			-o-transform: translate(-50%, -50%);
  			transform: translate(-50%, -50%);
  			background-color: rgba(238,238,238,0.8); 
  			border: 0;
  			text-transform: uppercase;
  			font-size: 28px;
  			letter-spacing: 1px;
  			width: 80px;
  			height:60px;
  			overflow: hidden;
  			outline: 0;
  			-webkit-transition: all 0.4s;
  			-moz-transition: all 0.4s;
  			-o-transition: all 0.4s;
  			transition: all 0.4s;
  			visibility: visible;
  			opacity: 1;
  			font-weight: bold;
  			box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.6);
  			top:140px;
  			right:-120px;
  			z-index:-1;
		}
		
		
		/* 		=========================================================================================== */
		.memListBtn button {
			font-family:'test';
			color:rgba(40,40,40,0.9);
			border-radius:50px;
			position: absolute;
  			-webkit-transform: translate(-50%, -50%);
  			-moz-transform: translate(-50%, -50%);
  			-ms-transform: translate(-50%, -50%);
  			-o-transform: translate(-50%, -50%);
  			transform: translate(-50%, -50%);
  			background-color: rgba(238,238,238,0.8); 
  			border: 0;
  			text-transform: uppercase;
  			font-size: 20px;
  			letter-spacing: 1px;
  			width: 120px;
  			height:40px;
  			overflow: hidden;
  			outline: 0;
  			-webkit-transition: all 0.4s;
  			-moz-transition: all 0.4s;
  			-o-transition: all 0.4s;
  			transition: all 0.4s;
  			visibility: visible;
  			opacity: 1;
  			font-weight: bold;
  			box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.6);
  			bottom: 236;
  			right:275;
  			z-index:1;
		}
		
		.memListBtn button:hover {
			cursor: pointer;
  			background-color: rgba(40,40,40,0.9);
  			color: #ffffff;
		}
/* 		=========================================================================================== */
		
		.pop2 .content .title h1 {
			color: rgb(137,189,233);
			font-family:'test';
		}
		
		.pop2 .content .subscribe form input[type=button] {
			background-color: rgb(137,189,233);
   			border: 1px solid rgb(137,189,233);
		}
		
		.pop2 .content .subscribe form input[type=button]:hover {
			background-color: rgb(101,151,191);
   			border: 1px solid rgb(101,151,191);
		}
		
		.pop2 .content .close:hover {
  			color: rgb(146,206,255);
		}
		
		.pop3 .content .close {
  			color: rgba(25,25,25,0.7);
		}
		
		.pop3 .content .close:hover {
  			color: rgba(25,25,25,0.2);
		}
		
		.pop4 .content .close {
  			color: rgba(25,25,25,0.7);
		}
		
		.pop4 .content .close:hover {
  			color: rgba(129,214,131,0.9);
		}
		
		.pop4 .content .subscribe form input[type=submit] {
			top: auto;
    		right: 203;
    		bottom: 0;
    		box-shadow: none;
    		border-radius: 0;
    		border-radius: 30px;
    		z-index: 10;
    		background-color: rgba(112,195,114,0.9);
    		border: 1px solid rgba(112,195,114,0.9);
		}
		
		.pop4 .content .subscribe form input[type=submit]:hover {
			background-color: rgb(84,173,86);
   			border: 1px solid rgb(84,173,86);
		}
		
 		.pop5 .content .subscribe form input[type=submit] {
     		background-color: rgba(255,86,95,0.9);
     		border: 1px solid rgba(255,86,95,0.9);
 		}
 		.pop5 .content .subscribe form input[type=submit]:hover {
 			background-color: rgba(196,72,80,0.9);
    		border: 1px solid rgba(196,72,80,0.9)
 		}
 		
 		.pop5 .content .close:hover {
  			color: rgba(229,215,121,0.9);
		}
		
		.button button:hover {
			cursor: pointer;
  			background-color: rgba(229,215,121,1);
  			color: #ffffff;
		}
		.star button:hover {
			cursor: pointer;
  			background-color: rgba(119,171,214,0.9);
  			color: #ffffff;
		}
		.uploadPic button:hover {
			cursor: pointer;
  			background-color: rgba(129,214,131,0.9);
  			color: #ffffff;
		}
		
		body {
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/bgG5.png");
			background-size:cover;
			background-repeat:no-repeat;
			background-attachment:fixed;
			background-position:center;
		}
		
		.base>img {
			position: absolute;
			width: 270;
    		bottom: 6;
    		left: 0;
		}
		
		.func>img {
			width: 350;
   		 	position: absolute;
    		right: 0;
    		top: 0;
		}
		
		.about {
    		padding: 1em;
    		border-radius: 15px;
    		transition: 0.3s;
		}
		
		.about:hover {
			background-color: rgba(255,255,255,0.7);
			
		}
		
		.func>.jbg{
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/thinkPic.png");
			background-size:cover;
			position: absolute;
			width: 150;
    		height: 100;
    		top: 50;
    		right: 180;
		}
		
		.func>.jbg:hover {
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/thinkPicHover.png");
			background-size:cover;
		}
		
		.carousel>.slide>img {
    			width: 100%;
    			height: 100%;
    			margin: 0 auto;
		}
		
		.custom-file-upload {
			position: relative;
			display: block;
			padding: 10px;
			color: rgb(40,40,40);
			margin: 0 auto;
			background: rgba(230,230,230,0.8);
			font-size: 18px;
			text-align: center;
			font-style: normal;
			width: 40px;
			border-radius:30px;
			margin-top: 10px;
			position: absolute;
   			left: 12%;
    		bottom: 22%;
    		box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.6);
    		transition: 0.3s;
		}
		
		.custom-file-upload:hover {
			background: rgba(0,0,0,0.6);
			color:rgb(230,230,230);
		}
		
		.GroAstar {
			position: absolute;
    		background: url(<%=request.getContextPath()%>/front_end/css_group/images/astar.png);
    		background-size: cover;
    		width: 80;
    		height: 85;
    		right: 10;
    		top: 175;
		}
		/* 		========================================================================== */
		
		.addFriend{
			font-family:'test';
			width: 50%;
			height: 30;
			background-color: rgba(255,255,255,0.9);
			border-color: rgba(0,0,0,0);
			border-radius: 30px;
			transition:0.5s;
		}
		
		.addFriend:hover {
			background-color: rgba(40,40,40,0.95);
			color:rgb(230,230,230);
		}
		
		tr:hover {
			background-color:rgba(255,255,255,0.1);
		}
		
		.createMem:hover {
			background-color:rgba(134,214,613,0.5);
		}
		
		.pop6 .content .close:hover {
			color:rgba(134,214,613,0.9);
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

.inviteBox ,.friendAlert{
	position:fixed;
	right:15;
	bottom:180;
	width:280px;
	height:150px;
	background:rgba(40,40,40,0.9);
	box-shadow: 5px 5px 5px rgba(40,40,40,0.5);
	border-radius: 20px 20px 0 20px;
	text-align:center;
	transition:0.5s;
	opacity:0;
	display:none;
	
}
.inviteBox>h3 ,.friendAlert>h3{
	color:white;
	font-family: 'test';
	margin: 30 auto;
}

.inviteBox>button{
	color: black;
    width: 40%;
    height: 20%;
    background-color: rgba(255,255,255,0.9);
    border-radius: 10px;
    font-family: 'test';
}
.acceptBtn{
	border-color: rgba(127,206,91,0.9);
	margin-right:5;
}
.refuseBtn{
	border-color: rgba(206,100,91,0.9);
	margin-left:5;
}
.friendAlert>h3{
	margin: 60 auto;
}
	</style>

</head>
<body  onload="connectChat();connectFriend()" onunload="disconnectChat();disconnectFriend();">


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
						<input type="submit" value="" style="position: absolute;background-color: rgba(0,0,0,0); border-color: rgb(0,0,0,0);z-index: 2;width: 100%;height: 100%;top: 30;right: -20;box-shadow: none;">  
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
					<h2 style="color: rgb(70,70,70);font-family: test,微軟正黑體;width:500px;margin:0 auto;margin-left: 45%;">揪團資訊</h2>
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
					<div class="resume">
						<div class="base">
							<div class="profile">
							<c:if test="${groupVO.gro_astar!=-1}">
								<div class="GroAstar"><h3 style="position: absolute; top: 15; left: 18;color: white;">${groupVO.gro_astar}</h3></div>
							</c:if>
								<div class="photo">
									<c:if test="${groupVO.gro_logo_pic != null}">
										<img
											src="<%=request.getContextPath()%>/DBGifReaderGro?gro_id=${groupVO.gro_id}">
									</c:if>
									<c:if test="${groupVO.gro_logo_pic == null}">
										<img
											src="<%=request.getContextPath()%>/front_end/css_group/images/G5_LOGO_v2.0.png">

									</c:if>
								</div>
								<div class="info">
									<h4 class="name">${groupVO.gro_name}</h4>
									<small class="job">建團人/${memberSvc.getOneMem(groupVO.mem_id).mem_name}</small>
								</div>
							</div>
							
							<img src="<%=request.getContextPath()%>/front_end/css_group/images/cute.png">

						</div>
						<div class="func">
							<img src="<%=request.getContextPath()%>/front_end/css_group/images/cute2.png">
<!--================================================================================================================================================================================= -->
							<div class="jbg" style="padding-bottom: 0;">
								<div class="button">
									<button class="btnPic" style="top: 50px;
   																width: 100%;
   																height: 100%;
   																right: -75;
   																z-index: 5;
   																background-color:rgba(0,0,0,0);
   																border-radius:0px;
   																box-shadow:0px 6px 30px rgba(0,0,0,0);">
									</button>
								</div>
							</div>
<!--================================================================================================================================================================================= -->

							<div class="work">
								<h3>
									<i class="fa fa-briefcase"></i>揪團資訊
								</h3>

								<ul>
									<li>
										<div class="about" style="width: 25%;">
											<h3 style="margin-bottom: 10;">揪團類別</h3>${groclassSvc.getOneClass(groupVO.gro_cid).gro_cname}
										</div>
									</li>
									<li>
										<div class="about">
											<h3 style="margin-top: 0; margin-bottom: 10">揪團介紹</h3>${groupVO.gro_info}
										</div>
									</li>

								</ul>
							</div>

							<div class="skills-prog">
								<h3>
									<i class="fa fa-th-list"></i>揪團時程
								</h3>
								<ul>
									<li data-percent="92"><span>報名開始</span>
										${groupVO.gro_sig_stime}</li>
									<li data-percent="96"><span>報名結束</span>
										${groupVO.gro_sig_ftime}</li>
									<li data-percent="40"><span>活動開始</span>
										${groupVO.gro_stime}</li>
									<li data-percent="60"><span>活動結束</span>
										${groupVO.gro_ftime}</li>
								</ul>
							</div>
							<div class="skills-soft">
								<h3>
									<i class="fa fa-graduation-cap"></i>揪團人數
								</h3>
								<ul>
									<li data-percent="0">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
        								</svg>
        								<span>最少人數<br>${groupVO.gro_mnum_min}人</span>
									</li>
									<% 
									int mnum = Integer.valueOf(groupVO.getGro_mnum());
									int mnumMax = Integer.valueOf(groupVO.getGro_mnum_max());
									int mnumPercent = mnum*100/mnumMax;
									%>
									<li data-percent="<%=mnumPercent%>">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
         								 </svg>
         								 <span>目前人數/${groupVO.gro_mnum}人</span><small></small>
         							</li>
									<li data-percent="100">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
         								</svg><span>最多人數<br>${groupVO.gro_mnum_max}人</span>
         							</li>
								</ul>

							</div>
							<div class="follow" style="float:right;">
									
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->									
									<FORM METHOD="post" ACTION="group.do">
										<input type="hidden" name="gro_id" value="${groupVO.gro_id}">
										<input type="hidden" name="action" value="getOne_For_Update"> 
										<input type="hidden" name="BF_url" value="/front_end"> 
										<input type="submit" value="修改">
									</FORM>
									
									<div class="uploadPic">
										<button class="btn3">
											<span>新圖</span>
										</button>
									</div>
									
									<div class="quitBtn">
										<button class="btn4">
											<span>刪除</span>
										</button>
									</div>
									
									
									<div class="memListBtn">
										<button class="btn5">
											<span>查看成員</span>
										</button>
									</div>
																		
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
									<div class="pop-up pop3" style="font-family:'test';background-color: rgba(255,255,255,0.8);border-radius: 20px;">
										<div class="content">
											<div class="container" style="padding:20px 0 80px 0;">
												
												<span class="close">X</span>

												<div class="title">
													<h1 style="margin-bottom:10px;font-family:'test';color:rgba(25,25,25,0.9);">照片牆</h1>
												</div>
											<!-- --------------------------------------------------- -->
											<div class="carousel">

												<c:forEach var="pictureVO" items="${pic}">
													<input type="checkbox" class="faux-ui-facia">
													<div class="slide">
														<img
															src="<%=request.getContextPath()%>/DBGifReaderGro?gro_pid=${pictureVO.gro_pid}">
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
<!----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ----------------------------  -->
									<div class="pop-up pop4" style="font-family:'test';background-color: rgba(255,255,255,0.8);border-radius: 20px;">
										<div class="content">
											<div class="container" style="padding:20px 0 100px 0;">
												
												<span class="close">X</span>

												<div class="title">
													<h1 style="margin-bottom:10px;color:rgba(129,214,131,0.9);font-family:'test';">Photo</h1>
												</div>
											<!-- ----------------------上傳預覽----------------------- -->
											<div class="carousel">

												<div class="slide">
			 										<img id="preview_1" src="<%=request.getContextPath()%>/front_end/css_group/images/G5_LOGO_v2.0.png" />
												</div>
											</div>
											<!-- --------------------------------------------------- -->
												<div class="subscribe">
													<form method="post" action="<%=request.getContextPath()%>/front_end/group/picturegroup.do" enctype="multipart/form-data">
														<label class="custom-file-upload">
    														<input style="display: none;" type="file" name="gro_pic" onchange="readURL(this)" targetID="preview_1" value="<%= (groupVO==null)? "" : groupVO.getGro_logo_pic()%>"/>
   															上傳圖片
														</label><br>
														<input type="hidden" name="gro_id" value="${groupVO.gro_id}">
														<input type="hidden" name="mem_id" value="<%=mem_id%>">
														<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
														<input type="hidden" name="action" value="insert">
														<input type="hidden" name="BF_url" value="/front_end">
														<input class="pic_btn" name="picbtn" type="hidden" value="新增照片">
													</form>
												</div>
											</div>
										</div>
									</div>
<!-------------------------------------------------------------------------------------------------------------------------------------------------------- -->
									<div class="pop-up pop5" style="font-family:'test';background-color: rgba(25,25,25,0.95);">
										<div class="content">
											<div class="container">
												
												<span class="close">close</span>

												<div class="title">
													<h1 style="font-family:'test';color:rgba(255,86,95,0.9);">刪除揪團紀錄</h1>
												</div>

												<img src="<%=request.getContextPath()%>/front_end/css_group/images/repAlert.png" alt="Car">

												<div class="subscribe">
													<h1 style="font-family:'test';">
														確定要<span style="color:rgba(255,86,95,0.9);">刪除</span>嗎?
													</h1>

													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/group.do">
														<input type="hidden" class="quit_btn" value="確認刪除" style="top: auto;
    																											   right: 203;
    																											   bottom: -10;
    																											   box-shadow: none;
    																											   border-radius: 30px;
    																											   z-index: 10;"> 
														<input type="hidden" name="gro_id" value="${groupVO.gro_id}">
														<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
														<input type="hidden" name="BF_url" value="/front_end">
														<input type="hidden" name="action" value="delete_Create">
													</FORM>
												</div>
											</div>
										</div>
									</div>
<!----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ----------------------------  -->
<!-------------------------------------------------------------------------------------------------------------------------------------------------------- -->
									<jsp:useBean id="friendSvc" scope="page" class="com.friendList.model.FriendListService" />
									<div class="pop-up pop6" style="font-family:'test';background-color: rgba(25,25,25,0.95);">
										<div class="content">
											<div class="container" style="max-width: 700px;">
												
												<span class="close">close</span>

												<div class="title">
													<h1 style="font-family:'test';color:rgba(134,214,613,0.9);">揪團會員</h1>
												</div>
											<!-- =============================================== -->
											<section>
												<div class="tbl-header">
													<table cellpadding="0" cellspacing="0" border="0">
														<thead>
															<tr>
																<th>頭貼</th>
																<th>性別</th>
																<th>姓名</th>
																<th></th>
															</tr>
														</thead>
													</table>
												</div>
												<div class="tbl-content">
													<c:set var="creater" value="<%=groupVO.getMem_id()%>" />
													<c:set var="me" value="<%=memVO.getMem_id()%>" />
													<table cellpadding="0" cellspacing="0" border="0">
														<tbody>
															<tr class="createMem">
																<td><c:if
																		test="${memberSvc.getOneMem(groupVO.mem_id).mem_pic != null}">
																		<img
																			src="<%=request.getContextPath()%>/DBGifReaderGro?mem_id=${memVO.mem_id}"
																			style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																	</c:if> <c:if
																		test="${memberSvc.getOneMem(groupVO.mem_id).mem_pic == null}">
																		<c:if
																			test='${memberSvc.getOneMem(groupVO.mem_id).mem_gender.equals("男")}'>
																			<img
																				src="<%=request.getContextPath()%>/front_end/css_group/images/men.png"
																				style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																		</c:if>
																		<c:if
																			test='${memberSvc.getOneMem(groupVO.mem_id).mem_gender.equals("女")}'>
																			<img
																				src="<%=request.getContextPath()%>/front_end/css_group/images/women.png"
																				style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																		</c:if>
																	</c:if></td>
																<td>${memberSvc.getOneMem(groupVO.mem_id).mem_gender}</td>
																<td>${memberSvc.getOneMem(groupVO.mem_id).mem_name}</td>
																<c:if test="${creater!=me}">
																	<c:if test='${friendSvc.getOneStat(memVO.mem_id,creater)!="2"}'>
																		<td><button class="addFriend" my_id="${memVO.mem_id}" his_id="${creater}">加為好友</button></td>
																	</c:if>
																	<c:if test='${friendSvc.getOneStat(memVO.mem_id,creater)=="2"}'>
																		<td><button class="addFriend" my_id="${memVO.mem_id}" his_id="${creater}"style="background-color:rgba(40,40,40,0.95);color:white;" disabled>已是好友</button></td>
																	</c:if>
																</c:if>
																<c:if test="${creater==me}">
																	<td></td>
																</c:if>
															</tr>


															<c:forEach var="memInfo" items="${groMember}">
																<tr>
																	<td><c:if
																			test="${memberSvc.getOneMem(memInfo.mem_id).mem_pic != null}">
																			<img
																				src="<%=request.getContextPath()%>/DBGifReaderGro?mem_id=${memInfo.mem_id}"
																				style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																		</c:if> <c:if
																			test="${memberSvc.getOneMem(memInfo.mem_id).mem_pic == null}">
																			<c:if
																				test='${memberSvc.getOneMem(memInfo.mem_id).mem_gender.equals("男")}'>
																				<img
																					src="<%=request.getContextPath()%>/front_end/css_group/images/men.png"
																					style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																			</c:if>
																			<c:if
																				test='${memberSvc.getOneMem(memInfo.mem_id).mem_gender.equals("女")}'>
																				<img
																					src="<%=request.getContextPath()%>/front_end/css_group/images/women.png"
																					style="width: 60; height: 60; border-radius: 50%; margin: 5 0">
																			</c:if>
																		</c:if></td>
																	<td>${memberSvc.getOneMem(memInfo.mem_id).mem_gender}</td>
																	<td>${memberSvc.getOneMem(memInfo.mem_id).mem_name}</td>
																	<c:if test="${memInfo.mem_id!=me}">
																		<c:if test='${friendSvc.getOneStat(memVO.mem_id,memInfo.mem_id)!="2"}'>
																			<td><button class="addFriend" my_id="${memVO.mem_id}" his_id="${memInfo.mem_id}">加為好友</button></td>
																		</c:if>
																		<c:if test='${friendSvc.getOneStat(memVO.mem_id,memInfo.mem_id)=="2"}'>
																			<td><button class="addFriend" my_id="${memVO.mem_id}" his_id="${memInfo.mem_id}"style="background-color:rgba(40,40,40,0.95);color:white;" disabled>已是好友</button></td>
																		</c:if>
																	</c:if>
																	<c:if test="${memInfo.mem_id==me}">
																		<td></td>
																	</c:if>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</section>
											<!-- =============================================== -->

												</div>
											</div>
										</div>
									</div>
<!----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ----------------------------  -->

							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
<!---about-->	

<div class="containerChat" style="background-color: rgba(0,0,0,0.2);">
        <button class="chatCloseBtn">close</button>
        <div class="left" style="background: rgba(255,255,255,0.9);">
            <div class="top-du" >
            	<div style="background: rgba(40,40,40,0.2);">
            	<c:if test="${memVO.mem_pic!=null}">
             		<img class="offline" src="/DA103G5/DBGifReaderMem?mem_id=${memVO.mem_id}" style="position: fixed;width:75px;height:75px;left:30px;top:5px;border-radius: 50%;margin: 11 0;" alt="">
             	</c:if>
             	<c:if test="${memVO.mem_pic==null}">
             		<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("男")}'>
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/men.png" style="width: 75px;left:30px;top:5px;border-radius: 50%;margin: 11 0;margin-left: 20;">
					</c:if>
					<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("女")}'>
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png" style="width: 75px;left:30px;top:5px;border-radius: 50%;margin: 11 0;margin-left: 20;">
					</c:if>
             	</c:if>
             <span style="position: absolute;right:100px;top:25px;font-size:30px;font-family:'test';">${memVO.mem_name}</span>
				</div>
            </div>
           <ul class="people" style="background: rgba(20,20,20,0.9);height: 504;">
				<c:forEach var="friendListVO2" items="${list2}" begin="0" >
				<c:if test="${friendListVO2.his_id != memVO.mem_id && friendListVO2.friend_stat==2}">
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
	                    <span class="preview">I was wondering...</span>
	                </li>
	            </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="right" style="float: right;bottom:0;background:rgba(20,20,20,0.9);">
            <div class="top" style="background: rgba(255,255,255,0.9);"><span>To: <span class="name"></span></span></div>
            <div class="chat" id="default" data-chat="default" style="display: none;"></div>
            <c:forEach var="friendListVO2" items="${list2}" begin="0" end="5">
            <c:if test="${friendListVO2.his_id != memVO.mem_id }">
<!--             ================================= -->
            <div class="chat" id="${memSvc.getOneMem(friendListVO2.his_id).mem_name}" data-chat="${memSvc.getOneMem(friendListVO2.his_id).mem_name}"> 
<!--             ================================= -->
                <div class="conversation-start">
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
    
    <div class="inviteBox">
		<h3 class="inviteInfo"></h3>
		<button class="acceptBtn">接受</button>
		<button class="refuseBtn">拒絕</button>
	</div>
	<div class="friendAlert">
		<h3 class="alertInfo"></h3>
	</div>
	
	<script>
	$('button[class="acceptBtn"]').click(function(){
		$('div[class="inviteBox"]').css("opacity","0");
		setTimeout(function(){
			$('div[class="inviteBox"]').css("display","none");
		},500);
	});
	$('button[class="refuseBtn"]').click(function(){
		$('div[class="inviteBox"]').css("opacity","0");
		setTimeout(function(){
			$('div[class="inviteBox"]').css("display","none");
		},500);
	});
</script>


	<!--copy-->
	<div class="copy-section wow fadeInUpBig animated animated" data-wow-delay="0.4s">
		<div class="container">
				<div class="social-icons">
					<a href="#"><i class="icon"></i></a>
					<a href="#"><i class="icon1"></i></a>
					<a href="#"><i class="icon2"></i></a>
					<a href="#"><i class="icon3"></i></a>
				</div>
				<p>Copyright &copy; 2016.Company name All rights reserved.More Templates <a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會出來必是精品</a></p>
		</div>
	</div>
	<!--copy-->
	
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js'></script>
	<!-- ==========================================================================================================杜===== -->
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
	<script>
	
var MyPointFriend = "/FriendListEchoServer" + "/" + '${memVO.mem_id}' ;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURLFriend = "ws://" + window.location.host + webCtx + MyPointFriend;
// var statusOutput = document.getElementById("statusOutput");
var his_id;
var webSocketFriend;

function connectFriend() {
	// 建立 websocket 物件
	webSocketFriend = new WebSocket(endPointURLFriend);
	webSocketFriend.onopen = function(event) {
		console.log("好友系統連線");
	};
	webSocketFriend.onmessage = function(event) {
        var jsonObj = JSON.parse(event.data);
        his_id = jsonObj.his_id;
        var event = jsonObj.event;
        var friend = jsonObj.his_name;

        if(event=="addFriend"){
        	$('div[class="inviteBox"]').css("display","initial");
        	if($('div[class="inviteBox"]').css("display")=="block"){
        		$('div[class="inviteBox"]').css("opacity","1");
            	$('h3[class="inviteInfo"]').text(friend+"想加你為好友");
        	}
        	
        }
		if(event=="acceptFriend"){
			$('div[class="friendAlert"]').css("display","initial");
        	if($('div[class="friendAlert"]').css("display")=="block"){
        		$('div[class="friendAlert"]').css("opacity","1");
            	$('h3[class="alertInfo"]').text(friend+"已接受你的邀請!");
        	}
        	setTimeout(function(){
    			$('div[class="friendAlert"]').css("opacity","0");
    		},1500);
        }
        
	};
	webSocketFriend.onclose = function(event) {
		console.log("好友系統關閉");
	};
}

// 加好友

$('button[class="addFriend"]').click(function(event){
	var him = $(this).attr("his_id");
	$.ajax({
		url:"friendList.do",
		type:"post",
		data:{
			my_id:'${memVO.mem_id}',
			his_id:him,
			action:'add',
			
		},
		success: function(){
			var jsonObj = {
					   "my_name" : '${memVO.mem_name}', 
					   "my_id" : '${memVO.mem_id}', 
					   "his_id" : him, 
					   "event" : "addFriend"
					   };
			webSocketFriend.send(JSON.stringify(jsonObj));
			$('div[class="friendAlert"]').css("display","initial");
			if($('div[class="friendAlert"]').css("display")=="block"){
				$('div[class="friendAlert"]').css("opacity","1");
		    	$('h3[class="alertInfo"]').text("已送出邀請!");
			}
			setTimeout(function(){
				$('div[class="friendAlert"]').css("opacity","0");
			},1500);
		}
	});
	
});
$('button[class="acceptBtn"]').click(function(event){
	
	$.ajax({
			url:"friendList.do",
			type:"post",
			data:{
				my_id:'${memVO.mem_id}',
				his_id:his_id,
				action:'confirm',
				
			},
			success: function(){
				var jsonObj = {
						   "my_name":'${memVO.mem_name}',
						   "my_id" : '${memVO.mem_id}', 
						   "his_id" : his_id, 
						   "event" : "acceptFriend"
						   };
				webSocketFriend.send(JSON.stringify(jsonObj));
			}
		});
	
});
$('button[class="refuseBtn"]').click(function(event){
	$.ajax({
		url:"friendList.do",
		type:"post",
		data:{
			my_id:'${memVO.mem_id}',
			his_id:his_id,
			action:'confirmNO',
			
		},
		success: function(){
			alert('已拒絕');
		}
	});
	

});



function disconnectFriend () {
	webSocketFriend.close();
}

</script>
<!-- ===================================================================================================================== -->
  	<script type="text/javascript">
    (function() {
  $('.skills-prog li').find('.skills-bar').each(function(i) {
    $(this).find('.bar').delay(i * 150).animate({
      width: $(this).parents().attr('data-percent') + '%'
    }, 1000, 'linear', function() {
      return $(this).css({
        'transition-duration': '.5s'
      });
    });
  });

  $('.skills-soft li').find('svg').each(function(i) {
    var c, cbar, circle, percent, r;
    circle = $(this).children('.cbar');
    r = circle.attr('r');
    c = Math.PI * (r * 2);
    percent = $(this).parent().data('percent');
    cbar = ((100 - percent) / 100) * c;
    circle.css({
      'stroke-dashoffset': c,
      'stroke-dasharray': c
    });
    circle.delay(i * 150).animate({
      strokeDashoffset: cbar
    }, 1000, 'linear', function() {
      return circle.css({
        'transition-duration': '.3s'
      });
    });
    $(this).siblings('small').prop('Counter', 0).delay(i * 150).animate({
      Counter: percent
    }, {
      duration: 1000,
      step: function(now) {
        return $(this).text(Math.ceil(now) + '%');
      }
    });
  });

}).call(this);
  </script>
  
  
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
  
  <script>

  
  
  	$('.stars_btn').click(function(){
  		var star = $('input[name*="stars"]:checked').val();
  		
  		$.ajax({
  			url:"joingroup.do",
  			type:"post",
  			data:{
  				gro_id:'${groupVO.gro_id}',
  				mem_id:'<%=mem_id%>',
  				requestURL:'<%=request.getServletPath()%>',
  				action:'update',
  				BF_url:'/front_end',
  				stars:star
  				
  			},
  			success: function(){
  				alert("評價成功");
  			}
  		});
  	});
//   ============================================================================================================
	
	$('.report_btn').click(function(){
  		var info = $('input[name*="gro_rep_info"]').val();
  		
  		$.ajax({
  			url:"reportgroup.do",
  			type:"post",
  			data:{
  				gro_id:'${groupVO.gro_id}',
  				mem_id:'<%=mem_id%>',
  				gro_rep_info:info,
  				requestURL:'<%=request.getServletPath()%>',
  				action:'insert',
  				BF_url:'/front_end',
  				
  			},
  			success: function(){
  				alert("檢舉成功");
  			}
  		});
  	});
  </script>
  
  <script type="text/javascript">
    
    $("[name*='alert']").click(function(){
    	if($("[name*='gro_rep_info']").val()==""){
    		alert("檢舉內容請勿空白");
    	}
    });
    
    $("[name*='starbtn']").click(function(){
    	if($('input[name*=stars]:checked').val()=="-1"){
    		alert("最少一顆星");
    	}else {
    		$("[name*='starbtn']").attr('disabled', 'disabled');
    		$("[name*='starbtn']").val("已評價");
    	};
    });
//  ------------------------------------------
    $('.btnPic').click(function(){
        $('.pop3').addClass('open');
        $('.pic_btn').attr('type','submit');
      });

      $('.pop3 .close').click(function(){
        $('.pop3').removeClass('open');
        $('.pic_btn').attr('type','hidden');
      });
//  ------------------------------------------
      $('.btn2').click(function(){
          $('.pop2').addClass('open');
          $('.stars_btn').attr('type','button');
        });

        $('.pop2 .close').click(function(){
          $('.pop2').removeClass('open');
          $('.stars_btn').attr('type','hidden');
        });
//  ------------------------------------------
      $('.btn3').click(function(){
          $('.pop4').addClass('open');
          $('.pic_btn').attr('type','submit');
        });

        $('.pop4 .close').click(function(){
          $('.pop4').removeClass('open');
          $('.pic_btn').attr('type','hidden');
        });
//  ------------------------------------------
        $('.btn4').click(function(){
            $('.pop5').addClass('open');
            $('.quit_btn').attr('type','submit');
          });

          $('.pop5 .close').click(function(){
            $('.pop5').removeClass('open');
            $('.quit_btn').attr('type','hidden');
          });
//  ------------------------------------------
          $('.btn5').click(function(){
              $('.pop6').addClass('open');
            });

            $('.pop6 .close').click(function(){
              $('.pop6').removeClass('open');
            });
          
  </script>
  
  <script type="text/javascript">
		function readURL(input){

  			if(input.files && input.files[0]){

    			var imageTagID = input.getAttribute("targetID");

    			var reader = new FileReader();

    			reader.onload = function (e) {

      								var img = document.getElementById(imageTagID);

        							img.src = e.target.result;

    							}

    			reader.readAsDataURL(input.files[0]);


  			}

		}
	</script>
</body>
</html>