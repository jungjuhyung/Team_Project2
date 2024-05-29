package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.MainPageDAO;
import com.ict.travel.ko.dao.PopupVO;

@Service
public class MainPageServiceImple implements MainPageService{
	
	@Autowired
	private MainPageDAO mainPageDAO;
	
	
	@Override
	public List<KoPostVO> getAreaList(String r_areacode) {
		return mainPageDAO.getAreaList(r_areacode);
	}

	@Override
	public List<KoPostVO> getTemaList(String r_contenttypeid) {
		return mainPageDAO.getTemaList(r_contenttypeid);
	}
	
	@Override
	public PopupVO popupOne() {
		return mainPageDAO.popupOne();
	}
	
}
