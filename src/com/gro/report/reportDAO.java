package com.gro.report;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class reportDAO implements reportDAO_interface {


	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO GRO_REPORT (GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT) VALUES ('GP'||LPAD(to_char(GRO_REPORT_SEQ.NEXTVAL), 5, '0'), ?, ?, 0)";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT ORDER BY GRO_REPID";
	private static final String GET_ONE_STMT = 
		"SELECT GRO_REPID, GRO_ID, GRO_REP_INFO, GRO_REP_STAT FROM GRO_REPORT WHERE GRO_REPID = ?";
	private static final String DELETE = 
		"DELETE FROM GRO_REPORT WHERE GRO_REPID = ?";
	private static final String UPDATE = 
		"UPDATE GRO_REPORT SET GRO_REP_STAT=? WHERE GRO_REPID = ?";

	@Override
	public void insert(reportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reportVO.getGro_id());
			pstmt.setString(2, reportVO.getGro_rep_info());

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void update(reportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		Integer changeStat=null;
		
		if(reportVO.getGro_rep_stat()==0) {
			changeStat=1;
		}else {
			changeStat=0;
		};
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, changeStat);
			pstmt.setString(2, reportVO.getGro_repid());

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void delete(String gro_repid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gro_repid);

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public reportVO findByPrimaryKey(String gro_repid) {

		reportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_repid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportVO = new reportVO();
				reportVO.setGro_repid(rs.getString("GRO_REPID"));
				reportVO.setGro_id(rs.getString("GRO_ID"));
				reportVO.setGro_rep_info(rs.getString("GRO_REP_INFO"));
				reportVO.setGro_rep_stat(rs.getInt("GRO_REP_STAT"));
			}


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
		return reportVO;
	}

	@Override
	public List<reportVO> getAll() {
		List<reportVO> list = new ArrayList<reportVO>();
		reportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportVO = new reportVO();
				reportVO.setGro_repid(rs.getString("GRO_REPID"));
				reportVO.setGro_id(rs.getString("GRO_ID"));
				reportVO.setGro_rep_info(rs.getString("GRO_REP_INFO"));
				reportVO.setGro_rep_stat(rs.getInt("GRO_REP_STAT"));
				list.add(reportVO);
			}


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
}