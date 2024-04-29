package com.ict.travel.kim.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.TourtestVO;
import com.ict.travel.kim.service.TourtestService;

@Controller
public class PathrvController {

	@Autowired
	private TourtestService tourtestService;
	
	@GetMapping("pathDetail")
	public ModelAndView pathDetail(String place_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/pathDetail");
		TourtestVO tourtestvo = tourtestService.tourDetail(place_idx);
		
		if(tourtestvo != null) {
			mv.addObject("tourtestvo", tourtestvo);
			return mv;
		}
		
		return mv;
	}
	
	
	
}
