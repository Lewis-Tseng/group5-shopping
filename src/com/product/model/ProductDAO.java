package com.product.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order.model.Product_OrderVO;

import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product;
import jdbc.util_CompositeQueryProduct.jdbcUtil_CompositeQuery_Product_Order;

public class ProductDAO implements ProductDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO product (pro_no, cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto) VALUES ('PT'||LPAD(to_char(product_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT pro_no, cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto FROM product order by pro_no";
	private static final String GET_ONE_STMT = "SELECT pro_no, cat_no, pro_nam, pro_con, pro_pri, pro_sta, pro_sto FROM product where pro_no = ?";
	private static final String DELETE = "DELETE FROM product where pro_no = ?";
	private static final String UPDATE = "UPDATE product set  pro_nam=?, cat_no=?, pro_con=?, pro_pri=?, pro_sta=?, pro_sto=? where pro_no = ?";
	private static final String UPDATEPRO_STO = "UPDATE product set pro_sto=? where pro_no=?";
	
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getCat_no());
			pstmt.setString(2, productVO.getPro_nam());           
			pstmt.setString(3, productVO.getPro_con());
			pstmt.setInt(4, productVO.getPro_pri());
			pstmt.setString(5, productVO.getPro_sta());
            pstmt.setInt(6, productVO.getPro_sto());
			
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getPro_nam());
			pstmt.setString(2, productVO.getCat_no());
			pstmt.setString(3, productVO.getPro_con());
			pstmt.setInt(4, productVO.getPro_pri());
			pstmt.setString(5, productVO.getPro_sta());
			pstmt.setInt(6, productVO.getPro_sto());
			pstmt.setString(7, productVO.getPro_no());

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
	public void delete(String pro_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pro_no);

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
	public ProductVO findByPrimaryKey(String pro_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPro_no(rs.getString("pro_no"));
				productVO.setCat_no(rs.getString("cat_no"));
				productVO.setPro_nam(rs.getString("pro_nam"));

				Reader reader = rs.getCharacterStream("pro_con");
				productVO.setPro_con(readString(reader));

				productVO.setPro_pri(rs.getInt("pro_pri"));
				productVO.setPro_sta(rs.getString("pro_sta"));
                productVO.setPro_sto(rs.getInt("pro_sto"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.err);
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPro_no(rs.getString("pro_no"));
				productVO.setCat_no(rs.getString("cat_no"));
				productVO.setPro_nam(rs.getString("pro_nam"));

				Reader reader = rs.getCharacterStream("pro_con");
				productVO.setPro_con(readString(reader));

				productVO.setPro_pri(rs.getInt("pro_pri"));
				productVO.setPro_sta(rs.getString("pro_sta"));
				productVO.setPro_sto(rs.getInt("pro_sto"));
				
				list.add(productVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.err);
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
	public List<ProductVO> getAll(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			String finalSQL = "select * from product"
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map) + "order by pro_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("===finalSQL(by DAO) = " + finalSQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPro_no(rs.getString("pro_no"));
				productVO.setCat_no(rs.getString("cat_no"));
				productVO.setPro_nam(rs.getString("pro_nam"));
				productVO.setPro_con(rs.getString("pro_con"));
				productVO.setPro_pri(rs.getInt("pro_pri"));
				productVO.setPro_sta(rs.getString("pro_sta"));
				productVO.setPro_sto(rs.getInt("pro_sto"));
				list.add(productVO);

			}

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
	
	@Override
	public void updatePro_Sto(String pro_no, Integer pro_sto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPRO_STO);
			
			pstmt.setInt(1, pro_sto);
			pstmt.setString(2, pro_no);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	
	
	
//	public static void main(String[] args) throws IOException {

//		ProductDAO dao = new ProductDAO();
		
//        String str = getLongString("E:\\product1.txt");

		//新增 
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setPro_nam("啞鈴");
//		productVO1.setCat_no("PR00001");
//		productVO1.setPro_con("");
//		productVO1.setPro_pri(10000);
//		productVO1.setPro_sta("0");
//		productVO1.setPro_sto(111);
//		dao.insert(productVO1);
//
//		//修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setPro_no("PT00020");
//		productVO2.setCat_no("PR00004");
//		productVO2.setPro_nam("�j��3");
//		productVO2.setPro_con("D:\\product1.txt");
//		productVO2.setPro_pri(5000);
//		productVO2.setPro_sta("0");
//		productVO2.setPro_sto(111);
//		dao.update(productVO2);
//
//		//刪除
//		dao.delete("PT00021");
//
//		//單查詢
//		ProductVO productVO3 = dao.findByPrimaryKey("PT00020");
//		System.out.print(productVO3.getPro_no() + ",");
//		System.out.print(productVO3.getCat_no() + ",");
//		System.out.print(productVO3.getPro_nam() + ",");
//		System.out.print(productVO3.getPro_con() + ",");
//		System.out.print(productVO3.getPro_pri() + ",");
//		System.out.print(productVO3.getPro_sta() + ",");
//		System.out.print(productVO3.getPro_sto() + ",");
//		System.out.println("---------------------");
//
//		//查詢全部
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//			System.out.print(aProduct.getPro_no() + ",");
//			System.out.print(aProduct.getCat_no() + ",");
//			System.out.print(aProduct.getPro_nam() + ",");
//			System.out.print(aProduct.getPro_con() + ",");
//			System.out.print(aProduct.getPro_pri() + ",");
//			System.out.print(aProduct.getPro_sta() + ",");
//			System.out.print(aProduct.getPro_sto() + ",");
//		}

//	}

	/*************************** 使用Clob物件 讀入CLOB(參考老師JDBC範例) ***************************************/	
//	
//	public static String readString(Clob clob) throws SQLException, IOException {
//		StringBuilder sb = new StringBuilder();
//		BufferedReader br = new BufferedReader(clob.getCharacterStream());
//		String str;
//		while((str = br.readLine()) != null) {
//			sb.append(str);
//			sb.append("\n");
//		}
//		br.close();
//		
//		return sb.toString();
//	}
	/**************************************************************************************/	
	
	
	/*************************** 使用String寫入CLOB(參考老師JDBC範例) ***************************************/	
//	public static String getLongString(String path) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		StringBuilder sb = new StringBuilder();
//		String str;
//		while((str = br.readLine()) != null) {
//			sb.append(str);
//			sb.append("\n");
//		}
//		br.close();
//		
//		return sb.toString();	
//	}
	/**************************************************************************************/
	
	
	/*************************** 使用Reader(參考老師JDBC範例) *********************************/
	public static String readString(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	/**************************************************************************************/

	



	/*************************** 使用資料流寫入CLOB(參考老師JDBC範例) *****************************/
//	public static Reader getLongStringStream(String path) throws FileNotFoundException {
//		return new FileReader(path);
//	}
	/**************************************************************************************/
}
