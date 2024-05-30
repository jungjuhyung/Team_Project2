package com.ict.travel.cho.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;

@Controller
public class ViewMoveController {
	
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

	// 관리자 관리 페이지
	@RequestMapping("adminManage")
	public ModelAndView adminManage() {
		ModelAndView mv = new ModelAndView("cho_views/adminManage");
		return mv;
	}

}
