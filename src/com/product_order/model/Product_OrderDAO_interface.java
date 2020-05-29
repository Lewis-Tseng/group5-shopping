package com.product_order.model;

import java.util.*;

import com.order_details.model.Order_DetailsVO;

public interface Product_OrderDAO_interface {

	public void insert(Product_OrderVO product_orderVO);

	public void update(Product_OrderVO product_orderVO);

	public void delete(Integer ord_no);

	public Product_OrderVO findByPrimaryKey(Integer ord_no);
                           //單純查詢全部
	public List<Product_OrderVO> getAll();
	                       //複合查詢用
	public List<Product_OrderVO> getAll_CompositeQuery(Map<String, String[]> map);
	              //新增訂單(一)同時新增訂單明細(多)
	public void insertWithOrder_Details(Product_OrderVO product_orderVO, List<Order_DetailsVO> list);

}
