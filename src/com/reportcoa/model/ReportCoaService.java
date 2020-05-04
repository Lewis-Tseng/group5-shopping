package com.reportcoa.model;

import java.util.List;
public class ReportCoaService {
	private ReportCoaDAO_interface dao;
	public  ReportCoaService () {
		dao=new ReportCoaDAO();
	}
	public ReportCoaVO  addReportcoa(String coa_id,String rep_cot) {
		ReportCoaVO reportcoa=new ReportCoaVO();
		
		reportcoa.setCoa_id(coa_id);
		reportcoa.setRep_cot(rep_cot);
	
		dao.insert(reportcoa);
		return reportcoa ;
	}
	public ReportCoaVO  updateReportcoaStatus(String rep_id,String rep_sta) {
		ReportCoaVO reportcoa=new ReportCoaVO();
		reportcoa.setRep_id(rep_id);
		reportcoa.setRep_sta(rep_sta);
		dao.update(reportcoa);
		return reportcoa ;
	}
	public ReportCoaVO  updateReportcoa(String rep_id,String coa_id,String rep_cot,String rep_sta) {
		ReportCoaVO reportcoa=new ReportCoaVO();
		reportcoa.setRep_id(rep_id);
		reportcoa.setCoa_id(coa_id);
		reportcoa.setRep_cot(rep_cot);
		reportcoa.setRep_sta(rep_sta);
		dao.update(reportcoa);
		return reportcoa ;
	}
	public void deleteReportcoa(String rep_id) {
		dao.delete(rep_id);
	}
	public ReportCoaVO getOneReportmem(String rep_id) {
		return dao.findPrimaryKey(rep_id);
	} 
	public ReportCoaVO getOneReportmem1(String coa_id) {
		return dao.findForeignKey(coa_id);
	}
	public List<ReportCoaVO> getAll() {
		return dao.getAll();
	}
	public static void main(String[] args) {
		ReportCoaService reportcoa=new ReportCoaService();
//		reportcoa.addReportcoa("RC00005","CO00002","長太美了","未處理");
//		System.out.println("新增成功");
		reportcoa.updateReportcoa("RC00001","CO00001","長太美了","0處理");
		System.out.println("修改成功");
	}
	

}
