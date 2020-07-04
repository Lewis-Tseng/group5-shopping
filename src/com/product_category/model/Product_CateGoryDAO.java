package com.product_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class Product_CategoryDAO implements Product_CategoryDAO_interface{

	private static final String GET_ALL_STMT = "from Product_CategoryVO order by cat_no";
	
	private HibernateTemplate hibernatetemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernatetemplate = hibernateTemplate;
	}
	
	@Override
	public void insert(Product_CategoryVO product_categoryVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(product_categoryVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}
	
	
	@Override
	public void update(Product_CategoryVO product_categoryVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(product_categoryVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}
	
	
	
	@Override
	public void delete(Integer cat_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            
            Query<Product_CategoryVO> query = session.createQuery("delete Product_CategoryVO where cat_no=?0");
		    query.setParameter(0, cat_no);
		    System.out.println("刪除的筆數=" + query.executeUpdate());
            
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}
	
	
	@Override
	public Product_CategoryVO findByPrimaryKey(Integer cat_no) {

		Product_CategoryVO product_categoryVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
            session.beginTransaction();
            product_categoryVO = (Product_CategoryVO) session.get(Product_CategoryVO.class, cat_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return product_categoryVO;
	}
	
	
	@Override
	public List<Product_CategoryVO> getAll() {
		
		List<Product_CategoryVO> list = new ArrayList<Product_CategoryVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();    
            Query<Product_CategoryVO> query = session.createQuery(GET_ALL_STMT, Product_CategoryVO.class);
		    list = query.getResultList();		    
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}
	
	
	
	public static void main(String[] args) {
//
//		Product_CategoryDAO dao = new Product_CategoryDAO();
//		//新增
//		Product_CategoryVO product_CategoryVO1 = new Product_CategoryVO();
//		product_CategoryVO1.setCat_nam("露營");
//		dao.insert(product_CategoryVO1);
		
		//修改
//		Product_CategoryVO product_CategoryVO2 = new Product_CategoryVO();
//		product_CategoryVO2.setCat_no(5000050);
//		product_CategoryVO2.setCat_nam("測試");
//		dao.update(product_CategoryVO2);
//		//刪除
//		dao.delete(5000070);
//		//單查詢
//		Product_CategoryVO product_CategoryVO3 = dao.findByPrimaryKey(5000020);
//		System.out.println(product_CategoryVO3.getCat_no() + ",");
//		System.out.println(product_CategoryVO3.getCat_nam() + ",");
//		System.out.println("----------------------------");
//		//查詢全部
//		List<Product_CategoryVO> list = dao.getAll();
//		for (Product_CategoryVO aProduct_Category : list) {
//			System.out.println(aProduct_Category.getCat_no() + ",");
//			System.out.println(aProduct_Category.getCat_nam() + ",");
//		}
//		
	}

}
