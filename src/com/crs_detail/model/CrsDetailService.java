package com.crs_detail.model;

import java.util.List;

public class CrsDetailService {
	private CrsDetailDAO_interface dao;

	public CrsDetailService() {
		dao = new CrsDetailDAO();
	}

	public CrsDetailVO addCrsDetail(String crs_no, String mem_id, String crs_name, Integer crs_fee) {

		CrsDetailVO crsDetailVO = new CrsDetailVO();
		
		crsDetailVO.setCrs_no(crs_no);
		crsDetailVO.setMem_id(mem_id);
		crsDetailVO.setCrs_name(crs_name);
		crsDetailVO.setCrs_fee(crs_fee);

		dao.insert(crsDetailVO);

		return crsDetailVO;
	}

	public CrsDetailVO updateCrsDetail(String crs_no, String mem_id, String crs_name, Integer crs_fee) {

		CrsDetailVO crsDetailVO = new CrsDetailVO();

		crsDetailVO.setCrs_no(crs_no);
		crsDetailVO.setMem_id(mem_id);
		crsDetailVO.setCrs_name(crs_name);
		crsDetailVO.setCrs_fee(crs_fee);

		dao.update(crsDetailVO);

		return crsDetailVO;
	}

	public void deleteCrsDetail(String crs_no, String mem_id) {
		dao.delete(crs_no, mem_id);
	}

	public CrsDetailVO getOneCrsDetail(String crs_no, String mem_id) {
		return dao.findByPrimaryKey(crs_no, mem_id);
	}	

	public List<CrsDetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<CrsDetailVO> getReserved(String mem_id) {
		return dao.getReserved(mem_id);
	}
	public List<CrsDetailVO> findByCrsNOAll (String mem_id) {
		return dao.findByCrsNOAll(mem_id);
	}
}
