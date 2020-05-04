package com.gro.group;

import java.util.*;
import java.util.Date;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gro.joingroup.joingroupVO;


public class groupDAO implements groupDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RGROUP(GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT) VALUES ('GR'||LPAD(to_char(RGROUP_SEQ.NEXTVAL), 5, '0'),?,?,?,?,?,?,?,1,?,?,?,-1,?,?)";
	private static final String GET_ALL_STMT = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP WHERE GRO_STAT!=-1 ORDER BY GRO_STIME";
	private static final String GET_ONE_STMT = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where GRO_ID = ?";
	private static final String GET_GROS_BY_MEM = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where MEM_ID = ? AND GRO_STAT!=-1 ORDER BY GRO_STIME";
	private static final String GET_GROS_BY_MEM_HISTORY = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where MEM_ID = ?  AND GRO_STAT=-1 ORDER BY GRO_STIME";

	private static final String DELETE_GROs = "DELETE FROM RGROUP where GRO_ID = ?";
	private static final String DELETE_REPORTs = "DELETE FROM GRO_REPORT where GRO_ID = ?";
	private static final String DELETE_PICs = "DELETE FROM GRO_PIC where GRO_ID = ?";
	private static final String DELETE_JOINs = "DELETE FROM JOIN_GROUP where GRO_ID = ?";
	
	private static final String UPDATE = "UPDATE RGROUP set GRO_CID=?, MEM_ID=?, GRO_NAME=?, GRO_SIG_FTIME=?, GRO_STIME=?, GRO_FTIME=?, GRO_MNUM_MIN=?, GRO_MNUM_MAX=?, GRO_INFO=?, GRO_LOGO_PIC=?, GRO_STAT=? where GRO_ID = ?";
	private static final String UPDATE_NO_PIC = "UPDATE RGROUP set GRO_CID=?, MEM_ID=?, GRO_NAME=?, GRO_SIG_FTIME=?, GRO_STIME=?, GRO_FTIME=?, GRO_MNUM_MIN=?, GRO_MNUM_MAX=?, GRO_INFO=?, GRO_STAT=? where GRO_ID = ?";
	
	private static final String GET_DATE_BEFORE = "SELECT GRO_ID,GRO_STAT FROM RGROUP WHERE GRO_FTIME < SYSDATE";
	private static final String UPDATE_DATE_BEFORE = "UPDATE RGROUP SET GRO_STAT=-1 WHERE GRO_ID=?";
	
	public static Reader getLongStringStream(String path) throws IOException {
		return new FileReader(path);

	}
	
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); 
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	@Override
	public void insert(groupVO groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			Integer gro_mnum_min = groupVO.getGro_mnum_min();
			Integer stat;
			
			Date today = new Date();
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());

			
			if(gro_mnum_min>1) {
				stat = 0;
			}else {
				stat = 1;
			}

			pstmt.setString(1, groupVO.getGro_cid());
			pstmt.setString(2, groupVO.getMem_id());
			pstmt.setString(3, groupVO.getGro_name());
			pstmt.setDate(4, sqlDate);
			pstmt.setDate(5, groupVO.getGro_sig_ftime());
			pstmt.setDate(6, groupVO.getGro_stime());
			pstmt.setDate(7, groupVO.getGro_ftime());
			pstmt.setInt(8, groupVO.getGro_mnum_min());
			pstmt.setInt(9, groupVO.getGro_mnum_max());
			pstmt.setString(10, groupVO.getGro_info());
			pstmt.setBytes(11, groupVO.getGro_logo_pic());
			pstmt.setInt(12, stat);

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void update(groupVO groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			Integer gro_mnum_min = groupVO.getGro_mnum_min();
			Integer gro_mnum = groupVO.getGro_mnum();
			Integer stat;
			
			if(gro_mnum_min > gro_mnum) {
				stat = 0;
			}else {
				stat = 1;
			}
					
			
			if(groupVO.getGro_logo_pic().length==0) {
				pstmt = con.prepareStatement(UPDATE_NO_PIC);

				pstmt.setString(1, groupVO.getGro_cid());
				pstmt.setString(2, groupVO.getMem_id());
				pstmt.setString(3, groupVO.getGro_name());
				pstmt.setDate(4, groupVO.getGro_sig_ftime());
				pstmt.setDate(5, groupVO.getGro_stime());
				pstmt.setDate(6, groupVO.getGro_ftime());
				pstmt.setInt(7, groupVO.getGro_mnum_min());
				pstmt.setInt(8, groupVO.getGro_mnum_max());
				pstmt.setString(9, groupVO.getGro_info());
				pstmt.setInt(10, stat);
				pstmt.setString(11, groupVO.getGro_id());
			}else {
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, groupVO.getGro_cid());
				pstmt.setString(2, groupVO.getMem_id());
				pstmt.setString(3, groupVO.getGro_name());
				pstmt.setDate(4, groupVO.getGro_sig_ftime());
				pstmt.setDate(5, groupVO.getGro_stime());
				pstmt.setDate(6, groupVO.getGro_ftime());
				pstmt.setInt(7, groupVO.getGro_mnum_min());
				pstmt.setInt(8, groupVO.getGro_mnum_max());
				pstmt.setString(9, groupVO.getGro_info());
				pstmt.setBytes(10, groupVO.getGro_logo_pic());
				pstmt.setInt(11, stat);
				pstmt.setString(12, groupVO.getGro_id());
			}
			

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void delete(String gro_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();


			con.setAutoCommit(false);
			
			
			pstmt = con.prepareStatement(DELETE_REPORTs);
			pstmt.setString(1, gro_id);
			pstmt.executeUpdate();
			
			
			pstmt = con.prepareStatement(DELETE_PICs);
			pstmt.setString(1, gro_id);
			pstmt.executeUpdate();

			
			pstmt = con.prepareStatement(DELETE_JOINs);
			pstmt.setString(1, gro_id);
			pstmt.executeUpdate();

			
			pstmt = con.prepareStatement(DELETE_GROs);
			pstmt.setString(1, gro_id);
			

			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			

		} catch (SQLException se) {
			if (con != null) {
				try {

					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public groupVO findByPrimaryKey(String gro_id) {

		groupVO groVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				groVO = new groupVO();
				groVO.setGro_id(rs.getString("GRO_ID"));
				groVO.setGro_cid(rs.getString("GRO_CID"));
				groVO.setMem_id(rs.getString("MEM_ID"));
				groVO.setGro_name(rs.getString("GRO_NAME"));
				groVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
				groVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
				groVO.setGro_stime(rs.getDate("GRO_STIME"));
				groVO.setGro_ftime(rs.getDate("GRO_FTIME"));
				groVO.setGro_mnum(rs.getInt("GRO_MNUM"));
				groVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
				groVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
				groVO.setGro_info(rs.getString("GRO_INFO"));
				groVO.setGro_astar(rs.getInt("GRO_ASTAR"));
				groVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
				groVO.setGro_stat(rs.getInt("GRO_STAT"));
			}


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
		return groVO;
	}
	
	@Override
	public groupVO findByPrimaryKeyStat(String gro_id) {

		groupVO groVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();
			
			
				while (rs.next()) {
					if(rs.getInt("GRO_STAT")!=-1) {
					groVO = new groupVO();
					groVO.setGro_id(rs.getString("GRO_ID"));
					groVO.setGro_cid(rs.getString("GRO_CID"));
					groVO.setMem_id(rs.getString("MEM_ID"));
					groVO.setGro_name(rs.getString("GRO_NAME"));
					groVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
					groVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
					groVO.setGro_stime(rs.getDate("GRO_STIME"));
					groVO.setGro_ftime(rs.getDate("GRO_FTIME"));
					groVO.setGro_mnum(rs.getInt("GRO_MNUM"));
					groVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
					groVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
					groVO.setGro_info(rs.getString("GRO_INFO"));
					groVO.setGro_astar(rs.getInt("GRO_ASTAR"));
					groVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
					groVO.setGro_stat(rs.getInt("GRO_STAT"));
					}
				}
			
			


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
		return groVO;
	}
	
	@Override
	public groupVO findByPrimaryKeyStatHistory(String gro_id) {

		groupVO groVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();
			
			
				while (rs.next()) {
					if(rs.getInt("GRO_STAT")==-1) {
					groVO = new groupVO();
					groVO.setGro_id(rs.getString("GRO_ID"));
					groVO.setGro_cid(rs.getString("GRO_CID"));
					groVO.setMem_id(rs.getString("MEM_ID"));
					groVO.setGro_name(rs.getString("GRO_NAME"));
					groVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
					groVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
					groVO.setGro_stime(rs.getDate("GRO_STIME"));
					groVO.setGro_ftime(rs.getDate("GRO_FTIME"));
					groVO.setGro_mnum(rs.getInt("GRO_MNUM"));
					groVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
					groVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
					groVO.setGro_info(rs.getString("GRO_INFO"));
					groVO.setGro_astar(rs.getInt("GRO_ASTAR"));
					groVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
					groVO.setGro_stat(rs.getInt("GRO_STAT"));
					
					}
				}
			
			


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
		return groVO;
	}
	@Override
	public List<groupVO> getGrosByMem(String mem_id) {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROS_BY_MEM);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				groupVO = new groupVO();
				groupVO.setGro_id(rs.getString("GRO_ID"));
				groupVO.setGro_cid(rs.getString("GRO_CID"));
				groupVO.setMem_id(rs.getString("MEM_ID"));
				groupVO.setGro_name(rs.getString("GRO_NAME"));
				groupVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
				groupVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
				groupVO.setGro_stime(rs.getDate("GRO_STIME"));
				groupVO.setGro_ftime(rs.getDate("GRO_FTIME"));
				groupVO.setGro_mnum(rs.getInt("GRO_MNUM"));
				groupVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
				groupVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
				groupVO.setGro_info(rs.getString("GRO_INFO"));
				groupVO.setGro_astar(rs.getInt("GRO_ASTAR"));
				groupVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
				groupVO.setGro_stat(rs.getInt("GRO_STAT"));
				
				list.add(groupVO);
			}
	

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
	
	@Override
	public List<groupVO> getGrosByMemHistory(String mem_id) {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROS_BY_MEM_HISTORY);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				groupVO = new groupVO();
				groupVO.setGro_id(rs.getString("GRO_ID"));
				groupVO.setGro_cid(rs.getString("GRO_CID"));
				groupVO.setMem_id(rs.getString("MEM_ID"));
				groupVO.setGro_name(rs.getString("GRO_NAME"));
				groupVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
				groupVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
				groupVO.setGro_stime(rs.getDate("GRO_STIME"));
				groupVO.setGro_ftime(rs.getDate("GRO_FTIME"));
				groupVO.setGro_mnum(rs.getInt("GRO_MNUM"));
				groupVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
				groupVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
				groupVO.setGro_info(rs.getString("GRO_INFO"));
				groupVO.setGro_astar(rs.getInt("GRO_ASTAR"));
				groupVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
				groupVO.setGro_stat(rs.getInt("GRO_STAT"));
				
				list.add(groupVO);
			}

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

	@Override
	public List<groupVO> getAll() {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groVO = new groupVO();
				groVO.setGro_id(rs.getString("GRO_ID"));
				groVO.setGro_cid(rs.getString("GRO_CID"));
				groVO.setMem_id(rs.getString("MEM_ID"));
				groVO.setGro_name(rs.getString("GRO_NAME"));
				groVO.setGro_sig_stime(rs.getDate("GRO_SIG_STIME"));
				groVO.setGro_sig_ftime(rs.getDate("GRO_SIG_FTIME"));
				groVO.setGro_stime(rs.getDate("GRO_STIME"));
				groVO.setGro_ftime(rs.getDate("GRO_FTIME"));
				groVO.setGro_mnum(rs.getInt("GRO_MNUM"));
				groVO.setGro_mnum_min(rs.getInt("GRO_MNUM_MIN"));
				groVO.setGro_mnum_max(rs.getInt("GRO_MNUM_MAX"));
				groVO.setGro_info(rs.getString("GRO_INFO"));
				groVO.setGro_astar(rs.getInt("GRO_ASTAR"));
				groVO.setGro_logo_pic(rs.getBytes("GRO_LOGO_PIC"));
				groVO.setGro_stat(rs.getInt("GRO_STAT"));
				
				list.add(groVO);
			}


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
	
	
	@Override
	public void updateTodayBeforeStat() {


		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DATE_BEFORE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getInt("GRO_STAT")!=-1) {
					pstmt2 = con.prepareStatement(UPDATE_DATE_BEFORE);
					pstmt2.setString(1,rs.getString("GRO_ID"));
					
					pstmt2.executeUpdate();
				}
			}

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
			if (pstmt2 != null) {
				try {
					pstmt2.close();
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
	}
	
	
}
