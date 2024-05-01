package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.ReportVO;


public interface ReportService {

	public int getTotalCount();

	public List<ReportVO> reportList(int offset, int limit);
	
	public List<ReportVO> reportList();
}
