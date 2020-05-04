package com.coach.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class CoaService {

private CoaDAO_interface dao;
	
	public CoaService() {
		dao = new CoaDAO();
	}
	
	public CoaVO addCoa(String coa_name, String coa_gender, String coa_email,String coa_psw,
			String expert,byte[] license,byte[] coa_pic,String coa_sta,String coa_intro,String coa_video,Integer coa_point) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoa_name(coa_name);
		coaVO.setCoa_gender(coa_gender);
		coaVO.setCoa_email(coa_email);
		coaVO.setCoa_psw(coa_psw);
		coaVO.setExpert(expert);
		coaVO.setLicense(license);
		coaVO.setCoa_pic(coa_pic);
		coaVO.setCoa_sta(coa_sta);
		coaVO.setCoa_intro(coa_intro);
		coaVO.setCoa_video(coa_video);
		coaVO.setCoa_point(coa_point);
		
		
		dao.insert(coaVO);

		return coaVO;
	}
	public CoaVO addcoareg(String coa_name, String coa_gender, String coa_email,String coa_psw,byte[] license,String coa_sta) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoa_name(coa_name);
		coaVO.setCoa_gender(coa_gender);
		coaVO.setCoa_email(coa_email);
		coaVO.setCoa_psw(coa_psw);
		coaVO.setLicense(license);
		coaVO.setCoa_sta("0");
		
		
		dao.insert2(coaVO);

		return coaVO;
	}

	
	public CoaVO updateCoa(String coa_id,String coa_name, String coa_gender, String coa_email,String coa_psw,
			String expert,byte[] license,byte[] coa_pic,String coa_sta,String coa_intro,String coa_video,Integer coa_point) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoa_id(coa_id);
		coaVO.setCoa_name(coa_name);
		coaVO.setCoa_gender(coa_gender);
		coaVO.setCoa_email(coa_email);
		coaVO.setCoa_psw(coa_psw);
		coaVO.setExpert(expert);
		coaVO.setLicense(license);
		coaVO.setCoa_pic(coa_pic);
		coaVO.setCoa_sta(coa_sta);	
		coaVO.setCoa_intro(coa_intro);
		coaVO.setCoa_video(coa_video);
		coaVO.setCoa_point(coa_point);
		dao.update(coaVO);

		return coaVO;
	}
	
	public void deleteCoa(String coa_id) {
		dao.delete(coa_id);
	}
	
	public CoaVO getOneCoa(String coa_id) {
		return dao.findByPrimaryKey(coa_id);
	}
	
	public List<CoaVO> getAll() {
		return dao.getAll();
	}
	public CoaVO addcoareg(String coa_name, String coa_gender, String coa_email,String coa_psw,byte[] license) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoa_name(coa_name);
		coaVO.setCoa_gender(coa_gender);
		coaVO.setCoa_email(coa_email);
		coaVO.setCoa_psw(coa_psw);
		coaVO.setLicense(license);
		
		
		dao.insert(coaVO);

		return coaVO;
	}
	public CoaVO UpdateCoaPer(String coa_email,String coa_name,String expert,byte[] license,byte[] coa_pic,
			String coa_intro,String coa_video) {

		CoaVO coaVO = new CoaVO();

		coaVO.setCoa_email(coa_email);
		coaVO.setCoa_name(coa_name);
		coaVO.setExpert(expert);
		coaVO.setLicense(license);
		coaVO.setCoa_pic(coa_pic);	
		coaVO.setCoa_intro(coa_intro);
		coaVO.setCoa_video(coa_video);
		
		dao.update_CoaPer(coaVO);

		return coaVO;
	}
	public String getCoaPsw(String coa_email) {
		return dao.findCoachByAccount(coa_email);
	}
	public CoaVO getCoaPsw2(String coa_email) {
		return dao.findCoachByAccount2(coa_email);
	}
	public void updateCoapoint(String coa_id,Integer coa_point) {
		CoaVO coaVO = new CoaVO();
		coaVO.setCoa_point(coa_point);
		coaVO.setCoa_id(coa_id);
		dao.updateCoaPoint(coa_id,coa_point);
		
	}
	
	public CoaVO getCoaPoint(String coa_id) {
		return dao.findCoaPoint(coa_id);
	}

}
