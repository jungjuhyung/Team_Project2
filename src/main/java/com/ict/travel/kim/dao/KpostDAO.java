package com.ict.travel.kim.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KpostDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public KpostVO kpostDetail(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectOne("kpost_t.kpostDetail", path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
