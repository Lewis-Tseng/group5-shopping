<%@ page import="com.forum_message.model.Forum_messageVO"%>
<%@ page import="com.forum_message.model.Forum_messageService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
	Forum_messageService forum_messageSvc = new Forum_messageService();
    List<Forum_messageVO> list = forum_messageSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<html>
<head>
<title>文章留言區 - listAllForum_message.jsp</title>

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
	width: 1200px;
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
  img{
   width:50%;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有文章 - listAllForum_message.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/forum_message/listAllForum_message.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
			<th>操作</th>
	</tr>
	
	<%@ include file="pages/page1.file" %> 

	<c:forEach var="forum_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${forumSvc.getOneForum(forum_messageVO.forum_id).forum_title}</td>
			<td>${memSvc.getOneMem(forum_messageVO.mem_id).mem_name}</td>
			<td>${forum_messageVO.forum_msg_info}</td>
			<td><c:if test="${forum_messageVO.forum_msg_pic != null }"><img src="<%=request.getContextPath()%>/DBGifReaderForum?forum_msg_id=${forum_messageVO.forum_msg_id}"/>
			</c:if></td>
			<td><fmt:formatDate value="${forum_messageVO.forum_msg_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${forum_messageVO.forum_msg_like}</td>
			<td>${forum_messageVO.forum_msg_dislike}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/forum_message/forum_message.do" style="margin-bottom: 0px;">
					<input type="submit"  value="編輯留言"> 
					<input type="hidden"  name="forum_msg_id" value="${forum_messageVO.forum_msg_id}"> 
					<input type="hidden"  name="action" value="getOneMsg_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/forum_message/forum_message.do" style="margin-bottom: 0px;">
					<input type="submit"  value="刪除留言(假刪除 真下架)"> 
					<input type="hidden"  name="forum_msg_id" value="${forum_messageVO.forum_msg_id}"> 
					<input type="hidden"  name="action" value="ChangeStat">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
<h4><a href="<%=request.getContextPath()%>/front_end/forum_message/addForum_message.jsp">新增留言</a></h4>

</body>
</html>