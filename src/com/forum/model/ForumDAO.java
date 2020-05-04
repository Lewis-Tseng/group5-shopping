package com.forum.model;

import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.forum_message.model.Forum_messageVO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;

public class ForumDAO implements ForumDAO_interface {
	
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

	private static final String INSERT_STMT = 
			"INSERT INTO forum (FORUM_ID,MEM_ID,FORUM_CLS_ID,FORUM_TITLE,FORUM_INFO,FORUM_TIME,FORUM_PIC,FORUM_HIT,FORUM_STAT,FORUM_LIKE,FORUM_DISLIKE) VALUES ('FO'||LPAD(TO_CHAR(FORUM_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";                       
	private static final String INSERT_STMT2 = //前台使用者創立文章
			"INSERT INTO forum (FORUM_ID,MEM_ID,FORUM_CLS_ID,FORUM_TITLE,FORUM_INFO,FORUM_TIME,FORUM_PIC,FORUM_HIT,FORUM_STAT,FORUM_LIKE,FORUM_DISLIKE) VALUES ('FO'||LPAD(TO_CHAR(FORUM_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, 0, 1, 0, 0)";
	private static final String GET_ALL_STMT = 
			"SELECT FORUM_ID,MEM_ID,FORUM_CLS_ID,FORUM_TITLE,FORUM_INFO,FORUM_TIME,FORUM_PIC,FORUM_HIT,FORUM_STAT,FORUM_LIKE,FORUM_DISLIKE FROM forum order by FORUM_ID"; 
	//遊覽數+1
	private static final String SET_ONE_HIT = 
			"UPDATE forum set FORUM_HIT=? where FORUM_ID=?";
	private static final String GET_ONE_HIT = 
			"SELECT FORUM_HIT FROM forum where FORUM_ID = ?";
	private static final String GET_ONE_STMT = 
			"SELECT FORUM_ID,MEM_ID,FORUM_CLS_ID,FORUM_TITLE,FORUM_INFO,FORUM_TIME,FORUM_PIC,FORUM_HIT,FORUM_STAT,FORUM_LIKE,FORUM_DISLIKE FROM forum where FORUM_ID = ?";
	private static final String GET_MSG_BY_FORUM =  //前台用文章找出留言
			"SELECT FORUM_MSG_ID,MEM_ID,FORUM_ID,FORUM_MSG_TIME,FORUM_MSG_INFO,FORUM_MSG_PIC,FORUM_MSG_STAT,FORUM_MSG_LIKE,FORUM_MSG_DISLIKE FROM forum_message where FORUM_ID = ? order by FORUM_MSG_ID";
	private static final String DELETE_FORUM_ID = 
			"DELETE FROM forum where FORUM_ID=?";
	private static final String UPDATE = 
			"UPDATE forum set MEM_ID=? ,FORUM_CLS_ID=? ,FORUM_TITLE=? ,FORUM_INFO=? ,FORUM_TIME=? ,FORUM_PIC=? ,FORUM_HIT=? ,FORUM_STAT=? ,FORUM_LIKE=? ,FORUM_DISLIKE=?  where FORUM_ID=?";
	private static final String UPDATE2 =   //後臺管理者更改狀態
			"UPDATE forum set FORUM_STAT=? where FORUM_ID=?";
	private static final String UPDATE3 =   //前台使用者修改文章
			"UPDATE forum set FORUM_CLS_ID=? ,FORUM_TITLE=? ,FORUM_INFO=? ,FORUM_PIC=? Where FORUM_ID=?";
	private static final String UPDATE3_NO_PIC =   //前台使用者修改文章 未更改圖片時
			"UPDATE forum set FORUM_CLS_ID=? ,FORUM_TITLE=? ,FORUM_INFO=? Where FORUM_ID=?";

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
	
