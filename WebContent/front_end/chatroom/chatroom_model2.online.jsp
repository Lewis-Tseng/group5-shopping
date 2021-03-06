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
<!-- 最原始聊天室 -->
<link href="<%=request.getContextPath()%>/front_end/dudu/css/chatroom.css" rel='stylesheet' type='text/css'/>
<!-- model 1 -->
 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script><script  src="./script.js"></script>
<!-- -- --------------------------------------------->
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
            
				<c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" end="5">
				<c:if test="${mem2VO.mem_id != memVO.mem_id }">
					 
	                	
	                <li class="person" data-chat="${mem2VO.mem_name}" >
	                	<!-- 未讀訊息小紅鈕	-------------------------->
						<span class="chat-message-counter">New</span>
	                    <img class="offline" src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${mem2VO.mem_id}" alt="" />
	                   
	                    <span class="name">${mem2VO.mem_name}</span>
	                    <span class="time">2:09 PM</span>
	                    <span class="preview">I was wondering...</span>
	                </li>
	            </c:if>
                </c:forEach>
                
            </ul>
        </div>
<!-- 右半部聊天畫面         -->
        <div class="right">
            <div class="top"><span>我是${memVO.mem_name}啦!!!! To: <span class="name"></span></span></div>
            
	   	
            <c:forEach var="mem2VO" items="${memSvc2.all}" begin="0" end="5">
            <c:if test="${memVO.mem_id != mem2VO.mem_id }">
            <div class="chat" data-chat="${mem2VO.mem_name}">
                <div class="conversation-start">
                    <span>開始聊天吧</span>
                </div>
           
            </div> 
            </c:if>
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
                <a href="javascript:;" class="write-link attach"></a>
                
                <a href="javascript:;" class="write-link smiley"></a>
                <!--訊息發送按鈕 -->
                <input type="text" placeholder="快發送你的第一句話吧..." />
                <a href="javascript:submit();" class="write-link send"></a>
            </div>
        </div>
    </div>
</div>
<!-- partial -->
</body>

<script type="text/javascript">

document.querySelector('.chat[data-chat=Draymon]').classList.add('active-chat');
document.querySelector('.person[data-chat=Draymon]').classList.add('active');

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
//提醒幾封訊息未讀的提醒框框小紅鈕  消失
  $('.person.active .chat-message-counter').fadeOut(200, 'swing');
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
		var jsonObj;
		var getFrom;
		var message2;
		var whoMessage;
		
		
		
			//傳送 上線名單 給剛上線的人
		if(event.data.includes('*')){
			var cutList; 
			cutList=(event.data).split("*");
			console.log("上線名單: " + cutList);
				for(var i=0; i<(cutList.length)-1 ; i++){
					$('.person[data-chat='+cutList[i]+'] img').removeClass('offline');
					$('.person[data-chat='+cutList[i]+'] img').addClass('online');
				}
			//當有人下線時
		}else if(event.data.includes('#')){
			var whoOffline;
			whoOffline = (event.data).split("#");
			console.log(whoOffline[0] +"下線拉");
			$('.person[data-chat='+whoOffline[0]+'] img').removeClass('online');
			$('.person[data-chat='+whoOffline[0]+'] img').removeClass('offline');
			
		}else if(!(event.data).includes('{')){
			//當有人上線時
			var whoOnline;
			whoOnline = event.data;
			console.log(event.data+"上線拉");
			$('.person[data-chat='+whoOnline+'] img').removeClass('offline');
			$('.person[data-chat='+whoOnline+'] img').addClass('online');
		
		}else{
	        jsonObj = JSON.parse(event.data);
	//         var checkMe = jsonObj.userName;
	        getFrom = jsonObj.userName;
	        message2 = jsonObj.message;
	        whoMessage = getFrom + ": " + message2 + "\r\n";
	        
	//         if( '${memVO.mem_name}' != checkMe ){
				//當非對方視窗時 對方發訊息給我
				if( getFrom != $('.person.active .name').text() ){
					//未讀訊息 設置
						//如果原本沒有未讀訊息 才加 未讀訊息的字
					if( ( ($('.chat[data-chat='+getFrom+'] .conversation-start:contains("未讀訊息")')).text() )=="" ){
						 $('.chat[data-chat='+getFrom+'] .conversation-start').html('<span>以下為尚未閱讀的訊息</span>');
	//小bug待解 					 $('<span>以下為尚未閱讀的訊息</span>').appendTo($('.chat[data-chat='+getFrom+'] .conversation-start'));
					}
					   //聊天訊息接收 加到聊天室窗
					$('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo( $('.chat[data-chat='+getFrom+']') );
					   //左邊部分的預覽訊息更新
					$('.person[data-chat='+getFrom+'] .preview').html('<span>'+getFrom+': </span>'+message2);
					   //提醒幾封訊息未讀的提醒框框小紅鈕  出現
					$('.person[data-chat='+getFrom+'] .chat-message-counter').fadeIn(300, 'swing');
					   
				//正常對話狀態
				}else{
					//聊天訊息輸出
			        $('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat.active-chat'));
	//	            messagesArea.scrollTop = messagesArea.scrollHeight;
			    	//左邊部分的預覽訊息更新
			    	$('.person.active .preview').html('<span>'+getFrom+': </span>'+message2);
			    	//去掉未讀訊息字樣
			    	$('.chat[data-chat='+getFrom+'] .conversation-start').html('');
				}
		}
// 			$(".person.active").click(function() {
// 				//未讀訊息的 小紅鈕  消失
// 				$('.person.active .chat-message-counter').fadeOut(200, 'swing');
// 			});
		
    	//應該是聊天訊息跳出的動畫???
//     	$(".messages").animate({ scrollTop: $(document).scrollheight() }, "fast");
       
	};

// 	webSocket.onclose = function(event) {
// 		updateStatus("WebSocket 已離線");
// 	};
}
// 老師範例初始化 結束---------------------------------------------------------------------
//按下按鈕 發送訊息----------------chat1---------------------------------------------------------------
	function submit() {
	  newMessage();
	}
	
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