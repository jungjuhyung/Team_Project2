package com.ict.travel.jung.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.jung.dao.MarkerInfoVO;
import com.ict.travel.jung.dao.MarkersVO;
import com.ict.travel.jung.service.MarkerService;

@Controller
public class MarkerController {
	
	@Autowired
	private MarkerService marService;
	
	@RequestMapping("recommend_write_go")
	public ModelAndView recommend_write_go(MarkersVO mvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("jung_view/kakaomap");
		List<MarkerInfoVO> marker_list = new ArrayList<MarkerInfoVO>();
		for (String k : mvo.getContentid()) {
			System.out.println(k);
			MarkerInfoVO mivo = marService.getMarkerInfo(k);
			marker_list.add(mivo);
		}
		
		mv.addObject("marker_list", marker_list);
		return mv;
	}
	
	@RequestMapping("test")
	public ModelAndView test() {
		return new ModelAndView("jung_view/kakaoline");
	}
}
