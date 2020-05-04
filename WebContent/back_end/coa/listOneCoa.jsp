<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.coach.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%CoaVO coaVO = (CoaVO) request.getAttribute("coaVO");%>


<html>
<head>
<title>會員資料 - listOneCoa.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - ListOneCoa.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/coa/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
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
	</tr>
</table>

</body>
</html>