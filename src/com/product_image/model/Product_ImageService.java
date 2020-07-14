package com.product_image.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.product.model.ProductVO;

public class Product_ImageService {

	private static Product_ImageDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext contex = new ClassPathXmlApplicationContext("model-config-JndiObjectFactoryBean.xml");
		dao = (Product_ImageDAO_interface) contex.getBean("product_imageDAO");
	}
	
	public Product_ImageVO addProduct_Image(byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		dao.insert(product_imageVO);

		return product_imageVO;
	}
	
	public Product_ImageVO addProduct_Image(Integer pro_no, byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		ProductVO productVO = new ProductVO();
		productVO.setPro_no(pro_no);
		product_imageVO.setProductVO(productVO);
		dao.insert(product_imageVO);

		return product_imageVO;
	}
	
	public Product_ImageVO updateProduct_Image(Integer pro_no) {

		Product_ImageVO product_imageVO = new Product_ImageVO();
		
		ProductVO productVO = new ProductVO();
		productVO.setPro_no(pro_no);
		product_imageVO.setProductVO(productVO);
		dao.update(product_imageVO);

		return product_imageVO;
	}

	public Product_ImageVO updateProduct_Image(Integer pro_img_no, Integer pro_no, byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setPro_img_no(pro_img_no);
		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		ProductVO productVO = new ProductVO();
		productVO.setPro_no(pro_no);
		product_imageVO.setProductVO(productVO);
		dao.update(product_imageVO);

		return product_imageVO;
	}

	public void deleteProduct_Image(Integer pro_img_no) {
		dao.delete(pro_img_no);
	}
	
	public Product_ImageVO getOneProduct_Image(Integer pro_img_no) {
		return dao.findByPrimaryKey(pro_img_no);
	}

	public List<Product_ImageVO> getAll() {
		return dao.getAll();
	}

}
