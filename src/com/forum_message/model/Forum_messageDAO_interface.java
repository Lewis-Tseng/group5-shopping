package com.forum_message.model;

import java.util.*;

public interface Forum_messageDAO_interface {
          public void insert(Forum_messageVO forum_messageVO);
          public void insert2(Forum_messageVO forum_messageVO);
//前台使用者編輯留言
          public void update2(Forum_messageVO forum_messageVO);
//前台使用者刪除留言(實為下架)
          public void update3(Forum_messageVO forum_messageVO);
          public void update(Forum_messageVO forum_messageVO);
          public void delete(String forum_msg_id);
          public Forum_messageVO findByPrimaryKey(String forum_msg_id);
          public List<Forum_messageVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
