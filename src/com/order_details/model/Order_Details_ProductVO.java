package com.order_details.model;

import com.product.model.ProductVO;
import com.product_category.model.Product_CategoryVO;

//為了方便購物車頁面抓取參數，新增此類別當訂單明細與商品參數結合的暫時性擴充類別
public class Order_Details_ProductVO extends ProductVO{

	private Integer pro_quantity;
	
	public Order_Details_ProductVO(Integer pro_no, Product_CategoryVO product_categoryVO, String pro_nam, String pro_con, Integer pro_pri, String pro_sta, Integer pro_sto, Integer pro_quantity) {
		super(pro_no, product_categoryVO, pro_nam, pro_con, pro_pri, pro_sta, pro_sto);
		this.pro_quantity = pro_quantity;
	}

	public Order_Details_ProductVO(ProductVO productVO, Integer pro_quantity) {
		this(productVO.getPro_no(), productVO.getProduct_categoryVO(), productVO.getPro_nam(), productVO.getPro_con(), productVO.getPro_pri(), productVO.getPro_sta(), productVO.getPro_sto(), pro_quantity);
	    this.pro_quantity = pro_quantity;
	}

	public Integer getPro_quantity() {
		return pro_quantity;
	}

	public void setPro_quantity(Integer pro_quantity) {
		this.pro_quantity = pro_quantity;
	}	
	
}
