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
<jsp:useBean id="forum_classSvc2" scope="page" class="com.forum_class.model.Forum_classService" />
<jsp:useBean id="memSvc2" scope="page" class="com.mem.model.MemService" />

<%
	ForumService forumSvc = new ForumService();
    List<ForumVO> list = forumSvc.getAll();
    pageContext.setAttribute("list",list);
    
	Forum_classService forum_classSvc = new Forum_classService();
    List<Forum_classVO> list2 = forum_classSvc.getAll();
    pageContext.setAttribute("list2",list2);
    
%>


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
					<h2 style="color: rgb(50,50,50)"><b>文章討論區</b></h2>
				</div>
			</div>
			<!---banner--->
	<h3 class="b1" style="margin-left: 20%; margin-bottom: 20px">
		<c:forEach var="forum_classVO" items="${list2}">
			<span class="label label-default">${forum_classVO.forum_cls_nam}</span>
		</c:forEach>
	</h3>


	<!---class--->
	<div class="class">
<!-- 		<div class="col-md-66"> -->
<!-- 			<nav> -->
<!-- 				<ul class="pagination pagination-lg"> -->
<!-- 					<li><a href="#" aria-label="Previous"><span -->
<!-- 							aria-hidden="true">«</span></a></li> -->
<!-- 					<li><a href="#">1</a></li> -->
<!-- 					<li><a href="#">2</a></li> -->
<!-- 					<li><a href="#">3</a></li> -->
<!-- 					<li><a href="#">4</a></li> -->
<!-- 					<li><a href="#">5</a></li> -->
<!-- 					<li><a href="#">6</a></li> -->
<!-- 					<li><a href="#">7</a></li> -->
<!-- 					<li><a href="#">8</a></li> -->
<!-- 					<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li> -->
<!-- 				</ul> -->
<!-- 			</nav> -->
<!-- 		</div> -->
		<div class="container">
			<div class="class-grids w3l">
				<div class="col-md-8 class-grid wow fadeInLeft animated animated"
					data-wow-delay="0.4s">
					<div class="class-top">
<!-- 文章列表----------------------------------------- -->
						<c:forEach var="forumVO" items="${list}">
						<c:if test="${forumVO.forum_stat==1}">
							<div class="col-md-6 class-left">
								<div class="class-text  hvr-bounce-to-bottom">
									<h4>
                               <!--文章標題 -->
										<b>${forumVO.forum_title}</b>
									</h4>
									<p>${fn:substring(forumVO.forum_info, 0, 130)}. . .</p>
									<h3 class="b3">
										<a><span class="label label-danger">作者:  ${memSvc2.getOneMem(forumVO.mem_id).mem_name}</span></a> 
										<a><span class="label label-success">${forum_classSvc2.getOneForum_class(forumVO.forum_cls_id).forum_cls_nam}</span></a> 
										<a><span class="label label-warning"><fmt:formatDate value="${forumVO.forum_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span></a>
									</h3>
									<h3 class="b3">
										<a><span class="label label-warning">瀏覽次數:  ${forumVO.forum_hit}</span></a>  
										<a><span class="label label-default"><img src="<%=request.getContextPath()%>/front_end/forum/images/thumb_up.png"> ${forumVO.forum_like}</span></a> 
										<a><span class="label label-default"><img src="<%=request.getContextPath()%>/front_end/forum/images/thumb_down.png"> ${forumVO.forum_dislike}</span></a>
									</h3>
									<div class="alert alert-warning" role="alert"     style="background-color: rgba(112,173,239);border-color: rgba(112,173,239);">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/forum/forum.do" style="margin-bottom: 0px;">
											<input type="submit" value="繼續閱讀..." style="
																						    background: rgba(0,0,0,0);
																						    border: none;
																						    color: white;
																						    
																						">
											<input type="hidden" name="forum_id" value="${forumVO.forum_id}">
											<input type="hidden" name="mem_id" value="${memVO.mem_id}">
											<input type="hidden" name="action" value="getOne_For_DisplaylistMsg_byForum">
										</FORM>
									</div>
								</div>
							</div>
						</c:if>
						</c:forEach>

						<div class="clearfix"></div>
					</div>
					<div class="col-md-66">
						<nav>
							<ul class="pagination pagination-lg">
								<li><a href="#" aria-label="Previous"><span
										aria-hidden="true">«</span></a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">6</a></li>
								<li><a href="#">7</a></li>
								<li><a href="#">8</a></li>
								<li><a href="#" aria-label="Next"><span
										aria-hidden="true">»</span></a></li>
							</ul>
						</nav>
					</div>

				</div>
				<div class="col-md-4 class-grid1 wow fadeInRight animated animated"
					data-wow-delay="0.4s">
					<div class="recent-top w3l">
						<!-- 杜杜杜 -->
						<h4>討論區功能</h4>
						<div class="recent-class">
							<div class="recent-left">
								<img
									src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png"
									class="img-responsive" alt="" />
							</div>
							<div class="recent-right">
