<%@page import="com.mem.model.MemVO"%>
<%@page import="com.coach.model.CoaVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>健康量測</title>
		<!---css--->
		<link href="<%=request.getContextPath()%>/front_end/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<link href="<%=request.getContextPath()%>/front_end/css/style.css" rel='stylesheet' type='text/css' />
		<!---css--->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!---js--->
		<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
		<!---js--->
		<!--web-fonts-->

		<style type="text/css">
			#cal{
				margin:0px auto;
				padding: 0px;
			}
			a {
				color:	#f0f0f0;
			}
			h2{	
				font-family: test;
				color: #d3a4ff; 
				text-align: center;
			}

			#health p{
				font-family: test;
				text-align: center;
				font-size: 25px;
				color: #ffffff;
			}
			#calculation1{
				font-family: test;
				font-size: 30px;
				color: #00BBFF	;
				border-color: #ffffff;
				text-align: center;

			}

			@font-face{
				font-family: 'test'; 
				src:url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
			}
			#navbar>ul>li>.nav-in{
				font-family: test;
			} 
			body{

				font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif; 

			} 
			table{
				margin: 0px auto;
			}
			#footer{
				width:auto;
				height:80px;
				text-align: center;
				font-size: 18px;
				color: #00BBFF;
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
    
   
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}"> 
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
<!-- ****************************************頭像*************************************************** -->
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
				<h2 style="color: rgb(0,20,0)" ><b style="font-family: test;">健康量測</b></h2>
			</div>
		</div>
		<!---about-->
		<h2>我的理想體重以及熱量</h2>
		<div id="health">
			<p>人體一天所需要的熱量怎麼計算?<br>
				熱量消耗的途徑主要有三個部分：<br>
				1. 基礎代謝率：約佔了人體總熱量消耗的65~70%,.<br>
				2. 身體活動量：約佔總熱量消耗的15~30%<br>
				3. 食物的攝食產熱效應：佔的比例最少約10%<br>
			三者比例大致已固定,搭配飲食計畫和適當的運動計畫，成效更加理想。</p>
		</div>
		<form id="calculation1">
			 <tr> 
					<td  class="Contents">性別： 
						<select id="gender" name="Gender">
							<option value="M">男</option>
							<option value="F">女</option>
						</select></td>
					</tr>
					<table >
						<tbody >
							<tr>
								<td><label for="Your Height">請輸入你的身高</label></td>
								<td>  <input type="text" id="height" name="height" title="Please provide your Height.">公分</td>
							</tr>
							<tr>
								<td><label for="Your Weight">請輸入你的體重</label></td>
								<td><input type="text" id="weight" name="weight" title="Please provide also your Weight.">公斤</td>
							</tr>
							<tr>
								<td><label for="age">請輸入你的年齡</label></td>
								<td style="text-align: left;"><input type="text" id="age" name="age" title="Please provide also your age.">歲</td>
							</tr>
							<tr><td>
								<div id="button" style="font-size: 0;">
									<input type="button" name="calculation" id="cal" value="計算" style="font-size: 25px;">
									<input type="button" id="clear" name="clear" value="清除" style="font-size:25px;">
								</div>
							</td></tr>
							<tr>
								<td> <label for="weight_ok">理想體重</label></td>
								<td><input type="text" id="weight_ok" name="weight_ok" title="weight_ok"></td>
							</tr> 	
							<tr>
								<td> <label for="weight_range">理想體重範圍</label></td>
								<td> <input type="text" id="weight_range" name="weight_range" title="weight_range"></td>
							</tr>
							<tr>
								<td><label for="bmi">您的BMI</label></td>
								<td><input type="text" id="bmi" name="bmi"></td>
							</tr>
							<tr>
								<td> <label for="weight_status">目前體重狀態</label></td>
								<td> <input type="text" id="weight_status" name="weight_status" title="weight_status"></td>
							</tr>
							<tr>
								<td> <label for="bmr">你的基礎代謝率</label></td>
								<td> <input type="text" id="bmr" name="bmr" title="bmr"></td>
							</tr>	
						</tbody>
					</table>
					
			</form>

			<script type="text/javascript">
				$("#clear").click(function(){
					$(":text").val("");
				});

				$("#cal").click(function(){
					var Msg="";
					var s_index=0;
					var Gender="";
					var h=$("#height").val();
					var w=$("#weight").val();
					var age=$("#age").val();
					if(""== h  || h === '0'  ){
						Error();
					}else if(!Is_number(h) ){
						Error();
					}
					else if(h!=""){
						var Gender =$("#gender option:selected").val();
						if(Gender=="M"){
							var man_ok=(h-80)*0.7;
							console.log(man_ok);
							$("#weight_ok").val(man_ok);
							var weight_range1 = (Math.round((man_ok * 0.9)*100) /100) ; 
							var weight_range2 = (Math.round((man_ok* 1.1)*100) /100);
							var bmr=(13.7*w)+(5.0*h)-(6.8*age)+66;
							$("#weight_range").val(weight_range1+ " ~ " + weight_range2+"KG");
							$("#bmr").val(bmr+"大卡");
						}else{
							var female_ok=(h-70)*0.6;
							console.log(female_ok);
							$("#weight_ok").val(female_ok);
							var weight_range1 = (Math.round((female_ok * 0.9)*100) /100) ; 
							var weight_range2 = (Math.round((female_ok* 1.1)*100) /100);
							var bmr=(9.6*w)+(1.8*h)-(4.7*age)+655;
							$("#weight_range").val(weight_range1+ " ~ " + weight_range2+"KG");
							$("#bmr").val(bmr+"大卡");
						}
						var height_m=h/100;
						var bmi=w/Math.pow(height_m,2);
						console.log(bmi);
						var bmi=Math.round(bmi);
						if(bmi <18){
							$("#bmi").val(bmi);
							$("#weight_status").val("過輕");
							Join();

						}else if(bmi >=24){
							$("#bmi").val(bmi);
							$("#weight_status").val("過重");
							Join();
						}else{
							$("#bmi").val(bmi);
							$("#weight_status").val("正常");
						}

					} 			
					function Is_number(string) {
						re = /^\d*$/;
						if (re.test(string)){
							return(true);
						}else{
							return(false);}}0});
				function Join(message){
					var message=Swal.fire({
						title: '<strong><u>趕快加入我們一起做運動</u></strong>',
						type: 'success',
						html:
						'<b>加入會員</b> ' +
						'<a href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp">links</a> ' ,
						showCloseButton: true,
						showCancelButton: true,
						focusConfirm: false,
						confirmButtonText:
						'<i class="fa fa-thumbs-up"></i><a href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp">加入我們</a>',
						confirmButtonAriaLabel: 'Thumbs up, great!',
						cancelButtonText:
						'<i class="fa fa-thumbs-down"></i>暫時先不要',
						cancelButtonAriaLabel: 'Thumbs down'
					})
					return message;
				}
				function Error(error){
					var error=Swal.fire({
						type: 'error',
						title: 'Oops...誠實填喔',
						text: '請輸入正確格式',
						footer: '<a href>Why do I have this issue?</a>'
					})
					return error;
				}
			</script>
			<div id="footer">
				<div class="container">	
					<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期
						<a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
					</div>

				</div>
			</body>
			</html>