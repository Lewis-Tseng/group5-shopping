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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.product_image.model.Product_ImageDAO_interface;

import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class Product_CategoryDAO implements Product_CategoryDAO_interface{

	private static final String GET_ALL_STMT = "from Product_CategoryVO order by cat_no";
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void insert(Product_CategoryVO product_categoryVO) {
		hibernateTemplate.saveOrUpdate(product_categoryVO);
	}

	@Override
	public void update(Product_CategoryVO product_categoryVO) {
		hibernateTemplate.saveOrUpdate(product_categoryVO);
	}
	
	
	@Override
	public void delete(Integer cat_no) {
		Product_CategoryVO product_categoryVO = (Product_CategoryVO) hibernateTemplate.get(Product_CategoryVO.class,cat_no);
		hibernateTemplate.delete(product_categoryVO);

//            Query<Product_CategoryVO> query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("delete Product_CategoryVO where cat_no=?0");
//		    query.setParameter(0, cat_no);
//		    System.out.println("刪除的筆數=" + query.executeUpdate());
	}
	
	
	@Override
	public Product_CategoryVO findByPrimaryKey(Integer cat_no) {
		Product_CategoryVO product_categoryVO = (Product_CategoryVO) hibernateTemplate.get(Product_CategoryVO.class, cat_no);
		return product_categoryVO;
	}
	
	
	@Override
	public List<Product_CategoryVO> getAll() {
		Query<Product_CategoryVO> query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(GET_ALL_STMT, Product_CategoryVO.class);
		List<Product_CategoryVO> list = query.getResultList();
		return list;
	}
	
	
	public static void main(String[] args) {
//
//		Product_CategoryDAO dao = new Product_CategoryDAO();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config-JndiObjectFactoryBean.xml");
		Product_CategoryDAO_interface dao = (Product_CategoryDAO_interface) context.getBean("product_categoryDAO");
		
		
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
