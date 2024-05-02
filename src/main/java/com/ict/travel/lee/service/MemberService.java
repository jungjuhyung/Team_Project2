package com.ict.travel.lee.service;


import com.ict.travel.lee.dao.MemberVO;

public interface MemberService {
	
	public int getSignUp(MemberVO mvo) throws Exception;

	public MemberVO getLoginOK(MemberVO mvo) throws Exception;

	public MemberVO getFindPW(String email);
	
	public int PassUpdate(MemberVO mvo);
	
	public int getFindId(MemberVO mvo) throws Exception;

	
}
