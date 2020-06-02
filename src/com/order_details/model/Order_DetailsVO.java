package com.order_details.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

@Entity  //要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "order_details") //代表這個class是對應到資料庫的實體table，目前對應的table是EMP2
@IdClass(Order_DetailsVO_PK_FK.class)
public class Order_DetailsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	

//    private Integer ord_no;
//	private Integer pro_no;	
	private Product_OrderVO product_orderVO;
	private ProductVO productVO;
	private Integer quantity;
	private Integer uni_pri;
	
	public Order_DetailsVO() {
	}

//	@Id
//    @Column(name = "ord_no")//, insertable = false, updatable = false)nullable = true)	
//	@ManyToOne
//	@JoinColumn(name = "ord_no")
//	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(name="name1", sequenceName="product_order_seq", allocationSize=1) //1.先用@SequenceGenerator建立一個generator
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq1") //(strategy = GenerationType.SEQUENCE, generator="name1") 	
//    public Integer getOrd_no() {
//		return ord_no;
//	}
//
//	public void setOrd_no(Integer ord_no) {
//		this.ord_no = ord_no;
//	}
//	@Id
//    @Column(name = "pro_no")	
//	public Integer getPro_no() {
//		return pro_no;
//	}
//
//	public void setPro_no(Integer pro_no) {
//		this.pro_no = pro_no;
//	}

//	@ManyToOne
//	@JoinColumns({
//		    @JoinColumn(name = "ord_no", referencedColumnName = "ord_no"),
//			@JoinColumn(name = "pro_no", referencedColumnName = "pro_no") 
//		    })
	@Id
	@ManyToOne
	@JoinColumn(name = "ord_no")//,referencedColumnName="ord_no")
//	@GeneratedValue
//	@SequenceGenerator(name="name1", sequenceName="product_order_seq", allocationSize=1) //1.先用@SequenceGenerator建立一個generator
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="name1") //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="name1")  
	public Product_OrderVO getProduct_orderVO() {
		return product_orderVO;
	}

	public void setProduct_orderVO(Product_OrderVO product_orderVO) {
		this.product_orderVO = product_orderVO;
	}
	@Id
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
	
               //product_orderVO.getOrd_no()
	@Override
	public String toString() {
		return "[訂單明細= 訂單編號:" + product_orderVO.getOrd_no() + ", 商品編號:" + productVO.getPro_no() + ", 單項商品總數量:" + quantity + 
				", 單項商品價格:" + uni_pri + "]";
	}
	
	

}
