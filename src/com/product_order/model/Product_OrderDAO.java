package com.product_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

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
	public void insertWithOrder_Details(Product_OrderVO product_OrderVO, List<Order_DetailsVO> list) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			// 設定於 pstm.executeUpdate()之前
//			con.setAutoCommit(false);
//
//			String cols[] = { "ord_no" };
//			pstmt = con.prepareStatement(INSERT_STMT, cols);
//			pstmt.setString(1, product_OrderVO.getMem_id());
//			pstmt.setDate(2, product_OrderVO.getOrd_dat());
//			pstmt.setInt(3, product_OrderVO.getOrd_amo());
//			pstmt.setInt(4, product_OrderVO.getPro_qua());
//			pstmt.setString(5, product_OrderVO.getOrd_sta());
//			pstmt.setString(6, product_OrderVO.getPay_met());
//			pstmt.setString(7, product_OrderVO.getDel_add());
//			pstmt.executeUpdate();
//
//			String next_ord_no = null;
//			ResultSet rs = pstmt.getGeneratedKeys();
//
//			if (rs.next()) {
//				next_ord_no = rs.getString(1);
//				System.out.println("訂單自增主鍵值= " + next_ord_no + "(剛新增成功的訂單編號)");
//			} else {
//				System.out.println("未取得自增主鍵值");
//			}
//			rs.close();
//
//			// 再同時新增訂單明細
//			Order_DetailsService order_detailsSvc = new Order_DetailsService();
//			// 確認裡面內容物
//			for (Order_DetailsVO aOrd : list) {
//				System.out.println(aOrd);
//			}
//
//			System.out.println("list.size()-A=" + list.size());
//			for (Order_DetailsVO aOrd : list) {// 先新增外來鍵
//				aOrd.setOrd_no(next_ord_no);
//				order_detailsSvc.insertProduct_Cart(aOrd, con);// 存到多方
//			}
//
//			// 設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("list.size()-B=" + list.size());
//			System.out.println("新增訂單編號" + next_ord_no + "時，共有" + list.size() + "訂單明細同時被新增");
//
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 設定於當有exception發生時之catch區塊內
//					System.out.println("交易正在進行中");
//					System.out.println("rolled back由-訂單");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured." + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}

	}

	public static void main(String[] args) {

		Product_OrderDAO dao = new Product_OrderDAO();
	    //mem_id, ord_dat, ord_amo, ord_qua, ord_sta, pay_met, del_add, phone
		//新增
		Product_OrderVO product_orderVO1 = new Product_OrderVO();
		product_orderVO1.setMem_id("ME00001");
		product_orderVO1.setOrd_dat(java.sql.Date.valueOf("2019-03-12"));
		product_orderVO1.setOrd_amo(new Integer(40000));
		product_orderVO1.setPro_qua(new Integer(300));
		product_orderVO1.setOrd_sta("1");
		product_orderVO1.setPay_met("信用卡");
		product_orderVO1.setDel_add("台北市");
		dao.insert(product_orderVO1);

//		//�ק�
//		Product_OrderVO product_orderVO2 = new Product_OrderVO();
//		product_orderVO2.setOrd_no("PO00001");
//		product_orderVO2.setMem_id("ME00001");
//		product_orderVO2.setOrd_dat(java.sql.Date.valueOf("2019-03-03"));
//		product_orderVO2.setOrd_amo(new Integer(40000));
//		product_orderVO2.setPro_qua(new Integer(30));
//		product_orderVO2.setOrd_sta("0");
//		product_orderVO2.setPay_met("�H�Υd");
//		product_orderVO2.setDel_add("�x�_���H�q��999��");
//		dao.update(product_orderVO2);

	// �R��
//		dao.delete("PO00003");
	// ord_no, mem_id, ord_dat, ord_amo, pro_qua, ord_sta, pay_met, del_add
	// �浧�d��
//		Product_OrderVO product_orderVO3 = dao.findByPrimaryKey("PO00009");
//		System.out.print(product_orderVO3.getOrd_no() + ",");
//		System.out.print(product_orderVO3.getMem_id() + ",");
//		System.out.print(product_orderVO3.getOrd_dat() + ",");
//		System.out.print(product_orderVO3.getOrd_amo() + ",");
//		System.out.print(product_orderVO3.getPro_qua() + ",");
//		System.out.print(product_orderVO3.getOrd_sta() + ",");
//		System.out.print(product_orderVO3.getPay_met() + ",");
//		System.out.print(product_orderVO3.getDel_add() + ",");
//		System.out.println("---------------------");
//		
	// �d�ߥ���
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
//		}

	}

}
