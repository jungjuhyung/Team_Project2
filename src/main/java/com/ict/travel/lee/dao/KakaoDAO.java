package com.ict.travel.lee.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KakaoDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int kakaoLogin(MemberVO mvo) {
		try {
			return sqlSessionTemplate.insert("lee-mapper.kakao_insert");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
