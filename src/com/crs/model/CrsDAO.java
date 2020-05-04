package com.crs.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CrsDAO implements CrsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CRS (CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE,"
			+ " CRS_CONTENT, START_DATE,END_DATE, WEEK_CRS, START_TIME, END_TIME, LIMIT, CRS_IMG) "
			+ "VALUES ('CN'||LPAD(to_char(crs_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String DELETE_CRS = "DELETE FROM CRS where crs_no = ?";

	private static final String GET_ONE_STMT = "SELECT CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE, CRS_CONTENT,"
			+ " to_char(START_DATE, 'yyyy-mm-dd') START_DATE , to_char(END_DATE, 'yyyy-mm-dd') END_DATE, "
			+ " WEEK_CRS,  to_char(START_TIME, 'yyyy-mm-dd hh:mi:ss') START_TIME, "
			+ "to_char(END_TIME, 'yyyy-mm-dd hh:mi:ss') END_TIME,"
			+ " STAR, STAR_TOTAL, LIMIT, RESERVED, CRS_IMG FROM CRS where crs_no = ?";

	private static final String UPDATE = "UPDATE CRS SET COA_ID=?, CRS_TYPE_NO=?, CRS_NAME =?, CRS_FEE=?, CRS_CONTENT=?, START_DATE=?, END_DATE=?, WEEK_CRS=? ,"
			+ "START_TIME=?, END_TIME=?, CRS_IMG=? where crs_no = ?";

	private static final String GET_ALL_STMT = "SELECT CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE, CRS_CONTENT,"
			+ " to_char(START_DATE, 'yyyy-mm-dd') START_DATE , to_char(END_DATE, 'yyyy-mm-dd') END_DATE, "
			+ " WEEK_CRS,  to_char(START_TIME, 'yyyy-mm-dd hh:mi:ss') START_TIME, "
			+ "to_char(END_TIME, 'yyyy-mm-dd hh:mi:ss') END_TIME,"
			+ " STAR, STAR_TOTAL, LIMIT,RESERVED, CRS_IMG FROM CRS order by crs_no";
	
	private static final String GET_ONE_STMT1 = "SELECT  CRS_NAME"+" FROM CRS where crs_name = ?";
	
	private static final String UPDATE_RESERVED ="UPDATE CRS SET RESERVED=? WHERE CRS_NO=?";
	
	private static final String FIND_RESERVED = "SELECT RESERVED FROM CRS WHERE CRS_NO=?";
	
	private static final String FIND_CRS ="SELECT CRS_NAME, START_DATE, END_DATE FROM CRS WHERE COA_ID=?";

	@Override
	public void insert(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crsVO.getCoa_id());
			pstmt.setString(2, crsVO.getCrs_type_no());
			pstmt.setString(3, crsVO.getCrs_name());
			pstmt.setDouble(4, crsVO.getCrs_fee());
			pstmt.setString(5, crsVO.getCrs_content());
			pstmt.setDate(6, crsVO.getStart_date());
			pstmt.setDate(7, crsVO.getEnd_date());
			pstmt.setString(8, crsVO.getWeek_crs());
			pstmt.setTimestamp(9, crsVO.getStart_time());
			pstmt.setTimestamp(10, crsVO.getEnd_time());
			pstmt.setInt(11, crsVO.getLimit());
			pstmt.setBytes(12, crsVO.getCrs_img());

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
	public void update(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crsVO.getCoa_id());
			pstmt.setString(2, crsVO.getCrs_type_no());
			pstmt.setString(3, crsVO.getCrs_name());
			pstmt.setDouble(4, crsVO.getCrs_fee());
			pstmt.setString(5, crsVO.getCrs_content());
			pstmt.setDate(6, crsVO.getStart_date());
			pstmt.setDate(7, crsVO.getEnd_date());
			pstmt.setString(8, crsVO.getWeek_crs());
			pstmt.setTimestamp(9, crsVO.getStart_time());
			pstmt.setTimestamp(10, crsVO.getEnd_time());
			pstmt.setBytes(11, crsVO.getCrs_img());
			pstmt.setString(12, crsVO.getCrs_no());

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
	public void delete(String crs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_CRS);
			
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
	public CrsVO findByPrimaryKey(String crs_no) {
		CrsVO CrsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsVO = new CrsVO();
				CrsVO.setCrs_no(rs.getString("crs_no"));
				CrsVO.setCoa_id(rs.getString("coa_id"));
				CrsVO.setCrs_type_no(rs.getString("crs_type_no"));
				CrsVO.setCrs_name(rs.getString("crs_name"));
				CrsVO.setCrs_fee(rs.getInt ("crs_fee"));
				CrsVO.setCrs_content(rs.getString("crs_content"));
				CrsVO.setStart_date(rs.getDate("start_date"));
				CrsVO.setEnd_date(rs.getDate("end_date"));
				CrsVO.setWeek_crs(rs.getString("week_crs"));
				CrsVO.setStart_time(rs.getTimestamp("start_time"));
				CrsVO.setEnd_time(rs.getTimestamp("end_time"));
				CrsVO.setStar(rs.getDouble("star"));
				CrsVO.setStar(rs.getDouble("star_total"));
				CrsVO.setLimit(rs.getInt("limit"));
				CrsVO.setReserved(rs.getInt("reserved"));
				CrsVO.setCrs_img(rs.getBytes("crs_img"));
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
		return CrsVO;

	}

	@Override
	public List<CrsVO> getAll() {
		List<CrsVO> list = new ArrayList<CrsVO>();
		CrsVO crsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				crsVO = new CrsVO();
				crsVO.setCrs_no(rs.getString("crs_no"));
				crsVO.setCoa_id(rs.getString("coa_id"));
				crsVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsVO.setCrs_name(rs.getString("crs_name"));
				crsVO.setCrs_fee(rs.getInt ("crs_fee"));
				crsVO.setCrs_content(rs.getString("crs_content"));
				crsVO.setStart_date(rs.getDate("start_date"));
				crsVO.setEnd_date(rs.getDate("end_date"));
				crsVO.setWeek_crs(rs.getString("week_crs"));
				crsVO.setStart_time(rs.getTimestamp("start_time"));
				crsVO.setEnd_time(rs.getTimestamp("end_time"));
				crsVO.setStar(rs.getDouble("star"));
				crsVO.setStar(rs.getDouble("star_total"));
				crsVO.setLimit(rs.getInt("limit"));
				crsVO.setReserved(rs.getInt("reserved"));
				crsVO.setCrs_img(rs.getBytes("crs_img"));
				list.add(crsVO); // Store the row in the list
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
	public static String weekCrsTransfer(String week_crs) {
		String weekCrs = week_crs;
		String date[] = new String[7];
		int index = 0;
		int j = 1;

		String anwser = "";
		String finalAnwser = null;

		for (int i = 0; i < 7; i++) {
			date[index] = weekCrs.substring(i, j);

			index++;
			j++;
		}

		if ("1".equals(date[0])) {
			anwser += "一、";
		}
		if ("1".equals(date[1])) {
			anwser += "二、";
		}
		if ("1".equals(date[2])) {
			anwser += "三、";
		}
		if ("1".equals(date[3])) {
			anwser += "四、";
		}
		if ("1".equals(date[4])) {
			anwser += "五、";
		}
		if ("1".equals(date[5])) {
			anwser += "六、";
		}
		if ("1".equals(date[6])) {
			anwser += "日、";
		}
	
		finalAnwser = anwser.substring(0, anwser.length() -1);

		return finalAnwser;

	}

	@Override
	public void updateReserved(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RESERVED);

			pstmt.setInt(1, crsVO.getReserved());
			pstmt.setString(2, crsVO.getCrs_no());

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
	public CrsVO findReserved(String crs_no) {
		CrsVO CrsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_RESERVED);

			pstmt.setString(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				CrsVO = new CrsVO();
				CrsVO.setReserved(rs.getInt("reserved"));

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
		return CrsVO;
	}

	@Override
	public List<CrsVO> getAllCrs(String coa_id) {
		List<CrsVO> list = new ArrayList<CrsVO>();
		CrsVO crsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_CRS);
			pstmt.setString(1, coa_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				crsVO = new CrsVO();
				
				crsVO.setCrs_name(rs.getString("crs_name"));
				crsVO.setStart_date(rs.getDate("start_date"));
				crsVO.setEnd_date(rs.getDate("end_date"));
				
				list.add(crsVO); 
			}
		} catch (SQLException se) {
			
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
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
