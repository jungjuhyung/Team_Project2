package com.ict.travel.lee.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	public List<MemberVO> getFindId(MemberVO mvo) {
		return memberDAO.getFindId(mvo);
	}

//	@Override
//	public Map<String, String> KakaoLogin(HashMap<String, String> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	

	
	
	
}
