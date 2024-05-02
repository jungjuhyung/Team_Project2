package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.ReportVO;


public interface ReportService {

	public int getTotalCount();

	public List<ReportVO> reportList(int offset, int limit);
	
	public List<ReportVO> reportList();
	
	public int reportWrite(ReportVO reportvo);
	
	public ReportVO reportDetail(String report_idx);
	
	public int reportDelete(String report_idx);

	public int reportUpdate(ReportVO reportvo);

	/*
	 * public List<CommentVO> commentList(String report_idx);
	 * 
	 * public int commentInsert(CommentVO commentvo);
	 * 
	 * public int commentDelete(String comment_idx);
	 */
}
