package com.reportcrs.model;

import java.util.*;


public interface CourseReportDAO_interface {
	public void insert(CourseReportVO coursereportVO);

	public void update(CourseReportVO coursereportVO);

	public void delete(String crs_repid);

	public CourseReportVO findByPrimaryKey(String crs_repid);
	public CourseReportVO findByForeignKey(String crs_no);
	public List<CourseReportVO> getAll();
}
