package com.ict.travel.jung.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.jung.vo.GptCountVO;
import com.ict.travel.jung.vo.WishListVO;

@Repository
public class GptDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 회원별 위시리스트 지역 개수 가져오기
	public List<GptCountVO> getAreaCount(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("gpt.area_count", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// 회원별 위시리스트 분류 개수 가져오기
	public List<GptCountVO> getContentTypeCount(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("gpt.contenttypeid_count", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
