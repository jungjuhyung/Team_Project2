package com.ict.travel.lee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MemberService;

@Controller
public class EditMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@RequestMapping("my_edit.do")
	public ModelAndView getMemberUp(String u_idx) {
		ModelAndView mv = new ModelAndView("lee_view/editMember");
		MemberVO mvo = memberService.getMemberUpDetail(u_idx);
		if(mvo != null) {
			mv.addObject("mvo", mvo);
			return mv;
		}
		return new ModelAndView("");
	}
	
	@RequestMapping("my_edit_ok.do")
	public ModelAndView getMemberUpOk(MemberVO mvo) {
		ModelAndView mv = new ModelAndView();
		
		String passup = passwordEncoder.encode(mvo.getU_pwd());
		mvo.setU_pwd(passup);
		
		
		int result = memberService.getMemberUpOk(mvo);
		if(result > 0 ) {
			mv.setViewName("redirect:my_edit.do?u_idx="+mvo.getU_idx());
//			mv.setViewName("jung_view/mypage");
			return mv;
		}
		return new ModelAndView("error");
	}
	
//	@RequestMapping("new_pass.do")
//	public ModelAndView getNewPwd(String u_pwd) {
//		try {
//			ModelAndView mv = new ModelAndView("lee_view/newPwd");
//			return mv;
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return new ModelAndView("error");
//		
//	}
	
	
//	@RequestMapping("pwd_up_ok.do")
//	public ModelAndView getNewpass(MemberVO mvo) {
//		try {
//			ModelAndView mv = new ModelAndView("lee_view/editMember");
//			
//			
//			
//			return mv;
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return null;
//	}
	
	
	
	
	
	
}
