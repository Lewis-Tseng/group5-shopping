package com.gro.type;

import java.util.List;


public class groclassService {

	private groclassDAO_interface dao;

	public groclassService() {
		dao = new groclassDAO();
	}

	public groclassVO addClass(String gro_cname) {

		groclassVO groclassVO = new groclassVO();

		groclassVO.setGro_cname(gro_cname);
		
		
		dao.insert(groclassVO);

		return groclassVO;
	}

	public groclassVO updateClass(String gro_cname,String gro_cid) {

		groclassVO groclassVO = new groclassVO();

		groclassVO.setGro_cname(gro_cname);
		groclassVO.setGro_cid(gro_cid);
		
		dao.update(groclassVO);

		return groclassVO;
	}

	public void deleteClass(String gro_cid) {
		dao.delete(gro_cid);
	}

	public groclassVO getOneClass(String gro_cid) {
		return dao.findByPrimaryKey(gro_cid);
	}

	public List<groclassVO> getAll() {
		return dao.getAll();
	}
}
