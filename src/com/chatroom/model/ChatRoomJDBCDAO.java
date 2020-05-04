package com.chatroom.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomJDBCDAO implements ChatRoomDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ChatRoomVO chatroomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String chat_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chat_id);
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
	public ChatRoomVO findPrimaryKey(String chat_id) {
		ChatRoomVO chatroomVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
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
		String str = getLongString("WebContent/item/最新消息.txt");
		ChatRoomJDBCDAO dao=new ChatRoomJDBCDAO();
		ChatRoomVO chatVO=new ChatRoomVO();
		chatVO.setMem_id_1("ME00001");
		chatVO.setMem_id_2("ME00002");
		chatVO.setCoa_id("CO00001");
		chatVO.setChat_info("你好");
		chatVO.setChat_info2("你好");
		chatVO.setChat_stat("已讀");
		chatVO.setChat_stat2("未已讀");
		dao.insert(chatVO);
		System.out.println("新增成功");
		//修改
		ChatRoomVO chatVO1=new ChatRoomVO();
		chatVO1.setMem_id_1("ME00001");
		chatVO1.setMem_id_2("ME00003");
		chatVO1.setCoa_id("CO00001");
		chatVO1.setChat_info("你好");
		chatVO1.setChat_info2(str);
		chatVO1.setChat_stat("已讀");
		chatVO1.setChat_stat2("未已讀");
		chatVO1.setChat_id("CH00001");
		dao.update(chatVO1);
		System.out.println("修改成功");
		//刪除
		dao.delete("CH00004");
		System.out.println("刪除成功");
		//查詢
		ChatRoomVO chat=dao.findPrimaryKey("CH00003");
		System.out.println(chat.getMem_id_1());
		System.out.println(chat.getMem_id_2());
		System.out.println(chat.getCoa_id());
		System.out.println(chat.getChat_info());
		System.out.println(chat.getChat_info2());
		System.out.println(chat.getChat_stat());
		System.out.println(chat.getChat_stat2());
		List<ChatRoomVO>chatVO2=dao.getAll();
		for(ChatRoomVO chat1:chatVO2 ) {
			System.out.print(chat1.getChat_id()+",");
			System.out.print(chat1.getMem_id_1()+",");
			System.out.print(chat1.getMem_id_2()+",");
			System.out.print(chat1.getCoa_id()+",");
			System.out.print(chat1.getChat_info()+",");
			System.out.print(chat1.getChat_info2()+",");
			System.out.print(chat1.getChat_stat()+",");
			System.out.print(chat1.getChat_stat2());
			System.out.println();
		}
		
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

