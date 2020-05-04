package com.forum_report.model;

import java.util.*;

public interface Forum_reportDAO_interface {
          public void insert(Forum_reportVO forum_reportVO);
          public void update(Forum_reportVO forum_reportVO);
          public void delete(String forum_rep_id);
          public Forum_reportVO findByPrimaryKey(String forum_rep_id);
          public List<Forum_reportVO> getAll();

          
}
