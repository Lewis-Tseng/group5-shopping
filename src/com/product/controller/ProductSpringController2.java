package com.product.controller;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@Controller
@Validated
@RequestMapping("/back_end/product")
public class ProductSpringController2 {
	
	@RequestMapping("getOne_For_Display")
	public String getOne_For_Display(
			@NotEmpty(message = "商品編號 請勿空白") 
			@Digits(integer = 7, fraction = 0, message = "商品編號 請填數字-請勿超過{integer}") 
			@Min(value = 6000001, message = "產品編號 不能小於}value}") 
			@Max(value = 6999999, message = "產品編號 不能大於{value}") 
			@RequestParam("pro_no") String pro_no,
			ModelMap model) {
		
		ProductService productSvc = new ProductService();
		ProductVO productVO = productSvc.getOneProduct(new Integer(pro_no));
		if(productVO == null) {
			model.addAttribute("errorMessage", "查無資料");
		    return "back_end/product/select_Product";
		}
		
		    model.addAttribute("productVO",productVO);
		return "back_end/product/listOneProduct";
	}
	
	

}
