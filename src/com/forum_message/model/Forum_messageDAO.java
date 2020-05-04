package com.forum_message.model;

import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

public class Forum_messageDAO implements Forum_messageDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	前台使用者 新增留言
	private static final String INSERT_STMT2 = 
			"INSERT INTO forum_message(FORUM_MSG_ID,MEM_ID,FORUM_ID,FORUM_MSG_TIME,FORUM_MSG_INFO,FORUM_MSG_PIC,FORUM_MSG_STAT,FORUM_MSG_LIKE,FORUM_MSG_DISLIKE) VALUES ('FM'||LPAD(TO_CHAR(FORUM_MESSAGE_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, 1, 0, 0)";       	
	private static final String INSERT_STMT = 
			"INSERT INTO forum_message(FORUM_MSG_ID,MEM_ID,FORUM_ID,FORUM_MSG_TIME,FORUM_MSG_INFO,FORUM_MSG_PIC,FORUM_MSG_STAT,FORUM_MSG_LIKE,FORUM_MSG_DISLIKE) VALUES ('FM'||LPAD(TO_CHAR(FORUM_MESSAGE_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";                       
	private static final String GET_ALL_STMT = 
			"SELECT FORUM_MSG_ID,MEM_ID,FORUM_ID,FORUM_MSG_TIME,FORUM_MSG_INFO,FORUM_MSG_PIC,FORUM_MSG_STAT,FORUM_MSG_LIKE,FORUM_MSG_DISLIKE FROM forum_message order by FORUM_MSG_ID"; 
	private static final String GET_ONE_STMT = 
			"SELECT FORUM_MSG_ID,MEM_ID,FORUM_ID,FORUM_MSG_TIME,FORUM_MSG_INFO,FORUM_MSG_PIC,FORUM_MSG_STAT,FORUM_MSG_LIKE,FORUM_MSG_DISLIKE FROM forum_message where FORUM_MSG_ID=?";
	private static final String DELETE_FORUM_MSG_ID = 
			"DELETE FROM forum_message where FORUM_MSG_ID=?";
//前台使用者編輯留言
	private static final String UPDATE2 =
			"UPDATE forum_message set FORUM_MSG_INFO=? ,FORUM_MSG_PIC=? where FORUM_MSG_ID=?";
