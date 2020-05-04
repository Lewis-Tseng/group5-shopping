package com.authority.model;

import java.util.List;

public class AuthService {
	
private AuthDAO_interface dao;
	
	public AuthService() {
		dao = new AuthDAO();
	}
	
	public AuthVO addAuth(String auth_name) {

		AuthVO authVO = new AuthVO();

		authVO.setAuth_name(auth_name);
			
		dao.insert(authVO);

		return authVO;
	}
	
	public AuthVO updateAuth(String auth_id,String auth_name) {

		AuthVO authVO = new AuthVO();

		authVO.setAuth_id(auth_id);
		authVO.setAuth_name(auth_name);
			
		dao.update(authVO);

		return authVO;
	}
	
	public void deleteAuth(String auth_id) {
		dao.delete(auth_id);
	}
	
	public AuthVO getOneAuth(String auth_id) {
		return dao.findByPrimaryKey(auth_id);
	}
	
	public List<AuthVO> getAll() {
		return dao.getAll();
	}

}
