package com.ict.travel.kim.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TourtestDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public TourtestVO tourDetail(String path_maker_idx) {
		try {
			return sqlSessionTemplate.selectOne("tourtest_t.tourDetail",path_maker_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
