package com.ict.travel.lee.service;


import java.util.List;

import com.ict.travel.lee.dao.MemberVO;

public interface MemberService {
	// 아이디 중복체크
	public String getIdChk(String u_id);
	
	public String getNickChk(String u_nickname);
	
	// 회원가입
	public int getSignUp(MemberVO mvo) throws Exception;
	
	// 로그인
	public MemberVO getLoginOK(MemberVO mvo);

	// 비밀번호 찾기
	public MemberVO getFindPW(String u_id, String email);
	
	// 임시비밀번호
	public int PassUpdate(MemberVO memberVO);
	
	/* public String getPwdChk(String u_pwd); */
	
	public String chkPassword(String u_pwd);
	
	// 아이디 찾기
	public List<MemberVO> getFindId(MemberVO mvo);

	// 카카오 로그인
 	public String getAccessToken(String code);

	public MemberVO getUserInfo(String access_Token);
	
	// 네이버 로그인
	public String getNaverToken(String code, String state);
	
	public MemberVO getUserNaver(String access_Token);
	
	
	public MemberVO getMemberUpDetail(String u_idx);

	public int getMemberUpOk(MemberVO mvo);
	
	public int getNewPwd(MemberVO mvo);

	





	
	
	



	
	

	
}
