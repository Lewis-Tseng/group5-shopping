<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>

<%
  CoaVO coaVO = (CoaVO) request.getAttribute("coaVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>教練資料新增 - addCoa.jsp</title>

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
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>教練資料新增 - addCoa.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/coa/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" name="form1" enctype="multipart/form-data" >
<table>
	<tr>
		<td>教練姓名:</td>
		<td><input type="TEXT" name="coa_name" size="45" 
			 value="<%= (coaVO==null)? "吳永志" : coaVO.getCoa_name()%>" /></td>
	</tr>
	<tr>
		<td>教練性別:</td>
		<td><input type="TEXT" name="coa_gender" size="45"
			 value="<%= (coaVO==null)? "MANAGER" : coaVO.getCoa_gender()%>" /></td>
	</tr>
	<tr>
		<td>教練帳號:</td>
		<td><input type="TEXT" name="coa_email" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_email()%>" /></td>
	</tr>
	<tr>
		<td>教練密碼:</td>
		<td><input type="TEXT" name="coa_psw" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_psw()%>" /></td>
	</tr>
	<tr>
		<td>專長:</td>
		<td><input type="TEXT" name="expert" size="50"
			 value="<%= (coaVO==null)? "10000" : coaVO.getExpert()%>" /></td>
	</tr>
	<tr>
		<td>證照:</td>
		<td><input type="file" name="license" 
			 value="<%= (coaVO==null)? "" : coaVO.getLicense()%>" /></td>
	</tr>
	<tr>
		<td>教練照片:</td>
		<td><input type="file" name="coa_pic" 
			 value="<%= (coaVO==null)? "" : coaVO.getCoa_pic()%>" /></td>
	</tr>
	<tr>
		<td>教練狀態:</td>
		<td><input type="TEXT" name="coa_sta" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_sta()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>




</html>