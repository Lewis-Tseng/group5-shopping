package com.crs_time.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CrsTimeVO implements java.io.Serializable {
	private String time_id, crs_no;
	private Date  crs_date;
	private Timestamp start_time, end_time;
	public String getTime_id() {
		return time_id;
	}
	public void setTime_id(String time_id) {
		this.time_id = time_id;
	}
	public String getCrs_no() {
		return crs_no;
	}
	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}
	public Date getCrs_date() {
		return crs_date;
	}
	public void setCrs_date(Date crs_date) {
		this.crs_date = crs_date;
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

	

}
