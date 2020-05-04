package com.administrator.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.administrator.model.AdmVO;

import jdbc.util.CompositeQueryEmployee.jdbcUtil_CompositeQuery_Emp2;

import com.administrator.model.AdmDAO_interface;

public class AdmDAO implements AdmDAO_interface{
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
			"INSERT INTO ADMINISTRATOR(emp_id,emp_name,emp_psw,emp_email,emp_pic) VALUES(('EM'||LPAD(to_char(ADMINISTRATOR_seq.NEXTVAL),5,'0')), ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR order by emp_id";
		private static final String GET_ONE_STMT = 
			"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR where emp_id = ?";
		private static final String DELETE = 
			"DELETE FROM ADMINISTRATOR where emp_id = ?";
		private static final String UPDATE = 
			"UPDATE ADMINISTRATOR set emp_name=?, emp_psw=? where emp_id = ?";
		private static final String INSERT_STMT1 = 
				"INSERT INTO ADMINISTRATOR(emp_id,emp_name,emp_psw) VALUES(('EM'||LPAD(to_char(ADMINISTRATOR_seq.NEXTVAL),5,'0')), ?)";
		private static final String GET_ONE_STMT_NAME=
				"SELECT emp_id,emp_name,emp_psw FROM ADMINISTRATOR where emp_name = ?";

		@Override
		public AdmVO findByInsertName(String emp_name) {
		
			AdmVO admVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_NAME);

				pstmt.setString(1, emp_name);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					admVO = new AdmVO();
					admVO.setEmp_id(rs.getString("emp_id"));
					admVO.setEmp_name(rs.getString("emp_name"));
					admVO.setEmp_psw(rs.getString("emp_psw"));
					
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
			return admVO;
		}

		
		@Override
		public void insert1(AdmVO admVO) {
			// TODO Auto-generated method stub
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT1);

					pstmt.setString(1, admVO.getEmp_name());
				
					
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
	public void insert(AdmVO admVO) {
		// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, admVO.getEmp_name());
				pstmt.setString(2, admVO.getEmp_psw());
				pstmt.setString(3, admVO.getEmp_email());
				pstmt.setBytes(4, admVO.getEmp_pic());
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
	public void update(AdmVO admVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getEmp_name());
			pstmt.setString(2, admVO.getEmp_psw());
			pstmt.setString(3, admVO.getEmp_id());
			
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
	public void delete(String emp_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id);

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
	public AdmVO findByPrimaryKey(String emp_id) {
		// TODO Auto-generated method stub
		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
				
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
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
				
				list.add(admVO); // Store the row in the list
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
	public List<AdmVO> getAll(Map<String, String[]> map) {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from administrator "
		          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
		          + "order by emp_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				admVO = new AdmVO();
				admVO.setEmp_id(rs.getString("emp_id"));
				admVO.setEmp_name(rs.getString("emp_name"));
				admVO.setEmp_psw(rs.getString("emp_psw"));
				
				list.add(admVO); // Store the row in the List
			}
	
			// Handle any SQL errors
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