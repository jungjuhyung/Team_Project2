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
	public ModelAndView test(HttpSession session) {
		ModelAndView mv = new ModelAndView("test");
		
		MemberVO uvo = choService.getUserLogin("1");
		
		session.setAttribute("userVO", uvo);
		
		return mv;
	}
}
