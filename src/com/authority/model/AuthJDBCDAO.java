package com.authority.model;

import java.util.*;

import com.administrator.model.AdmVO;
import com.authority.model.AuthDAO_interface;
import com.authority.model.AuthJDBCDAO;
import com.authority.model.AuthVO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class AuthJDBCDAO implements AuthDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO AUTHORITY(auth_id,auth_name) VALUES(('AU'||LPAD(to_char(AUTHORITY_seq.NEXTVAL),5,'0')), ?)";
		private static final String GET_ALL_STMT = 
			"SELECT auth_id,auth_name FROM AUTHORITY order by auth_id";
		private static final String GET_ONE_STMT = 
			"SELECT auth_id,auth_name FROM AUTHORITY where auth_id = ?";
		private static final String DELETE = 
			"DELETE FROM AUTHORITY where auth_id = ?";
		private static final String UPDATE = 
			"UPDATE AUTHORITY set auth_name=? where auth_id = ?";
		private static final String INSERT_EMP = "INSERT INTO  AUTHORITY (auth_id,auth_name) VALUES ('AU'||LPAD(to_char(AUTHORITY_seq.NEXTVAL),5,'0')), ?)";
	@Override
	public void insert(AuthVO authVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authVO.getAuth_name());
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
	public void update(AuthVO authVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_id());
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
	public void delete(String auth_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, auth_id);

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
	public AuthVO findByPrimaryKey(String auth_id) {

		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, auth_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo �]�٬� Domain objects
				authVO = new AuthVO();
				authVO.setAuth_id(rs.getString("auth_id"));
				authVO.setAuth_name(rs.getString("auth_name"));
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
		return authVO;
	}

	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

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
				authVO = new AuthVO();
				authVO.setAuth_id(rs.getString("auth_id"));
				authVO.setAuth_name(rs.getString("auth_name"));
				
				
				list.add(authVO); // Store the row in the list
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
		AuthJDBCDAO dao = new AuthJDBCDAO();
//		
//		// �s�W
		AuthVO authVO1 = new AuthVO();
		authVO1.setAuth_name("middle");
		
		

		dao.insert(authVO1);

		// �ק�
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setAuth_id("AU00002");
//		authVO2.setAuth_name("jay");
//		
//		
//		dao.update(authVO2);
//////
//////		// �R��
//		dao.delete("AU00002");
//////
//////		// �d��
//		AuthVO AuthVO3 = dao.findByPrimaryKey("AU00001");
//		System.out.print(AuthVO3.getAuth_id() + ",");
//		System.out.print(AuthVO3.getAuth_name());
//		
//		
//		System.out.println("---------------------");
////
////		// �d��
//		List<AuthVO> list = dao.getAll();
//		for (AuthVO aAuth : list) {
//			System.out.print(aAuth.getAuth_id() + ",");
//			System.out.print(aAuth.getAuth_name());
//			
//			System.out.println();
//		}
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
