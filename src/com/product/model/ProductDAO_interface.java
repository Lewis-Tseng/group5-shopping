package com.product.model;

import java.util.*;

import com.product_order.model.Product_OrderVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void delete(String pro_no);

	public ProductVO findByPrimaryKey(String pro_no);

	public List<ProductVO> getAll();
	
	public List<ProductVO> getAll_CompositeQuery(Map<String, String[]> map);
	
	public List<ProductVO> getAllPro_StaisZero();
	
	public void updatePro_Sto(String pro_no, Integer pro_sto);

}
