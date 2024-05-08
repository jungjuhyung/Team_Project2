package com.ict.travel.lee.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ict.travel.lee.controller.kakao.KakaoAccout;
import com.ict.travel.lee.controller.kakao.KakaoProperties;
import com.ict.travel.lee.controller.kakao.KakaoUsersVO;
import com.ict.travel.lee.controller.kakao.KakaoVO;
import com.ict.travel.lee.service.KakaoService;
import com.ict.travel.lee.service.MemberService;

@Controller
public class SnsController {
	
	
	
	@RequestMapping("login_go.do")
	public ModelAndView getSnsLogin() {
		return new ModelAndView("lee_view/loginForm"); 
	}

	@RequestMapping("kakaologin.do")
	public ModelAndView kakaoLogin(HttpServletRequest request) {
		// 1. 인증 코드 받기
		String code = request.getParameter("code");
		
		// 2. 토큰 받기(인증코드필요)
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
			
			// 서버로 요청 보내기
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=acef18f41c74c70cc25d2050d26d4e94");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code="+code);
			bw.write(sb.toString());
			bw.flush();
			
			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("200?"+responseCode);
			if(responseCode == HttpURLConnection.HTTP_OK) {
				// 토큰 요청을 통한 결과를 얻는데 JSON 타입
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				// 받는 정보는 session 저장(ajax를 사용해서 사용자 정보를 가져온다.)
				Gson gson = new Gson();
				KakaoVO kvo = gson.fromJson(result, KakaoVO.class);
				
				
				request.getSession().setAttribute("access_token", kvo.getAccess_token());
				request.getSession().setAttribute("refresh_token", kvo.getRefresh_token());
				request.getSession().setAttribute("token", kvo.getToken_type());
				
				
				return new ModelAndView("lee_view/login_test");
				
			}
		} catch (Exception e) {
			System.out.println("연결 실패" + e);
		}
		return new ModelAndView("error");
	}
	
	
	
	// 카카오 로그아웃
	@RequestMapping("kakaologout.do")
	public ModelAndView getKakaoLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("lee_view/loginForm");
	}
	
}





















