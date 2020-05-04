package com.gro.type;

import java.util.*;
import java.sql.*;

public class groclassJDBCDAO implements groclassDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO GRO_CLASS (GRO_CID, GRO_CNAME) VALUES ('GC'||LPAD(to_char(GRO_CLASS_SEQ.NEXTVAL), 5, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_CID, GRO_CNAME FROM GRO_CLASS ORDER BY GRO_CID";
	private static final String GET_ONE_STMT = 
		"SELECT GRO_CID, GRO_CNAME FROM GRO_CLASS WHERE GRO_CID = ?";
	private static final String DELETE = 
		"DELETE FROM GRO_CLASS WHERE GRO_CID = ?";
	private static final String UPDATE = 
		"UPDATE GRO_CLASS SET GRO_CNAME=? WHERE GRO_CID = ?";

	//INSERT INTO GRO_CLASS (GRO_CID, GRO_CNAME) VALUES ('GC'||LPAD(to_char(GRO_CLASS_SEQ.NEXTVAL), ?)
	@Override
	public void insert(groclassVO groclassVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, groclassVO.getGro_cname());

			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	//UPDATE GRO_CLASS SET GRO_CNAME=? WHERE GRO_CID = ?
	@Override
	public void update(groclassVO groclassVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, groclassVO.getGro_cname());
			pstmt.setString(2, groclassVO.getGro_cid());

			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	//DELETE FROM GRO_CLASS WHERE GRO_CID = ?
	@Override
	public void delete(String gro_cid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gro_cid);

			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	//SELECT GRO_CID, GRO_CNAME FROM GRO_CLASS WHERE GRO_CID = ?
	@Override
	public groclassVO findByPrimaryKey(String gro_cid) {

		groclassVO groclassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_cid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				groclassVO = new groclassVO();
				groclassVO.setGro_cid(rs.getString("GRO_CID"));
				groclassVO.setGro_cname(rs.getString("GRO_CNAME"));
				
			}


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
		return groclassVO;
	}

	//SELECT GRO_CID, GRO_CNAME FROM GRO_CLASS ORDER BY GRO_CID
	@Override
	public List<groclassVO> getAll() {
		List<groclassVO> list = new ArrayList<groclassVO>();
		groclassVO groclassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				groclassVO = new groclassVO();
				groclassVO.setGro_cid(rs.getString("GRO_CID"));
				groclassVO.setGro_cname(rs.getString("GRO_CNAME"));
				list.add(groclassVO);
			}


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	public static void main(String[] args) {

		groclassJDBCDAO dao = new groclassJDBCDAO();

		// 新增
		groclassVO groclassVO1 = new groclassVO();
		
		groclassVO1.setGro_cname("測試");
		
		dao.insert(groclassVO1);

		// 修改
//		groclassVO groclassVO2 = new groclassVO();
//		
//		groclassVO2.setGro_cname("測試修改");
//		groclassVO2.setGro_cid("GC00001");
//		
//		dao.update(groclassVO2);

		// 刪除
//		dao.delete("GC00004");

		// 查詢
//		groclassVO groclassVO3 = dao.findByPrimaryKey("GC00001");
//		
//		System.out.print(groclassVO3.getGro_cid() + ",");
//		System.out.print(groclassVO3.getGro_cname());

		// 查詢(所有資料)
//		List<groclassVO> list = dao.getAll();
//		
//		for (groclassVO aClass : list) {
//			System.out.print(aClass.getGro_cid() + ",");
//			System.out.print(aClass.getGro_cname());
//			System.out.println();
//		
//		}
	}
}