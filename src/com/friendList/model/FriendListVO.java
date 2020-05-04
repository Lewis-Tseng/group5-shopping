package com.friendList.model;

public class FriendListVO implements java.io.Serializable{

	private String my_id;
	private String his_id;
	private String friend_stat;
	
	public String getMy_id() {
		return my_id;
	}
	public void setMy_id(String my_id) {
		this.my_id = my_id;
	}
	public String getHis_id() {
		return his_id;
	}
	public void setHis_id(String his_id) {
		this.his_id = his_id;
	}
	public String getFriend_stat() {
		return friend_stat;
	}
	public void setFriend_stat(String friend_stat) {
		this.friend_stat = friend_stat;
	}

	
}
