package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.BaduserVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.ReportVO;


public interface ReportService {

	public int getTotalCount();

	public List<ReportVO> reportList(int offset, int limit);
	
	public List<ReportVO> reportList();
	
	public int reportWrite(ReportVO reportvo);
	
	public ReportVO reportDetail(String report_idx);
	
	public int reportDelete(String report_idx);

	public int reportUpdate(ReportVO reportvo);

	public int reportState(String report_idx, String admin_id);
	
	public int reportConfirm(String admin_id);
	
	public ReportVO baduser(String reported_id);

}
