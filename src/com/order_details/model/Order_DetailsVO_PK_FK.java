package com.order_details.model;

import java.io.Serializable;
import java.util.Objects;

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

public class Order_DetailsVO_PK_FK implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer ord_no;
	private Integer pro_no;
	
	private Order_DetailsVO_PK_FK(){
		
	}
	
	private Product_OrderVO product_orderVO;
	private ProductVO productVO;
	


	public Order_DetailsVO_PK_FK(Product_OrderVO product_orderVO, ProductVO productVO) {
		this.product_orderVO = product_orderVO;
		this.productVO = productVO;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(product_orderVO, productVO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_DetailsVO_PK_FK other = (Order_DetailsVO_PK_FK) obj;
		return Objects.equals(product_orderVO, other.product_orderVO) && Objects.equals(productVO, other.productVO);
	}
	
	
	
	
	
//	public Order_DetailsVO_PK_FK(Integer ord_no, Integer pro_no) {
//		this.ord_no = ord_no;
//		this.pro_no = pro_no;
//	}
//
//	public Integer getOrd_no() {
//		return ord_no;
//	}
//
//	public void setOrd_no(Integer ord_no) {
//		this.ord_no = ord_no;
//	}
//
//	public Integer getPro_no() {
//		return pro_no;
//	}
//
//	public void setPro_no(Integer pro_no) {
//		this.pro_no = pro_no;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(ord_no, pro_no);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Order_DetailsVO_PK_FK other = (Order_DetailsVO_PK_FK) obj;
//		return Objects.equals(ord_no, other.ord_no) && Objects.equals(pro_no, other.pro_no);
//	}

	
    

}
