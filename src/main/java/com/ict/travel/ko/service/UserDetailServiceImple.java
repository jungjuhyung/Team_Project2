package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.ko.dao.UserDetailDAO;
import com.ict.travel.ko.dao.UserVO;

@Service
public class UserDetailServiceImple implements UserDetailService {
	
	@Autowired
	private UserDetailDAO userDetailDAO;

	@Override
	public UserVO getUserDetail(String u_idx) {
		return userDetailDAO.getUserDetail(u_idx);
	}

	@Override
	public int getBoardCount(String u_idx) {
		return userDetailDAO.getBoardCount(u_idx);
	}

	@Override
	public List<BoardVO> getBoardList(String u_idx, int offset, int limit) {
		return userDetailDAO.getBoardList(u_idx, offset, limit);
	}

	@Override
	public int getReportCount(String u_idx) {
		return userDetailDAO.getReportCount(u_idx);
	}

	@Override
	public List<ReportVO> getReportList(String u_idx, int offset, int limit) {
		return userDetailDAO.getReportList(u_idx, offset, limit);
	}

	@Override
	public int getPathCount(String u_idx) {
		return userDetailDAO.getPathCount(u_idx);
	}

	@Override
	public List<KpostVO> getPathList(String u_idx, int offset, int limit) {
		return userDetailDAO.getPathList(u_idx, offset, limit);
	}

	@Override
	public int getCommentCount(String u_idx) {
		return userDetailDAO.getCommentCount(u_idx);
	}

	@Override
	public List<CommentVO> getCommentList(String u_idx, int offset, int limit) {
		return userDetailDAO.getCommentList(u_idx, offset, limit);
	}
}
