package com.ict.travel.ko.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;
import com.ict.travel.ko.dao.UserVO;
import com.ict.travel.ko.service.UserDetailService;

@Controller
public class UserDetailController {

	@Autowired
	private UserDetailService userDetailService;

	// 특정 유저 게시글 관리
	@Autowired
	private Paging paging;

	// 첫페이지
	@RequestMapping("user_board.do")
	public ModelAndView userBoard(@ModelAttribute("u_idx") String u_idx) {
		ModelAndView mv = new ModelAndView("ko_view/user_detail");
		UserVO uvo = userDetailService.getUserDetail(u_idx);
		mv.addObject("uvo", uvo);
		return mv;
	}

	// 자유게시판 목록
	@RequestMapping("board_list.do")
	public ModelAndView boardList(@ModelAttribute("u_idx") String u_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/board_list");

		UserVO uvo = userDetailService.getUserDetail(u_idx);
		mv.addObject("uvo", uvo);

		int count = userDetailService.getBoardCount(u_idx);
		paging.setTotalRecord(count);

		paging.setNumPerPage(5);
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		List<BoardVO> board_list = userDetailService.getBoardList(u_idx, paging.getOffset(), paging.getNumPerPage());
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}

	// 신고게시판 목록
	@RequestMapping("report_list.do")
	public ModelAndView userReport(@ModelAttribute("u_idx") String u_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/report_list");

		UserVO uvo = userDetailService.getUserDetail(u_idx);
		mv.addObject("uvo", uvo);

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = userDetailService.getReportCount(u_idx);
		paging.setTotalRecord(count);

		// 전체페이지의 수
		paging.setNumPerPage(5);
		// 한페이지 당 게시물의 수보다 작으면 항상 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 2.
		// 현재페이지 구하자
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 3.
		// offset 구하기
		// limit = numPerPage
		// offset = limit * (현재페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 4.
		// 시작블록과 끝블록 구하기
		// 시작블록 = (int){(현재페이지 -1) / 페이지당 블록수} * 페이지당 블록수 + 1
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5.
		// DB 갔다오기
		List<ReportVO> report_list = userDetailService.getReportList(u_idx, paging.getOffset(), paging.getNumPerPage());
		mv.addObject("report_list", report_list);
		mv.addObject("paging", paging);
		return mv;

	}

	// 추천경로게시판 목록
	@RequestMapping("path_list.do")
	public ModelAndView userPath(@ModelAttribute("u_idx") String u_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/path_list");

		UserVO uvo = userDetailService.getUserDetail(u_idx);
		mv.addObject("uvo", uvo);

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = userDetailService.getPathCount(u_idx);
		paging.setTotalRecord(count);

		// 전체페이지의 수
		paging.setNumPerPage(3);
		// 한페이지 당 게시물의 수보다 작으면 항상 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 2.
		// 현재페이지 구하자
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 3.
		// offset 구하기
		// limit = numPerPage
		// offset = limit * (현재페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 4.
		// 시작블록과 끝블록 구하기
		// 시작블록 = (int){(현재페이지 -1) / 페이지당 블록수} * 페이지당 블록수 + 1
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5.
		// DB 갔다오기
		List<KpostVO> path_list = userDetailService.getPathList(u_idx, paging.getOffset(), paging.getNumPerPage());
		mv.addObject("path_list", path_list);
		mv.addObject("paging", paging);
		return mv;

	}

	// 작성댓글 목록
	@RequestMapping("comment_list.do")
	public ModelAndView userComment(@ModelAttribute("u_idx") String u_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/comment_list");

		UserVO uvo = userDetailService.getUserDetail(u_idx);
		mv.addObject("uvo", uvo);

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = userDetailService.getCommentCount(u_idx);
		paging.setTotalRecord(count);

		// 전체페이지의 수
		paging.setNumPerPage(5);
		// 한페이지 당 게시물의 수보다 작으면 항상 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 2.
		// 현재페이지 구하자
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 3.
		// offset 구하기
		// limit = numPerPage
		// offset = limit * (현재페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 4.
		// 시작블록과 끝블록 구하기
		// 시작블록 = (int){(현재페이지 -1) / 페이지당 블록수} * 페이지당 블록수 + 1
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5.
		// DB 갔다오기
		List<CommentVO> comment_list = userDetailService.getCommentList(u_idx, paging.getOffset(), paging.getNumPerPage());
		mv.addObject("comment_list", comment_list);
		mv.addObject("paging", paging);
		return mv;

	}

}
