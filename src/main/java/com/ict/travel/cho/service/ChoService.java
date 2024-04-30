package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.lee.dao.MemberVO;

public interface ChoService {
	// 페이징 카운트
	int getTourListCount(String areaCode, String sigunguCode, String contentType, String title);
	// 검색(지역,시군구,기타)
	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, int offset, int limit);
	List<PlaceWishVO> getPlaceWishList(String u_idx);
	MemberVO getUserLogin(String string);
	int getPlaceWishAdd(String contentid, String u_idx);
	int getPlaceWishRemove(String contentid, String u_idx);


}
