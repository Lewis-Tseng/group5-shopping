package com.reportcrs.model;

public class CourseReportVO implements java.io.Serializable {
	private String crs_repid, crs_no, rep_content, rep_status;
	

	public String getCrs_repid() {
		return crs_repid;
	}

	public void setCrs_repid(String crs_repid) {
		this.crs_repid = crs_repid;
	}

	public String getCrs_no() {
		return crs_no;
	}

	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}

	public String getRep_status() {
		return rep_status;
	}

	public void setRep_status(String rep_status) {
		this.rep_status = rep_status;
	}
}
