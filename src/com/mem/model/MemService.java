package com.mem.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
	public MemVO addForum(
			String mem_id,
			String mem_name,
			String mem_gender,
			String phone,
			String address,
			String pos_code,
			String mem_email,
			String mem_psw,
			Date   birthday,
			byte[] mem_pic,
			String mem_sta,
			Integer mem_point) {

		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setPhone(phone);
		memVO.setAddress(address);
		memVO.setPos_code(pos_code);
		memVO.setMem_email(mem_email);
		memVO.setMem_psw(mem_psw);
		memVO.setBirthday(birthday);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_sta(mem_sta);
		memVO.setMem_point(mem_point);
		dao.insert(memVO);
		
		return memVO;
	}
	public MemVO addMem(String mem_name, String mem_gender, String mem_email,String mem_psw,String phone,String address,String pos_code,Date birthday,
			byte[] mem_pic,String mem_sta,Integer mem_point) {

		MemVO memVO = new MemVO();

		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_psw(mem_psw);
		memVO.setPhone(phone);
		memVO.setAddress(address);
		memVO.setPos_code(pos_code);
		memVO.setBirthday(birthday);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_sta(mem_sta);
		memVO.setMem_point(mem_point);
		dao.insert(memVO);

		return memVO;
	}
	
	public MemVO addmemreg(String mem_name, String mem_gender, String mem_email,String mem_psw) {

		MemVO memVO = new MemVO();

		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_psw(mem_psw);
//		memVO.setPhone(null);
//		memVO.setAddress(null);
//		memVO.setPos_code(null);
//		memVO.setBirthday(null);
//		memVO.setMem_pic(null);
//		memVO.setMem_sta(null);
		
		dao.insert2(memVO);

		return memVO;
	}
	public MemVO UpdateMemPer(String mem_email,String mem_name,String phone,String address,String pos_code,Date birthday,
			byte[] mem_pic) {

		MemVO memVO = new MemVO();

		memVO.setMem_email(mem_email);
		memVO.setMem_name(mem_name);
		memVO.setPhone(phone);
		memVO.setAddress(address);
		memVO.setPos_code(pos_code);
		memVO.setBirthday(birthday);
		memVO.setMem_pic(mem_pic);
		
		dao.update_MemPer(memVO);

		return memVO;
	}
	
	public MemVO updateMem(String mem_id,String mem_name, String mem_gender, String mem_email,String mem_psw,String phone,String address,String pos_code,Date birthday,
			byte[] mem_pic,String mem_sta,Integer mem_point) {

		MemVO memVO = new MemVO();

		memVO.setMem_id(mem_id);
		memVO.setMem_name(mem_name);
		memVO.setMem_gender(mem_gender);
		memVO.setMem_email(mem_email);
		memVO.setMem_psw(mem_psw);
		memVO.setPhone(phone);
		memVO.setAddress(address);
		memVO.setPos_code(pos_code);
		memVO.setBirthday(birthday);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_sta(mem_sta);
		memVO.setMem_point(mem_point);
		dao.update(memVO);

		return memVO;
	}
	
	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
	}
	
	public MemVO getOneMem(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
public void updatePoint(String mem_id, Integer mem_point) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_point(mem_point);

		dao.updateAddPoint(mem_id, mem_point);
	}
	
	
	public String getMemPsw(String mem_email) {
		return dao.findMemberByAccount(mem_email);
	}
	public MemVO getMemPsw2(String mem_email) {
		return dao.findMemberByAccount2(mem_email);
	}
	public List<String> getAllEmail() {
		return dao.getAllEmail();
	}

public void updatePsw(String mem_email, String mem_psw) {
	
	MemVO memVO = new MemVO();
	
	memVO.setMem_email(mem_email);
	memVO.setMem_psw(mem_psw);

	dao.updatePsw(mem_email, mem_psw);
}

    
}
