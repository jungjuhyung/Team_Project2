package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.AreaPlaceDAO;
import com.ict.travel.cho.dao.ChoDAO;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PlaceWishVO;

@Service
public class AreaPlaceServiceImpl implements AreaPlaceService{
	@Autowired
	private AreaPlaceDAO areaPlaceDAO;
	
	// 장소 검색(지역,시군구,기타)
	@Override
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit) {
		return areaPlaceDAO.getChoTourList(areaCode, sigunguCode, contentType, title, order, type, offset, limit);
	}
	// 장소 좋아요 리스트
	@Override
	public List<PlaceWishVO> getPlaceWishList(String u_idx) {
		return areaPlaceDAO.getPlaceWishList(u_idx);
	}
	// 장소 좋아요 추가
	@Override
	public int getPlaceWishAdd(String contentid, String u_idx) {
		return areaPlaceDAO.getPlaceWishAdd(contentid,u_idx);
	}
	// 장소 좋아요 삭제
	@Override
	public int getPlaceWishRemove(String contentid, String u_idx) {
		return areaPlaceDAO.getPlaceWishRemove(contentid, u_idx);
	}

	// 랜덤 장소 리스트
	@Override
	public List<ChoTourVO> getRandomTourList(String areaCode, int limit) {
		return areaPlaceDAO.getRandomTourList(areaCode,limit);
	}
}

