package com.order_details.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.product_order.model.Product_OrderVO;

public class Order_DetailsService {

	private Order_DetailsDAO_interface dao;

	public Order_DetailsService() {
		dao = new Order_DetailsDAO();
	}

	public Order_DetailsVO addOrder_Details(String ord_no, String pro_no, Integer quantity, Integer uni_pri) {

		Order_DetailsVO order_detailsVO = new Order_DetailsVO();

		order_detailsVO.setOrd_no(ord_no);
		order_detailsVO.setPro_no(pro_no);
		order_detailsVO.setQuantity(quantity);
		order_detailsVO.setUni_pri(uni_pri);
		dao.insert(order_detailsVO);

		return order_detailsVO;
	}

	public Order_DetailsVO updateOrder_Details(String ord_no, String pro_no, Integer quantity, Integer uni_pri) {

		Order_DetailsVO order_detailsVO = new Order_DetailsVO();

		order_detailsVO.setOrd_no(ord_no);
		order_detailsVO.setPro_no(pro_no);
		order_detailsVO.setQuantity(quantity);
		order_detailsVO.setUni_pri(uni_pri);
		dao.update(order_detailsVO);

		return order_detailsVO;
	}

	public Order_DetailsVO getOneOrder_Details(String ord_no, String pro_no) {
		return dao.findByPrimaryKey(ord_no, pro_no);
	}

	public void deleteOrder_Details(String ord_no, String pro_no) {
		dao.delete(ord_no, pro_no);
	}

	public Set<Order_DetailsVO> getAllOrder_DetailsByOrd_no(String ord_no) {
		return dao.getOrder_DetailsByOrd_no(ord_no);
	}

	public List<Order_DetailsVO> getAll() {
		return dao.getAll();
	}

	public void insertProduct_Cart(Order_DetailsVO order_detailsVO, Connection con) {
		dao.insertCart(order_detailsVO, con);
	}

}
