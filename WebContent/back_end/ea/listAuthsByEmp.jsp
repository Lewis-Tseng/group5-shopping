<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.authority.model.*"%>

<jsp:useBean id="eaSvc" scope="page" class="com.emp_auth.model.EaService" />
<jsp:useBean id="admSvc" scope="page" class="com.administrator.model.AdmService" />
<jsp:useBean id="authSvc" scope="page" class="com.authority.model.AuthService" />
<jsp:useBean id="admVO" scope="page" class="com.administrator.model.AdmVO" />


<html>
<head><title>部門員工 - listEmps_ByDeptno.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-2">
	<tr><td>
		 <h3>部門員工 - listAuths_ByEmp_id.jsp</h3>
		 <h4><a href="select_page3.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工權限</th>
	</tr>
	
	<c:forEach var="eaVO" items="${listAuths_ByEmp}" >
		<tr>
		    <td>${admVO.emp_id }</td>
			<td>${admSvc.getOneAdm(eaVO.emp_id).emp_name}</td>
			<td>${authSvc.getOneAuth(eaVO.auth_id).auth_name}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ea/ea.do" style="margin-bottom: 0px;">
			  
			    <input type="hidden" name="auth_id"      value="${eaVO.auth_id}">
			    <input type="hidden" name="emp_id"      value="${eaVO.emp_id}">
			    
			     <input type="hidden" name="action"	value="getAuths_For_Display"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ea/ea.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			   <input type="hidden" name="auth_id"      value="${eaVO.auth_id}">
			    <input type="hidden" name="emp_id"      value="${eaVO.emp_id}">
			    
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    </FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>