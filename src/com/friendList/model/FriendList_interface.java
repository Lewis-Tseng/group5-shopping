package com.friendList.model;
import java.util.*;

public interface FriendList_interface {
	
	public List<FriendListVO> getMyAll(String my_id);
	
	public String getOneStat(String my_id,String his_id);
}
