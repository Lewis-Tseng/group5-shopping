package com.friendListOLD.model;
import java.io.*;
public class FriendListVO implements Serializable {
	private String mem_id_1;
	private String mem_id_2;
	private String frd_memo;
	private String frd_add;
	public String getMem_id_1() {
		return mem_id_1;
	}
	public void setMem_id_1(String mem_id_1) {
		this.mem_id_1 = mem_id_1;
	}
	public String getMem_id_2() {
		return mem_id_2;
	}
	public void setMem_id_2(String mem_id_2) {
		this.mem_id_2 = mem_id_2;
	}
	public String getFrd_memo() {
		return frd_memo;
	}
	public void setFrd_memo(String frd_memo) {
		this.frd_memo = frd_memo;
	}
	public String getFrd_add() {
		return frd_add;
	}
	public void setFrd_add(String frd_add) {
		this.frd_add = frd_add;
	}
	
}
