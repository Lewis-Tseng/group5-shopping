package com.forum.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// 或者註冊在WEB.XML
@WebServlet("/DBGifReaderForum")
public class DBGifReaderForum extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String forum_id = req.getParameter("forum_id");
			String forum_msg_id = req.getParameter("forum_msg_id");
			ResultSet rs = null;
			if(forum_msg_id==null) {
				rs = stmt.executeQuery(
					"SELECT FORUM_PIC FROM FORUM WHERE FORUM_ID='"+forum_id+"'");
				
					if (rs.next()) {
						BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("forum_pic"));
						byte[] buf = new byte[4 * 1024]; // 4K buffer
						int len;
						while ((len = in.read(buf)) != -1) {
							out.write(buf, 0, len);
						}
						in.close();
					} else {
						res.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
			}else if(forum_id==null) {
				rs = stmt.executeQuery(
						"SELECT FORUM_MSG_PIC FROM FORUM_MESSAGE WHERE FORUM_MSG_ID='"+forum_msg_id+"'");
					
					if (rs.next()) {
						BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("forum_msg_pic"));
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