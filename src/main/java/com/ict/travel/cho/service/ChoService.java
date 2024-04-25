package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;

public interface ChoService {
	// 페이징 카운트
	int getTourListCount(String areaCode, String sigunguCode, String contentType, String title);
	// 검색(지역,시군구,기타)
	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, int offset, int limit);


}
