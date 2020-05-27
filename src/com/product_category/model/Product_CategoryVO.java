package com.product_category.model;

import java.util.HashSet;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_CategoryVO {
    private Integer cat_no;
    private String cat_nam;  
	private Set<ProductVO> products = new HashSet<ProductVO>();
    		
	public Integer getCat_no() {
		return cat_no;
	}
	public void setCat_no(Integer cat_no) {
		this.cat_no = cat_no;
	}
	public String getCat_nam() {
		return cat_nam;
	}
	public void setCat_nam(String cat_nam) {
		this.cat_nam = cat_nam;
	}
	
	public Set<ProductVO> getProducts(){
		return products;
	}
	
	public void setProducts(Set<ProductVO> products) {
		this.products = products;
	}
}
