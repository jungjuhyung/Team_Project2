package com.ict.travel.lee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MailService;
import com.ict.travel.lee.service.MemberService;


@Controller
public class EmailController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	
	
	@RequestMapping("email_send.do")
	public ModelAndView sendMail() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/email_form");
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	// 비밀번호 찾기 - 이메일 전송
//	@PostMapping("email_send_ok.do")
//	public ModelAndView sendMailOK(MemberVO memberVO ,String email) {
//		ModelAndView mv = new ModelAndView();
//		MemberVO memberVO2 = memberService.getLoginOK(memberVO);
//		System.out.println("1");
//		try {
//			if(memberVO2 != null && memberVO2.getU_id().equals(memberVO.getU_id()) && memberVO2.getU_email().equals(memberVO.getU_email())) {
//				// 임시 번호 만들기
//				Random random = new Random();
//				// 1000000 => 숫2ㅏ 6자리
//				String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
//				System.out.println("2");
//				if(randomNumber.length() <6) {
//					int substract = 6 - randomNumber.length();
//					StringBuffer sb = new StringBuffer();
//					for(int i = 0; i< substract; i++) {
//						sb.append("0");
//						System.out.println("3");
//					}
//					sb.append(randomNumber);
//					randomNumber = sb.toString();
//					System.out.println("4");
//				}
//				// 임시번호 서버에 출력
//				System.out.println("임시번호 : " + randomNumber);
//				String pwd = passwordEncoder.encode(randomNumber);
//				memberVO2.setU_pwd(pwd);
//				
//				int result = memberService.PassUpdate(memberVO2);
//				System.out.println(result);
//				if(result > 0) {
//					mailService.sendEmail(randomNumber, memberVO2.getU_email());
//					mv.addObject("msg", "메일 보내기성공");
//					mv.setViewName("lee_view/loginForm");
//					return mv;
//				}
//			}
//			System.out.println("5");
//			mv.addObject("msg", "안감");
//			mv.setViewName("lee_view/email_form");
//			return mv;
//		} catch (Exception e) {
//			System.out.println("메일전송 오류 캐치 : "+e);
//		}
//		return null;
//	}
	// 비밀번호 찾기 - 이메일 전송
	@PostMapping("email_send_ok.do")
	public ModelAndView sendMailOK(String u_id, String email) {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println(email);
			MemberVO mvo = memberService.getFindPW(u_id, email);
			if(mvo != null) {
				System.out.println(mvo.getU_email());
				// 임시 번호 만들기
				Random random = new Random();
				// 1000000 => 숫2ㅏ 6자리
				String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
				if(randomNumber.length() <6) {
					int substract = 6 - randomNumber.length();
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i< substract; i++) {
						sb.append("0");
					}
					sb.append(randomNumber);
					randomNumber = sb.toString();
				}
				// 임시번호 서버에 출력
				System.out.println("임시번호 : " + randomNumber);
				String pwd = passwordEncoder.encode(randomNumber);
				mvo.setU_pwd(pwd);
				mvo.setU_id(mvo.getU_id());
				
				int result = memberService.PassUpdate(mvo);
				
				System.out.println(result);
				if(result > 0) {
					mailService.sendEmail(randomNumber, email);
					mv.addObject("msg", "메일보내기 성공"); //
					mv.setViewName("lee_view/email_chk");
//						return new ModelAndView("lee_view/email_chk");
					return mv;
				}
				
			}else {
				mv.addObject("msg", "아이디나 이메일이 일치하지 않습니다. 다시 입력해주세요.");
				mv.setViewName("lee_view/email_form");
				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
		mv.addObject("msg", "시스템 오류가 발생했습니다. 다시 시도해주세요.");
		mv.setViewName("lee_view/email_form");
		return mv;
	}
	
	
		
	
	@PostMapping("email_pass_ok.do")
	public ModelAndView sentNumberOK() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/loginForm");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	@RequestMapping("id_email_send.do")
	public ModelAndView sendid() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/id_email_form");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	// 아이디 찾기
	@PostMapping("idFind_go.do")
	public ModelAndView getFindId(MemberVO mvo) {
			ModelAndView mv = new ModelAndView();
			List<MemberVO> list = memberService.getFindId(mvo);
			
			if(list != null && !list.isEmpty()) {
				
				mv.addObject("list", list);
				mv.setViewName("lee_view/id_find");
				return mv;
			} else {
				mv.addObject("message", "다시 입력해주세요.");
				mv.setViewName("lee_view/id_find");
				
			}
			return mv;
			
			
			
		
	}
	@PostMapping("backup.do")
	public ModelAndView backEmailFomr() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/loginForm");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	
	
}
