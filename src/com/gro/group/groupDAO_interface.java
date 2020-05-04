package com.gro.group;

import java.util.*;

import com.gro.group.groupVO;
import com.gro.joingroup.joingroupVO;

public interface groupDAO_interface {
	      public void insert(groupVO group_idVO);
          public void update(groupVO group_idVO);
          public void delete(String gro_id);
          public groupVO findByPrimaryKey(String gro_id);
          public groupVO findByPrimaryKeyStat(String gro_id);
          public groupVO findByPrimaryKeyStatHistory(String gro_id);
	      public List<groupVO> getAll();
	      
	      public List<groupVO> getGrosByMem(String mem_id);
	      public List<groupVO> getGrosByMemHistory(String mem_id);
	      public void updateTodayBeforeStat();
	      
	      
//	      public Set<GRO_CLASS> getEmpsByGroClass(String GRO_ID);
//	      public Set<MEMBER> getEmpsByMemId(String MEM_ID);
}
