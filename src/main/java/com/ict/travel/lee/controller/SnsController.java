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
		return new ModelAndView("lee/loginForm"); 
	}
	
	@RequestMapping("kakaologin.do")
	public ModelAndView kakaoLogin(HttpServletRequest request) {
		String code = request.getParameter("code");
		
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
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
				
				return new ModelAndView("lee/login_test");
				
			}
		} catch (Exception e) {
			System.out.println("연결실패");
		}
		return new ModelAndView("error");
	}
	// 카카오 로그아웃
	@RequestMapping("kakaologout.do")
	public ModelAndView getKakaoLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("lee/loginForm");
	}
	
}





















