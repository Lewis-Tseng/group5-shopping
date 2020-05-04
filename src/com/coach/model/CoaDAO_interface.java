package com.coach.model;

import java.util.List;

import com.coach.model.CoaVO;
import com.mem.model.MemVO;


public interface CoaDAO_interface {
	public void insert(CoaVO coaVO);
    public void update(CoaVO coaVO);
    public void delete(String coa_id);
    public CoaVO findByPrimaryKey(String coa_id);
    public List<CoaVO> getAll();
    public String findCoachByAccount(String coa_email);
    public void update_CoaPer(CoaVO coaVO);
    public CoaVO findCoachByAccount2(String coa_email);
    public void updateCoaPoint(String coa_id, Integer coa_point);
    public CoaVO findCoaPoint(String coa_id);
    public void insert2(CoaVO coaVO);


}
