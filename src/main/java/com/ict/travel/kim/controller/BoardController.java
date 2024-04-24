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
		System.out.println("카운트 : "+count);
		boardPaging.setTotalRecord(count);
		// 전체페이지의 수를 구하자. 
		if(boardPaging.getTotalRecord() <= boardPaging.getNumPerPage()) {
			boardPaging.setTotalPage(1);
		}else {
			boardPaging.setTotalPage(boardPaging.getTotalRecord()/boardPaging.getNumPerPage());
			if(boardPaging.getTotalRecord()/boardPaging.getNumPerPage() != 0) {
				boardPaging.setTotalPage(boardPaging.getTotalPage()+1);
				
			}
		}
		// 현재페이지 구하기 => begin, end 구한다.
		String cPage = request.getParameter("cPage");
		// 제일 처음 들어오면 cPage가 없으므로 null 이다. 
		// 제일 처음 오면 무조건 1페이지 이다. 
		if(cPage == null) {
			boardPaging.setNowPage(1);
		}else {
			boardPaging.setNowPage(Integer.parseInt(cPage));
		}
		// 오라클은 begin, end
		// 마리아는 limit, offset 
		// offset = limit * (현재페이지 -1)
		// limit = numPerPage
		boardPaging.setOffset(boardPaging.getNumPerPage()*(boardPaging.getNowPage()-1));
		
		// 시작블록과 끝블록 구하기
		boardPaging.setBeginBlock(
				(int)(((boardPaging.getNowPage() -1) / 
						boardPaging.getPagePerBlock()) * 
						boardPaging.getPagePerBlock() +1));
		boardPaging.setEndBlock(boardPaging.getBeginBlock() + boardPaging.getPagePerBlock() -1);
		
		// 주의사항
		// endBlock 과 totalPage 중 endBlock 이 크면 endBlock를 totalPage 로 지정한다. 
		if (boardPaging.getEndBlock() > boardPaging.getTotalPage()) {
			boardPaging.setEndBlock(boardPaging.getTotalPage());
		}
		
		List<BoardVO> boardlist = boardService.boardList(boardPaging.getOffset(), boardPaging.getNumPerPage());
		mv.addObject("boardlist", boardlist);
		mv.addObject("paging", boardPaging);
		return mv;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



































