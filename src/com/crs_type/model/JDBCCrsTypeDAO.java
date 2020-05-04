package com.crs_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crs.model.CrsVO;
import com.crs.model.JDBCCrsDAO;

public class JDBCCrsTypeDAO implements CrsTypeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CRS_TYPE(CRS_TYPE_NO, CRS_TYPE_NAME)"
			+ "VALUES('CT'||LPAD(to_char(CRS_TYPE_NO_SEQ.NEXTVAL), 5, '0'), ?)";

	private static final String UPDATE = "UPDATE CRS_TYPE SET CRS_TYPE_NAME=? where crs_type_no = ?";

	private static final String DELETE = "DELETE FROM CRS_TYPE where crs_type_no = ?";

	private static final String GET_ONE_STMT = "SELECT CRS_TYPE_NO, CRS_TYPE_NAME FROM CRS_TYPE where crs_type_no=?  ";

	private static final String GET_ALL_STMT = "SELECT CRS_TYPE_NO, CRS_TYPE_NAME "
			+ "FROM CRS_TYPE order by crs_type_no";

	@Override
	public void insert(CrsTypeVO crstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crstypeVO.getCrs_type_name());

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
	public void update(CrsTypeVO crstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crstypeVO.getCrs_type_name());
			pstmt.setString(2, crstypeVO.getCrs_type_no());

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
	public void delete(String crs_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crs_type_no);

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
	public CrsTypeVO findByPrimaryKey(String crs_type_no) {
		CrsTypeVO crsTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				crsTypeVO = new CrsTypeVO();

				crsTypeVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsTypeVO.setCrs_type_name(rs.getString("crs_type_name"));

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
		return crsTypeVO;
	}

	@Override
	public List<CrsTypeVO> getAll() {
		List<CrsTypeVO> list = new ArrayList<CrsTypeVO>();
		CrsTypeVO crsTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				crsTypeVO = new CrsTypeVO();
				crsTypeVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsTypeVO.setCrs_type_name(rs.getString("crs_type_name"));
				list.add(crsTypeVO); // Store the row in the list
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

	public static void main(String args[]) {
		JDBCCrsTypeDAO dao = new JDBCCrsTypeDAO();

		CrsTypeVO crsTypeVO = new CrsTypeVO();

		// 新增
//		crsTypeVO.setCrs_type_name("燃脂類");
//		dao.insert(crsTypeVO);

		// 修改
//		crsTypeVO.setCrs_type_no("CT00001");
//		crsTypeVO.setCrs_type_name("游泳類");
//		dao.update(crsTypeVO);

		// 刪除
//		dao.delete("CT00008");

		// 查詢
//		CrsTypeVO crsTypeVO1 = dao.findByPrimaryKey("CT00001");
//		System.out.println( crsTypeVO1.getCrs_type_no());
//		System.out.println( crsTypeVO1.getCrs_type_name());
		// 查詢全部
		List<CrsTypeVO> list = dao.getAll();
		for (CrsTypeVO aCrsType : list) {
			System.out.println(aCrsType.getCrs_type_no());
			System.out.println(aCrsType.getCrs_type_name());
			System.out.println();
		}
	}

	@Override
	public Set<CrsVO> getCrsByCrsTypeNo(String crs_type_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
