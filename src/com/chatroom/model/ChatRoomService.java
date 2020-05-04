package com.chatroom.model;

import java.util.List;


public class ChatRoomService {
	private ChatRoomDAO_interface dao;
	public ChatRoomService() {
		dao=new ChatRoomDAO();
	}
	public ChatRoomVO addChatroom(String chat_id,String mem_id_1,String mem_id_2,String coa_id,
			String chat_info,String chat_info2,String chat_stat,String chat_stat2) {
		ChatRoomVO chatroomvo=new ChatRoomVO();
		chatroomvo.setChat_id(chat_id);
		chatroomvo.setMem_id_1(mem_id_1);
		chatroomvo.setMem_id_2(mem_id_2);
		chatroomvo.setCoa_id(coa_id);
		chatroomvo.setChat_info(chat_info);
		chatroomvo.setChat_info2(chat_info2);
		chatroomvo.setChat_stat(chat_stat);
		chatroomvo.setChat_stat2(chat_stat2);
		dao.insert(chatroomvo);
		return chatroomvo;
		
	}
	public ChatRoomVO updateChatroom(String chat_id,String mem_id_1,String mem_id_2,String coa_id,
			String chat_info,String chat_info2,String chat_stat,String chat_stat2) {
		ChatRoomVO chatroomvo=new ChatRoomVO();
		chatroomvo.setChat_id(chat_id);
		chatroomvo.setMem_id_1(mem_id_1);
		chatroomvo.setMem_id_2(mem_id_2);
		chatroomvo.setCoa_id(coa_id);
		chatroomvo.setChat_info(chat_info);
		chatroomvo.setChat_info2(chat_info2);
		chatroomvo.setChat_stat(chat_stat);
		chatroomvo.setChat_stat2(chat_stat2);
		dao.update(chatroomvo);
		return chatroomvo;
		
	}


	public void deleteChatroom(String chat_id) {
		dao.delete(chat_id);
	}

	public ChatRoomVO getOneChatRoom(String chat_id) {
		return dao.findPrimaryKey(chat_id);
	}

	public List<ChatRoomVO> getAll() {
		return dao.getAll();
	}
	public static void main(String[] args) {
	ChatRoomService chatroom=new ChatRoomService();
//	chatroom.addChatroom("CH00004","ME00001","ME00002",null, "JAVA超簡單","萬般兼物件","0","1");
//	System.out.println("新增成功");
//	chatroom.updateChatroom("CH00001","ME00002","ME00003","CO00001","JAVA三部曲","宣告，取值,拿來用","已讀","未讀");
//	System.out.println("修改成功");
//	chatroom.deleteChatroom("CH00006");
//	System.out.println("刪除成功");
	System.out.println(chatroom.getOneChatRoom("CH00001"));
	
}
}
	