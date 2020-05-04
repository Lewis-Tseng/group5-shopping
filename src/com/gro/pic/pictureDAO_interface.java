package com.gro.pic;

import java.util.*;

import com.gro.group.groupVO;

public interface pictureDAO_interface {
          public void insert(pictureVO pictureVO);
          public void delete(String gro_pid);
          
          public Set<pictureVO> getPicsByGro(String gro_id);
          
          public List<pictureVO> getAll();


          
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
