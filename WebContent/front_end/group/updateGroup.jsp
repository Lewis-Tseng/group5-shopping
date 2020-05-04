<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gro.group.groupVO"%>

<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
	groupVO groupVO = (groupVO) request.getAttribute("groupVO");
	
	String loginMem=null;
	if(session.getAttribute("memVO")!=null){
		loginMem = memVO.getMem_name();
	};
%>

<jsp:useBean id="groclassSvc" scope="page" class="com.gro.type.groclassService" />

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
<!--//web-datetimepicker-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css_group/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/css_group/js/wow.min.js"></script>
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


		.aboutGroup{
			width: 90%;
		}
		
		h2{
			font-size: 22px;
			color: rgba(25,25,25,0.8);
			padding-bottom:6px;
			text-align: center;
			
		}

		.form-style-5{
			max-width: 70%;
			background: url("<%=request.getContextPath()%>/front_end/css_group/images/reexw.png");
			background-size: cover;
			margin: 0 auto;
			padding: 20px;
			border-radius: 8px;
			font-family: 'test';
		}
		.form-style-5 fieldset{
			border: none;
		}
		.form-style-5 legend {
			font-size: 2em;
			margin-bottom: 10px;
		}
		.form-style-5 label {
			display: block;
			margin-bottom: 8px;
		}
		.form-style-5 input[type="text"],
		.form-style-5 input[type="date"],
		.form-style-5 input[type="datetime"],
		.form-style-5 input[type="email"],
		.form-style-5 input[type="number"],
		.form-style-5 input[type="search"],
		.form-style-5 input[type="time"],
		.form-style-5 input[type="url"],
		.form-style-5 textarea,
		.form-style-5 select {
			font-family: '微軟正黑體';
			background: rgba(255,255,255,.1);
			border: none;
			border-radius: 4px;
			font-size: 18px;
			margin: 0;
			outline: 0;
			padding: 10px;
			width: 100%;
			box-sizing: border-box; 
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box; 
			background-color: #e8eeef;
			color:rgb(25,25,25);
			-webkit-box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
			box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
			margin-bottom: 30px;
		}
		.form-style-5 input[type="text"]:focus,
		.form-style-5 input[type="date"]:focus,
		.form-style-5 input[type="datetime"]:focus,
		.form-style-5 input[type="email"]:focus,
		.form-style-5 input[type="number"]:focus,
		.form-style-5 input[type="search"]:focus,
		.form-style-5 input[type="time"]:focus,
		.form-style-5 input[type="url"]:focus,
		.form-style-5 textarea:focus,
		.form-style-5 select:focus{
			background: #d2d9dd;
		}
		.form-style-5 select{
			-webkit-appearance: menulist-button;
			height:45px;
		}
		.form-style-5 .number {
			background: rgb(186,98,100);
			color: #fff;
			height: 30px;
			width: 30px;
			display: inline-block;
			font-size: 0.8em;
			margin-right: 4px;
			line-height: 30px;
			text-align: center;
			text-shadow: 0 1px 0 rgba(255,255,255,0.2);
			border-radius: 15px 15px 15px 0px;
		}

		.form-style-5 input[type="submit"],
		.form-style-5 input[type="button"],
		.custom-file-upload
		{
			position: relative;
			display: block;
			padding: 19px 39px 18px 39px;
			color: #FFF;
			margin: 0 auto;
			background: rgb(186,98,100);
			font-size: 18px;
			text-align: center;
			font-style: normal;
			width: 100%;
			border: 1px solid rgb(186,98,100);
			border-width: 1px 1px 3px;
			margin-bottom: 10px;
		}
		.form-style-5 input[type="submit"]:hover,
		.form-style-5 input[type="button"]:hover
		{
			background: rgb(186,98,100);
		}
		
		input[type="file"] {
 		   display: none;
		}		
		.banner {
			background-image:none;
			padding-bottom:0;
		}
		
		.about-section{
			padding-top:2em;
		}
		
		legend {
			color: rgba(25,25,25,0.8);
		}
		
		body {
			background:url("<%=request.getContextPath()%>/front_end/css_group/images/bgG5.png");
			background-size:cover;
			background-repeat:no-repeat;
			background-attachment:fixed;
			background-position:center;
		}
		
		h2 {
			font-family:'test';
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
					<h2 style="color: rgb(70,70,70);font-family: test,微軟正黑體;width:500px;margin:0 auto;margin-left: 30%;">修改揪團資料</h2>
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
<!-- -------------------------------------------------------------------瀏覽揪團位置-------------------------------------------- -->

							
							<div class="form-style-5">
								<c:if test="${not empty errorMsgs}">
									<font style="color: red; font-size:2em;">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red; font-size:1.7em;margin-left:20px;">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<form METHOD="post" ACTION="group.do" name="form1" enctype="multipart/form-data">
									<fieldset>
										<legend><span class="number">1</span><b>揪團資訊</b></legend>
										
										<h2>揪團名稱</h2>
										<input type="TEXT" name="gro_name" size="45" value="<%= (groupVO==null)? "=U=" : groupVO.getGro_name()%>"/>
										<h2>最少人數</h2>
										<input type="TEXT" name="gro_mnum_min" size="45"value="<%= (groupVO==null)? "2" : groupVO.getGro_mnum_min()%>"/>
										<h2>最多人數</h2>
										<input type="TEXT" name="gro_mnum_max" size="45"value="<%= (groupVO==null)? "18" : groupVO.getGro_mnum_max()%>"/> 
										
										<h2>揪團類別</h2>
										<select size="1" name="gro_cid" id="list" name="list">
											<optgroup label="">
												<c:forEach var="groupclassVO" items="${groclassSvc.all}">
													<option value="${groupclassVO.gro_cid}">${groupclassVO.gro_cname}
												</c:forEach>
											</optgroup>
										</select>
										
 										<!--圖片 -->
 										<h2>揪團LOGO</h2><br>
 										<img style="width: 50%;padding-bottom: 20px;margin-left: 25%;"id="preview_1" src="<c:if test="${groupVO.gro_logo_pic != null}">
												<%=request.getContextPath()%>/DBGifReaderGro?gro_id=${groupVO.gro_id}
											</c:if>
											<c:if test="${groupVO.gro_logo_pic == null}">
												<%=request.getContextPath()%>/front_end/css_group/images/G5_LOGO_v2.0.png
											</c:if>"/>
 										
 										<label class="custom-file-upload" style="padding-bottom: 20px;">
    										<input type="file" name="gro_logo_pic" onchange="readURL(this)" targetID="preview_1" value="<%= (groupVO==null)? "" : groupVO.getGro_logo_pic()%>"/>
   											上傳圖片
										</label>
			 										 							
			 							
<!-- 										<input type="text" name="GRO_LOC" placeholder="揪團地點"> -->
										<legend><span class="number">2</span><b>報名時間</b></legend>
										<h2>報名截止時間</h2>
										<input name="gro_sig_ftime" id="f_date2" type="text">
										
										<legend><span class="number">3</span><b>活動時間</b></legend>
										<h2>活動開始時間</h2>
										<input name="gro_stime" id="f_date3" type="text">
										<h2>活動結束時間</h2>
										<input name="gro_ftime" id="f_date4" type="text">

										<legend><span class="number">4</span><b>關於揪團</b></legend>
										<h2>揪團介紹</h2>
										<textarea name="gro_info" placeholder="揪團介紹" style="height:200px;width:100%;"><%= (groupVO==null)? "請輸入揪團介紹" : groupVO.getGro_info()%></textarea>
									</fieldset>
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="gro_id" value="<%=groupVO.getGro_id()%>">
									<input type="hidden" name="mem_id" value="<%=groupVO.getMem_id()%>">
									<input type="hidden" name="gro_mnum" value="<%=groupVO.getGro_mnum()%>">
									<input type="hidden" name="BF_url" value="/front_end">
									<input type="submit" value="送出修改" style="font-family:'test'"/>
								</form>
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
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date today = null;
  try {
	  today = groupVO.getGro_sig_stime();
   } catch (Exception e) {
	   today = new java.sql.Date(System.currentTimeMillis());
   }
%>



<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

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
<script>
        $.datetimepicker.setLocale('zh');
        
        $('#f_date2').datetimepicker({
 	       theme: 'dark',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '-1969-12-27',
//             disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//             startDate:	            '-1969-12-27',  // 起始日
            minDate:'-1969-12-27', 
            // 去除今日(不含)之前
//             maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        $('#f_date3').datetimepicker({
 	       theme: 'dark',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '-1969-12-26',
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            minDate:               '-1969-12-26', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        $('#f_date4').datetimepicker({
 	       theme: 'dark',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '-1969-12-26',
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            minDate:               '-1969-12-26', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>