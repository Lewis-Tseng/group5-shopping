<%@page import="com.coach.model.CoaVO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page import="com.forum_class.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.forum_message.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
	
	ForumService forumSvc = new ForumService();
	List<ForumVO> list = forumSvc.getAll();
	pageContext.setAttribute("list",list);
	
// 創建文章用
	ForumVO forum_addVO = (ForumVO) request.getAttribute("forum_addVO");

	Forum_classService forum_classSvc = new Forum_classService();
	List<Forum_classVO> list2 = forum_classSvc.getAll();
	pageContext.setAttribute("list2",list2);

//收藏文章
	List<String> Favorite = (List<String>)request.getAttribute("favoriteForum") ;
	int hasFavorite = 0;
	for(String x : Favorite){
		if(x.equals(forumVO.getForum_id())){
			hasFavorite=1;
		}
	}

%>

<jsp:useBean id="forum_classSvc2" scope="page" class="com.forum_class.model.Forum_classService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>文章討論區read</title>
<!---css--->
<!-- 聊天室保留 -------------------------------------------------------------------------------------------------------->
<%-- <link href="<%=request.getContextPath()%>/front_end/dudu/css/chatroom.css" rel='stylesheet' type='text/css'/> --%>
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
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
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
				<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s" >
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
<!---banner--->
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
					<h2 style="color: rgb(50,50,50)"><b>文章討論區</b></h2>
				</div>
			</div>
			<!---banner--->
<!--杜杜杜--->
	<h3 class="b1" style="margin-left: 20%; margin-bottom: 20px">
		<c:forEach var="forum_classVO" items="${list2}">
			<span class="label label-default">${forum_classVO.forum_cls_nam}</span>
		</c:forEach>
	</h3>					
<!--/杜杜杜---> 	
		<!---class--->
			<div class="class">
				<div class="col-md-66">
<!-- 						  <nav> -->
<!-- 						  <ul class="pagination pagination-lg"> -->
<!-- 							<li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li> -->
<!-- 							<li><a href="#">1</a></li> -->
<!-- 							<li><a href="#">2</a></li> -->
<!-- 							<li><a href="#">3</a></li> -->
<!-- 							<li><a href="#">4</a></li> -->
<!-- 							<li><a href="#">5</a></li> -->
<!-- 							<li><a href="#">6</a></li> -->
<!-- 							<li><a href="#">7</a></li> -->
<!-- 							<li><a href="#">8</a></li> -->
<!-- 							<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li> -->

<!-- 						  </ul> -->
<!-- 						  </nav> -->

				</div>				
				
				<div class="container">
					<div class="class-grids w3l">
						<div class="col-md-8 class-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
							<div class="class-top">
								<div class="col-md-6 class-left">
									<div class="class-text  hvr-bounce-to-bottom">
										<h4><b>${forumVO.forum_title}</b>
											<c:if test="${memVO.mem_id==forumVO.mem_id }">    
										      	<span class="dropdown" style="float:right">
													<button class="btn btn-primary dropdown-toggle" type="button" id="about-us" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
													   操作
													<span class="caret"></span>
													</button>
													<ul class="dropdown-menu" aria-labelledby="about-us" style="min-width:0px; padding: 0 0; background:none; height: 70px ">
														<li><button type="button" class="btn btn-primary edit" data-toggle="modal" data-target="#editForumModal" data-dismiss="modal">編輯修改</button></li>
														<li>
															<form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
																<input type="hidden" name="action" value="deleteForum">
																<input type="hidden" name="forum_id" value="<%=forumVO.getForum_id()%>">
																<input type="submit" class="btn btn-primary edit" value="刪除文章">
															</form>
														</li>
													</ul>
												</span>
											</c:if> 
											<c:if test="${memVO.mem_id!=forumVO.mem_id }">    
										      	<span class="dropdown" style="float:right">
													<button class="btn btn-primary dropdown-toggle" type="button" id="about-us" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
													   操作
													<span class="caret"></span>
													</button>
													<ul class="dropdown-menu" aria-labelledby="about-us" style="min-width:0px; padding: 0 0; background:none; height: 35px ">
