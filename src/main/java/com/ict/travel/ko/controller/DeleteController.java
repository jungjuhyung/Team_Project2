package com.ict.travel.ko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.service.BoardService;

@Controller
public class DeleteController {

	@Autowired
	private BoardService boardService;

	// 자유게시글 삭제하기
	@RequestMapping("board_delete.do")
	public ModelAndView boardDelete(String board_idx, @ModelAttribute("u_idx") String u_idx) {
		ModelAndView mv = new ModelAndView("redirect: board_list.do");

		int result = boardService.boardDelete(board_idx);
		if (result > 0) {
			return mv;
		}

		return new ModelAndView("common_view/error");
	}

	// 작성댓글 삭제하기
	@RequestMapping("comment_delete.do")
	public ModelAndView commentDelete(String comment_idx, @ModelAttribute("u_idx") String u_idx) {
		ModelAndView mv = new ModelAndView("redirect: comment_list.do");

		int result = boardService.commentDelete(comment_idx);
		if (result > 0) {
			return mv;
		}

		return new ModelAndView("common_view/error");
	}

}
