package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PlaceWishVO;


public interface AreaPlaceService {
	// 장소 검색(지역,시군구,기타)
	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	// 장소 좋아요 리스트
	List<PlaceWishVO> getPlaceWishList(String u_idx);
	// 장소 좋아요 추가
	int getPlaceWishAdd(String contentid, String u_idx);
	// 장소 좋아요 삭제
	int getPlaceWishRemove(String contentid, String u_idx);
	// 랜덤 장소 리스트
	List<ChoTourVO> getRandomTourList(String areaCode, int limit);
}
