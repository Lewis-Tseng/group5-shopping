package com.crs_time.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.crs.model.CrsVO;

public class CrsTimeDAO implements CrsTimeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CRS_TIME(TIME_ID, CRS_NO, START_TIME, END_TIME, CRS_DATE)"
			+ "VALUES(CRS_TIME_SEQ.NEXTVAL, ?, ?, ?, ?)";

	private static final String DELETE = "DELETE FROM CRS_TIME where time_id = ?";

	private static final String GET_ONE_STMT = "SELECT TIME_ID, CRS_NO, START_TIME, END_TIME, CRS_DATE FROM CRS_TIME WHERE TIME_ID = ?";

	private static final String UPDATE = "UPDATE CRS_TIME SET CRS_NO=?, START_TIME=?, END_TIME=?, CRS_DATE=?"
			+ "where time_id=?";

	private static final String GET_ALL_STMT = "SELECT  TIME_ID, CRS_NO, START_TIME, END_TIME, CRS_DATE "
			+ "FROM CRS_TIME ORDER BY TIME_ID";

	@Override
	public void insert(CrsTimeVO crstimeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crstimeVO.getCrs_no());
			pstmt.setTimestamp(2, crstimeVO.getStart_time());
			pstmt.setTimestamp(3, crstimeVO.getEnd_time());
			pstmt.setDate(4, crstimeVO.getCrs_date());

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
	public void update(CrsTimeVO crstimeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crstimeVO.getCrs_no());
			pstmt.setTimestamp(2, crstimeVO.getStart_time());
			pstmt.setTimestamp(3, crstimeVO.getEnd_time());
			pstmt.setDate(4, crstimeVO.getCrs_date());
			pstmt.setString(5, crstimeVO.getTime_id());

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
	public void delete(String time_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, time_id);

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
	public CrsTimeVO findByPrimaryKey(String time_id) {
		CrsTimeVO CrsTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, time_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsTimeVO = new CrsTimeVO();
				CrsTimeVO.setTime_id(rs.getString("time_id"));
				CrsTimeVO.setCrs_no(rs.getString("crs_no"));
				CrsTimeVO.setStart_time(rs.getTimestamp("start_time"));
				CrsTimeVO.setEnd_time(rs.getTimestamp("end_time"));
				CrsTimeVO.setCrs_date(rs.getDate("crs_date"));

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
		return CrsTimeVO;

	}

	@Override
	public List<CrsTimeVO> getAll() {
		List<CrsTimeVO> list = new ArrayList<CrsTimeVO>();
		CrsTimeVO CrsTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsTimeVO = new CrsTimeVO();
				CrsTimeVO.setTime_id(rs.getString("time_id"));
				CrsTimeVO.setCrs_no(rs.getString("crs_no"));
				CrsTimeVO.setStart_time(rs.getTimestamp("start_time"));
				CrsTimeVO.setEnd_time(rs.getTimestamp("end_time"));
				CrsTimeVO.setCrs_date(rs.getDate("crs_date"));
				list.add(CrsTimeVO); // Store the row in the list
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
