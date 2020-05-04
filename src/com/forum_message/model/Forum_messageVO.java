package com.forum_message.model;

import java.sql.Timestamp;

public class Forum_messageVO implements java.io.Serializable{
	private String forum_msg_id;
	private String mem_id;
	private String forum_id;
	private Timestamp forum_msg_time;
	private String forum_msg_info;
	private byte[] forum_msg_pic;
	private String forum_msg_stat;
	private Integer forum_msg_like;
	private Integer forum_msg_dislike;
	
	public String getForum_msg_id() {
		return forum_msg_id;
	}
	public void setForum_msg_id(String forum_msg_id) {
		this.forum_msg_id = forum_msg_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getForum_id() {
		return forum_id;
	}
	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}
	public Timestamp getForum_msg_time() {
		return forum_msg_time;
	}
	public void setForum_msg_time(Timestamp forum_msg_time) {
		this.forum_msg_time = forum_msg_time;
	}
	public String getForum_msg_info() {
		return forum_msg_info;
	}
	public void setForum_msg_info(String forum_msg_info) {
		this.forum_msg_info = forum_msg_info;
	}
	public byte[] getForum_msg_pic() {
		return forum_msg_pic;
	}
	public void setForum_msg_pic(byte[] forum_msg_pic) {
		this.forum_msg_pic = forum_msg_pic;
	}
	public String getForum_msg_stat() {
		return forum_msg_stat;
	}
	public void setForum_msg_stat(String forum_msg_stat) {
		this.forum_msg_stat = forum_msg_stat;
	}
	public Integer getForum_msg_like() {
		return forum_msg_like;
	}
	public void setForum_msg_like(Integer forum_msg_like) {
		this.forum_msg_like = forum_msg_like;
	}
	public Integer getForum_msg_dislike() {
		return forum_msg_dislike;
	}
	public void setForum_msg_dislike(Integer forum_msg_dislike) {
		this.forum_msg_dislike = forum_msg_dislike;
	}

	
}
