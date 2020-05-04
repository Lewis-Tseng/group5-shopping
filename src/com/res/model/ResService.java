package com.res.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ResService {
	private ResDAO_interface dao;

	public ResService() {
		dao = new ResDAO();
	}

	public ResVO addRes(String mem_id, String coa_id, String res_status, java.sql.Date res_day) {

		ResVO resVO = new ResVO();

		resVO.setMem_id(mem_id);
		resVO.setCoa_id(coa_id);
		resVO.setRes_status(res_status);
		resVO.setRes_day(res_day);


		dao.insert(resVO);

		return resVO;
	}

	public ResVO updateRes(String res_no, String mem_id, String coa_id, String res_status, java.sql.Date res_day) {

		ResVO resVO = new ResVO();

		resVO.setRes_no(res_no);
		resVO.setMem_id(mem_id);
		resVO.setCoa_id(coa_id);
		resVO.setRes_status(res_status);
		resVO.setRes_day(res_day);
		
		dao.update(resVO);

		return resVO;
	}
	
	public ResVO updateStatus(String res_no, String res_status) {

		ResVO resVO = new ResVO();

		resVO.setRes_no(res_no);
		resVO.setRes_status(res_status);
		
		dao.updateStatus(resVO);

		return resVO;
	}
	
	

	public void deleteRes(String res_no) {
		dao.delete(res_no);
	}

	public ResVO getOneRes(String res_no) {
		return dao.findByPrimaryKey(res_no);
	}
	
	public List<ResVO> getResDay(String coa_id) {
		return dao.getResDay(coa_id);
	}
	
	public List<ResVO> getResDayMem(String mem_id) {
		return dao.getResDayMem(mem_id);
	}

	public List<ResVO> getAll() {
		return dao.getAll();
	}

}
