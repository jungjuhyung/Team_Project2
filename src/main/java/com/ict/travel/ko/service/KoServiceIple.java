package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoDAO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class KoServiceIple implements KoService{
	
	@Autowired
	private KoDAO koDAO;

	@Override
	public List<KoPostVO> getAreaList(String r_areacode) {
		return koDAO.getAreaList(r_areacode);
	}

	@Override
	public List<KoPostVO> getTemaList(String r_contenttypeid) {
		return koDAO.getTemaList(r_contenttypeid);
	}
	
	@Override
	public List<KoPostVO> getPathList(String contentid) {
		return koDAO.getPathList(contentid);
	}
	
	@Override
	public ItemVO getPlaceDetail(String contentid) {
		return koDAO.getPlaceDetail(contentid);
	}
	
	@Override
	public int popupInsert(PopupVO popvo) {
		return koDAO.popupInsert(popvo);
	}
	
	@Override
	public List<PopupVO> popupList(int offset, int limit) {
		return koDAO.popupList(offset, limit);
	}
	
	@Override
	public PopupVO popupOne() {
		return koDAO.popupOne();
	}
	
	@Override
	public int popupUpdate(String popup_idx) {
		return koDAO.popupUpdate(popup_idx);
	}
	
	@Override
	public int popupDelete(String popup_idx) {
		return koDAO.popupDelete(popup_idx);
	}
	
	@Override
	public int getTotalCount() {
		return koDAO.getTotalCount();
	}
	
	@Override
	public int getTotalUser() {
		return koDAO.getTotalUser();
	}
	
	@Override
	public List<MemberVO> getUserList() {
		return koDAO.getUserList();
	}
	
	@Override
	public List<MemberVO> getUserList(int offset, int limit) {
		return koDAO.getUserList(offset, limit);
	}
	
	@Override
	public int getStopUpdate(String u_idx) {
		return koDAO.getStopUpdate(u_idx);
	}
	
}
