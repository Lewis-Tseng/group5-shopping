package com.mem.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemVO;

import com.mem.model.MemDAO_interface;

public class MemDAO implements MemDAO_interface{
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
			"INSERT INTO MEMBER(mem_id,mem_name,mem_gender,mem_email,mem_psw,phone,address,pos_code,birthday,mem_pic,mem_sta,mem_point) VALUES(('ME'||LPAD(to_char(MEMBER_seq.NEXTVAL),5,'0')), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mem_id,mem_name,mem_gender,mem_email,mem_psw,phone,address,pos_code,to_char(birthday,'yyyy-mm-dd') birthday,mem_pic,mem_sta,mem_point FROM MEMBER order by mem_id";
		private static final String GET_ONE_STMT = 
			"SELECT mem_id,mem_name,mem_gender,mem_email,mem_psw,phone,address,pos_code,to_char(birthday,'yyyy-mm-dd') birthday,mem_pic,mem_sta,mem_point FROM MEMBER where mem_id = ?";
		private static final String DELETE = 
			"DELETE FROM MEMBER where mem_id = ?";
		private static final String UPDATE = 
			"UPDATE MEMBER set mem_name=?, mem_gender=?, mem_email=?, mem_psw=?, phone=?, address=?, pos_code=?,  birthday=?, mem_pic=?, mem_sta=?,mem_point=? where mem_id = ?";
		private static final String UPDATE_MemPer = 
			"UPDATE MEMBER set mem_name=?, phone=?, address=?, pos_code=?,  birthday=?, mem_pic=? where mem_email = ?";
		private static final String UPDATE_MemPer_NoChangePIC = 
				"UPDATE MEMBER set mem_name=?, phone=?, address=?, pos_code=?,  birthday=? where mem_email = ?";
		private static final String GET_ONE_PSW = 
				"SELECT mem_psw FROM MEMBER where mem_email = ?";
		private static final String GET_ONE_STMT_BY_EMAIL = 
				"SELECT mem_id,mem_name,mem_gender,mem_email,mem_psw,phone,address,pos_code,to_char(birthday,'yyyy-mm-dd') birthday,mem_pic,mem_sta,mem_point FROM MEMBER where mem_email = ?";
		private static final String UPDATEPOINT =
				"UPDATE member set mem_point = ? where mem_id = ?";
		private static final String INSERT_STMT2 = 
				"INSERT INTO MEMBER(mem_id,mem_name,mem_gender,mem_email,mem_psw,mem_sta,mem_point) VALUES(('ME'||LPAD(to_char(MEMBER_seq.NEXTVAL),5,'0')), ?, ?, ?, ?,'0','0')";
		private static final String UPDATEPSW =
				"UPDATE member set mem_psw = ? where mem_email = ?";
		private static final String GET_ALL_EMAIL =
				"SELECT mem_email FROM MEMBER order by mem_id ";

		
		@Override
		public void insert2(MemVO memVO) {
			// TODO Auto-generated method stub
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT2);

					pstmt.setString(1, memVO.getMem_name());
					pstmt.setString(2, memVO.getMem_gender());
					pstmt.setString(3, memVO.getMem_email());
					pstmt.setString(4, memVO.getMem_psw());
				
