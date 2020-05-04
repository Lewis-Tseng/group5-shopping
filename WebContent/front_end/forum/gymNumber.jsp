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
					 </c:forEach>
					 
				 </tbody>
			</table>
      
      </div>
      <div class="modal-footer">
      
      
     
      </div>
    </div>
  </div>
</div>	




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