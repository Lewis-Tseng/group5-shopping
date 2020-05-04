<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
  CoaVO coaVO = (CoaVO) request.getAttribute("coaVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE HTML>
		<html>
		<head>
			
			<title>會員登入</title>
			<!---css--->
			<link href="<%=request.getContextPath()%>/front_end/login/css/bootstrap.css" rel='stylesheet' type='text/css' />
			<link href="<%=request.getContextPath()%>/front_end/login/css/style.css" rel='stylesheet' type='text/css' />
			<link href="<%=request.getContextPath()%>/front_end/login/css/loginTab.css" rel='stylesheet' type='text/css' />
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
				@font-face{
					font-family: 'ggyy';
					src:url(SentyTEA-20190904.ttf);
				}
				#videobg{
					position: fixed;
					right: 0;
					bottom: 0;
					min-width: 100%;
					min-height: 100%;
					z-index: -1;
				}
				.btn-ul:hover{
					color: #E74C3C;
				}
				#footer-section{
					clear: both;
					background-color:#272727;
					height:80px;
					text-align: center;
					line-height: 80px; 
					margin-top: 17%;
				}
				img{
					max-width: 100%;
				}
				.btn-li{
					width:100%;
				}
      /*-------------------------忘記密碼-------------------------------------------------------------------------- */
				form h1 {
	font-size: 36px;
	background: #AA7700 none repeat scroll 0% 0%;
	color: rgb(255, 255, 255);
	padding: 22px 25px;
	border-radius: 5px 5px 0px 0px;
	margin: auto;
	text-shadow: none;
}

form {
	border-radius: 5px;
	max-width: 700px;
	width: 100%;
	margin: 5% auto;
	background-color: #FFFFFF;
	overflow: hidden;
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
#closeCrs{
	width: 40px;
    border-radius: 50%;
     left: 220px;
    color: black;
    float: right;
}
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
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp"><span data-letters="登入" ><b>登入</b></span></a></li>
						</ul>
					</div><!--/.nav-collapse -->
				</div>
			</nav>
			
			<!---banner--->
			<div class="banner wow fadeInDownBig animated animated" data-wow-delay="0.4s">
				<div class="container">
					<h2 style="color: rgb(255,255,255)">會員登入</h2>
					
				</div>
			</div>
			<div class="contact w3l">
				<div class="map wow fadeInUpBig animated animated" data-wow-delay="0.4s">
					<VIDEO src="images/Pexels Videos 1449878.mp4" autoplay muted loop id="videobg"></VIDEO>
				</div>
				<div class="map-grids w3-agile">
					<div class="col-md-8 map-middle wow fadeInRight animated animated" data-wow-delay="0.4s">
					<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
							</ul>
						</c:if>
						
<section id="wrapper">
   <div class="content">
      <!-- Tab links -->
      <div class="tabs">
         <button class="tablinks active" data-country="London"><p data-title="會員">會員</p></button>
         <button class="tablinks" data-country="Paris"><p data-title="教練">教練</p></button>
      </div>

      <!-- Tab content -->
      <div class="wrapper_tabcontent">

<!-- ============================================================================================================================================================= -->

      	<form action="<%=request.getContextPath()%>/mem.do" method="post">
         <div id="London" class="tabcontent active">
            <h3>MEMBER</h3>										 
			<input type="text" name="mem_useremail" class="mem_email t1" placeholder="會員帳號" required="" value="<%= (memVO==null)? "" : memVO.getMem_email()%>"  id="mem_email">
			<input type="password" name="mem_userpsw" class="t1" placeholder="    會員密碼" required="" value=""  id="mem_psw" style="font-size:14px;">
			<br>
			<br>
			<input type="hidden" name="action" value="login">
			<input type="submit" class="t1 is" value="登入" style="margin-bottom:25px;">
			<a class="aa" href="<%=request.getContextPath()%>/front_end/mem/registrator.jsp">
			<li class="btn-li" style="list-style: none;">
			<figure class="figure-apollo"style="font-family: 'ggyy'">
			<b>Register</b><br>
		<input type="submit" value="註冊" class="t1 is" style="background-color: rgba(100,100,100,0.5);">
		</figure>
								
							</li>
							</a>
						</ul>
<!-- 						<ul class="btn-ul" style="width: 100%;"> -->
<!-- 							<li class="btn-li"> -->
<!-- 								<a class="aa"href="https://www.google.com"> -->
<!-- 									<figure class="figure-apollo"style="font-family: 'ggyy'"> -->
<!-- 										<b>Retrieve password</b><br> -->
<!-- 										<input type="submit" class="t1 is" value="忘記密碼" style="background-color: rgba(100,100,100,0.5);"> -->
										
<!-- 									</figure> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">忘記密碼</button>		
                                <input id="b" type="button" value="神奇小按鈕" onclick="reg();" style="width:70px; font-size:10px;"/>					
         </div>
<!--          ---------------------------忘記密碼---------------------------- ---------------------------------------->
                         
         </form>
<!--          <button id="addCrs1">忘記密碼</button> -->
				
<!-- 				-header- -->
<!-- 				<form METHOD="post" ACTION="crs.do" name="form1" -->
<!-- 				enctype="multipart/form-data" id="lightBox" style="display:none"> -->
<%-- 				<jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" /> --%>
<!-- 				<h1>忘記會員密碼了嗎^^?<input type="button" id="closeCrs" value="X"/></h1> -->
		
	
<!-- 				<div class="contentform"> -->
<!-- 					<h2>請輸入要寄送密碼的email信箱</h2> -->
<!-- 						<input type="text" name="mem_useremail" class="email" placeholder="email" required="" > -->
					
