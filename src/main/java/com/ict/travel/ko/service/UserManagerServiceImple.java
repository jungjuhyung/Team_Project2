package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.PageVO;
import com.ict.travel.ko.dao.UserManagerDAO;
import com.ict.travel.ko.dao.UserStopVO;
import com.ict.travel.ko.dao.UserVO;

@Service
public class UserManagerServiceImple implements UserManagerService{
	
	@Autowired
	private UserManagerDAO userManagerDAO;
	
	@Override
	public int getTotalUser() {
		return userManagerDAO.getTotalUser();
	}
	
	@Override
	public int getSearchTotal(String search) {
		return userManagerDAO.getSearchTotal(search);
	}
	
	@Override
	public List<UserVO> getStopUser() {
		return userManagerDAO.getStopUser();
	}
	
	@Override
	public List<UserVO> getUserList(int offset, int limit) {
		return userManagerDAO.getUserList(offset, limit);
	}
	
	@Override
	public UserStopVO getStopDetail(String u_idx) {
		return userManagerDAO.getStopDetail(u_idx);
	}
	
	@Override
	public List<UserVO> getSearchUser(PageVO pvo) {
		return userManagerDAO.getSearchUser(pvo);
	}
	
	@Override
	public int getStopState(String u_idx, String ustop_idx) {
		return userManagerDAO.getStopState(u_idx, ustop_idx);
	}
	
	@Override
	public int getStopUpdate(String stop_days, String u_idx, String stop_note, String admin_id) {
		return userManagerDAO.getStopUpdate(stop_days, u_idx, stop_note, admin_id);
	}
	
}
