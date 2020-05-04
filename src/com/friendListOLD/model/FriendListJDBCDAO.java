package com.friendListOLD.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FriendListJDBCDAO implements FriendListDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA103G5";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO FRIEND_LIST (MEM_ID_1,MEM_ID_2,FRD_MEMO,FRD_ADD) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FRIEND_LIST";
	private static final String GET_ONE_STMT = "SELECT FRD_MEMO,FRD_ADD FROM FRIEND_LIST WHERE MEM_ID_1=? AND MEM_ID_2=?";
	private static final String DELETE = "DELETE FROM FRIEND_LIST WHERE MEM_ID_1=? AND MEM_ID_2=?";
	private static final String UPDATE = "UPDATE FRIEND_LIST SET FRD_MEMO=?,FRD_ADD=? " + "WHERE MEM_ID_1=? AND MEM_ID_2=?";

	@Override
	public void insert(FriendListVO friendListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,friendListVO.getMem_id_1());
			pstmt.setString(2,friendListVO.getMem_id_2());
			pstmt.setString(3, friendListVO.getFrd_memo());
			pstmt.setString(4,friendListVO.getFrd_add());
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
	public void update(FriendListVO friendListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, friendListVO.getFrd_memo());
			pstmt.setString(2, friendListVO.getFrd_add());
			pstmt.setString(3, friendListVO.getMem_id_1());
			pstmt.setString(4, friendListVO.getMem_id_2());
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
	public void delete(String mem_id_1,String mem_id_2) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,mem_id_1);
			pstmt.setString(2, mem_id_2);
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
	public FriendListVO findPrimaryKey(String mem_id_1,String mem_id_2) {
		FriendListVO friendListVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,mem_id_1);
			pstmt.setString(2, mem_id_2);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				friendListVO=new FriendListVO();
				friendListVO.setFrd_memo(rs.getString("frd_memo"));
				friendListVO.setFrd_add(rs.getString("frd_add"));
				
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
		return friendListVO;
	}

	@Override
	public List<FriendListVO> getAll() {
		List<FriendListVO> list=new ArrayList<FriendListVO>();
		FriendListVO friendListVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				friendListVO=new FriendListVO();
				friendListVO.setMem_id_1(rs.getString("mem_id_1"));
				friendListVO.setMem_id_2(rs.getString("mem_id_2"));
				friendListVO.setFrd_memo(rs.getString("frd_memo"));
				friendListVO.setFrd_add(rs.getString("frd_add"));
				list.add(friendListVO);
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
	public static void main(String[] args) {
		FriendListJDBCDAO dao=new FriendListJDBCDAO();
		//新增
//		FriendListVO friendListVO=new FriendListVO();
//		friendListVO.setMem_id_1("ME00002");
//		friendListVO.setMem_id_2("ME00003");
//		friendListVO.setFrd_memo("家人");
//		friendListVO.setFrd_add("已加入");
//		dao.insert(friendListVO);
//		System.out.println("新增成功");
		//修改
//		FriendListVO friendListVO2=new FriendListVO();
//		friendListVO2.setFrd_memo("朋友");
//		friendListVO2.setFrd_add("未加入");
//		friendListVO2.setMem_id_1("ME00001");
//		friendListVO2.setMem_id_2("ME00003");
//		dao.update(friendListVO2);
//		System.out.println("修改成功");
		//查詢
		FriendListVO friendList=dao.findPrimaryKey("ME00001", "ME00002");
		System.out.println(friendList.getFrd_memo());
		System.out.println(friendList.getFrd_add());
//		List<FriendListVO> list=dao.getAll();
//		for(FriendListVO friendList:list) {
//			System.out.print(friendList.getMem_id_1()+",");
//			System.out.print(friendList.getMem_id_2()+",");
//			System.out.print(friendList.getFrd_memo()+",");
//			System.out.print(friendList.getFrd_add());
//			System.out.println();
//		}
		
	}
}
