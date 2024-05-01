package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.ReportDAO;
import com.ict.travel.kim.dao.ReportVO;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDAO reportDAO;
	

	@Override
	public int getTotalCount() {
		return reportDAO.getTotalCount();
	}

	@Override
	public List<ReportVO> reportList(int offset, int limit) {
		return reportDAO.reportList(offset, limit);
	}

	@Override
	public List<ReportVO> reportList() {
		return reportDAO.reportList();
	}
}