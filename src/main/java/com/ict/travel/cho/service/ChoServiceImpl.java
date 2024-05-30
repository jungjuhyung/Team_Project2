package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchVO;

@Service
public class ChoServiceImpl implements ChoService{
	@Override
	public int getSearchListCount(String areaCode, String sigunguCode, String contentType, String title, String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceWishVO> getPlaceWishList(String u_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlaceWishAdd(String contentid, String u_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlaceWishRemove(String contentid, String u_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PathWishVO> getpathWishList(String u_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPathWishAdd(String path_post_idx, String u_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathWishRemove(String path_post_idx, String u_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChoTourVO> getRandomTourList(String areaCode, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PathPostVO> getAllPathPostList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

