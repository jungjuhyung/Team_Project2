package com.ict.travel.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChoController {
	@GetMapping("searchResult")
	public ModelAndView searchResult() {
		return new ModelAndView("cho_views/searchResult");
	}
	@GetMapping("themaCategory")
	public ModelAndView themaCategory() {
		return new ModelAndView("cho_views/themaCategory");
	}
	@GetMapping("dbUpdateTest")
	public ModelAndView dbUpdateTest() {
		return new ModelAndView("cho_views/dbUpdateTest");
	}
}
