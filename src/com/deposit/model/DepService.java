package com.deposit.model;


import java.sql.Timestamp;
import java.util.List;

public class DepService {
	
private DepDAO_interface dao;
	
	public DepService() {
		dao = new DepDAO();
	}
	
	public DepVO addDep(String mem_id, String coa_id,Integer dep_money,
			Timestamp dep_day,String dep_sta) {

		DepVO depVO = new DepVO();

		depVO.setMem_id(mem_id);
		depVO.setCoa_id(coa_id);
		depVO.setDep_money(dep_money);
		depVO.setDep_day(dep_day);
		depVO.setDep_sta(dep_sta);
		dao.insert(depVO);

		return depVO;
	}
	public DepVO addDep2(String mem_id, int dep_money,Timestamp dep_day) {

		DepVO depVO = new DepVO();

		depVO.setMem_id(mem_id);

		depVO.setDep_money(dep_money);
		
		depVO.setDep_day(dep_day);
		
		dao.insert(depVO);

		return depVO;
	}
	
	public DepVO updateDep(String dep_id,String mem_id, String coa_id,Integer dep_money,
			Timestamp dep_day,String dep_sta) {

		DepVO depVO = new DepVO();

		depVO.setDep_id(dep_id);
		depVO.setMem_id(mem_id);
		depVO.setCoa_id(coa_id);
		depVO.setDep_money(dep_money);
		depVO.setDep_day(dep_day);
		depVO.setDep_sta(dep_sta);
		dao.update(depVO);

		return depVO;
	}
	
	public void deleteDep(String dep_id) {
		dao.delete(dep_id);
	}
	
	public DepVO getOneDep(String dep_id) {
		return dao.findByPrimaryKey(dep_id);
	}
	
	public List<DepVO> getAll() {
		return dao.getAll();
	}
	
	public void updateSta(String dep_id,String dep_sta) {

		DepVO depVO = new DepVO();

		depVO.setDep_id(dep_id);
		depVO.setDep_sta(dep_sta);
		dao.updateSta(depVO);

	}


}
