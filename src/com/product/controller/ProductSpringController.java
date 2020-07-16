package com.product.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@Controller
@RequestMapping("/back_end/product")
public class ProductSpringController {
	
	@RequestMapping(method = RequestMethod.GET, value = "addProduct")
	public String addProduct(ModelMap model) {
		ProductVO productVO = new ProductVO();
		model.addAttribute("productVO", productVO);
		return "back_end/product/addProduct";
	}

	@RequestMapping(method = RequestMethod.POST, value = "insert")
	public String insert(@Valid ProductVO productVO, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "back_end/product/addProduct";
		}

		ProductService productSvc = new ProductService();
		productSvc.addProduct(productVO);

		model.addAttribute("success", "新增成功");
		return "back_end/product/listAllProduct";
	}

	@RequestMapping(method = RequestMethod.POST, value = "getOne_For_Update")
	public String getOne_For_Update(@RequestParam("pro_no") String pro_no, ModelMap model) {

		ProductService productSvc = new ProductService();
		ProductVO productVO = productSvc.getOneProduct(new Integer(pro_no));

		model.addAttribute("productVO", productVO);
		return "back_end/product/update_product_input";
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="update")
	public String update(@Valid ProductVO productVO,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "back_end/product/update_product_input";
		}
		
		ProductService productSvc = new ProductService();
		productSvc.updateProduct(productVO);
		System.out.println("update成功");
		
		model.addAttribute("success", "修改成功");
		return "back_end/product/listOneProduct";
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="delete")
	public String delete(@RequestParam Integer pro_no, ModelMap model) {
		
		ProductService productSvc = new ProductService();
		productSvc.deleteProduct(new Integer(pro_no));
		System.out.println("資料庫 delete 成功");
		
		return "back_end/product/listAllProduct";
	}
	
	
	
	
	
	
	
	
	
	
	

}
