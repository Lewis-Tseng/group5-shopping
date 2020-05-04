<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
MemVO memVO = (MemVO) session.getAttribute("memVO");  //Session取得全部會員資料
// MemService memSvc = new MemService();
// List<MemVO> list = memSvc.getAll();
// pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc2" scope="page" class="com.mem.model.MemService" />

<head>
  <meta charset="UTF-8">
  <title>Direct Messaging</title>
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
<!-- model 1 -->
 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script><script  src="./script.js"></script>
<!-- model 1 -->
<meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/dudu/css/chat_new2_style.css">

</head>
<body onload="connect();" onunload="disconnect();">
<!-- partial:index.partial.html -->
<div class="wrapper">
    <div class="container">
<!-- 左邊會員列表    -->
        <div class="left">
            <div class="top">
                <input type="text" placeholder="Search" />
                <a href="javascript:;" class="search"></a>
            </div>
            <ul class="people">
	   				<% 
	   					int i=1;
	   					String person="person"+i;
	   				%>
				<c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" end="5">
				<% person="person"+i ;%>
<!----------------------不知為啥沒有作用 -->
<%-- 				<c:if test="${(mem2VO.mem_name)!=(memVO.mem_name)}"> --%>
	                <li class="person" data-chat="<%=person%>" >
	           									 <% i++; %>
	                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/382994/thomas.jpg" alt="" />
	                    <span class="name">${mem2VO.mem_name}</span>
	                    <span class="time">2:09 PM</span>
	                    <span class="preview">I was wondering...</span>
	                </li>
<%-- 	            </c:if> --%>
                </c:forEach>
                
            </ul>
        </div>
<!-- 右半部聊天畫面         -->
        <div class="right">
            <div class="top"><span>我是${memVO.mem_name}啦!!!! To: <span class="name"></span></span></div>
            		<% 
	   					int j=1;
	   					String person2;
	   				%>
	   		
            <c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" end="5">
            
            <% person2="person"+j ;%>
            
            <div class="chat" data-chat="<%=person2%>">
                <div class="conversation-start">
                    <span>Today, 6:48 AM</span>
                </div>
                <div class="bubble you">
                    	哈囉! 我是${mem2VO.mem_name}
                </div>
             	<div class="bubble me">
                    	您好! 我是${memVO.mem_name}
                </div>
            </div>
            							 <% j++; %>  
            </c:forEach>
            
<!--             <div class="chat" data-chat="person2"> -->
<!--                 <div class="conversation-start"> -->
<!--                     <span>Today, 5:38 PM</span> -->
<!--                 </div> -->
<!--                 <div class="bubble you"> -->
<!--                     Hello, can you hear me? -->
<!--                 </div> -->
             
<!--                 <div class="bubble me"> -->
<!--                     ... about who we used to be. -->
<!--                 </div> -->
<!--             </div> -->
               
            <div class="write">
<!--                 <a href="javascript:;" class="write-link attach"></a> -->
                
<!--                 <a href="javascript:;" class="write-link smiley"></a> -->
                <!--訊息發送按鈕 -->
                <input type="text" placeholder="開始聊天吧..." />
                <button class="submit">發送 </button>
            </div>
        </div>
    </div>
</div>
<!-- partial -->
</body>

<script type="text/javascript">
document.querySelector('.chat[data-chat=person1]').classList.add('active-chat');
document.querySelector('.person[data-chat=person1]').classList.add('active');

let friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .person'),
  name: '' },

chat = {
  container: document.querySelector('.container .right'),
  current: null,
  person: null,
  name: document.querySelector('.container .right .top .name') };
  


friends.all.forEach(f => {
  f.addEventListener('mousedown', () => {
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
}
//老師範例初始化 開始-----------------------------------------------------------
var MyPoint = "/MyEchoServer" +"/" + '${memVO.mem_name}' + "/" + "阿昆" ;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

// var statusOutput = document.getElementById("statusOutput");
var webSocket;
function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
// 	webSocket.onopen = function(event) {
// 		updateStatus("WebSocket 成功連線");
// 	};
	//從websock取得json message
	webSocket.onmessage = function(event) {
		
        var jsonObj = JSON.parse(event.data);
//         var checkMe = jsonObj.userName;
        var getFrom = jsonObj.userName;
        var message2 = jsonObj.message;
        var whoMessage = getFrom + ": " + message2 + "\r\n";
//         if( '${memVO.mem_name}' != checkMe ){
		//測試用---------------------
		console.log(getFrom);
		var xar ;
		xxx = $('.person.active .name').text();
		console.log(xxx);
		var yyy ;
		yyy = $('.person .name:contains('+getFrom+')').text() ;
		console.log(yyy);
		//測試用---------------------
			if(getFrom != xxx){
				if( getFrom == $('.person .name:contains(getFrom)').text() ){
					$('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat'));
					$('.person .preview').html('<span>'+getFrom+': </span>'+message2);
				  }
			}else{
		        $('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat.active-chat'));
//	            messagesArea.scrollTop = messagesArea.scrollHeight;
		    	//左邊部分的預覽訊息更新
		    	$('.person.active .preview').html('<span>'+getFrom+': </span>'+message2);
			}
    	//應該是聊天訊息跳出的動畫???
//     	$(".messages").animate({ scrollTop: $(document).scrollheight() }, "fast");
//         }
	};

// 	webSocket.onclose = function(event) {
// 		updateStatus("WebSocket 已離線");
// 	};
}
// 老師範例初始化 結束---------------------------------------------------------------------
//按下按鈕 發送訊息----------------chat1---------------------------------------------------------------
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
	message = $(".write input").val();
	if($.trim(message) == '') {
		return false;
	}
	//發送訊息存到JSON發到websocket處理
	var jsonObj ;
	jsonObj= {"userName" : '${memVO.mem_name}', "message" : message, "hisName" : $('.person.active .name').text() };
	webSocket.send(JSON.stringify(jsonObj));
	$('<div class="bubble me"><p>' +'${memVO.mem_name}'+' : ' +message+ '</p></div>').appendTo($('.chat.active-chat'));
	//左邊部分的預覽訊息更新
	$('.person.active .preview').html('<span>我: </span>' + message);
	//清空輸入的訊息
	$('.write input').val(null);
};
// ----------------------chat1------------------------------------


</script>
</html>