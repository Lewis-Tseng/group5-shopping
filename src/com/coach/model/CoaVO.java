package com.coach.model;



public class CoaVO implements java.io.Serializable{
	private String coa_id;
	private String coa_name;
	private String coa_gender;
	private String coa_email;
	private String coa_psw;
	private String expert;
	private byte[] license;
	private byte[] coa_pic;
	private String coa_sta;
	private String coa_intro;
	private String coa_video;
	private Integer coa_point;
	public Integer getCoa_point() {
		return coa_point;
	}
	public void setCoa_point(Integer coa_point) {
		this.coa_point = coa_point;
	}
	public String getCoa_id() {
		return coa_id;
	}
	public void setCoa_id(String coa_id) {
		this.coa_id = coa_id;
	}
	public String getCoa_name() {
		return coa_name;
	}
	public void setCoa_name(String coa_name) {
		this.coa_name = coa_name;
	}
	public String getCoa_gender() {
		return coa_gender;
	}
	public void setCoa_gender(String coa_gender) {
		this.coa_gender = coa_gender;
	}
	public String getCoa_email() {
		return coa_email;
	}
	public void setCoa_email(String coa_email) {
		this.coa_email = coa_email;
	}
	public String getCoa_psw() {
		return coa_psw;
	}
	public void setCoa_psw(String coa_psw) {
		this.coa_psw = coa_psw;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	public byte[] getLicense() {
		return license;
	}
	public void setLicense(byte[] license) {
		this.license = license;
	}
	public byte[] getCoa_pic() {
		return coa_pic;
	}
	public void setCoa_pic(byte[] coa_pic) {
		this.coa_pic = coa_pic;
	}
	public String getCoa_sta() {
		return coa_sta;
	}
	public void setCoa_sta(String coa_sta) {
		this.coa_sta = coa_sta;
	}
	public String getCoa_intro() {
		return coa_intro;
	}
	public void setCoa_intro(String coa_intro) {
		this.coa_intro = coa_intro;
	}
	public String getCoa_video() {
		return coa_video;
	}
	public void setCoa_video(String coa_video) {
		this.coa_video = coa_video;
	}
	

	
	
}
