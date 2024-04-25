package com.ict.travel.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChoController {
	@GetMapping("areaCategory")
	public ModelAndView areaCategory() {
		return new ModelAndView("cho_views/areaCategory");
	}
}
