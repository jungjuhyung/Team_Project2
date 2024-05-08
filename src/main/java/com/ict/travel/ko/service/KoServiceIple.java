package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoDAO;
import com.ict.travel.ko.dao.KoVO;

@Service
public class KoServiceIple implements KoService{
	
	@Autowired
	private KoDAO koDAO;

	@Override
	public List<KoVO> getAreaList(String areacode) {
		return koDAO.getAreaList(areacode);
	}

	@Override
	public List<KoVO> getTemaList(String contenttypeid) {
		return koDAO.getTemaList(contenttypeid);
	}
	
	@Override
	public List<KoVO> getPathList(String contentid) {
		return koDAO.getPathList(contentid);
	}
	/*
	@Override public List<KoPathVO> getPathList(String contentid) { 
		return koDAO.getPathList(contentid); 
	}
	*/
	
	@Override
	public ItemVO getPlaceDetail(String contentid) {
		return koDAO.getPlaceDetail(contentid);
	}
	
	@Override
	public String getPlaceWish(ItemVO itemVO) {
		return koDAO.getPlaceWish(itemVO);
	}
	
}
