package com.ict.travel.lee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ict.travel.lee.service.MemberService;

@Controller
public class EditMemberController {
	
	@Autowired
	private MemberService memberService;
	
}
