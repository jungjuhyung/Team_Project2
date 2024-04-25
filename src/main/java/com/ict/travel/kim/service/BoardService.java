package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;

public interface BoardService {

	// ����¡ ó�� - ��ü �Խù��� ��
	public int getTotalCount();

	// ����¡ ó���� ���� ����Ʈ
	public List<BoardVO> boardList(int offset, int limit);

	// ����Ʈ
	public List<BoardVO> boardList();

	// ����
	public int boardInsert(BoardVO boardvo);

	// �󼼺���
	public BoardVO boardDetail(String board_idx);

	// ����
	public int boardDelete(String board_idx);

	//  ����
	public int boardUpdate(BoardVO boardvo);

	// ��ȸ�� ������Ʈ
	public int boardHitUpdate(String board_idx);

	// ��� ��������
	public List<CommentVO> commentList(String board_idx);

	// ��� ����
	public int commentInsert(CommentVO commentvo);

	// ��� ����
	public int commentDelete(String comment_idx);

}
