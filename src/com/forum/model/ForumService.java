package com.forum.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.forum_message.model.Forum_messageVO;

public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}
	//前台使用者新增文章
	public ForumVO addForum( String mem_id, String forum_cls_id, String forum_title, String forum_info, byte[] forum_pic) {

		ForumVO forumVO = new ForumVO();
		forumVO.setMem_id(mem_id);
		forumVO.setForum_cls_id(forum_cls_id);
		forumVO.setForum_title(forum_title);
		forumVO.setForum_info(forum_info);
		forumVO.setForum_pic(forum_pic);
		dao.insert2(forumVO);
		
		return forumVO;
	}

	public ForumVO updateForum(String forum_id, String mem_id, String forum_cls_id, String forum_title, String forum_info, Timestamp forum_time, byte[] forum_pic, Integer forum_hit, String forum_stat, Integer forum_like,  Integer forum_dislike) {

		ForumVO forumVO = new ForumVO();
		forumVO.setForum_id(forum_id);
		forumVO.setMem_id(mem_id);
		forumVO.setForum_cls_id(forum_id);
		forumVO.setForum_title(forum_title);
		forumVO.setForum_info(forum_info);
		forumVO.setForum_time(forum_time);
		forumVO.setForum_pic(forum_pic);
		forumVO.setForum_hit(forum_hit);
		forumVO.setForum_stat(forum_stat);
		forumVO.setForum_like(forum_like);
		forumVO.setForum_dislike(forum_dislike);
		dao.update(forumVO);
		return forumVO;
	}
	//前台使用者文章修改
	public ForumVO updateForum(String forum_cls_id, String forum_title, String forum_info, String forum_id, byte[] forum_pic) {

		ForumVO forumVO = new ForumVO();
		forumVO.setForum_cls_id(forum_cls_id);
		forumVO.setForum_title(forum_title);
		forumVO.setForum_info(forum_info);
		forumVO.setForum_id(forum_id);
		forumVO.setForum_pic(forum_pic);
		dao.update3(forumVO);
		return forumVO;
	}
	//後臺文章狀態修改
	public ForumVO updateForum(String forum_stat, String forum_id) {

		ForumVO forumVO = new ForumVO();
		forumVO.setForum_stat(forum_stat);
		forumVO.setForum_id(forum_id);
		dao.update2(forumVO);
		return forumVO;
	}
	public void deleteForum(String forum_id) {
		dao.delete(forum_id);
	}

	public ForumVO getOneForum(String forum_id) {
		return dao.findByPrimaryKey(forum_id);
	}
	public Integer getOneHit(String forum_id) {
		return dao.getOneHit(forum_id);
	}
	public void setOneHit(String forum_id, Integer forum_hit) {
		dao.setOneHit(forum_id,forum_hit);
	}
	

	

	public List<ForumVO> getAll() {
		return dao.getAll();
	}
//前台閱讀文章時 用文章ID列出所有相關留言
	public Set<Forum_messageVO> getMsgByForum(String forum_id) {
		return dao.getMsgByForum(forum_id);
	}
}
