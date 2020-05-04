package com.coach.model;


import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coach.model.CoaVO;
import com.mem.model.MemVO;
import com.coach.model.CoaDAO_interface;

public class CoaDAO implements CoaDAO_interface{
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
			"INSERT INTO COACH(coa_id,coa_name,coa_gender,coa_email,coa_psw,expert,license,coa_pic,coa_sta,coa_intro,coa_video,coa_point) VALUES(('CO'||LPAD(to_char(COACH_seq.NEXTVAL),5,'0')), ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT coa_id,coa_name,coa_gender,coa_email,coa_psw,expert,license,coa_pic,coa_sta,coa_intro,coa_video,coa_point FROM COACH order by coa_id";
		private static final String GET_ONE_STMT = 
			"SELECT coa_id,coa_name,coa_gender,coa_email,coa_psw,expert,license,coa_pic,coa_sta,coa_intro,coa_video,coa_point FROM COACH where coa_id = ?";
		private static final String DELETE = 
			"DELETE FROM COACH where coa_id = ?";
		private static final String UPDATE = 
			"UPDATE COACH set coa_name=?, coa_gender=?, coa_email=?, coa_psw=?, expert=?, license=?, coa_pic=?, coa_sta=?,coa_intro=?,coa_video=?,coa_point=? where coa_id = ?";
		private static final String UPDATE_CoaPer =
				"UPDATE COACH set coa_name=?, expert=?, license=?, coa_pic=?,coa_intro=?,coa_video=? where coa_email = ?";
		private static final String UPDATE_CoaPer_NoChangePIC = 
				"UPDATE COACH set coa_name=?, expert=?,coa_intro=?,coa_video=? where mem_email = ?";
		private static final String GET_ONE_PSW_COA = 
				"SELECT coa_psw FROM COACH where coa_email = ?";
		private static final String GET_ONE_STMT_BY_EMAIL_COA = 
				"SELECT coa_id,coa_name,coa_gender,coa_email,coa_psw,expert,license,coa_pic,coa_sta,coa_intro,coa_video,coa_point FROM COACH where coa_email = ?";
		private static final String UPDATE_COAPOINT = 
				"UPDATE COACH set coa_point=? where coa_id = ?";
		private static final String INSERT_STMT2 = 
				"INSERT INTO COACH(coa_id,coa_name,coa_gender,coa_email,coa_psw,license,coa_sta) VALUES(('CO'||LPAD(to_char(COACH_seq.NEXTVAL),5,'0')), ?, ?, ?, ?, ?, ?)";
		private static final String GET_COA_POINT = "SELECT coa_point FROM COACH where coa_id = ?";


	@Override
	public void insert(CoaVO coaVO) {
		// TODO Auto-generated method stub
		
		

			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, coaVO.getCoa_name());
				pstmt.setString(2, coaVO.getCoa_gender());
				pstmt.setString(3, coaVO.getCoa_email());
				pstmt.setString(4, coaVO.getCoa_psw());
				pstmt.setString(5, coaVO.getExpert());
				pstmt.setBytes(6, coaVO.getLicense());
				pstmt.setBytes(7, coaVO.getCoa_pic());
				pstmt.setString(8, coaVO.getCoa_sta());
				pstmt.setString(9, coaVO.getCoa_intro());
				pstmt.setString(10, coaVO.getCoa_video());
				pstmt.setInt(11, coaVO.getCoa_point());
				
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
	public void insert2(CoaVO coaVO) {
		// TODO Auto-generated method stub
		
		

			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT2);

				pstmt.setString(1, coaVO.getCoa_name());
				pstmt.setString(2, coaVO.getCoa_gender());
				pstmt.setString(3, coaVO.getCoa_email());
				pstmt.setString(4, coaVO.getCoa_psw());
				pstmt.setBytes(5, coaVO.getLicense());
				pstmt.setString(6, coaVO.getCoa_sta());

				
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
	public void update(CoaVO coaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, coaVO.getCoa_name());
			pstmt.setString(2, coaVO.getCoa_gender());
			pstmt.setString(3, coaVO.getCoa_email());
			pstmt.setString(4, coaVO.getCoa_psw());
			pstmt.setString(5, coaVO.getExpert());
			pstmt.setBytes(6, coaVO.getLicense());
			pstmt.setBytes(7, coaVO.getCoa_pic());
			pstmt.setString(8, coaVO.getCoa_sta());
			pstmt.setString(9, coaVO.getCoa_intro());
			pstmt.setString(10, coaVO.getCoa_video());
			pstmt.setInt(11, coaVO.getCoa_point());
			pstmt.setString(12, coaVO.getCoa_id());
		
			
			
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
	public void delete(String coa_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coa_id);

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
	public CoaVO findByPrimaryKey(String coa_id) {
		// TODO Auto-generated method stub
		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coa_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				coaVO = new CoaVO();
				coaVO.setCoa_id(rs.getString("coa_id"));
				coaVO.setCoa_name(rs.getString("coa_name"));
				coaVO.setCoa_gender(rs.getString("coa_gender"));
				coaVO.setCoa_email(rs.getString("coa_email"));
				coaVO.setCoa_psw(rs.getString("coa_psw"));
				coaVO.setExpert(rs.getString("expert"));
				coaVO.setLicense(rs.getBytes("license"));
				coaVO.setCoa_pic(rs.getBytes("coa_pic"));
				coaVO.setCoa_sta(rs.getString("coa_sta"));
				coaVO.setCoa_intro(rs.getString("coa_intro"));
				coaVO.setCoa_video(rs.getString("coa_video"));
				coaVO.setCoa_point(rs.getInt("coa_point"));
				
				
				
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
		return coaVO;
	}

