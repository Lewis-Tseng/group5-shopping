package com.product_image.model;

import java.util.List;

public interface Product_ImageDAO_interface {
	public void insert(Product_ImageVO product_ImageVO);

	public void update(Product_ImageVO product_ImageVO);

	public void delete(String pro_img_no);

	public Product_ImageVO findByPrimaryKey(String pro_img_no);

	public List<Product_ImageVO> getAll();
}
