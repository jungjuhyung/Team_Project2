package com.ict.travel.kim.dao;

import java.util.List;

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
	
	
	public List<CommentVO> rcommentList(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectList("kpost_t.rcommentList", path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int rcommentInsert(CommentVO commentvo) {
		try {
			return sqlSessionTemplate.insert("kpost_t.rcommentInsert", commentvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int rcommentDelete(String comment_idx) {
		try {
			return sqlSessionTemplate.delete("kpost_t.rcommentDelete", comment_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

}
