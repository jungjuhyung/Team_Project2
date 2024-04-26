package com.ict.travel.kim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// ����¡ ó�� - ��ü �Խù��� ��
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board_t.boardCount");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// ����¡ ó���� ���� ����Ʈ
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
	
	// ����Ʈ
	public List<BoardVO> boardList() {
		try {
			return sqlSessionTemplate.selectList("board_t.boardlist");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// �ۼ�
	public int boardWrite(BoardVO boardvo) {
		try {
			return sqlSessionTemplate.insert("board_t.boardWrite", boardvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// �󼼺���
	public BoardVO boardDetail(String board_idx) {
		try {
			return sqlSessionTemplate.selectOne("board_t.boardDetail", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// ����
	public int boardDelete(String board_idx) {
		try {
			return sqlSessionTemplate.delete("board_t.boardDelete", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// ������Ʈ
	public int boardUpdate(BoardVO boardvo) {
		try {
			return sqlSessionTemplate.update("board_t.boardUpdate", boardvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	// ��ȸ��
	public int boardHitUpdate(String board_idx) {
		try {
			return sqlSessionTemplate.update("board_t.boardHitUpdate", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// ���
	public List<CommentVO> commentList(String board_idx) {
		try {
			return sqlSessionTemplate.selectList("board_t.commentList", board_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// ��� �ۼ�
	public int commentInsert(CommentVO commentvo) {
		try {
			return sqlSessionTemplate.insert("board_t.commentInsert", commentvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	// ��� ����
	public int commentDelete(String comment_idx) {
		try {
			return sqlSessionTemplate.delete("board_t.commentDelete", comment_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

}
































