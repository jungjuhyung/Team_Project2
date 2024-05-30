package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchVO;

public interface ChoService {
	// 페이징 카운트
	int getSearchListCount(String areaCode, String sigunguCode, String contentType, String title,String type);
	// 장소 검색(지역,시군구,기타)
	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	// 장소 좋아요 리스트
	List<PlaceWishVO> getPlaceWishList(String u_idx);
	// 장소 좋아요 추가
	int getPlaceWishAdd(String contentid, String u_idx);
	// 장소 좋아요 삭제
	int getPlaceWishRemove(String contentid, String u_idx);
	// 경로 검색(지역,시군구,기타)
	List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	// 경로 좋아요 리스트
	List<PathWishVO> getpathWishList(String u_idx);
	// 경로 좋아요 추가
	int getPathWishAdd(String path_post_idx, String u_idx);
	// 경로 좋아요 삭제
	int getPathWishRemove(String path_post_idx, String u_idx);
	// 랜덤 장소 리스트
	List<ChoTourVO> getRandomTourList(String areaCode, int limit);
	// 통합 경로 게시판 리스트
	List<PathPostVO> getAllPathPostList();
	// 통합 검색
	List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title, String order,
			String type, int offset, int limit);

}
