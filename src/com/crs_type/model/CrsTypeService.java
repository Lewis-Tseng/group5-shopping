package com.crs_type.model;

import java.util.List;
import java.util.Set;

import com.crs.model.CrsVO;

public class CrsTypeService {
	private CrsTypeDAO_interface dao;

	public CrsTypeService() {
		dao = new CrsTypeDAO();
	}

	public void deleteCrsType(String crs_type_no) {
		dao.delete(crs_type_no);
	}

	public CrsTypeVO getOneCrsType(String crs_type_no) {
		return dao.findByPrimaryKey(crs_type_no);
	}

	public List<CrsTypeVO> getAll() {
		return dao.getAll();
	}

	public Set<CrsVO> getCrsByCrsTypeNo(String crs_type_no) {
		return dao.getCrsByCrsTypeNo(crs_type_no);
	}

}
