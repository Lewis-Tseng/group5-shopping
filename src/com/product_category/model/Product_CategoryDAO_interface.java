package com.product_category.model;

import java.util.List;

public interface Product_CategoryDAO_interface {
	public void insert(Product_CategoryVO product_categoryVO);

	public void update(Product_CategoryVO product_categoryVO);

	public void delete(Integer cat_no);

	public Product_CategoryVO findByPrimaryKey(Integer cat_no);

	public List<Product_CategoryVO> getAll();

}
