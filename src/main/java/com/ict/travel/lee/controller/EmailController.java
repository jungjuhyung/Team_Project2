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
	@PostMapping("email_send_ok.do")
	public ModelAndView sendMailOK(String email, String u_id) {
		try {
			System.out.println("1");
			System.out.println(email);
			System.out.println(u_id);
			MemberVO mvo = memberService.getFindPW(email);
			System.out.println(mvo.getU_email());
			if(mvo != null) {
				// 임시 번호 만들기
				Random random = new Random();
				// 1000000 => 숫2ㅏ 6자리
				String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
				System.out.println("안되니1");
				if(randomNumber.length() <6) {
					int substract = 6 - randomNumber.length();
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i< substract; i++) {
						sb.append("0");
					}
					sb.append(randomNumber);
					randomNumber = sb.toString();
					System.out.println("안됨!");
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
					return new ModelAndView("lee_view/email_chk");
				}
				
			}
		} catch (Exception e) {
			System.out.println("2");
			System.out.println(e);
		}
		System.out.println("안되니3");
		return null;
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
	
	// 아이디 찾기
	@PostMapping("idFind_go.do")
	public ModelAndView getFindId(MemberVO mvo) {
			ModelAndView mv = new ModelAndView();
			List<MemberVO> list = memberService.getFindId(mvo);
			if(list != null) {
				mv.addObject("list", list);
				mv.setViewName("lee_view/id_find");
				return mv;
			}
		return new ModelAndView("error");
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
