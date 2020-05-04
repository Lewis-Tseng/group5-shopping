package com.reportmem.model;
import java.util.List;
public class ReportMemService {
	private ReportMemDAO_interface dao;
	public ReportMemService () {
		dao=new ReportMemDAO();
	}
	public  ReportMemVO addReportmem(String mem_id,String rep_cot,String rep_sta) {
		ReportMemVO reportmemvo=new ReportMemVO();
		
		 reportmemvo.setMem_id(mem_id);
		 reportmemvo.setRep_cot(rep_cot);
		 reportmemvo.setRep_sta(rep_sta);
		 dao.insert(reportmemvo);
		return reportmemvo;
	}
	public  ReportMemVO addReportmem(String rep_id,String mem_id,String rep_cot,String rep_sta) {
		ReportMemVO reportmemvo=new ReportMemVO();
		 reportmemvo.setRep_id(rep_id);
		 reportmemvo.setMem_id(mem_id);
		 reportmemvo.setRep_cot(rep_cot);
		 reportmemvo.setRep_sta(rep_sta);
		 dao.insert(reportmemvo);
		return reportmemvo;
	} 
	public ReportMemVO updateReportmem(String rep_id,String mem_id,String rep_cot,String rep_sta) {
		ReportMemVO reportmemvo=new ReportMemVO();
		 reportmemvo.setRep_id(rep_id);
		 reportmemvo.setMem_id(mem_id);
		 reportmemvo.setRep_cot(rep_cot);
		 reportmemvo.setRep_sta(rep_sta);
		 dao.update(reportmemvo);
		return reportmemvo;
	} 
	public void deleteReportmem(String rep_id) {
		dao.delete(rep_id);
	}
	public ReportMemVO getOneReportmem(String rep_id) {
		return dao.findPrimaryKey(rep_id);
	} 
	public ReportMemVO getOneReportmem1(String mem_id) {
		return dao.findForigenKey(mem_id);
	} 
	public List<ReportMemVO> getAll() {
		return dao.getAll();
	}
	public static void main(String[] args) {
		ReportMemService report=new ReportMemService ();
//		report.addReportmem("0","ME00002","長太醜","未處理");
		report.updateReportmem("RM00003","ME00002", "長太醜", "已處理");
		System.out.println("修改成功");
	}
}
