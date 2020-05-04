package com.reportmem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chatroom.model.ChatRoomVO;


public class ReportMemJDBCDAO implements ReportMemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO REPORT_MEM (REP_ID,MEM_ID,REP_COT,REP_STA )VALUES('RM' || LPAD(to_char(REPORT_MEM_seq.NEXTVAL), 5,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT_MEM";
	private static final String GET_ONE_STMT = "SELECT REP_ID, MEM_ID,REP_COT,REP_STA FROM REPORT_MEM WHERE REP_ID=?";
	private static final String DELETE = "DELETE FROM REPORT_MEM WHERE REP_ID=?";
	private static final String UPDATE = "UPDATE  REPORT_MEM SET MEM_ID=? ,REP_COT=? ,REP_STA=?  WHERE REP_ID=?";
	private static final String GET_ONE_STMT1 = "SELECT MEM_ID,REP_COT,REP_STA FROM REPORT_MEM WHERE MEM_ID=?";
	@Override
	public void insert(ReportMemVO reportmenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
		
			pstmt.setString(1, reportmenVO.getMem_id());
			pstmt.setString(2, reportmenVO.getRep_cot());
			pstmt.setString(3, reportmenVO.getRep_sta());
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
	public void update(ReportMemVO reportmenVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, reportmenVO.getMem_id());
			pstmt.setString(2, reportmenVO.getRep_cot());
			pstmt.setString(3, reportmenVO.getRep_sta());
			pstmt.setString(4, reportmenVO.getRep_id());
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
	public ReportMemVO findForigenKey(String mem_id) {
		ReportMemVO reportmenVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT1);
			pstmt.setString(1, mem_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				reportmenVO=new ReportMemVO();
				reportmenVO.setMem_id(rs.getString("mem_id"));
				reportmenVO.setRep_cot(rs.getString("rep_cot"));
				reportmenVO.setRep_sta(rs.getString("rep_sta"));
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
		return reportmenVO;
	}
	@Override
	public ReportMemVO findPrimaryKey(String rep_id) {
		ReportMemVO reportmenVO=null;
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
				reportmenVO=new ReportMemVO();
				reportmenVO.setRep_id(rs.getString("rep_id"));
				reportmenVO.setMem_id(rs.getString("mem_id"));
				reportmenVO.setRep_cot(rs.getString("rep_cot"));
				reportmenVO.setRep_sta(rs.getString("rep_sta"));
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
		return reportmenVO;
	}

	@Override
	public List<ReportMemVO> getAll() {
		List<ReportMemVO> list=new ArrayList<ReportMemVO>();
		ReportMemVO reportmenVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
					reportmenVO=new ReportMemVO();
					reportmenVO.setRep_id(rs.getString("rep_id"));
					reportmenVO.setMem_id(rs.getString("mem_id"));
					reportmenVO.setRep_cot(rs.getString("rep_cot"));
					reportmenVO.setRep_sta(rs.getString("rep_sta"));
					list.add(reportmenVO);
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
		ReportMemJDBCDAO dao=new ReportMemJDBCDAO();
		ReportMemVO report=new ReportMemVO();
//		//新增
		report.setMem_id("ME00001");
		report.setRep_cot("這會員真的好煩");
		report.setRep_sta("未處理");
		dao.insert(report);
		System.out.println("新增成功");
		//修改
		ReportMemVO report2=new ReportMemVO();
		report2.setMem_id("ME00002");
		report2.setRep_cot("這會員美到很過分");
		report2.setRep_sta("未處理");
		report2.setRep_id("RM00002");
		dao.update(report2);
		System.out.println("修改成功");
		//查詢
		ReportMemVO report3=dao.findPrimaryKey("RM00001");
		System.out.println(report3.getMem_id());
		System.out.println(report3.getRep_cot());
		System.out.println(report3.getRep_sta());
		//查詢
		List<ReportMemVO> list=dao.getAll();
		for(ReportMemVO report1:list) {
			System.out.println(report1.getRep_id());
			System.out.println(report1.getMem_id());
			System.out.println(report1.getRep_cot());
			System.out.println(report1.getRep_sta());
		}
	}
}
