package com.administrator.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQueryEmployee.jdbcUtil_CompositeQuery_Emp2;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class AdmJDBCDAO implements AdmDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO ADMINISTRATOR(emp_id,emp_name,emp_psw,emp_email,emp_pic) VALUES(('EM'||LPAD(to_char(ADMINISTRATOR_seq.NEXTVAL),5,'0')), ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR order by emp_id";
		private static final String GET_ONE_STMT = 
			"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR where emp_id = ?";
		private static final String DELETE = 
			"DELETE FROM ADMINISTRATOR where emp_id = ?";
		private static final String UPDATE = 
			"UPDATE ADMINISTRATOR set emp_name=?, emp_psw=? where emp_id = ?";
		private static final String INSERT_STMT1 = 
				"INSERT INTO ADMINISTRATOR(emp_id,emp_name,emp_psw) VALUES(('EM'||LPAD(to_char(ADMINISTRATOR_seq.NEXTVAL),5,'0')), ?)";
		private static final String GET_ONE_STMT_NAME=
				"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR where emp_name = ?";

		@Override
		public AdmVO findByInsertName(String emp_name) {

			AdmVO admVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT_NAME);

				pstmt.setString(1, emp_name);

				rs = pstmt.executeQuery();

				while (rs.next()) {
						admVO = new AdmVO();
					admVO.setEmp_id(rs.getString("emp_id"));
					admVO.setEmp_name(rs.getString("emp_name"));
					admVO.setEmp_psw(rs.getString("emp_psw"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return admVO;
		}

		@Override
		public void insert1(AdmVO admVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT1);

				pstmt.setString(1, admVO.getEmp_name());
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public void insert(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getEmp_name());
			pstmt.setString(2, admVO.getEmp_psw());
			pstmt.setString(3, admVO.getEmp_email());
			pstmt.setBytes(4, admVO.getEmp_pic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getEmp_name());
			pstmt.setString(2, admVO.getEmp_psw());
			pstmt.setString(3, admVO.getEmp_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public AdmVO findByPrimaryKey(String emp_id) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo �]�٬� Domain objects
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO �]�٬� Domain objects
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
				
				list.add(admVO);// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<AdmVO> getAll(Map<String, String[]> map) {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			
			String finalSQL = "select * from administrator "
		          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
		          + "order by emp_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
				
				list.add(admVO); // Store the row in the List
			}
	
			// Handle any SQL errors
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
//		byte[] pic = getPictureByteArray("items/�^��.png");
		AdmJDBCDAO dao = new AdmJDBCDAO();
//		
//		// �s�W
//		AdmVO admVO1 = new AdmVO();
//		admVO1.setEmp_name("HENRY");
//		admVO1.setEmp_psw("123456");
//		
//
//		dao.insert(admVO1);

		// �ק�
//		AdmVO admVO2 = new AdmVO();
//		admVO2.setEmp_id("EM00003");
//		admVO2.setEmp_name("george");
//		admVO2.setEmp_psw("12345");
//		
//		dao.update(admVO2);
////
////		// �R��
//		dao.delete("EM00003");
////
////		// �d��
//		AdmVO admVO3 = dao.findByPrimaryKey("EM00001");
//		System.out.print(admVO3.getEmp_id() + ",");
//		System.out.print(admVO3.getEmp_name() + ",");
//		System.out.println(admVO3.getEmp_psw());
//		
//		System.out.println("---------------------");
//
//		// �d��
		List<AdmVO> list = dao.getAll();
		for (AdmVO aAdm : list) {
			System.out.print(aAdm.getEmp_id() + ",");
			System.out.print(aAdm.getEmp_name() + ",");
			System.out.print(aAdm.getEmp_psw());
			System.out.println();
		}
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);//���Y
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	
	
}
