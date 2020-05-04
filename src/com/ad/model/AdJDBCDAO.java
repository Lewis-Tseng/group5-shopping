package com.ad.model;
import java.util.*;
import java.io.*;
import java.sql.*;
public class AdJDBCDAO implements Ad_DAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
		
		private static final String INSERT_STMT = 
"Insert into AD(AD_ID,PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF) values ('AD'||LPAD(TO_CHAR(AD_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?)";
			
		private static final String GET_ALL_STMT = 
//			"SELECT AD_ID,PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF FROM AD order by AD_ID";
		"SELECT * FROM AD";
		private static final String GET_ONE_STMT = 
			"SELECT PRO_NO,AD_PIC,AD_TITLE,AD_INFO,AD_DATE_ON,AD_DATE_OFF FROM AD where AD_ID = ?";
		private static final String DELETE = 
			"DELETE FROM AD WHERE AD_ID=?";
		private static final String UPDATE = 
			"UPDATE AD set PRO_NO=?, AD_PIC=?, AD_TITLE=?, AD_INFO=?, AD_DATE_ON=?,AD_DATE_OFF=? where AD_ID = ?";
	
	@Override
	public void insert(AdVO adVO)  {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,adVO.getPro_no());
			pstmt.setBytes(2,adVO.getAd_pic());
			pstmt.setString(3,adVO.getAd_title());
			pstmt.setString(4, adVO.getAd_info());
			pstmt.setDate(5, adVO.getAd_date_on());
			pstmt.setDate(6, adVO.getAd_date_off());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adVO.getPro_no());
			pstmt.setBytes(2,adVO.getAd_pic());
			pstmt.setString(3, adVO.getAd_title());
			pstmt.setString(4, adVO.getAd_info());
			pstmt.setDate(5, adVO.getAd_date_on());
			pstmt.setDate(6, adVO.getAd_date_off());
			pstmt.setString(7, adVO.getAd_id());
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
	public void delete(String ad_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ad_id);

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
	public AdVO findByPrmarykey(String ad_id) {
		AdVO adVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
	public static void main(String[] args) throws IOException  {
		byte[] pic = getPictureByteArray("WebContent/image/彈力繩.jpg");
		String str = getLongString("WebContent/item/彈力繩.txt");
		AdJDBCDAO dao = new AdJDBCDAO();
		//新增
//		AdVO adVO1=new AdVO();
//		adVO1.setPro_no("PT00002");
//		adVO1.setAd_pic(pic);
//		adVO1.setAd_title("好禮贈送");
//		adVO1.setAd_info(str);
//		adVO1.setAd_date_on(java.sql.Date.valueOf("2019-10-06"));
//		adVO1.setAd_date_off(java.sql.Date.valueOf("2019-10-20"));
//		dao.insert(adVO1);
//		System.out.println("成功新增");	
		//查詢
//		AdVO adVO3=dao.findByPrmarykey("AD00003");
//		System.out.print(adVO3.getPro_no() + ",");
//		System.out.print(adVO3.getAd_pic() + ",");
//		System.out.print(adVO3.getAd_title() + ",");
//		System.out.print(adVO3.getAd_info() + ",");
//		System.out.print(adVO3.getAd_date_on() + ",");
//		System.out.println(adVO3.getAd_date_off());
		//查詢
		List<AdVO> list = dao.getAll();
		for (AdVO ad : list) {
			System.out.print(ad.getAd_id() + ",");
			System.out.print(ad.getPro_no() + ",");
			System.out.print(ad.getAd_pic() + ",");
			System.out.print(ad.getAd_title() + ",");
			System.out.print(ad.getAd_info() + ",");
			System.out.print(ad.getAd_date_on() + ",");
			System.out.print(ad.getAd_date_off());
			System.out.println();
		}
		//修改
	
//		AdVO adVO2=new AdVO();
//		adVO2.setAd_id("AD0001");
//		adVO2.setPro_no("PT00001");
//		adVO2.setAd_pic(pic);
//		adVO2.setAd_title("彈力繩");
//		adVO2.setAd_info(str);
//		adVO2.setAd_date_on(java.sql.Date.valueOf("2019-11-01"));
//		adVO2.setAd_date_off(java.sql.Date.valueOf("2019-12-31"));
//		dao.update(adVO2);
//		System.out.println("成功修改");	
		//刪除
//		dao.delete("AD00015");
//		System.out.println("成功刪除");	
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
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

}
