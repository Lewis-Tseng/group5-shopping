package com.crs.model;

import java.sql.*;

public class CrsVO implements java.io.Serializable {
	private String crs_no, coa_id, crs_type_no, crs_name, week_crs, crs_content;
	private Integer crs_fee , limit, reserved;
	private Date start_date, end_date;
	private byte[] crs_img;
	public String getCrs_no() {
		return crs_no;
	}
	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}
	public String getCoa_id() {
		return coa_id;
	}
	public void setCoa_id(String coa_id) {
		this.coa_id = coa_id;
	}
	public String getCrs_type_no() {
		return crs_type_no;
	}
	public void setCrs_type_no(String crs_type_no) {
		this.crs_type_no = crs_type_no;
	}
	public String getCrs_name() {
		return crs_name;
	}
	public void setCrs_name(String crs_name) {
		this.crs_name = crs_name;
	}
	public String getWeek_crs() {
		return week_crs;
	}
	public void setWeek_crs(String week_crs) {
		this.week_crs = week_crs;
	}
	public String getCrs_content() {
		return crs_content;
	}
	public void setCrs_content(String crs_content) {
		this.crs_content = crs_content;
	}
	public Integer getCrs_fee() {
		return crs_fee;
	}
	public void setCrs_fee(Integer crs_fee) {
		this.crs_fee = crs_fee;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getReserved() {
		return reserved;
	}
	public void setReserved(Integer reserved) {
		this.reserved = reserved;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public byte[] getCrs_img() {
		return crs_img;
	}
	public void setCrs_img(byte[] crs_img) {
		this.crs_img = crs_img;
	}
	public Double getStar() {
		return star;
	}
	public void setStar(Double star) {
		this.star = star;
	}
	public Double getStar_total() {
		return star_total;
	}
	public void setStar_total(Double star_total) {
		this.star_total = star_total;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	private Double star, star_total ;
	private Timestamp start_time, end_time;
	
}




