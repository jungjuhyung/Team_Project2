package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoDAO;
import com.ict.travel.ko.dao.KoPostVO;

@Service
public class KoServiceIple implements KoService{
	
	@Autowired
	private KoDAO koDAO;

	@Override
	public List<KoPostVO> getAreaList(String r_areacode) {
		return koDAO.getAreaList(r_areacode);
	}

	@Override
	public List<KoPostVO> getTemaList(String r_contenttypeid) {
		return koDAO.getTemaList(r_contenttypeid);
	}
	
	@Override
	public List<KoPostVO> getPathList(String contentid) {
		return koDAO.getPathList(contentid);
	}
	
	@Override
	public ItemVO getPlaceDetail(String contentid) {
		return koDAO.getPlaceDetail(contentid);
	}
	
	
}
