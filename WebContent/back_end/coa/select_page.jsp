<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Coa: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Coa: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Mem: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/coa/listAllCoa.jsp'>List</a> all Mems.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" >
        <b>��J�нm�s�� (�pCO00001):</b>
        <input type="text" name="coa_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="coaSvc" scope="page" class="com.coach.model.CoaService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" >
       <b>��ܱнm�s��:</b>
       <select size="1" name="coa_id">
         <c:forEach var="coaVO" items="${coaSvc.all}" > 
          <option value="${coaVO.coa_id}">${coaVO.coa_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coa/coa.do" >
       <b>��ܱнm�m�W:</b>
       <select size="1" name="coa_id">
         <c:forEach var="coaVO" items="${coaSvc.all}" > 
          <option value="${coaVO.coa_id}">${coaVO.coa_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�нm�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/coa/addCoa.jsp'>Add</a> a new Coa.</li>
</ul>

</body>
</html>