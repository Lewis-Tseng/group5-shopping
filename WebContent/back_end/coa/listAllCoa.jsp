<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CoaService coaSvc = new CoaService();
    List<CoaVO> list = coaSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllCoa.jsp</title>

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
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllCoa.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/coa/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>教練編號</th>
		<th>教練姓名</th>
		<th>教練性別</th>
		<th>教練帳號</th>
		<th>教練密碼</th>
		<th>專長</th>
		<th>證照</th>
		<th>教練照片</th>
		<th>教練狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="coaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coaVO.coa_id}</td>
			<td>${coaVO.coa_name}</td>
			<td>${coaVO.coa_gender}</td>
			<td>${coaVO.coa_email}</td>
			<td>${coaVO.coa_psw}</td>
			<td>${coaVO.expert}</td>
			<td><img src="<%=request.getContextPath()%>/DBGifReaderCoach?coa_id=${coaVO.coa_id}"/></td>
			<td><img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}"/></td>
			<td>${coaVO.coa_sta}</td>
			
	<%-- 		<td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】
                    </c:if>
                </c:forEach>
			</td>   --%>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coa_id"  value="${coaVO.coa_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="coa_id"  value="${coaVO.coa_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>