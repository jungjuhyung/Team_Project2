package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;

public interface PlaceDetailService {

	List<KoPostVO> getPathList(String contentid);

	ItemVO getPlaceDetail(String contentid);

}
