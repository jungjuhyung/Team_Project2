package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PageVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.ko.dao.UserStopVO;
import com.ict.travel.ko.dao.UserVO;

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
	
	int getSearchTotal(String search);
	
	List<UserVO> getStopUser();
	
	List<UserVO> getUserList(int offset, int limit);
	
	UserStopVO getStopDetail(String u_idx);
	
	List<UserVO> getSearchUser(PageVO pvo);
	
	//	정지 상태 변경
	int getStopState(String u_idx);
	//	정지하기
	int getStopUpdate(String stop_days, String u_idx, String stop_note, String admin_idx);
	
	//=================================================
	// 특정 유저 작성글 
	UserVO getUserDetail(String u_idx);
	
	int getBoardCount(String u_idx);
	
	List<BoardVO> getBoardList(String u_idx, int offset, int limit);

	int getReportCount(String u_idx);
	
	List<ReportVO> getReportList(String u_idx, int offset, int limit);
	
	int getPathCount(String u_idx);
	
	List<KpostVO> getPathList(String u_idx, int offset, int limit);
	
	
	int getCommentCount(String u_idx);
	
	List<CommentVO> getCommentList(String u_idx, int offset, int limit);
	
	
	
	
}













