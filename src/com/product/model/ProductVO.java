package com.product.model;

public class ProductVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String pro_no;
	private String cat_no;
	private String pro_nam;
	private String pro_con;
	private Integer pro_pri;
	private String pro_sta;
	private Integer pro_sto;
	
	public ProductVO(){
		
	}
	
	public ProductVO(String pro_no, String cat_no, String pro_nam, String pro_con, Integer pro_pri, String pro_sta,
			Integer pro_sto) {
		this.pro_no = pro_no;
		this.cat_no = cat_no;
		this.pro_nam = pro_nam;
		this.pro_con = pro_con;
		this.pro_pri = pro_pri;
		this.pro_sta = pro_sta;
		this.pro_sto = pro_sto;
	}

	public String getPro_no() {
		return pro_no;
	}

	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}

	public String getCat_no() {
		return cat_no;
	}

	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}

	public String getPro_nam() {
		return pro_nam;
	}

	public void setPro_nam(String pro_nam) {
		this.pro_nam = pro_nam;
	}

	public String getPro_con() {
		return pro_con;
	}

	public void setPro_con(String pro_con) {
		this.pro_con = pro_con;
	}

	public Integer getPro_pri() {
		return pro_pri;
	}

	public void setPro_pri(Integer pro_pri) {
		this.pro_pri = pro_pri;
	}

	public String getPro_sta() {
		return pro_sta;
	}

	public void setPro_sta(String pro_sta) {
		this.pro_sta = pro_sta;
	}

	public Integer getPro_sto() {
		return pro_sto;
	}

	public void setPro_sto(Integer pro_sto) {
		this.pro_sto = pro_sto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pro_no == null) ? 0 : pro_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		if (pro_no == null) {
			if (other.pro_no != null)
				return false;
		} else if (!pro_no.equals(other.pro_no))
			return false;
		return true;
	}


	
	@Override
	public String toString() {
		return "ProductVO [pro_no=" + pro_no + ", cat_no=" + cat_no + ", pro_nam=" + pro_nam + ", pro_con=" + pro_con +
				", pro_pri=" + pro_pri + ", pro_sta=" + pro_sta + ", pro_sto=" + pro_sto + "]";
	}

	
}
