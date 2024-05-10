package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoVO;

public interface KoService {
	
	List<KoVO> getAreaList(String areacode);
	
	List<KoVO> getTemaList(String contenttypeid);
	
	List<KoVO> getPathList(String contentid);
	//List<KoPathVO> getPathList(String contentid);
	
	ItemVO getPlaceDetail(String contentid);
	
	
	
}
