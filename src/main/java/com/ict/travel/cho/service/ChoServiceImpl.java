package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.ChoDAO;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.TourapiVO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class ChoServiceImpl implements ChoService{
	@Autowired
	private ChoDAO choDAO;
	
	@Override
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order,int offset, int limit) {
		return choDAO.getChoTourList(areaCode, sigunguCode, contentType, title, order, offset, limit);
	}
	
	@Override
	public int getTourListCount(String areaCode, String sigunguCode, String contentType, String title) {
		return choDAO.getTourListCount(areaCode, sigunguCode, contentType, title);
	}
	
	@Override
	public List<PlaceWishVO> getPlaceWishList(String u_idx) {
		return choDAO.getPlaceWishList(u_idx);
	}
	
	@Override
	public MemberVO getUserLogin(String string) {
		return choDAO.getUserLogin(string);
	}
	
	@Override
	public int getPlaceWishAdd(String contentid, String u_idx) {
		return choDAO.getPlaceWishAdd(contentid,u_idx);
	}
	
	@Override
	public int getPlaceWishRemove(String contentid, String u_idx) {
		return choDAO.getPlaceWishRemove(contentid, u_idx);
	}
	
	@Override
	public int dataUpdate(List<TourapiVO> voList) {
		return choDAO.dataUpdate(voList);
	}
}
