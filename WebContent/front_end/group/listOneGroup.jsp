<%@page import="com.gro.joingroup.joingroupService"%>
<%@page import="java.util.Set"%>
<%@page import="com.gro.joingroup.joingroupVO"%>
<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gro.group.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	groupVO groupVO = (groupVO) request.getAttribute("groupVO"); 
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	String loginMem=null;
	if(session.getAttribute("memVO")!=null){
		loginMem = memVO.getMem_name();
	};
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	joingroupService joingroupSvc = new joingroupService();
	joingroupVO joingroupVO = joingroupSvc.findOneInfo(groupVO.getGro_id(), memVO.getMem_id());

%>

<jsp:useBean id="groclassSvc" scope="page" class="com.gro.type.groclassService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />


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
		
		.resume>img {
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
		
		.skills-prog {
			width: 40%;
    		margin-right: 0;
    		margin-left: auto;
		}
		
		.func>.jbg{
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/think.png");
			background-size:cover;
			position: absolute;
			width: 280;
			height: 220;
    		top: auto;
    		right: auto;
    		bottom: 300;
    		left: 170;
		}
		
		.func>.jbg:hover {
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/thinkHover.png");
			background-size:cover;
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
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
<!---banner--->
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
<!-- -------------------------------------------------------------------瀏覽揪團位置-------------------------------------------- ---------------------------- ---------------------------- -->

					<div class="resume">
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/cute2.png">
						<div class="base">
							<div class="profile">
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
						<%if(joingroupVO!=null && memVO.getMem_id()!=groupVO.getMem_id()) {%>
						<div class="jbg" style="background: none;">
						
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/thinkHoverJoin.png" style="width:100%;height:100%;">
						
						<%}else if(memVO.getMem_id().equals(groupVO.getMem_id())){ %>
						<div class="jbg" style="background: none;">
						
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/thinkHoverCreate.png" style="width:100%;height:100%;">
						<%}else{
									if(groupVO.getGro_mnum()==groupVO.getGro_mnum_max()){%>
										<div class="jbg" style="background: none;">
										<img src="<%=request.getContextPath()%>/front_end/css_group/images/thinkHoverFull.png" style="width:100%;height:100%;">
									<%} else{%>
										<div class="jbg">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/group/joingroup.do" style="margin-bottom: 0px;width:100%;height:100%;">
												<input type="submit" value=""style="width: 100%;
    																    			height: 100%;
    																     			background-color: rgba(0,0,0,0);
    																      			border: none;"> 
												<input type="hidden" name="gro_id" value="${groupVO.gro_id}">
												<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
												<input type="hidden" name="BF_url" value="/front_end">
												<input type="hidden" name="action" value="insert">
											</FORM>
									<%}
							}%>
						</div>
						
							<div class="work">
								<h3>
									<i class="fa fa-briefcase"></i>揪團資訊
								</h3>

								<ul>
									<li>
										<div class="about" style="width: 25%;">
											<h3 style="margin-bottom: 10">揪團類別</h3>${groclassSvc.getOneClass(groupVO.gro_cid).gro_cname}
										</div>
									</li>
									<li>
										<div class="about">
											<h3 style="margin-top: 0; margin-bottom: 10">揪團介紹</h3>${groupVO.gro_info}
										</div>
									</li>

								</ul>
							</div>

							<div class="skills-prog" style="margin: 0 0 0 45%;position: relative;z-index: 5;">
								<h3 style="width: 70%;">
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
									<%if(mnum==mnumMax) {%>
									<li data-percent="<%=mnumPercent%>">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
         								 </svg>
         								 <span>已額滿</span><small></small>
         							</li>
         							<% }else{%>
         							<li data-percent="<%=mnumPercent%>">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
         								 </svg>
         								 <span>目前人數/${groupVO.gro_mnum}人</span><small></small>
         							</li>
         							<%} %>
									<li data-percent="100">
										<svg viewbox="0 0 100 100">
            								<circle cx="50" cy="50" r="45"></circle>
            								<circle class="cbar" cx="50" cy="50" r="45"></circle>
         								</svg><span>最多人數<br>${groupVO.gro_mnum_max}人</span>
         							</li>
								</ul>

							</div>
						</div>
					</div>



					<!-- -------------------------------------------------------------------瀏覽揪團位置------------------------------------------------------------------------ ----------------------------  -->
								<div class="clearfix"></div>
							</div>
							
						</div>
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
				<p>Copyright &copy; 2016.Company name All rights reserved.More Templates <a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會出來必是精品</a></p>
		</div>
	</div>
	<!--copy-->
	
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js'></script>

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
</body>
</html>