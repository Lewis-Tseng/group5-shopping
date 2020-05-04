<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");  //Session取得全部會員資料
%>
<jsp:useBean id="memSvc2" scope="page" class="com.mem.model.MemService" />
<head>
  <meta charset="UTF-8">
  <title>${memVO.mem_name}的聊天室 </title>
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script><script  src="./script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/dudu/css/chat_new2_styleJI.css">
<style>
/* ==================================================================================== */
.chatBtn img{
	position: fixed;
    right: 15;
    bottom: 130;
    width: 100;
}
.containerChat{
	opacity:0;
	display:none;
	transition:all 0.5s ease 0s;
	z-index:4;
	bottom:-100;
}
.chatCloseBtn{
	position: absolute;
    top: 0;
    right: 0;
    z-index: 5;
    font-size: 2em;
    background: none;
    border-color: rgba(0,0,0,0);
}

.people::-webkit-scrollbar {
	 width: 0 !important 
}

.people {
	overflow: scroll;
    overflow-x: hidden;
    height: 100%;
}

.containerChat .left {
	border:none;
}
.containerChat .left .people {
	border:none;
}

.containerChat .right .chat::-webkit-scrollbar {
	 width: 0 !important }
	 
.containerChat .right .chat {
	border:none;
	justify-content: normal;
	overflow:srcoll;
	overflow-x: hidden;
}
.chat-message-counter {
	border:none;
	z-index:5;
}
/* ==================================================================================== */
</style>
</head>
<body onload="connect();" onunload="disconnect();" >
<!-- ========================================================================================================== -->
    <div class="containerChat">
<!-- ========================================================================================================== -->
        <button class="chatCloseBtn">X</button>
        <div class="left">
            <div class="top-du" >
            	<div>
             <img class="offline" src="/DA103G5_1.013/DBGifReaderMem?mem_id=${memVO.mem_id}" style="position: fixed;width:85px;left:30px;top:5px;border-radius: 50%" alt="">
             <span style="float-left;position: absolute;right:80px;top:25px;font-size:30px">${memVO.mem_name}</span>
				</div>
            </div>
            <ul class="people">
				<c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" >
				<c:if test="${mem2VO.mem_id != memVO.mem_id }">
	                <li class="person" data-chat="${mem2VO.mem_name}" >
						<span class="chat-message-counter" >1</span>
	                    <img class="offline" src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${mem2VO.mem_id}" alt="" />
	                    <span class="name">${mem2VO.mem_name}</span>
	                    <span class="on-offLine">離線</span>
	                    <span class="preview">I was wondering...</span>
	                </li>
	            </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="right">
            <div class="top"><span>To: <span class="name"></span></span></div>
            <c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" end="5">
            <c:if test="${memVO.mem_id != mem2VO.mem_id }">
<!--             ================================= -->
            <div class="chat" id="chat" data-chat="${mem2VO.mem_name}"> 
<!--             ================================= -->
                <div class="conversation-start">
                    <span>開始聊天吧</span>
                </div>
            </div> 
            </c:if>
            </c:forEach> 
        </div>
<!--===================================================================================================原本的刪掉 -->
        <div class="right" style="height: 16%; background-color: white;float:right;">
        	<div class="write">
                <a href="javascript:;" class="write-link attach"></a>
                <a href="javascript:;" class="write-link smiley"></a>
                <input type="text" id="inputplace" placeholder="快發送你的第一句話吧..." />
                <a href="javascript:submit();" class="write-link send"></a>
            </div>
        </div>
    </div>
<!--=================================================================================================== -->
    <div class="chatBtn"><img src="<%=request.getContextPath()%>/front_end/chatroom/astar.png"></div>

</body>
<!-- ========================================================================================================== -->
<script>
	$('.chatBtn').click(function(){
		$('.containerChat').css("display","initial");
		if($('.containerChat').css("display")=="block"){
			$('.containerChat').css("opacity","1");
		}
		
	});
	$('.chatCloseBtn').click(function(){
		
		if($('.containerChat').css("display")=="block"){
			$('.containerChat').css("opacity","0");
		}
		setTimeout(function(){
			$('.containerChat').css("display","none");
		},500);
		
	});
	
</script>
<!-- ========================================================================================================== -->

<script type="text/javascript">
var noSeeNum= 1;
document.querySelector('.chat[data-chat=吳金生]').classList.add('active-chat');
document.querySelector('.person[data-chat=吳金生]').classList.add('active');
let friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .person'),
  name: '' },
chat = {
  container: document.querySelector('.containerChat .right'),
  current: "",
  person: "",
  name: document.querySelector('.containerChat .right .top .name') };