	@Override
	public List<CoaVO> getAll() {
		List<CoaVO> list = new ArrayList<CoaVO>();
		CoaVO coaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				coaVO = new CoaVO();
				coaVO.setCoa_id(rs.getString("coa_id"));
				coaVO.setCoa_name(rs.getString("coa_name"));
				coaVO.setCoa_gender(rs.getString("coa_gender"));
				coaVO.setCoa_email(rs.getString("coa_email"));
				coaVO.setCoa_psw(rs.getString("coa_psw"));
				coaVO.setExpert(rs.getString("expert"));
				coaVO.setLicense(null);
				coaVO.setCoa_pic(null);
				coaVO.setCoa_sta(rs.getString("coa_sta"));
				coaVO.setCoa_intro(rs.getString("coa_intro"));
				coaVO.setCoa_video(rs.getString("coa_video"));
				coaVO.setCoa_point(rs.getInt("coa_point"));
				
				list.add(coaVO); // Store the row in the list
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
	public void update_CoaPer(CoaVO coaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			if(coaVO.getCoa_pic().length == 0 && coaVO.getLicense().length == 0) {
				pstmt = con.prepareStatement(UPDATE_CoaPer_NoChangePIC);
				pstmt.setString(1, coaVO.getCoa_name());
				pstmt.setString(2, coaVO.getExpert());
				pstmt.setString(3, coaVO.getCoa_intro());
				pstmt.setString(4, coaVO.getCoa_video());
				
							
				pstmt.setString(5, coaVO.getCoa_email());
			
			}else {
				
				pstmt = con.prepareStatement(UPDATE_CoaPer);

				pstmt.setString(1, coaVO.getCoa_name());
				pstmt.setString(2, coaVO.getExpert());
				pstmt.setBytes(3, coaVO.getLicense());
				pstmt.setBytes(4, coaVO.getCoa_pic());
				pstmt.setString(5, coaVO.getCoa_intro());
				pstmt.setString(6, coaVO.getCoa_video());
			
				pstmt.setString(7, coaVO.getCoa_email());
				
			}
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
	public String findCoachByAccount(String coa_email) {
		// TODO Auto-generated method stub
		String coa_psw = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PSW_COA);

			pstmt.setString(1, coa_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				
		
				coa_psw	=rs.getString("coa_psw");
				
				
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
		return coa_psw;
	}
	@Override
	public CoaVO findCoachByAccount2(String coa_email) {
		// TODO Auto-generated method stub
		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_EMAIL_COA);

			pstmt.setString(1, coa_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				coaVO = new CoaVO();
				coaVO.setCoa_id(rs.getString("coa_id"));
				coaVO.setCoa_name(rs.getString("coa_name"));
				coaVO.setCoa_gender(rs.getString("coa_gender"));
				coaVO.setCoa_email(rs.getString("coa_email"));
				coaVO.setCoa_psw(rs.getString("coa_psw"));
				coaVO.setExpert(rs.getString("expert"));
				coaVO.setLicense(rs.getBytes("license"));
				coaVO.setCoa_pic(rs.getBytes("coa_pic"));
				coaVO.setCoa_sta(rs.getString("coa_sta"));
				coaVO.setCoa_intro(rs.getString("coa_intro"));
				coaVO.setCoa_video(rs.getString("coa_video"));
				coaVO.setCoa_point(rs.getInt("coa_point"));			
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
		return coaVO;
	}
	@Override
	public void updateCoaPoint(String coa_id, Integer coa_point) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COAPOINT);

			pstmt.setInt(1, coa_point);
			pstmt.setString(2, coa_id);
		
			
			
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
	public CoaVO findCoaPoint(String coa_id) {
		// TODO Auto-generated method stub
				CoaVO coaVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_COA_POINT);

					pstmt.setString(1, coa_id);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						coaVO = new CoaVO();
						coaVO.setCoa_point(rs.getInt("coa_point"));
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
				return coaVO;
		
	}

	
	


}
