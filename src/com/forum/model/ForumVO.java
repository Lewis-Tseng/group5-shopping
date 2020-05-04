package com.forum.model;

import java.sql.Timestamp;

public class ForumVO implements java.io.Serializable{
	private String forum_id;
	private String mem_id;
	private String forum_cls_id;
	private String forum_title;
	private String forum_info;
	private Timestamp forum_time;
	private byte[] forum_pic;
	private Integer forum_hit;
	private String forum_stat;
	private Integer forum_like;
	private Integer forum_dislike;
	public String getForum_id() {
		return forum_id;
	}
	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getForum_cls_id() {
		return forum_cls_id;
	}
	public void setForum_cls_id(String forum_cls_id) {
		this.forum_cls_id = forum_cls_id;
	}
	public String getForum_title() {
		return forum_title;
	}
	public void setForum_title(String forum_title) {
		this.forum_title = forum_title;
	}
	public String getForum_info() {
		return forum_info;
	}
	public void setForum_info(String forum_info) {
		this.forum_info = forum_info;
	}
	public Timestamp getForum_time() {
		return forum_time;
	}
	public void setForum_time(Timestamp forum_time) {
		this.forum_time = forum_time;
	}
	public byte[] getForum_pic() {
		return forum_pic;
	}
	public void setForum_pic(byte[] forum_pic) {
		this.forum_pic = forum_pic;
	}
	public Integer getForum_hit() {
		return forum_hit;
	}
	public void setForum_hit(Integer forum_hit) {
		this.forum_hit = forum_hit;
	}
	public String getForum_stat() {
		return forum_stat;
	}
	public void setForum_stat(String forum_stat) {
		this.forum_stat = forum_stat;
	}
	public Integer getForum_like() {
		return forum_like;
	}
	public void setForum_like(Integer forum_like) {
		this.forum_like = forum_like;
	}
	public Integer getForum_dislike() {
		return forum_dislike;
	}
	public void setForum_dislike(Integer forum_dislike) {
		this.forum_dislike = forum_dislike;
	}
	
}
