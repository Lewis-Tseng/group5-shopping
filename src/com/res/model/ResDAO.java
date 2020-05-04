package com.res.model;

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

public class ResDAO implements ResDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RES (RES_NO, MEM_ID, COA_ID, "
			+ "RES_DAY, RES_STATUS) VALUES('RN'||LPAD(to_char(RES_NO_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?)";

	private static final String DELETE = "DELETE FROM RES where res_no = ?";

	private static final String GET_ONE_STMT = "SELECT RES_NO, MEM_ID, COA_ID, "
			+ "to_char(RES_DAY, 'yyyy-mm-dd') RES_DAY, RES_STATUS FROM RES where res_no = ?";

	private static final String UPDATE = "UPDATE RES SET MEM_ID=?, COA_ID=?, RES_DAY=?, "
			+ "RES_STATUS=? where res_no =?";

	private static final String UPDATE_STATUS = "UPDATE RES SET RES_STATUS=? where res_no =?";

	private static final String GET_ALL_STMT = "SELECT  RES_NO, MEM_ID, COA_ID,"
			+ "to_char(RES_DAY, 'yyyy-mm-dd') RES_DAY, RES_STATUS FROM RES order by res_no";

	private static final String GET_RES_DAY = "select Res_NO, RES_DAY, MEM_ID , RES_STATUS from RES where coa_id=?";
	
	private static final String GET_RES_DAY_MEM="select RES_DAY,RES_STATUS from RES where mem_id=?";

	@Override
	public void insert(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resVO.getMem_id());
			pstmt.setString(2, resVO.getCoa_id());
			pstmt.setDate(3, resVO.getRes_day());
			pstmt.setString(4, resVO.getRes_status());
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resVO.getMem_id());
			pstmt.setString(2, resVO.getCoa_id());
			pstmt.setDate(3, resVO.getRes_day());
			pstmt.setString(4, resVO.getRes_status());
			pstmt.setString(5, resVO.getRes_no());

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
	public void updateStatus(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setString(1, resVO.getRes_status());
			pstmt.setString(2, resVO.getRes_no());
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
	public void delete(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

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
	public ResVO findByPrimaryKey(String res_no) {
		ResVO ResVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResVO = new ResVO();
				ResVO.setRes_no(rs.getString("res_no"));
				ResVO.setMem_id(rs.getString("mem_id"));
				ResVO.setCoa_id(rs.getString("coa_id"));
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
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
		return ResVO;
	}

	@Override
	public List<ResVO> getAll() {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO ResVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ResVO = new ResVO();
				ResVO.setRes_no(rs.getString("res_no"));
				ResVO.setMem_id(rs.getString("mem_id"));
				ResVO.setCoa_id(rs.getString("coa_id"));
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
				list.add(ResVO); // Store the row in the list
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
	public List<ResVO> getResDay(String coa_id) {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO ResVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RES_DAY);
			pstmt.setString(1, coa_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResVO = new ResVO();
				ResVO.setRes_no(rs.getString("res_no"));
				ResVO.setMem_id(rs.getString("mem_id"));
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
				list.add(ResVO);
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
	public List<ResVO> getResDayMem(String mem_id) {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO ResVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RES_DAY_MEM);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResVO = new ResVO();
				ResVO.setRes_day(rs.getDate("res_day"));
				ResVO.setRes_status(rs.getString("res_status"));
				list.add(ResVO);
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
