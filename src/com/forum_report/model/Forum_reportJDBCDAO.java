package com.forum_report.model;

import java.util.*;

import com.forum.model.ForumVO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

public class Forum_reportJDBCDAO implements Forum_reportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO forum_report(FORUM_REP_ID,FORUM_ID,FORUM_MSG_ID,FORUM_REP_TIME,FORUM_REP_INFO,FORUM_REP_STAT) VALUES ('FR'||LPAD(TO_CHAR(FORUM_REPORT_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?)";                       
	private static final String GET_ALL_STMT = 
			"SELECT FORUM_REP_ID,FORUM_ID,FORUM_MSG_ID,FORUM_REP_TIME,FORUM_REP_INFO,FORUM_REP_STAT FROM forum_report order by FORUM_REP_ID"; 
	private static final String GET_ONE_STMT = 
			"SELECT FORUM_REP_ID,FORUM_ID,FORUM_MSG_ID,FORUM_REP_TIME,FORUM_REP_INFO,FORUM_REP_STAT FROM forum_report where FORUM_REP_ID = ?";
	private static final String DELETE_FORUM_REP_ID = 
			"DELETE FROM forum_report where FORUM_REP_ID=?";
	private static final String UPDATE = 
			"UPDATE forum_report set FORUM_ID=? ,FORUM_MSG_ID=? ,FORUM_REP_TIME=? ,FORUM_REP_INFO=? ,FORUM_REP_STAT=? where FORUM_REP_ID=?";

	public static Reader getLongStringStream(String path) throws IOException {
		return new FileReader(path);
	}
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	
	@Override
	public void insert(Forum_reportVO forum_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, forum_reportVO.getForum_id());
			pstmt.setString(2, forum_reportVO.getForum_msg_id());
			pstmt.setTimestamp(3, forum_reportVO.getForum_rep_time());
			pstmt.setString(4, forum_reportVO.getForum_rep_info());   //
			pstmt.setString(5, forum_reportVO.getForum_rep_stat());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Forum_reportVO forum_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, forum_reportVO.getForum_id());
			pstmt.setString(2, forum_reportVO.getForum_msg_id());
			pstmt.setTimestamp(3, forum_reportVO.getForum_rep_time());
			pstmt.setString(4, forum_reportVO.getForum_rep_info());   //
			pstmt.setString(5, forum_reportVO.getForum_rep_stat());
			pstmt.setString(6, forum_reportVO.getForum_rep_id());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String forum_rep_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_FORUM_REP_ID);
			pstmt.setString(1, forum_rep_id);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Forum_reportVO findByPrimaryKey(String forum_rep_id) {
		Forum_reportVO forum_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, forum_rep_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forum_reportVO = new Forum_reportVO();
				forum_reportVO.setForum_rep_id(rs.getString("forum_rep_id"));
				forum_reportVO.setForum_id(rs.getString("forum_id"));
				forum_reportVO.setForum_msg_id(rs.getString("forum_msg_id"));
				forum_reportVO.setForum_rep_time(rs.getTimestamp("forum_rep_time"));
				forum_reportVO.setForum_rep_info(rs.getString("forum_rep_info"));
				forum_reportVO.setForum_rep_stat(rs.getString("forum_rep_stat"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forum_reportVO;
	}

	@Override
	public List<Forum_reportVO> getAll() {
		List<Forum_reportVO> list = new ArrayList<Forum_reportVO>();
		Forum_reportVO forum_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				forum_reportVO = new Forum_reportVO();
				forum_reportVO.setForum_rep_id(rs.getString("forum_rep_id"));
				forum_reportVO.setForum_id(rs.getString("forum_id"));
				forum_reportVO.setForum_msg_id(rs.getString("forum_msg_id"));
				forum_reportVO.setForum_rep_time(rs.getTimestamp("forum_rep_time"));
				forum_reportVO.setForum_rep_info(rs.getString("forum_rep_info"));
				forum_reportVO.setForum_rep_stat(rs.getString("forum_rep_stat"));
				list.add(forum_reportVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
//	public static void main(String[] args) throws IOException {
//		Forum_reportJDBCDAO dao = new Forum_reportJDBCDAO();
//		String str = getLongString("item/健身.txt");
		
	// 新增
//		Forum_reportVO forum_reportVO1 = new Forum_reportVO();
//		forum_reportVO1.setForum_id("FO00002");
//		forum_reportVO1.setForum_msg_id("FM00002");
//		forum_reportVO1.setForum_rep_time(null);
//		forum_reportVO1.setForum_rep_info(str);
//		forum_reportVO1.setForum_rep_stat("0");
//		dao.insert(forum_reportVO1);
//		System.out.println("新增成功");

	// 修改
//		Forum_reportVO forum_reportVO2 = new Forum_reportVO();
//		forum_reportVO2.setForum_rep_id("FR00002");
//		forum_reportVO2.setForum_id("FO00002");
//		forum_reportVO2.setForum_msg_id("FM00002");
//		forum_reportVO2.setForum_rep_time(null);
//		forum_reportVO2.setForum_rep_info(str);
//		forum_reportVO2.setForum_rep_stat("0");
//		dao.update(forum_reportVO2);

	// 刪除
//		dao.delete("FR00005");

	// 查詢
//		Forum_reportVO forum_reportVO3 = dao.findByPrimaryKey("FR00002");
//		System.out.print(forum_reportVO3.getForum_rep_id() + ",");
//		System.out.print(forum_reportVO3.getForum_id() + ",");
//		System.out.print(forum_reportVO3.getForum_msg_id() + ",");
//		System.out.print(forum_reportVO3.getForum_rep_time() + ",");
//		System.out.print(forum_reportVO3.getForum_rep_info() + ",");
//		System.out.print(forum_reportVO3.getForum_rep_stat() + ",");
//		System.out.println("---------------------");

	// 查詢
//		List<Forum_reportVO> list = dao.getAll();
//		for (Forum_reportVO aForum_report : list) {
//			System.out.print(aForum_report.getForum_rep_id() + ",");
//			System.out.print(aForum_report.getForum_id() + ",");
//			System.out.print(aForum_report.getForum_msg_id() + ",");
//			System.out.print(aForum_report.getForum_rep_time() + ",");
//			System.out.print(aForum_report.getForum_rep_info() + ",");
//			System.out.print(aForum_report.getForum_rep_stat() + ",");
//
//			System.out.println();
//		}
//	}
}