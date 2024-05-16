package com.ict.travel.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.kim.service.ReportService;
import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MemberService;

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
	public String getReportList(HttpServletRequest request,
			@RequestParam("page") String page
			) {
		
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		
		
		
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
		String cPage = page;
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
		List<ReportVO> list = reportService.reportList(paging.getOffset(), paging.getNumPerPage());
		
		if (list != null) {
			// xml 만들기
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<reports>");
			
			// 세션 정보 추가
	        if (membervo != null) {
	            sb.append("<sessionInfo>");
	            sb.append("<userGrade>").append(membervo.getU_grade()).append("</userGrade>");
	            // 다른 세션 정보들도 필요한 경우에 추가할 수 있습니다.
	            sb.append("</sessionInfo>");
	        }
			
			
			
			  // 페이징 정보 추가 1111111111111111
			  sb.append("<paging>"); 
			  sb.append("<nowPage>" + paging.getNowPage() + "</nowPage>"); 
			  sb.append("<endBlock>" + paging.getEndBlock() + "</endBlock>"); 
			  sb.append("<beginBlock>" + paging.getBeginBlock() + "</beginBlock>"); 
			  sb.append("<totalPage>" + paging.getTotalPage() + "</totalPage>"); sb.append("</paging>");
			
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
			return sb.toString();
			
			
		}
		return "fail";
		
	}
	
	
	@GetMapping("reportWrite")
	public ModelAndView reportWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/reportWrite");
		
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		mv.addObject("membervo", membervo);
		
		return mv;
	}
	
	@PostMapping("reportWriteOK")
	public ModelAndView reportWriteOK(ReportVO reportvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:getReportgo");
			String pwd = passwordEncoder.encode(reportvo.getReport_pw());
			String bad_id = reportvo.getReported_id();
			reportvo.setReport_pw(pwd);;
			String u_idx = reportvo.getU_idx();
			reportvo.setU_idx(u_idx);
			
			HttpSession session = request.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
			mv.addObject("membervo", membervo);
			reportvo.setU_id(membervo.getU_id());
			reportvo.setU_idx(membervo.getU_idx());
			
			ReportVO reportvo2 = reportService.baduser(bad_id);
			if (reportvo2 == null) {
				System.out.println("여기 와?");
				mv.setViewName("kim_view/reportWrite");
			    mv.addObject("reportvo", reportvo);
			    mv.addObject("membervo", membervo);
			    System.out.println(membervo.getU_id());
			    mv.addObject("badid", "fail");
			    return mv;
			}
			reportvo.setReported_idx(reportvo2.getU_idx());
			
			int result = reportService.reportWrite(reportvo);
			if(result > 0 ) {
				mv.addObject("reportvo", reportvo);
				return mv;
			}
			return new ModelAndView("");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("");
	}
	
	@GetMapping("reportDetail")
	public ModelAndView reportDetail(String report_idx, String cPage, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("kim_view/reportDetail");
			ReportVO reportvo = reportService.reportDetail(report_idx);
			HttpSession session = request.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
			mv.addObject("membervo", membervo);
			if(membervo == null || !membervo.getU_idx().equals(reportvo.getU_idx())) {
				return new ModelAndView("redirect:getReportgo");
				
			}
			if (reportvo !=null) {
				
				mv.addObject("membervo", membervo);
				mv.addObject("reportvo", reportvo);
				mv.addObject("cPage", cPage);
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("kim_view/reportDetail");
	}
	
	
	@PostMapping("reportUpdate")
	public ModelAndView getReportUpdate(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("report_idx")String report_idx) {
		ModelAndView mv = new ModelAndView("kim_view/reportUpdate");
		ReportVO reportvo = reportService.reportDetail(report_idx);
		if (reportvo != null) {
			mv.addObject("reportvo", reportvo);
			return mv;
		}
		
		
		return new ModelAndView("report/error");
	}
	
	@RequestMapping("reportUpdateOK")
	public ModelAndView getReportUpdateOK(
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("report_idx")String report_idx,
			@ModelAttribute("report_cpw")String report_cpw,
			ReportVO reportvo
			) {
		ModelAndView mv = new ModelAndView();
		ReportVO reportvo2 = reportService.reportDetail(report_idx);
		String dpwd = reportvo2.getReport_pw();
		if (! passwordEncoder.matches(report_cpw, dpwd)) {
			mv.addObject("pwdchk", "fail");
			mv.addObject("reportvo", reportvo);
			mv.setViewName("kim_view/reportUpdate");
			return mv;
		}else {
							
			int result = reportService.reportUpdate(reportvo);
			if (result>0) {
				mv.setViewName("redirect:reportDetail");
				return mv;
			}
				
			
		}
				
		return new ModelAndView("");
	}
	
	@PostMapping("reportDelete")
	public ModelAndView getBbsDelete(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("report_idx")String report_idx,
			@ModelAttribute("report_cpw")String report_cpw) {
		return new ModelAndView("kim_view/reportDelete");
	}
	
	
	@PostMapping("reportDeleteOK")
	public ModelAndView reportDeleteOK(@ModelAttribute("report_cpw")String report_cpw,
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("report_idx")String report_idx,
			ReportVO reportvo) {
		ModelAndView mv = new ModelAndView();
		ReportVO reportvo2 = reportService.reportDetail(report_idx);
		String dpwd = reportvo2.getReport_pw();
		if(! passwordEncoder.matches(report_cpw, dpwd)) {
			mv.setViewName("kim_view/reportDelete");
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			
			int result = reportService.reportDelete(report_idx);
			if(result > 0) {
				mv.setViewName("redirect:getReportgo");
				return mv;
			}
		}
		return new ModelAndView("report/error");
		
	}
	
	@PostMapping("reportConfirm")
	public ModelAndView reportConfirm(String report_idx) {
		ModelAndView mv = new ModelAndView();
		int result = reportService.reportState(report_idx);
		if(result > 0) {
			mv.setViewName("redirect:getReportgo");
			return mv;
		}
		
		return new ModelAndView("report/error");
	}
	
	
	

	
	
}
