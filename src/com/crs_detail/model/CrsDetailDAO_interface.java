package com.crs_detail.model;

import java.util.*;
import com.crs_detail.model.CrsDetailVO;

public interface CrsDetailDAO_interface {
	public void insert(CrsDetailVO crsdetailVO);

	public void update(CrsDetailVO crsdetailVO);

	public void delete(String crs_no,String mem_id);

	public CrsDetailVO findByPrimaryKey(String crs_no,String mem_id);	

	public List<CrsDetailVO> getAll();
	public List<CrsDetailVO> findByCrsNOAll(String mem_id);
	public List<CrsDetailVO> getReserved(String mem_id);
	
}
