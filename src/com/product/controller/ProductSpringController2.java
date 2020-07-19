package com.product.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@Controller
@Validated
@RequestMapping("/back_end/product")
public class ProductSpringController2 {
	
	@RequestMapping("getOne_For_Display")
	public String getOne_For_Display(
			@NotEmpty(message = "1商品編號 請勿空白") 
			@Digits(integer = 7, fraction = 0, message = "2商品編號 請填數字-請勿超過{integer}") 
			@Min(value = 6000001, message = "3產品編號 不能小於{value}") 
			@Max(value = 6999999, message = "4產品編號 不能大於{value}") 
			@RequestParam("pro_no") String pro_no,
			ModelMap model) {
		
		ProductService productSvc = new ProductService();
		ProductVO productVO = productSvc.getOneProduct(new Integer(pro_no));
		System.out.println("1111111111");
		if(productVO == null) {
			model.addAttribute("errorMessage1", "查無資料from SpringController2");
		    return "back_end/product/select_Product";
		}
		
		    model.addAttribute("productVO",productVO);
		return "back_end/product/listOneProduct";
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    String message = strBuilder.toString();
	    return new ModelAndView("back_end/product/select_Product", "errorMessage1", "5請修正以下錯誤:<br>"+message);
	}

}
