package com.gro.report;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class reportJDBCDAO implements reportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO GRO_REPORT (GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT) VALUES ('GP'||LPAD(to_char(GRO_REPORT_SEQ.NEXTVAL), 5, '0'), ?, ?, 0)";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT ORDER BY GRO_REPID";
	private static final String GET_ONE_STMT = 
		"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT WHERE GRO_REPID = ?";
	private static final String DELETE = 
		"DELETE FROM GRO_REPORT WHERE GRO_REPID = ?";
	private static final String UPDATE = 
		"UPDATE GRO_REPORT SET GRO_ID=?, GRO_REP_INFO=?, GRO_REP_STAT=? WHERE GRO_REPID = ?";

	
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
	
	//"INSERT INTO GRO_REPORT (GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT) VALUES ('GP'||LPAD(to_char(GRO_REPORT_SEQ.NEXTVAL), 5, '0'), ?, ?, ?)"
	@Override
	public void insert(reportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reportVO.getGro_id());
			pstmt.setString(2, reportVO.getGro_rep_info());
			

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

	//"UPDATE GRO_REPORT SET GRO_ID=?, GRO_REP_INFO=?, GRO_REP_STAT=? WHERE GRO_REPID = ?"
	@Override
	public void update(reportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reportVO.getGro_id());
			pstmt.setString(2, reportVO.getGro_rep_info());
			pstmt.setInt(3, reportVO.getGro_rep_stat());
			pstmt.setString(4, reportVO.getGro_repid());

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

	//"DELETE FROM GRO_REPORT WHERE GRO_REPID = ?"
	@Override
	public void delete(String gro_repid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gro_repid);

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

	//"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT WHERE GRO_REPID = ?"
	@Override
	public reportVO findByPrimaryKey(String gro_repid) {

		reportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_repid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportVO = new reportVO();
				reportVO.setGro_repid(rs.getString("GRO_REPID"));
				reportVO.setGro_id(rs.getString("GRO_ID"));
				reportVO.setGro_rep_info(rs.getString("GRO_REP_INFO"));
				reportVO.setGro_rep_stat(rs.getInt("GRO_REP_STAT"));
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
		return reportVO;
	}

	//"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT ORDER BY GRO_REPID"
	@Override
	public List<reportVO> getAll() {
		List<reportVO> list = new ArrayList<reportVO>();
		reportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportVO = new reportVO();
				reportVO.setGro_repid(rs.getString("GRO_REPID"));
				reportVO.setGro_id(rs.getString("GRO_ID"));
				reportVO.setGro_rep_info(rs.getString("GRO_REP_INFO"));
				reportVO.setGro_rep_stat(rs.getInt("GRO_REP_STAT"));
				list.add(reportVO);
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

	public static void main(String[] args) throws IOException {

		reportJDBCDAO dao = new reportJDBCDAO();

		// 新增
//		String str1 = getLongString("item/rep1.txt");
//		reportVO reportVO1 = new reportVO();
//		
//		reportVO1.setGro_id("GR00002");
//		reportVO1.setGro_rep_info(str1);
//		
//		dao.insert(reportVO1);

		// 修改
		String str2 = getLongString("item/rep2.txt");
		reportVO reportVO2 = new reportVO();
		
		reportVO2.setGro_id("GR00001");
		reportVO2.setGro_rep_info(str2);
		reportVO2.setGro_rep_stat(1);
		reportVO2.setGro_repid("GP00001");
		
		dao.update(reportVO2);

		// 刪除
//		dao.delete("GP00002");

		// 查詢
//		reportVO reportVO3 = dao.findByPrimaryKey("GP00001");
//		
//		System.out.print(reportVO3.getGro_repid() + ",");
//		System.out.print(reportVO3.getGro_id() + ",");
//		System.out.print(reportVO3.getGro_rep_info() + ",");
//		System.out.print(reportVO3.getGro_rep_stat());


		// 查詢(所有資料)
//		List<reportVO> list = dao.getAll();
//		
//		for (reportVO aRep : list) {
//			System.out.print(aRep.getGro_repid() + ",");
//			System.out.print(aRep.getGro_id() + ",");
//			System.out.print(aRep.getGro_rep_info() + ",");
//			System.out.print(aRep.getGro_rep_stat());
//			System.out.println();
//		}
		
	}
}