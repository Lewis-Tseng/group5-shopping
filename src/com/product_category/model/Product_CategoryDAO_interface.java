package com.product_category.model;

import java.util.List;

public interface Product_CategoryDAO_interface {
	public void insert(Product_CategoryVO product_CategoryVO);

	public void update(Product_CategoryVO product_CategoryVO);

	public void delete(String cat_no);

	public Product_CategoryVO findByPrimaryKey(String cat_no);

	public List<Product_CategoryVO> getAll();

}
