package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.AreaPathDAO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;

@Service
public class AreaPathServiceImpl implements AreaPathService{
	@Autowired
	private AreaPathDAO areaPathDAO;
	
	// 경로 검색(지역,시군구,기타)
	@Override
	public List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		return areaPathDAO.getChoTourPathList(areaCode, sigunguCode, contentType, title, order, type , offset, limit);
	}
	// 경로 좋아요 리스트
	@Override
	public List<PathWishVO> getpathWishList(String u_idx) {
		return areaPathDAO.getpathWishList(u_idx);
	}
	// 경로 좋아요 추가
	@Override
	public int getPathWishAdd(String path_post_idx, String u_idx) {
		return areaPathDAO.getPathWishAdd(path_post_idx, u_idx);
	}
	// 경로 좋아요 삭제
	@Override
	public int getPathWishRemove(String path_post_idx, String u_idx) {
		return areaPathDAO.getPathWishRemove(path_post_idx, u_idx);
	}
	// 통합 경로 게시판 리스트
	@Override
	public List<PathPostVO> getAllPathPostList() {
		return areaPathDAO.getAllPathPostList();
	}
}

