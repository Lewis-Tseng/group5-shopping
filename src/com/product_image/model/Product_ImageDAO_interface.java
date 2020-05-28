package com.product_image.model;

import java.util.List;

public interface Product_ImageDAO_interface {
	public void insert(Product_ImageVO product_ImageVO);

	public void update(Product_ImageVO product_ImageVO);

	public void delete(Integer pro_img_no);

	public Product_ImageVO findByPrimaryKey(Integer pro_img_no);

	public List<Product_ImageVO> getAll();
}
