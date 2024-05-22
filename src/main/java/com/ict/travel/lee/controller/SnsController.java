package com.ict.travel.lee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MemberService;

@Controller
public class SnsController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("login_go.do")
	public ModelAndView getSnsLogin() {
		return new ModelAndView("lee_view/loginForm");
	}

	@RequestMapping("kakaologin.do")
	public ModelAndView kakaoLogin(HttpServletRequest request, String code, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		System.out.println("#######" + code);

		String access_Token = memberService.getAccessToken(code);
		MemberVO userInfo = memberService.getUserInfo(access_Token);
		
		if(userInfo.getK_status() != null) {
			session.setAttribute("memberUser", userInfo);
			System.out.println("###access_Token### : " + userInfo);
			
			return new ModelAndView("ko_view/main_page");
			
		} else {
			mv.addObject("mvo2", userInfo);
			mv.setViewName("lee_view/joinForm");
			return mv;
		}

	}

	@RequestMapping("naverlogin.do")
	public ModelAndView naverLogin(HttpServletRequest request, String code, String state, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		System.out.println("#####" + code);
		System.out.println("#### state : " + state);

		String access_Token = memberService.getNaverToken(code, state);
		MemberVO userInfo2 = memberService.getUserNaver(access_Token);

		System.out.println(userInfo2.getU_name());

		if (userInfo2.getN_status() != null) {
			session.setAttribute("memberUser", userInfo2);
			System.out.println("#####url : " + access_Token);

			return new ModelAndView("ko_view/main_page");

		} else {
			mv.addObject("mvo", userInfo2);
			mv.setViewName("lee_view/joinForm");
			return mv;
		}

	}

//	@RequestMapping("kakaologin.do")
//	public ModelAndView kakaoLogin(HttpServletRequest request) {
//		// 1. 인증 코드 받기
//		String code = request.getParameter("code");
//		System.out.println(code);
//		
//		// 2. 토큰 받기(인증코드필요)
//		String reqURL = "https://kauth.kakao.com/oauth/token";
//		try {
//			URL url = new URL(reqURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			
//			// POST 요청
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//			
//			// 헤더 요청
//			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
//			
//			// 서버로 요청 보내기
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("grant_type=authorization_code");
//			sb.append("&client_id=acef18f41c74c70cc25d2050d26d4e94");
//			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
//			sb.append("&code="+code);
//			bw.write(sb.toString());
//			bw.flush();
//			
//			// 결과 코드가 200이면 성공
//			int responseCode = conn.getResponseCode();
//			System.out.println("200?"+responseCode);
//			if(responseCode == HttpURLConnection.HTTP_OK) {
//				// 토큰 요청을 통한 결과를 얻는데 JSON 타입
//				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//				
//				String line = "";
//				StringBuffer sb2 = new StringBuffer();
//				while ((line = br.readLine()) != null) {
//					sb2.append(line);
//				}
//				String result = sb2.toString();
//				
//				// 받는 정보는 session 저장(ajax를 사용해서 사용자 정보를 가져온다.)
//				Gson gson = new Gson();
//				KakaoVO kvo = gson.fromJson(result, KakaoVO.class);
//				
//				request.getSession().setAttribute("access_token", kvo.getAccess_token());
//				request.getSession().setAttribute("refresh_token", kvo.getRefresh_token());
//				request.getSession().setAttribute("token", kvo.getToken_type());
//				
//				
//				return new ModelAndView("lee_view/login_test");
//				
//			}
//		} catch (Exception e) {
//			System.out.println("연결 실패" + e);
//		}
//		return new ModelAndView("error");
//	}

	// 카카오 로그아웃
	@RequestMapping("kakaologout.do")
	public ModelAndView getKakaoLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("lee_view/loginForm");
	}

}
