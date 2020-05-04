package com.res.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ResVO implements java.io.Serializable {
	private String res_no, mem_id, coa_id, res_status;
	private Date res_day;
	
	public String getRes_no() {
		return res_no;
	}
	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCoa_id() {
		return coa_id;
	}
	public void setCoa_id(String coa_id) {
		this.coa_id = coa_id;
	}
	public String getRes_status() {
		return res_status;
	}
	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}
	public Date getRes_day() {
		return res_day;
	}
	public void setRes_day(Date res_day) {
		this.res_day = res_day;
	}
	
}
