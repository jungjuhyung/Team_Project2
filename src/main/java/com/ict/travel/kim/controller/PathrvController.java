package com.ict.travel.kim.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PathrvController {

	
	
	@GetMapping("pathDetail2")
	public ModelAndView pathDetail(@ModelAttribute("left")String left, 
			@ModelAttribute("right")String right, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/pathDetail2");
		System.out.println("��ǥ1 : " + left);
		System.out.println("��ǥ2 : " + right);
		return mv;
	}
	
	
	
}
