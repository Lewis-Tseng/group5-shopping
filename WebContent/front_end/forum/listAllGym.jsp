<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.forum_class.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<html>
<head>
<title>文章討論區 </title>
<!---css--->
<link href="<%=request.getContextPath()%>/front_end/dudu/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front_end/dudu/css/style.css" rel='stylesheet' type='text/css' />
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
<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
		<script>
			new WOW().init();
		</script>
	<!--//end-animate-->
<style type="text/css">
.table tr th {
     width: 0%; 
}
.alert-warning {
    color: #bf1515;
    background-color: #1e1e1e;
    border-color: #1e1e1e;
    float: right;
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
.class-grid{
	float: initial;
    margin: 0 auto;
    width: 70%;
}
th,td{
	text-align:center;
	font-size:1.5em;
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
					<h2 style="color: rgb(50,50,50)"><b>國民運動中心</b></h2>
				</div>
			</div>
			<!---banner--->


	<!---class--->
	<div class="class">
		<div class="container">
			<div class="class-grids w3l">
				<div class="col-md-8 class-grid wow fadeInLeft animated animated"
					data-wow-delay="0.4s">
					<div class="class-top">

						<div class="clearfix"></div>
						</div>

<!-- 爬蟲開始 -->
<%
    Map<String,Map> GYMmap = (Map<String,Map>) request.getAttribute("GYMmap");
	Map swimNum = GYMmap.get("游泳");
	Map gymNum = GYMmap.get("健身");
	Map loc = GYMmap.get("地點");
	Map gymLimit= GYMmap.get("健身上限");
	Map swimLimit = GYMmap.get("泳池上限");
%>

<table class="table table-dark" style="color:white" top="20px">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">國民運動中心</th>
      <th scope="col">健身房人數</th>
      <th scope="col">上限</th>
      <th scope="col">游泳池人數</th>
      <th scope="col">上限</th>
    </tr>
  </thead>
  <tbody>
  <%
  	int i; 
  	for(i=0;i<17;i++){%>
  	<tr>
      <th scope="row"><%=i+1%></th>
      <td ><%=loc.get(i)%></td>
      <td><%=gymNum.get(loc.get(i))%></td>
      <td><%=gymLimit.get(i)%></td>
      <td><%=swimNum.get(loc.get(i))%></td>
      <td><%=swimLimit.get(i)%></td>
    </tr>
  	<% } %>
  	
  	
	</tbody>  
<!--     <tr> -->
<!--       <th scope="row">1</th> -->
<!--       <td>北投</td> -->
<%--       <td><%=gymNum.get("北投")%></td> --%>
<!--       <td>80</td> -->
<%--       <td><%=swimNum.get("北投")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <th scope="row">2</th> -->
<!--       <td>士林</td> -->
<%--       <td><%=gymNum.get("士林")%></td> --%>
<!--       <td>100</td> -->
<%--       <td><%=swimNum.get("士林")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--        <tr> -->
<!--       <th scope="row">3</th> -->
<!--       <td>內湖</td> -->
<%--       <td><%=gymNum.get("內湖")%></td> --%>
<!--       <td>130</td> -->
<%--       <td><%=swimNum.get("內湖")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--        <tr> -->
<!--       <th scope="row">4</th> -->
<!--       <td>南港</td> -->
<%--       <td><%=gymNum.get("南港")%></td> --%>
<!--       <td>110</td> -->
<%--       <td><%=swimNum.get("南港")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--        <tr> -->
<!--       <th scope="row">5</th> -->
<!--       <td>信義</td> -->
<%--       <td><%=gymNum.get("信義")%></td> --%>
<!--       <td>65</td> -->
<%--       <td><%=swimNum.get("信義")%></td> --%>
<!--       <td>164</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <th scope="row">6</th> -->
<!--       <td>中山</td> -->
<%--       <td><%=gymNum.get("中山")%></td> --%>
<!--       <td>70</td> -->
<%--       <td><%=swimNum.get("中山")%></td> --%>
<!--       <td>180</td> -->
<!--     </tr> -->
<!--         <tr> -->
<!--       <th scope="row">6</th> -->
<!--       <td>中正</td> -->
<%--       <td><%=gymNum.get("中正")%></td> --%>
<!--       <td>120</td> -->
<%--       <td><%=swimNum.get("中正")%></td> --%>
<!--       <td>300</td> -->
<!--     </tr> -->
<!--         <tr> -->
<!--       <th scope="row">7</th> -->
<!--       <td>大安</td> -->
<%--       <td><%=gymNum.get("大安")%></td> --%>
<!--       <td>85</td> -->
<%--       <td><%=swimNum.get("大安")%></td> --%>
<!--       <td>350</td> -->
<!--     </tr> -->
<!--         <tr> -->
<!--       <th scope="row">8</th> -->
<!--       <td>文山</td> -->
<%--       <td><%=gymNum.get("文山")%></td> --%>
<!--       <td>85</td> -->
<%--       <td><%=swimNum.get("文山")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--         <tr> -->
<!--       <th scope="row">9</th> -->
<!--       <td>蘆洲</td> -->
<%--       <td><%=gymNum.get("蘆洲")%></td> --%>
<!--       <td>75</td> -->
<%--       <td><%=swimNum.get("蘆洲")%></td> --%>
<!--       <td>210</td> -->
<!--     </tr> -->
<!--         <tr> -->
<!--       <th scope="row">10</th> -->
<!--       <td>淡水</td> -->
<%--       <td><%=gymNum.get("淡水")%></td> --%>
<!--       <td>70</td> -->
<%--       <td><%=swimNum.get("淡水")%></td> --%>
<!--       <td>400</td> -->
<!--     </tr> -->
<!--            <tr> -->
<!--       <th scope="row">11</th> -->
<!--       <td>土城</td> -->
<%--       <td><%=gymNum.get("土城")%></td> --%>
<!--       <td>90</td> -->
<%--       <td><%=swimNum.get("土城")%></td> --%>
<!--       <td>230</td> -->
<!--     </tr> -->
<!--            <tr> -->
<!--       <th scope="row">12</th> -->
<!--       <td>中和</td> -->
<%--       <td><%=gymNum.get("中和")%></td> --%>
<!--       <td>80</td> -->
<%--       <td><%=swimNum.get("中和")%></td> --%>
<!--       <td>400</td> -->
<!--     </tr> -->
<!--            <tr> -->
<!--       <th scope="row">13</th> -->
<!--       <td>板橋</td> -->
<%--       <td><%=gymNum.get("板橋")%></td> --%>
<!--       <td>80</td> -->
<%--       <td><%=swimNum.get("板橋")%></td> --%>
<!--       <td>400</td> -->
<!--     </tr> -->
<!--            <tr> -->
<!--       <th scope="row">14</th> -->
<!--       <td>永和</td> -->
<%--       <td><%=gymNum.get("永和")%></td> --%>
<!--       <td>65</td> -->
<%--       <td><%=swimNum.get("永和")%></td> --%>
<!--       <td>300</td> -->
<!--     </tr> -->
<!--            <tr> -->
<!--       <th scope="row">15</th> -->
<!--       <td>汐止</td> -->
<%--       <td><%=gymNum.get("汐止")%></td> --%>
<!--       <td>80</td> -->
<%--       <td><%=swimNum.get("汐止")%></td> --%>
<!--       <td>200</td> -->
<!--     </tr> -->
<!--                <tr> -->
<!--       <th scope="row">16</th> -->
<!--       <td>桃園</td> -->
<%--       <td><%=gymNum.get("桃園")%></td> --%>
<!--       <td>90</td> -->
<%--       <td><%=swimNum.get("桃園")%></td> --%>
<!--       <td>120</td> -->
<!--     </tr> -->
<!--                <tr> -->
<!--       <th scope="row">17</th> -->
<!--       <td>中壢</td> -->
<%--       <td><%=gymNum.get("中壢")%></td> --%>
<!--       <td>90</td> -->
<%--       <td><%=swimNum.get("中壢")%></td> --%>
<!--       <td>180</td> -->
<!--     </tr> -->
    

<!-- 爬蟲結束 -->


				</div>
				<div class="col-md-4 class-grid1 wow fadeInRight animated animated"
					data-wow-delay="0.4s">
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
	
	<!---class--->
<!-- <!---footer---> -->
<!-- 		<div class="footer-section"> -->
<!-- 			<div class="container"> -->
<!-- 			<div class="footer-grids"> -->
<!-- 				<div class="col-md-3 footer-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s"> -->
<!-- 					<h4>About</h4> -->
<!-- 					<p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy hendrerit mauris. Praesent vestibulummolestie lacus.</p> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-3 footer-grid wow fadeInLeft animated animated" data-wow-delay="0.4s"> -->
<!-- 					<h4>Categories</h4> -->
<!-- 					<ul> -->
<!-- 						<li>Beauty</li> -->
<!-- 						<li>Diet & Fitness</li> -->
<!-- 						<li>Lifestyle</li> -->
<!-- 						<li>Help Desk</li> -->
<!-- 						<li>Pregnancy</li> -->
<!-- 						<li>Performance Metrics</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-3 footer-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s"> -->
<!-- 				<h4>Work</h4> -->
<!-- 					<ul> -->
<!-- 						<li>Customer Support</li> -->
<!-- 						<li>Platinum Support</li> -->
<!-- 						<li>Gold Support</li> -->
<!-- 						<li>Training</li> -->
<!-- 						<li>Workshops</li> -->
<!-- 						<li>Online Training</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-3 footer-grid wow fadeInRight animated animated" data-wow-delay="0.4s"> -->
<!-- 					<h4>Contact</h4> -->
<!-- 					<p>7801 Marmora Road</p> -->
<!-- 					<p>Glasgow, DO5 98GR.</p> -->
<!-- 					<p>Freephone: +1 800 558 8990</p> -->
<!-- 					<p>Telephone: +1 659 803 9035</p> -->
<!-- 					<p>FAX: + 1 314 889 9898</p> -->
<!-- 					<a href="mailto:example@mail.com"> example@mail.com</a> -->
<!-- 				</div> -->
<!-- 				<div class="clearfix"></div> -->
<!-- 			</div> -->
<!-- 			</div> -->
<!-- 		</div>	 -->
<!-- 	<!---footer---> -->
<!-- 	<!--copy--> -->
<!-- 	<div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s"> -->
<!-- <div class="container"> -->
<!-- 			<div class="social-icons"> -->
<!-- 				<a href="https://www.facebook.com/"><i class="icon"></i></a> -->
<!-- 				<a href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a> -->
<!-- 				<a href="https://www.google.com.tw/webhp?tab=rw"><i class="icon2"></i></a> -->
<!-- 				<a href="https://www.instagram.com/?hl=zh-tw"><i class="icon3"></i></a> -->
<!-- 			</div> -->
<!-- 			<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期 -->
<!-- 				<a href="#https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="#https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	<!--copy--> -->
	



</body>

<!-- 創建文章彈出的按鈕JS--------------------------------------------------------- -->

</html>