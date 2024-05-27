package com.ict.travel.ko.service;

import java.util.List;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.ko.dao.UserVO;

public interface UserDetailService {

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
