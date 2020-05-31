package com.order_details.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

@Entity  //要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "order_details") //代表這個class是對應到資料庫的實體table，目前對應的table是EMP2
public class Order_DetailsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Product_OrderVO product_orderVO;
	private ProductVO productVO;
	private Integer quantity;
	private Integer uni_pri;
	
	public Order_DetailsVO() {	
	}

//	@ManyToOne
//	@JoinColumns({
//		    @JoinColumn(name = "ord_no", referencedColumnName = "ord_no"),
//			@JoinColumn(name = "pro_no", referencedColumnName = "pro_no") 
//		    })
	@ManyToOne
	@JoinColumn(name = "ord_no")
	public Product_OrderVO getProduct_orderVO() {
		return product_orderVO;
	}

	public void setProduct_orderVO(Product_OrderVO product_orderVO) {
		this.product_orderVO = product_orderVO;
	}
	
	@ManyToOne
	@JoinColumn(name = "pro_no")
	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "uni_pri")
	public Integer getUni_pri() {
		return uni_pri;
	}

	public void setUni_pri(Integer uni_pri) {
		this.uni_pri = uni_pri;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productVO == null) ? 0 : productVO.hashCode());
		result = prime * result + ((product_orderVO == null) ? 0 : product_orderVO.hashCode());
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
		Order_DetailsVO other = (Order_DetailsVO) obj;
		if (productVO == null) {
			if (other.productVO != null)
				return false;
		} else if (!productVO.equals(other.productVO))
			return false;
		if (product_orderVO == null) {
			if (other.product_orderVO != null)
				return false;
		} else if (!product_orderVO.equals(other.product_orderVO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[訂單明細= 訂單編號:" + product_orderVO.getOrd_no() + ", 商品編號:" + productVO.getPro_no() + ", 單項商品總數量:" + quantity + 
				", 單項商品價格:" + uni_pri + "]";
	}
	
	

}
