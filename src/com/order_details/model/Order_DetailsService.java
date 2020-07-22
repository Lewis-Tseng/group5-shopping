package com.order_details.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

public class Order_DetailsService {

	private static Order_DetailsDAO_interface dao;

	static {
		@SuppressWarnings("resource")
		ApplicationContext contex = new ClassPathXmlApplicationContext("model-config-JndiObjectFactoryBean.xml");
		dao = (Order_DetailsDAO_interface) contex.getBean("order_detailsDAO");
	}
	
	public Order_DetailsVO addOrder_Details(Integer ord_no, Integer pro_no, Integer quantity, Integer uni_pri) {
		
		Order_DetailsVO order_detailsVO = new Order_DetailsVO();
		
		Product_OrderVO product_orderVO = new Product_OrderVO();
        product_orderVO.setOrd_no(ord_no);
        order_detailsVO.setProduct_orderVO(product_orderVO);
		ProductVO productVO = new ProductVO();
        productVO.setPro_no(pro_no);
        order_detailsVO.setProductVO(productVO);
		order_detailsVO.setQuantity(quantity);
		order_detailsVO.setUni_pri(uni_pri);
		dao.insert(order_detailsVO);

		return order_detailsVO;
	}

	public void addOrder_Details(Order_DetailsVO order_detailsVO, HibernateTemplate hibernateTemplate) {
		dao.insertCart2(order_detailsVO, hibernateTemplate);
	}
	
	public Order_DetailsVO updateOrder_Details(Integer ord_no, Integer pro_no, Integer quantity, Integer uni_pri) {
		
		Order_DetailsVO order_detailsVO = new Order_DetailsVO();

		Product_OrderVO product_orderVO = new Product_OrderVO();
        product_orderVO.setOrd_no(ord_no);
        order_detailsVO.setProduct_orderVO(product_orderVO);
		ProductVO productVO = new ProductVO();
        productVO.setPro_no(pro_no);
        order_detailsVO.setProductVO(productVO);
		order_detailsVO.setQuantity(quantity);
		order_detailsVO.setUni_pri(uni_pri);
		dao.update(order_detailsVO);

		return order_detailsVO;
	}

	public Order_DetailsVO getOneOrder_Details(Integer ord_no, Integer pro_no) {
		return dao.findByPrimaryKey(ord_no, pro_no);
	}

	public void deleteOrder_Details(Integer ord_no) {
		dao.delete(ord_no);
	}

	public List<Order_DetailsVO> getAllOrder_DetailsByOrd_no(Integer ord_no) {
		return dao.getOrder_DetailsByOrd_no(ord_no);
	}

	public List<Order_DetailsVO> getAll() {
		return dao.getAll();
	}

	public void insertProduct_Cart(Order_DetailsVO order_detailsVO, Connection con) {
		dao.insertCart(order_detailsVO, con);
	}

}
