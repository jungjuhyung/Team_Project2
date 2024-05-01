package com.ict.travel.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.kim.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;

	@RequestMapping(value = "getReportList", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String getReportList(HttpServletRequest request) {
		int count = reportService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if (paging.getTotalRecord() < paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (((paging.getTotalRecord()*1.0) / paging.getNumPerPage()) % 2 != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		// 현재 페이지 구하기
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 오라클 = begin, end
		// offset구하기 limit * 현재페이지-1
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() -1 ));
		
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock() + 1)
				);
		
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		// DB 처리 (전체정보 가져오기)
		List<ReportVO> list = reportService.reportList();
		
		if (list != null) {
			// xml 만들기
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<reports>");
			/*
			 * // 페이징 정보 추가 sb.append("<paging>"); sb.append("<nowPage>" +
			 * paging.getNowPage() + "</nowPage>"); sb.append("<endBlock>" +
			 * paging.getEndBlock() + "</endBlock>"); sb.append("<beginBlock>" +
			 * paging.getBeginBlock() + "</beginBlock>"); sb.append("<totalPage>" +
			 * paging.getTotalPage() + "</totalPage>"); sb.append("</paging>");
			 */
			for (ReportVO k : list) {
				sb.append("<report>");
				sb.append("<report_idx>"+k.getReport_idx()+"</report_idx>");
				sb.append("<u_id>"+k.getU_id()+"</u_id>");
				sb.append("<report_title>"+k.getReport_title()+"</report_title>");
				/* sb.append("<content>"+k.getContent()+"</content>"); */
				sb.append("<regdate>"+k.getRegdate().substring(0, 10)+"</regdate>");
				sb.append("<report_state>"+k.getReport_state()+"</report_state>");
				sb.append("</report>");
			}
			
			sb.append("</reports>");
			System.out.println("성공?");
			System.out.println(sb.toString());
			return sb.toString();
		}
		System.out.println("에러");
		return "fail";
		
	}
	
	
	@GetMapping("reportWrite")
	public ModelAndView reportWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/reportWrite");
		return mv;
	}
	
	@PostMapping("reportWriteOK")
	public ModelAndView reportWriteOK(ReportVO reportvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:getReportgo");
			String pwd = passwordEncoder.encode(reportvo.getReport_pw());
			String title = reportvo.getReport_title();
			reportvo.setReport_pw(pwd);;
			String u_idx = reportvo.getU_idx();
			reportvo.setU_idx(u_idx);
			
			int result = reportService.reportWrite(reportvo);
			if(result > 0) {
				return mv;
			}
			return new ModelAndView("");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
