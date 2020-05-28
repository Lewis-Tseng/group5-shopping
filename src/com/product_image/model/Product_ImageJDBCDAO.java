package com.product_image.model;

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

public class Product_ImageJDBCDAO implements Product_ImageDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product_image (pro_img_no, pro_no, img, img_nam) VALUES ('PI'||LPAD(to_char(product_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT pro_img_no, pro_no, img, img_nam FROM product_image order by pro_img_no";
	private static final String GET_ONE_STMT = "SELECT pro_img_no, pro_no, img, img_nam FROM product_image where pro_img_no = ?";
	private static final String DELETE = "DELETE FROM product_image where pro_img_no = ?";
	private static final String UPDATE = "UPDATE product_image set pro_no=?, img=?, img_nam=? where pro_img_no = ?";

	@Override
	public void insert(Product_ImageVO product_ImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_ImageVO.getPro_no());
			pstmt.setBytes(2, product_ImageVO.getImg());
			pstmt.setString(3, product_ImageVO.getImg_nam());

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
	public void update(Product_ImageVO product_ImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_ImageVO.getPro_no());
			pstmt.setBytes(2, product_ImageVO.getImg());
			pstmt.setString(3, product_ImageVO.getImg_nam());
			pstmt.setString(4, product_ImageVO.getPro_img_no());

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
	public void delete(String pro_img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pro_img_no);

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
	public Product_ImageVO findByPrimaryKey(String pro_img_no) {

		Product_ImageVO product_ImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_img_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				product_ImageVO = new Product_ImageVO();
				product_ImageVO.setPro_img_no(rs.getString("pro_img_no"));
				product_ImageVO.setPro_no(rs.getString("pro_no"));
				product_ImageVO.setImg(rs.getBytes("img"));
				product_ImageVO.setImg_nam(rs.getString("img_nam"));

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
		return product_ImageVO;
	}

	@Override
	public List<Product_ImageVO> getAll() {

		List<Product_ImageVO> list = new ArrayList<Product_ImageVO>();
		Product_ImageVO product_ImageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				product_ImageVO = new Product_ImageVO();
				product_ImageVO.setPro_img_no(rs.getString("pro_img_no"));
				product_ImageVO.setPro_no(rs.getString("pro_no"));
				product_ImageVO.setImg(rs.getBytes("img"));
				product_ImageVO.setImg_nam(rs.getString("img_nam"));
				list.add(product_ImageVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		finally {
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

//	public static void main(String[] args) throws IOException {

//		Product_ImageJDBCDAO dao = new Product_ImageJDBCDAO();
//		//�s�W
//		Product_ImageVO product_ImageVO1 = new Product_ImageVO();
//		product_ImageVO1.setPro_no("PT00001");
//		product_ImageVO1.setImg(getPictureByteArray("D:\\圖庫\\test10.jpg"));
//		product_ImageVO1.setImg_nam("測試用");
//		dao.insert(product_ImageVO1);

//		//�ק�
//		Product_ImageVO product_ImageVO2 = new Product_ImageVO();
//		product_ImageVO2.setPro_img_no("PI00002");
//		product_ImageVO2.setPro_no("PT00005");
//		product_ImageVO2.setImg(getPictureByteArray("D:\\�Ϯw\\test10.jpg"));
//		product_ImageVO2.setImg_nam("��M");
//		dao.update(product_ImageVO2);

//		//�R��
//		dao.delete("PI00001");

//		//��d��
//		Product_ImageVO product_ImageVO3 = dao.findByPrimaryKey("PI00007");
//		System.out.println(product_ImageVO3.getPro_no() + ",");
//		System.out.println(product_ImageVO3.getImg() + ",");
//		System.out.println(product_ImageVO3.getImg_nam() + ",");
//		System.out.println("---------------------------");

//		//�d�ߥ���
//		List<Product_ImageVO> list = dao.getAll();
//		for(Product_ImageVO aProduct_Image : list) {
//			System.out.println(aProduct_Image.getPro_img_no() + ",");
//			System.out.println(aProduct_Image.getPro_no() + ",");
//			System.out.println(aProduct_Image.getImg() + ",");
//			System.out.println(aProduct_Image.getImg_nam() + ",");
//		}

//	}

	// Write Blob(參考老師JDBC範例)
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
	
//	//Read Blob(參考老師JDBC範例)
//	public static byte[] readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("Output/2.png");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//		return bytes;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