<!-- ----------------------創建文章按鈕---------------- -->
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addForumModal" >創建文章</button>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="recent-class">
							<div class="recent-left">
								<img
									src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png"
									class="img-responsive" alt="" />
							</div>
							<div class="recent-right">
<!-- -----------------------------------我的文章按鈕 -->
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myForumModal" >我的文章</button>
							</div>
							<div class="clearfix"></div>
						</div>
								<div class="recent-class">
									<div class="recent-left">
									<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" class="img-responsive" alt=""/>
									</div>
								<div class="recent-right">
	<!-- -----------------------------------我的收藏按鈕 -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#favoriteModal" >我的收藏</button>
								</div>
									<div class="clearfix"></div>
								</div>
								
								
					<div class="recent-class">
									<div class="recent-left">
									<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" class="img-responsive" alt=""/>
									</div>
								<div class="recent-right">
	<!-- -----------------------------------健身房按鈕 -->
					<form action="forum.do" method="POST">
								<input type="hidden" name="action" value="gymNumber">
									<button type="submit" class="btn btn-primary">國民運動中心</button>
					</form> 
								</div>
									<div class="clearfix"></div>
					</div>
								
								
					</div>
					<!-- /杜杜杜 -->
					<div class="register">
						<h4>文章搜尋</h4>
						<form action="#" method="post">
							<input type="text" name="name" placeholder="請輸入關鍵字" required="">

							<input type="submit" value="開始搜">
						</form>
					</div>

				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!---class--->
<!---footer--->
		<div class="footer-section">
			<div class="container">
			<div class="footer-grids">
				<div class="col-md-3 footer-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
					<h4>About</h4>
					<p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy hendrerit mauris. Praesent vestibulummolestie lacus.</p>
				</div>
				<div class="col-md-3 footer-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
					<h4>Categories</h4>
					<ul>
						<li>Beauty</li>
						<li>Diet & Fitness</li>
						<li>Lifestyle</li>
						<li>Help Desk</li>
						<li>Pregnancy</li>
						<li>Performance Metrics</li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
				<h4>Work</h4>
					<ul>
						<li>Customer Support</li>
						<li>Platinum Support</li>
						<li>Gold Support</li>
						<li>Training</li>
						<li>Workshops</li>
						<li>Online Training</li>
					</ul>
				</div>
				<div class="col-md-3 footer-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
					<h4>Contact</h4>
					<p>7801 Marmora Road</p>
					<p>Glasgow, DO5 98GR.</p>
					<p>Freephone: +1 800 558 8990</p>
					<p>Telephone: +1 659 803 9035</p>
					<p>FAX: + 1 314 889 9898</p>
					<a href="mailto:example@mail.com"> example@mail.com</a>
				</div>
				<div class="clearfix"></div>
			</div>
			</div>
		</div>	
	<!---footer--->
	<!--copy-->
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
	
