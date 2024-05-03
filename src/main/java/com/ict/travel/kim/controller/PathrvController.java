package com.ict.travel.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.kim.service.TourtestService;

@RestController
public class PathrvController {

	@Autowired
	private TourtestService tourtestService;
	
	@RequestMapping(value = "pathDetail", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String pathDetail(HttpServletRequest request,
			String place_idx) {
		
		return null;
	}
	
	
	
}
