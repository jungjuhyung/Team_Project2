package com.ict.travel.lee.service;


import java.util.List;

import com.ict.travel.lee.dao.MemberVO;

public interface MemberService {
	
	public int getSignUp(MemberVO mvo) throws Exception;

	public MemberVO getLoginOK(MemberVO mvo);

	public MemberVO getFindPW(String u_id, String email);
	
	public int PassUpdate(MemberVO memberVO);

	public List<MemberVO> getFindId(MemberVO mvo);

	// 카카오 로그인
 	public String getAccessToken(String code);

	public MemberVO getUserInfo(String access_Token);
	
	// 네이버 로그인
	public String getNaverToken(String code, String state);
	
	public MemberVO getUserNaver(String access_Token);




	
	
	



	
	

	
}
