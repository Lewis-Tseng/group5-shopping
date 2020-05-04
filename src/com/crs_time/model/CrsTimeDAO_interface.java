package com.crs_time.model;

import java.util.*;
import com.crs_time.model.CrsTimeVO;

public interface CrsTimeDAO_interface {
	public void insert(CrsTimeVO crstimeVO);

	public void update(CrsTimeVO crstimeVO);

	public void delete(String time_id);

	public CrsTimeVO findByPrimaryKey(String time_id);

	public List<CrsTimeVO> getAll();
}
