package com.product_order.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.order_details.model.Order_DetailsVO;

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
	
	public Integer getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(Integer ord_no) {
		this.ord_no = ord_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Date getOrd_dat() {
		return ord_dat;
	}

	public void setOrd_dat(Date ord_dat) {
		this.ord_dat = ord_dat;
	}

	public Integer getOrd_amo() {
		return ord_amo;
	}

	public void setOrd_amo(Integer ord_amo) {
		this.ord_amo = ord_amo;
	}

	public Integer getPro_qua() {
		return pro_qua;
	}

	public void setPro_qua(Integer ord_qua) {
		this.pro_qua = ord_qua;
	}

	public String getOrd_sta() {
		return ord_sta;
	}

	public void setOrd_sta(String ord_sta) {
		this.ord_sta = ord_sta;
	}

	public String getPay_met() {
		return pay_met;
	}

	public void setPay_met(String pay_met) {
		this.pay_met = pay_met;
	}

	public String getDel_add() {
		return del_add;
	}

	public void setDel_add(String del_add) {
		this.del_add = del_add;
	}

	public Set<Order_DetailsVO> getOrder_detailss() {
		return order_detailss;
	}

	public void setOrder_detailss(Set<Order_DetailsVO> order_detailss) {
		this.order_detailss = order_detailss;
	}
	
}
