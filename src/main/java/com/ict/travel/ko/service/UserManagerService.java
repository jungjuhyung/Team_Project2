package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.ko.dao.PageVO;
import com.ict.travel.ko.dao.UserStopVO;
import com.ict.travel.ko.dao.UserVO;

public interface UserManagerService {

	int getTotalUser();

	int getSearchTotal(String search);

	List<UserVO> getStopUser();

	List<UserVO> getUserList(int offset, int limit);

	UserStopVO getStopDetail(String u_idx);

	List<UserVO> getSearchUser(PageVO pvo);

	// 정지 상태 변경
	int getStopState(String u_idx, String ustop_idx);

	// 정지하기
	int getStopUpdate(String stop_days, String u_idx, String stop_note, String admin_id);

}
