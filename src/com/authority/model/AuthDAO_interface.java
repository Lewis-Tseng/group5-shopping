package com.authority.model;

import java.util.List;
import java.util.Set;

import com.authority.model.AuthVO;
import com.emp_auth.model.EaVO;



public interface AuthDAO_interface {
	public void insert(AuthVO authVO);
    public void update(AuthVO authVO);
    public void delete(String auth_id);
    public AuthVO findByPrimaryKey(String auth_id);
    public List<AuthVO> getAll();
    
	
}
