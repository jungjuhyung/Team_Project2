package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.SearchVO;

public interface SearchService {
	// 페이징 카운트
	int getSearchListCount(String areaCode, String sigunguCode, String contentType, String title,String type);
	// 통합 검색
	List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title, String order,
			String type, int offset, int limit);

}
