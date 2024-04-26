package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.BoardDAO;
import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public int getTotalCount() {
		return boardDAO.getTotalCount();
	}

	@Override
	public List<BoardVO> boardList(int offset, int limit) {
		return boardDAO.boardList(offset, limit);
	}

	@Override
	public List<BoardVO> boardList() {
		return boardDAO.boardList();
	}

	@Override
	public int boardWrite(BoardVO boardvo) {
		return boardDAO.boardWrite(boardvo);
	}

	@Override
	public BoardVO boardDetail(String board_idx) {
		return boardDAO.boardDetail(board_idx);
	}

	@Override
	public int boardDelete(String board_idx) {
		return boardDAO.boardDelete(board_idx);
	}

	@Override
	public int boardUpdate(BoardVO boardvo) {
		return boardDAO.boardUpdate(boardvo);
	}

	@Override
	public int boardHitUpdate(String board_idx) {
		return boardDAO.boardHitUpdate(board_idx);
	}

	@Override
	public List<CommentVO> commentList(String board_idx) {
		return boardDAO.commentList(board_idx);
	}

	@Override
	public int commentInsert(CommentVO commentvo) {
		return boardDAO.commentInsert(commentvo);
	}

	@Override
	public int commentDelete(String comment_idx) {
		return boardDAO.commentDelete(comment_idx);
	}

	
	
	
}
