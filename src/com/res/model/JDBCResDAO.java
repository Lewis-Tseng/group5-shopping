package com.res.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crs.model.CrsVO;

public class JDBCResDAO implements ResDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO RES (RES_NO, MEM_ID, COA_ID, "
			+ "RES_DAY, RES_STATUS) VALUES('RN'||LPAD(to_char(RES_NO_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?)";

	private static final String DELETE = "DELETE FROM RES where res_no = ?";

	private static final String GET_ONE_STMT = "SELECT RES_NO, MEM_ID, COA_ID, "
			+ "to_char(RES_DAY, 'yyyy-mm-dd') RES_DAY, RES_STATUS FROM RES where res_no = ?";

	private static final String UPDATE = "UPDATE RES SET MEM_ID=?, COA_ID=?, RES_DAY=?, "
			+ "RES_STATUS=? where res_no =?";

	private static final String UPDATE_STATUS = "UPDATE RES SET RES_STATUS=? where res_no =?";
	
	private static final String GET_ALL_STMT = "SELECT  RES_NO, MEM_ID, COA_ID,"		
			+"to_char(RES_DAY, 'yyyy-mm-dd') RES_DAY, RES_STATUS FROM RES order by res_no";
	
	private static final String  GET_RES_DAY="select RES_DAY from RES where coa_id=?";
	
	private static final String GET_RES_DAY_MEM="select RES_DAY,RES_STATUS from RES where mem_id=?";
	
	


	@Override
	public void insert(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resVO.getMem_id());
			pstmt.setString(2, resVO.getCoa_id());
			pstmt.setDate(3, resVO.getRes_day());
			pstmt.setString(4, resVO.getRes_status());
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resVO.getMem_id());
			pstmt.setString(2, resVO.getCoa_id());
			pstmt.setDate(3, resVO.getRes_day());
			pstmt.setString(4, resVO.getRes_status());
			pstmt.setString(5, resVO.getRes_no());

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
	public void updateStatus(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, resVO.getRes_status());
			pstmt.setString(2, resVO.getRes_no());
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
	public void delete(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

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
	public ResVO findByPrimaryKey(String res_no) {
		ResVO ResVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ResVO = new ResVO();
				ResVO.setRes_no(rs.getString("res_no"));
				ResVO.setMem_id(rs.getString("mem_id"));
				ResVO.setCoa_id(rs.getString("coa_id"));
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
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
		return ResVO;

	}

	@Override
	public List<ResVO> getAll() {

		List<ResVO> list = new ArrayList<ResVO>();
		ResVO ResVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ResVO = new ResVO();
				ResVO.setRes_no(rs.getString("res_no"));
				ResVO.setMem_id(rs.getString("mem_id"));
				ResVO.setCoa_id(rs.getString("coa_id"));
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
				list.add(ResVO); // Store the row in the list
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
	
	@Override
	public List<ResVO> getResDay(String coa_id) {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO ResVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RES_DAY);

			pstmt.setString(1, coa_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ResVO = new ResVO();
				ResVO.setRes_day(rs.getDate("res_day"));
				list.add(ResVO);
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

	public static void main(String[] args) {
		JDBCResDAO dao = new JDBCResDAO();

		ResVO resVO = new ResVO();

		// 新增
//		resVO.setMem_id("ME00003");
//		resVO.setCoa_id("CO00003");
//		resVO.setRes_day(java.sql.Date.valueOf("2019-11-11"));
//		resVO.setRes_status("0");
//
//		dao.insert(resVO);

		// 修改
//		resVO.setRes_no("RN00016");
//		resVO.setMem_id("ME00005");
//		resVO.setCoa_id("CO00005");
//		resVO.setRes_day(java.sql.Date.valueOf("2019-10-01"));
//		resVO.setRes_status("1");
//		dao.update(resVO);
		
		resVO.setRes_no("RN00001");
		resVO.setRes_status("1");
		dao.updateStatus(resVO);

		// 刪除
//		dao.delete("RN00001");

		// 查詢
//		ResVO resVO1 = dao.findByPrimaryKey("RN00016");
//		System.out.println(resVO1.getRes_no());
//		System.out.println(resVO1.getMem_id());
//		System.out.println(resVO1.getCoa_id());	
//		System.out.println(resVO1.getRes_day());
//		System.out.println(resVO1.getRes_status());
		
		
		// 查詢 預約日期
		
//		List<ResVO> list = dao.getResDay("CO00003");
//		
//		for (ResVO aResVO  : list) {		
//		System.out.println(aResVO.getRes_day());
//		}
		//查全部
		
//		List<ResVO> list = dao.getAll();
//		for (ResVO aResVO  : list) {
//			System.out.print(aResVO.getRes_no() + ",");
//			System.out.print(aResVO.getMem_id() + ",");
//			System.out.print(aResVO.getCoa_id() + ",");
//			System.out.print(aResVO.getRes_day()+ "," );
//			System.out.print(aResVO.getRes_status());
//
//			System.out.println();
//		
//		}
	}

	@Override
	public List<ResVO> getResDayMem(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
