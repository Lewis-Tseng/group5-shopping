<%@page import="com.friendList.model.*"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

MemVO memVO = (MemVO) session.getAttribute("memVO");  //Session取得全部會員資料


FriendListService FriendListSvc2 = new FriendListService();
List<FriendListVO> list2 = FriendListSvc2.getMyAll(memVO.getMem_id());
pageContext.setAttribute("list2",list2);


%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
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
    bottom: 80;
    width: 80;
    z-index: 2;
}
.containerChat{
	opacity:0;
	display:none;
	transition:all 0.5s ease 0s;
	z-index:4;
	bottom:-270;
}
.chatCloseBtn{
	position: absolute;
    top: 5;
    right: 10;
    z-index: 5;
    font-size: 2em;
    background: none;
    border-color: rgba(0,0,0,0);
    font-family:'test';
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

.containerChat .right {
	bottom: 100;
	background-color:rgba(0,0,0,0);
	transition: 0.5s;
}

.containerChat .right .chat {
	border:none;
	justify-content: normal;
	overflow:srcoll;
	overflow-x: hidden;
	height: calc(100% -90px);
}
.chat-message-counter {
	border:none;
	z-index:5;
}
.containerChat .left .people .person .name {
	font-family:'微軟正黑體';
	font-size: 15px;
	color: rgb(230,230,230);
}
.containerChat .right .top span .name {
	font-family:'微軟正黑體';
}
/* ==================================================================================== */
</style>
</head>
<body  onload="connectChat();" onunload="disconnectChat();">






<div class="containerChat" style="background-color: rgba(0,0,0,0.2);">
        <button class="chatCloseBtn">close</button>
        <div class="left" style="background: rgba(255,255,255,0.9);">
            <div class="top-du" >
            	<div style="background: rgba(40,40,40,0.2);">
            	<c:if test="${memVO.mem_pic!=null}">
             		<img class="offline" src="/DA103G5/DBGifReaderMem?mem_id=${memVO.mem_id}" style="position: fixed;width:75px;left:30px;top:5px;height: 75px;border-radius: 50%;margin: 11 0;margin-left: 20;" alt="">
             	</c:if>
             	<c:if test="${memVO.mem_pic==null}">
             		<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("男")}'>
						<img src="/DA103G5/front_end/css_group/images/men.png" style="width: 75px;left:30px;top:5px;border-radius: 50%;margin: 11 0;margin-left: 20;height: 75px;" class="online">
					</c:if>
					<c:if test='${memberSvc.getOneMem(memVO.mem_id).mem_gender.equals("女")}'>
						<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png" style="width: 75px;left:30px;top:5px;border-radius: 50%;height: 75px;margin: 11 0;margin-left: 20;">
					</c:if>
             	</c:if>
             <span style="position: absolute;right:100px;top:25px;font-size:30px;font-family:'test';">${memVO.mem_name}</span>
				</div>
            </div>
           <ul class="people" style="background: rgba(20,20,20,0.9);height: 504;">
				<c:forEach var="friendListVO2" items="${list2}" begin="0" >
				<c:if test="${(friendListVO2.his_id != memVO.mem_id) &&(friendListVO2.friend_stat==2)}">
					<li class="person" data-chat="default" style="display: none;"></li>
	                <li class="person" data-chat="${memSvc.getOneMem(friendListVO2.his_id).mem_name}" >
						<span class="chat-message-counter" >1</span>
						<c:if test="${memberSvc.getOneMem(friendListVO2.his_id).mem_pic!=null}">
								<img class="offline" src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${friendListVO2.his_id}" alt="" />
							</c:if> 
							<c:if test="${memberSvc.getOneMem(friendListVO2.his_id).mem_pic==null}">
								<c:if test='${memberSvc.getOneMem(friendListVO2.his_id).mem_gender.equals("男")}'>
									<img class="offline" src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
								</c:if>
								<c:if test='${memberSvc.getOneMem(friendListVO2.his_id).mem_gender.equals("女")}'>
									<img class="offline" src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
								</c:if>
							</c:if>
	                    <span class="name">${memSvc.getOneMem(friendListVO2.his_id).mem_name}</span>
	                    <span class="on-offLine">離線</span>
	                    <span class="preview">I was wondering...</span>
	                </li>
	            </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="right" style="float: right;bottom:0;background:rgba(20,20,20,0.9);">
            <div class="top" style="background: rgba(255,255,255,0.9);"><span>To: <span class="name"></span></span></div>
            <div class="chat" id="default" data-chat="default" style="display: none;"></div>
            <c:forEach var="friendListVO2" items="${list2}" begin="0" end="5">
            <c:if test="${friendListVO2.his_id != memVO.mem_id }">