					pstmt.executeUpdate();

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
	public void insert(MemVO memVO) {
		// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, memVO.getMem_name());
				pstmt.setString(2, memVO.getMem_gender());
				pstmt.setString(3, memVO.getMem_email());
				pstmt.setString(4, memVO.getMem_psw());
				pstmt.setString(5, memVO.getPhone());
				pstmt.setString(6, memVO.getAddress());
				pstmt.setString(7, memVO.getPos_code());
				pstmt.setDate(8, memVO.getBirthday());
				pstmt.setBytes(9, memVO.getMem_pic());
				pstmt.setString(10, memVO.getMem_sta());
				pstmt.setInt(11, memVO.getMem_point());
				pstmt.executeUpdate();

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
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_gender());
			pstmt.setString(3, memVO.getMem_email());
			pstmt.setString(4, memVO.getMem_psw());
			pstmt.setString(5, memVO.getPhone());
			pstmt.setString(6, memVO.getAddress());
			pstmt.setString(7, memVO.getPos_code());
			pstmt.setDate(8, memVO.getBirthday());
			pstmt.setBytes(9, memVO.getMem_pic());
		
			
			pstmt.setString(10, memVO.getMem_sta());
			pstmt.setInt(11, memVO.getMem_point());
			pstmt.setString(12, memVO.getMem_id());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String mem_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MemVO findByPrimaryKey(String mem_id) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPos_code(rs.getString("pos_code"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setBirthday(rs.getDate("birthday"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_sta(rs.getString("mem_sta"));
				memVO.setMem_point(rs.getInt("mem_point"));
				
			}

			// Handle any driver errors
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPos_code(rs.getString("pos_code"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setBirthday(rs.getDate("birthday"));
				memVO.setMem_pic(null);
				memVO.setMem_sta(rs.getString("mem_sta"));
				memVO.setMem_point(rs.getInt("mem_point"));
				
				list.add(memVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	public void update_MemPer(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			if(memVO.getMem_pic().length == 0) {
				pstmt = con.prepareStatement(UPDATE_MemPer_NoChangePIC);
				pstmt.setString(1, memVO.getMem_name());
				pstmt.setString(2, memVO.getPhone());
				pstmt.setString(3, memVO.getAddress());
				pstmt.setString(4, memVO.getPos_code());
				pstmt.setDate(5, memVO.getBirthday());
							
				pstmt.setString(6, memVO.getMem_email());
			
			}else {
				
				pstmt = con.prepareStatement(UPDATE_MemPer);

				pstmt.setString(1, memVO.getMem_name());
				pstmt.setString(2, memVO.getPhone());
				pstmt.setString(3, memVO.getAddress());
				pstmt.setString(4, memVO.getPos_code());
				pstmt.setDate(5, memVO.getBirthday());
				pstmt.setBytes(6, memVO.getMem_pic());
			
				pstmt.setString(7, memVO.getMem_email());
				
			}
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public String findMemberByAccount(String mem_email) {
		// TODO Auto-generated method stub
		String mem_psw = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PSW);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				
		
				mem_psw	=rs.getString("mem_psw");
				
				
			}

			// Handle any driver errors
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
		return mem_psw;
	}
	@Override
	public MemVO findMemberByAccount2(String mem_email) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_EMAIL);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_gender"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPos_code(rs.getString("pos_code"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setBirthday(rs.getDate("birthday"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_sta(rs.getString("mem_sta"));
				memVO.setMem_point(rs.getInt("mem_point"));
				
			}

			// Handle any driver errors
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
		return memVO;
	}
	
public void updateAddPoint(String mem_id, Integer mem_point) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPOINT);
			
			
			pstmt.setInt(1, mem_point);
			pstmt.setString(2, mem_id);
			
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
public List<String> getAllEmail() {
	List<String> list = new ArrayList<String>();
//	String memlist = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_EMAIL);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
//			MemVO memVO = new MemVO();
//			mem_email=memVO.setMem_email(rs.getString("mem_email"));
			
			list.add(rs.getString("mem_email")); // Store the row in the list
		}

		// Handle any driver errors
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

public void updatePsw(String mem_email,String mem_psw) {

Connection con = null;
PreparedStatement pstmt = null;

try {
	con = ds.getConnection();
	pstmt = con.prepareStatement(UPDATEPSW);
	
	
	pstmt.setString(1, mem_psw);
	pstmt.setString(2, mem_email);
	
	pstmt.executeUpdate();
	
}  catch (SQLException se) {
	throw new RuntimeException("A database error occured. "
			+ se.getMessage());
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
