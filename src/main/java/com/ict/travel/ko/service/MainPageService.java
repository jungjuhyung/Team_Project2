package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PopupVO;

public interface MainPageService {
	
	List<KoPostVO> getAreaList(String r_areacode);
	
	List<KoPostVO> getTemaList(String r_contenttypeid);

	PopupVO popupOne();

}
