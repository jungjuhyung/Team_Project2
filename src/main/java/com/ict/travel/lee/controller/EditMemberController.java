package com.ict.travel.lee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MemberService;

@Controller
public class EditMemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	
//	@RequestMapping("my_edit.do")
//	public ModelAndView getMemberUp(String u_idx) {
//		ModelAndView mv = new ModelAndView("lee_view/editMember");
//		MemberVO mvo = memberService.getMemberUpDetail(u_idx);
//		if(mvo != null) {
//			mv.addObject("mvo", mvo);
//			return mv;
//		}
//		return new ModelAndView("lee_view/error");
//	}
//	
//	@RequestMapping("my_edit_ok.do")
//	public ModelAndView getMemberUpOk(MemberVO mvo) {
//		ModelAndView mv = new ModelAndView();
//		
//		MemberVO mvo2 = memberService.getMemberUpDetail(mvo.getU_idx());
//		if(mvo2 == null) {
//			mv.addObject("mvo", mvo2);
//			mv.setViewName("jung_view/mypage");
//			return mv;
//		}
//		mvo2.setU_nickname(mvo.getU_nickname());
//		mvo2.setU_self(mvo.getU_self());
//		System.out.println(mvo2);
//		
//		int result = memberService.getMemberUpOk(mvo);
//		if(result > 0 ) {
//			mv.setViewName("redirect:my_edit.do?idx="+mvo.getU_idx());
//			return mv;
//		}
//		return new ModelAndView("error");
//	}
	
	
	
	
}
