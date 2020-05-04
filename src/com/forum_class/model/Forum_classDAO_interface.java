package com.forum_class.model;

import java.util.*;

public interface Forum_classDAO_interface {
          public void insert(Forum_classVO forum_classVO);
          public void update(Forum_classVO forum_classVO);
          public void delete(String forum_cls_id);
          public Forum_classVO findByPrimaryKey(String forum_cls_id);
          public List<Forum_classVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 

}
