package com.ict.travel.lee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		int result = memberService.getMemberUpOk(mvo);
		if(result > 0 ) {
			mv.setViewName("redirect:my_edit.do?u_idx="+mvo.getU_idx());
//			mv.setViewName("jung_view/mypage");
			return mv;
		}
		return new ModelAndView("error");
	}
	
	// 비밀번호 변경
	@RequestMapping("new_pass.do")
	public ModelAndView getNewPwd(@ModelAttribute("mvo")MemberVO mvo) {
		try {
			ModelAndView mv = new ModelAndView("lee_view/newPwd");
			System.out.println("new_pass.do");
			System.out.println(mvo.getU_idx());
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
		
	}

	// 비밀번호 변경
	@RequestMapping("new_pass_ok.do")
	public ModelAndView getNewpass(MemberVO mvo) {
		ModelAndView mv = new ModelAndView();
		System.out.println(mvo.getU_idx());
		System.out.println(mvo.getU_pwd());
		String cpwd = passwordEncoder.encode(mvo.getU_pwd());
		mvo.setU_pwd(cpwd);
		
		int result = memberService.getNewPwd(mvo);
		if(result > 0) {
			mv.setViewName("redirect:my_edit.do?u_idx="+mvo.getU_idx());
			return mv;
		}
		return new ModelAndView("error");
		
	}
	
	
}
