package com.util.product;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.util.product.ImageUtil;

/**
 * Servlet implementation class Reader_Image
 */
@WebServlet("/Reader_Image")
public class Reader_Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}   
	
	
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		req.setCharacterEncoding("UTF-8");//注意中文字編碼問題 向POST看齊
		ServletOutputStream out = res.getOutputStream();
        //出問題(Class Not Found)請參考p394
		try {
			Statement stmt = con.createStatement();
			
			String pro_no = req.getParameter("pro_no");// 放在try裡面
			String imgAll = null;
			String imgCl = null;
			if (pro_no != null) {
				imgAll = pro_no;
				imgCl = "pro_no";
			} else {
				String pro_img_no = req.getParameter("pro_img_no");
				imgAll = pro_img_no;
				imgCl = "pro_img_no";
			}
			
			ResultSet rs = stmt.executeQuery(

			    "SELECT img FROM product_image WHERE "+imgCl+" = '"+imgAll+"'");//中文記得用字元' '
					
			int imageSize = 0;
					
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("img"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					if (buf != null) {
						buf = ImageUtil.shrink(buf, imageSize);
					}
					out.write(buf, 0, len);
				}
				in.close();
					
				
				
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


}
