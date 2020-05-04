<%@page import="com.forum.model.ForumVO"%>
<%@page import="com.forum.model.ForumService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ForumService forumSvc = new ForumService();
    List<ForumVO> list = forumSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="forum_classSvc" scope="page" class="com.forum_class.model.Forum_classService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<html>
<head>
<title>�峹�Q�װϫ�O�޲z - listAllForum.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��峹 - listAllForum.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/forum/listAllForum.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�峹�s��</th>
		<th>�峹�@��</th>
		<th>�峹���O</th>
		<th>�峹���D</th>
		<th>�峹�i�K�ɶ�</th>
		<th>�峹�s���H��</th>
		<th>�峹�I�g����</th>
		<th>�峹���g����</th>
		<th>�峹���A</th>
		<th>�ާ@</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="forumVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${forumVO.forum_id}</td>
			<td>${memSvc.getOneMem(forumVO.mem_id).mem_name}</td>
			<td>${forum_classSvc.getOneForum_class(forumVO.forum_cls_id).forum_cls_nam}</td>
			<td>${forumVO.forum_title}</td>
			<td><fmt:formatDate value="${forumVO.forum_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${forumVO.forum_like}</td>
			<td>${forumVO.forum_dislike}</td>
			<td>${forumVO.forum_hit}</td>
			<td><c:if test="${forumVO.forum_stat == '1'}">�W�[��</c:if>
				<c:if test="${forumVO.forum_stat == '0'}">�U�[</c:if>
			    </td>
 			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/forum/forum.do" style="margin-bottom: 0px;">
			     <input type="submit" value="��窱�A">
			     <input type="hidden" name="forum_id"  value="${forumVO.forum_id}">
			     <input type="hidden" name="action" value="ChangeStat">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="pages/page2.file" %>

</body>
</html>