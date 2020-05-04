package com.ad.model;
import java.sql.*;
import java.util.List;
public class AdService {
	private Ad_DAO_interface dao;
	public AdService() {
		dao=new AdDAO();
	}
	public AdVO addAd(String ad_id,String pro_no,byte[]ad_pic,
					  String ad_title,String ad_info,Date ad_date_on,Date ad_date_off ) {
		AdVO adVO=new AdVO();
		adVO.setAd_id(ad_id);
		adVO.setPro_no(pro_no);
		adVO.setAd_pic(ad_pic);
		adVO.setAd_title(ad_title);
		adVO.getAd_info();
		adVO.setAd_date_on(ad_date_on);
		adVO.setAd_date_off(ad_date_off);
		dao.insert(adVO);
		return adVO;
	}

	public AdVO updateAd(String ad_id, String pro_no, byte[] ad_pic, String ad_title, String ad_info, Date ad_date_on,
			Date ad_date_off) {
		AdVO adVO = new AdVO();
		adVO.setAd_id(ad_id);
		adVO.setPro_no(pro_no);
		adVO.setAd_pic(ad_pic);
		adVO.setAd_title(ad_title);
		adVO.getAd_info();
		adVO.setAd_date_on(ad_date_on);
		adVO.setAd_date_off(ad_date_off);
		dao.update(adVO);
		return adVO;
}
	public void deleteAd(String ad_id) {
		dao.delete(ad_id);
	}
	public AdVO getOneNews(String ad_id) {
		return dao.findByPrmarykey(ad_id);
	}
		public List<AdVO> getAll() {
			return dao.getAll();
		}

		public static void main(String[] args) {
			AdService ad=new AdService();
			List<AdVO>list=ad.getAll();
			for(AdVO vo:list) {
				System.out.print(vo.getAd_id()+",");
				System.out.print(vo.getPro_no()+",");
				System.out.print(vo.getAd_title()+",");
				System.out.print(vo.getAd_info()+"\n");
			
			}
			
		}
}
	