package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.PopupDAO;
import com.ict.travel.ko.dao.PopupVO;

@Service
public class PopupServiceImple implements PopupService {

	@Autowired
	private PopupDAO popupDAO;

	@Override
	public int popupInsert(PopupVO popvo) {
		return popupDAO.popupInsert(popvo);
	}

	@Override
	public List<PopupVO> popupList(int offset, int limit) {
		return popupDAO.popupList(offset, limit);
	}

	@Override
	public int popupUpdate(String popup_idx) {
		return popupDAO.popupUpdate(popup_idx);
	}

	@Override
	public int popupDelete(String popup_idx) {
		return popupDAO.popupDelete(popup_idx);
	}

	@Override
	public int getTotalCount() {
		return popupDAO.getTotalCount();
	}
}
