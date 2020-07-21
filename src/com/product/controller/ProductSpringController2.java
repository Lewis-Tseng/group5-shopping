package com.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.product.model.ProductService;
import com.product.model.ProductVO;

@Controller
@Validated
@RequestMapping({"/back_end/product","/front_end/product"})
public class ProductSpringController2 {
	
	@RequestMapping(method = RequestMethod.POST, value = "getOne_For_Display")
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
	
	@RequestMapping(method = RequestMethod.POST, value = "listProduct_ByCompositeQuery")
	public String listProduct_ByCompositeQuery(
			@NotNull(message="員工姓名: 請勿空白")
			@Size(max = 100, min = 0, message = "輸入字元在{min}到{max}之間") 
			@RequestParam Map<String, String[]> map1,
//			HttpServletRequest req, 
			ModelMap model) {
		
//		HttpSession session = req.getSession();
//		Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//		if (req.getParameter("whichPage") == null){//第一次空值
//      //因為原本取出的Map資料Java不能動,所以用new一個新的HashMap存回去,把舊Map的限制行為洗掉 
//		//	HashMap<String, String[]> map1 = new HashMap<String, String[]>(((ServletRequest) req).getParameterMap());
//			session.setAttribute("map",map1);//處理過過水後  
//			map = map1;//存回去
//		} 
		
//		Set<String> keys = map1.keySet();
//		for (String key : keys) {
//			System.out.println(key + "," + map1.get(key));
//		}
	
		Map newMap = this.getParameterMap(map1);
		
		Set<String> keys = newMap.keySet();
		for (String key : keys) {
			System.out.println(key + "," + newMap.get(key));
			
			String value = (String) newMap.get(key);
			
			System.out.println(key + "," + value);
			
			
		}
			
		ProductService productSvc = new ProductService();
		List<ProductVO> list  = productSvc.getAll_CompositeQuery(newMap);
		list = list.stream()
				.filter(p -> p.getPro_sta().equals("0"))
				.collect(Collectors.toList());
		        Collections.reverse(list);
		        
		        for(ProductVO pVO : list) {
		        	System.out.println(pVO.getPro_nam());    	
		        }		       		    		        
		if(list == null) {
			model.addAttribute("errorMessage1", "查無資料from SpringController3");
		}
		
		model.addAttribute("listProduct_ByCompositeQuery",list);
		return "front_end/product_front/shopping_mall_home_search";
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

	public static Map getParameterMap(Map<String, String[]> map) {
	    // 引數Map
	    Map properties = map;
	    // 返回值Map
	    Map returnMap = new HashMap();
	    Iterator entries = properties.entrySet().iterator();
	    Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        returnMap.put(name, value);
	    }
	    return returnMap;
	}
	
	
	
}
