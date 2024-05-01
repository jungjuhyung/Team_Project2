package com.ict.travel.jung.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.jung.dao.MarkerInfoVO;
import com.ict.travel.jung.dao.WishListVO;
import com.ict.travel.jung.service.MarkerService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class MarkerController {
	
	@Autowired
	private MarkerService marService;
	
	@RequestMapping("recommend_write_go")
	public ModelAndView recommend_write_go(HttpSession session) {
		ModelAndView mv = new ModelAndView("jung_view/kakaomap");
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		List<WishListVO> marker_list = marService.getWishList(uvo.getU_idx());
		mv.addObject("marker_list", marker_list);
		return mv;
	}
	
	@RequestMapping("test")
	public ModelAndView test() {
		return new ModelAndView("jung_view/gpt_test");
	}
	
}
