package com.product_order.model;

import java.sql.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.order_details.model.Order_DetailsVO;

@Entity  //要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "product_order") //代表這個class是對應到資料庫的實體table，目前對應的table是EMP2
public class Product_OrderVO  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer ord_no;
	private String mem_id;
	private Date ord_dat;
	private Integer ord_amo;
	private Integer pro_qua;
	private String ord_sta;
	private String pay_met;
	private String del_add;
	private Set<Order_DetailsVO> order_detailss = new HashSet<Order_DetailsVO>();
	
	public Product_OrderVO() {		
	}
	
	@Id
	@Column(name = "ord_no")
	@SequenceGenerator(name="name1", sequenceName="product_order_seq", allocationSize=1) //1.先用@SequenceGenerator建立一個generator
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="name1")    
	public Integer getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(Integer ord_no) {
		this.ord_no = ord_no;
	}
	@Column(name = "mem_id")
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	@Column(name = "ord_dat")
	public Date getOrd_dat() {
		return ord_dat;
	}

	public void setOrd_dat(Date ord_dat) {
		this.ord_dat = ord_dat;
	}
	@Column(name = "ord_amo")
	public Integer getOrd_amo() {
		return ord_amo;
	}

	public void setOrd_amo(Integer ord_amo) {
		this.ord_amo = ord_amo;
	}
	@Column(name = "pro_qua")
	public Integer getPro_qua() {
		return pro_qua;
	}

	public void setPro_qua(Integer ord_qua) {
		this.pro_qua = ord_qua;
	}
	@Column(name = "ord_sta")
	public String getOrd_sta() {
		return ord_sta;
	}

	public void setOrd_sta(String ord_sta) {
		this.ord_sta = ord_sta;
	}
	@Column(name = "pay_met")
	public String getPay_met() {
		return pay_met;
	}

	public void setPay_met(String pay_met) {
		this.pay_met = pay_met;
	}
	@Column(name = "del_add")
	public String getDel_add() {
		return del_add;
	}

	public void setDel_add(String del_add) {
		this.del_add = del_add;
	}
	//(cascade=CascadeType.ALL ,fetch=FetchType.EAGER, mappedBy="product_orderVO")
	@OneToMany(fetch=FetchType.EAGER, mappedBy="product_orderVO")
	@OrderBy("ord_no asc")
	public Set<Order_DetailsVO> getOrder_detailss() {
		return order_detailss;
	}

	public void setOrder_detailss(Set<Order_DetailsVO> order_detailss) {
		this.order_detailss = order_detailss;
	}
	
}
