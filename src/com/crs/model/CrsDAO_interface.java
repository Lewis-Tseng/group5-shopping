package com.crs.model;

import java.util.*;
import com.crs.model.CrsVO;


public interface CrsDAO_interface {

	public void insert(CrsVO crsVO);

	public void update(CrsVO crsVO);
	
	public void updateReserved(CrsVO crsVO);

	public void delete(String crs_no);

	public CrsVO findByPrimaryKey(String crs_no);
	
	public CrsVO findReserved(String crs_no);

	public List<CrsVO> getAll();
	
	public List<CrsVO> getAllCrs(String coa_id);	

}
