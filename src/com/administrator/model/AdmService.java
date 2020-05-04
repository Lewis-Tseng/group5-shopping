package com.administrator.model;

import java.util.List;
import java.util.Map;

public class AdmService {
	
private AdmDAO_interface dao;
	
	public AdmService() {
		dao = new AdmDAO();
	}
	public AdmVO getOneEmpName(String emp_name) {
		return dao.findByInsertName(emp_name);
	}

	public AdmVO addAdm(String emp_name) {

		AdmVO admVO = new AdmVO();

		admVO.setEmp_name(emp_name);
	
		
		
		dao.insert1(admVO);

		return admVO;
	}
	
	public AdmVO addAdm(String emp_name,String emp_psw,String emp_email,byte[] emp_pic) {

		AdmVO admVO = new AdmVO();

		admVO.setEmp_name(emp_name);
		admVO.setEmp_psw(emp_psw);
		admVO.setEmp_email(emp_email);
		admVO.setEmp_pic(emp_pic);
		dao.insert(admVO);
		return admVO;
	}
	
	public AdmVO updateAdm(String emp_id,String emp_name,String emp_psw) {

		AdmVO admVO = new AdmVO();

		admVO.setEmp_id(emp_id);
		admVO.setEmp_name(emp_name);
		admVO.setEmp_psw(emp_psw);
		
		
		dao.update(admVO);

		return admVO;
	}
	
	public void deleteAdm(String emp_id) {
		dao.delete(emp_id);
	}
	
	public AdmVO getOneAdm(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	public List<AdmVO> getAll() {
		return dao.getAll();
	}
	
	public List<AdmVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
