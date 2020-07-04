package com.product_image.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.product.model.ProductVO;

import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class Product_ImageDAO implements Product_ImageDAO_interface {
	
	private static final String GET_ALL_STMT = "from Product_ImageVO order by pro_img_no";
	
	private HibernateTemplate hibernatetemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernatetemplate = hibernateTemplate;
	}

	@Override
	public void insert(Product_ImageVO product_imageVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(product_imageVO);
            session.getTransaction().commit();	
		} catch (RuntimeException ex) {
		    session.getTransaction().rollback();
		    throw ex;
		}
	}

	@Override
	public void update(Product_ImageVO product_imageVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
            session.saveOrUpdate(product_imageVO);
            session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}

	@Override
	public void delete(Integer pro_img_no) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			
			Query<Product_ImageVO> query = session.createQuery("delete Product_ImageVO where pro_img_no=?0");
			query.setParameter(0, pro_img_no);
			System.out.println("刪除的筆數=" + query.executeUpdate());	
		    
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
	}

	@Override
	public Product_ImageVO findByPrimaryKey(Integer pro_img_no) {

		Product_ImageVO product_imageVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            product_imageVO = (Product_ImageVO) session.get(Product_ImageVO.class, pro_img_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return product_imageVO;
	}

	@Override
	public List<Product_ImageVO> getAll() {

		List<Product_ImageVO> list = new ArrayList<Product_ImageVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
            session.beginTransaction();
            Query<Product_ImageVO> query = session.createQuery(GET_ALL_STMT, Product_ImageVO.class);
		    list = query.getResultList();
		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} 
		return list;
	}

//	public static void main(String[] args) throws IOException {
//
//		Product_ImageDAO dao = new Product_ImageDAO();
		//新增
//		Product_ImageVO product_ImageVO1 = new Product_ImageVO();
//	//	product_ImageVO1.setPro_no(6000021);
//		product_ImageVO1.setImg(getPictureByteArray("D:\\77777.jpg"));
//		product_ImageVO1.setImg_nam("測試用");
//		ProductVO productVO = new ProductVO();
//		productVO.setPro_no(6000024);
//		product_ImageVO1.setProductVO(productVO);
//		dao.insert(product_ImageVO1);

		//修改
//		Product_ImageVO product_ImageVO2 = new Product_ImageVO();
//		product_ImageVO2.setPro_img_no(4000021);
//	//	product_ImageVO2.setPro_no("PT00005");
//		product_ImageVO2.setImg(getPictureByteArray("D:\\666.jpg"));
//		product_ImageVO2.setImg_nam("好好");
//		ProductVO productVO = new ProductVO();
//		productVO.setPro_no(6000024);
//		product_ImageVO2.setProductVO(productVO);
//		dao.update(product_ImageVO2);
//
//		//刪除
//		dao.delete(4000021);
//
//		//單查詢
//		Product_ImageVO product_ImageVO3 = dao.findByPrimaryKey(4000010);
//		System.out.println(product_ImageVO3.getProductVO().getPro_no() + ",");
//		System.out.println(product_ImageVO3.getImg() + ",");
//		System.out.println(product_ImageVO3.getImg_nam() + ",");
//		System.out.println("---------------------------");
//
//		//查詢全部
//		List<Product_ImageVO> list = dao.getAll();
//		for(Product_ImageVO aProduct_Image : list) {
//			System.out.println(aProduct_Image.getPro_img_no() + ",");
//			System.out.println(aProduct_Image.getProductVO().getPro_no() + ",");
//			System.out.println(aProduct_Image.getImg() + ",");
//			System.out.println(aProduct_Image.getImg_nam() + ",");
//		}

//	}

	// Write Blob(參考老師JDBC範例)
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
	
//	//Read Blob(參考老師JDBC範例)
//	public static byte[] readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("Output/2.png");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//		return bytes;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
