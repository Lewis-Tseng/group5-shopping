package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;

public class AjaxEmailServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		MemService memSvc = new MemService();
		List<String> memList =memSvc.getAllEmail();
		
		String mem_email = req.getParameter("mem_email");
		String str;
		
		
		if(mem_email.contains("@")&&mem_email.contains(".com")){
			str = "此信箱可以使用";
		}else {
			str="此信箱不可使用";
		}
		
		for(String email : memList) {
			if(email.equals(mem_email)) {
				str="此信箱已註冊";
			}
		}
		out.print(str);
		
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}

}
