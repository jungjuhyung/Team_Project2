package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.BaduserVO;
import com.ict.travel.kim.dao.CommentVO;
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

	@Override
	public int reportWrite(ReportVO reportvo) {
		return reportDAO.reportWrite(reportvo);
	}

	@Override
	public ReportVO reportDetail(String report_idx) {
		return reportDAO.reportDetail(report_idx);
	}

	@Override
	public int reportDelete(String report_idx) {
		return reportDAO.reportDelete(report_idx);
	}

	@Override
	public int reportUpdate(ReportVO reportvo) {
		return reportDAO.reportUpdate(reportvo);
	}

	@Override
	public int reportState(String report_idx) {
		return reportDAO.reportState(report_idx);
	}

	@Override
	public ReportVO baduser(String reported_id) {
		return reportDAO.baduser(reported_id);
	}


}
