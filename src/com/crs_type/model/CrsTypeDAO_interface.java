package com.crs_type.model;

import java.util.*;

import com.crs.model.CrsVO;
import com.crs_type.model.CrsTypeVO;


public interface CrsTypeDAO_interface {
	public void insert(CrsTypeVO crstypeVO);

	public void update(CrsTypeVO crstypeVO);

	public void delete(String crs_type_no);

	public CrsTypeVO findByPrimaryKey(String crs_type_no);

	public List<CrsTypeVO> getAll();
	
	public Set<CrsVO> getCrsByCrsTypeNo(String crs_type_no);
}
