package com.gro.type;

import java.util.*;

public interface groclassDAO_interface {
          public void insert(groclassVO groclassVO);
          public void update(groclassVO groclassVO);
          public void delete(String gro_cid);
          public groclassVO findByPrimaryKey(String gro_cid);
          public List<groclassVO> getAll();


          //        public List<EmpVO> getAll(Map<String, String[]> map); 
}
