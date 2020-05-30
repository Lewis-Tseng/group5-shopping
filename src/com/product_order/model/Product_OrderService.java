package com.product_order.model;

import java.util.*;

import com.order_details.model.Order_DetailsVO;

public class Product_OrderService {

	private Product_OrderDAO_interface dao;

	public Product_OrderService() {
		dao = new Product_OrderDAO();
	}

	public Product_OrderVO addProduct_Order(String mem_id, java.sql.Date ord_dat, Integer ord_amo, Integer pro_qua,
			String ord_sta, String pay_met, String del_add) {

		Product_OrderVO product_OrderVO = new Product_OrderVO();

		product_OrderVO.setMem_id(mem_id);
		product_OrderVO.setOrd_dat(ord_dat);
		product_OrderVO.setOrd_amo(ord_amo);
		product_OrderVO.setPro_qua(pro_qua);
		product_OrderVO.setOrd_sta(ord_sta);
		product_OrderVO.setPay_met(pay_met);
		product_OrderVO.setDel_add(del_add);
		dao.insert(product_OrderVO);

		return product_OrderVO;

	}

	public Product_OrderVO updateProduct_Order(Integer ord_no, String mem_id, java.sql.Date ord_dat, Integer ord_amo,
			Integer pro_qua, String ord_sta, String pay_met, String del_add) {

		Product_OrderVO product_OrderVO = new Product_OrderVO();

		product_OrderVO.setOrd_no(ord_no);
		product_OrderVO.setMem_id(mem_id);
		product_OrderVO.setOrd_dat(ord_dat);
		product_OrderVO.setOrd_amo(ord_amo);
		product_OrderVO.setPro_qua(pro_qua);
		product_OrderVO.setOrd_sta(ord_sta);
		product_OrderVO.setPay_met(pay_met);
		product_OrderVO.setDel_add(del_add);
		dao.update(product_OrderVO);

		return product_OrderVO;
	}

	public void deleteProduct_Order(Integer ord_no) {
		dao.delete(ord_no);
	}

	public Product_OrderVO getOneProduct_Order(Integer ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}

	public List<Product_OrderVO> getAll() {
		return dao.getAll();
	}

	public List<Product_OrderVO> getAll(Map<String, String[]> map) {
		return dao.getAll_CompositeQuery(map);
	}

	public void insertShopping_Order(Product_OrderVO product_OrderVO) {
		dao.insertWithOrder_Details(product_OrderVO);
	}
	
//	public void insertShopping_Order(Product_OrderVO product_OrderVO, List<Order_DetailsVO> list) {
//		dao.insertWithOrder_Details(product_OrderVO, list);
//	}

}
