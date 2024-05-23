package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoDAO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PageVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.ko.dao.UserStopVO;
import com.ict.travel.ko.dao.UserVO;

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
	
	// ========================================================
	
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
	
	// ========================================================
	
	@Override
	public int getTotalUser() {
		return koDAO.getTotalUser();
	}
	
	@Override
	public int getSearchTotal(String search) {
		return koDAO.getSearchTotal(search);
	}
	
	@Override
	public List<UserVO> getStopUser() {
		return koDAO.getStopUser();
	}
	
	@Override
	public List<UserVO> getUserList(int offset, int limit) {
		return koDAO.getUserList(offset, limit);
	}
	
	@Override
	public UserStopVO getStopDetail(String u_idx) {
		return koDAO.getStopDetail(u_idx);
	}
	
	@Override
	public List<UserVO> getSearchUser(PageVO pvo) {
		return koDAO.getSearchUser(pvo);
	}
	
	@Override
	public int getStopState(String u_idx) {
		return koDAO.getStopState(u_idx);
	}
	
	@Override
	public int getStopUpdate(String stop_days, String u_idx, String stop_note, String admin_idx) {
		return koDAO.getStopUpdate(stop_days, u_idx, stop_note, admin_idx);
	}
	
	//====================================================
	@Override
	public UserVO getUserDetail(String u_idx) {
		return koDAO.getUserDetail(u_idx);
	}
	
	@Override
	public int getBoardCount(String u_idx) {
		return koDAO.getBoardCount(u_idx);
	}
	
	@Override
	public List<BoardVO> getBoardList(String u_idx, int offset, int limit) {
		return koDAO.getBoardList(u_idx, offset, limit);
	}
	
	@Override
	public int getReportCount(String u_idx) {
		return koDAO.getReportCount(u_idx);
	}
	
	@Override
	public List<ReportVO> getReportList(String u_idx, int offset, int limit) {
		return koDAO.getReportList(u_idx, offset, limit);
	}
	
	@Override
	public int getPathCount(String u_idx) {
		return koDAO.getPathCount(u_idx);
	}
	
	@Override
	public List<KpostVO> getPathList(String u_idx, int offset, int limit) {
		return koDAO.getPathList(u_idx, offset, limit);
	}
	
	@Override
	public int getCommentCount(String u_idx) {
		return koDAO.getCommentCount(u_idx);
	}
	
	@Override
	public List<CommentVO> getCommentList(String u_idx, int offset, int limit) {
		return koDAO.getCommentList(u_idx, offset, limit);
	}
	
	
	
}

















