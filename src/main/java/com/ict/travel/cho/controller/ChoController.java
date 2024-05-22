package com.ict.travel.cho.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class ChoController {
	
	@Autowired
	private ChoService choService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// 검색
	@GetMapping("searchResult")
	public ModelAndView searchResult(HttpSession session) {
		ModelAndView mv = new ModelAndView("cho_views/searchResult");
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if(uvo !=null) {
			mv.addObject("userLogin", "ok");
		}
		return mv;
	}
	// 지역 카테고리
	@GetMapping("themaCategory")
	public ModelAndView themaCategory(HttpSession session) {
		ModelAndView mv = new ModelAndView("cho_views/themaCategory");
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if(uvo !=null) {
			mv.addObject("userLogin", "ok");
		}
		return mv;
	}
	
	// 추천경로 카테고리
	@GetMapping("pathCategory")
	public ModelAndView pathCategory(HttpSession session) {
		ModelAndView mv = new ModelAndView("cho_views/pathCategory");
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if(uvo !=null) {
			mv.addObject("userLogin", "ok");
		}
		return mv;
	}
	
	//DB 업데이트 
		@GetMapping("dbUpdateTest")
		public ModelAndView dbUpdateTest(HttpSession session) {
			ModelAndView mv = new ModelAndView("cho_views/dbUpdateTest");
			MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
			if(uvo !=null) {
				mv.addObject("userLogin", "ok");
			}
			return mv;
		}
	
	// 검색 번역기
	@GetMapping("sideBar")
	public ModelAndView translateTest(HttpSession session) {
		ModelAndView mv = new ModelAndView("cho_views/sideBar");
		return mv;
	}
	
	
	// 관리자 로그인 페이지
//	@RequestMapping("adminLogin")
//	public ModelAndView adminLogin() {
//		ModelAndView mv = new ModelAndView("cho_views/adminLogin");
//		return mv;
//	}
	
	// 관리자 로그인 체크
	@PostMapping("adminLoginOK")
	public ModelAndView adminLoginOK(HttpSession session ,AdminVO adminVO) {
		ModelAndView mv = new ModelAndView("redirect:main_page.do");
		AdminVO adminVO2 = choService.getAdminLogin(adminVO);
		
		if(adminVO2 == null) {
			mv.setViewName("redirect:adminLogin");
			return mv;
		}else {
			if(! passwordEncoder.matches(adminVO.getAdmin_pwd(), adminVO2.getAdmin_pwd())) {
				mv.setViewName("redirect:adminLogin");
				return mv;
			}else  {
				session.setAttribute("adminUser", adminVO2);
				session.setAttribute("u_id", adminVO2.getAdmin_id());
				session.setAttribute("u_idx", adminVO2.getAdmin_idx());
				session.setAttribute("u_grade", adminVO2.getAdmin_grade());
			} 
			return mv;
		}
	}
	
	// 관리자 관리 페이지
	@RequestMapping("adminManage")
	public ModelAndView adminManage() {
		ModelAndView mv = new ModelAndView("cho_views/adminManage");
		return mv;
	}
	
//	// 관리자 생성
//	@RequestMapping("CreateAdmin")
//	public ModelAndView CreateAdmin() {
//		ModelAndView mv = new ModelAndView("cho_views/CreateAdmin");
//		return mv;
//	}
	
}
