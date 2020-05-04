package com.gro.group.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DBGifReaderGro extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String gro_pid = req.getParameter("gro_pid");
			String gro_id = req.getParameter("gro_id");
			String mem_id = req.getParameter("mem_id");
			
			ResultSet rs = null;
			if(gro_id==null && gro_pid!=null) {
				rs = stmt.executeQuery(
						"SELECT GRO_PIC FROM GRO_PIC  WHERE GRO_PID='"+gro_pid+"' ");
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("GRO_PIC"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			}else if(gro_pid==null && gro_id!=null) {
				rs = stmt.executeQuery(
						"SELECT GRO_LOGO_PIC FROM RGROUP  WHERE GRO_ID='"+gro_id+"' ");
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("GRO_LOGO_PIC"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			}else if (mem_id != null) {
				rs = stmt.executeQuery(
						"SELECT MEM_PIC FROM MEMBER  WHERE MEM_ID='"+mem_id+"' ");
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("MEM_PIC"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			}
						
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA103G5", "123456");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
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