package com.deposit.model;

import java.sql.*;

public class DepVO implements java.io.Serializable{
	private String dep_id;
	private String mem_id;
	private String coa_id;
	private Integer dep_money;
	private Timestamp dep_day;
	private String dep_sta;
	public String getDep_id() {
		return dep_id;
	}
	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
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
	public Integer getDep_money() {
		return dep_money;
	}
	public void setDep_money(Integer dep_money) {
		this.dep_money = dep_money;
	}
	public Timestamp getDep_day() {
		return dep_day;
	}
	public void setDep_day(Timestamp dep_day) {
		this.dep_day = dep_day;
	}
	public String getDep_sta() {
		return dep_sta;
	}
	public void setDep_sta(String dep_sta) {
		this.dep_sta = dep_sta;
	}
	
	

}
