package com.gro.joingroup;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gro.group.groupVO;
import com.gro.type.groclassVO;
import com.mem.model.MemVO;

public class joingroupDAO implements joingroupDAO_interface {

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
		"INSERT INTO JOIN_GROUP (GRO_ID,MEM_ID,GRO_STAR) VALUES (?,?,-1)";
	private static final String GET_ONE_STMT = 
			"SELECT GRO_ID, MEM_ID,GRO_STAR FROM JOIN_GROUP WHERE GRO_ID = ? AND MEM_ID = ?";
	private static final String UPDATE_STAR =
			"UPDATE JOIN_GROUP SET　GRO_STAR=?  WHERE GRO_ID = ? AND MEM_ID = ?";
									//查詢此團所有會員
	private static final String GET_ALL_MEMs = 
		"SELECT GRO_ID,MEM_ID,GRO_STAR FROM JOIN_GROUP where GRO_ID = ? ORDER BY GRO_ID";
									//查詢此會員參加的所有團
	private static final String GET_ALL_GROs = 
		"SELECT MEM_ID,GRO_ID,GRO_STAR FROM JOIN_GROUP where MEM_ID = ? ORDER BY MEM_ID";
	private static final String GET_ALL_STMT = 
		"SELECT GRO_ID,MEM_ID,GRO_STAR FROM JOIN_GROUP ORDER BY GRO_ID";
	private static final String DELETE = 
			"DELETE FROM JOIN_GROUP where GRO_ID = ? AND MEM_ID = ?";
	
	private static final String FIND_MNUM =
			"SELECT GRO_MNUM FROM RGROUP WHERE GRO_ID=?";
	private static final String FIND_MNUM_MIN =
			"SELECT GRO_MNUM_MIN FROM RGROUP WHERE GRO_ID=?";
	private static final String UPDATE_MNUM =
			"UPDATE RGROUP SET　GRO_MNUM=?  WHERE GRO_ID=?";
	private static final String UPDATE_STAT =
			"UPDATE RGROUP SET　GRO_STAT=?  WHERE GRO_ID=?";
	private static final String UPDATE_ASTAR =
			"UPDATE RGROUP SET　GRO_ASTAR=?  WHERE GRO_ID=?";
	
	@Override
	public void insert(joingroupVO joingroupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, joingroupVO.getGro_id());
			pstmt.setString(2, joingroupVO.getMem_id());

			pstmt.executeUpdate();


		} catch (SQLException se) {
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
	public joingroupVO findByPrimaryKey(String gro_id,String mem_id) {

		joingroupVO joingroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gro_id);
			pstmt.setString(2, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				joingroupVO = new joingroupVO();
				joingroupVO.setGro_id(rs.getString("GRO_ID"));
				joingroupVO.setMem_id(rs.getString("MEM_ID"));
				joingroupVO.setGro_star(rs.getInt("GRO_STAR"));
			}


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
		return joingroupVO;
	}
	
	@Override
	public void updatestar(joingroupVO joingroupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAR);

			pstmt.setInt(1, joingroupVO.getGro_star());
			pstmt.setString(2, joingroupVO.getGro_id());
			pstmt.setString(3, joingroupVO.getMem_id());

			pstmt.executeUpdate();


		} catch (SQLException se) {
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
	public Integer getmnum(String gro_id) {

		Integer gro_mnum = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_MNUM);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				gro_mnum = Integer.valueOf(rs.getString("GRO_MNUM"));
			}


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
		return gro_mnum;
	}
	
	@Override
	public Integer getmnumMin(String gro_id) {

		Integer gro_mnum_min = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_MNUM_MIN);

			pstmt.setString(1, gro_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				gro_mnum_min = Integer.valueOf(rs.getString("GRO_MNUM_MIN"));
			}


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
		return gro_mnum_min;
	}
	
	@Override
	public void updatemnum(Integer gro_mnum,String gro_id) {

		Connection con = null;
		PreparedStatement pstmt = null;


		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MNUM);

			pstmt.setInt(1,gro_mnum);
			pstmt.setString(2,gro_id);

			pstmt.executeUpdate();


		} catch (SQLException se) {
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
	public void updatstat(Integer gro_stat,String gro_id) {

		Connection con = null;
		PreparedStatement pstmt = null;


		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAT);

			pstmt.setInt(1,gro_stat);
			pstmt.setString(2,gro_id);

			pstmt.executeUpdate();


		} catch (SQLException se) {
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
	public void updateastar(String gro_id) {

		Integer count =0;
		Integer star =0;
		Integer aStar =0;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtAstat = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMs);
			pstmt.setString(1, gro_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {

				if(rs.getInt("GRO_STAR") != -1) {
					count += 1;
					star += rs.getInt("GRO_STAR");
				}
				
			}
			
			if(count!=0) {
				aStar = star / count;
			}
			
			pstmtAstat = con.prepareStatement(UPDATE_ASTAR);
			pstmtAstat.setInt(1, aStar);
			pstmtAstat.setString(2, gro_id);
			pstmtAstat.executeUpdate();

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
			if (pstmtAstat != null) {
				try {
					pstmtAstat.close();
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
	}


	@Override
	public void delete(String gro_id, String mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gro_id);
			pstmt.setString(2, mem_id);

			pstmt.executeUpdate();


		} catch (SQLException se) {
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
	public Set<joingroupVO> getMemsByGro(String gro_id) {
		Set<joingroupVO> set = new LinkedHashSet<joingroupVO>();
		joingroupVO joingroupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMs);
			pstmt.setString(1, gro_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				joingroupVO = new joingroupVO();
				joingroupVO.setMem_id(rs.getString("MEM_ID"));
				joingroupVO.setGro_id(rs.getString("GRO_ID"));
				joingroupVO.setGro_star(rs.getInt("GRO_STAR"));
				
				set.add(joingroupVO);

			}
			

	

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
	
	@Override
	public Set<joingroupVO> getGrosByMem(String mem_id) {
		Set<joingroupVO> set = new LinkedHashSet<joingroupVO>();
		joingroupVO joingroupVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GROs);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				joingroupVO = new joingroupVO();
				joingroupVO.setGro_id(rs.getString("GRO_ID"));
				joingroupVO.setMem_id(rs.getString("MEM_ID"));
				joingroupVO.setGro_star(rs.getInt("GRO_STAR"));
				
				set.add(joingroupVO);
			}
	

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

	@Override
	public List<joingroupVO> getAll() {
		List<joingroupVO> list = new ArrayList<joingroupVO>();
		joingroupVO joingroupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				joingroupVO = new joingroupVO();
				joingroupVO.setGro_id(rs.getString("GRO_ID"));
				joingroupVO.setMem_id(rs.getString("MEM_ID"));
				joingroupVO.setGro_star(rs.getInt("GRO_STAR"));
				list.add(joingroupVO);
			}


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
		return list;
	}
	
}