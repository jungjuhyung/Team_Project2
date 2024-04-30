package com.ict.travel.lee.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getSignUp(MemberVO mvo) {
		try {
			return sqlSessionTemplate.insert("lee_mapper.insert", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
}
