package com.ict.travel.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.cho.service.ChoService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class CommonController {
	
	@Autowired
	private ChoService choService;
	
	@RequestMapping("/")
	public ModelAndView firstpage(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:main_page.do");
		return mv;
	}
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:main_page.do");
		session.removeAttribute("memberUser");
		session.removeAttribute("u_id");
		session.removeAttribute("u_idx");
		
		session.removeAttribute("adminUser");
		session.removeAttribute("admin_id");
		session.removeAttribute("admin_idx");
		session.removeAttribute("admin_grade");
		
		return mv;
	}
}
