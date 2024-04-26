package com.ict.travel.jung.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.travel.jung.dao.MarkerInfoVO;

public interface MarkerService {
	public MarkerInfoVO getMarkerInfo(String contentid);
}
