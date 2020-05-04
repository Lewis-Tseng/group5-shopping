package com.gro.report;

import java.util.List;


public class reportService {

	private reportDAO_interface dao;

	public reportService() {
		dao = new reportDAO();
	}

	public reportVO addRep(String gro_id,String gro_rep_info) {

		reportVO reportVO = new reportVO();

		reportVO.setGro_id(gro_id);
		reportVO.setGro_rep_info(gro_rep_info);
		
		dao.insert(reportVO);

		return reportVO;
	}

	public reportVO updateRep(Integer gro_rep_stat,String gro_repid) {

		reportVO reportVO = new reportVO();

		reportVO.setGro_rep_stat(gro_rep_stat);
		reportVO.setGro_repid(gro_repid);
		
		dao.update(reportVO);

		return reportVO;
	}

	public void deleteRep(String gro_repid) {
		dao.delete(gro_repid);
	}

	public reportVO getOneRep(String gro_repid) {
		return dao.findByPrimaryKey(gro_repid);
	}

	public List<reportVO> getAll() {
		return dao.getAll();
	}
}
