package com.forum_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Forum_reportService {

	private Forum_reportDAO_interface dao;

	public Forum_reportService() {
		dao = new Forum_reportDAO();
	}

	public Forum_reportVO addForum_report(String forum_rep_id,String forum_id,String forum_msg_id,Timestamp forum_rep_time,String forum_rep_info,String forum_rep_stat) {

		Forum_reportVO forum_reportVO = new Forum_reportVO();

		forum_reportVO.setForum_rep_id(forum_rep_id);
		forum_reportVO.setForum_id(forum_id);
		forum_reportVO.setForum_msg_id(forum_msg_id);
		forum_reportVO.setForum_rep_time(forum_rep_time);
		forum_reportVO.setForum_rep_info(forum_rep_info);
		forum_reportVO.setForum_rep_stat(forum_rep_stat);

		dao.insert(forum_reportVO);
		
		return forum_reportVO;
	}

	public Forum_reportVO updateEmp(String forum_rep_id,String forum_id,String forum_msg_id,Timestamp forum_rep_time,String forum_rep_info,String forum_rep_stat) {

		Forum_reportVO forum_reportVO = new Forum_reportVO();
		forum_reportVO.setForum_rep_id(forum_rep_id);
		forum_reportVO.setForum_id(forum_id);
		forum_reportVO.setForum_msg_id(forum_msg_id);
		forum_reportVO.setForum_rep_time(forum_rep_time);
		forum_reportVO.setForum_rep_info(forum_rep_info);
		forum_reportVO.setForum_rep_stat(forum_rep_stat);
		dao.update(forum_reportVO);
		return forum_reportVO;
	}

	public void deleteForum_Report(String forum_rep_id) {
		dao.delete(forum_rep_id);
	}

	public Forum_reportVO getOneForum_Report(String FORUM_REP_ID) {
		return dao.findByPrimaryKey(FORUM_REP_ID);
	}

	public List<Forum_reportVO> getAll() {
		return dao.getAll();
	}
}
