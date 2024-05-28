package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.PopupVO;

public interface PopupService {

	int popupInsert(PopupVO popvo);

	List<PopupVO> popupList(int offset, int limit);

	int popupUpdate(String popup_idx);

	int popupDelete(String popup_idx);

	int getTotalCount();
}