<!--             ================================= -->
            <div class="chat" id="${memSvc.getOneMem(friendListVO2.his_id).mem_name}" data-chat="${memSvc.getOneMem(friendListVO2.his_id).mem_name}"> 
<!--             ================================= -->
                <div class="conversation-start">
                    <span>開始聊天吧</span>
                </div>
            </div> 
            </c:if>
            </c:forEach> 
        </div>

        <div class="right" style="height: 100; background-color: rgba(255,255,255,0.7);float:right;">
        	<div class="write">
                <a href="javascript:;" class="write-link attach"></a>
                <a href="javascript:;" class="write-link smiley"></a>
                <input type="text" id="inputplace" placeholder="快發送你的第一句話吧..." />
                <a href="javascript:submit();" class="write-link send"></a>
            </div>
        </div>
    </div>

    <div class="chatBtn"><img src="<%=request.getContextPath()%>/front_end/css_group/images/chatroomIcon.png"></div>

</body>
<!-- ========================================================================================================== -->
<script type="text/javascript">
var noSeeNum= 1;
document.querySelector('.chat[data-chat=default]').classList.add('active-chat');
document.querySelector('.person[data-chat=default]').classList.add('active');
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

var MyPointChat = "/MyEchoServer" +"/" + '${memVO.mem_name}' + "/" + "阿昆";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURLChat = "ws://" + window.location.host + webCtx + MyPointChat;
var webSocketChat;
function connectChat() {
	webSocketChat = new WebSocket(endPointURLChat);
	
	webSocketChat.onopen = function(event) {
		$('.top-du img').removeClass('offline');
		$('.top-du img').addClass('online');
		console.log("聊天室開啟");
	};
	webSocketChat.onmessage = function(event) {
		var jsonObj;
		var getFrom;
		var message2;
		var whoMessage;
		var noSeeFromSocket;
//===============================================================id要加
		if($('.active-chat').attr("id")!="default"){
			var fuck = $('.active-chat').attr("id");
			console.log(fuck);
			var chatArea = document.getElementById(fuck);
			chatArea.scrollTop = chatArea.scrollHeight;
		}
//===============================================================
		console.log(event.data);
		
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
							webSocketChat.send(JSON.stringify(noSeeJson));
						}else{
							
					   		$('.person[data-chat='+getFrom+'] .chat-message-counter').html(noSeeFromSocket);
					   		noSeeFromSocket++ ;
							console.log("不是1的:" + noSeeFromSocket);
							noSeeJson= {"me" : '${memVO.mem_name}', "him" : getFrom, "noSeeNum" : noSeeFromSocket.toString() };
							webSocketChat.send(JSON.stringify(noSeeJson));
						}
				}else{
			        $('<div class="bubble you"><p>' + whoMessage + '</p></div>').appendTo($('.chat.active-chat'));
			    	$('.person.active .preview').html('<span>'+getFrom+': </span>'+message2);
			    	$('.chat[data-chat='+getFrom+'] .conversation-start').html('');
			    	
				}
		}
	};
	
	webSocketChat.onclose = function(event) {
		$('.top-du img').removeClass('online');
		$('.top-du img').addClass('offline');
	};
	
};
	function submit() {
		newMessage();
	};
	
	$(window).on('keydown', function(e) {
	  if (e.which == 13) {
//===============================================================id要加
			if($('.active-chat').attr("id")!="default"){
				var fuck = $('.active-chat').attr("id");
				console.log(fuck);
				var chatArea = document.getElementById(fuck);
				chatArea.scrollTop = chatArea.scrollHeight;
			}
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
	webSocketChat.send(JSON.stringify(jsonObj));
	$('<div class="bubble me"><p>'+message+'</p></div>').appendTo($('.chat.active-chat'));
	$('.person.active .preview').html('<span>我: </span>' + message);
	$('.write input').val(null);
};

function disconnectChat () {
	webSocketChat.close();
	console.log("聊天室離開");
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

</html>