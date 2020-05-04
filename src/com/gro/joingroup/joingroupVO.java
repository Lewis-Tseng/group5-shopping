package com.gro.joingroup;

public class joingroupVO implements java.io.Serializable{
	
	private String mem_id;
	private String gro_id;
	private Integer gro_mnum;
	private Integer gro_star;
	
	private Integer gro_astar;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGro_id() {
		return gro_id;
	}
	public void setGro_id(String gro_id) {
		this.gro_id = gro_id;
	}
	public Integer getGro_mnum() {
		return gro_mnum;
	}
	public void setGro_mnum(Integer gro_mnum) {
		this.gro_mnum = gro_mnum;
	}
	public Integer getGro_star() {
		return gro_star;
	}
	public void setGro_star(Integer gro_star) {
		this.gro_star = gro_star;
	}
	public Integer getGro_astar() {
		return gro_astar;
	}
	public void setGro_astar(Integer gro_astar) {
		this.gro_astar = gro_astar;
	}
	
	
}
