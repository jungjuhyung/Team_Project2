package com.ict.travel.lee.service;


import java.util.List;

import com.ict.travel.lee.dao.MemberVO;

public interface MemberService {
	
	public int getSignUp(MemberVO mvo) throws Exception;

	public MemberVO getLoginOK(MemberVO mvo) throws Exception;

	public MemberVO getFindPW(String email);
	
	public int PassUpdate(MemberVO mvo);

	public List<MemberVO> getFindId(MemberVO mvo);

	
	

	
}
