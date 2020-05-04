<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>

<%-- <% --%>
<!--    MemVO memVO = (MemVO) request.getAttribute("memVO"); -->
<%-- %> --%>
<%
    CoaVO CoaloginVO = (CoaVO) request.getAttribute("CoaloginVO");
    Object CoaAccount = session.getAttribute("CoaloginVO");                  // 從 session內取出 (key) account的值
    if (CoaAccount == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
      session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/CoaLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
      return;
    }
    CoaVO coaVO = (CoaVO) request.getAttribute("coaVO");
%>
<html>
		<head>
			<title>教練帳號管理</title>
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
			<!--css-->
			<style type="text/css">
@font-face {
	font-family: 'test';
	src: url('SentyTEA-20190904.ttf');
}

body {
	background-color: lightgray;
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

table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
.copy-section{
    margin-top:-90%

}
table{
						border:1px solid #grey;
						border-collapse: collapse;/*邊框縫隙*/
						width:1500px;
						height:20px auto;
						margin-left: 300px;
                        width:1150px;
					}
		table caption{
			font-size: 30px;
			text-align: center;
			font-weight:bolder;
			letter-spacing: 5px;
			background-color: #666;
			border-top-left-radius: 3px;
            border-top-right-radius: 3px;
            border-bottom-left-radius: 3px;
            border-bottom-right-radius: 3px;
            color: white;
            height:50px ;
            line-height:50px;


		}
		table td,table th{/*兩個都用同樣的外觀，用逗點隔開就好，不用重複寫*/
			/*border:1px solid #696;*/
			text-align: center;
			font-size: 20px;
			

		}
		table th{
			background-color: #BFB6B3;
			color:black;

		}
		table td{
			background-color: #E8E1DE;
			color:black;
		}
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
			<div class="navbar navbar-default navbar-fixed-top"style="position:static;">
				<div class="container">
					<div class="navbar-header wow fadeInLeft animated animated" data-wow-delay="0.4s">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/FirstPageLogin.jsp"><img src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
							<li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/FirstPageLogin.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li ><a class="nav-in" href="健康量測.html"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/crs/crsEntrance.jsp"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp" ><span data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/FirstPage.jsp"><span data-letters="登出" ><b>登出</b></span></a></li>
						</ul>
					</div><!--/.nav-collapse -->
				</div>
			</div>
			<!---banner--->
		<div id="wrapper">
					<div class="sidebar">
						<div class="content">
							<div class="col-md-4 about-grid1 wow fadeInLeft animated animated" data-wow-delay="0.4s" id="content1">
								<div class="latest-top" >
								<h4><img src="<%=request.getContextPath()%>/front_end/images/face-24px.svg" id="mem">教練帳戶</h4>
									<div class="latest-class">
										<div class="latest-right">
											<h5>教練檔案</h5>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
								
								<div class="latest-top">
								<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapsepoint"
								aria-expanded="true" aria-controls="collapsepoint">
									<h4><img src="<%=request.getContextPath()%>/front_end/images/payment-24px.svg" id="point">點數兌現</h4></a>
									<div id="collapsepoint" class="collapse"
										aria-labelledby="headingpoint" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="<%=request.getContextPath()%>/front_end/dep/CoaManagePersonalDep.jsp">教練點數</a><br>
											<a class="collapse-item" href="">申請紀錄</a><br> 
										</div>			
									</ul>
								</div>	
								</div>
								<div class="latest-top">
								<ul>
									<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseActivity"
										aria-expanded="true" aria-controls="collapseActivity">
										<h4><img src="<%=request.getContextPath()%>/front_end/images/date_range-24px.svg" id="Activity">管理課程</h4></a>
										<div id="collapseActivity" class="collapse"
										aria-labelledby="headingActivity" data-parent="#accordionSidebar">
										<div class="bg-white py-2 collapse-inner rounded">
											<a class="collapse-item" href="">課程管理</a><br>
											<a class="collapse-item" href="">預約管理</a><br> 
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
											<a class="collapse-item" href="">LLLLLL</a><br> 
										</div>			
									</ul>
								</div>
								
								
								</div>
							<div>	
						</div>
					</div>
				</div>
				</div>
	<div class="col-md-8 map-middle wow fadeInRight animated animated" data-wow-delay="0.4s" style="font-size: 24px ;margin-left: -17%;">
	<jsp:useBean id="depSvc" scope="page" class="com.deposit.model.DepService" />
	<c:forEach var="depVO" items="${depSvc.all}">			
				<c:if test="${depVO.coa_id == coaVO.coa_id}">
	<table>
	
					<caption>申請紀錄</caption>
						<tr>
							<th>儲值編號</th>
							<th>儲值日期</th>
							<th>儲值金額</th>
							<th>申請提款</th>
						</tr>
				
						<tr>
							<td>${depVO.dep_id}</td>
							<td>${depVO.dep_day}</td>
							<td>${depVO.dep_money}</td>						
							<td>
							<input type="hidden" name="action" value="apply_DepCoa">
							<input type="submit" class="t1 is"  id="sta${depVO.dep_sta}" value="${depVO.dep_sta==0?"申請":"申請中"}" depid="${depVO.dep_id}"></td>
						</tr>
						
					</table>
					</c:if>
					</c:forEach>
</div>
<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- Sidebar END -->
		
			<!-- calender END -->
	

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
			</body>
			<script>
			 $(".is").click(function(event){
				  $.ajax({
					  url:"dep.do",
					  type:"POST",
					  data:{
						  action:"apply_DepCoa",
						  dep_id:$(this).attr("depid"),
						  dep_sta:"1"					
					  },
					  success: function(){
					  }
					  
					  
				  });
				  $(this).val("申請中" );
				  $(this).attr("disabled","true");
			  });
			</script>
			 
			</html>