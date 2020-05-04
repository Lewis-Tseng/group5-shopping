package com.mem.model;

import java.util.List;

import com.mem.model.MemVO;

public interface MemDAO_interface {
	public void insert(MemVO memVO);
	public void insert2(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String mem_id);
    public MemVO findByPrimaryKey(String mem_id);
    public List<MemVO> getAll();
    public String findMemberByAccount(String mem_email);
    public void update_MemPer(MemVO memVO);
    public MemVO findMemberByAccount2(String mem_email);
    public void updateAddPoint(String mem_no, Integer mem_point);
    public List<String> getAllEmail();
    public void updatePsw(String mem_email,String  mem_psw);
}
