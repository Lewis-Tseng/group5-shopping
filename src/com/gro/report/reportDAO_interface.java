package com.gro.report;

import java.util.*;

public interface reportDAO_interface {
          public void insert(reportVO reportVO);
          public void update(reportVO reportVO);
          public void delete(String gro_repid);
          public reportVO findByPrimaryKey(String gro_repid);
          public List<reportVO> getAll();


          
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
