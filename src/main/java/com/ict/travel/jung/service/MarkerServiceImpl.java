package com.ict.travel.jung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.jung.dao.MarkerDAO;
import com.ict.travel.jung.dao.MarkerInfoVO;

@Service
public class MarkerServiceImpl implements MarkerService {
	@Autowired
	private MarkerDAO markerDAO;
	
	@Override
	public MarkerInfoVO getMarkerInfo(String contentid) {
		return markerDAO.getMarkerInfo(contentid) ;
	}

}
