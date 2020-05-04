package com.news.model;
import java.util.*;

public interface News_DAO_interface {
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public void update_nopicture(NewsVO newsVO);
	public void delete(String news_id);
	public NewsVO findPrimaryKey(String news_id);
	public List<NewsVO> getAll();
}