	@Override
	public void insert(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			Date date = new Date();       
			Timestamp Time = new Timestamp(date.getTime());
			pstmt.setString(1, forumVO.getMem_id());
			pstmt.setString(2, forumVO.getForum_cls_id());
			pstmt.setString(3, forumVO.getForum_title());
			pstmt.setString(4, forumVO.getForum_info());   //
			pstmt.setTimestamp(5, Time);  
			pstmt.setBytes(6, forumVO.getForum_pic());  //
			pstmt.setInt(7, forumVO.getForum_hit());
			pstmt.setString(8, forumVO.getForum_stat());
			pstmt.setInt(9, forumVO.getForum_like());
			pstmt.setInt(10, forumVO.getForum_dislike());
			
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
//	前台使用者創立文章
	@Override
	public void insert2(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);
			Date date = new Date();       
			Timestamp Time = new Timestamp(date.getTime());
			pstmt.setString(1, forumVO.getMem_id());
			pstmt.setString(2, forumVO.getForum_cls_id());
			pstmt.setString(3, forumVO.getForum_title());
			pstmt.setString(4, forumVO.getForum_info());
			pstmt.setTimestamp(5, Time);  
			pstmt.setBytes(6, forumVO.getForum_pic());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void update(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, forumVO.getMem_id());
			pstmt.setString(2, forumVO.getForum_cls_id());
			pstmt.setString(3, forumVO.getForum_title());
			pstmt.setString(4, forumVO.getForum_info());
			pstmt.setTimestamp(5, forumVO.getForum_time());
			pstmt.setBytes(6, forumVO.getForum_pic());
			pstmt.setInt(7, forumVO.getForum_hit());
			pstmt.setString(8, forumVO.getForum_stat());
			pstmt.setInt(9, forumVO.getForum_like());
			pstmt.setInt(10, forumVO.getForum_dislike());
			pstmt.setString(11, forumVO.getForum_id());
			pstmt.executeUpdate();


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
	@Override //後台改變文章狀態
	public void update2(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
			
			pstmt.setString(1, forumVO.getForum_stat());
			pstmt.setString(2, forumVO.getForum_id());
			pstmt.executeUpdate();
			// Handle any driver errors

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
	@Override //前台使用者修改文章
	public void update3(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		
			con = ds.getConnection();
			if(forumVO.getForum_pic().length ==0) {
				pstmt = con.prepareStatement(UPDATE3_NO_PIC);
				pstmt.setString(1, forumVO.getForum_cls_id());
				pstmt.setString(2, forumVO.getForum_title());
				pstmt.setString(3, forumVO.getForum_info());
				pstmt.setString(4, forumVO.getForum_id());
			}else {
				pstmt = con.prepareStatement(UPDATE3);
				pstmt.setString(1, forumVO.getForum_cls_id());
				pstmt.setString(2, forumVO.getForum_title());
				pstmt.setString(3, forumVO.getForum_info());
				pstmt.setBytes(4, forumVO.getForum_pic());
				pstmt.setString(5, forumVO.getForum_id());
			}
			
			pstmt.executeUpdate();
			// Handle any driver errors

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
	public void delete(String forum_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FORUM_ID);
			pstmt.setString(1, forum_id);
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
//查單一文章
	@Override
	public ForumVO findByPrimaryKey(String forum_id) {
		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	   //查單一文章	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, forum_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forumVO = new ForumVO();
			
				//查詢文章資料
				forumVO.setForum_id(rs.getString("forum_id"));
				forumVO.setMem_id(rs.getString("mem_id"));
				forumVO.setForum_cls_id(rs.getString("forum_cls_id"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_info(rs.getString("forum_info"));
				forumVO.setForum_time(rs.getTimestamp("forum_time"));
				forumVO.setForum_pic(rs.getBytes("forum_pic"));
				forumVO.setForum_hit(rs.getInt("forum_hit"));
				forumVO.setForum_stat(rs.getString("forum_stat"));
				forumVO.setForum_like(rs.getInt("forum_like"));
				forumVO.setForum_dislike(rs.getInt("forum_dislike"));
			}
			// Handle any driver errors

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
		return forumVO;
		
	}

	@Override
	public List<ForumVO> getAll() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				forumVO = new ForumVO();
				forumVO.setForum_id(rs.getString("forum_id"));
				forumVO.setMem_id(rs.getString("mem_id"));
				forumVO.setForum_cls_id(rs.getString("forum_cls_id"));
				forumVO.setForum_title(rs.getString("forum_title"));
				forumVO.setForum_info(rs.getString("forum_info"));
				forumVO.setForum_time(rs.getTimestamp("forum_time"));
				forumVO.setForum_pic(rs.getBytes("forum_pic"));
				forumVO.setForum_hit(rs.getInt("forum_hit"));
				forumVO.setForum_stat(rs.getString("forum_stat"));
				forumVO.setForum_like(rs.getInt("forum_like"));
				forumVO.setForum_dislike(rs.getInt("forum_dislike"));
				list.add(forumVO); // Store the row in the list
			}
			// Handle any driver errors
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
//前台 用文章ID列出留言
	@Override
	public Set<Forum_messageVO> getMsgByForum(String forum_id) {
		Set<Forum_messageVO> set = new LinkedHashSet<Forum_messageVO>();
		Forum_messageVO forum_messageVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MSG_BY_FORUM);
			pstmt.setString(1, forum_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
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
				set.add(forum_messageVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
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
	//查HIT數量
	@Override
	public Integer getOneHit(String forum_id) {
		
		ForumVO forumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer hit = null;
	   //查HIT數量	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_HIT);
			
			pstmt.setString(1, forum_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
			
			hit = rs.getInt("forum_hit");
			}
			// Handle any driver errors

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
		return hit;
	}
	//增加hit數量
	@Override
	public void setOneHit(String forum_id, Integer forum_hit) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		
			con = ds.getConnection();
				
				pstmt = con.prepareStatement(SET_ONE_HIT);
				pstmt.setInt(1, forum_hit);
				pstmt.setString(2, forum_id);
		

			
			pstmt.executeUpdate();
			// Handle any driver errors

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
}