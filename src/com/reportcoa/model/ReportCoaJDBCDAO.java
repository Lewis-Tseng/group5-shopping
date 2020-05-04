package com.reportcoa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reportmem.model.ReportMemVO;

public class ReportCoaJDBCDAO implements ReportCoaDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO REPORT_COA (REP_ID,COA_ID,REP_COT,REP_STA)VALUES('RC' || LPAD(to_char(REPORT_MEM_seq.NEXTVAL), 5,'0'),?,?,0)";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT_COA";
	private static final String GET_ONE_STMT = "SELECT REP_ID,COA_ID,REP_COT,REP_STA FROM REPORT_COA WHERE REP_ID=?";
	private static final String DELETE = "DELETE FROM REPORT_COA WHERE REP_ID=?";
	private static final String UPDATE = "UPDATE  REPORT_COA SET REP_STA=?  WHERE REP_ID=?";
	
	private static final String GET_ONE_STMT1 = "SELECT COA_ID,REP_COT,REP_STA FROM REPORT_COA WHERE COA_ID=?";
	@Override
	public void insert(ReportCoaVO reportCoaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, reportCoaVO.getCoa_id());
			pstmt.setString(2, reportCoaVO.getRep_cot());
			pstmt.executeUpdate();

			// Handle any driver errorst
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(ReportCoaVO reportCoaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, reportCoaVO.getRep_sta());
			pstmt.setString(2, reportCoaVO.getRep_id());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,rep_id);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public ReportCoaVO findForeignKey(String coa_id) {
		ReportCoaVO reportcoaVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT1);
			pstmt.setString(1, coa_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				reportcoaVO=new ReportCoaVO();
				reportcoaVO.setCoa_id(rs.getString("coa_id"));
				reportcoaVO.setRep_cot(rs.getString("rep_cot"));
				reportcoaVO.setRep_sta(rs.getString("rep_sta"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return reportcoaVO;
	}
	
	@Override
	public ReportCoaVO findPrimaryKey(String rep_id) {
		ReportCoaVO reportcoaVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public static void main(String[] args) {
		ReportCoaJDBCDAO dao=new ReportCoaJDBCDAO ();
		ReportCoaVO reportcoaVO=new ReportCoaVO();
//		reportcoaVO.setCoa_id("CO00001");
//		reportcoaVO.setRep_cot("這教練真的好煩");
//		reportcoaVO.setRep_sta("未處理");
//		dao.insert(reportcoaVO);
//		System.out.println("新增成功");
//		ReportCoaVO reportcoaVO2=new ReportCoaVO();
//		reportcoaVO2.setCoa_id("CO00002");
//		reportcoaVO2.setRep_cot("這教練美到很過分");
//		reportcoaVO2.setRep_sta("未處理");
//		reportcoaVO2.setRep_id("RC00001");
//		dao.update(reportcoaVO2);
//		System.out.println("修改成功");
		ReportCoaVO reportcoaVO3=dao.findPrimaryKey("RC00001");
//		System.out.println(reportcoaVO3.getCoa_id());
//		System.out.println(reportcoaVO3.getRep_cot());
//		System.out.println(reportcoaVO3.getRep_sta());
		List<ReportCoaVO> list=dao.getAll();
		for(ReportCoaVO reportcoaVO4:list) {
			System.out.println(reportcoaVO4.getRep_id());
			System.out.println(reportcoaVO4.getCoa_id());
			System.out.println(reportcoaVO4.getRep_cot());
			System.out.println(reportcoaVO4.getRep_sta());
		}
		
	}
}
