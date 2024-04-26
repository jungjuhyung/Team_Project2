package com.ict.travel.lee.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ict.travel.lee.dao.KakaoVO;

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
			
			System.out.println("2");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=0d71c1a838198546c59f71aef1208e6e");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code="+code);
			bw.write(sb.toString());
			bw.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println("200?"+responseCode);
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				Gson gson = new Gson();
				KakaoVO kvo = gson.fromJson(result, KakaoVO.class);
				
				request.getSession().setAttribute("access_token", kvo.getAccess_token());
				request.getSession().setAttribute("refresh_token", kvo.getRefresh_token());
				request.getSession().setAttribute("token", kvo.getToken_type());
				
				return new ModelAndView("lee_view/login_test");
				
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return new ModelAndView("error");
	}
	// 移댁뭅�삤 濡쒓렇�븘�썐
	@RequestMapping("kakaologout.do")
	public ModelAndView getKakaoLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("lee_view/loginForm");
	}
	
}





