friends.all.forEach(function(f) {
  f.addEventListener('mousedown', function(){
    f.classList.contains('active') || setAciveChat(f);
  });
});
function setAciveChat(f) {
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  friends.name = f.querySelector('.name').innerText;
  chat.name.innerHTML = friends.name;
  $('.person.active .chat-message-counter').fadeOut(50,'swing');
  $('.person.active .chat-message-counter').html("1");
  noSeeNum= 1;
}
var MyPoint = "/MyEchoServer" +"/" + '${memVO.mem_name}' + "/" + "阿昆" ;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var webSocket;
function connect() {
	webSocket = new WebSocket(endPointURL);
	webSocket.onopen = function(event) {
		$('.top-du img').removeClass('offline');
		$('.top-du img').addClass('online');
	};
	webSocket.onmessage = function(event) {
		var jsonObj;
		var getFrom;
		var message2;
		var whoMessage;
		var noSeeFromSocket;
//===============================================================id="chart"要加
		var chatArea = document.getElementById("chat");
		chatArea.scrollTop = chatArea.scrollHeight;
//===============================================================
		if(event.data.includes('*')){
			var cutList; 
			cutList=(event.data).split("*");
			console.log("上線名單: " + cutList);
				for(var i=0; i<(cutList.length)-1 ; i++){
					$('.person[data-chat='+cutList[i]+'] img').removeClass('offline');
					$('.person[data-chat='+cutList[i]+'] img').addClass('online');
					$('.person[data-chat='+cutList[i]+'] .on-offLine').html('上線中');
				}
		}else if(event.data.includes('#')){
			var whoOffline;
			whoOffline = (event.data).split("#");
			console.log(whoOffline[0] +"下線拉");
			$('.person[data-chat='+whoOffline[0]+'] img').removeClass('online');
			$('.person[data-chat='+whoOffline[0]+'] img').addClass('offline');
			$('.person[data-chat='+whoOffline[0]+'] .on-offLine').html('離線')
		}else if(!(event.data).includes('{')){
			var whoOnline;
			whoOnline = event.data;
			console.log(event.data+"上線拉");
			$('.person[data-chat='+whoOnline+'] img').removeClass('offline');
			$('.person[data-chat='+whoOnline+'] img').addClass('online');
			$('.person[data-chat='+whoOnline+'] .on-offLine').html('上線中');
		}else{
	        jsonObj = JSON.parse(event.data);
	        getFrom = jsonObj.userName;
	        message2 = jsonObj.message;
	        noSeeFromSocket = jsonObj.noSee;
	        whoMessage = getFrom + ": " + message2 + "\r\n";
	        console.log("伺服器回傳的未讀次數:" + noSeeFromSocket);
				if( getFrom != $('.person.active .name').text() ){
					if( ( ($('.chat[data-chat='+getFrom+'] .conversation-start:contains("未讀訊息")')).text() )=="" ){
						 $('.chat[data-chat='+getFrom+'] .conversation-start').html('<span>以下為尚未閱讀的訊息</span>');
					}
					$('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo( $('.chat[data-chat='+getFrom+']') );
					$('.person[data-chat='+getFrom+'] .preview').html('<span>'+getFrom+': </span>'+message2);
					$('.person[data-chat='+getFrom+'] .chat-message-counter').fadeIn(300, 'swing');
						if($('.person[data-chat='+getFrom+'] .chat-message-counter').html()==1 && noSeeNum== 1){
							noSeeNum++;
							console.log("是1的:" + noSeeNum);
							noSeeJson= {"me" : '${memVO.mem_name}', "him" : getFrom, "noSeeNum" : noSeeNum.toString() };
							webSocket.send(JSON.stringify(noSeeJson));
						}else{
							
					   		$('.person[data-chat='+getFrom+'] .chat-message-counter').html(noSeeFromSocket);
					   		noSeeFromSocket++ ;
							console.log("不是1的:" + noSeeFromSocket);
							noSeeJson= {"me" : '${memVO.mem_name}', "him" : getFrom, "noSeeNum" : noSeeFromSocket.toString() };
							webSocket.send(JSON.stringify(noSeeJson));
						}
				}else{
			        $('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat.active-chat'));
			    	$('.person.active .preview').html('<span>'+getFrom+': </span>'+message2);
			    	$('.chat[data-chat='+getFrom+'] .conversation-start').html('');
			    	
				}
		}
	};
	webSocket.onclose = function(event) {
		$('.top-du img').removeClass('online');
		$('.top-du img').addClass('offline');
	};
}
	function submit() {
		newMessage();
	}
	
	$(window).on('keydown', function(e) {
	  if (e.which == 13) {
//===============================================================id="chart"要加
			var chatArea = document.getElementById("chat");
			chatArea.scrollTop = chatArea.scrollHeight;
//===============================================================
		  newMessage();
	    return false;
	  }
	});
function newMessage() {
	message = $(".write input").val();
	if($.trim(message) == '') {
		return false;
	}
	jsonObj= {"userName" : '${memVO.mem_name}', "message" : message, "hisName" : $('.person.active .name').text() };
	webSocket.send(JSON.stringify(jsonObj));
	$('<div class="bubble me"><p>'+message+'</p></div>').appendTo($('.chat.active-chat'));
	$('.person.active .preview').html('<span>我: </span>' + message);
	$('.write input').val(null);
};
</script>
<script type="text/javascript">
 	$('img').on('click', function() {
 		$('.wrapper').fadeToggle(300, 'swing');
 	});
 	$('.chat-close').on('click', function(e) {
 		e.preventDefault();
 		$('#live-chat').fadeOut(300)});
</script>


</html>