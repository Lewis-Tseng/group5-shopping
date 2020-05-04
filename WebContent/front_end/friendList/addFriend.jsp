

<%@page import="com.friendList.model.FriendListVO"%>
<%@page import="com.friendList.model.FriendListService"%>
<%@page import="com.mem.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.*"%>
<!-- 取得會員資料 -->

<%  MemVO memVO = (MemVO) session.getAttribute("memVO");

	// 查詢誰想加我好友
	FriendListService FriendListSvc2 = new FriendListService();
    List<FriendListVO> list2 = FriendListSvc2.getMyAll(memVO.getMem_id());
    pageContext.setAttribute("list2",list2);
%>  		

<%-- <% --%>
<!-- // 	FriendListVO forum_messageSvc = new Forum_messageService(); -->
<!-- //     List<Forum_messageVO> list = forum_messageSvc.getAll(); -->
<!-- //     pageContext.setAttribute("list",list); -->
<%-- %> --%>

<%-- <jsp:useBean id="friendListSvc" scope="page" class="com.friendList.model.FriendListService" /> --%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>

 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
 <script  src="./script.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>好友列表</title>

<style>
table {
	width: 1000px;
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

<body onload="connect();" onunload="disconnect();">

<div>登入者: ${memVO.mem_name}</div>

<div>
	<table>
	
		<c:forEach var="memAllVO" items="${memSvc.all}">
		<tr>
			<td>${memAllVO.mem_id }</td>
			<td>${memAllVO.mem_name}</td>
			<td>
				<form METHOD="post" ACTION="friendList.do" name="form1" enctype="multipart/form-data">
					
					<input type="hidden" name="action" value="add">
					<input type="hidden" name="my_id" value="${memVO.mem_id }">
					<input type="hidden" name="his_id"  value="${memAllVO.mem_id }">
					<input type="submit" id="addFriend" class="${memAllVO.mem_id}" value="加入好友" >
				</form>
			</td>
		
		</tr>
		</c:forEach>
			

		<tr>
		</tr>		
			<c:forEach var="friendListVO2" items="${list2}">
		<tr>	
				
					
		</tr>			
		<tr>	
				<c:if test="${fn:contains(friendListVO2.friend_stat,2)}">
						<td>我的好友: ${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
					    <td>
							<form METHOD="post" ACTION="friendList.do" name="form1" enctype="multipart/form-data">
								<input type="hidden" name="action" value="delete">
								<input type="hidden" name="my_id" value="${memVO.mem_id }">
								<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
								<input type="submit" value="刪除好友">
							</form>
			            </td>
				</c:if>
				<c:if test="${fn:contains(friendListVO2.friend_stat,1)}">
						<td>想加我好友的: ${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
				<td>
					<form METHOD="post" ACTION="friendList.do" name="form1" enctype="multipart/form-data">
						<input type="hidden" name="action" value="confirm">
						<input type="hidden" name="my_id" value="${memVO.mem_id }">
						<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
						<input type="submit" value="確認加入好友">
					</form>
				</td>
				<td>
					<form METHOD="post" ACTION="friendList.do" name="form1" enctype="multipart/form-data">
						<input type="hidden" name="action" value="confirmNo">
						<input type="hidden" name="my_id" value="${memVO.mem_id }">
						<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
						<input type="submit" value="拒絕加入好友">
					</form>
				</td>
				</c:if>	
		</tr>
		
		<tr>		
					<c:if test="${fn:contains(friendListVO2.friend_stat,0)}">
						<td>已經送出邀請 等待對方確認中: ${memSvc.getOneMem(friendListVO2.his_id).mem_name}</td>
							<td>
					<form METHOD="post" ACTION="friendList.do" name="form1" enctype="multipart/form-data">
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="my_id" value="${memVO.mem_id }">
						<input type="hidden" name="his_id" value="${friendListVO2.his_id}">
						<input type="submit" value="放棄加入好友">
					</form>
				</td>
					</c:if>	
		</tr>		
		      
		
		 </c:forEach>
		 
		 <tr class="addInfo">
		 
		 
		 </tr>
	</table>
</div>

</body>
<script>

var MyPoint = "/FriendListEchoServer" + "/" + '${memVO.mem_id}' ;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
// var statusOutput = document.getElementById("statusOutput");
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		console.log("前端連線");
	};
	
	webSocket.onmessage = function(event) {
		
        var jsonObj = JSON.parse(event.data);
        var message = jsonObj.his_id + "想要加你為好友!!!" +"\r\n";
        
        
        $('<td>' + message + '</td>').appendTo($('.addInfo'));
	};

	webSocket.onclose = function(event) {
		console.log("前端關閉");
	};
}

// 加好友
$('input[type="submit"]').click(function(event){
	
	var jsonObj = {
				   "my_id" : '${memVO.mem_name}', 
				   "his_id" : $(this).attr("class"), 
				   "event" : $(this).attr("id") 
				   };
	
	webSocket.send(JSON.stringify(jsonObj));
});



function disconnect () {
	webSocket.close();
	
}

</script>
</html>