//前台使用者刪除留言(實為下架)
	private static final String UPDATE3 =
			"UPDATE forum_message set FORUM_MSG_STAT=? where FORUM_MSG_ID=?";
	private static final String UPDATE = 
			"UPDATE forum_message set MEM_ID=? ,FORUM_ID=? ,FORUM_MSG_TIME=? ,FORUM_MSG_INFO=? ,FORUM_MSG_PIC=? ,FORUM_MSG_STAT=? ,FORUM_MSG_LIKE=? ,FORUM_MSG_DISLIKE=?  where FORUM_MSG_ID=?";

	public static Reader getLongStringStream(String path) throws IOException {
		return new FileReader(path);

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
	
//前台使用者 新增留言
	@Override
	public void insert2(Forum_messageVO forum_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);
			Date date = new Date();       
			Timestamp Time = new Timestamp(date.getTime());
			pstmt.setString(1, forum_messageVO.getMem_id());
			pstmt.setString(2, forum_messageVO.getForum_id());
			pstmt.setTimestamp(3, Time);
			pstmt.setString(4, forum_messageVO.getForum_msg_info());   //
			pstmt.setBytes(5, forum_messageVO.getForum_msg_pic());  //
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
	public void insert(Forum_messageVO forum_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, forum_messageVO.getMem_id());
			pstmt.setString(2, forum_messageVO.getForum_id());
			pstmt.setTimestamp(3, forum_messageVO.getForum_msg_time());
			pstmt.setString(4, forum_messageVO.getForum_msg_info());   //
			pstmt.setBytes(5, forum_messageVO.getForum_msg_pic());  //
			pstmt.setString(6, forum_messageVO.getForum_msg_stat());
			pstmt.setInt(7, forum_messageVO.getForum_msg_like());
			pstmt.setInt(8, forum_messageVO.getForum_msg_dislike());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
//前台使用者編輯留言
	@Override
	public void update2(Forum_messageVO forum_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
	
			pstmt.setString(1, forum_messageVO.getForum_msg_info());   
			pstmt.setBytes(2, forum_messageVO.getForum_msg_pic());  
			pstmt.setString(3, forum_messageVO.getForum_msg_id());
			pstmt.executeUpdate();
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
//前台使用者刪除留言 (實為下架)
		@Override
		public void update3(Forum_messageVO forum_messageVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE3);
		
				pstmt.setString(1, forum_messageVO.getForum_msg_stat());   
				pstmt.setString(2, forum_messageVO.getForum_msg_id());
				pstmt.executeUpdate();
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
	public void update(Forum_messageVO forum_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, forum_messageVO.getMem_id());
			pstmt.setString(2, forum_messageVO.getForum_id());
			pstmt.setTimestamp(3, forum_messageVO.getForum_msg_time());
			pstmt.setString(4, forum_messageVO.getForum_msg_info());   //
			pstmt.setBytes(5, forum_messageVO.getForum_msg_pic());  //
			pstmt.setString(6, forum_messageVO.getForum_msg_stat());
			pstmt.setInt(7, forum_messageVO.getForum_msg_like());
			pstmt.setInt(8, forum_messageVO.getForum_msg_dislike());
			pstmt.setString(9, forum_messageVO.getForum_msg_id());
			pstmt.executeUpdate();
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
	public void delete(String forum_msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FORUM_MSG_ID);
			pstmt.setString(1, forum_msg_id);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
	
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
	public Forum_messageVO findByPrimaryKey(String forum_msg_id) {
		Forum_messageVO forum_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, forum_msg_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 銋迂� Domain objects
				forum_messageVO = new Forum_messageVO();
				forum_messageVO.setForum_msg_id(rs.getString("forum_msg_id"));
				forum_messageVO.setMem_id(rs.getString("mem_id"));
				forum_messageVO.setForum_id(rs.getString("forum_id"));
				forum_messageVO.setForum_msg_time(rs.getTimestamp("forum_msg_time"));
				forum_messageVO.setForum_msg_info(rs.getString("forum_msg_info"));
				forum_messageVO.setForum_msg_pic(rs.getBytes("forum_msg_pic"));
				forum_messageVO.setForum_msg_stat(rs.getString("forum_msg_stat"));
				forum_messageVO.setForum_msg_like(rs.getInt("forum_msg_like"));
				forum_messageVO.setForum_msg_dislike(rs.getInt("forum_msg_dislike"));
			}
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
		return forum_messageVO;
	}

	@Override
	public List<Forum_messageVO> getAll() {
		List<Forum_messageVO> list = new ArrayList<Forum_messageVO>();
		Forum_messageVO forum_messsageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				forum_messsageVO = new Forum_messageVO();
				forum_messsageVO.setForum_msg_id(rs.getString("forum_msg_id"));
				forum_messsageVO.setMem_id(rs.getString("mem_id"));
				forum_messsageVO.setForum_id(rs.getString("forum_id"));
				forum_messsageVO.setForum_msg_time(rs.getTimestamp("forum_msg_time"));
				forum_messsageVO.setForum_msg_info(rs.getString("forum_msg_info"));
				forum_messsageVO.setForum_msg_pic(rs.getBytes("forum_msg_pic"));
				forum_messsageVO.setForum_msg_stat(rs.getString("forum_msg_stat"));
				forum_messsageVO.setForum_msg_like(rs.getInt("forum_msg_like"));
				forum_messsageVO.setForum_msg_dislike(rs.getInt("forum_msg_dislike"));
				list.add(forum_messsageVO); // Store the row in the list
			}
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

}