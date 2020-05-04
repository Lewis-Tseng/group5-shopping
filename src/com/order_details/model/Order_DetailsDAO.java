package com.order_details.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order.model.Product_OrderVO;

public class Order_DetailsDAO implements Order_DetailsDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO order_details (ord_no, pro_no, quantity, uni_pri) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ord_no, pro_no, quantity, uni_pri FROM order_details order by ord_no";
	private static final String GET_ONE_STMT = "SELECT ord_no, pro_no, quantity, uni_pri FROM order_details where ord_no = ? and pro_no = ?";
	private static final String DELETE = "DELETE FROM order_details where ord_no = ? and pro_no = ?";
	private static final String UPDATE = "UPDATE order_details set quantity=?, uni_pri=? where ord_no = ? and pro_no = ?";
	private static final String GET_OD_STMT = "SELECT ord_no, pro_no, quantity, uni_pri FROM order_details where ord_no = ?";

	@Override
	public void insert(Order_DetailsVO order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detailsVO.getOrd_no());
			pstmt.setString(2, order_detailsVO.getPro_no());
			pstmt.setInt(3, order_detailsVO.getQuantity());
			pstmt.setInt(4, order_detailsVO.getUni_pri());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Order_DetailsVO order_detailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_detailsVO.getQuantity());
			pstmt.setInt(2, order_detailsVO.getUni_pri());
			pstmt.setString(3, order_detailsVO.getOrd_no());
			pstmt.setString(4, order_detailsVO.getPro_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String ord_no, String pro_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, pro_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<Order_DetailsVO> getOrder_DetailsByOrd_no(String ord_no) {

		Set<Order_DetailsVO> set = new LinkedHashSet<Order_DetailsVO>();
		Order_DetailsVO order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OD_STMT);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailsVO = new Order_DetailsVO();
				order_detailsVO.setOrd_no(rs.getString("ord_no"));
				order_detailsVO.setPro_no(rs.getString("pro_no"));
				order_detailsVO.setQuantity(rs.getInt("quantity"));
				order_detailsVO.setUni_pri(rs.getInt("uni_pri"));

				set.add(order_detailsVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	@Override
	public List<Order_DetailsVO> getAll() {
		List<Order_DetailsVO> list = new ArrayList<Order_DetailsVO>();
		Order_DetailsVO order_detailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailsVO = new Order_DetailsVO();
				order_detailsVO.setOrd_no(rs.getString("ord_no"));
				order_detailsVO.setPro_no(rs.getString("pro_no"));
				order_detailsVO.setQuantity(rs.getInt("quantity"));
				order_detailsVO.setUni_pri(rs.getInt("uni_pri"));

				list.add(order_detailsVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public Order_DetailsVO findByPrimaryKey(String ord_no, String pro_no) {

		Order_DetailsVO order_detailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);
			pstmt.setString(2, pro_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailsVO = new Order_DetailsVO();
				order_detailsVO.setOrd_no(rs.getString("ord_no"));
				order_detailsVO.setPro_no(rs.getString("pro_no"));
				order_detailsVO.setQuantity(rs.getInt("quantity"));
				order_detailsVO.setUni_pri(rs.getInt("uni_pri"));

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
		return order_detailsVO;
	}

	@Override
	public void insertCart(Order_DetailsVO order_detailsVO, Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, order_detailsVO.getOrd_no());
			pstmt.setString(2, order_detailsVO.getPro_no());
			pstmt.setInt(3, order_detailsVO.getQuantity());
			pstmt.setInt(4, order_detailsVO.getUni_pri());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.out.println("交易正在進行中");
					System.out.println("rolled back由-訂單明細");
					con.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace(System.err);
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			se.printStackTrace(System.err);
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

//	public static void main(String[] args) {

//		Order_DetailsDAO dao = new Order_DetailsDAO();

	// 新增
//		Order_DetailsVO odVO1 = new Order_DetailsVO();
//		odVO1.setOrd_no("PO00001");
//		odVO1.setPro_no("PT00002");
//		odVO1.setQuantity(50);
//		odVO1.setUni_pri(1000);
//		dao.insert(odVO1);

//		//修改
//		Order_DetailsVO odVO2 = new Order_DetailsVO();
//		odVO2.setOrd_no("PO00001");
//		odVO2.setPro_no("PT00002");
//		odVO2.setQuantity(520);
//		odVO2.setUni_pri(9999);
//        dao.update(odVO2);

//        //刪除
//        dao.delete("PO00001", "PT00002");
//        
//        //單查詢
//        Set<Order_DetailsVO> set = dao.getOrder_DetailsByOrd_no("PO00001");
//        for(Order_DetailsVO aODVO : set) {
//        	System.out.println(aODVO.getQuantity() + ",");
//        	System.out.println(aODVO.getUni_pri() + ",");
//        	System.out.println();	
//        }
//        
//        
	// 全部查詢
//        List<Order_DetailsVO> list = dao.getAll();
//        for(Order_DetailsVO aODVO2 : list) {
//        	System.out.println(aODVO2.getOrd_no() + ",");
//        	System.out.println(aODVO2.getPro_no() + ",");
//        	System.out.println(aODVO2.getQuantity() + ",");
//        	System.out.println(aODVO2.getUni_pri() + ",");
//        	System.out.println();
//        }     

//	}

}
