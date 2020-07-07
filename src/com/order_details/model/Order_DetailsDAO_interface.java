package com.order_details.model;

import java.util.List;
import java.util.Set;

import com.product_order.model.Product_OrderVO;

public interface Order_DetailsDAO_interface {

	public void insert(Order_DetailsVO order_detailsVO);

	public void update(Order_DetailsVO order_detailsVO);

	public void delete(Integer ord_no);
	
	public Order_DetailsVO findByPrimaryKey(Integer ord_no, Integer pro_no);

	public List<Order_DetailsVO> getOrder_DetailsByOrd_no(Integer ord_no);

	public List<Order_DetailsVO> getAll();
	      //新增訂單(一)同時新增訂單明細(多) 
	public void insertCart(Order_DetailsVO order_detailsVO, java.sql.Connection con);
	
}
