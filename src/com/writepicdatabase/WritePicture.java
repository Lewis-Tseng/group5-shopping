package com.writepicdatabase;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class WritePicture {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "DA103G5";
	private static final String passwd = "123456";
	private static final String UPDATE =
			"UPDATE COACH SET COA_PIC=? WHERE coa_id=?";
//			"UPDATE product_image SET img=? WHERE pro_img_no=?";
//			"UPDATE administrator SET emp_pic=? WHERE emp_id=?";
//			"UPDATE member SET mem_pic=? WHERE mem_id=?";
public static void main(String[] args) {

	Connection con = null;
	PreparedStatement pstmt = null;
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);
		String path="C:\\Users\\henrydo30\\Desktop\\專題照片\\教練\\";
		File file =new File(path);
		String[] obj=file.list();
		for(int i=1;i<obj.length;i++) {
			byte[] pic = getPictureByteArray(path+i+".jpg");
			
			pstmt.setBytes(1, pic);
//			pstmt.setString(2,"PI0000"+i);
			pstmt.setString(2,"CO0000"+i);
//			pstmt.setString(2,"EM0000"+i);
//			pstmt.setString(2,"ME0000"+i);
			
			pstmt.executeUpdate();
		}
		// 清空裡面參數，重覆使用已取得的PreparedStatement物件
		pstmt.clearParameters();
		System.out.println("修改成功");

	} catch (ClassNotFoundException ce) {
		System.out.println(ce);
	} catch (SQLException se) {
		System.out.println(se);
	} catch (IOException ie) {
		System.out.println(ie);
	} finally {
		// 依建立順序關閉資源 (越晚建立越早關閉)
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				System.out.println(se);
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
	}
}
//使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
