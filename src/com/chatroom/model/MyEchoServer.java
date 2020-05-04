package com.chatroom.model;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;


@ServerEndpoint("/MyEchoServer/{myName}/{hisName}")
public class MyEchoServer {
	
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	//抓取會員與session的關係
	private static Map <String,Session> map = new HashMap<String,Session>() ;
	
	private static Map <String,String> noSeeMap = new HashMap<String,String>() ;
	
		@OnOpen
		public void onOpen(@PathParam("myName") String myName, Session userSession) throws IOException {
			
				allSessions.add(userSession);
				
				map.put(myName, userSession);
				
							//更新給所有人 誰上線
							for (Session session : allSessions) {
								if (session.isOpen() && session!=userSession) {
									session.getBasicRemote().sendText(myName);
									System.out.println("祭出線上名單");
								}
							}
							//給剛上線的 抓取誰已經在線上
								StringBuffer onlineList = new StringBuffer(); 
									for (String key : map.keySet()) {
											//如果這個session還是開著的話      map.get(key)==session
												onlineList.append(key+"*");
												
								}
									
									Session session = map.get(myName);
									session.getBasicRemote().sendText( onlineList.toString() );
									
								System.out.println("祭出線上名單222");
								System.out.println("這是上線名單 : "+onlineList);
								//名單收集完成 發給剛上線的人
							}
	//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
		
	
//------------------------------------------------------------------------------------------------------------		
		@OnMessage
		public void onMessage(Session userSession, String message) throws JSONException, IOException {
				String me;
				String him;
				String notSeeNum;
				//未讀訊息 計次功能
				if(message.contains("noSeeNum")) {
					
					JSONObject noSeeJson = new JSONObject(message);
					me = noSeeJson.getString("me");
					him = noSeeJson.getString("him");
					notSeeNum = noSeeJson.getString("noSeeNum");
					System.out.println("這是寫入map的未讀次數"+notSeeNum);
					System.out.println("這是key  "+ (me+him) );
					System.out.println("**********************************");
					noSeeMap.put( (me+him) , notSeeNum );
					
				//一般訊息發送
				}else {
			
					JSONObject jsonObject = new JSONObject(message);
					String sendTo = jsonObject.getString("hisName");
					String from = jsonObject.getString("userName");
					String msg = jsonObject.getString("message");
					String notSee = "1";
					
						for(String key : noSeeMap.keySet()) {
							System.out.println(  "KEY和sendTo+from做比對:" +key+" ## "+ (sendTo+from)  );
							if(key.equals((sendTo+from))) {
								System.out.println("一般訊息發送時的未讀次數"+(noSeeMap.get(key)));
								System.out.println("**********************************");
								notSee = noSeeMap.get(key);
								
							}
						}
						
					JSONObject messageMix = new JSONObject();
					
					messageMix.put("userName", from);
					messageMix.put("message", msg);
					messageMix.put("hisName", sendTo);
					messageMix.put("noSee", notSee);
						
					System.out.println(messageMix.toString());
				
					//單一聊天session發送
					Session session = map.get(sendTo);
					if(session!=null) {
					//session is open測試中 
						if(session.isOpen()){
							session.getBasicRemote().sendText(messageMix.toString());
						}
					}
	//			for (Session session : allSessions) {
	//				if (session.isOpen())
	//					session.getAsyncRemote().sendText(message);
	//			}
				
					System.out.println(sendTo+":" + message);
				}	
		}
//--------------------------------------------------------------------------------------------------------------------------		
		@OnError
		public void onError(Session userSession, Throwable e){
			e.printStackTrace();
		}
//---------------------------------------------------------------------------------------------------------------------------		
		@OnClose
		public void onClose(@PathParam("myName") String myName,Session userSession, CloseReason reason) throws IOException {
			allSessions.remove(userSession);
			// 誰下了線 發送給其他人
			for (Session session : allSessions) {
				
				if (session.isOpen())
					session.getBasicRemote().sendText(myName + "#" );
				
			} 
			
			
			System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		}

 
}
