<%@page import="com.forum_message.model.Forum_messageVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<%
	Forum_messageVO forum_messageVO = (Forum_messageVO) request.getAttribute("forum_messageVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章新增 - addForum.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
td>img{
	width:200px
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章新增 - addForum.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front_end/forum_message/listAllForum_message.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="forum_message.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="mem_id" size="45"
					value="<%=(forum_messageVO == null) ? "ME00002" : forum_messageVO.getMem_id()%>" /></td>
			</tr>
			
			<tr>
				<td>文章</td>
				<td><select size="1" name="forum_id">
					<c:forEach var="forumVO" items="${forumSvc.all}">
						<option value="${forumVO.forum_id}" ${(forum_messageVO.forum_id==forumVO.forum_id)? 'selected':'' }>${forumVO.forum_title}
					</c:forEach>
				   </select></td>
			</tr>
			<tr>
				<td>留言內容:</td>
				<td><input type="TEXT" name="forum_msg_info" size="45"
					value="<%=(forum_messageVO == null) ? "100" : forum_messageVO.getForum_msg_info()%>" /></td>
			</tr>
			<tr>
				<td>留言圖片:</td>
				<td><input type="file" name="forum_msg_pic" size="45" onchange="readURL(this)" targetID="preview"
					value="<%=(forum_messageVO == null) ? "" : forum_messageVO.getForum_msg_pic()%>" /><br>
					<img id="preview" src="images/selectImg.png" /></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增"></FORM>
		
	
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

</html>