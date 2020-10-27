package com.product_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

import com.order_details.model.Order_DetailsDAO;
import com.order_details.model.Order_DetailsService;
import com.order_details.model.Order_DetailsVO;
import com.product.model.ProductVO;

import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product;
import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product_Order;

public class Product_OrderDAO implements Product_OrderDAO_interface {
	
	private static final String GET_ALL_STMT = "from Product_OrderVO order by ord_no";
	
	@Override
	public void insert(Product_OrderVO product_orderVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(product_orderVO);
            session.getTransaction().commit();           
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}
		
	}

	@Override
	public void update(Product_OrderVO product_orderVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(product_orderVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}

	}

	@Override
	public void delete(Integer ord_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            
            Query<Product_OrderVO> query = session.createQuery("delete Product_OrderVO where ord_no=?0 ");
			query.setParameter(0, ord_no);
			System.out.println("刪除的筆數=" + query.executeUpdate());
			
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 

	}

	@Override
	public Product_OrderVO findByPrimaryKey(Integer ord_no) {

		Product_OrderVO product_orderVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            product_orderVO = (Product_OrderVO) session.get(Product_OrderVO.class, ord_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return product_orderVO;
	}

	@Override
	public List<Product_OrderVO> getAll() {

		List<Product_OrderVO> list = new ArrayList<Product_OrderVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            Query<Product_OrderVO> query = session.createQuery(GET_ALL_STMT, Product_OrderVO.class);
		    list = query.getResultList();
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

	@Override
	public List<Product_OrderVO> getAll_CompositeQuery(Map<String, String[]> map) {
		List<Product_OrderVO> list = new ArrayList<Product_OrderVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            
			String finalSQL = "select * from product"
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map) + "order by pro_no";
		    System.out.println("===finalSQL(by DAO) = " + finalSQL);
		    
			NativeQuery<Product_OrderVO> query = session.createNativeQuery(finalSQL, Product_OrderVO.class);
			list = query.getResultList();
			 
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

	@Override
	public void insertWithOrder_Details(Product_OrderVO product_orderVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(product_orderVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}
	}

	public static void main(String[] args) {

		Product_OrderDAO dao = new Product_OrderDAO();
		Order_DetailsVO order_detailsVO1 = new Order_DetailsVO();
		Order_DetailsVO order_detailsVO2 = new Order_DetailsVO();
		ProductVO productVO1 = new ProductVO();
		ProductVO productVO2 = new ProductVO();
		Product_OrderVO product_orderVO2 = new Product_OrderVO();
		Set<Order_DetailsVO> set = new LinkedHashSet<Order_DetailsVO>();
		
		//下面為嘗試拆開分別新增的code--失敗
//		Product_OrderVO po = new Product_OrderVO();
//        Order_DetailsVO b = new Order_DetailsVO();
//        Set<Order_DetailsVO> set = new LinkedHashSet<Order_DetailsVO>();
//        set = po.getOrder_detailss();
//        for(Order_DetailsVO a : set) {
//        	b.setOrd_no(a.getProduct_orderVO().getOrd_no());
//        	b.setPro_no(a.getProductVO().getPro_no());
//        	b.setQuantity(a.getQuantity());
//        	b.setUni_pri(a.getUni_pri());
//        	set.add(b);
//        }
//        for(Order_DetailsVO a : set) {
//        	session.saveOrUpdate(a);
//        }
			
//	    //mem_id, ord_dat, ord_amo, ord_qua, ord_sta, pay_met, del_add, phone
		//新增
		Product_OrderVO product_orderVO1 = new Product_OrderVO();
		
		order_detailsVO1.setQuantity(70);
		order_detailsVO1.setUni_pri(5000);
		productVO1.setPro_no(6000010);
//		product_orderVO1.setOrd_no(null);
//		product_orderVO2.setOrd_no(8000010);
		order_detailsVO1.setProductVO(productVO1);
//		order_detailsVO1.setPro_no(6000010);
		
		order_detailsVO2.setQuantity(60);
		order_detailsVO2.setUni_pri(4000);
		productVO2.setPro_no(6000002);
//		product_orderVO2.setOrd_no(null);
		order_detailsVO2.setProductVO(productVO2);
//        order_detailsVO2.setPro_no(6000002);
		
//		set.add(order_detailsVO1);
		set.add(order_detailsVO2);
		
		product_orderVO1.setOrder_detailss(set);
		product_orderVO1.setMem_id("ME00001");
		product_orderVO1.setOrd_dat(java.sql.Date.valueOf("2019-03-12"));
		product_orderVO1.setOrd_amo(new Integer(40000));
		product_orderVO1.setPro_qua(new Integer(300));
		product_orderVO1.setOrd_sta("1");
		product_orderVO1.setPay_met("信用卡");
		product_orderVO1.setDel_add("台北市");
		dao.insert(product_orderVO1);

//		//修改
//		Product_OrderVO product_orderVO2 = new Product_OrderVO();
//		product_orderVO2.setOrd_no(8000011);
//		product_orderVO2.setMem_id("ME00001");
//		product_orderVO2.setOrd_dat(java.sql.Date.valueOf("2019-03-03"));
//		product_orderVO2.setOrd_amo(new Integer(40000));
//		product_orderVO2.setPro_qua(new Integer(30));
//		product_orderVO2.setOrd_sta("0");
//		product_orderVO2.setPay_met("555");
//		product_orderVO2.setDel_add("777");
//		dao.update(product_orderVO2);

	    //刪除
//		dao.delete(8000010);
//	    //ord_no, mem_id, ord_dat, ord_amo, pro_qua, ord_sta, pay_met, del_add
//	    //單查詢
//		Product_OrderVO product_orderVO3 = dao.findByPrimaryKey(8000010);
//		System.out.print(product_orderVO3.getOrd_no() + ",");
//		System.out.print(product_orderVO3.getMem_id() + ",");
//		System.out.print(product_orderVO3.getOrd_dat() + ",");
//		System.out.print(product_orderVO3.getOrd_amo() + ",");
//		System.out.print(product_orderVO3.getPro_qua() + ",");
//		System.out.print(product_orderVO3.getOrd_sta() + ",");
//		System.out.print(product_orderVO3.getPay_met() + ",");
//		System.out.print(product_orderVO3.getDel_add() + ",");
//		System.out.println("---------------------");
//		set = product_orderVO3.getOrder_detailss();
//		for(Order_DetailsVO ods : set) {
//			System.out.println(ods.getProductVO().getPro_nam() + "Pro_nam,");
//			System.out.println(ods.getQuantity() + "Quantity,");
//			System.out.println(ods.getUni_pri() + "Uni_pri,");
//		}
//		
		
//		
	    //查詢全部
//		List<Product_OrderVO> list = dao.getAll();
//		for (Product_OrderVO aProduct_Order : list) {
//			System.out.println(aProduct_Order.getOrd_no());
//			System.out.print(aProduct_Order.getMem_id() + ",");
//			System.out.print(aProduct_Order.getOrd_dat() + ",");
//			System.out.print(aProduct_Order.getOrd_amo() + ",");
//			System.out.print(aProduct_Order.getPro_qua() + ",");
//			System.out.print(aProduct_Order.getOrd_sta() + ",");
//			System.out.print(aProduct_Order.getPay_met() + ",");
//			System.out.print(aProduct_Order.getDel_add() + ",");
//			set = aProduct_Order.getOrder_detailss();
//			for(Order_DetailsVO ods : set) {
//				System.out.println(ods.getQuantity() + ",");
//				System.out.println(ods.getUni_pri() + ",");
//			}
//		}
//
	}

}
