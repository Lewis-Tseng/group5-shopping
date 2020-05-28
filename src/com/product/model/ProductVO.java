package com.product.model;

import java.util.HashSet;
import java.util.Set;

import com.product_category.model.Product_CategoryVO;
import com.product_image.model.Product_ImageVO;

public class ProductVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer pro_no;
	private String pro_nam;
	private String pro_con;
	private Integer pro_pri;
	private String pro_sta;
	private Integer pro_sto;
	private Product_CategoryVO product_categoryVO;
	private Set<Product_ImageVO> product_images = new HashSet<Product_ImageVO>();
	
	public ProductVO(){
		
	}
	
	public ProductVO(Integer pro_no, Product_CategoryVO product_categoryVO, String pro_nam, String pro_con, Integer pro_pri, String pro_sta,
			Integer pro_sto) {
		this.pro_no = pro_no;	
		this.product_categoryVO = product_categoryVO;
		this.pro_nam = pro_nam;
		this.pro_con = pro_con;
		this.pro_pri = pro_pri;
		this.pro_sta = pro_sta;
		this.pro_sto = pro_sto;
	}

	public Integer getPro_no() {
		return pro_no;
	}

	public void setPro_no(Integer pro_no) {
		this.pro_no = pro_no;
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
	
	public Product_CategoryVO getProduct_categoryVO() {
		return product_categoryVO;
	}

	public void setProduct_categoryVO(Product_CategoryVO product_categoryVO) {
		this.product_categoryVO = product_categoryVO;
	}

	public Set<Product_ImageVO> getProduct_images() {
		return product_images;
	}

	public void setProduct_images(Set<Product_ImageVO> product_images) {
		this.product_images = product_images;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pro_nam == null) ? 0 : pro_nam.hashCode());
		return result;
	}

	@Override
	// 比對欲加入商品與購物車內商品的編號是否相同，true則值相同
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof ProductVO)) {
			return true;
		}
		return this.getPro_no().equals(((ProductVO)obj).getPro_no());
	}
	

	@Override
	public String toString() {
		return "ProductVO [pro_no=" + pro_no + ", cat_no=" + product_categoryVO + ", pro_nam=" + pro_nam + ", pro_con=" + pro_con +
				", pro_pri=" + pro_pri + ", pro_sta=" + pro_sta + ", pro_sto=" + pro_sto + "]";
	}

	
}
