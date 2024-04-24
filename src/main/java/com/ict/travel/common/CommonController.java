package com.ict.travel.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	@RequestMapping("/")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}
}
