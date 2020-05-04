package com.chatroom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChatRoomDAO implements ChatRoomDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO CHAT_ROOM(CHAT_ID,MEM_ID_1,MEM_ID_2,COA_ID,CHAT_INFO,CHAT_INFO2,CHAT_STAT,CHAT_STAT2)"
			+ "VALUES('CH'||LPAD(TO_CHAR(NEWS_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CHAT_ROOM";
	private static final String GET_ONE_STMT = "SELECT MEM_ID_1,MEM_ID_2,COA_ID,CHAT_INFO,CHAT_INFO2,CHAT_STAT,CHAT_STAT2 FROM CHAT_ROOM WHERE CHAT_ID=?";
	private static final String DELETE = "DELETE FROM CHAT_ROOM WHERE CHAT_ID=?";
	private static final String UPDATE = "UPDATE CHAT_ROOM SET MEM_ID_1=?,MEM_ID_2=?,COA_ID=?,CHAT_INFO=?,CHAT_INFO2=?,CHAT_STAT=?,CHAT_STAT2=? WHERE CHAT_ID=?";

	@Override
	public void insert(ChatRoomVO chatroomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,chatroomVO.getMem_id_1());
			pstmt.setString(2,chatroomVO.getMem_id_2());
			pstmt.setString(3,chatroomVO.getCoa_id());
			pstmt.setString(4,chatroomVO.getChat_info());
			pstmt.setString(5,chatroomVO.getChat_info2());
			pstmt.setString(6,chatroomVO.getChat_stat());
			pstmt.setString(7,chatroomVO.getChat_stat2());
			pstmt.executeUpdate();

			// Handle any driver errorst
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
	public void update(ChatRoomVO chatroomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, chatroomVO.getMem_id_1());
			pstmt.setString(2, chatroomVO.getMem_id_2());
			pstmt.setString(3, chatroomVO.getCoa_id());
			pstmt.setString(4, chatroomVO.getChat_info());
			pstmt.setString(5, chatroomVO.getChat_info2());
			pstmt.setString(6, chatroomVO.getChat_stat());
			pstmt.setString(7, chatroomVO.getChat_stat2());
			pstmt.setString(8, chatroomVO.getChat_id());
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
	public void delete(String chat_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chat_id);
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
	public ChatRoomVO findPrimaryKey(String chat_id) {
		ChatRoomVO chatroomVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, chat_id);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				chatroomVO=new ChatRoomVO();
				chatroomVO.setMem_id_1(rs.getString("mem_id_1"));
				chatroomVO.setMem_id_2(rs.getString("mem_id_2"));
				chatroomVO.setCoa_id(rs.getString("coa_id"));
				chatroomVO.setChat_info(rs.getString("chat_info"));
				chatroomVO.setChat_info2(rs.getString("chat_info2"));
				chatroomVO.setChat_stat(rs.getString("chat_stat"));
				chatroomVO.setChat_stat2(rs.getString("chat_stat2"));
				
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
		return chatroomVO;
	}

	@Override
	public List<ChatRoomVO> getAll() {
		List<ChatRoomVO> list=new ArrayList<ChatRoomVO>();
		ChatRoomVO chatroomVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				chatroomVO=new ChatRoomVO();
				chatroomVO.setChat_id(rs.getString("chat_id"));
				chatroomVO.setMem_id_1(rs.getString("mem_id_1"));
				chatroomVO.setMem_id_2(rs.getString("mem_id_2"));
				chatroomVO.setCoa_id(rs.getString("coa_id"));
				chatroomVO.setChat_info(rs.getString("chat_info"));
				chatroomVO.setChat_info2(rs.getString("chat_info2"));
				chatroomVO.setChat_stat(rs.getString("chat_stat"));
				chatroomVO.setChat_stat2(rs.getString("chat_stat2"));
				list.add(chatroomVO);
			}
			
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

}
