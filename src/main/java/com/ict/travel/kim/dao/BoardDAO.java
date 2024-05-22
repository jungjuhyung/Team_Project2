package com.ict.travel.kim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.lee.dao.MemberVO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 전체 페이지
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board_t.boardCount");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// 페이징
	public List<BoardVO> boardList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("board_t.boardList", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 리스트
	public List<BoardVO> boardList() {
		try {
			return sqlSessionTemplate.selectList("board_t.boardlist");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 글쓰기
	public int boardWrite(BoardVO boardvo) {
		try {
			sqlSessionTemplate.update("board_t.boardscore", boardvo);
			MemberVO mvo = sqlSessionTemplate.selectOne("board_t.userinfo", boardvo);
			/*
			 * if (mvo.getu_exp == 100) { sqlSessionTemplate.update("board_t.levelup", mvo);
			 * }
			 */
			return sqlSessionTemplate.insert("board_t.boardWrite", boardvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 상세보기
	public BoardVO boardDetail(String board_idx) {
		try {
			return sqlSessionTemplate.selectOne("board_t.boardDetail", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// 삭제
	public int boardDelete(String board_idx) {
		try {
			return sqlSessionTemplate.update("board_t.boardDelete", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// 수정
	public int boardUpdate(BoardVO boardvo) {
		try {
			return sqlSessionTemplate.update("board_t.boardUpdate", boardvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// 조회수
	public int boardHitUpdate(String board_idx) {
		try {
			return sqlSessionTemplate.update("board_t.boardHitUpdate", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 댓글 리스트
	public List<CommentVO> commentList(String board_idx) {
		try {
			return sqlSessionTemplate.selectList("board_t.commentList", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 댓글 입력
	public int commentInsert(CommentVO commentvo) {
		try {
			return sqlSessionTemplate.insert("board_t.commentInsert", commentvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	// 댓글 삭제
	public int commentDelete(String comment_idx) {
		try {
			return sqlSessionTemplate.delete("board_t.commentDelete", comment_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

}
































