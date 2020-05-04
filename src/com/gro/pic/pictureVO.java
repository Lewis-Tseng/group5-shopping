package com.gro.pic;

public class pictureVO implements java.io.Serializable{
	
	private String gro_pid;
	private String gro_id;
	private byte[] gro_pic;
	
	
	public String getGro_pid() {
		return gro_pid;
	}
	public void setGro_pid(String gro_pid) {
		this.gro_pid = gro_pid;
	}
	public String getGro_id() {
		return gro_id;
	}
	public void setGro_id(String gro_id) {
		this.gro_id = gro_id;
	}
	public byte[] getGro_pic() {
		return gro_pic;
	}
	public void setGro_pic(byte[] gro_pic) {
		this.gro_pic = gro_pic;
	}
	
	
}
