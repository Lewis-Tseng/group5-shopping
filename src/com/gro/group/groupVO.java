package com.gro.group;

import java.sql.Date;

public class groupVO  implements java.io.Serializable{
	
	private String gro_id;
	private String gro_cid;
	private String mem_id;
	private String gro_name;
	private Date gro_sig_stime;
	private Date gro_sig_ftime;
	private Date gro_stime;
	private Date gro_ftime;
	private Integer gro_mnum;
	private Integer gro_mnum_min;
	private Integer gro_mnum_max;
	private String gro_info;
	private Integer gro_stat;
	private Integer gro_astar;
	private byte[] gro_logo_pic;
	public String getGro_id() {
		return gro_id;
	}
	public void setGro_id(String gro_id) {
		this.gro_id = gro_id;
	}
	public String getGro_cid() {
		return gro_cid;
	}
	public void setGro_cid(String gro_cid) {
		this.gro_cid = gro_cid;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGro_name() {
		return gro_name;
	}
	public void setGro_name(String gro_name) {
		this.gro_name = gro_name;
	}
	public Date getGro_sig_stime() {
		return gro_sig_stime;
	}
	public void setGro_sig_stime(Date gro_sig_stime) {
		this.gro_sig_stime = gro_sig_stime;
	}
	public Date getGro_sig_ftime() {
		return gro_sig_ftime;
	}
	public void setGro_sig_ftime(Date gro_sig_ftime) {
		this.gro_sig_ftime = gro_sig_ftime;
	}
	public Date getGro_stime() {
		return gro_stime;
	}
	public void setGro_stime(Date gro_stime) {
		this.gro_stime = gro_stime;
	}
	public Date getGro_ftime() {
		return gro_ftime;
	}
	public void setGro_ftime(Date gro_ftime) {
		this.gro_ftime = gro_ftime;
	}
	public Integer getGro_mnum() {
		return gro_mnum;
	}
	public void setGro_mnum(Integer gro_mnum) {
		this.gro_mnum = gro_mnum;
	}
	public Integer getGro_mnum_min() {
		return gro_mnum_min;
	}
	public void setGro_mnum_min(Integer gro_mnum_min) {
		this.gro_mnum_min = gro_mnum_min;
	}
	public Integer getGro_mnum_max() {
		return gro_mnum_max;
	}
	public void setGro_mnum_max(Integer gro_mnum_max) {
		this.gro_mnum_max = gro_mnum_max;
	}
	public String getGro_info() {
		return gro_info;
	}
	public void setGro_info(String gro_info) {
		this.gro_info = gro_info;
	}
	public Integer getGro_stat() {
		return gro_stat;
	}
	public void setGro_stat(Integer gro_stat) {
		this.gro_stat = gro_stat;
	}
	public Integer getGro_astar() {
		return gro_astar;
	}
	public void setGro_astar(Integer gro_astar) {
		this.gro_astar = gro_astar;
	}
	public byte[] getGro_logo_pic() {
		return gro_logo_pic;
	}
	public void setGro_logo_pic(byte[] gro_logo_pic) {
		this.gro_logo_pic = gro_logo_pic;
	}
	


}
