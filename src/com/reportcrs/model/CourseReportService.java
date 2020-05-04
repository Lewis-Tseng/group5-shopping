package com.reportcrs.model;

import java.util.List;

public class CourseReportService {
	private CourseReportDAO_interface dao;

	public CourseReportService() {
		dao = new CourseReportDAO();
	}

	public CourseReportVO addCourseReport(String crs_no, String rep_content) {
		CourseReportVO courseReportVO = new CourseReportVO();
		courseReportVO.setCrs_no(crs_no);
		courseReportVO.setRep_content(rep_content);
		dao.insert(courseReportVO);
		return courseReportVO;
	}
	

	public CourseReportVO updateCourseReportStatus(String crs_repid, String rep_status) {

		CourseReportVO courseReportVO = new CourseReportVO();

		courseReportVO.setCrs_repid(crs_repid);
		courseReportVO.setRep_status(rep_status);

		dao.update(courseReportVO);

		return courseReportVO;
	}
	public CourseReportVO updateCourseReport(String crs_repid, String crs_no, String rep_content, String rep_status) {

		CourseReportVO courseReportVO = new CourseReportVO();

		courseReportVO.setCrs_repid(crs_repid);
		courseReportVO.setCrs_no(crs_no);
		courseReportVO.setRep_content(rep_content);
		courseReportVO.setRep_status(rep_status);

		dao.update(courseReportVO);

		return courseReportVO;
	}

	public void deleteCourseReport(String crs_repid) {
		dao.delete(crs_repid);
	}

	public CourseReportVO getOneCrs(String crs_repid) {
		return dao.findByPrimaryKey(crs_repid);
	}
	public CourseReportVO getOneCrs1(String crs_no) {
		return dao.findByForeignKey(crs_no);
	}
	public List<CourseReportVO> getAll() {
		return dao.getAll();
	}

}
