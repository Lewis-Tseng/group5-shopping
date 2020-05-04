package com.administrator.model;
import java.sql.Blob;
import java.sql.Date;

	
public class AdmVO implements java.io.Serializable{
		private String emp_id;
		private String emp_name;
		private String emp_psw;
		private String emp_email;
		private byte[] emp_pic;
		
		public byte[] getEmp_pic() {
			return emp_pic;
		}
		public void setEmp_pic(byte[] emp_pic) {
			this.emp_pic = emp_pic;
		}
		public String getEmp_email() {
			return emp_email;
		}
		public void setEmp_email(String emp_email) {
			this.emp_email = emp_email;
		}
		public String getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}
		public String getEmp_name() {
			return emp_name;
		}
		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}
		public String getEmp_psw() {
			return emp_psw;
		}
		public void setEmp_psw(String emp_psw) {
			this.emp_psw = emp_psw;
		}
		
		

}
