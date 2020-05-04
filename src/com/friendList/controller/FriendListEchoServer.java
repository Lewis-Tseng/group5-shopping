package com.friendList.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/FriendListEchoServer/{my_id}")
public class FriendListEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());

//抓取會員與session的關係
private static Map <String,Session> map = new HashMap<String,Session>() ;

	@OnOpen
	public void onOpen(@PathParam("my_id") String my_id, Session userSession) throws IOException {
		
		
		allSessions.add(userSession);
		
		
		map.put( my_id, userSession );
		
		System.out.println("開機加入"+my_id);
//		userSession.getBasicRemote().sendText("WebSocket �s�u���\");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException, IOException {
		
//		for (Session session : allSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
		String sendTo;
		String from ;
		String event;
		String fromWho;
		
		JSONObject jsonObject = new JSONObject(message);
		sendTo = jsonObject.getString("his_id");
		from = jsonObject.getString("my_id");
		event = jsonObject.getString("event");
		fromWho = jsonObject.getString("my_name");
		
		System.out.println("sendTO:"+sendTo);
		System.out.println("from:"+from);
		System.out.println("event:"+event);
		
		if("addFriend".equals(event) || "acceptFriend".equals(event)) {
		//單一聊天session發送
			
			JSONObject messageMix = new JSONObject(message);
			
			messageMix.put("his_id", from);
			messageMix.put("my_id", sendTo);
			messageMix.put("event", event);
			messageMix.put("his_name", fromWho);
			
			Session session = map.get(sendTo);
				
				if(session.isOpen()){
					synchronized (session) {
					session.getBasicRemote().sendText(messageMix.toString());
					System.out.println("送"+messageMix.toString());
					}
				}
			
		}
		
		
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
