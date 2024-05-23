package com.ict.travel.lee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MailService;
import com.ict.travel.lee.service.MemberService;
import java.util.Random;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChoService choService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("agree_go.do")
	public ModelAndView getAgreeMent() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/agreement");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	@RequestMapping("agree_acc.do")
	public ModelAndView getSignUp_2() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/joinForm");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	@RequestMapping(value = "getIdChk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getIdChk(String u_id) {
		String result = memberService.getIdChk(u_id);
		return result;
	}
	
	@RequestMapping(value = "getNickChk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getNickChk(String u_nickname) {
		String res = memberService.getNickChk(u_nickname);
		return res;
	}
	
	@RequestMapping("join_success_go.do")
	public ModelAndView getSignUp(MemberVO mvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("lee_view/loginForm");
			
			String u_id = request.getParameter("u_id");
			String u_pwd = request.getParameter("u_pwd");
			String u_name = request.getParameter("u_name");
			String u_birth = request.getParameter("u_birth");
			
			String u_email = request.getParameter("u_email");
			String u_gender = request.getParameter("u_gender");
			String u_nickname = request.getParameter("u_nickname");
			String u_self = request.getParameter("u_self");
			String n_status = request.getParameter("n_status");
			String k_status = request.getParameter("k_status");
			String u_pwd2 = passwordEncoder.encode(u_pwd);
	        
			MemberVO mvo1 = new MemberVO();
			mvo1.setU_id(u_id);
			mvo1.setU_pwd(u_pwd2);
			mvo1.setU_name(u_name);
			mvo1.setU_birth(u_birth);
			mvo1.setU_email(u_email);
			mvo1.setU_gender(u_gender);
			mvo1.setU_nickname(u_nickname);
			mvo1.setU_self(u_self);
			mvo1.setN_status(n_status);
			mvo1.setK_status(k_status);
			int result = memberService.getSignUp(mvo1);
			System.out.println(result);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	@RequestMapping("login_go_ok.do")
	public ModelAndView getLoginOK(HttpServletRequest request, MemberVO mvo) {
		try {
			HttpSession session = request.getSession();
			ModelAndView mv = new ModelAndView("redirect:main_page.do");
			AdminVO adminVO = new AdminVO();
			adminVO.setAdmin_id(mvo.getU_id());
			adminVO.setAdmin_pwd(mvo.getU_pwd());
			AdminVO adminVO2 = choService.getAdminLogin(adminVO);
			if(adminVO2 != null) {
			
				if(! passwordEncoder.matches(adminVO.getAdmin_pwd(), adminVO2.getAdmin_pwd())) {
					mv.setViewName("redirect:adminLogin");
					return mv;
				}else  {
					session.setAttribute("adminUser", adminVO2);
					session.setAttribute("admin_id", adminVO2.getAdmin_id());
					session.setAttribute("admin_idx", adminVO2.getAdmin_idx());
					session.setAttribute("admin_grade", adminVO2.getAdmin_grade());
					System.out.println(1);
					return mv;
				}
			}
			
			MemberVO mvo2 = memberService.getLoginOK(mvo);
			
			if( mvo2== null || ! passwordEncoder.matches(mvo.getU_pwd(), mvo2.getU_pwd())) {
				String errorMessage = "비밀번호가 틀렸습니다. 다시 시도해주세요.";
	            mv.addObject("errorMessage", errorMessage);
				mv.setViewName("lee_view/loginForm");
				return mv;
			}
			else {
				session.setAttribute("memberUser", mvo2);
				session.setAttribute("u_id", mvo2.getU_id());
				session.setAttribute("u_idx", mvo2.getU_idx());
				
				return new ModelAndView("redirect:main_page.do"); 
				

			}
		} catch (Exception e) {
			return new ModelAndView("error");
		}
	}
	
	
}





