package com.reportcoa.model;

import java.util.List;

public interface ReportCoaDAO_interface {
	public void insert(ReportCoaVO reportCoaVO);
	public void update(ReportCoaVO reportCoaVO);
	
	public void delete(String rep_id);
	public ReportCoaVO findPrimaryKey(String rep_id);
	public ReportCoaVO findForeignKey(String coa_id);
	List<ReportCoaVO> getAll();
}
