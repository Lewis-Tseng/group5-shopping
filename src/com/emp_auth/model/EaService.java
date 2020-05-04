package com.emp_auth.model;

import java.util.List;
import java.util.Set;

public class EaService {
	
private EaDAO_interface dao;
	
	public EaService() {
		dao = new EaJDBCDAO();
	}
	public EaVO addEa(String emp_id,String auth_id) {

		EaVO eaVO = new EaVO();
		eaVO.setEmp_id(emp_id);
		eaVO.setAuth_id(auth_id);
			
		dao.insert(eaVO);

		return eaVO;
	}
	public EaVO addEa(String auth_id) {

		EaVO eaVO = new EaVO();

		eaVO.setAuth_id(auth_id);
			
		dao.insert(eaVO);

		return eaVO;
	}
	
	public EaVO updateEa(String emp_id,String auth_id) {

		EaVO eaVO = new EaVO();

		eaVO.setEmp_id(emp_id);
		eaVO.setAuth_id(auth_id);
			
		dao.update(eaVO);

		return eaVO;
	}
	
	public void deleteEa(String emp_id,String auth_id) {
		dao.delete(emp_id,auth_id);
	}
	
	public Set<EaVO> getAuthsByEmp(String emp_id) {
		return dao.getAuthsByEmp(emp_id);
	}
	
	public List<EaVO> getAll() {
		return dao.getAll();
	}

}
