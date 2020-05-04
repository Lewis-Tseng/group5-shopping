package com.gro.joingroup;

import java.util.List;
import java.util.Set;

import com.gro.type.groclassVO;




public class joingroupService {

	private joingroupDAO_interface dao;

	public joingroupService() {
		dao = new joingroupDAO();
	}

	public Integer getMnum(String gro_id) {
		return dao.getmnum(gro_id);
	}
	
	public Integer getMnumMin(String gro_id) {
		return dao.getmnumMin(gro_id);
	}
	
	public void updateMnum(Integer gro_mnum,String gro_id) {
		dao.updatemnum(gro_mnum,gro_id);
	}
	
	public void updateStat(Integer gro_stat,String gro_id) {
		dao.updatstat(gro_stat,gro_id);
	}
	
	public joingroupVO addMem(String gro_id,String mem_id) {

		joingroupVO joingroupVO = new joingroupVO();

		joingroupVO.setGro_id(gro_id);
		joingroupVO.setMem_id(mem_id);
		dao.insert(joingroupVO);

		return joingroupVO;
	}

	public void deleteGroMem(String gro_id, String mem_id) {
		dao.delete(gro_id,mem_id);
	}
	
	public joingroupVO getOneStar(String gro_id, String mem_id) {
		return dao.findByPrimaryKey(gro_id,mem_id);
	}
	
	
	public joingroupVO updateStar(String gro_id,String mem_id,Integer gro_star) {

		joingroupVO joingroupVO = new joingroupVO();

		joingroupVO.setGro_id(gro_id);
		joingroupVO.setMem_id(mem_id);
		joingroupVO.setGro_star(gro_star);
		
		dao.updatestar(joingroupVO);

		return joingroupVO;
	}

	public Set<joingroupVO> getAllMems(String gro_id) {
		return dao.getMemsByGro(gro_id);
	}
	
	public Set<joingroupVO> getAllGros(String mem_id) {
		return dao.getGrosByMem(mem_id);
	}

	public List<joingroupVO> getAll() {
		return dao.getAll();
	}
	
	public void updateAstar(String gro_id) {
		dao.updateastar(gro_id);
	}
	
	public joingroupVO findOneInfo(String gro_id,String mem_id) {
		return dao.findByPrimaryKey(gro_id, mem_id);
	}
}
