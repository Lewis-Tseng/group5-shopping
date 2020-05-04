package com.forum_class.model;

import java.util.List;

public class Forum_classService {

	private Forum_classDAO_interface dao;

	public Forum_classService() {
		dao = new Forum_classDAO();
	}

	public Forum_classVO addForum_class(String forum_cls_id, String forum_cls_nam) {

		Forum_classVO forum_classVO = new Forum_classVO();

		forum_classVO.setForum_cls_nam(forum_cls_nam);
		forum_classVO.setForum_cls_id(forum_cls_nam);
		dao.insert(forum_classVO);
		
		return forum_classVO;
	}

	public Forum_classVO updateForum_class(String forum_cls_id,String forum_cls_nam) {

		Forum_classVO forum_classVO = new Forum_classVO();
		forum_classVO.setForum_cls_id(forum_cls_nam);
		forum_classVO.setForum_cls_nam(forum_cls_nam);
		dao.update(forum_classVO);
		return forum_classVO;
	}

	public void deleteForum_class(String forum_cls_id) {
		dao.delete(forum_cls_id);
	}

	public Forum_classVO getOneForum_class(String forum_cls_id) {
		return dao.findByPrimaryKey(forum_cls_id);
	}

	public List<Forum_classVO> getAll() {
		return dao.getAll();
	}
}
