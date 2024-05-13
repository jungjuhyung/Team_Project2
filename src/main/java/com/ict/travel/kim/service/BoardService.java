package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;

public interface BoardService {

	public int getTotalCount();

	public List<BoardVO> boardList(int offset, int limit);

	public List<BoardVO> boardList();

	public int boardWrite(BoardVO boardvo);

	public BoardVO boardDetail(String board_idx);

	public int boardDelete(String board_idx);

	public int boardUpdate(BoardVO boardvo);

	public int boardHitUpdate(String board_idx);

	public List<CommentVO> commentList(String board_idx);

	public int commentInsert(CommentVO commentvo);

	public int commentDelete(String comment_idx);
	

}
