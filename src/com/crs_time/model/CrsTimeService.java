package com.crs_time.model;

import java.sql.Timestamp;
import java.util.List;

public class CrsTimeService {
	private CrsTimeDAO_interface dao;

	public CrsTimeService() {
		dao = new CrsTimeDAO();
	}

	public CrsTimeVO addEmp(String crs_no, java.sql.Date crs_date, java.sql.Timestamp start_time,
			java.sql.Timestamp end_time) {

		CrsTimeVO crsTimeVO = new CrsTimeVO();

		crsTimeVO.setCrs_no(crs_no);
		crsTimeVO.setStart_time(start_time);
		crsTimeVO.setEnd_time(end_time);
		crsTimeVO.setCrs_date(crs_date);

		dao.insert(crsTimeVO);

		return crsTimeVO;
	}

	public CrsTimeVO updateEmp(String time_id, String crs_no, java.sql.Date crs_date, java.sql.Timestamp start_time,
			java.sql.Timestamp end_time) {

		CrsTimeVO crsTimeVO = new CrsTimeVO();

		crsTimeVO.setTime_id(time_id);
		crsTimeVO.setCrs_no(crs_no);
		crsTimeVO.setStart_time(start_time);
		crsTimeVO.setEnd_time(end_time);
		crsTimeVO.setCrs_date(crs_date);

		dao.update(crsTimeVO);

		return crsTimeVO;
	}

	public void deleteEmp(String time_id) {
		dao.delete(time_id);
	}

	public CrsTimeVO getOneEmp(String time_id) {
		return dao.findByPrimaryKey(time_id);
	}

	public List<CrsTimeVO> getAll() {
		return dao.getAll();
	}

}
