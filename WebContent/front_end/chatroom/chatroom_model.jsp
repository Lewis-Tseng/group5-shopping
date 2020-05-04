<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");  //Session取得全部會員資料
%>

<html>
<head>
<meta charset="UTF-8">
 <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300' rel='stylesheet' type='text/css'>
<script src="https://use.typekit.net/hoy3lrg.js"></script>
<script>try{Typekit.load({ async: true });}catch(e){}</script><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/dudu/css/chat_new_style.css">

</head>
<body onload="connect();" onunload="disconnect();">
<!-- partial:index.partial.html -->
<!-- 
A concept for a chat interface. 
Try writing a new message! :)
Follow me here:
Twitter: https://twitter.com/thatguyemil
Codepen: https://codepen.io/emilcarlsson/
Website: http://emilcarlsson.se/
-->

<div id="frame">
	<div id="sidepanel">
		<div id="profile">
			<div class="wrap">
			
				<img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="online" alt="" />
				<p>${memVO.mem_name}</p>
				<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i>
				<div id="status-options">
					<ul>
						<li id="status-online" class="active"><span class="status-circle"></span> <p>Online</p></li>
						<li id="status-offline"><span class="status-circle"></span> <p>Offline</p></li>
					</ul>
				</div>
				
				<div id="expanded">
					<label for="twitter"><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></label>
					<input name="twitter" type="text" value="mikeross" />
					<label for="twitter"><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></label>
					<input name="twitter" type="text" value="ross81" />
					<label for="twitter"><i class="fa fa-instagram fa-fw" aria-hidden="true"></i></label>
					<input name="twitter" type="text" value="mike.ross" />
				</div>
				
			</div>
		</div>
		<div id="search">
			<label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
			<input type="text" placeholder="Search contacts..." />
		</div>
<!-- 左邊區塊------------- -->
		<div id="contacts">
			<ul>
			
				<li class="contact">
					<div class="wrap">
						<span class="contact-status offline"></span>
						<img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
						<div class="meta">
							<p class="name">Louis Litt</p>
							<p class="preview">You just got LITT up, Mike.</p>
						</div>
					</div>
				</li>
				
				<li class="contact active">
					<div class="wrap">
						<span class="contact-status online"></span>
						<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
						<div class="meta">
							<p class="name">Harvey Specter</p>
							<p class="preview">Wrong. You take the gun, or you pull out a bigger one. Or, you call their bluff. Or, you do any one of a hundred and forty six other things.</p>
						</div>
					</div>
				</li>
				
			</ul>
		</div>
<!-- 左邊區塊結束------------- -->
<!-- 右邊聊天區塊 開始------------->
		<div id="bottom-bar">
			<button id="addcontact"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add contact</span></button>
			<button id="settings"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>Settings</span></button>
		</div>
	</div>
	<div class="content">
		<div class="contact-profile">
			<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
			<p id="statusOutput" class="statusOutput">Harvey Specter</p>
			<div class="social-media">
				<i class="fa fa-facebook" aria-hidden="true"></i>
				<i class="fa fa-twitter" aria-hidden="true"></i>
				 <i class="fa fa-instagram" aria-hidden="true"></i>
			</div>
		</div>
		<div class="messages">
			<ul>
				<li class="sent">
					<img src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
					<p>How the hell am I supposed to get a jury to believe you when I am not even sure that I do?!</p>
				</li>
				<li class="replies">
					<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
					<p>Wrong. You take the gun, or you pull out a bigger one. Or, you call their bluff. Or, you do any one of a hundred and forty six other things.</p>
				</li>
			</ul>
		</div>
<!-- 輸入訊息和送出紐 ----------------------------------------------------------------------------------------------------->
		<div class="message-input">
			<div class="wrap">
			<input type="text" placeholder="開始聊天吧..." />
			<i class="fa fa-paperclip attachment" aria-hidden="true"></i>
			<button class="submit" ><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
			</div>
		</div>
	</div>
</div>
<!-- partial -->
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script><script  src="./script.js"></script>

</body>


 
<script type="text/javascript">
//老師範例初始化 開始-----------------------------------------------------------

var MyPoint = "/MyEchoServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var statusOutput = document.getElementById("statusOutput");
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		updateStatus("WebSocket 成功連線");
	};
	//從websock取得json message
	webSocket.onmessage = function(event) {
		
        var jsonObj = JSON.parse(event.data);
        var checkMe = jsonObj.userName;
        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
        if( '${memVO.mem_name}' == checkMe ){
        $('<li class="sent"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
        messagesArea.scrollTop = messagesArea.scrollHeight;
    	//左邊部分的預覽訊息更新
    	$('.contact.active .preview').html('<span>You: </span>' + message);
    	//應該是聊天訊息跳出的動畫???
    	$(".messages").animate({ scrollTop: $(document).scrollheight() }, "fast");
        }
	};

	webSocket.onclose = function(event) {
		updateStatus("WebSocket 已離線");
	};
}
// 老師範例初始化 結束---------------------------------------------------------------------
// model 開始--------------------------------------------------------------------------
$(".messages").animate({ scrollTop: $(document).height() }, "fast");

$("#profile-img").click(function() {
	$("#status-options").toggleClass("active");
});

$(".expand-button").click(function() {
  $("#profile").toggleClass("expanded");
	$("#contacts").toggleClass("expanded");
});
//自己加 失敗中
$(".contact").click(function() {
	$(".contact .active").removeClass("active");
	$(".contact").addClass("active");
});

$("#status-options ul li").click(function() {
	$("#profile-img").removeClass();
	$("#status-online").removeClass("active");
	$("#status-offline").removeClass("active");
	$(this).addClass("active");
	
	if($("#status-online").hasClass("active")) {
		$("#profile-img").addClass("online");
	} else if ($("#status-offline").hasClass("active")) {
		$("#profile-img").addClass("offline");
	} else {
		$("#profile-img").removeClass();
	};
	
	$("#status-options").removeClass("active");
});
//按下按鈕 發送訊息-------------------------------------------------------------------------------
$('.submit').click(function() {
	  newMessage();
	});

	$(window).on('keydown', function(e) {
	  if (e.which == 13) {
	    newMessage();
	    return false;
	  }
	});
function newMessage() {
	message = $(".message-input input").val();
	if($.trim(message) == '') {
		return false;
	}
	//發送訊息存到JSON發到websocket處理
	var jsonObj = {"userName" : '${memVO.mem_name}', "message" : message};
	webSocket.send(JSON.stringify(jsonObj));
	 $('<li class="replies"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' +'${memVO.mem_name}'+ message + '</p></li>').appendTo($('.messages ul'));
	//左邊部分的預覽訊息更新
	$('.contact.active .preview').html('<span>You: </span>' + message);
	//清空輸入的訊息
	$('.message-input input').val(null);

};

//update h3標題的狀態
function updateStatus(newStatus) {
	statusOutput.innerHTML = newStatus;
}
// -----------------------------------------------------------------------
</script>

</html>