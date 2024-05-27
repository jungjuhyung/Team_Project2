package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PlaceDetailDAO;

@Service
public class PlaceDetailServiceImple implements PlaceDetailService{
	
	@Autowired
	private PlaceDetailDAO placeDetailDAO;
	
	@Override
	public List<KoPostVO> getPathList(String contentid) {
		return placeDetailDAO.getPathList(contentid);
	}
	
	@Override
	public ItemVO getPlaceDetail(String contentid) {
		return placeDetailDAO.getPlaceDetail(contentid);
	}
}
