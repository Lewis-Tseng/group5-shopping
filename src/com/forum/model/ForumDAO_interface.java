package com.forum.model;

import java.util.*;

import com.forum_message.model.Forum_messageVO;

public interface ForumDAO_interface {
          public void insert(ForumVO forumVO);
//前台 新增留言
          public void insert2(ForumVO forumVO);
          public void update(ForumVO forumVO);
          public void delete(String forum_id);
//後台改變文章狀態
          public void update2(ForumVO forumVO); 
//前台使用者改變文章
          public void update3(ForumVO forumVO); 
          public ForumVO findByPrimaryKey(String forum_id);
          public List<ForumVO> getAll();
//文章列出所有留言用
          public Set<Forum_messageVO> getMsgByForum(String forum_id);
          
          public Integer getOneHit(String forum_id);
          public void setOneHit(String forum_id, Integer forum_hit);
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
		
}
