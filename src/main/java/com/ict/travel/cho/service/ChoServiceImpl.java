package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.dao.ChoDAO;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchVO;
import com.ict.travel.cho.dao.TourapiVO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class ChoServiceImpl implements ChoService{
	@Autowired
	private ChoDAO choDAO;
	
	@Override
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit) {
		return choDAO.getChoTourList(areaCode, sigunguCode, contentType, title, order, type, offset, limit);
	}
	
	@Override
	public int getTourListCount(String areaCode, String sigunguCode, String contentType, String title, String type) {
		return choDAO.getTourListCount(areaCode, sigunguCode, contentType, title, type);
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
	
	@Override
	public List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		return choDAO.getChoTourPathList(areaCode, sigunguCode, contentType, title, order, type , offset, limit);
	}
	@Override
	public List<PathWishVO> getpathWishList(String u_idx) {
		return choDAO.getpathWishList(u_idx);
	}
	
	@Override
	public int getPathWishAdd(String path_post_idx, String u_idx) {
		return choDAO.getPathWishAdd(path_post_idx, u_idx);
	}
	@Override
	public int getPathWishRemove(String path_post_idx, String u_idx) {
		return choDAO.getPathWishRemove(path_post_idx, u_idx);
	}
	
	@Override
	public List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		return choDAO.getSearchTotal(areaCode, sigunguCode, contentType, title, order, type, offset, limit);
	}
	
	@Override
	public AdminVO getAdminLogin(AdminVO adminVO) {
		return choDAO.getAdminLogin(adminVO);
	}
	@Override
	public List<AdminVO> getAdminList() {
		return choDAO.getAdminList();
	}
	@Override
	public int adminDelete(String admin_idx) {
		return choDAO.adminDelete(admin_idx);
	}
	@Override
	public String getLoginChk(String admin_id) {
		return choDAO.getLoginChk(admin_id);
	}
	@Override
	public String adminCreate(AdminVO adminVO) {
		return choDAO.adminCreate(adminVO);
	}
}

