package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.SearchDAO;
import com.ict.travel.cho.dao.SearchVO;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDAO searchDAO;
	
	// 페이징 카운트
	@Override
	public int getSearchListCount(String areaCode, String sigunguCode, String contentType, String title, String type) {
		return searchDAO.getSearchListCount(areaCode, sigunguCode, contentType, title, type);
	}
	// 통합 검색
	@Override
	public List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		return searchDAO.getSearchTotal(areaCode, sigunguCode, contentType, title, order, type, offset, limit);
	}
	
}