<!-- 														<li><button type="button" class="btn btn-primary edit" data-toggle="modal" data-target="#editForumModal" data-dismiss="modal">編輯
</button></li> -->
														<li>
																<%if(hasFavorite==1){%>
															<form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
																<input type="hidden" name="action" value="deleteFavorite">
																<input type="hidden" name="forum_id" value="<%=forumVO.getForum_id()%>">
																<input type="hidden" name="mem_id" value="${memVO.mem_id}">
																<input type="submit" class="btn btn-primary edit" value="取消收藏">
															</form>
																<%}else{%>
															<form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
																<input type="hidden" name="action" value="addFavorite">
																<input type="hidden" name="forum_id" value="<%=forumVO.getForum_id()%>">
																<input type="hidden" name="mem_id" value="${memVO.mem_id}">
																<input type="submit" class="btn btn-primary edit" value="收藏文章">
															</form>
																<%}%>
															
														</li>
													</ul>
												</span>
											</c:if> 
					     			   </h4>
												<h3 class="b3">
												
													<span class="label label-danger">作者:  ${memSvc.getOneMem(forumVO.mem_id).mem_name}</span>
													<span class="label label-success">${forum_classSvc2.getOneForum_class(forumVO.forum_cls_id).forum_cls_nam}</span>
													<span class="label label-info"></span>
													<span class="label label-warning" 
														<% if(request.getAttribute("hit_life")!=null){
														long hit_life = (long) request.getAttribute("hit_life");
														long hr = 60*60;
														long min = 60;		
														long xhr =  (hit_life/hr);
														long xmin = (hit_life%hr/min);
														int xhr2 = (int) xhr;
														int xmin2 = (int) xmin; %>
														data-toggle="todyaHit" title="親愛的會員您好 因為你今天已經登入過...所以這次遊覽次數不會增加  
													           將於<%=xhr2%>小時<%=xmin2%>分重置"<%}%>> 
														瀏覽次數:  ${forumVO.forum_hit}</span>
														
													<span class="label label-default"><fmt:formatDate value="${forumVO.forum_time}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
												</h3>	
													<p>${forumVO.forum_info}</p>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							
<!-- 以下為留言區塊*******************************************************************************************************-->
						<%int floor = 1;%>
				
							<div class="class-top">
								<div class="col-md-6 class-left">
									<c:forEach var="forum_messageVO" items="${listMsg_ByForum}">
										<div class="class-text2  hvr-bounce-to-bottom">
												<h3 class="b3">
													<a href="#">
														<span class="label label-default"><%=floor%>樓</span><%floor++; %>
													</a>
													<a href="#">
													<span class="label label-danger">留言者: ${memSvc.getOneMem(forum_messageVO.mem_id).mem_name}</span>
													</a>
												</h3>	
													<p>${forum_messageVO.forum_msg_info}</p>
													
													<div>
														<c:if test="${forum_messageVO.forum_msg_pic != null }">
															<img style="width:50%;height:180px; margin-top: 20px;" src="<%=request.getContextPath()%>/DBGifReaderForum?forum_msg_id=${forum_messageVO.forum_msg_id}"/>
														</c:if>
													</div>
													<a href="#">
														<span class="label label-commentTime"><fmt:formatDate value="${forum_messageVO.forum_msg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</a>
										</div>
									</c:forEach>
									 <!--我要留言 的按鈕 -->
						<div id="faq" role="tablist" aria-multiselectable="true">
							<div class="panel panel-default" style="width:96%">
								<div class="panel-heading" role="tab" id="questionOne">
									<h5 class="panel-title">
										<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-parent="#faq" href="#answerOne"
											aria-expanded="false" aria-controls="answerOne">我要留言</button>
									</h5>
								</div>
                     <!--我要留言的按鈕彈出部分 -->
                     			<%
									Forum_messageVO forum_messageVO = (Forum_messageVO) request.getAttribute("forum_messageVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
								%>
								<div id="answerOne" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="questionOne">
									<div class="panel-body">
													<span class="label label-default"><%=floor%>樓</span><%floor++; %>
													<span class="label label-danger">${memVO.mem_name}</span>
											<FORM METHOD="post" ACTION="forum_message.do" name="form1" enctype="multipart/form-data">
												 <span class="form-group" >
													<textarea class="form-control" rows="8" name="forum_msg_info"></textarea>
												 </span>
												 
												 <input type="file" name="forum_msg_pic" size="45" onchange="readURL(this)" targetID="preview"
													value="<%=(forum_messageVO == null) ? "" : forum_messageVO.getForum_msg_pic()%>" /><br>
												 <img id="preview" src="images/selectImg.png" style="width: 100%;height:300px">
												
												 <button type="button" class="btn btn-secondary" data-toggle="collapse" href="#answerOne">關閉</button>	
												 <input type="hidden" name="mem_id" value="${memVO.mem_id}">
						   						 <input type="hidden" name="forum_id" value="${forumVO.forum_id}">
						   						 <input type="hidden" name="action" value="insert">	
        										 <button type="submit" class="btn btn-primary" >確定送出</button>
        									</FORM>
									</div>
								</div>
								 
							</div>
						</div>	
									
								</div>
							    <div class="clearfix"></div>
							</div>
							
				
						


							
					<!-- 留言結束****************************** -->
							
							<br>
							<br>

				<div class="col-md-66">
						  <nav>
						  <ul class="pagination pagination-lg">
							<li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">6</a></li>
							<li><a href="#">7</a></li>
							<li><a href="#">8</a></li>
							<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
						  </ul>
						  </nav>
				</div>	
							
						</div>
						<div class="col-md-4 class-grid1 wow fadeInRight animated animated" data-wow-delay="0.4s">
							<div class="recent-top w3l">

								<h4>討論區功能</h4>
								<div class="recent-class">
									<div class="recent-left">
										<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" class="img-responsive" alt=""/>
									</div>
								<div class="recent-right">
	<!-- ----------------------創建文章按鈕---------------- -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addForumModal" >創建文章</button>
								</div>
									<div class="clearfix"></div>
								</div>
								<div class="recent-class">
									<div class="recent-left">
									<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" class="img-responsive" alt=""/>
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
							</div>
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

<!-- 按下修改文章彈出的modal ------------------------>

<div class="modal fade" id="editForumModal" tabindex="-1" role="dialog" aria-labelledby="addForum" aria-hidden="true">
  <div class="modal-dialog" role="document" style="width:1000px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
             <h5 class="modal-title" id="addForum"> 您好! <b>${memVO.mem_name}</b> 您正在修改文章</h5>
      </div>
      <div class="modal-body">
       		<form METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
						<div class="form-group">
							<label for="title">文章標題</label> 
							<input type="text" class="form-control" name="forum_title" 
								value="<%=(forumVO == null) ? "" : forumVO.getForum_title()%>">
						</div>
						<div class="form-group">
							<label for="select">文章分類</label> 
								<select class="form-control" name="forum_cls_id">
									<c:forEach var="forum_classVO" items="${forum_classSvc2.all}">
										<option value="${forum_classVO.forum_cls_id}" ${(forumVO.forum_cls_id==forum_classVO.forum_cls_id)? 'selected':'' }>${forum_classVO.forum_cls_nam}
									</c:forEach>
								</select>
						</div>
						<div class="form-group" id=ckeditor>
							<label for="exampleFormControlTextarea1">文章內容</label>
							<textarea class="form-control" name="forum_info2" >${forumVO.forum_info}</textarea>
								<script>
                        			CKEDITOR.replace('forum_info2');
              					</script>
						</div>
						<div class="form-group">
							<input type="file" name="forum_pic" size="45" onchange="readURL(this)" targetID="preview" 
								value="<%=(forumVO == null) ? "" :forumVO.getForum_pic()%>" ><br>
							<img id="preview" src="images/selectImg.png" width="550px" />
						</div>
						
						    <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						    <input type="hidden" name="mem_id" value="${memVO.mem_id}">
						   	<input type="hidden" name="forum_id" value="<%=forumVO.getForum_id()%>">
						   	<input type="hidden" name="action" value="update">
        					<button type="submit" class="btn btn-primary" >修改完成</button>
			</form>
      </div>
      <div class="modal-footer">
     
      </div>
    </div>
  </div>
</div>	

<!-- 創建文章按鈕談出的畫面 --------------------------------------------------------------------->


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
										<option value="${forum_classVO.forum_cls_id}" ${(forum_addVO.forum_cls_id==forum_classVO.forum_cls_id)? 'selected':'' }>${forum_classVO.forum_cls_nam}
									</c:forEach>
								</select>
						</div>
						<div class="form-group" id=ckeditor>
							<label for="exampleFormControlTextarea1">文章內容</label>
							<textarea class="form-control" name="forum_info"></textarea>
								<script>
                        			CKEDITOR.replace('forum_info');
              					</script>
						</div>
						<div class="form-group">
							<input type="file" name="forum_pic" size="45" onchange="readURL(this)" targetID="preview" 
								value="<%=(forum_addVO == null)? "" :forum_addVO.getForum_pic()%>" ><br>
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
					      <td>							  <%i++ ;%>
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
	
<!-- 我的收藏 modal彈出畫面---------------------------------------------------------------------------------- -->
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
				      <th scope="col">作者</th>
				      <th scope="col">文章瀏覽數</th>
				      <th scope="col">文章按讚數</th>
				      <th scope="col">文章倒讚數</th>
				 	  <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  <% int j=1; %>
				 <%for(String x: Favorite){ 
					 ForumVO forumVO2 = forumSvc.getOneForum(x);
				 	 String who = forumVO2.getMem_id();
					 MemVO memVO2 = memSvc.getOneMem(who);
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
						   	<input type="hidden" name="action" value="deleteFavorite">
						   	<input type="hidden" name="forum_id" value="${forumVO.forum_id}">
					      <td><button type="submit" class="btn btn-primary" >取消收藏</button></td>
					      </form>
					    </tr>
				<%}%>	    
					 
				 </tbody>
			</table>
      
      </div>
      <div class="modal-footer">
      
      
     
      </div>
    </div>
  </div>
</div>		
<!-- 聊天室區塊 保留----------------------------------------------------------------------------------- -->
<!-- 	<div id="live-chat"> -->
<!-- 		<header class="clearfix"> -->
<!-- 			<a href="#" class="chat-close">x</a> -->
<!-- 			<h4>阿吉</h4> -->
<!-- 			<span class="chat-message-counter">3</span> -->
<!-- 		</header> -->
<!-- 		<div class="chat"> -->
<!-- 			<div class="chat-history"> -->
<!-- 				<div class="chat-message clearfix"> -->
<%-- 					<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" alt="" width="32" height="32"> --%>
<!-- 					<div class="chat-message-content clearfix"> -->
<!-- 						<span class="chat-time">13:35</span> -->
<!-- 						<h5>杜杜</h5> -->
<!-- 						<p>刻板面怎麼這麼累= =</p> -->
<!-- 					</div> end chat-message-content -->
<!-- 				</div> end chat-message -->
<!-- 				<hr> -->
<!-- 				<div class="chat-message clearfix"> -->
<%-- 					<img src="<%=request.getContextPath()%>/front_end/images/G5.png" alt="" width="32" height="32"> --%>
<!-- 					<div class="chat-message-content clearfix"> -->
<!-- 						<span class="chat-time">13:37</span> -->
<!-- 						<h5>阿吉</h5> -->
<!-- 						<p>先去吃飯 不要刻了拉 吃飯皇帝大</p> -->
<!-- 					</div> end chat-message-content -->
<!-- 				</div> end chat-message -->
<!-- 				<hr> -->
<!-- 				<div class="chat-message clearfix"> -->
<%-- 					<img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" alt="" width="32" height="32"> --%>
<!-- 					<div class="chat-message-content clearfix"> -->
<!-- 						<span class="chat-time">13:38</span> -->
<!-- 						<h5>杜杜</h5> -->
<!-- 						<p>要吃什麼?? 漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎漢堡王可以嗎</p> -->
<!-- 					</div> end chat-message-content -->
<!-- 				</div> end chat-message -->
<!-- 				<hr> -->
<!-- 			</div> end chat-history -->
<!-- 			<p class="chat-feedback">回覆中..</p> -->
<!-- 			<form action="#" method="post"> -->
<!-- 				<fieldset> -->
<!-- 					<input type="text" placeholder="可以呀 那" autofocus> -->
<!-- 					<input type="hidden"> -->
<!-- 				</fieldset> -->
<!-- 			</form> -->
<!-- 		</div> end chat -->
<!-- 	</div> end live-chat -->
<!-- 	<script type="text/javascript"> -->
<!-- // 	$('#live-chat header').on('click', function() { -->
<!-- // 		$('.chat').slideToggle(300, 'swing'); -->
<!-- // 		$('.chat-message-counter').fadeToggle(300, 'swing'); -->
<!-- // 	}); -->
<!-- // 	$('.chat-close').on('click', function(e) { -->
<!-- // 		e.preventDefault(); -->
<!-- // 		$('#live-chat').fadeOut(300)}); -->
<!-- 	</script> -->
	
	
</body>

<!-- 圖片預覽 -->
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

<!-- 遊覽次數 顯示已經登入過 -->
<script type="text/javascript">
$(function(){
	$('[data-toggle="todyaHit"]').tooltip();
});
</script>

</html>