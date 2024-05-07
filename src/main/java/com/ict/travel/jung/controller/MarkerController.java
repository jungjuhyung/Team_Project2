package com.ict.travel.jung.controller;



import java.lang.reflect.Field;
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
import com.ict.travel.jung.dao.RecommendMarkerVO;
import com.ict.travel.jung.dao.RecommendVO;
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
	
	@RequestMapping("recommend_write_ok")
	public ModelAndView recommend_write_ok(RecommendVO rcvo, RecommendMarkerVO rcmvo) {
		ModelAndView mv = new ModelAndView("jung_view/test");
		for (String k : rcmvo.getContentid()) {
			System.out.println(k);
		}
		for (Field k : rcmvo.getClass().getDeclaredFields()) {
		    try {
		    	k.setAccessible(true);
				if (k.get(rcmvo) == null) {
				    System.out.println(k.getName() + " is null.");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
		return mv;
	}
}
