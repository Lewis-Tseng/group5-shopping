package com.emp_auth.model;

import java.util.*;

import com.administrator.model.AdmVO;
import com.authority.model.AuthVO;
import com.emp_auth.model.EaDAO_interface;
import com.emp_auth.model.EaJDBCDAO;
import com.emp_auth.model.EaVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class EaJDBCDAO implements EaDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO EMP_AUTH(emp_id,auth_id) VALUES(?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT emp_id,auth_id FROM EMP_AUTH order by emp_id";
		private static final String GET_AUTHS_STMT = 
			"SELECT emp_id,auth_id FROM EMP_AUTH where emp_id = ?";
		private static final String DELETE = 
			"DELETE FROM EMP_AUTH where emp_id = ? and auth_id = ?";
		private static final String UPDATE = 
			"UPDATE EMP_AUTH set auth_id=? where emp_id = ?";
		
	@Override
	public void insert(EaVO eaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, eaVO.getEmp_id());
			pstmt.setString(2, eaVO.getAuth_id());
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
	public void update(EaVO eaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, eaVO.getAuth_id());
			pstmt.setString(2, eaVO.getEmp_id());
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
	public void delete(String emp_id,String auth_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id);
			pstmt.setString(2, auth_id);

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

	
	//emp_id,   auth_id
	@Override
	public Set<EaVO> getAuthsByEmp(String emp_id) {
		Set<EaVO> set = new LinkedHashSet<EaVO>();
		EaVO eaVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_AUTHS_STMT);
			pstmt.setString(1, emp_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				eaVO = new EaVO();
				eaVO.setEmp_id(rs.getString("emp_id"));
				eaVO.setAuth_id(rs.getString("auth_id"));
				
				set.add(eaVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return set;
	}

	@Override
	public List<EaVO> getAll() {
		List<EaVO> list = new ArrayList<EaVO>();
		EaVO eaVO = null;

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
				eaVO = new EaVO();
				eaVO.setEmp_id(rs.getString("emp_id"));
				eaVO.setAuth_id(rs.getString("auth_id"));
				
				
				list.add(eaVO);  // Store the row in the list
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

	public static void main(String[] args) throws IOException {
//		byte[] pic = getPictureByteArray("items/�^��.png");
		EaJDBCDAO dao = new EaJDBCDAO();
		
		// �s�W
		EaVO eaVO1 = new EaVO();
		eaVO1.setEmp_id("EM00003");
		eaVO1.setAuth_id("AU00001");
		

		dao.insert(eaVO1);

		// �ק�
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setAuth_id("AU00002");
//		authVO2.setAuth_name("jay");
//		
//		
//		dao.update(authVO2);
//////
//////		// �R��
		
//		dao.delete("EM00001","AU00003");
//////
		// �d��(�����u�Ҧ��v��)
//		Set<EaVO> set = dao.getAuthsByEmp("EM00001");
//		for (EaVO aEa : set) {
//		System.out.print(aEa.getEmp_id()+" ,");
//		System.out.print(aEa.getAuth_id());
//		System.out.println();
//		}
		
////
////		// �d��
		List<EaVO> list = dao.getAll();
		for (EaVO aEa : list) {
			System.out.print(aEa.getEmp_id() + ",");
			System.out.print(aEa.getAuth_id());
			
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
