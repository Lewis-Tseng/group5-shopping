package com.product.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

import com.product_category.model.Product_CategoryVO;
import com.product_order.model.Product_OrderVO;

import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product;
import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product_Order;

public class ProductDAO implements ProductDAO_interface {
	
	private static final String GET_ALL_STMT = "from ProductVO order by pro_no";
	
	@Override
	public void insert(ProductVO productVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(productVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}
	}

	@Override
	public void update(ProductVO productVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            session.saveOrUpdate(productVO);
            session.beginTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}

	@Override
	public void delete(String pro_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();

            Query<ProductVO> query = session.createQuery("delete ProductVO where pro_no=?0",ProductVO.class);
            query.setParameter(0, pro_no);
            System.out.println("刪除的比數=" + query.executeUpdate());
            
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		
	}

	@Override
	public ProductVO findByPrimaryKey(String pro_no) {

		ProductVO productVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            productVO = (ProductVO) session.get(ProductVO.class, pro_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            Query<ProductVO> query = session.createQuery(GET_ALL_STMT, ProductVO.class);
		    list = query.getResultList();
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

	@Override
	public List<ProductVO> getAll_CompositeQuery(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            
			String finalSQL = "select * from product"
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map) + "order by pro_no";
		    System.out.println("===finalSQL(by DAO) = " + finalSQL);
		    
			NativeQuery<ProductVO> query = session.createNativeQuery(finalSQL, ProductVO.class);
			list = query.getResultList();
			 
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}
	
	@Override
	public List<ProductVO> getAllPro_StaisZero() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            Query<ProductVO> query = session.createQuery(GET_ALL_STMT, ProductVO.class);
            list = query.getResultList();
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}
	
	@Override
	public void updatePro_Sto(String pro_no, Integer pro_sto) {
		
	}
	
	
	public static void main(String[] args) throws IOException {

		ProductDAO dao = new ProductDAO();
		
//        String str = getLongString("E:\\product1.txt");

		//新增 
		ProductVO productVO1 = new ProductVO();
		productVO1.setPro_nam("啞鈴");
//		productVO1.setCat_no("PR00001");
//		productVO1.setPro_con("");
		productVO1.setPro_pri(10000);
		productVO1.setPro_sta("0");
		productVO1.setPro_sto(111);
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();
		product_categoryVO.setCat_no("PR00001");
		productVO1.setCat_no(product_categoryVO);
		dao.insert(productVO1);
//
//		//修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setPro_no("PT00020");
//		productVO2.setCat_no("PR00004");
//		productVO2.setPro_nam("�j��3");
//		productVO2.setPro_con("D:\\product1.txt");
//		productVO2.setPro_pri(5000);
//		productVO2.setPro_sta("0");
//		productVO2.setPro_sto(111);
//		dao.update(productVO2);
//
//		//刪除
//		dao.delete("PT00021");
//
//		//單查詢
//		ProductVO productVO3 = dao.findByPrimaryKey("PT00020");
//		System.out.print(productVO3.getPro_no() + ",");
//		System.out.print(productVO3.getCat_no() + ",");
//		System.out.print(productVO3.getPro_nam() + ",");
//		System.out.print(productVO3.getPro_con() + ",");
//		System.out.print(productVO3.getPro_pri() + ",");
//		System.out.print(productVO3.getPro_sta() + ",");
//		System.out.print(productVO3.getPro_sto() + ",");
//		System.out.println("---------------------");
//
//		//查詢全部
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//			System.out.print(aProduct.getPro_no() + ",");
//			System.out.print(aProduct.getCat_no() + ",");
//			System.out.print(aProduct.getPro_nam() + ",");
//			System.out.print(aProduct.getPro_con() + ",");
//			System.out.print(aProduct.getPro_pri() + ",");
//			System.out.print(aProduct.getPro_sta() + ",");
//			System.out.print(aProduct.getPro_sto() + ",");
//		}

	}

	/*************************** 使用Clob物件 讀入CLOB(參考老師JDBC範例) ***************************************/	
//	
//	public static String readString(Clob clob) throws SQLException, IOException {
//		StringBuilder sb = new StringBuilder();
//		BufferedReader br = new BufferedReader(clob.getCharacterStream());
//		String str;
//		while((str = br.readLine()) != null) {
//			sb.append(str);
//			sb.append("\n");
//		}
//		br.close();
//		
//		return sb.toString();
//	}
	/**************************************************************************************/	
	
	
	/*************************** 使用String寫入CLOB(參考老師JDBC範例) ***************************************/	
//	public static String getLongString(String path) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		StringBuilder sb = new StringBuilder();
//		String str;
//		while((str = br.readLine()) != null) {
//			sb.append(str);
//			sb.append("\n");
//		}
//		br.close();
//		
//		return sb.toString();	
//	}
	/**************************************************************************************/
	
	
	/*************************** 使用Reader(參考老師JDBC範例) *********************************/
	public static String readString(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	/**************************************************************************************/



	



	/*************************** 使用資料流寫入CLOB(參考老師JDBC範例) *****************************/
//	public static Reader getLongStringStream(String path) throws FileNotFoundException {
//		return new FileReader(path);
//	}
	/**************************************************************************************/
}
