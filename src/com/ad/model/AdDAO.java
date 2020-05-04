package com.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdDAO implements Ad_DAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "Insert into AD(AD_ID,PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF) values ('AD'||LPAD(TO_CHAR(AD_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT AD_ID,PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF FROM AD order by AD_ID";
	private static final String GET_ONE_STMT = "SELECT PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF FROM AD where AD_ID = ?";
	private static final String DELETE = "DELETE FROM AD WHERE AD_ID=?";
	private static final String UPDATE = "UPDATE AD set PRO_NO=?, AD_PIC=?, AD_TITLE=?, AD_INFO=?, AD_DATE_ON=?,AD_DATE_OFF=? where AD_ID = ?";

	@Override
	public void insert(AdVO adVO)  {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,adVO.getPro_no());
			pstmt.setBytes(2,adVO.getAd_pic());
			pstmt.setString(3,adVO.getAd_title());
			pstmt.setString(4, adVO.getAd_info());
			pstmt.setDate(5, adVO.getAd_date_on());
			pstmt.setDate(6, adVO.getAd_date_off());
			pstmt.executeUpdate();
			
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
	public void update(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adVO.getPro_no());
			pstmt.setBytes(2,adVO.getAd_pic());
			pstmt.setString(3, adVO.getAd_title());
			pstmt.setString(4, adVO.getAd_info());
			pstmt.setDate(5, adVO.getAd_date_on());
			pstmt.setDate(6, adVO.getAd_date_off());
			pstmt.setString(7, adVO.getAd_id());
			pstmt.executeUpdate();
			
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
	public void delete(String ad_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ad_id);
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public AdVO findByPrmarykey(String ad_id) {
		AdVO adVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ad_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				adVO=new AdVO();
				adVO.setPro_no(rs.getString("pro_no"));
				adVO.setAd_pic(rs.getBytes("ad_pic"));
				adVO.setAd_title(rs.getString("ad_title"));
				adVO.setAd_info(rs.getString("ad_info"));
				adVO.setAd_date_on(rs.getDate("ad_date_on"));
				adVO.setAd_date_off(rs.getDate("ad_date_off"));
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
		return adVO;
		
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list=new ArrayList<AdVO>();
		AdVO adVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				adVO=new AdVO();
				adVO.setAd_id(rs.getString("ad_id"));
				adVO.setPro_no(rs.getString("pro_no"));
				adVO.setAd_pic(rs.getBytes("ad_pic"));
				adVO.setAd_title(rs.getString("ad_title"));
				adVO.setAd_info(rs.getString("ad_info"));
				adVO.setAd_date_on(rs.getDate("ad_date_on"));
				adVO.setAd_date_off(rs.getDate("ad_date_off"));
				list.add(adVO);
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
		return list;
	}

}
