package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.ChoDAO;
import com.ict.travel.cho.dao.ChoTourVO;

@Service
public class ChoServiceImpl implements ChoService{
	@Autowired
	private ChoDAO choDAO;
	
	@Override
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, int offset, int limit) {
		return choDAO.getChoTourList(areaCode, sigunguCode, contentType,title, offset, limit);
	}
	
	@Override
	public int getTourListCount(String areaCode, String sigunguCode, String contentType, String title) {
		return choDAO.getTourListCount(areaCode, sigunguCode, contentType, title);
	}
}
