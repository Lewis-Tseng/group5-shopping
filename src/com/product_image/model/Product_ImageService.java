package com.product_image.model;

import java.util.List;

public class Product_ImageService {

	private Product_ImageDAO_interface dao;

	public Product_ImageService() {
		dao = new Product_ImageDAO();
	}

	public Product_ImageVO addProduct_Image(byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		dao.insert(product_imageVO);

		return product_imageVO;
	}
	
	public Product_ImageVO addProduct_Image(String pro_no, byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setPro_no(pro_no);
		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		dao.insert(product_imageVO);

		return product_imageVO;
	}
	
	public Product_ImageVO updateProduct_Image(String pro_no) {

		Product_ImageVO product_imageVO = new Product_ImageVO();
		
		product_imageVO.setPro_no(pro_no);
		dao.update(product_imageVO);

		return product_imageVO;
	}

	public Product_ImageVO updateProduct_Image(String pro_img_no, String pro_no, byte[] img, String img_nam) {

		Product_ImageVO product_imageVO = new Product_ImageVO();

		product_imageVO.setPro_img_no(pro_img_no);
		product_imageVO.setPro_no(pro_no);
		product_imageVO.setImg(img);
		product_imageVO.setImg_nam(img_nam);
		dao.update(product_imageVO);

		return product_imageVO;
	}

	public void deleteProduct_Image(String pro_img_no) {
		dao.delete(pro_img_no);
	}
	
	public Product_ImageVO getOneProduct_Image(String pro_img_no) {
		return dao.findByPrimaryKey(pro_img_no);
	}

	public List<Product_ImageVO> getAll() {
		return dao.getAll();
	}

}
