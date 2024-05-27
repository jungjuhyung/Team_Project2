package com.ict.travel.ko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OtherController {

	// 풋터 이동
	@RequestMapping("footer_terms.do")
	public ModelAndView getTerms() {
		return new ModelAndView("ko_view/footer_terms");
	}

	@RequestMapping("footer_location.do")
	public ModelAndView getLoc() {
		return new ModelAndView("ko_view/footer_location");
	}

	@RequestMapping("footer_email.do")
	public ModelAndView getEmail() {
		return new ModelAndView("ko_view/footer_email");
	}

	// 관리자 첫 페이지
	@RequestMapping("adminpage")
	public ModelAndView adminPage() {
		return new ModelAndView("ko_view/admin_page");
	}
	

}
