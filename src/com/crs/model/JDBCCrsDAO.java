package com.crs.model;

import java.util.*;

import com.res.model.ResVO;

import java.io.*;

import java.sql.*;

public class JDBCCrsDAO implements CrsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@18.222.254.179:1521:XE";
//	String userid = "DA103G5";
//	String passwd = "DA103G5";

	private static final String INSERT_STMT = "INSERT INTO CRS (CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE,"
			+ " CRS_CONTENT, START_DATE,END_DATE, WEEK_CRS, START_TIME, END_TIME, CRS_IMG) "
			+ "VALUES ('CN'||LPAD(to_char(crs_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String DELETE_CRS = "DELETE FROM CRS where crs_no = ?";

	private static final String GET_ONE_STMT = "SELECT CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE, CRS_CONTENT,"
			+ " to_char(START_DATE, 'yyyy-mm-dd') START_DATE , to_char(END_DATE, 'yyyy-mm-dd') END_DATE, "
			+ " WEEK_CRS,  to_char(START_TIME, 'yyyy-mm-dd hh:mi:ss') START_TIME, "
			+ "to_char(END_TIME, 'yyyy-mm-dd hh:mi:ss') END_TIME,"
			+ " STAR, STAR_TOTAL, CRS_IMG FROM CRS where crs_no = ?";
	
	private static final String UPDATE = "UPDATE CRS SET COA_ID=?, CRS_TYPE_NO=?, CRS_NAME =?, CRS_FEE=?, CRS_CONTENT=?, START_DATE=?, END_DATE=?, WEEK_CRS=? ,"
			+ "START_TIME=?, END_TIME=?, CRS_IMG=? where crs_no = ?";

	private static final String GET_ALL_STMT = "SELECT CRS_NO, COA_ID, CRS_TYPE_NO, CRS_NAME, CRS_FEE, CRS_CONTENT,"
			+ " to_char(START_DATE, 'yyyy-mm-dd') START_DATE , to_char(END_DATE, 'yyyy-mm-dd') END_DATE, "
			+ " WEEK_CRS,  to_char(START_TIME, 'yyyy-mm-dd hh:mi:ss') START_TIME, "
			+ "to_char(END_TIME, 'yyyy-mm-dd hh:mi:ss') END_TIME,"
			+ " STAR, STAR_TOTAL, CRS_IMG FROM CRS order by crs_no";
	
	private static final String UPDATE_RESERVED ="UPDATE CRS SET RESERVED=? WHERE CRS_NO=?";
	
	private static final String FIND_RESERVED = "SELECT RESERVED FROM CRS WHERE CRS_NO=?";
	
	private static final String FIND_CRS ="SELECT CRS_NAME, START_DATE, END_DATE FROM CRS WHERE COA_ID=?";

	@Override
	public void insert(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, crsVO.getCoa_id());
			pstmt.setString(2, crsVO.getCrs_type_no());
			pstmt.setString(3, crsVO.getCrs_name());
			pstmt.setDouble(4, crsVO.getCrs_fee());
			pstmt.setString(5, crsVO.getCrs_content());
			pstmt.setDate(6, crsVO.getStart_date());
			pstmt.setDate(7, crsVO.getEnd_date());
			pstmt.setString(8, crsVO.getWeek_crs());
			pstmt.setTimestamp(9, crsVO.getStart_time());
			pstmt.setTimestamp(10, crsVO.getEnd_time());
			pstmt.setBytes(11, crsVO.getCrs_img());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, crsVO.getCoa_id());
			pstmt.setString(2, crsVO.getCrs_type_no());
			pstmt.setString(3, crsVO.getCrs_name());
			pstmt.setDouble(4, crsVO.getCrs_fee());
			pstmt.setString(5, crsVO.getCrs_content());
			pstmt.setDate(6, crsVO.getStart_date());
			pstmt.setDate(7, crsVO.getEnd_date());
			pstmt.setString(8, crsVO.getWeek_crs());
			pstmt.setTimestamp(9, crsVO.getStart_time());
			pstmt.setTimestamp(10, crsVO.getEnd_time());
			pstmt.setBytes(11, crsVO.getCrs_img());
			pstmt.setString(12, crsVO.getCrs_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String crs_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_CRS);

			pstmt.setString(1, crs_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public CrsVO findByPrimaryKey(String crs_no) {
		CrsVO CrsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, crs_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CrsVO = new CrsVO();
				CrsVO.setCrs_no(rs.getString("crs_no"));
				CrsVO.setCoa_id(rs.getString("coa_id"));
				CrsVO.setCrs_type_no(rs.getString("crs_type_no"));
				CrsVO.setCrs_name(rs.getString("crs_name"));
				CrsVO.setCrs_fee(rs.getInt("crs_fee"));
				CrsVO.setCrs_content(rs.getString("crs_content"));
				CrsVO.setStart_date(rs.getDate("start_date"));
				CrsVO.setEnd_date(rs.getDate("end_date"));
				CrsVO.setWeek_crs(weekCrsTransfer(rs.getString("week_crs")));
				CrsVO.setStart_time(rs.getTimestamp("start_time"));
				CrsVO.setEnd_time(rs.getTimestamp("end_time"));
				CrsVO.setStar(rs.getDouble("star"));
				CrsVO.setStar(rs.getDouble("star_total"));
				CrsVO.setCrs_img(rs.getBytes("crs_img"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return CrsVO;

	}

	@Override
	public List<CrsVO> getAll() {
		List<CrsVO> list = new ArrayList<CrsVO>();
		CrsVO crsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				crsVO = new CrsVO();
				crsVO.setCrs_no(rs.getString("crs_no"));
				crsVO.setCoa_id(rs.getString("coa_id"));
				crsVO.setCrs_type_no(rs.getString("crs_type_no"));
				crsVO.setCrs_name(rs.getString("crs_name"));
				crsVO.setCrs_fee(rs.getInt("crs_fee"));
				crsVO.setCrs_content(rs.getString("crs_content"));
				crsVO.setStart_date(rs.getDate("start_date"));
				crsVO.setEnd_date(rs.getDate("end_date"));
				crsVO.setWeek_crs(weekCrsTransfer(rs.getString("week_crs")));
				crsVO.setStart_time(rs.getTimestamp("start_time"));
				crsVO.setEnd_time(rs.getTimestamp("end_time"));
				crsVO.setStar(rs.getDouble("star"));
				crsVO.setStar(rs.getDouble("star_total"));
				crsVO.setCrs_img(rs.getBytes("crs_img"));
				list.add(crsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	

	@Override
	public void updateReserved(CrsVO crsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_RESERVED);

			pstmt.setInt(1, crsVO.getReserved());
			pstmt.setString(2, crsVO.getCrs_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public CrsVO findReserved(String crs_no) {
		CrsVO CrsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_RESERVED);

			pstmt.setString(1, crs_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				CrsVO = new CrsVO();
				CrsVO.setReserved(rs.getInt("reserved"));

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return CrsVO;
	}
	
	@Override
	public List<CrsVO> getAllCrs(String coa_id) {
		List<CrsVO> list = new ArrayList<CrsVO>();
		CrsVO crsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_CRS);

			pstmt.setString(1, coa_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				crsVO = new CrsVO();
				
				crsVO.setCrs_name(rs.getString("crs_name"));
				crsVO.setStart_date(rs.getDate("start_date"));
				crsVO.setEnd_date(rs.getDate("end_date"));
				
				list.add(crsVO); 
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) throws IOException {
//		byte[] pic = getPictureByteArray("WebContent/images/icon-0326.jpg");
//		byte[] pic2 = getPictureByteArray("WebContent/images/images.png");
//		String str = getLongString("WebContent/context/大悲咒.txt");
//		String str2 = getLongString("WebContent/context/般若波羅蜜.txt");
		JDBCCrsDAO dao = new JDBCCrsDAO();

		CrsVO crsVO = new CrsVO();
		// 新增
//		crsVO.setCoa_id("CO00001");
//		crsVO.setCrs_type_no("CT00001");
//		crsVO.setCrs_name("重量訓練");
//		crsVO.setCrs_fee(7000);
//		crsVO.setCrs_content("test");
//		crsVO.setStart_date(java.sql.Date.valueOf("2020-01-15"));
//		crsVO.setEnd_date(java.sql.Date.valueOf("2020-02-14"));
//		crsVO.setWeek_crs("0100011");
//		crsVO.setStart_time(java.sql.Timestamp.valueOf("2020-01-15 08:00:00"));
//		crsVO.setEnd_time(java.sql.Timestamp.valueOf("2020-02-14 10:00:00"));
//		crsVO.setCrs_img(null);
//
//		dao.insert(crsVO);
//		System.out.println("新增成功");
// ------------------------修改------------------------	
//		crsVO.setCrs_no("CN00001");
//		crsVO.setCoa_id("CO00002");
//		crsVO.setCrs_type_no("CT00001");
//		crsVO.setCrs_name("動感健康操");
//		crsVO.setCrs_fee(9000);
//		crsVO.setCrs_content("123");
//		crsVO.setStart_date(java.sql.Date.valueOf("2019-11-01"));
//		crsVO.setEnd_date(java.sql.Date.valueOf("2019-12-01"));
//		crsVO.setWeek_crs("1010111");
//		crsVO.setStart_time(java.sql.Timestamp.valueOf("2019-11-01 14:30:00"));
//		crsVO.setEnd_time(java.sql.Timestamp.valueOf("2019-12-01 17:30:00"));
//		crsVO.setStar(1.0);
//		crsVO.setStar_total(30.0);
//		crsVO.setCrs_img(null);
//
//		dao.update(crsVO);
		
//		crsVO.setReserved(7);
//		crsVO.setCrs_no("CN00005");
//		dao.updateReserved(crsVO);	
//		System.out.println("成功");
// ------------------------刪除------------------------

//		dao.delete("CN00010");

// ------------------------查詢------------------------
//		CrsVO crsVO1 = dao.findByPrimaryKey("CN00001");
//		System.out.println(crsVO1.getCrs_no());
//		System.out.println(crsVO1.getCoa_id());
//		System.out.println(crsVO1.getCrs_type_no());
//		System.out.println(crsVO1.getCrs_name());
//		System.out.println(crsVO1.getCrs_fee());
//		System.out.println(crsVO1.getCrs_content());
//		System.out.println(crsVO1.getStart_date());
//		System.out.println(crsVO1.getEnd_date());
//		System.out.println(weekCrsTransfer(crsVO1.getWeek_crs()));
//		System.out.println(crsVO1.getStart_time());
//		System.out.println(crsVO1.getEnd_time());
//		System.out.println(crsVO1.getStar());
//		System.out.println(crsVO1.getStar_total());
//		System.out.println(crsVO1.getCrs_img());
		
//		CrsVO crsVO1 = dao.findReserved("CN00007");
//		System.out.println(crsVO1.getReserved());

// ------------------------查詢全部------------------------

//		List<CrsVO> list = dao.getAll();
//		for (CrsVO aCrs : list) {
//			System.out.print(aCrs.getCrs_no() + ",");
//			System.out.print(aCrs.getCoa_id() + ",");
//			System.out.print(aCrs.getCrs_type_no() + ",");
//			System.out.print(aCrs.getCrs_name() + ",");
//			System.out.print(aCrs.getCrs_fee() + ",");
//			System.out.print(aCrs.getCrs_content() + ",");
//			System.out.print(aCrs.getStart_date() + ",");
//			System.out.print(aCrs.getEnd_date() + ",");
//			System.out.print(weekCrsTransfer(aCrs.getWeek_crs())+ ",");
//			System.out.print(aCrs.getStart_time() + ",");
//			System.out.print(aCrs.getEnd_time() + ",");
//			System.out.print(aCrs.getStar() + ",");
//			System.out.print(aCrs.getStar_total() + ",");
//			System.out.print(aCrs.getCrs_img());
//			System.out.println();
//		}
		
		List<CrsVO> list = dao.getAllCrs("CO00001");
		for (CrsVO aCrs : list) {
			System.out.print(aCrs.getCrs_name() + ",");
			System.out.print(aCrs.getStart_date() + ",");
			System.out.print(aCrs.getEnd_date() + ",");

			System.out.println();
		}
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

	public static String weekCrsTransfer(String week_crs) {
		String weekCrs = week_crs;
		String date[] = new String[7];
		int index = 0;
		int j = 1;

		String anwser = "";

		for (int i = 0; i < 7; i++) {
			date[index] = weekCrs.substring(i, j);

			index++;
			j++;
		}

		if (date[0].equals("1")) {
			anwser += "一";
		}
		if (date[1].equals("1")) {
			anwser += "二";
		}
		if (date[2].equals("1")) {
			anwser += "三";
		}
		if (date[3].equals("1")) {
			anwser += "四";
		}
		if (date[4].equals("1")) {
			anwser += "五";
		}
		if (date[5].equals("1")) {
			anwser += "六";
		}
		if (date[6].equals("1")) {
			anwser += "日";
		}

		return anwser;

	}


	

	
}
