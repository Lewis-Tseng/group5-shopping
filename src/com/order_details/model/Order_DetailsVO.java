package com.order_details.model;

public class Order_DetailsVO implements java.io.Serializable{

	private String ord_no;
	private String pro_no;
	private Integer quantity;
	private Integer uni_pri;

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public String getPro_no() {
		return pro_no;
	}

	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
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
		return "[訂單明細= 訂單編號:" + ord_no + ", 商品編號:" + pro_no + ", 單項商品總數量:" + quantity + 
				", 單項商品價格:" + uni_pri + "]";
	}
	
	

}
