<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.gro.group.groupVO"%>
<%@page import="com.gro.group.groupDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>

<% 
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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css_group/css/index.css">
	<link href="<%=request.getContextPath()%>/front_end/css_group/css/select.css" rel='stylesheet' type='text/css' />

		<script>
			new WOW().init();
		</script>
	<!--//end-animate-->
	<style type="text/css">

		
		@font-face{
  		font-family:'test';
  		src:url("<%=request.getContextPath()%>/front_end/group/SentyTEA-20190904.ttf");
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

		.pen-wrapper__inner{
			width: 620px;
			margin: 150px auto; 
		}

		.buton-cover>button, .button-set>a>button {
			font-family: 'test';	
			font-size: 30px;
		}
		
		.btn--primary {
  			background: url("<%=request.getContextPath()%>/front_end/css_group/images/reex.png");
			background-size: cover;
		}
		.btn--primary:hover {
 			 background: url("<%=request.getContextPath()%>/front_end/css_group/images/rex.png");
			background-size: cover;
		}
		input[type="submit"] {
			font-family: 'test';
    		font-size: 30px;
		}
		
		input[type='submit']{
			position: absolute;
    		top: 0;
    		width: 100%;
    		height: 100%;
    		background-color: rgba(0,0,0,0);
    		border: none;
		}
		.selectChar {
			z-index: 10;
    		position: absolute;
    		width: 4%;
    		top: 150px;
    		left: 0;
    		right: 0;
    		margin-left: auto;
   			margin-right: auto;
   			opacity:0.8;
   			transition:0.5s;
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
<img class="selectChar" src="<%=request.getContextPath()%>/front_end/css_group/images/charSelect.png">

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


<div class="arrow-slider" style="transition:all 0.5s;margin-top: 106;">
	<div class="wrapper mobile-carousel">
		<div class="left" style="background-image: url(<%=request.getContextPath()%>/front_end/css_group/images/rex.png)">
			<div class="color-overlay colorA" style="background-color:rgba(40,40,40);"></div>			
			
			<div class="content-wrap" style="color:rgba(255,255,255);">
				<div class="content areaA">
					<h2>歷史參團紀錄</h2>
					<p></p>
				</div>
			</div>
			<div class="button-wrap">
				
        <a href="<%=request.getContextPath()%>/front_end/group/listAllGroup_Create_History.jsp">
        	<img src="<%=request.getContextPath()%>/front_end/css_group/images/create.png" style="width: 80%;">
<!--         	<form action="group.do" method="POST"> -->
<!--         	<input type="submit" value=""> -->
<!--         	<input type="hidden" name="action" value="list_create"> -->
<!--         	<input type="hidden" name="BF_url" value="/front_end"> -->

<%--         	<input type="hidden" name="mem_id" value="<%= memVO.getMem_id()%>"> --%>
<!--         	 </form> -->

        </a>
       
			
			</div>
		</div>



		<div class="right" style="background-image: url(<%=request.getContextPath()%>/front_end/css_group/images/rexw.png)">
			<div class="color-overlay colorB"></div>			
			<div class="content-wrap" style="right:35%;">
				<div class="content areaB" style="right:0;left:auto;">
					<h2>歷史建團紀錄</h2>
					<p></p>
					
				</div>
			</div>
			
			<div class="button-wrap">
			
        		<a href="<%=request.getContextPath()%>/front_end/group/listAllGroup_Join_History.jsp" style="float: right;">
        		<img src="<%=request.getContextPath()%>/front_end/css_group/images/join.png" style="width: 80%;float: right;">
<!--         		<form action="group.do" method="POST">  -->
<!--         			<input type="submit" value=""> -->
<!--         			<input type="hidden" name="action" value="list_join"> -->
<!--         			<input type="hidden" name="BF_url" value="/front_end">  -->

<%--         			<input type="hidden" name="mem_id" value="<%= memVO.getMem_id()%>"> --%>
<!--         			</form> -->

        </a>
        
				
			</div>
		</div>
	</div>
	<div class="mobile-arrows"></div>
</div>

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

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js'></script>
<script type="text/javascript">
	function arrowSliderSetup() {
	
	// Mobile carousel
	if ($(window).outerWidth() < 768) {
		
		// Set the height of the mobile arrow class to match the height of the image slide so we can vertially center it
		$('.mobile-arrows').css('height', $('.mobile-image').outerHeight());
		
		//Init the carousel if it already has not been initialized
		if(!$('.mobile-carousel').hasClass('slick-initialized')){
			$('.mobile-carousel').slick({
				dots: true,
				draggable: true,
				infinite: true,
				speed: 300,		
				adaptiveHeight: false,
				arrows: true,
				appendArrows: $('.mobile-arrows')
			});
		}
		
		// Remove desktop class
		if($('.arrow-slider').hasClass('desktop-view')){
			$('.arrow-slider').removeClass('desktop-view');
		}
		
	// Desktop slider
	} else {
		// If the user resizes their screen from mobile to desktop, destroy the mobile carousel
		if($('.mobile-carousel').hasClass('slick-initialized')){
			$('.mobile-carousel').slick('unslick');
		}
		
		// Set up the arrow hover actions for desktop, just once
		if(!$('.arrow-slider').hasClass('desktop-view')){
			arrowSliderDesktop()
		}
		
		$('.arrow-slider').addClass('desktop-view');
	}
}

function arrowSliderDesktop() {
	const slider = $('.arrow-slider');
	const sliderLeft = $('.arrow-slider .left');	
	const sliderRight = $('.arrow-slider .right');

	// Mouse-listeners for the left and right sides of the slider
	sliderLeft.mouseenter(function() {
		slider.addClass('left-focused');
	}).mouseleave(function() {
		slider.removeClass('left-focused');
	});
	
	sliderRight.mouseenter(function() {
		slider.addClass('right-focused');
	}).mouseleave(function() {
		slider.removeClass('right-focused');
	});
}


$(document).ready(function(){

	// Set up the arrow slider functionality depending on if its mobile or desktop sizing
	arrowSliderSetup();
	
	// On window resize, set up the arrow slider functionality depending on if its mobile or desktop sizing
	$(window).resize(function () {
		arrowSliderSetup();
	});
	
});

$('.left').mouseover(function(){
	$('.arrow-slider').css("background-color","rgba(255,255,255)");
});
$('.left').mouseleave(function(){
	$('.arrow-slider').css("background-color","rgba(40,40,40)");
});

$('.arrow-slider').mouseover(function(){
	$('.selectChar').css("opacity","0");
	$('.selectChar').css("transition","all 0.5s ease 0s");
});
$('.arrow-slider').mouseleave(function(){
	$('.selectChar').css("opacity","0.8");
	$('.selectChar').css("transition","all 0.5s ease 0.7s");
});
</script>
<script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
<script type="text/javascript">
// 	(function($){
  
//   function clickHandler() {
//     $(this).parents('.buton-cover').toggleClass('is_active');
//   }

//   $('.btn').on('mouseover', clickHandler);
//   $('.btn').on('mouseout', clickHandler);

// }(jQuery));
</script>


</html>