package com.ict.travel.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BoardPaging boardPaging;
	
	@RequestMapping("board")
	public ModelAndView claimList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kch-view/board");
		int count = boardService.getTotalCount();
		System.out.println("ī��Ʈ : "+count);
		boardPaging.setTotalRecord(count);
		// ��ü�������� ���� ������. 
		if(boardPaging.getTotalRecord() <= boardPaging.getNumPerPage()) {
			boardPaging.setTotalPage(1);
		}else {
			boardPaging.setTotalPage(boardPaging.getTotalRecord()/boardPaging.getNumPerPage());
			if(boardPaging.getTotalRecord()/boardPaging.getNumPerPage() != 0) {
				boardPaging.setTotalPage(boardPaging.getTotalPage()+1);
				
			}
		}
		// ���������� ���ϱ� => begin, end ���Ѵ�.
		String cPage = request.getParameter("cPage");
		// ���� ó�� ������ cPage�� �����Ƿ� null �̴�. 
		// ���� ó�� ���� ������ 1������ �̴�. 
		if(cPage == null) {
			boardPaging.setNowPage(1);
		}else {
			boardPaging.setNowPage(Integer.parseInt(cPage));
		}
		// ����Ŭ�� begin, end
		// �����ƴ� limit, offset 
		// offset = limit * (���������� -1)
		// limit = numPerPage
		boardPaging.setOffset(boardPaging.getNumPerPage()*(boardPaging.getNowPage()-1));
		
		// ���ۺ�ϰ� ����� ���ϱ�
		boardPaging.setBeginBlock(
				(int)(((boardPaging.getNowPage() -1) / 
						boardPaging.getPagePerBlock()) * 
						boardPaging.getPagePerBlock() +1));
		boardPaging.setEndBlock(boardPaging.getBeginBlock() + boardPaging.getPagePerBlock() -1);
		
		// ���ǻ���
		// endBlock �� totalPage �� endBlock �� ũ�� endBlock�� totalPage �� �����Ѵ�. 
		if (boardPaging.getEndBlock() > boardPaging.getTotalPage()) {
			boardPaging.setEndBlock(boardPaging.getTotalPage());
		}
		
		List<BoardVO> boardlist = boardService.boardList(boardPaging.getOffset(), boardPaging.getNumPerPage());
		mv.addObject("boardlist", boardlist);
		mv.addObject("paging", boardPaging);
		return mv;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



































