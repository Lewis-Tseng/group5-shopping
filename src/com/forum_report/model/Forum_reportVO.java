package com.forum_report.model;

import java.sql.Timestamp;

public class Forum_reportVO implements java.io.Serializable{
	private String forum_rep_id;
	private String forum_id;
	private String forum_msg_id;
	private Timestamp forum_rep_time;
	private String forum_rep_info;
	private String forum_rep_stat;
	
	public String getForum_rep_id() {
		return forum_rep_id;
	}
	public void setForum_rep_id(String forum_rep_id) {
		this.forum_rep_id = forum_rep_id;
	}
	public String getForum_id() {
		return forum_id;
	}
	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}
	public String getForum_msg_id() {
		return forum_msg_id;
	}
	public void setForum_msg_id(String forum_msg_id) {
		this.forum_msg_id = forum_msg_id;
	}
	public Timestamp getForum_rep_time() {
		return forum_rep_time;
	}
	public void setForum_rep_time(Timestamp forum_rep_time) {
		this.forum_rep_time = forum_rep_time;
	}
	public String getForum_rep_info() {
		return forum_rep_info;
	}
	public void setForum_rep_info(String forum_rep_info) {
		this.forum_rep_info = forum_rep_info;
	}
	public String getForum_rep_stat() {
		return forum_rep_stat;
	}
	public void setForum_rep_stat(String forum_rep_stat) {
		this.forum_rep_stat = forum_rep_stat;
	}
	
	
	
}
