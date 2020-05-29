package com.order_details.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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

import com.product.model.ProductVO;
import com.product_order.model.Product_OrderVO;

public class Order_DetailsDAO implements Order_DetailsDAO_interface {
	
	private static final String GET_ALL_STMT = "from Order_DetailsVO order by ord_no";
    private static final String GET_OD_WITH_ORDER_DETAILS_STMT = "from Order_DetailsVO where ord_no=?0";
    private static final String GET_ONE_STMT = "from Order_DetailsVO where ord_no=?0 and pro_no=?1";
    
	@Override
	public void insert(Order_DetailsVO order_detailsVO) {
	    
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(order_detailsVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}

	}

	@Override
	public void update(Order_DetailsVO order_detailsVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            session.saveOrUpdate(order_detailsVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 

	}

	@Override
	public void delete(Integer ord_no, Integer pro_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();

            Query<ProductVO> query = session.createQuery("delete Order_DetailsVO where ord_no=?0 and pro_no=?1");
            query.setParameter(0, ord_no);
            query.setParameter(1, pro_no);
            System.out.println("刪除的筆數=" + query.executeUpdate());
            
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 

	}

	@Override
	public List<Order_DetailsVO> getOrder_DetailsByOrd_no(Integer ord_no) {
        //使用ord_no編號查訂單時，一次查詢訂單明細
		List<Order_DetailsVO> list = new ArrayList<Order_DetailsVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
            session.beginTransaction();
			
            Query<Order_DetailsVO> query = session.createQuery(GET_OD_WITH_ORDER_DETAILS_STMT, Order_DetailsVO.class);
            query.setParameter(0, ord_no);
            list = query.getResultList();
            
			session.getTransaction().commit();

		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

	@Override
	public List<Order_DetailsVO> getAll() {
		
		List<Order_DetailsVO> list = new ArrayList<Order_DetailsVO>();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            Query<Order_DetailsVO> query = session.createQuery(GET_ALL_STMT, Order_DetailsVO.class);
		    list = query.getResultList();
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

	@Override
	public Order_DetailsVO findByPrimaryKey(Integer ord_no, Integer pro_no) {

		Order_DetailsVO order_detailsVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			 session.beginTransaction();
	            Query<Order_DetailsVO> query = session.createQuery(GET_ONE_STMT, Order_DetailsVO.class);
	            query.setParameter(0, ord_no);
	            query.setParameter(1, pro_no);
	            order_detailsVO = query.getSingleResult();
			    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return order_detailsVO;
	}

	@Override
	public void insertCart(Order_DetailsVO order_detailsVO, Connection con) {

//		PreparedStatement pstmt = null;
//
//		try {
//
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, order_detailsVO.getOrd_no());
//			pstmt.setString(2, order_detailsVO.getPro_no());
//			pstmt.setInt(3, order_detailsVO.getQuantity());
//			pstmt.setInt(4, order_detailsVO.getUni_pri());
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					System.out.println("交易正在進行中");
//					System.out.println("rolled back由-訂單明細");
//					con.rollback();
//				} catch (SQLException excep) {
//					excep.printStackTrace(System.err);
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			se.printStackTrace(System.err);
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}

	}

	public static void main(String[] args) {
//
		Order_DetailsDAO dao = new Order_DetailsDAO();
		Product_OrderVO product_orderVO = new Product_OrderVO();
        ProductVO productVO = new ProductVO();
        Set<ProductVO> set = new LinkedHashSet<ProductVO>();
        
//	// 新增
//		Order_DetailsVO odVO1 = new Order_DetailsVO();
//		product_orderVO.setOrd_no(8000010);
//		odVO1.setProduct_orderVO(product_orderVO);
//		productVO.setPro_no(6000011);
//		odVO1.setProductVO(productVO);
//		odVO1.setQuantity(50);
//		odVO1.setUni_pri(1000);
//		dao.insert(odVO1);

		
		
//		//修改
//		Order_DetailsVO odVO2 = new Order_DetailsVO();
//		product_orderVO.setOrd_no(8000010);
//		odVO2.setProduct_orderVO(product_orderVO);
//		productVO.setPro_no(6000011);
//		odVO2.setProductVO(productVO);
//		odVO2.setQuantity(520);
//		odVO2.setUni_pri(9999);
//        dao.update(odVO2);

//      //刪除
//      dao.delete(8000010, 6000011);
//        
//      //單查詢
//      List<Order_DetailsVO> list = dao.getOrder_DetailsByOrd_no(8000010);
//        for(Order_DetailsVO aODVO : list) {
//        	System.out.println(aODVO.getQuantity() + ",");
//        	System.out.println(aODVO.getUni_pri() + ",");
//        	System.out.println();
//    		System.out.println(aODVO.getProductVO().getPro_nam()+ ",");
//    		System.out.println(aODVO.getProductVO().getPro_con() + ",");
//    	
//        }
//        
//        
//	    //全部查詢
//        List<Order_DetailsVO> list = dao.getAll();
//        for(Order_DetailsVO aODVO2 : list) {
//        	System.out.println(aODVO2.getProduct_orderVO().getOrd_no() + ",");
//        	System.out.println(aODVO2.getProductVO().getPro_nam() + ",");
//        	System.out.println(aODVO2.getQuantity() + ",");
//        	System.out.println(aODVO2.getUni_pri() + ",");
//        	System.out.println();
//        }     

	}

}
