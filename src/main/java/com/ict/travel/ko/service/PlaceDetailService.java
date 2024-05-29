package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;

public interface PlaceDetailService {

	List<KoPostVO> getPathList(String contentid);

	ItemVO getPlaceDetail(String contentid);
	
	int getPlaceWishAdd(String contentid, String u_idx);
	
	int getPlaceWishRemove(String contentid, String u_idx);

}
