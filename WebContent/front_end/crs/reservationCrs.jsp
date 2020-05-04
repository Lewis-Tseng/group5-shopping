<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.crs.model.*"%>
<%@ page import="com.crs_type.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.crs_detail.model.*"%>
<jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />
<%
	CrsService crsSvc = new CrsService();
	List<CrsVO> list = crsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
	
<% 
	CrsVO crsVO =(CrsVO) request.getAttribute("crsVO");
%>

 <%
	CrsDAO crsdao = new CrsDAO();
 %>
 

<!DOCTYPE html>
<html>
<title>Classes</title>
	<!---css--->
	<link href="<%=request.getContextPath()%>/front_end/css/bootstrap_poan.css" rel='stylesheet' type='text/css' />
	<link href="<%=request.getContextPath()%>/front_end/css/style_poan.css" rel='stylesheet' type='text/css' />
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
	<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		new WOW().init();
	</script>
	<!--//end-animate-->
<style type="text/css">
	#select {
		margin-top: 12px;
	}
	
	.custom-dropdown {
		position: relative;
		display: inline-block;
		vertical-align: middle;
		margin: 10px;
		/* demo only */
	}
	
	.custom-dropdown select {
		background-color: #60768b8f;
		color: #fff;
		font-size: inherit;
		padding: .5em;
		padding-right: 2.5em;
		border: 0;
		margin: -10px;
		margin-top: 2px;
		border-radius: 3px;
		text-indent: 0.01px;
		-webkit-appearance: button;
		/* hide default arrow in chrome OSX */
	}
	
	#formcss select {
		float: left;
	}
	
	.info-list {
		font-size: 0px;
	}
	
	.info-list div.info-card {
		display: inline-block;
		width: calc(100%/ 3);
		box-sizing: border-box;
		padding: 10px 20px;
	}
	
	.card-background {
		background-color: #FFF;
		border-radius: 10px;
		overflow: hidden;
		border: 1px solid #aaa;
	}
	
	.info-card .card-image {
		width: 100%;
		height: 200px;
		overflow: hidden;
		box-sizing: border-box;
	}
	
	.card-image img {
		width: 100%;
	}
	
	.info-card .card-text {
		padding: 10px 20px 5px 20px;
		font-size: 18px;
	}
	
	.info-card .card-text h3 {
		font-weight: bolder;
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
	
	h1 {
		color: white;
	}
	
	#reservation {
		border: 2px solid;
		border-color: white;
		width: 60%;
		text-align: center;
		margin-left: 20%;
		margin-top: 5%;
		margin-bottom: 5%;
	}
	
	#divimage {
		border: 5px solid;
		border-color: white;
		margin: 10px 10px;
		height: 600px;
	}
	
	#divimage img {
		width: 100%;
		height: 100%;
	}
	
	#content1 {
		border: 2px solid;
		border-color: white;
		margin: 10px 10px;
		font-family: "微軟正黑體";
		font-weight: bolder;
	}
	
	#content1 p {
		font-size: 20px;
		margin-top: 10px;
	}
	
	#content1 h2 {
		margin-top: 10px;
	}
	
	#left {
		float: left;
		width: 50%;
		height: 650px;
		background-color: white;
		border: 3px solid;
	}
	
	#right {
		background-color: white;
		margin-left: 32%;
		height: 650px;
		border: 3px solid;
		padding-right: 5%;
		padding-left: 5%;
		/*overflow-y: scroll;*/
	}
	
	#price {
		float: left;
		margin-top: 7.5%;
	}
	
	#price h1 {
		color: black;
	}
	
	.button {
		display: inline-block;
		position: relative;
		border-radius: 3px;
		text-decoration: none;
		padding: .5em;
		margin-bottom: 1%;
		font-size: 2em;
		font-weight: bold;
		transition: all .5s;
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
		width: 98%;
		font-family: "微軟正黑體";
	}
	
	.button:hover {
		text-shadow: 0px 0px 0px rgba(255, 255, 255, .75);
	}
	
	.button:hover:after {
		left: 100%;
		top: 100%;
		bottom: 100%;
		right: 100%;
	}
	
	.button:before {
		content: '';
		display: block;
		position: absolute;
		left: 0;
		top: 0;
		bottom: 0;
		right: 0;
		z-index: -1;
		border-radius: 5px;
		transition: all .5s;
	}
	
	.button:after {
		content: '';
		display: block;
		position: absolute;
		left: 2px;
		top: 2px;
		bottom: 2px;
		right: 2px;
		z-index: -1;
		border-radius: 5px;
		transition: all .5s;
	}
	
	[type="submit"] {
		font-family: "微軟正黑體";
		width: 100%;
		background: #CC6666;
		border-radius: 5px;
		border: 0;
		cursor: pointer;
		color: white;
		font-size: 24px;
		padding-top: 10px;
		padding-bottom: 10px;
		transition: all 0.3s;
		margin-top: -4px;
		font-weight: 700;
	}
	
	[type="submit"]:hover {
		background: #CC4949;
	}
	
	.modal-footer1 {
	    padding: 19px 20px 20px;
	    text-align: right;
	    border-top: 1px solid #e5e5e5;
	}
	
	.modal-title {
		font-family: "微軟正黑體" ;
		font-weight: bold;
	}
	
	#limit{
		float: left;
		margin-right: 15%;
	}
	
	#underRight{
    	margin-left: 36%;
    	margin-top: 3%;
	}
	
	#underRightText{
		margin-left: 30%;
		height: 77%;
		overflow-y: scroll;
	}
	
	#underRightText::-webkit-scrollbar {
	  display: none;
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
                <ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s" >
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
			<c:if test="<%=memVO.getMem_pic() !=null %>">
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
     <%}else if(coaVO!=null){ %>    <%--*************************************************************** --%>
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
			<h2 style="color: black;">課程報名</h2>
		</div>
	</div>
	<!---banner--->
	
	<!---reservation--->
	<div id="reservation">

		<div id="divimage">
			<img src="<%=request.getContextPath()%>/DBGifReaderCrs?crs_no=${crsVO.crs_no}" alt="">
		</div>
		<div id="content1">
			<div id="left">
				<h2>課程名稱:</h2>
				<p>${crsVO.crs_name}</p>
				<h2>教練:</h2>
				<p>${coaSvc.getOneCoa(crsVO.coa_id).coa_name}</p>
				<h2>課程開始日期:</h2>
				<p>${crsVO.start_date}</p>
				<h2>課程結束日期:</h2>
				<p>${crsVO.end_date}</p>
				<h2>每周上課日:</h2>
				<p><%= crsdao.weekCrsTransfer(crsVO.getWeek_crs()) %></p>
				<h2>上課時間:</h2>
				<p><fmt:formatDate value="${crsVO.start_time}" pattern="k:mm" /></p>
				<h2>下課時間:</h2>
				<p><fmt:formatDate value="${crsVO.end_time}" pattern="k:mm" /></p>
				<h2>人數上限:</h2>
				<p id="limitPeaple"> ${crsVO.limit} 人</p>
			</div>
			<div id="right">
				<div id="underRightText">
					<h2>課程內容:</h2>
					<p>${crsVO.crs_content}
					
					</p>
				</div>
				<div id="underRight">
					<div id ="limit">
						<img style="width:60px" height="70px"src="<%=request.getContextPath()%>/front_end/crs/images/people.png" />
						<p>目前報名人數 <span id="alreadyRes" style="color:red;">${crsVO.reserved}</span> 人</p>
					</div>			
					<div id="price">
						<h1>課程價格:</h1>
						<p>${crsVO.crs_fee}</p>
					</div>	
				</div>			
			</div>
		</div>
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/crs/crsDetail.do" >		
		    <input type="hidden" name="crs_name" value="${crsVO.crs_name}">
		    <input type="hidden" name="coa_id" value="${crsVO.coa_id}">
		    <input id="crs_no"  type="hidden" name="crs_no" value="${crsVO.crs_no}">
		    <input id="mem_id"  type="hidden" name="mem_id" value="${memVO.mem_id}">
		    <input id="crs_fee" type="hidden" name="crs_fee" value="${crsVO.crs_fee}">
		    <input id="mem_point" type="hidden" name="mem_point" value="${memVO.mem_point}">
			<div id="content3">
				<input id="sendRes" type="button" class="button b-blue" name="action1" value="我要報名">
				<input type="hidden" name="action" value="insert">
			</div>		
		</form>
	</div>
	<!---reservation--->
	
	<!-- 取出該會員是否已報名此課程用(開始) -->
	<%
		CrsDetailService crsDetailSvc = new CrsDetailService();
		List<CrsDetailVO> listCrsDetail = crsDetailSvc. getReserved(memVO.getMem_id());
		pageContext.setAttribute("listCrsDetail", listCrsDetail);
	%>
	
	<ul id ="listCrsDetail">
		<c:forEach var="listCrsDetail" items="${listCrsDetail}">
			<li style="display:none">${listCrsDetail.crs_no}</li>
		</c:forEach>
	</ul>
	<!-- 取出該會員是否已報名此課程用(結尾)-->

	
	<!---modalFirst--->
	<div class="modal fade" id="basicModalFirst" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">					
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h3 class="modal-title" id="myModalLabel">餘額不足，是否要前往儲值畫面?</h3>
		            </div>
					
					<div class="modal-footer1">
						<input id="goDeposit" type="submit" value="前往" > 
		            </div>		 
			</div>
		</div>
	</div>
	<!---modalFirst--->
	
	<!---modalSecond--->
	<div class="modal fade" id="basicModalSecond" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">					
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h3 class="modal-title" id="myModalLabel">已報名過此課程!</h3>
		            </div>
					
					<div class="modal-footer1">
						<input id="goDeposit" type="submit" value="關閉"  data-dismiss="modal"> 
		            </div>		 
			</div>
		</div>
	</div>
	<!---modalSecond--->
	
	<!---modalThird--->
	<div class="modal fade" id="basicModalThird" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">					
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h3 class="modal-title" id="myModalLabel">報名已額滿!</h3>
		            </div>
					
					<div class="modal-footer1">
						<input id="goDeposit" type="submit" value="關閉"  data-dismiss="modal"> 
		            </div>		 
			</div>
		</div>
	</div>
	<!---modalThird--->
	
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
	<script>
		function nofind(){
			  var img=event.srcElement;
			  img.src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png";
			  img.onerror=null; //控制不要一直觸發錯誤
		}
		
		$("#basicModalFirst").modal({show: false});
		$("#basicModalSecond").modal({show: false});
		$("#basicModalThird").modal({show: false});
		
		$("#sendRes").click(function(){		
			var memPoint = $("#mem_point").val();
			var crsFee = $("#crs_fee").val();
			var crsNo = $("#crs_no").val();
			var memId = $("#mem_id").val();
			var reserved =  crsNo + memId ;
			var reservedArray = new Array();
			var liLength = $("#listCrsDetail > li").length;
			var repeated = 0 ; //0為沒報名過
			var limit = $("#limitPeaple").text();
			var alreadyRes = $("#alreadyRes").text();
			console.log((limit).substring(1,3));
			console.log(alreadyRes);
			$("#listCrsDetail > li").each(function(){
	    		reservedArray.push($(this).text());
	    	});  	
			
			for(var i = 0; i < liLength; i++){
				if(reservedArray[i]+memId == reserved ){
					repeated = 1; //1為已報名過
				}
			}
			/*判斷是否已報名過、報名是否已滿、餘額是否足夠*/
			if(repeated == 1){
				$("#basicModalSecond").modal({show: true});
			}else if(parseInt(alreadyRes)  >=  parseInt((limit).substring(1,3))){
				$("#basicModalThird").modal({show: true});
			}else if(parseInt(memPoint)  <  parseInt(crsFee) ){
				$("#basicModalFirst").modal({show: true});
			}else{
				$("#sendRes").attr('type' , 'submit').submit();	
			}	
		});
		
		
		$("#goDeposit").click(function(){
			var url = "<%=request.getContextPath()%>/front_end/dep/MemManagePersonalDep.jsp";
			$(location).attr('href',url) ;
		});
		
		

	</script>
</body>
</html>