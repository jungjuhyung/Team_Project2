package com.ict.travel.lee.controller.kakao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.lee.dao.KakaoDAO;

@RestController
public class KakaoAjaxController2 {
	;
	
	@RequestMapping(value = "kakaoUser2.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String memberChk(HttpSession session) {
		
		String access_token = (String) session.getAttribute("access_token");
		
//		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("Authorization", "Bearer" + access_token);
			
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = 
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				StringBuffer sb = new StringBuffer();
				while((line=br.readLine())!= null) {
					sb.append(line);
				}
				String result = sb.toString();
				
				Gson gson = new Gson();
				
				
				KakaoUsersVO kvo = gson.fromJson(result, KakaoUsersVO.class);
				
				String kakao_id = kvo.getId();
				String kakao_nickname = kvo.getProperties().getNickname();
				String kakao_email = kvo.getKakao_account().getEmail();
//				userInfo.put("kakao_nickname", kakao_nickname);
//				userInfo.put("kakao_email", kakao_email);
				
				// DB 저장하기
				return kakao_id + "/" + kakao_nickname + "/" + kakao_email + "/";
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		
		
		return null;
	}
}
