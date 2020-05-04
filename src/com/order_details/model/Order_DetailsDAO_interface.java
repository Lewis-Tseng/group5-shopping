package com.order_details.model;

import java.util.List;
import java.util.Set;

import com.product_order.model.Product_OrderVO;

public interface Order_DetailsDAO_interface {

	public void insert(Order_DetailsVO order_detailsVO);

	public void update(Order_DetailsVO order_detailsVO);

	public void delete(String ord_no, String pro_no);
	
	public Order_DetailsVO findByPrimaryKey(String ord_no, String pro_no);

	public Set<Order_DetailsVO> getOrder_DetailsByOrd_no(String ord_no);

	public List<Order_DetailsVO> getAll();
	      //新增訂單(一)同時新增訂單明細(多) 
	public void insertCart(Order_DetailsVO order_detailsVO, java.sql.Connection con);
}
