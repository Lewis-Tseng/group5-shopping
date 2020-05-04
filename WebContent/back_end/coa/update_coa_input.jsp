<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>

<%
  CoaVO coaVO = (CoaVO) request.getAttribute("coaVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�нm��ƭק� - update_coa_input.jsp</title>

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
		 <h3>�нm��ƭק� - update_coa_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/coa/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�нm�m�W:</td>
		<td><input type="TEXT" name="coa_name" size="45" 
			 value="<%= (coaVO==null)? "�d�ç�" : coaVO.getCoa_name()%>" /></td>
	</tr>
	<tr>
		<td>�нm�ʧO:</td>
		<td><input type="TEXT" name="coa_gender" size="45"
			 value="<%= (coaVO==null)? "MANAGER" : coaVO.getCoa_gender()%>" /></td>
	</tr>
	<tr>
		<td>�нm�b��:</td>
		<td><input type="TEXT" name="coa_email" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_email()%>" /></td>
	</tr>
	<tr>
		<td>�нm�K�X:</td>
		<td><input type="TEXT" name="coa_psw" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_psw()%>" /></td>
	</tr>
	<tr>
		<td>�M��:</td>
		<td><input type="TEXT" name="expert" size="50"
			 value="<%= (coaVO==null)? "10000" : coaVO.getExpert()%>" /></td>
	</tr>
	<tr>
		<td>�ҷ�:</td>
		<td><input type="file" name="license" 
			 value="<%= (coaVO==null)? "" : coaVO.getLicense()%>" /></td>
	</tr>
	<tr>
		<td>�нm�Ӥ�:</td>
		<td><input type="file" name="coa_pic" 
			 value="<%= (coaVO==null)? "" : coaVO.getCoa_pic()%>" /></td>
	</tr>
	<tr>
		<td>�нm���A:</td>
		<td><input type="TEXT" name="coa_sta" size="45"
			 value="<%= (coaVO==null)? "100" : coaVO.getCoa_sta()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="coa_id" value="<%=coaVO.getCoa_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</html>