package com.product.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.product_category.model.Product_CategoryVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(Integer cat_no, String pro_nam, String pro_con, Integer pro_pri, String pro_sta,
			Integer pro_sto) {

		ProductVO productVO = new ProductVO();
		productVO.setPro_nam(pro_nam);
		productVO.setPro_con(pro_con);
		productVO.setPro_pri(pro_pri);
		productVO.setPro_sta(pro_sta);
		productVO.setPro_sto(pro_sto);
		//原本productVO.setCat_no(cat_no);
		//改成下面三行轉存進去
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();
		product_categoryVO.setCat_no(cat_no);
		productVO.setProduct_categoryVO(product_categoryVO);
		
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer pro_no, Integer cat_no, String pro_nam, String pro_con, Integer pro_pri,
			String pro_sta, Integer pro_sto) {

		ProductVO productVO = new ProductVO();
		productVO.setPro_no(pro_no);
		productVO.setPro_nam(pro_nam);
		productVO.setPro_con(pro_con);
		productVO.setPro_pri(pro_pri);
		productVO.setPro_sta(pro_sta);
		productVO.setPro_sto(pro_sto);
		
		Product_CategoryVO product_categoryVO = new Product_CategoryVO();
		product_categoryVO.setCat_no(cat_no);
		productVO.setProduct_categoryVO(product_categoryVO);
		
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer pro_no) {
		dao.delete(pro_no);
	}

	public ProductVO getOneProduct(Integer pro_no) {
		return dao.findByPrimaryKey(pro_no);
	}
	
	public void updateProductStock(Integer pro_no, Integer pro_sto) {
	    dao.updatePro_Sto(pro_no, pro_sto);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getAll_CompositeQuery(Map<String, String[]> map) {
		return dao.getAll_CompositeQuery(map);
	}
   
	public List<ProductVO> getAllPro_StaisZero() {
		List<ProductVO> list = dao.getAllPro_StaisZero();
		list = list.stream().filter(p -> p.getPro_sta().equals("0")).collect(Collectors.toList());
		Collections.reverse(list);
		return list;
	}
	
	
}
