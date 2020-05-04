package com.crs_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.crs.model.CrsVO;
import com.res.model.ResVO;

public class CrsDetailDAO implements CrsDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CRS_DETAIL (CRS_NO, MEM_ID, CRS_NAME, CRS_FEE)"
			+ " VALUES( ?, ?, ?, ?)";

	private static final String DELETE = "DELETE FROM CRS_DETAIL where crs_no = ?";

	private static final String GET_ONE_STMT = "SELECT CRS_NO,  MEM_ID, CRS_NAME, CRS_FEE FROM Crs_Detail where crs_no = ?";

	private static final String GET_RESERVERD = "SELECT CRS_NO FROM Crs_Detail where mem_id = ?";

	private static final String UPDATE = "UPDATE Crs_Detail SET MEM_ID=?, CRS_NAME=?, CRS_FEE=? where crs_no =?";

	private static final String GET_ALL_STMT = "SELECT CRS_NO,  MEM_ID, CRS_NAME, CRS_FEE FROM CRS_DETAIL order by crs_no , mem_id ";
	private static final String GET_ONE_STMT2 = "SELECT CRS_NO,  MEM_ID, CRS_NAME, CRS_FEE FROM Crs_Detail where MEM_ID = ?";
	
	@Override
	public List<CrsDetailVO> findByCrsNOAll( String mem_id) {
		List<CrsDetailVO> list = new LinkedList<CrsDetailVO>();
		CrsDetailVO CrsDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsDetailVO = new CrsDetailVO();

				CrsDetailVO.setCrs_no(rs.getString("crs_no"));
				CrsDetailVO.setMem_id(rs.getString("mem_id"));
				CrsDetailVO.setCrs_name(rs.getString("crs_name"));
				CrsDetailVO.setCrs_fee(rs.getInt("crs_fee"));
				list.add(CrsDetailVO);
			}
			// Handle any driver errors
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
	public void insert(CrsDetailVO crsdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crsdetailVO.getCrs_no());
			pstmt.setString(2, crsdetailVO.getMem_id());
			pstmt.setString(3, crsdetailVO.getCrs_name());
			pstmt.setInt(4, crsdetailVO.getCrs_fee());

			pstmt.executeUpdate();

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
	public void update(CrsDetailVO crsdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crsdetailVO.getMem_id());
			pstmt.setString(2, crsdetailVO.getCrs_name());
			pstmt.setInt(3, crsdetailVO.getCrs_fee());
			pstmt.setString(4, crsdetailVO.getCrs_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String crs_no, String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crs_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public CrsDetailVO findByPrimaryKey(String crs_no, String mem_id) {
		CrsDetailVO CrsDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsDetailVO = new CrsDetailVO();

				CrsDetailVO.setCrs_no(rs.getString("crs_no"));
				CrsDetailVO.setMem_id(rs.getString("mem_id"));
				CrsDetailVO.setCrs_name(rs.getString("crs_name"));
				CrsDetailVO.setCrs_fee(rs.getInt("crs_fee"));

			}
			// Handle any driver errors
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
		return CrsDetailVO;
	}

	public CrsDetailVO findReserved(String mem_id) {
		CrsDetailVO CrsDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RESERVERD);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsDetailVO = new CrsDetailVO();

				CrsDetailVO.setCrs_no(rs.getString("crs_no"));

			}
			// Handle any driver errors
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
		return CrsDetailVO;
	}

	@Override
	public List<CrsDetailVO> getAll() {
		List<CrsDetailVO> list = new ArrayList<CrsDetailVO>();
		CrsDetailVO CrsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsDetailVO = new CrsDetailVO();

				CrsDetailVO.setCrs_no(rs.getString("crs_no"));
				CrsDetailVO.setMem_id(rs.getString("mem_id"));
				CrsDetailVO.setCrs_name(rs.getString("crs_name"));
				CrsDetailVO.setCrs_fee(rs.getInt("crs_fee"));
				list.add(CrsDetailVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<CrsDetailVO> getReserved(String mem_id) {
		List<CrsDetailVO> list = new ArrayList<CrsDetailVO>();
		CrsDetailVO CrsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RESERVERD);
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsDetailVO = new CrsDetailVO();

				CrsDetailVO.setCrs_no(rs.getString("crs_no"));
				list.add(CrsDetailVO); // Store the row in the list
			}

			// Handle any driver errors
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
}
