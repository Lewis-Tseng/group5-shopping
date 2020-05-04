package com.friendList.model;

import java.util.List;


public class FriendListService {
	
	private FriendList_interface dao;

	public FriendListService() {
		dao = new FriendListDAO();
	}	
	
	//查詢好友
	public List<FriendListVO> getMyAll(String my_id) {
		return dao.getMyAll(my_id);
	}
	
	public String getOneStat(String my_id,String his_id) {
		return dao.getOneStat(my_id,his_id);
	}
}
