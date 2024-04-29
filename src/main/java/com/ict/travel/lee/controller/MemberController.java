package com.ict.travel.lee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MailService;
import com.ict.travel.lee.service.MemberService;
import java.util.Random;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private MailService mailService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("agree_go.do")
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
	
//	@RequestMapping("email_send_go")
//	public ModelAndView sendMail() {
//		return new ModelAndView("lee_view/joinForm");
//	}
//	@RequestMapping("email_send_ok.do")
//	public ModelAndView sendMailOK(String email) {
//		try {
//			// 임시 번호 만들기
//			Random random = new Random();
//			// 1000000 => 숫자 6자리
//			String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);  
//			if (randomNumber.length() < 6) {
//				int substract = 6 - randomNumber.length();
//				StringBuffer sb = new StringBuffer();
//				for (int i = 0; i < substract; i++) {
//					sb.append("0");
//				}
//
//				sb.append(randomNumber);
//				randomNumber = sb.toString();
//			}
//			// 임시번호 서버에 출력
//			System.out.println("임시번호 : "+ randomNumber);
//			
//			// 임시번호를 DB에 저장 해야 된다.
//			
//			// 사용자메일에 임시번호를 보내기
//			mailService.sendEmail(randomNumber, email);
//			// 성공하면 
//			return new ModelAndView("email/email_chk");
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return new ModelAndView("email/error");
//	}
	
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
			int result = memberService.getSignUp(mvo1);
			System.out.println(result);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
}
