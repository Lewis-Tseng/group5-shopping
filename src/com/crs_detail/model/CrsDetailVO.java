package com.crs_detail.model;

public class CrsDetailVO implements java.io.Serializable {
	private String crs_no, mem_id, crs_name;
	private Integer crs_fee;
	public String getCrs_no() {
		return crs_no;
	}
	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCrs_name() {
		return crs_name;
	}
	public void setCrs_name(String crs_name) {
		this.crs_name = crs_name;
	}
	public Integer getCrs_fee() {
		return crs_fee;
	}
	public void setCrs_fee(Integer crs_fee) {
		this.crs_fee = crs_fee;
	}
	
	
}
