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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.service.BoardService;
import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MemberService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping("getReportgo")
	public ModelAndView getReportgo() {
		return new ModelAndView("kim_view/reportList");
	}

	
	@RequestMapping("boardList")
	@ResponseBody
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/boardList");
		int count = boardService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수
		if (paging.getTotalRecord() < paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (((paging.getTotalRecord()*1.0) / paging.getNumPerPage()) % 2 != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		// 현재 페이지 구하기
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 오라클 = begin, end
		// offset구하기 limit * 현재페이지-1
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() -1 ));
		
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock() + 1)
				);
		
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<BoardVO> boardlist = boardService.boardList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("boardlist", boardlist);
		mv.addObject("paging", paging);
		
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		mv.addObject("membervo", membervo);
		
		return mv;		
	}
	
	
	@GetMapping("boardWrite")
	public ModelAndView boardWrite(BoardVO boardvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("kim_view/boardWrite");
		
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		mv.addObject("membervo", membervo);
		
		
		
		return mv;
	}
	
	@PostMapping("boardWriteOK")
	public ModelAndView boardWriteOK(BoardVO boardvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:boardList");
			String pwd = passwordEncoder.encode(boardvo.getBoard_pw());
			boardvo.setBoard_pw(pwd);
		
			HttpSession session = request.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
			mv.addObject("membervo", membervo);
			boardvo.setU_nickname(membervo.getU_nickname());
			boardvo.setU_idx(membervo.getU_idx());
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
	public ModelAndView boardDetail(String board_idx, String cPage, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("kim_view/boardDetail");
			int result = boardService.boardHitUpdate(board_idx);
			BoardVO boardvo = boardService.boardDetail(board_idx);
			HttpSession session = request.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
			mv.addObject("membervo", membervo);
			if (result>0 && boardvo !=null) {
				List<CommentVO> comment_list = boardService.commentList(board_idx);
				mv.addObject("comment_list", comment_list);
				mv.addObject("boardvo", boardvo);
				mv.addObject("cPage", cPage);
				return mv;
			}
			
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
	
	@RequestMapping("boardUpdateOK")
	public ModelAndView getBoardUpdateOK(
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx,
			@ModelAttribute("board_cpw")String board_cpw,
			BoardVO boardvo
			) {
		ModelAndView mv = new ModelAndView();
		System.out.println("입력pw" + board_cpw);
		BoardVO boardvo2 = boardService.boardDetail(board_idx);
		String dpwd = boardvo2.getBoard_pw();
		System.out.println("dbpw: " + dpwd );
		if (! passwordEncoder.matches(board_cpw, dpwd)) {
			mv.setViewName("kim_view/boardUpdate");
			mv.addObject("boardvo", boardvo);
			mv.addObject("pwdchk", "fail"); // pwdchk 값을 모델에 추가
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
	public ModelAndView getBoardDelete(@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx,
			@ModelAttribute("board_cpw")String board_cpw) {
		return new ModelAndView("kim_view/boardDelete");
	}
	
	
	@PostMapping("boardDeleteOK")
	public ModelAndView boardDeleteOK(@ModelAttribute("board_cpw")String board_cpw,
			@ModelAttribute("cPage")String cPage,
			@ModelAttribute("board_idx")String board_idx,
			BoardVO boardvo) {
		ModelAndView mv = new ModelAndView();
		BoardVO boardvo2 = boardService.boardDetail(board_idx);
		String dpwd = boardvo2.getBoard_pw();
		if(! passwordEncoder.matches(board_cpw, dpwd)) {
			mv.setViewName("kim_view/boardDelete");
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			
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
			@ModelAttribute("board_idx")String board_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:boardDetail");
		boardvo.setBoard_idx(board_idx);;
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		mv.addObject("membervo", membervo);
		commentvo.setU_idx(membervo.getU_idx());
		commentvo.setU_nickname(membervo.getU_nickname());
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



































