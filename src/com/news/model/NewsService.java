package com.news.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class NewsService {
	private News_DAO_interface dao;
	public NewsService() {
		dao=new NewsDAO();
	}
	public NewsVO addNews(String news_id,String news_title,String news_info,byte[] news_pic, Timestamp news_time) {
		NewsVO newsVO=new NewsVO();
		newsVO.setNews_id(news_id);
		newsVO.setNews_title(news_title);
		newsVO.setNews_info(news_info);
		newsVO.setNews_pic(news_pic);
		newsVO.setNews_time(news_time);
		dao.insert(newsVO);
		return newsVO;
	}
	
	public NewsVO update_nopicture(String news_id,String news_title,String news_info, Timestamp news_time) {
		NewsVO newsVO=new NewsVO();
		newsVO.setNews_id(news_id);
		newsVO.setNews_title(news_title);
		newsVO.setNews_info(news_info);
		newsVO.setNews_time(news_time);
		dao.update_nopicture(newsVO);
		return newsVO;
	}
	public NewsVO updateNews(String news_id,String news_title,String news_info,byte[] news_pic, Timestamp news_time) {
		NewsVO newsVO=new NewsVO();
		newsVO.setNews_id(news_id);
		newsVO.setNews_title(news_title);
		newsVO.setNews_info(news_info);
		newsVO.setNews_pic(news_pic);
		newsVO.setNews_time(news_time);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(String news_id) {
		dao.delete(news_id);
	}

	public NewsVO getOneNews(String news_id) {
		return dao.findPrimaryKey(news_id);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
	public static void main(String[] args) throws IOException {
		byte[] pic = getPictureByteArray("WebContent/image/newsP1.jpg");
		String str = getLongString("WebContent/item/最新消息.txt");
		Timestamp time=java.sql.Timestamp.valueOf("2019-10-10 12:00:00");
		NewsService news=new NewsService();
		news.addNews("NW0005","秋季健身大放送",str,pic ,time);
		System.out.println("新增成功");
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
