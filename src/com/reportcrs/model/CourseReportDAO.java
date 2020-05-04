package com.reportcrs.model;

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


public class CourseReportDAO implements CourseReportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COURSE_REPORT(CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS)"
			+ "VALUES ('CR'||LPAD(to_char(crs_seq.NEXTVAL), 5, '0'), ?, ?, 0 )";

	private static final String GET_ONE_STMT = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS FROM COURSE_REPORT WHERE CRS_REPID = ?";

	private static final String DELETE = "DELETE FROM COURSE_REPORT WHERE CRS_REPID = ?";

	private static final String UPDATE = "UPDATE COURSE_REPORT SET  REP_STATUS=?"
			+ "WHERE CRS_REPID=?";

	private static final String GET_ALL_STMT = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS "
			+ "FROM COURSE_REPORT ORDER BY CRS_REPID";
	private static final String GET_ONE_STMT1 = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS FROM COURSE_REPORT WHERE CRS_NO = ?";
	@Override
	public void insert(CourseReportVO coursereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, coursereportVO.getCrs_no());
			pstmt.setString(2, coursereportVO.getRep_content());
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
	public void update(CourseReportVO coursereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

	
			pstmt.setString(1, coursereportVO.getRep_status());
			pstmt.setString(2, coursereportVO.getCrs_repid());

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
	public void delete(String crs_repid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crs_repid);

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
	public CourseReportVO findByForeignKey(String crs_no) {
		CourseReportVO CourseReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT1);

			pstmt.setString(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseReportVO = new CourseReportVO();

				CourseReportVO.setCrs_repid(rs.getString("crs_repid"));
				CourseReportVO.setCrs_no(rs.getString("crs_no"));
				CourseReportVO.setRep_content(rs.getString("rep_content"));
				CourseReportVO.setRep_status(rs.getString("rep_status"));
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
		return CourseReportVO;

	}
	
	
	@Override
	public CourseReportVO findByPrimaryKey(String crs_repid) {
		CourseReportVO CourseReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_repid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseReportVO = new CourseReportVO();

				CourseReportVO.setCrs_repid(rs.getString("crs_repid"));
				CourseReportVO.setCrs_no(rs.getString("crs_no"));
				CourseReportVO.setRep_content(rs.getString("rep_content"));
				CourseReportVO.setRep_status(rs.getString("rep_status"));
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
		return CourseReportVO;

	}

	@Override
	public List<CourseReportVO> getAll() {
		List<CourseReportVO> list = new ArrayList<CourseReportVO>();
		CourseReportVO CourseReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseReportVO = new CourseReportVO();

				CourseReportVO.setCrs_repid(rs.getString("crs_repid"));
				CourseReportVO.setCrs_no(rs.getString("crs_no"));
				CourseReportVO.setRep_content(rs.getString("rep_content"));
				CourseReportVO.setRep_status(rs.getString("rep_status"));
				list.add(CourseReportVO); // Store the row in the list
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
