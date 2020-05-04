package com.friendListOLD.model;

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

public class FriendListDAO implements FriendListDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,friendListVO.getMem_id_1());
			pstmt.setString(2,friendListVO.getMem_id_2());
			pstmt.setString(3, friendListVO.getFrd_memo());
			pstmt.setString(4,friendListVO.getFrd_add());
			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, friendListVO.getFrd_memo());
			pstmt.setString(2, friendListVO.getFrd_add());
			pstmt.setString(3, friendListVO.getMem_id_1());
			pstmt.setString(4, friendListVO.getMem_id_2());
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
	public void delete(String mem_id_1,String mem_id_2) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,mem_id_1);
			pstmt.setString(2, mem_id_2);
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
	public FriendListVO findPrimaryKey(String mem_id_1,String mem_id_2) {
		FriendListVO friendListVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,mem_id_1);
			pstmt.setString(2, mem_id_2);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				friendListVO=new FriendListVO();
				friendListVO.setFrd_memo(rs.getString("frd_memo"));
				friendListVO.setFrd_add(rs.getString("frd_add"));
				
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
			con = ds.getConnection();
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
		return list;
	}
	
}
