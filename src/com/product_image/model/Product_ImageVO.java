package com.product_image.model;

import com.product.model.ProductVO;

public class Product_ImageVO implements java.io.Serializable {
	
	private Integer pro_img_no;
	private byte[] img;
	private String img_nam;
	private ProductVO productVO;
	
	public Integer getPro_img_no() {
		return pro_img_no;
	}

	public void setPro_img_no(Integer pro_img_no) {
		this.pro_img_no = pro_img_no;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getImg_nam() {
		return img_nam;
	}

	public void setImg_nam(String img_nam) {
		this.img_nam = img_nam;
	}
	
	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
}
