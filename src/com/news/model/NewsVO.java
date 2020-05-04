package com.news.model;

import java.sql.Timestamp;

public class NewsVO implements java.io.Serializable {
	private String news_id;
	private String news_title;
	private String news_info;
	private byte[] news_pic;
	private Timestamp news_time;
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_info() {
		return news_info;
	}
	public void setNews_info(String news_info) {
		this.news_info = news_info;
	}
	public byte[] getNews_pic() {
		return news_pic;
	}
	public void setNews_pic(byte[] news_pic) {
		this.news_pic = news_pic;
	}
	public Timestamp getNews_time() {
		return news_time;
	}
	public void setNews_time(Timestamp news_time) {
		this.news_time = news_time;
	}
}
