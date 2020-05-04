package com.mem.model;

import java.sql.Blob;
import java.sql.Date;

public class MemVO implements java.io.Serializable{
	private String mem_id;
	private String mem_name;
	private String mem_gender;
	private String phone;
	private String address;
	private String pos_code;
	private String mem_email;
	private String mem_psw;
	private Date   birthday;
	private byte[] mem_pic;
	private String mem_sta;
	private Integer mem_point;
	
	public Integer getMem_point() {
		return mem_point;
	}
	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPos_code() {
		return pos_code;
	}
	public void setPos_code(String pos_code) {
		this.pos_code = pos_code;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_psw() {
		return mem_psw;
	}
	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getMem_sta() {
		return mem_sta;
	}
	public void setMem_sta(String mem_sta) {
		this.mem_sta = mem_sta;
	}
	
}