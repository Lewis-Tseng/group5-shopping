<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.crs.model.*"%>
<%@ page import="com.crs_type.model.*"%>
<%@ page import="com.coach.model.*"%>
<%
	CrsService crsSvc = new CrsService();
	List<CrsVO> list = crsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<title>Classes</title>
	<!---css--->
	<link href="<%=request.getContextPath()%>/front_end/css/bootstrap_poan.css" rel='stylesheet' type='text/css' />
	<link href="<%=request.getContextPath()%>/front_end/css/style_poan.css" rel='stylesheet' type='text/css' />
	<script src="<%=request.getContextPath()%>/front_end/css_group/js/jquery-1.11.1.min.js"></script>
	<!---css--->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

	<!--web-fonts-->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
	<!--//web-fonts-->
	<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css/animate_poan.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>
	<!--//end-animate-->
	<style type="text/css">
		#select{
			margin-top: 12px;
		}
		.custom-dropdown {
			position: relative;
			display: inline-block;
			vertical-align: middle;
			margin: 10px; /* demo only */
		}

		.custom-dropdown select {
			background-color:#60768b8f;
			color: #fff;
			font-size: inherit;
			padding: .5em;
			padding-right: 2.5em;	
			border: 0;
			margin: -10px;
			margin-top: 2px;
			border-radius: 3px;
			text-indent: 0.01px;
			-webkit-appearance: button; /* hide default arrow in chrome OSX */
		}
		#formcss select{
			float: left;
		}

		        .info-list {
            font-size: 0px;
        }
        .info-list div.info-card {
            display: inline-block;
            width: calc(100% / 3);
            box-sizing: border-box;
            padding: 10px 20px;
        }

        .card-background {
            background-color: #FFF;
            border-radius: 10px;
            overflow: hidden;
            border: 1px solid #aaa;
        }

        .info-card div.card-image {
            width: 100%;
            height: 270px;
            overflow: hidden;
            box-sizing: border-box;
        }
        

        .card-image img {
            width: 100%;
        }

        .info-card .card-text {
            padding: 10px 20px 5px 20px;
            font-size: 18px;
            height: 200px;
        }
        
        .info-card .card-text p{
        	word-wrap: break-word;
        	font-family:"微軟正黑體";
        	font-weight: bolder;
        	margin-top: 10px;
        }

        .info-card .card-text h3 {
            font-weight: bolder;
            font-family:"微軟正黑體";           
        }

        .card-stars {
            padding: 0px 20px;
        }

        .card-stars .starNum {
            font-weight: bolder;
            font-size: 24px;
            display: inline-block;
            position: relative;
            top: -5px;
        }
        .card-stars .reviews-star {
            padding-left: 10px;
        }

        .card-stars .star {
            font-size: 28px;
            color: rgb(245, 223, 28);
        }
  		
  		b{
  			color: white;
  		}
	
		#nextPage1{
  			color: white;
  		}
  		
  		#nextPage{
  			margin-left: 44%;
  		}
  		
  		#deleteUnderline{
  			text-decoration:none;
  		}
  		
  		#deleteUnderline:hover{
  			color:grey;
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
	position: fixed;
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
         <h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img src="<%=request.getContextPath()%>/front_end/images/G5.png" />Just強身</a></h1>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
                   <li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"><b>健康量測</b></span></a> </li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/res/CoachPage.jsp"><span data-letters="瀏覽教練"><b>瀏覽教練</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp"><span data-letters="揪團運動"><b>揪團運動</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp"><span data-letters="討論區"><b>討論區</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城"><b>購物商城</b></span></a></li>
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
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=<%=memVO.getMem_id() %>"> 
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
     <%}else if(coaVO!=null){ %>   
  <%--*************************************************************** --%>
    <div class="memPic">
		<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}">
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${memVO.mem_id}"> 
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
		<div class="container" >
			<h2 style="color: black;">瀏覽課程</h2>
		</div>
	</div>
	<!---banner--->
	
<!---class--->
	<div class="container">
	<%@ include file="pages/page1.file"%>
		<div class="info-list">
			<c:forEach var="crsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
 			<div class="info-card">
				 <Form method="post" ACTION="<%=request.getContextPath()%>/front_end/crs/crs.do">
 						<a id="deleteUnderline" href="#" onclick="this.parentNode.submit(); return false;">
							<div class="card-background">
								<div class="card-image">
									<img id="imgChange" src="<%=request.getContextPath()%>/DBGifReaderCrs?crs_no=${crsVO.crs_no}" alt="" height="270" onerror="nofind()">
								</div>
								<div class="card-text">
									<h3>${crsVO.crs_name}</h3>
									<p>${crsVO.crs_content}<br></p>
								</div>
 							</div>
								<input type="submit" value="送出" style="display:none">
								<input type="hidden" name="crs_no"  value="${crsVO.crs_no}">
								<input type="hidden" name="action"  value="CrsIndex">${crsVO.crs_no}
		 				</a>
					 </Form>
		 	</div>
	 		</c:forEach>
 </div>
		<%@ include file="pages/page2.file"%>
	</div>

	<!---class--->

	<!--copy-->
	<div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
			<div class="container">
				<div class="social-icons">
					<a href="#"><i class="icon"></i></a>
					<a href="#"><i class="icon1"></i></a>
					<a href="#"><i class="icon2"></i></a>
					<a href="#"><i class="icon3"></i></a>
				</div>
					<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期
						<a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
		</div>
	</div>
	<!--copy-->
	
	<script type="text/javascript">
		function nofind(){
			  var img=event.srcElement;
			  img.src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png";
			  img.onerror=null; //控制不要一直觸發錯誤
		}
	</script>
	
</body>
</html>