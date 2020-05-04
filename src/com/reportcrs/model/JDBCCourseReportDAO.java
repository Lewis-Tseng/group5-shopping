package com.reportcrs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crs.model.CrsVO;
import com.crs.model.JDBCCrsDAO;

public class JDBCCourseReportDAO implements CourseReportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO COURSE_REPORT(CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS)"
			+ "VALUES ('CR'||LPAD(to_char(crs_seq.NEXTVAL), 5, '0'), ?, ?, 0 )";

	private static final String GET_ONE_STMT = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS FROM COURSE_REPORT WHERE CRS_REPID = ?";
	private static final String GET_ONE_STMT1 = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS FROM COURSE_REPORT WHERE CRS_NO = ?";
	private static final String DELETE = "DELETE FROM COURSE_REPORT WHERE CRS_REPID = ?";

	private static final String UPDATE = "UPDATE COURSE_REPORT SET  REP_STATUS=?"
			+ "WHERE CRS_REPID=?";

	private static final String GET_ALL_STMT = "SELECT CRS_REPID, CRS_NO, REP_CONTENT, REP_STATUS "
			+ "FROM COURSE_REPORT ORDER BY CRS_REPID";

	@Override
	public void insert(CourseReportVO coursereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, coursereportVO.getCrs_no());
			pstmt.setString(2, coursereportVO.getRep_content());
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
	public void update(CourseReportVO coursereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, coursereportVO.getRep_status());
			pstmt.setString(2, coursereportVO.getCrs_repid());
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
	public void delete(String crs_repid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crs_repid);

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
	public CourseReportVO findByPrimaryKey(String crs_repid) {

		CourseReportVO CourseReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return CourseReportVO;
	}
	@Override
	public CourseReportVO findByForeignKey(String crs_no) {

		CourseReportVO CourseReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return CourseReportVO;
	}
	public List<CourseReportVO> getAll() {
		List<CourseReportVO> list = new ArrayList<CourseReportVO>();
		CourseReportVO CourseReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		JDBCCourseReportDAO dao = new JDBCCourseReportDAO();

		CourseReportVO courseReportVO = new CourseReportVO();

		// 新增
//		courseReportVO.setCrs_no("CN00001");
//		courseReportVO.setRep_content("教練性騷擾");
//		courseReportVO.setRep_status("0");
//		dao.insert(courseReportVO);

		// 修改
//		courseReportVO.setCrs_repid("CR00006");
//		courseReportVO.setCrs_no("CN00003");
//		courseReportVO.setRep_content("教練性騷擾修改版");
//		courseReportVO.setRep_status("1");
//		dao.update(courseReportVO);

		// 刪除

//		dao.delete("CR00006");

		// 查詢

//		CourseReportVO courseReportVO1= dao.findByPrimaryKey("CR00006");
//		
//		System.out.println(courseReportVO1.getCrs_repid());
//		System.out.println(courseReportVO1.getCrs_no());
//		System.out.println(courseReportVO1.getRep_content());
//		System.out.println(courseReportVO1.getRep_status());

		// 查全部

//		List<CourseReportVO> list = dao.getAll();
//		for (CourseReportVO aCourseReport : list) {
//			System.out.print(aCourseReport.getCrs_repid() + ",");
//			System.out.print(aCourseReport.getCrs_no() + ",");
//			System.out.print(aCourseReport.getRep_content() + ",");
//			System.out.print(aCourseReport.getRep_status() );
//
//			System.out.println();
		}
	}

