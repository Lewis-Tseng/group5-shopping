package com.forum_message.model;

import java.sql.Timestamp;
import java.util.List;

public class Forum_messageService {

	private Forum_messageDAO_interface dao;

	public Forum_messageService() {
		dao = new Forum_messageDAO();
	}
//	前台使用者 新增留言
	public Forum_messageVO addForum_message(String mem_id, String forum_id, String forum_msg_info, byte[] forum_msg_pic) {

		Forum_messageVO forum_messageVO = new Forum_messageVO();

		forum_messageVO.setMem_id(mem_id);
		forum_messageVO.setForum_id(forum_id);
		forum_messageVO.setForum_msg_info(forum_msg_info);
		forum_messageVO.setForum_msg_pic(forum_msg_pic);

		dao.insert2(forum_messageVO);
		
		return forum_messageVO;
	}
	
	public Forum_messageVO addForum_message(String forum_msg_id, String mem_id, String forum_id, Timestamp forum_msg_time, String forum_msg_info, byte[] forum_msg_pic, String forum_msg_stat, Integer forum_msg_like,  Integer forum_msg_dislike) {

		Forum_messageVO forum_messageVO = new Forum_messageVO();

		forum_messageVO.setForum_msg_id(forum_msg_id);
		forum_messageVO.setMem_id(mem_id);
		forum_messageVO.setForum_id(forum_id);
		forum_messageVO.setForum_msg_time(forum_msg_time);
		forum_messageVO.setForum_msg_info(forum_msg_info);
		forum_messageVO.setForum_msg_pic(forum_msg_pic);
		forum_messageVO.setForum_msg_stat(forum_msg_stat);
		forum_messageVO.setForum_msg_like(forum_msg_like);
		forum_messageVO.setForum_msg_dislike(forum_msg_dislike);
		dao.insert(forum_messageVO);
		
		return forum_messageVO;
	}
//前台使用者編輯留言
	public Forum_messageVO updateForum_message(String forum_msg_info, byte[] forum_msg_pic, String forum_msg_id) {

		Forum_messageVO forum_messageVO = new Forum_messageVO();
		forum_messageVO.setForum_msg_info(forum_msg_info);
		forum_messageVO.setForum_msg_pic(forum_msg_pic);
		forum_messageVO.setForum_msg_id(forum_msg_id);
		dao.update2(forum_messageVO);
		return forum_messageVO;
	}
//前台使用者刪除留言(實為下架)
	public Forum_messageVO updateForum_message(String forum_msg_stat, String forum_msg_id) {

		Forum_messageVO forum_messageVO = new Forum_messageVO();
		forum_messageVO.setForum_msg_stat(forum_msg_stat);
		forum_messageVO.setForum_msg_id(forum_msg_id);
		dao.update3(forum_messageVO);
		return forum_messageVO;
	}
	public Forum_messageVO updateForum_message(String forum_msg_id, String mem_id, String forum_id, Timestamp forum_msg_time, String forum_msg_info, byte[] forum_msg_pic, String forum_msg_stat, Integer forum_msg_like,  Integer forum_msg_dislike) {

		Forum_messageVO forum_messageVO = new Forum_messageVO();
		forum_messageVO.setForum_msg_id(forum_msg_id);
		forum_messageVO.setMem_id(mem_id);
		forum_messageVO.setForum_id(forum_id);
		forum_messageVO.setForum_msg_time(forum_msg_time);
		forum_messageVO.setForum_msg_info(forum_msg_info);
		forum_messageVO.setForum_msg_pic(forum_msg_pic);
		forum_messageVO.setForum_msg_stat(forum_msg_stat);
		forum_messageVO.setForum_msg_like(forum_msg_like);
		forum_messageVO.setForum_msg_dislike(forum_msg_dislike);
		dao.update(forum_messageVO);
		return forum_messageVO;
	}

	public void deleteForum_message(String forum_msg_id) {
		dao.delete(forum_msg_id);
	}

	public Forum_messageVO getOneForum_message(String forum_msg_id) {
		return dao.findByPrimaryKey(forum_msg_id);
	}

	public List<Forum_messageVO> getAll() {
		return dao.getAll();
	}
}
