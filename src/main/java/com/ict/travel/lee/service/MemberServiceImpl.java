package com.ict.travel.lee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.lee.dao.MemberDAO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public int getSignUp(MemberVO mvo) throws Exception {
		return memberDAO.getSignUp(mvo);
	}

	@Override
	public MemberVO getLoginOK(MemberVO mvo) throws Exception {
		return memberDAO.getLoginOK(mvo);
	}
	
	
	
}
