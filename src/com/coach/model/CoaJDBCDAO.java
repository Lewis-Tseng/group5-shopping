package com.coach.model;

import java.util.*;



import java.io.*;
import java.sql.*;

public class CoaJDBCDAO implements CoaDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

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



	@Override
	public void insert(CoaVO coaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void insert2(CoaVO coaVO) {
		// TODO Auto-generated method stub
		
		

			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT2);

				pstmt.setString(1, coaVO.getCoa_name());
				pstmt.setString(2, coaVO.getCoa_gender());
				pstmt.setString(3, coaVO.getCoa_email());
				pstmt.setString(4, coaVO.getCoa_psw());
				pstmt.setBytes(5, coaVO.getLicense());
				pstmt.setString(6, coaVO.getCoa_sta());

				
				pstmt.executeUpdate();

				// Handle any SQL errors
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
	public void update(CoaVO coaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String coa_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coa_id);

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
	public CoaVO findByPrimaryKey(String coa_id) {

		CoaVO coaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coa_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo �]�٬� Domain objects
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
			}

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO �]�٬� Domain objects
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
	public void update_CoaPer(CoaVO coaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PSW_COA);

			pstmt.setString(1, coa_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				
		
				coa_psw	=rs.getString("coa_psw");
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_EMAIL_COA);

			pstmt.setString(1, coa_email);

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
				coaVO.setLicense(null);
				coaVO.setCoa_pic(null);
				coaVO.setCoa_sta(rs.getString("coa_sta"));
				coaVO.setCoa_intro(rs.getString("coa_intro"));
				coaVO.setCoa_video(rs.getString("coa_video"));
				coaVO.setCoa_point(rs.getInt("coa_point"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_COAPOINT);

			pstmt.setInt(1, coa_point);
			pstmt.setString(2, coa_id);
		
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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


	public static void main(String[] args) throws IOException {
		//byte[] pic = getPictureByteArray("items/c.png");
		CoaJDBCDAO dao = new CoaJDBCDAO();
		
		// �s�W
		CoaVO coaVO1 = new CoaVO();
		coaVO1.setCoa_name("Bella");
		coaVO1.setCoa_gender("女");
		coaVO1.setCoa_email("123e@gmail.com");
		coaVO1.setCoa_psw("12345");
		coaVO1.setExpert("有氧");
		coaVO1.setLicense(null);
		coaVO1.setCoa_pic(null);
		coaVO1.setCoa_sta("0");
		coaVO1.setCoa_intro("自我介紹");
		coaVO1.setCoa_video("影片");

		dao.insert(coaVO1);

		// �ק�
//		CoaVO coaVO2 = new CoaVO();
//		coaVO2.setCoa_id("CO00003");
//		coaVO2.setCoa_name("志成");
//		coaVO2.setCoa_gender("男");
//		coaVO2.setCoa_email("123c@gmail.com");
//		coaVO2.setCoa_psw("12345");
//		coaVO2.setExpert("格鬥");
//		coaVO2.setLicense(pic);
//		coaVO2.setCoa_pic(pic);
//		coaVO2.setCoa_sta("0");
//		coaVO2.setCoa_intro("自我介紹");
//		coaVO2.setCoa_video("影片");
//		dao.update(coaVO2);
////
////		// �R��
//		dao.delete("CO00002");
////
////		// �d��
//		CoaVO coaVO3 = dao.findByPrimaryKey("CO00001");
//		System.out.print(coaVO3.getCoa_id() + ",");
//		System.out.print(coaVO3.getCoa_name() + ",");
//		System.out.print(coaVO3.getCoa_gender() + ",");
//		System.out.print(coaVO3.getCoa_email() + ",");
//		System.out.print(coaVO3.getCoa_psw() + ",");
//		System.out.print(coaVO3.getExpert() + ",");
//		System.out.println(coaVO3.getLicense() + ",");
//		System.out.println(coaVO3.getCoa_pic() + ",");
//		System.out.println(coaVO3.getCoa_sta());
//		System.out.println(coaVO3.getCoa_intro());
//		System.out.println(coaVO3.getCoa_video());
//		System.out.println("---------------------");
//
//		// �d��
//		List<CoaVO> list = dao.getAll();
//		for (CoaVO aCoa : list) {
//			System.out.print(aCoa.getCoa_id() + ",");
//			System.out.print(aCoa.getCoa_name() + ",");
//			System.out.print(aCoa.getCoa_gender() + ",");
//			System.out.print(aCoa.getCoa_email() + ",");
//			System.out.print(aCoa.getCoa_psw() + ",");
//			System.out.print(aCoa.getExpert() + ",");
//			System.out.println(aCoa.getLicense() + ",");
//			System.out.println(aCoa.getCoa_pic() + ",");
//			System.out.println(aCoa.getCoa_sta());
//		    System.out.println(aCoa.getCoa_intro());
//		    System.out.println(aCoa.getCoa_video());
//			System.out.println();
//		}
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);//���Y
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
	public CoaVO findCoaPoint(String coa_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
