
<%@page import="com.forum_message.model.Forum_messageVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	Forum_messageVO forum_messageVO = (Forum_messageVO) request.getAttribute("forum_messageVO");
%>

<%-- 取出 對應的DeptVO物件--%>

<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>文章閱讀區 - listOneForum_message.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

td {
	padding: 5px;
	text-align: center;
}
img {
  	width:50%;
}
</style>

</head>
<body bgcolor='white'>
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front_end/forum_message/listAllForum_message.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
	<tr>
			<th>文章名稱</th>
			<th>留言者</th>
			<th>留言內容</th>
			<th>留言圖片</th>
			<th>文章留言時間</th>
			<th>留言點讚次數</th>
			<th>留言倒讚次數</th>
			<th>操作</th>
	</tr>

		<tr>
			<td>${forumSvc.getOneForum(forum_messageVO.forum_id).forum_title}</td>
			<td>${memSvc.getOneMem(forum_messageVO.mem_id).mem_name}</td>
			<td>${forum_messageVO.forum_msg_info}</td>
			<td><c:if test="${forum_messageVO.forum_msg_pic != null }"><img src="<%=request.getContextPath()%>/DBGifReaderForum?forum_msg_id=${forum_messageVO.forum_msg_id}"/>
			</c:if></td>
			<td><fmt:formatDate value="${forum_messageVO.forum_msg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${forum_messageVO.forum_msg_like}</td>
			<td>${forum_messageVO.forum_msg_dislike}</td>
		</tr>
</table>
	
</body>
<%--   <jsp:include page="/front_end/forum_message/listAllForum_message.jsp" /> --%>
</html>