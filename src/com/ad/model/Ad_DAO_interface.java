package com.ad.model;

import java.util.*;
public interface Ad_DAO_interface {
	public void insert(AdVO adVO) ;
	public void update(AdVO adVO);
	public void delete(String ad_id);
	public AdVO findByPrmarykey(String ad_id);
	public List<AdVO> getAll();
}
