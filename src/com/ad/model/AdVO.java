package com.ad.model;

import java.sql.Clob;
import java.sql.Date;

public class AdVO implements java.io.Serializable {
	private String ad_id;
	private String pro_no;
	private byte[] ad_pic;
	private String ad_title;
	private String ad_info;
	private Date   ad_date_on;
	private Date   ad_date_off;
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public byte[] getAd_pic() {
		return ad_pic;
	}
	public void setAd_pic(byte[] ad_pic) {
		this.ad_pic = ad_pic;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public String getAd_info() {
		return ad_info;
	}
	public void setAd_info(String ad_info) {
		this.ad_info = ad_info;
	}
	public Date getAd_date_on() {
		return ad_date_on;
	}
	public void setAd_date_on(Date ad_date_on) {
		this.ad_date_on = ad_date_on;
	}
	public Date getAd_date_off() {
		return ad_date_off;
	}
	public void setAd_date_off(Date ad_date_off) {
		this.ad_date_off = ad_date_off;
	}

	
	
	
}
