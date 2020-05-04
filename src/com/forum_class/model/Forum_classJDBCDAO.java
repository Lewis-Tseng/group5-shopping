package com.forum_class.model;

import java.util.*;
import java.sql.*;

public class Forum_classJDBCDAO implements Forum_classDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO forum_class (FORUM_CLS_ID,FORUM_CLS_NAM) VALUES ('FC'||LPAD(TO_CHAR(FORUM_CLASS_SEQ.NEXTVAL),5,'0'), ?)";
	private static final String GET_ALL_STMT = "SELECT FORUM_CLS_ID,FORUM_CLS_NAM FROM forum_class order by FORUM_CLS_ID"; 
	private static final String GET_ONE_STMT = "SELECT FORUM_CLS_ID,FORUM_CLS_NAM FROM forum_class where FORUM_CLS_ID = ?";
	private static final String DELETE_FORUM_ClS_ID = "DELETE FROM forum_class where FORUM_CLS_ID=?";
	private static final String UPDATE = "UPDATE forum_class set FORUM_CLS_NAM=? where FORUM_CLS_ID = ?";

	@Override
	public void insert(Forum_classVO forum_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, forum_classVO.getForum_cls_nam());
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
	public void update(Forum_classVO forum_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(2, forum_classVO.getForum_cls_id());
			pstmt.setString(1, forum_classVO.getForum_cls_nam());
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
	public void delete(String forum_cls_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_FORUM_ClS_ID);
			pstmt.setString(1, forum_cls_id);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
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
	public Forum_classVO findByPrimaryKey(String forum_cls_id) {
		Forum_classVO forum_classVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, forum_cls_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forum_classVO = new Forum_classVO();
				forum_classVO.setForum_cls_id(rs.getString("forum_cls_id"));
				forum_classVO.setForum_cls_nam(rs.getString("forum_cls_nam"));
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
		return forum_classVO;
	}

	@Override
	public List<Forum_classVO> getAll() {
		List<Forum_classVO> list = new ArrayList<Forum_classVO>();
		Forum_classVO forum_classVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				forum_classVO = new Forum_classVO();
				forum_classVO.setForum_cls_id(rs.getString("forum_cls_id"));
				forum_classVO.setForum_cls_nam(rs.getString("forum_cls_nam"));
				list.add(forum_classVO); // Store the row in the list
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

//	public static void main(String[] args) {
//		Forum_classJDBCDAO dao = new Forum_classJDBCDAO();
//		
	// 新增
//		Forum_classVO forum_classVO1 = new Forum_classVO();
//		forum_classVO1.setForum_cls_nam("吃飯睡覺");
//		dao.insert(forum_classVO1);

	// 修改
//		Forum_classVO forum_classVO2 = new Forum_classVO();
//		forum_classVO2.setForum_cls_id("FC00001");
//		forum_classVO2.setForum_cls_nam("吃飯睡覺");
//		dao.update(forum_classVO2);
	
	// 刪除
//		dao.delete("FC00005");

	// 查詢
//		Forum_classVO forum_classVO3 = dao.findByPrimaryKey("FC00003");
//		System.out.print(forum_classVO3.getForum_cls_id() + ",");
//		System.out.print(forum_classVO3.getForum_cls_nam() + ",");
//		System.out.println("---------------------");

	// 查詢
//		List<Forum_classVO> list = dao.getAll();
//		for (Forum_classVO aForum_class : list) {
//			System.out.print(aForum_class.getForum_cls_id() + ",");
//			System.out.print(aForum_class.getForum_cls_nam() + ",");
//			System.out.println();
//		}
//	}
}