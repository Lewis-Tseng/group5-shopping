package com.reportcoa.model;

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

public class ReportCoaDAO implements ReportCoaDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO REPORT_COA (REP_ID,COA_ID,REP_COT,REP_STA )VALUES('RC' || LPAD(to_char(REPORT_MEM_seq.NEXTVAL), 5,'0'),?,?,0)";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT_COA";
	private static final String GET_ONE_STMT = "SELECT REP_ID,COA_ID,REP_COT,REP_STA FROM REPORT_COA WHERE REP_ID=?";
	private static final String DELETE = "DELETE FROM REPORT_COA WHERE REP_ID=?";
	private static final String UPDATE = "UPDATE REPORT_COA SET REP_STA=?  WHERE REP_ID=?";
	
	private static final String GET_ONE_STMT1 = "SELECT COA_ID,REP_COT,REP_STA FROM REPORT_COA WHERE COA_ID=?";
	@Override
	public void insert(ReportCoaVO reportCoaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		
			pstmt.setString(1, reportCoaVO.getCoa_id());
			pstmt.setString(2, reportCoaVO.getRep_cot());
		
			pstmt.executeUpdate();

			// Handle any driver errorst
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 

		finally {
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
	public void update(ReportCoaVO reportCoaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, reportCoaVO.getRep_sta());
			pstmt.setString(2, reportCoaVO.getRep_id());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		
		finally {
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
	public void delete(String rep_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,rep_id);
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
	public ReportCoaVO findForeignKey(String coa_id) {
		ReportCoaVO reportcoaVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT1);
			pstmt.setString(1, coa_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				reportcoaVO=new ReportCoaVO();
				
				reportcoaVO.setCoa_id(rs.getString("coa_id"));
				reportcoaVO.setRep_cot(rs.getString("rep_cot"));
				reportcoaVO.setRep_sta(rs.getString("rep_sta"));
			}
			
		}  catch (SQLException se) {
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
		return reportcoaVO;
	}
	
	@Override
	public ReportCoaVO findPrimaryKey(String rep_id) {
		ReportCoaVO reportcoaVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, rep_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				reportcoaVO=new ReportCoaVO();
				reportcoaVO.setRep_id(rs.getString("rep_id"));
				reportcoaVO.setCoa_id(rs.getString("coa_id"));
				reportcoaVO.setRep_cot(rs.getString("rep_cot"));
				reportcoaVO.setRep_sta(rs.getString("rep_sta"));
			}
			
		}  catch (SQLException se) {
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
		return reportcoaVO;
	}

	@Override
	public List<ReportCoaVO> getAll() {
		List<ReportCoaVO> list=new ArrayList<ReportCoaVO>();
		ReportCoaVO reportcoaVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
					reportcoaVO=new ReportCoaVO();
					reportcoaVO.setRep_id(rs.getString("rep_id"));
					reportcoaVO.setCoa_id(rs.getString("coa_id"));
					reportcoaVO.setRep_cot(rs.getString("rep_cot"));
					reportcoaVO.setRep_sta(rs.getString("rep_sta"));
					list.add(reportcoaVO);
			}
			
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
	
	
	
	
}
