<%@page import="com.forum.model.ForumVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="forum_classSvc" scope="page" class="com.forum_class.model.Forum_classService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%
ForumVO forumVO =(ForumVO) request.getAttribute("forumVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<!-- ckeditor -->
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章修改 - update_Forum.jsp</title>

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
	<tr><td>
		 <h3>文章修改 - update_Forum.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${ekrrorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="forum.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>文章編號:</td>
		<td>${forumVO.forum_id}</td>
	</tr>
    <tr>
		<td>文章作者</td>
		<td>${memSvc.getOneMem(forumVO.mem_id).mem_name}</td>
	</tr>
		
		<tr>
			<td>文章類別</td>
			<td><select size="1" name="forum_cls_id">
			<c:forEach var="forum_classVO" items="${forum_classSvc.all}">
			<option value="${forum_classVO.forum_cls_id}" ${(forumVO.forum_cls_id==forum_classVO.forum_cls_id)?'selected':'' } >${forum_classVO.forum_cls_nam}
			</c:forEach>
			</select></td>
		</tr>
	<tr>
		<td>文章標題</td>
		<td><input type="TEXT" name="forum_title" size="45"	value="<%=forumVO.getForum_title()%>" /></td>
	</tr>
	<tr>
		<td>文章內容</td>
		<td><textarea name="forum_info" placeholder="${forumVO.forum_info}"></textarea>
                <script>
                        CKEDITOR.replace( 'forum_info' );
               </script></td>
      
	</tr>
	<tr>
		<td>文章圖片:</td>
		<td><input type="file" name="forum_pic" size="45" onchange="readURL(this)" targetID="preview"
					value="<%=(forumVO == null) ? "" :forumVO.getForum_pic()%>" /><br>
					<img id="preview" src="<%=request.getContextPath()%>/DBGifReaderForum?forum_id=${forumVO.forum_id}"/></td>
	</tr>


</table>
<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="forum_id" value="<%=forumVO.getForum_id()%>">
		<input type="submit" value="確認修改"></FORM>
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