package com.deposit.model;

import java.util.List;

import com.deposit.model.DepVO;

public interface DepDAO_interface {
	public void insert(DepVO depVO);
    public void update(DepVO depVO);
    public void delete(String dep_id);
    public DepVO findByPrimaryKey(String dep_id);
    public List<DepVO> getAll();
    public void updateSta(DepVO depVO);

}
