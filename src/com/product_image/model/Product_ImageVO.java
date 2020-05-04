package com.product_image.model;

public class Product_ImageVO {
	private String pro_img_no;
	private String pro_no;
	private byte[] img;
	private String img_nam;

	public String getPro_img_no() {
		return pro_img_no;
	}

	public void setPro_img_no(String pro_img_no) {
		this.pro_img_no = pro_img_no;
	}

	public String getPro_no() {
		return pro_no;
	}

	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
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
	
}
