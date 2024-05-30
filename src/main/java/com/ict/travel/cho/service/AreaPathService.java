package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;

public interface AreaPathService {

	// 경로 검색(지역,시군구,기타)
	List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit);
	// 경로 좋아요 리스트
	List<PathWishVO> getpathWishList(String u_idx);
	// 경로 좋아요 추가
	int getPathWishAdd(String path_post_idx, String u_idx);
	// 경로 좋아요 삭제
	int getPathWishRemove(String path_post_idx, String u_idx);
	// 통합 경로 게시판 리스트
	List<PathPostVO> getAllPathPostList();
	

}
