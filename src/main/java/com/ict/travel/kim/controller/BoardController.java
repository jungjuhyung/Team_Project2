package com.ict.travel.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BoardPaging boardPaging;
	
	@RequestMapping("boardList")
	public ModelAndView claimList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/boardList");
		int count = boardService.getTotalCount();
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
	
	
	@GetMapping("boardWrite")
	public ModelAndView boardWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/boardWrite");
		return mv;
	}
	
	@PostMapping("board_write_of")
	public ModelAndView board_write_of(BoardVO boardvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:boardList");
			String pwd = passwordEncoder.encode(boardvo.getBoard_pw());
			boardvo.setBoard_pw(pwd);
			String u_idx = boardvo.getU_idx();
			boardvo.setU_idx(u_idx);
			
			int result = boardService.boardWrite(boardvo);
			if(result > 0) {
				return mv;
			}
			return new ModelAndView("");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("");
	}

	@GetMapping("boardDetail")
	public ModelAndView boardDetail(String board_idx, String cPage) {
		try {
			ModelAndView mv = new ModelAndView("kim_view/boardDetail");
			
			BoardVO boardvo = boardService.boardDetail(board_idx);
			List<CommentVO> comment_list = boardService.commentList(board_idx);
			mv.addObject("comment_list", comment_list);
			mv.addObject("boardvo", boardvo);
			mv.addObject("cPage", cPage);
			return mv;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("kim_view/boardDetail");
	}
	
	@PostMapping("boardUpdate")
	public ModelAndView getBoardUpdate(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx) {
		ModelAndView mv = new ModelAndView("kim_view/boardUpdate");
		BoardVO boardvo = boardService.boardDetail(board_idx);
		if (boardvo != null) {
			mv.addObject("boardvo", boardvo);
			return mv;
		}
		
		
		return new ModelAndView("board/error");
	}
	
	@PostMapping("boardUpdateOK")
	public ModelAndView getBoardUpdateOK(
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx,
			@ModelAttribute("board_cpw")String board_cpw
			) {
		ModelAndView mv = new ModelAndView();
		System.out.println("입력비밀번호" + board_cpw);
		// 비밀번호 체크
		BoardVO boardvo = boardService.boardDetail(board_idx);
		String dpwd = boardvo.getBoard_pw();
		System.out.println("디비비밀번호: " + dpwd );
		if (! passwordEncoder.matches(board_cpw, dpwd)) {
			mv.setViewName("redirect:boardUpdate");
			mv.addObject("pwchk", "fail");
			mv.addObject("boardvo", boardvo);
			return mv;
		}else {
							
			int result = boardService.boardUpdate(boardvo);
			if (result>0) {
				mv.setViewName("redirect:boardDetail");
				return mv;
			}
				
			
		}
				
		return new ModelAndView("");
	}
	
	@PostMapping("boardDelete")
	public ModelAndView getBbsDelete(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx) {
		return new ModelAndView("kch_view/boardDelete");
	}
	
	
	@PostMapping("boardDeleteOK")
	public ModelAndView boardDeleteOK(@RequestParam("board_cpw")String board_cpw,
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx) {
		ModelAndView mv = new ModelAndView();
		// 비밀번호 체크
		BoardVO boardvo = boardService.boardDetail(board_idx);
		String dpwd = boardvo.getBoard_pw();
		
		if(! passwordEncoder.matches(board_cpw, dpwd)) {
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			// 원글 삭제 (댓글이 있을 경우 그냥 삭제하면 외래키 때문에 오류 발생)
			// active 컬럼의 값을 1로 변경한다. 
			int result = boardService.boardDelete(board_idx);
			if(result > 0) {
				mv.setViewName("redirect:boardList");
				return mv;
			}
		}
		return new ModelAndView("bbs/error");
		
	}
	
	@PostMapping("commentInsert")
	public ModelAndView CommentInsert(CommentVO commentvo, BoardVO boardvo,
			@ModelAttribute("board_idx")String board_idx) {
		ModelAndView mv = new ModelAndView("redirect:boardDetail");
		boardvo.setBoard_idx(board_idx);;
		int result = boardService.commentInsert(commentvo);
		return mv;
	}
	
	@PostMapping("commentDelete")
	public ModelAndView getCommentDelete(String comment_idx, @ModelAttribute("board_idx")String board_idx) {
		ModelAndView mv = new ModelAndView("redirect:boardDetail");
		int result = boardService.commentDelete(comment_idx);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}



































