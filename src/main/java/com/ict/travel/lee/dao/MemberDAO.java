package com.ict.travel.lee.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getSignUp(MemberVO mvo) {
		try {
			return sqlSessionTemplate.insert("lee-mapper.insert", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public MemberVO getLoginOK(MemberVO mvo) throws Exception {
		try {
			return sqlSessionTemplate.selectOne("lee-mapper.login", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public MemberVO getFindPW(String email) {
		try {
			return sqlSessionTemplate.selectOne("lee-mapper.findPwd", email);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int PassUpdate(MemberVO mvo) {
		try {
			return sqlSessionTemplate.update("lee-mapper.passUpdate", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public List<MemberVO> getFindId(MemberVO mvo) {
		try {
			return sqlSessionTemplate.selectList("lee-mapper.findId", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public MemberVO findkakao(HashMap<String, Object> userInfo) {
		try {
			return sqlSessionTemplate.selectOne("lee-mapper.findkakao", userInfo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int kakaoinsert(HashMap<String, Object> userInfo) {
		try {
			System.out.println("RN:"+userInfo.get("nickname"));
			System.out.println("RE:"+userInfo.get("email"));
			return sqlSessionTemplate.insert("lee-mapper.kakao_insert", userInfo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
		
	}
	

	


	

	

	


	

	

	
	
	
}
