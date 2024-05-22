package com.ict.travel.kim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.lee.dao.MemberVO;

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
	
	// pathwish 테이블 저장
	public int ilikethis(MemberVO membervo, KpostVO kpostvo) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("membervo", membervo);
			map.put("kpostvo", kpostvo);
			return sqlSessionTemplate.insert("kpost_t.ilikethis", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// pathwish 테이블 삭제
	public int ihatethis(MemberVO membervo, KpostVO kpostvo) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("membervo", membervo);
			map.put("kpostvo", kpostvo);			
			return sqlSessionTemplate.delete("kpost_t.ihatethis", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 찜 증가
	public int ilikehit(KpostVO kpostvo) {
		try {
			return sqlSessionTemplate.insert("kpost_t.ilikehit", kpostvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 찜 감소
	public int ihatehit(KpostVO kpostvo) {
		try {
			return sqlSessionTemplate.update("kpost_t.ihatehit", kpostvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public KpostVO pathTable(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectOne("kpost_t.pathtable", path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
