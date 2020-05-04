package com.friendListOLD.model;

import java.util.List;



public class FriendListService {
	private FriendListDAO_interface dao;
	public FriendListService() {
		dao=new FriendListDAO();
	}
	public FriendListVO addFriend(String mem_id_1,String mem_id_2,String frd_memo,String frd_add) {
		FriendListVO friendList=new FriendListVO();
		friendList.setMem_id_1(mem_id_1);
		friendList.setMem_id_2(mem_id_2);
		friendList.setFrd_memo(frd_memo);
		friendList.setFrd_add(frd_add);
		dao.insert(friendList);
		return friendList;
	}
	public FriendListVO updateFriend(String mem_id_1,String mem_id_2,String frd_memo,String frd_add) {
		FriendListVO friendList=new FriendListVO();
		friendList.setMem_id_1(mem_id_1);
		friendList.setMem_id_2(mem_id_2);
		friendList.setFrd_memo(frd_memo);
		friendList.setFrd_add(frd_add);
		dao.update(friendList);
		return friendList;
	}
	public void deleteFriendList(String mem_id_1,String mem_id_2) {
		dao.delete(mem_id_1, mem_id_2);
		
	}
	public FriendListVO getOneChatRoom(String mem_id_1,String mem_id_2) {
		return dao.findPrimaryKey(mem_id_1,mem_id_2);
	}

	public List<FriendListVO> getAll() {
		return dao.getAll();
	}
	public static void main(String[] args) {
		FriendListService friendList=new FriendListService();
//	 	friendList.addFriend("ME00002","ME00001","朋友","未加");
//		System.out.println("新增成功");
		friendList.updateFriend("ME00001","ME00002","同學","2");
		System.out.println("修改成功");
	}
}
