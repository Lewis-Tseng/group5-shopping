package com.deposit.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

import com.deposit.model.DepVO;
import com.deposit.model.DepDAO_interface;

public class DepDAO implements DepDAO_interface{
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
		// TODO Auto-generated method stub
		
		

			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, depVO.getMem_id());
				pstmt.setString(2, depVO.getCoa_id());
				pstmt.setInt(3, depVO.getDep_money());
				pstmt.setTimestamp(4, depVO.getDep_day());
				pstmt.setString(5, depVO.getDep_sta());
				
				
				pstmt.executeUpdate();

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
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, depVO.getMem_id());
			pstmt.setString(2, depVO.getCoa_id());
			pstmt.setInt(3, depVO.getDep_money());
			pstmt.setTimestamp(4, depVO.getDep_day());
			pstmt.setString(5, depVO.getDep_sta());
			pstmt.setString(6, depVO.getDep_id());
		
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dep_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
		// TODO Auto-generated method stub
		DepVO depVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				depVO = new DepVO();
				depVO.setDep_id(rs.getString("dep_id"));
				depVO.setMem_id(rs.getString("mem_id"));
				depVO.setCoa_id(rs.getString("coa_id"));
				depVO.setDep_money(rs.getInt("dep_money"));
				depVO.setDep_day(rs.getTimestamp("dep_day"));
				depVO.setDep_sta(rs.getString("dep_sta"));
				
				
				
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STA);

			pstmt.setString(1, depVO.getDep_sta());

			pstmt.setString(2, depVO.getDep_id());
		
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
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




}
