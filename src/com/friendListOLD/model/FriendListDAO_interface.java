package com.friendListOLD.model;
import java.util.*;
public interface FriendListDAO_interface {
	public void insert(FriendListVO friendListVO);
	public void update(FriendListVO friendListVO);
	public void delete(String mem_id_1,String mem_id_2);
	public FriendListVO findPrimaryKey(String mem_id_1,String mem_id_2);
	public List<FriendListVO> getAll();
}
