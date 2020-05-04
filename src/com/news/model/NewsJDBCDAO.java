package com.news.model;

import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewsJDBCDAO implements News_DAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO NEWS(NEWS_ID,NEWS_TITLE,NEWS_INFO,NEWS_PIC,NEWS_TIME)"
			+ "VALUES('NW'||LPAD(TO_CHAR(NEWS_SEQ.NEXTVAL),5,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM NEWS";
	private static final String GET_ONE_STMT = "SELECT NEWS_ID, NEWS_TITLE,NEWS_INFO,NEWS_PIC,NEWS_TIME FROM NEWS WHERE NEWS_ID=?";
	private static final String DELETE = "DELETE FROM NEWS WHERE NEWS_ID=?";
	private static final String UPDATE = "UPDATE  NEWS SET NEWS_TITLE=?,NEWS_INFO=?,NEWS_PIC=?,NEWS_TIME=? WHERE NEWS_ID=?";
	private static final String UPDATE1 = "UPDATE  NEWS SET NEWS_TITLE=?,NEWS_INFO=?,NEWS_TIME=? WHERE NEWS_ID=?";
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,newsVO.getNews_title());
			pstmt.setString(2,newsVO.getNews_info());
			pstmt.setBytes(3,newsVO.getNews_pic());
			pstmt.setTimestamp(4, newsVO.getNews_time());
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
	public void update_nopicture(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE1);
			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2,newsVO.getNews_info());
			pstmt.setTimestamp(3, newsVO.getNews_time());
			pstmt.setString(4,newsVO.getNews_id());
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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2,newsVO.getNews_info());
			pstmt.setBytes(3, newsVO.getNews_pic());
			pstmt.setTimestamp(4, newsVO.getNews_time());
			pstmt.setString(5,newsVO.getNews_id());
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
	public void delete(String news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, news_id);

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
	public NewsVO findPrimaryKey(String news_id) {
		NewsVO newsVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, news_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				newsVO=new NewsVO();
				newsVO.setNews_id(rs.getString("news_id"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_info(rs.getString("news_info"));
				newsVO.setNews_pic(rs.getBytes("news_pic"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
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
		return newsVO;
		

	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list=new ArrayList<NewsVO>();
		NewsVO newsVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				newsVO=new NewsVO();
				newsVO.setNews_id(rs.getString("news_id"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_info(rs.getString("news_info"));
				newsVO.setNews_pic(rs.getBytes("news_pic"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
				list.add(newsVO);
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

	public static void main(String[] args) throws IOException {
		byte[] pic = getPictureByteArray("WebContent/image/newsP1.jpg");
//		String str = getLongString("WebContent/item/最新消息.txt");
		NewsJDBCDAO dao=new NewsJDBCDAO();
		//新增
		NewsVO newsVO1=new NewsVO();
		
		newsVO1.setNews_title("好康分享");
		newsVO1.setNews_info("好禮大放送");
		newsVO1.setNews_pic(pic);
		newsVO1.setNews_time(java.sql.Timestamp.valueOf("2019-10-07 14:00:00"));
		dao.insert(newsVO1);
		System.out.println("新增成功");
		//刪除
//		dao.delete("NW00001");
//		System.out.println("刪除成功");
		//查詢
//		List<NewsVO>list=dao.getAll();
//		for(NewsVO news:list) {
//			System.out.println(news.getNews_id()+",");
//			System.out.println(news.getNews_title()+",");
//			System.out.println(news.getNews_info()+",");
//			System.out.println(news.getNews_pic()+",");
//			System.out.println(news.getNews_time());
//			System.out.println();
//		}
		//修改
//		NewsVO newsVO3=new NewsVO();
//		newsVO3.setNews_title("狂抽猛送");
//		newsVO3.setNews_info(str);
//		newsVO3.setNews_pic(pic);
//		newsVO3.setNews_time(java.sql.Timestamp.valueOf("2019-10-11 13:30:30"));
//		newsVO3.setNews_id("NW00003");
//		dao.update(newsVO3);
//		System.out.println("修改成功");
//		//PK查詢
//		NewsVO newsVO2=dao.findPrimaryKey("NW00003");
//		System.out.println(newsVO2.getNews_title()+"\t");
//		System.out.println(newsVO2.getNews_info()+"\t");
//		System.out.println(newsVO2.getNews_pic()+"\t");
//		System.out.println(newsVO2.getNews_time());
//		System.out.println();
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