<!-- 				<br> <input type="hidden" class="bouton-contact" name="action" -->
<!-- 					value="insert"> -->
<!-- 					 <input type="submit" class="bouton-contact" -->
<!-- 					value="確定送出"> -->
<!-- 				</div> -->
				
<!-- 	</form> -->
<script type="text/javascript">
document.getElementById("addCrs1").addEventListener('click', function(){
		
		document.getElementById("lightBox").style.display="block";
		document.getElementById("wrapper").style.height="780px";
		
	},false);
	
document.getElementById("closeCrs").addEventListener('click', function(){
		
		document.getElementById("lightBox").style.display="none";
		document.getElementById("wrapper").style.height="780px";
		
	},false);
</script>

<!-- ============================================================================================================================================================= -->

         <form action="<%=request.getContextPath()%>/coa.do" method="post">
         	<div id="Paris" class="tabcontent">
            	<h3>COACH</h3>										
							<input type="text" name="coa_useremail" class="email t1" placeholder="教練帳號" required="" value="<%= (coaVO==null)? "" : coaVO.getCoa_email()%>" id="coa_email">
							<input type="password" name="coa_userpsw" class="t1" placeholder="    教練密碼" required="" value="<%= (coaVO==null)? "" : coaVO.getCoa_psw()%>" style="font-size:14px;" id="coa_psw">
							<br>
							<br>
							<input type="hidden" name="action" value="login">
								<input type="submit" class="t1 is" value="登入" style="margin-bottom:25px;">
								<ul>
							<li class="btn-li" style="list-style: none;">
								<a href="<%=request.getContextPath()%>/front_end/coa/Registrator_Coa.jsp">
									<figure class="figure-apollo"style="font-family: 'ggyy'">
										<b>Register</b><br>
										<input type="submit" value="註冊" class="t1 is" style="background-color: rgba(100,100,100,0.5);">
										
									</figure>
								</a>
							</li>
						</ul>
<!-- 						<ul class="btn-ul" style="width: 100%;"> -->
<!-- 							<li class="btn-li"> -->
<!-- 								<a class="aa"href="https://www.google.com"> -->
<!-- 									<figure class="figure-apollo"style="font-family: 'ggyy'"> -->
<!-- 										<b>Retrieve password</b><br> -->
<!-- 										<input type="submit" class="t1 is" value="忘記密碼" style="background-color: rgba(100,100,100,0.5);"> -->
										
<!-- 									</figure> -->
<!-- 								</a> -->
<!-- 							</li> -->
<!-- 						</ul>	 -->
							<button type="button" class="btn btn-primary" data-toggle="modal2" data-target="#exampleModal2" data-whatever="@mdo">忘記密碼</button>
						 	<input id="b" type="button" value="神奇小按鈕" onclick="regCoa();" style="width:70px; font-size:10px;"/>
         	</div>
         </form>

<!-- ============================================================================================================================================================= -->

      </div>
   </div>
</section>


					</div>
					<div class="col-md-4 map-left wow fadeInLeft animated animated" data-wow-delay="0.4s">
						<ul>
							<li><img src="<%=request.getContextPath()%>/front_end/images/G5.png" style="width: 50px 50px"> 
							</ul>

						</div>
						<div class="clearfix"></div>
					</div>
				</div>
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
								<a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
							</div>
						</div>
					</div>
					<!--copy-->
					<!--copy-->
	<script type="text/javascript">
		var tabLinks = document.querySelectorAll(".tablinks");
		var tabContent = document.querySelectorAll(".tabcontent");

		tabLinks.forEach(function(el) {
			el.addEventListener("click", openTabs);
		});

		function openTabs(el) {
			var btnTarget = el.currentTarget;
			var country = btnTarget.dataset.country;

			tabContent.forEach(function(el) {
				el.classList.remove("active");
			});

			tabLinks.forEach(function(el) {
				el.classList.remove("active");
			});

			document.querySelector("#" + country).classList.add("active");

			btnTarget.classList.add("active");
		}
	</script>
	<!-----------------------忘記密碼(會員)-------------------------------------->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">忘記密碼了嗎^^?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
       <form action="<%=request.getContextPath()%>/mem.do" method="post">
      <div class="modal-body">       
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">請輸入你的信箱</label>
            <input type="text" class="form-control" id="recipient-name" name="mem_email"  value="<%= (memVO==null)? "" : memVO.getMem_email()%>">            
          </div>
      </div>
      <div class="modal-footer">
      <input type="submit" class="btn btn-primary" value="送出新的密碼">
        <input type="hidden"  name="action" value="forgetPsw">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>              
      </div>
       </form>
    </div>
  </div>
</div>
<!-- ----------------------------------忘記密碼(教練)------------------------ -->
	<div class="modal2 fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">忘記密碼了嗎^^?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
       <form action="<%=request.getContextPath()%>/coa.do" method="post">
      <div class="modal-body">       
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">請輸入你的信箱</label>
            <input type="text" class="form-control" id="recipient-name" name="coa_email"  value="<%= (coaVO==null)? "" : coaVO.getCoa_email()%>">            
          </div>
      </div>
      <div class="modal-footer">
      <input type="submit" class="btn btn-primary" value="送出新的密碼">
        <input type="hidden"  name="action" value="forgetPsw">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>              
      </div>
       </form>
    </div>
  </div>
</div>
	
</body>
<!-----------------------------神奇小按鈕------------------------------>
<script type="text/javascript">
					function reg(){
						document.getElementById("mem_email").value="henrygjktestmail@gmail.com";
						document.getElementById("mem_psw").value="12345678";
					}
					function regCoa(){
						document.getElementById("coa_email").value="henrygjkcoach@gmail.com";
						document.getElementById("coa_psw").value="12345678";
					}
					</script>
				</html>