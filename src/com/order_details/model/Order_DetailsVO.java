package com.order_details.model;

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

public class Order_DetailsVO implements java.io.Serializable{

	private Product_OrderVO product_orderVO;
	private ProductVO productVO;
	private Integer quantity;
	private Integer uni_pri;

	public Product_OrderVO getProduct_orderVO() {
		return product_orderVO;
	}

	public void setProduct_orderVO(Product_OrderVO product_orderVO) {
		this.product_orderVO = product_orderVO;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUni_pri() {
		return uni_pri;
	}

	public void setUni_pri(Integer uni_pri) {
		this.uni_pri = uni_pri;
	}
	


	@Override
	public String toString() {
		return "[訂單明細= 訂單編號:" + product_orderVO.getOrd_no() + ", 商品編號:" + productVO.getPro_no() + ", 單項商品總數量:" + quantity + 
				", 單項商品價格:" + uni_pri + "]";
	}
	
	

}
