package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchVO;
import com.ict.travel.cho.dao.TourapiVO;
import com.ict.travel.lee.dao.MemberVO;

public interface ChoService {
	// 페이징 카운트
	int getTourListCount(String areaCode, String sigunguCode, String contentType, String title,String type);
	// 장소 검색(지역,시군구,기타)
	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	List<PlaceWishVO> getPlaceWishList(String u_idx);
	MemberVO getUserLogin(String string);
	int getPlaceWishAdd(String contentid, String u_idx);
	int getPlaceWishRemove(String contentid, String u_idx);
	int dataUpdate(List<TourapiVO> voList);
	// 경로 검색(지역,시군구,기타)
	List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	List<PathWishVO> getpathWishList(String u_idx);
	int getPathWishAdd(String path_post_idx, String u_idx);
	int getPathWishRemove(String path_post_idx, String u_idx);
	List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title, String order,
			String type, int offset, int limit);
	

}
