package com.administrator.model;

import java.util.List;
import java.util.Map;

import com.administrator.model.AdmVO;

public interface AdmDAO_interface {
	public void insert(AdmVO empVO);
	public void insert1(AdmVO empVO);
    public void update(AdmVO empVO);
    public void delete(String emp_id);
    public AdmVO findByPrimaryKey(String emp_id);
    public List<AdmVO> getAll();
    public List<AdmVO> getAll(Map<String, String[]> map);
    public AdmVO findByInsertName(String emp_name);

}
