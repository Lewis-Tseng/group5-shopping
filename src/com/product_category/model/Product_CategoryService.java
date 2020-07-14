package com.product_category.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Product_CategoryService {

	private static Product_CategoryDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext contex = new ClassPathXmlApplicationContext("model-config-JndiObjectFactoryBean.xml");
		dao = (Product_CategoryDAO_interface) contex.getBean("product_categoryDAO");	
	}

	public Product_CategoryVO addProduct_Category(String cat_nam) {
		
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();

		product_categoryVO.setCat_nam(cat_nam);
		dao.insert(product_categoryVO);

		return product_categoryVO;
	}

	public Product_CategoryVO updateProduct_Category(String cat_nam) {
		
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();
		
		product_categoryVO.setCat_nam(cat_nam);
		dao.update(product_categoryVO);

		return product_categoryVO;
	}
	
	public Product_CategoryVO updateProduct_Category(Integer cat_no, String cat_nam) {
		
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();

		product_categoryVO.setCat_no(cat_no);
		product_categoryVO.setCat_nam(cat_nam);
		dao.update(product_categoryVO);

		return product_categoryVO;
	}

	public void deleteProduct_Category(Integer cat_no) {
		dao.delete(cat_no);
	}

	public Product_CategoryVO getOneProduct_Category(Integer cat_no) {
		return dao.findByPrimaryKey(cat_no);
	}

	public List<Product_CategoryVO> getAll() {
		return dao.getAll();
	}

}
