package com.crs_type.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.crs.model.CrsVO;

public class CrsTypeDAO implements CrsTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CRS(CRS_TYPE_NO, CRS_TYPE_NAME)"
			+ "VALUES('CT'||LPAD(to_char(CRS_TYPE_NO_SEQ.NEXTVAL), 5, '0'), ?)";

	private static final String UPDATE = "UPDATE CRS_TYPE SET CRS_TYPE_NAME=? where crs_type_no";

	private static final String DELETE = "DELETE FROM CRS_TYPE where crs_type_no = ?";

	private static final String GET_ONE_STMT = "SELECT CRS_TYPE_NO, CRS_TYPE_NAME FROM CRS_TYPE where crs_type_no=?  ";

	private static final String GET_Crs_ByCrsTypeNo_STMT = "SELECT CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE, CRS_CONTENT,"
			+ "to_char(START_DATE, 'yyyy-mm-dd') START_DATE , to_char(END_DATE, 'yyyy-mm-dd') END_DATE,"
			+ "WEEK_CRS,  to_char(START_TIME, 'yyyy-mm-dd hh:mm:ss') START_TIME,"
			+ "to_char(END_TIME, 'yyyy-mm-dd hh:mm:ss') END_TIME,"
			+ "STAR, STAR_TOTAL, CRS_IMG FROM CRS where crs_type_no = ? order by crs_no";

	private static final String GET_ALL_STMT = "SELECT CRS_TYPE_NO, CRS_TYPE_NAME "
			+ "FROM CRS_TYPE order by crs_type_no";

	@Override
	public void insert(CrsTypeVO crstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crstypeVO.getCrs_type_name());

			pstmt.executeUpdate();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crstypeVO.getCrs_type_name());
			pstmt.setString(2, crstypeVO.getCrs_type_no());

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
	public void delete(String crs_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crs_type_no);

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
	public CrsTypeVO findByPrimaryKey(String crs_type_no) {
		CrsTypeVO crsTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				crsTypeVO = new CrsTypeVO();

				crsTypeVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsTypeVO.setCrs_type_name(rs.getString("crs_type_name"));

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

			con = ds.getConnection();
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
	public Set<CrsVO> getCrsByCrsTypeNo(String crs_type_no) {
		Set<CrsVO> set = new LinkedHashSet<CrsVO>();
		CrsVO crsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Crs_ByCrsTypeNo_STMT);
			pstmt.setString(1, crs_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				crsVO = new CrsVO();
				crsVO.setCrs_no(rs.getString("crs_no"));
				crsVO.setCoa_id(rs.getString("coa_id"));
				crsVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsVO.setCrs_name(rs.getString("crs_name"));
				crsVO.setCrs_fee(rs.getInt("crs_fee"));
				crsVO.setCrs_content(rs.getString("crs_content"));
				crsVO.setStart_date(rs.getDate("start_date"));
				crsVO.setEnd_date(rs.getDate("end_date"));
				crsVO.setWeek_crs(rs.getString("week_crs"));
				crsVO.setStart_time(rs.getTimestamp("start_time"));
				crsVO.setEnd_time(rs.getTimestamp("end_time"));
				crsVO.setStar(rs.getDouble("star"));
				crsVO.setStar(rs.getDouble("star_total"));
				crsVO.setCrs_img(rs.getBytes("crs_img"));
				set.add(crsVO); // Store the row in the vector
			}

			// Handle any SQL errors
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
		return set;
	}
}