<!-- 創建文章按鈕談出的畫面 --------------------------------------------------------------------->
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<%
	ForumVO forum_addVO = (ForumVO) request.getAttribute("forum_addVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<div class="modal fade" id="addForumModal" tabindex="-1" role="dialog" aria-labelledby="addForum" aria-hidden="true">
  <div class="modal-dialog" role="document" style="width:1000px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
             <h5 class="modal-title" id="addForum"> 您好! <b>${memVO.mem_name}</b> 您正在編輯文章</h5>
      </div>
      <div class="modal-body">
       		<form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
						<div class="form-group">
							<label for="title">文章標題</label> 
							<input type="text" class="form-control" name="forum_title" 
								value="<%=(forum_addVO == null) ? "" : forum_addVO.getForum_title()%>">
						</div>
						<div class="form-group">
							<label for="select">文章分類</label> 
								<select class="form-control" name="forum_cls_id">
									<c:forEach var="forum_classVO" items="${forum_classSvc2.all}">
										<option value="${forum_classVO.forum_cls_id}" ${(forumVO.forum_cls_id==forum_classVO.forum_cls_id)? 'selected':'' }>${forum_classVO.forum_cls_nam}
									</c:forEach>
								</select>
						</div>
						<div class="form-group" id=ckeditor >
							<label for="exampleFormControlTextarea1">文章內容</label>
							<textarea class="form-control" name="forum_info"></textarea>
								<script>
                        			CKEDITOR.replace('forum_info');
              					</script>
						</div>
						<div class="form-group">
							<input type="file" name="forum_pic" size="45" onchange="readURL(this)" targetID="preview" 
								value="<%=(forum_addVO == null) ? "" :forum_addVO.getForum_pic()%>" ><br>
							<img id="preview" src="images/selectImg.png" width="550px" />
						</div>
						
						    <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						   	<input type="hidden" name="mem_id" value="${memVO.mem_id}">
						   	<input type="hidden" name="action" value="insert">
        					<button type="submit" class="btn btn-primary" >送出文章</button>
			</form>
      </div>
      <div class="modal-footer">
     
      </div>
    </div>
  </div>
</div>	
<!-- 我的文章 彈出按鈕---------------------------------------------------------------------------------- -->
<div class="modal fade" id="myForumModal" tabindex="-1" role="dialog" aria-labelledby="myForum" aria-hidden="true" >
  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
             <h5 class="modal-title" id="myForum"> <b>${memVO.mem_name} 您好! 以下是你的文章:</b></h5>
      </div>
      <div class="modal-body">
			      		
		  <table class="table table-dark">	      		
				  <thead>
				    <tr>
				      <th scope="col"></th>
				      <th scope="col">文章標題</th>
				      <th scope="col">文章瀏覽數</th>
				      <th scope="col">文章按讚數</th>
				      <th scope="col">文章倒讚數</th>
				 
				    </tr>
				  </thead>
				  <tbody>
				  	<% int i=1; %>
					 <c:forEach var="forumVO" items="${list}">
					 <c:if test="${forumVO.mem_id == memVO.mem_id }">
					 <c:if test="${forumVO.forum_stat == 1 }">
					    <tr>
					      <th scope="row" class="myForum"><%=i%></th>
					      								  <%i++ ;%>
					      <td>
						<a href="<%=request.getContextPath()%>/front_end/forum/forum.do?forum_id=${forumVO.forum_id}&action=getOne_For_DisplaylistMsg_byForum&mem_id=${memVO.mem_id}">${forumVO.forum_title}</a>	
					     </td>
					      <td>${forumVO.forum_hit}</td>
					      <td>${forumVO.forum_like}</td>
					      <td>${forumVO.forum_dislike}</td>
					    </tr>
					 </c:if>   
					 </c:if>
					 </c:forEach>
					 
				 </tbody>
			</table>
      
      </div>
      <div class="modal-footer">
      
      
     
      </div>
    </div>
  </div>
</div>	

<!-- 我的收藏 跳出的modal---------------------------------------------------------------------------------- -->
<div class="modal fade" id="favoriteModal" tabindex="-1" role="dialog" aria-labelledby="myForum" aria-hidden="true" >
  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
             <h5 class="modal-title" id="myForum"> <b>${memVO.mem_name} 您好! 以下是你的收藏:</b></h5>
      </div>
      <div class="modal-body">
			      		
		  <table class="table table-dark">	      		
				  <thead>
				    <tr>
				      <th scope="col"></th>
				      <th scope="col">文章標題</th>
				      <th scope="col">文章瀏覽數</th>
				      <th scope="col">文章按讚數</th>
				      <th scope="col">文章倒讚數</th>
				    </tr>
				  </thead>
				  <tbody>
			   <%  Jedis jedis = new Jedis("localhost", 6379);
						try {
							List<String> list3 = jedis.lrange( "favoriteForum:"+memVO.getMem_id(), 0, -1 ); 
					
			     		int j=1; 
			     		
							for(String x: list3){ 
							 ForumVO forumVO2 = forumSvc.getOneForum(x);
						 	 String who = forumVO2.getMem_id();
							 MemVO memVO2 = memSvc2.getOneMem(who);
							 System.out.println(who);
				 %> 
					    <tr>
					      <th scope="row" class="myForum"><%=j%></th>
					      <td>							  <%j++ ;%>
						 <a href="<%=request.getContextPath()%>/front_end/forum/forum.do?forum_id=<%=forumVO2.getForum_id()%>&action=getOne_For_DisplaylistMsg_byForum&mem_id=${memVO.mem_id}"><%=forumVO2.getForum_title()%></a>	
					     </td>
					      <td><%=memVO2.getMem_name()%></td>
					      <td><%=forumVO2.getForum_hit()%></td>
					      <td><%=forumVO2.getForum_like()%></td>
					      <td><%=forumVO2.getForum_dislike()%></td>
					      <form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
					      	<input type="hidden" name="mem_id" value="${memVO.mem_id}">
						   	<input type="hidden" name="action" value="deleteFavorite2">
						   	<input type="hidden" name="forum_id" value="<%=forumVO2.getForum_id()%>">
					      <td><button type="submit" class="btn btn-primary" >取消收藏</button></td>
					      </form>
					    </tr>
							<%}
						}finally {
							if (jedis != null) {
								jedis.close();
							}
						}%>		
				
				 </tbody>
			</table>
      
      </div>
      <div class="modal-footer">
      
      
     
      </div>
    </div>
  </div>
</div>		

<!------------------------- 健身房人數------------------------------------- -->
<!-- <div class="modal fade" id="gymModal" tabindex="-1" role="dialog" aria-labelledby="myForum" aria-hidden="true" > -->
<!--   <div class="modal-dialog" role="document" style="width:1000px;"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
<%--              <h5 class="modal-title" id="myForum"> <b>${memVO.mem_name} 您好! 以下是你的文章:</b></h5> --%>
<!--       </div> -->
<!--       <div class="modal-body"> -->
			      		
<!-- 		  <table class="table table-dark">	      		 -->
<!-- 				  <thead> -->
<!-- 				    <tr> -->
<!-- 				      <th scope="col"></th> -->
<!-- 				      <th scope="col">運動中心</th> -->
<!-- 				      <th scope="col">健身房人數</th> -->
<!-- 				      <th scope="col">游泳池人數</th> -->
<!-- 				      <th scope="col">連結</th> -->
				 
<!-- 				    </tr> -->
<!-- 				  </thead> -->
<!-- 				  <tbody> -->
<%-- 				  	<% int k=1; %> --%>
<!-- 					    <tr> -->
<%-- 					      <th scope="row" class="myForum"><%=k%></th> --%>
<%-- 					      								  <%k++ ;%> --%>
<!-- 					      <td></td> -->
<!-- 					      <td></td> -->
<!-- 					      <td></td> -->
<!-- 					      <td></td> -->
<!-- 					    </tr> -->
					 
<!-- 				 </tbody> -->
<!-- 			</table> -->
      
<!--       </div> -->
<!--       <div class="modal-footer"> -->
      
      
     
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div>	 -->

</body>

<!-- 創建文章彈出的按鈕JS--------------------------------------------------------- -->
<script type="text/javascript">
// $('#addForumModal').on('show.bs.modal', function (event) {
//   var button = $(event.relatedTarget) // Button that triggered the modal
//   var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//   var modal = $(this)
//   modal.find('.modal-title').text('New message to ' + recipient)
//   modal.find('.modal-body input').val(recipient)
// })
</script>
<!-- 上傳時 圖片即時預覽 -->
<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var imageTagID = input.getAttribute("targetID");
			var reader = new FileReader();
			
			reader.onload = function(e){
				var img = document.getElementById(imageTagID);
				img.src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);		
		}
	}
</script>

</html>