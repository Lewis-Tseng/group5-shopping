package com.res.model;

import java.util.*;

import com.res.model.ResVO;

public interface ResDAO_interface {
	public void insert(ResVO resVO);

	public void update(ResVO resVO);
	
	public void updateStatus(ResVO resVO);

	public void delete(String res_no);

	public ResVO findByPrimaryKey(String res_no);

	public List<ResVO> getAll();

	public List<ResVO> getResDay(String coa_id);
	
	public List<ResVO> getResDayMem(String mem_id);
	
}
