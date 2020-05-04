package com.gro.joingroup;

import java.util.*;

import com.gro.group.groupVO;
import com.gro.type.groclassVO;
import com.mem.model.MemVO;

public interface joingroupDAO_interface {
          public void insert(joingroupVO joingroupVO);
          public void delete(String mem_id, String gro_id);
          public joingroupVO findByPrimaryKey(String gro_id,String mem_id);
          public void updatestar(joingroupVO joingroupVO);
          public void updateastar(String gro_id);
          
          public Integer getmnum(String gro_id);
          public Integer getmnumMin(String gro_id);
          public void updatemnum(Integer gro_mnum,String gro_id);
          public void updatstat(Integer gro_stat,String gro_id);
          
          public Set<joingroupVO> getMemsByGro(String gro_id);
          public Set<joingroupVO> getGrosByMem(String mem_id);
          
          public List<joingroupVO> getAll();
          
}
