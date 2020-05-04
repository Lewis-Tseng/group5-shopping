package com.order_details.model;

import com.product.model.ProductVO;

public class Order_Details_ProductVO extends ProductVO{

	private Integer pro_quantity;
	
	public Order_Details_ProductVO(String pro_no, String cat_no, String pro_nam, String pro_con, Integer pro_pri, String pro_sta, Integer pro_sto, Integer pro_quantity) {
		super(pro_no, cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto);
		this.pro_quantity = pro_quantity;
	}

	public Order_Details_ProductVO(ProductVO productVO, Integer pro_quantity) {
		this(productVO.getPro_no(), productVO.getCat_no(), productVO.getPro_nam(), productVO.getPro_con(), productVO.getPro_pri(), productVO.getPro_sta(), productVO.getPro_sto(), pro_quantity);
	    this.pro_quantity = pro_quantity;
	}

	public Integer getPro_quantity() {
		return pro_quantity;
	}

	public void setPro_quantity(Integer pro_quantity) {
		this.pro_quantity = pro_quantity;
	}	
	
}
