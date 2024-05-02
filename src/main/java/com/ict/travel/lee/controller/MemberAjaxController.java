package com.ict.travel.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.lee.service.MemberService;

@RestController
public class MemberAjaxController {

	@Autowired
	private MemberService memberService;
	
//	@RequestMapping(value = "getAjaxId.do", produces = "text/html; charset=utf-8")
//	@ResponseBody
//	public String getAjaxId() {
//		
//			
//		
//	}
}
