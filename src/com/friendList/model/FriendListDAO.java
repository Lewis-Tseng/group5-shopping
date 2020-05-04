package com.friendList.model;

import java.util.*;



import redis.clients.jedis.Jedis;

public class FriendListDAO implements FriendList_interface{
	
	@Override
	public List<FriendListVO> getMyAll(String my_id) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		try {
			
			List<FriendListVO> list = new LinkedList<FriendListVO>();
				
				
					for (String xxx : jedis.hkeys("friendList:" + my_id)) {
										
							FriendListVO friendListVO = new FriendListVO();
							String yyy = jedis.hget("friendList:" + my_id, xxx);
							friendListVO.setHis_id(xxx);
							friendListVO.setFriend_stat(yyy);
							list.add(friendListVO);
							
					}
					return list;
		} finally {

			if (jedis != null) {

				jedis.close();

			}
		}
	}
	
	@Override
	public String getOneStat(String my_id,String his_id) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		try {
			
			String stat=jedis.hget("friendList:" + my_id, his_id);;
				
				
					return stat;
		} finally {

			if (jedis != null) {

				jedis.close();

			}
		}
	}
}
	