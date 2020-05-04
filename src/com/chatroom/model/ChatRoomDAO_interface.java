package com.chatroom.model;

import java.util.*;

public interface ChatRoomDAO_interface {
	public void insert(ChatRoomVO chatroomVO);
	public void update(ChatRoomVO chatroomVO);
	public void delete(String chat_id);
	public ChatRoomVO findPrimaryKey(String chat_id);
	public List<ChatRoomVO> getAll();
}
