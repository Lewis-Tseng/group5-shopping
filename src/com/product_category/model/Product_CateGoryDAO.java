package com.product_category.model;

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

public class Product_CategoryDAO implements Product_CategoryDAO_interface{
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
			"INSERT INTO product_category (cat_no, cat_nam) VALUES ('PR'||LPAD(to_char(product_seq.NEXTVAL), 5, '0'), ?)";
	private static final String GET_ALL_STMT =
			"SELECT cat_no, cat_nam FROM product_category order by cat_no";
	private static final String GET_ONE_STMT =
			"SELECT cat_no, cat_nam FROM product_category where cat_no = ?";
	private static final String DELETE =
			"DELETE FROM product_category where cat_no = ?";
	private static final String UPDATE =
			"UPDATE product_category set cat_nam=? where cat_no = ?";
	
	
	@Override
	public void insert(Product_CategoryVO product_CategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_CategoryVO.getCat_nam());

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
	public void update(Product_CategoryVO product_CategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_CategoryVO.getCat_nam());
			pstmt.setString(2, product_CategoryVO.getCat_no());
			
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
	public void delete(String cat_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cat_no);

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
	public Product_CategoryVO findByPrimaryKey(String cat_no) {

		Product_CategoryVO product_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cat_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				product_categoryVO = new Product_CategoryVO();
				product_categoryVO.setCat_no(rs.getString("cat_no"));
				product_categoryVO.setCat_nam(rs.getString("cat_nam"));

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
		return product_categoryVO;
	}
	
	
	@Override
	public List<Product_CategoryVO> getAll() {
		
		List<Product_CategoryVO> list = new ArrayList<Product_CategoryVO>();
		Product_CategoryVO product_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				product_categoryVO = new Product_CategoryVO();
				product_categoryVO.setCat_no(rs.getString("cat_no"));
				product_categoryVO.setCat_nam(rs.getString("cat_nam"));
				list.add(product_categoryVO);

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
	
	
	
	public static void main(String[] args) {
//
//		Product_CateGoryDAO dao = new Product_CateGoryDAO();
//		//新增
//		Product_CategoryVO product_CategoryVO1 = new Product_CategoryVO();
//		product_CategoryVO1.setCat_nam("手杖");
//		dao.insert(product_CategoryVO1);
		//修改
//		Product_CategoryVO product_CategoryVO2 = new Product_CategoryVO();
//		product_CategoryVO2.setCat_no("PR00050");
//		product_CategoryVO2.setCat_nam("測試");
//		dao.update(product_CategoryVO2);
//		//刪除
//		dao.delete("PR00025");
//		//單查詢
//		Product_CategoryVO product_CategoryVO3 = dao.findByPrimaryKey("PR00004");
//		System.out.println(product_CategoryVO3.getCat_no() + ",");
//		System.out.println(product_CategoryVO3.getCat_nam() + ",");
//		System.out.println("----------------------------");
//		//查詢全部
//		List<Product_CategoryVO> list = dao.getAll();
//		for (Product_CategoryVO aProduct_Category : list) {
//			System.out.println(aProduct_Category.getCat_no() + ",");
//			System.out.println(aProduct_Category.getCat_nam() + ",");
//		}
//		
	}

}
