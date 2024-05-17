package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.lee.dao.MemberVO;

public interface KoService {
	
	List<KoPostVO> getAreaList(String r_areacode);
	
	List<KoPostVO> getTemaList(String r_contenttypeid);
	
	List<KoPostVO> getPathList(String contentid);
	
	ItemVO getPlaceDetail(String contentid);
	
	// =============================================
	//	팝업관련
	int popupInsert(PopupVO popvo);
	
	List<PopupVO> popupList(int offset, int limit);
	
	PopupVO popupOne();
	
	int popupUpdate(String popup_idx);
	
	int popupDelete(String popup_idx);
	
	int getTotalCount();
	
	//================================================
	//	유저관리
	int getTotalUser();
	
	List<MemberVO> getUserList(int offset, int limit);
	
}
