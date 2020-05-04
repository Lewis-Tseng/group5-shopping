package com.crs.model;

import java.sql.Timestamp;
import java.util.List;

public class CrsService {
	private CrsDAO_interface dao;

	public CrsService() {
		dao = new CrsDAO();
	}

	public CrsVO addCrs(String coa_id, String crs_type_no, String crs_name, String week_crs,
			String crs_content, Integer crs_fee, 
			java.sql.Date start_date, java.sql.Date end_date, java.sql.Timestamp start_time,
			java.sql.Timestamp end_time,Integer limit, byte[] crs_img) {

		CrsVO crsVO = new CrsVO();
		
		crsVO.setCoa_id(coa_id);
		crsVO.setCrs_type_no(crs_type_no);
		crsVO.setCrs_name(crs_name);
		crsVO.setCrs_fee(crs_fee);
		crsVO.setCrs_content(crs_content);
		crsVO.setStart_date(start_date);
		crsVO.setEnd_date(end_date);
		crsVO.setWeek_crs(week_crs);
		crsVO.setStart_time(start_time);
		crsVO.setEnd_time(end_time);
		crsVO.setCrs_img(crs_img);
		crsVO.setLimit(limit);

		dao.insert(crsVO);

		return crsVO;
	}

	public CrsVO updateCrs(String crs_no, String coa_id, String crs_type_no, String crs_name, 
			String week_crs,String crs_content, Integer crs_fee, 
			java.sql.Date start_date, java.sql.Date end_date,
			java.sql.Timestamp start_time, java.sql.Timestamp end_time, byte[] crs_img) {

		CrsVO crsVO = new CrsVO();
		
		crsVO.setCrs_no(crs_no);
		crsVO.setCoa_id(coa_id);
		crsVO.setCrs_type_no(crs_type_no);
		crsVO.setCrs_name(crs_name);
		crsVO.setCrs_fee(crs_fee);
		crsVO.setCrs_content(crs_content);
		crsVO.setStart_date(start_date);
		crsVO.setEnd_date(end_date);
		crsVO.setWeek_crs(week_crs);
		crsVO.setStart_time(start_time);
		crsVO.setEnd_time(end_time);
		crsVO.setCrs_img(crs_img);
		
		dao.update(crsVO);

		return crsVO;
	}
	
	public CrsVO updateReserved(String crs_no, Integer reserved) {
		
		CrsVO crsVO = new CrsVO();
		
		crsVO.setCrs_no(crs_no);
		crsVO.setReserved(reserved);
		dao.updateReserved(crsVO);
		return crsVO;
	}

	public void deleteCrs(String crs_no) {
		dao.delete(crs_no);
	}

	public CrsVO getOneCrs(String crs_no) {
		return dao.findByPrimaryKey(crs_no);
	}
	
	public CrsVO findReserved(String crs_no) {
		return dao.findReserved(crs_no); 
	}

	public List<CrsVO> getAll() {
		return dao.getAll();
	}
	
	public List<CrsVO> getAllCrs(String coa_id) {
		return dao.getAllCrs(coa_id);
	}

}
