package com.ict.travel.jung.controller;



import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.jung.dao.MarkerVO;

@Controller
public class JungController {
	
	@RequestMapping("recommend_write_go")
	public ModelAndView recommend_write_go(MarkerVO mvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("jung_view/kakaomap");
		mv.addObject("mvo", mvo);
		return mv;
	}
}
