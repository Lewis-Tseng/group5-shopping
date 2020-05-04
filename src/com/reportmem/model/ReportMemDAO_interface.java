package com.reportmem.model;
import java.util.*;
public interface ReportMemDAO_interface {
	 public void insert(ReportMemVO reportmenVO);
	 public void update(ReportMemVO reportmenVO);
	 public void delete(String rep_id);
	 public ReportMemVO findPrimaryKey(String rep_id);
	 public ReportMemVO findForigenKey(String mem_id);
	 public List<ReportMemVO> getAll();
}
