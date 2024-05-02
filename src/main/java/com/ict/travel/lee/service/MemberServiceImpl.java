package com.ict.travel.lee.service;

import java.util.List;

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

	@Override
	public MemberVO getFindPW(String email) {
		return memberDAO.getFindPW(email);
	}
	@Override
	public int PassUpdate(MemberVO mvo) {
		return memberDAO.PassUpdate(mvo);
	}

	@Override
	public int getFindId(MemberVO mvo) throws Exception {
		return memberDAO.getFindId(mvo);
	}

	

	

	

	
	
	
}
