package com.product.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(String cat_no, String pro_nam, String pro_con, Integer pro_pri, String pro_sta,
			Integer pro_sto) {

		ProductVO productVO = new ProductVO();

		productVO.setCat_no(cat_no);
		productVO.setPro_nam(pro_nam);
		productVO.setPro_con(pro_con);
		productVO.setPro_pri(pro_pri);
		productVO.setPro_sta(pro_sta);
		productVO.setPro_sto(pro_sto);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(String pro_no, String cat_no, String pro_nam, String pro_con, Integer pro_pri,
			String pro_sta, Integer pro_sto) {

		ProductVO productVO = new ProductVO();

		productVO.setPro_no(pro_no);
		productVO.setCat_no(cat_no);
		productVO.setPro_nam(pro_nam);
		productVO.setPro_con(pro_con);
		productVO.setPro_pri(pro_pri);
		productVO.setPro_sta(pro_sta);
		productVO.setPro_sto(pro_sto);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(String pro_no) {
		dao.delete(pro_no);
	}

	public ProductVO getOneProduct(String pro_no) {
		return dao.findByPrimaryKey(pro_no);
	}
	
	public void updateProductStock(String pro_no, Integer pro_sto) {
	    dao.updatePro_Sto(pro_no, pro_sto);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
    //不知道為甚麼下面方法在jsp頁面會出錯 原本的getAll就可以
	public List<ProductVO> getAllPro_StaisZero() {
		List<ProductVO> list = dao.getAllPro_StaisZero();
		list = list.stream().filter(p -> p.getPro_sta().equals("0")).collect(Collectors.toList());
		Collections.reverse(list);
		return list;
	}
	
	
}
