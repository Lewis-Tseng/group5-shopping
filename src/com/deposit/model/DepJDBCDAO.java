package com.deposit.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.coach.model.CoaJDBCDAO;
import com.coach.model.CoaVO;
import com.deposit.model.DepDAO_interface;
import com.deposit.model.DepVO;
import com.deposit.model.DepJDBCDAO;

public class DepJDBCDAO implements DepDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO DEPOSIT(dep_id,mem_id,coa_id,dep_money,dep_day,dep_sta) VALUES(('DE'||LPAD(to_char(DEPOSIT_seq.NEXTVAL),5,'0')), ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT dep_id,mem_id,coa_id,dep_money,dep_day,dep_sta FROM DEPOSIT order by dep_id";
		private static final String GET_ONE_STMT = 
			"SELECT dep_id,mem_id,coa_id,dep_money,dep_day,dep_sta FROM DEPOSIT where dep_id = ?";
		private static final String DELETE = 
			"DELETE FROM DEPOSIT where dep_id = ?";
		private static final String UPDATE = 
			"UPDATE DEPOSIT set mem_id=?, coa_id=?, dep_money=?, dep_day=?, dep_sta=? where dep_id = ?";
		private static final String UPDATE_STA = 
				"UPDATE DEPOSIT set  dep_sta=? where dep_id = ?";

	@Override
	public void insert(DepVO depVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, depVO.getMem_id());
			pstmt.setString(2, depVO.getCoa_id());
			pstmt.setInt(3, depVO.getDep_money());
			pstmt.setTimestamp(4, depVO.getDep_day());
			pstmt.setString(5, depVO.getDep_sta());
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
	public void update(DepVO depVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, depVO.getMem_id());
			pstmt.setString(2, depVO.getCoa_id());
			pstmt.setInt(3, depVO.getDep_money());
			pstmt.setTimestamp(4, depVO.getDep_day());
			pstmt.setString(5, depVO.getDep_sta());
			pstmt.setString(6, depVO.getDep_id());
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
	public void delete(String dep_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dep_id);

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
	public DepVO findByPrimaryKey(String dep_id) {

		DepVO depVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo �]�٬� Domain objects
				depVO = new DepVO();
				depVO.setDep_id(rs.getString("dep_id"));
				depVO.setMem_id(rs.getString("mem_id"));
				depVO.setCoa_id(rs.getString("coa_id"));
				depVO.setDep_money(rs.getInt("dep_money"));
				depVO.setDep_day(rs.getTimestamp("dep_day"));
				depVO.setDep_sta(rs.getString("dep_sta"));
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
		return depVO;
	}

	@Override
	public List<DepVO> getAll() {
		List<DepVO> list = new ArrayList<DepVO>();
		DepVO depVO = null;

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
				depVO = new DepVO();
				depVO.setDep_id(rs.getString("dep_id"));
				depVO.setMem_id(rs.getString("mem_id"));
				depVO.setCoa_id(rs.getString("coa_id"));
				depVO.setDep_money(rs.getInt("dep_money"));
				depVO.setDep_day(rs.getTimestamp("dep_day"));
				depVO.setDep_sta(rs.getString("dep_sta"));
				
				list.add(depVO); // Store the row in the list
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
	
	@Override
	public void updateSta(DepVO depVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STA);

			pstmt.setString(1, depVO.getDep_sta());

			pstmt.setString(2, depVO.getDep_id());
		
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
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


	public static void main(String[] args) throws IOException {
		byte[] pic = getPictureByteArray("items/�^��.png");
		DepJDBCDAO dao = new DepJDBCDAO();
		
		// �s�W
		DepVO depVO1 = new DepVO();
		depVO1.setMem_id("ME00001");
		depVO1.setCoa_id("CO00001");
		depVO1.setDep_money(3000);
		depVO1.setDep_day(null);
		depVO1.setDep_sta("0");

		dao.insert(depVO1);

		// �ק�
//		DepVO depVO2 = new DepVO();
//		depVO2.setDep_id("DE00001");
//		depVO2.setMem_id("ME00001");
//		depVO2.setCoa_id("CO00001");
//		depVO2.setDep_money(2000);
//		depVO2.setDep_day(null);
//		depVO2.setDep_sta("0");
//		dao.update(depVO2);
//
//		// �R��
//		dao.delete("DE00002");
//
//		// �d��
//		DepVO depVO3 = dao.findByPrimaryKey("DE00001");
//		System.out.print(depVO3.getDep_id() + ",");
//		System.out.print(depVO3.getMem_id() + ",");
//		System.out.print(depVO3.getCoa_id() + ",");
//		System.out.print(depVO3.getDep_money() + ",");
//		System.out.print(depVO3.getDep_day() + ",");
//		System.out.print(depVO3.getDep_sta());
//		
//		System.out.println("---------------------");

		// �d��
//		List<DepVO> list = dao.getAll();
//		for (DepVO aDep : list) {
//			System.out.print(aDep.getDep_id() + ",");
//			System.out.print(aDep.getMem_id() + ",");
//			System.out.print(aDep.getCoa_id() + ",");
//			System.out.print(aDep.getDep_money() + ",");
//			System.out.print(aDep.getDep_day() + ",");
//			System.out.print(aDep.getDep_sta());
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
