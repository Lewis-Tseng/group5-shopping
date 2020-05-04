package com.emp_auth.model;

import java.util.List;
import java.util.Set;

import com.administrator.model.AdmVO;
import com.emp_auth.model.EaVO;

public interface EaDAO_interface {
	public void insert(EaVO eaVO);
    public void update(EaVO eaVO);
    public void delete(String emp_id,String auth_id);
    public Set<EaVO> getAuthsByEmp(String emp_id);
    public List<EaVO> getAll();
   
    
}
