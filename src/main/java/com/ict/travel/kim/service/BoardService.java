package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;

public interface BoardService {

	// 페이징 처리 - 전체 게시물의 수
	public int getTotalCount();

	// 페이징 처리를 위한 리스트
	public List<BoardVO> boardList(int offset, int limit);

	// 리스트
	public List<BoardVO> boardList();

	// 삽입
	public int boardInsert(BoardVO boardvo);

	// 상세보기
	public BoardVO boardDetail(String board_idx);

	// 삭제
	public int boardDelete(String board_idx);

	//  수정
	public int boardUpdate(BoardVO boardvo);

	// 조회수 업데이트
	public int boardHitUpdate(String board_idx);

	// 댓글 가져오기
	public List<CommentVO> commentList(String board_idx);

	// 댓글 삽입
	public int commentInsert(CommentVO commentvo);

	// 댓글 삭제
	public int commentDelete(String comment_idx);

}
