package com.ict.travel.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChoController {
	@GetMapping("searchResult")
	public ModelAndView searchResult() {
		System.out.println(1);
		return new ModelAndView("cho_views/searchResult");
	}
	@GetMapping("themaCategory")
	public ModelAndView themaCategory() {
		System.out.println(1);
		return new ModelAndView("cho_views/themaCategory");
	}
}
