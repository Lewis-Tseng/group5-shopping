package com.gro.group;

import java.util.*;

import com.gro.group.groupVO;
import com.gro.joingroup.joingroupVO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

public class groupJDBCDAO implements groupDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO RGROUP(GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT) VALUES ('GR'||LPAD(to_char(RGROUP_SEQ.NEXTVAL), 5, '0'),?,?,?,?,?,?,?,1,?,?,?,-1,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP WHERE GRO_STAT!=-1 ORDER BY GRO_STIME";
	private static final String GET_ONE_STMT = 
		"SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where GRO_ID = ?";
	private static final String GET_GROS_BY_MEM = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where MEM_ID = ? AND GRO_STAT!=-1 ORDER BY GRO_STIME";
	private static final String GET_GROS_BY_MEM_HISTORY = "SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_ASTAR,GRO_LOGO_PIC,GRO_STAT FROM RGROUP where MEM_ID = ? AND GRO_STAT=-1 ORDER BY GRO_STIME";

	
	private static final String DELETE_GROs = 
		"DELETE FROM RGROUP where GRO_ID = ?";
	private static final String DELETE_REPORTs = 
		"DELETE FROM GRO_REPORT where GRO_ID = ?";
	private static final String DELETE_PICs = 
		"DELETE FROM GRO_PIC where GRO_ID = ?";
	private static final String DELETE_JOINs = 
		"DELETE FROM JOIN_GROUP where GRO_ID = ?";

	private static final String UPDATE = 
		"UPDATE RGROUP set GRO_CID=?, MEM_ID=?, GRO_NAME=?, GRO_SIG_STIME=?, GRO_SIG_FTIME=?, GRO_STIME=?, GRO_FTIME=?, GRO_MNUM_MIN=?, GRO_MNUM_MAX=?, GRO_INFO=?, GRO_LOGO_PIC=?, GRO_STAT=? where GRO_ID = ?";
	
	
	private static final String GET_DATE_BEFORE = "SELECT GRO_ID,GRO_FTIME,GRO_STAT FROM RGROUP WHERE GRO_FTIME < SYSDATE";
	private static final String UPDATE_DATE_BEFORE = "UPDATE RGROUP SET　GRO_STAT=-1 WHERE GRO_ID=?";
	
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
	
	//"INSERT INTO RGROUP(GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_STAT) VALUES ('GR'||LPAD(to_char(RGROUP_SEQ.NEXTVAL), 5, '0'),?,?,?,?,?,?,?,?,?,?,?,1)"
	@Override
	public void insert(groupVO groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//
			Integer gro_mnum_min = groupVO.getGro_mnum_min();
			Integer stat;
			
			if(gro_mnum_min>1) {
				stat = 0;
			}else {
				stat = 1;
			}
			//
			
			pstmt.setString(1, groupVO.getGro_cid());
			pstmt.setString(2, groupVO.getMem_id());
			pstmt.setString(3, groupVO.getGro_name());
			pstmt.setDate(4, groupVO.getGro_sig_stime());
			pstmt.setDate(5, groupVO.getGro_sig_ftime());
			pstmt.setDate(6, groupVO.getGro_stime());
			pstmt.setDate(7, groupVO.getGro_ftime());
			pstmt.setInt(8, groupVO.getGro_mnum_min());
			pstmt.setInt(9, groupVO.getGro_mnum_max());
			pstmt.setString(10, groupVO.getGro_info());
			pstmt.setBytes(11, groupVO.getGro_logo_pic());
			pstmt.setInt(12, stat);

			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

	//"UPDATE RGROUP set GRO_CID=?, MEM_ID=?, GRO_NAME=?, GRO_SIG_STIME=?, GRO_SIG_FTIME=?, GRO_STIME=?, GRO_FTIME=?, GRO_MNUM=?, GRO_MNUM_MIN=?, GRO_MNUM_MAX=?, GRO_INFO=?, GRO_STAT=? where GRO_ID = ?"
	@Override
	public void update(groupVO groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			//
			Integer gro_mnum_min = groupVO.getGro_mnum_min();
			Integer gro_mnum = groupVO.getGro_mnum();
			Integer stat;
			
			if(gro_mnum_min > gro_mnum) {
				stat = 0;
			}else {
				stat = 1;
			}
			//

			pstmt.setString(1, groupVO.getGro_cid());
			pstmt.setString(2, groupVO.getMem_id());
			pstmt.setString(3, groupVO.getGro_name());
			pstmt.setDate(4, groupVO.getGro_sig_stime());
			pstmt.setDate(5, groupVO.getGro_sig_ftime());
			pstmt.setDate(6, groupVO.getGro_stime());
			pstmt.setDate(7, groupVO.getGro_ftime());
			pstmt.setInt(8, groupVO.getGro_mnum_min());
			pstmt.setInt(9, groupVO.getGro_mnum_max());
			pstmt.setString(10, groupVO.getGro_info());
			pstmt.setBytes(11, groupVO.getGro_logo_pic());
			pstmt.setInt(12, stat);
			pstmt.setString(13, groupVO.getGro_id());

			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

	//"DELETE FROM RGROUP where GRO_ID = ?"
	@Override
	public void delete(String gro_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
			


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

	//"SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_STAT FROM RGROUP where GRO_ID = ?"
	@Override
	public groupVO findByPrimaryKey(String gro_id) {

		groupVO groVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();

			if(rs.getInt("GRO_STAT")!=-1) {
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
			}
			


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();

			if(rs.getInt("GRO_STAT")==-1) {
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
			}
			


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
	public List<groupVO> getGrosByMem(String MEM_ID) {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROS_BY_MEM);
			pstmt.setString(1, MEM_ID);
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
	

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
	public List<groupVO> getGrosByMemHistory(String MEM_ID) {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROS_BY_MEM_HISTORY);
			pstmt.setString(1, MEM_ID);
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
	

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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

	//"SELECT GRO_ID,GRO_CID,MEM_ID,GRO_NAME,GRO_SIG_STIME,GRO_SIG_FTIME,GRO_STIME,GRO_FTIME,GRO_MNUM,GRO_MNUM_MIN,GRO_MNUM_MAX,GRO_INFO,GRO_STAT FROM RGROUP"
	@Override
	public List<groupVO> getAll() {
		List<groupVO> list = new ArrayList<groupVO>();
		groupVO groVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
//	================================================================================================================================
	@Override
	public void updateTodayBeforeStat() {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DATE_BEFORE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				pstmt2 = con.prepareStatement(UPDATE_DATE_BEFORE);
				pstmt2.setString(1,rs.getString("GRO_ID"));
				
				pstmt2.executeUpdate();
			}
	

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

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
//	================================================================================================================================

	public static void main(String[] args) throws IOException {

		groupJDBCDAO dao = new groupJDBCDAO();
		// �憓�
//		String str1 = getLongString("item/gro1.txt");
//		byte[] pic = getPictureByteArray("image/a1.gif");
//		groupVO groupVO1 = new groupVO();
//		
//		groupVO1.setGro_cid("GC00002");
//		groupVO1.setMem_id("ME00002");
//		groupVO1.setGro_name("=U=");
//		groupVO1.setGro_sig_stime(java.sql.Date.valueOf("2019-10-13"));
//		groupVO1.setGro_sig_ftime(java.sql.Date.valueOf("2019-11-13"));
//		groupVO1.setGro_stime(java.sql.Date.valueOf("2019-12-13"));
//		groupVO1.setGro_ftime(java.sql.Date.valueOf("2020-1-13"));
//		groupVO1.setGro_mnum_min(1);
//		groupVO1.setGro_mnum_max(15);
//		groupVO1.setGro_info(str1);
//		groupVO1.setGro_logo_pic(pic);
//		
//		dao.insert(groupVO1);

		// 靽格
//		String str2 = getLongString("item/gro2.txt");
//		byte[] pic2 = getPictureByteArray("image/003L.png");
//		groupVO groupVO2 = new groupVO();
//		
//		groupVO2.setGro_id("GR00004");
//		groupVO2.setGro_cid("GC00001");
//		groupVO2.setMem_id("ME00001");
//		groupVO2.setGro_name("皜祈岫");
//		groupVO2.setGro_sig_stime(java.sql.Date.valueOf("2020-10-13"));
//		groupVO2.setGro_sig_ftime(java.sql.Date.valueOf("2020-11-13"));
//		groupVO2.setGro_stime(java.sql.Date.valueOf("2020-12-13"));
//		groupVO2.setGro_ftime(java.sql.Date.valueOf("2021-1-13"));
//		groupVO2.setGro_mnum_min(3);
//		groupVO2.setGro_mnum_max(33);
//		groupVO2.setGro_info(str2);
//		groupVO2.setGro_logo_pic(pic2);
//		
//		dao.update(groupVO2);

		// ��
//		dao.delete("GR00001");

		// �閰�
//		groupVO groupVO3 = dao.findByPrimaryKey("GR00004");
//		
//		System.out.print(groupVO3.getGro_id() + ",");
//		System.out.print(groupVO3.getGro_name() + ",");
//		System.out.print(groupVO3.getGro_astar() + ",");
//		System.out.print(groupVO3.getGro_logo_pic() + ",");
//		System.out.println(groupVO3.getGro_info());
//		System.out.println("---------------------");

		// �閰�(�������)
//		List<groupVO> list = dao.getAll();
//		
//		for (groupVO Group: list) {
//			System.out.print(Group.getGro_id() + ",");
//			System.out.print(Group.getGro_cid() + ",");
//			System.out.print(Group.getMem_id() + ",");
//			System.out.print(Group.getGro_name() + ",");
//			System.out.print(Group.getGro_sig_stime() + ",");
//			System.out.print(Group.getGro_sig_ftime() + ",");
//			System.out.print(Group.getGro_stime() + ",");
//			System.out.print(Group.getGro_ftime() + ",");
//			System.out.print(Group.getGro_mnum() + ",");
//			System.out.print(Group.getGro_mnum_min() + ",");
//			System.out.print(Group.getGro_mnum_max() + ",");
//			System.out.print(Group.getGro_info() + ",");
//			System.out.print(groupVO3.getGro_astar() + ",");
//			System.out.print(groupVO3.getGro_logo_pic() + ",");
//			System.out.print(Group.getGro_stat());
//			
//			System.out.println();
//		
//		}
		
			dao.updateTodayBeforeStat();
		
		
	}
}