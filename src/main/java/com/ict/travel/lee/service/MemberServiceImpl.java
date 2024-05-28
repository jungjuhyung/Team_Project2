package com.ict.travel.lee.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ict.travel.lee.dao.MemberDAO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	// 회원정보 수정
	@Override
	public MemberVO getMemberUpDetail(String u_idx) {
		return memberDAO.getMemberUpDetail(u_idx);
	}
	
	@Override
	public int getMemberUpOk(MemberVO mvo) {
		return memberDAO.getMemberUpOk(mvo);
	}
	// 아이디 중복체크
	@Override
	public String getIdChk(String u_id) {
		return memberDAO.getIdChk(u_id);
	}
	
	// 닉네임 중복체크
	public String getNickChk(String u_nickname) {
		return memberDAO.getNickChk(u_nickname);
	}
	
	// 회원가입
	@Override
	public int getSignUp(MemberVO mvo) throws Exception {
		return memberDAO.getSignUp(mvo);
	}
	
	
	// 로그인
	@Override
	public MemberVO getLoginOK(MemberVO mvo) {
		return memberDAO.getLoginOK(mvo);
	}
	
	// 비밀번호 찾기
	@Override
	public MemberVO getFindPW(String u_id, String email) {
		return memberDAO.getFindPW(u_id, email);
	}
	
	// 임시비밀번호
	@Override
	public int PassUpdate(MemberVO memberVO) {
		return memberDAO.PassUpdate(memberVO);
	}
	
	@Override
	public String chkPassword(String u_pwd) {
		return memberDAO.chkPassword(u_pwd);
	}
	
	@Override
	public int getNewPwd(MemberVO mvo) {
		return memberDAO.getNewPwd(mvo);
	}
	
	// 아이디 찾기
	@Override
	public List<MemberVO> getFindId(MemberVO mvo) {
		return memberDAO.getFindId(mvo);
	}
	
	// 카카오
	@Override
	public String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(reqURL);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=acef18f41c74c70cc25d2050d26d4e94");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while ((line = br.readLine())!=null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			
			br.close();
			bw.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return access_Token;
	}
	
	// 카카오
	@Override
	public  MemberVO getUserInfo(String access_Token){
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
			while ((line = br.readLine())!= null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//			String id = element.getAsJsonObject().get("id").getAsString();
//			System.out.println("+++++++++++++++++++++++++" + id);
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			System.out.println("nickname@@@@@@@@@ : " + nickname);
			System.out.println("email@@@@@@@@@@ : " + email);
			
			MemberVO mvo = memberDAO.findkakao(userInfo);
			System.out.println("S : " + mvo);
			if(mvo == null) {
				MemberVO mvo2 = new MemberVO();
				
				mvo2.setU_nickname(nickname);
				mvo2.setU_email(email);
				
				return mvo2;
				
			}else {
				return mvo;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		
		
	}
	
	// 네이버
	@Override
	public String getNaverToken(String code, String state) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		
		try {
			URL url = new URL(reqURL);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=TRKMsvddX9k547J_e_XD");
			sb.append("&client_secret=8AJJIuOG6V");
			sb.append("&redirect_uri=http://localhost:8090/naverlogin.do");
			sb.append("&code=" + code);
			sb.append("&state=" + state);
			bw.write(sb.toString());
			bw.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while ((line = br.readLine())!=null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			
			br.close();
			bw.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return access_Token;
	}
	
	// 네이버
	@Override
	public MemberVO getUserNaver(String access_Token) {
		HashMap<String, Object> userInfo2 = new HashMap<String, Object>();
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
			while ((line = br.readLine())!= null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			// 네이버는 response 사용
			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
			System.out.println("response : "+response);
			
			String name = response.getAsJsonObject().get("name").getAsString();
			String email = response.getAsJsonObject().get("email").getAsString();
			String mobile = response.getAsJsonObject().get("mobile").getAsString();
			
			userInfo2.put("name", name);
			userInfo2.put("email", email);
			userInfo2.put("mobile", mobile);
			
			System.out.println("name@@@@@@@@@ : " + name);
			System.out.println("email@@@@@@@@@@ : " + email);
			System.out.println("mobile@@@@@@@@@@ : " + mobile);
			
			MemberVO mvo2 = memberDAO.findnaver(userInfo2);
			System.out.println("S : " + mvo2);
			if(mvo2 == null) {
				MemberVO mvo = new MemberVO();
				mvo.setU_name(name);
				mvo.setU_email(email);
				
				return mvo;
				
			}else {
				return mvo2;
			}
		} catch (Exception e) {
			System.out.println("3");
			System.out.println(e);
			return null;
		}
	}

	

	

	

	

	
	
	
}
