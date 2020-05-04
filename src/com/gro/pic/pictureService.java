package com.gro.pic;

import java.util.List;
import java.util.Set;



public class pictureService {

	private pictureDAO_interface dao;

	public pictureService() {
		dao = new pictureDAO();
	}

	public pictureVO addPic(String gro_id,byte[] gro_pic) {

		pictureVO pictureVO = new pictureVO();

		pictureVO.setGro_id(gro_id);
		pictureVO.setGro_pic(gro_pic);
		
		dao.insert(pictureVO);

		return pictureVO;
	}

	public void deletePic(String gro_pid) {
		dao.delete(gro_pid);
	}

	public Set<pictureVO> getAllPics(String gro_id) {
		return dao.getPicsByGro(gro_id);
	}

	public List<pictureVO> getAll() {
		return dao.getAll();
	}
}
