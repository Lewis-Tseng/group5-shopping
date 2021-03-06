package com.gro.pic;

import java.util.*;

import com.gro.group.groupVO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

public class pictureJDBCDAO implements pictureDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO GRO_PIC (GRO_PID,GRO_ID,GRO_PIC) VALUES ('GI'||LPAD(to_char(GRO_PIC_SEQ.NEXTVAL), 5, '0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_PID,GRO_ID,GRO_PIC FROM GRO_PIC ORDER BY GRO_PID";
	private static final String GET_ALL_PICs = 
		"SELECT GRO_PID,GRO_ID,GRO_PIC FROM GRO_PIC WHERE GRO_ID = ?";
	private static final String DELETE = 
		"DELETE FROM GRO_PIC WHERE GRO_PID = ?";
	
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

	//"INSERT INTO GRO_PIC (GRO_PID,GRO_ID,GRO_PIC) VALUES ('GI'||LPAD(to_char(GRO_PIC_SEQ.NEXTVAL), 5, '0'), ?, ?)"
	@Override
	public void insert(pictureVO pictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pictureVO.getGro_id());
			pstmt.setBytes(2, pictureVO.getGro_pic());


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

	//"DELETE FROM GRO_PIC WHERE GRO_PID = ?"
	@Override
	public void delete(String gro_pid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gro_pid);

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

	//"SELECT GRO_PID,GRO_ID,GRO_PIC FROM GRO_PIC WHERE GRO_ID = ?"
	@Override
	public Set<pictureVO> getPicsByGro(String gro_id) {
		Set<pictureVO> set = new LinkedHashSet<pictureVO>();
		pictureVO pictureVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_PICs);
			
			pstmt.setString(1, gro_id);
			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				pictureVO = new pictureVO();
				pictureVO.setGro_id(rs.getString("GRO_ID"));
				pictureVO.setGro_pid(rs.getString("GRO_PID"));
				pictureVO.setGro_pic(rs.getBytes("GRO_PIC"));
				set.add(pictureVO); 
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
		return set;
	}

	//"SELECT GRO_PID,GRO_ID,GRO_PIC FROM GRO_PIC ORDER BY GRO_PID"
	@Override
	public List<pictureVO> getAll() {
		List<pictureVO> list = new ArrayList<pictureVO>();
		pictureVO pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				pictureVO = new pictureVO();
				pictureVO.setGro_id(rs.getString("GRO_ID"));
				pictureVO.setGro_pid(rs.getString("GRO_PID"));
				pictureVO.setGro_pic(rs.getBytes("GRO_PIC"));
				list.add(pictureVO);
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

	public static void main(String[] args) throws IOException {

		pictureJDBCDAO dao = new pictureJDBCDAO();

		// 新增
		byte[] pic = getPictureByteArray("image/a1.gif");
		pictureVO pictureVO1 = new pictureVO();
		
		pictureVO1.setGro_id("GR00001");
		pictureVO1.setGro_pic(pic);
		
		dao.insert(pictureVO1);

		// 刪除
//		dao.delete("GI00003");

		// 查詢(此團所有圖片)
//		Set<pictureVO> set = dao.getPicsByGro("GR00001");
//		
//		for (pictureVO aPic : set) {
//		System.out.print(aPic.getGro_id() + ",");
//		System.out.print(aPic.getGro_pid() + ",");
//		System.out.print(aPic.getGro_pic());
//		System.out.println();
		
//		}

		// 查詢(所有資料)
//		List<pictureVO> list = dao.getAll();
//		
//		for (pictureVO aPic : list) {
//			System.out.print(aPic.getGro_id() + ",");
//			System.out.print(aPic.getGro_pid() + ",");
//			System.out.print(aPic.getGro_pic());
//			System.out.println();
//		
//		}
		
	}
}