package com.gro.group;

import java.sql.Date;
import java.util.List;
import java.util.Set;


public class groupService {

	private groupDAO_interface dao;

	public groupService() {
		dao = new groupDAO();
	}

	public groupVO addGro(String gro_cid, String mem_id,String gro_name, Date gro_sig_ftime,Date gro_stime, Date gro_ftime, Integer gro_mnum_min,Integer gro_mnum_max, String gro_info,byte[] gro_logo_pic) {

		groupVO groupVO = new groupVO();

		groupVO.setGro_cid(gro_cid);
		groupVO.setMem_id(mem_id);
		groupVO.setGro_name(gro_name);
		groupVO.setGro_sig_ftime(gro_sig_ftime);
		groupVO.setGro_stime(gro_stime);
		groupVO.setGro_ftime(gro_ftime);
		groupVO.setGro_mnum_min(gro_mnum_min);
		groupVO.setGro_mnum_max(gro_mnum_max);
		groupVO.setGro_info(gro_info);
		groupVO.setGro_logo_pic(gro_logo_pic);
		
		dao.insert(groupVO);

		return groupVO;
	}

	public groupVO updateGro(String gro_id,String gro_cid,String mem_id,String gro_name,Date gro_sig_ftime,Date gro_stime,Date gro_ftime,Integer gro_mnum,Integer gro_mnum_min,Integer gro_mnum_max,String gro_info,byte[] gro_logo_pic) {

		groupVO groupVO = new groupVO();

		groupVO.setGro_id(gro_id);
		groupVO.setGro_cid(gro_cid);
		groupVO.setMem_id(mem_id);
		groupVO.setGro_name(gro_name);
		groupVO.setGro_sig_ftime(gro_sig_ftime);
		groupVO.setGro_stime(gro_stime);
		groupVO.setGro_ftime(gro_ftime);
		groupVO.setGro_mnum(gro_mnum);
		groupVO.setGro_mnum_min(gro_mnum_min);
		groupVO.setGro_mnum_max(gro_mnum_max);
		groupVO.setGro_info(gro_info);
		groupVO.setGro_logo_pic(gro_logo_pic);
		
		dao.update(groupVO);

		return groupVO;
	}

	public void deleteGro(String gro_id) {
		dao.delete(gro_id);
	}

	public groupVO getOneGro(String gro_id) {
		return dao.findByPrimaryKey(gro_id);
	}
	
	public groupVO getOneGroWithStat(String gro_id) {
		return dao.findByPrimaryKeyStat(gro_id);
	}
	
	public groupVO getOneGroWithStatHistory(String gro_id) {
		return dao.findByPrimaryKeyStatHistory(gro_id);
	}
	
	public List<groupVO> getGroByMem(String mem_id){
		return dao.getGrosByMem(mem_id);
	}
	
	public List<groupVO> getGroByMemHistory(String mem_id){
		return dao.getGrosByMemHistory(mem_id);
	}

	public List<groupVO> getAll() {
		return dao.getAll();
	}
	
	public void finishGroup() {
		dao.updateTodayBeforeStat();
	}
}
