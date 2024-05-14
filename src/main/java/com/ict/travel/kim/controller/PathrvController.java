package com.ict.travel.kim.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.TourtestVO;
import com.ict.travel.kim.service.KpostService;
import com.ict.travel.kim.service.TourtestService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class PathrvController {

	@Autowired
	private TourtestService tourtestService;
	
	@Autowired
	private KpostService kpostService;
		
	@RequestMapping("pathReviewDetail")
	public ModelAndView pathReviewDetail(@RequestParam("path_post_idx") String path_post_idx,
			HttpServletRequest request) {
	    try {
	        ModelAndView mv = new ModelAndView("kim_view/pathDetail");
			/* TourtestVO tourtestvo = tourtestService.tourDetail(path_post_idx); */
	        KpostVO kpostvo = kpostService.kpostDetail(path_post_idx);
	        List<TourtestVO> tourtestvo2 = tourtestService.tourMaps(path_post_idx);
	        HttpSession session = request.getSession();
			MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
			mv.addObject("membervo", membervo);

			mv.addObject("kpostvo", kpostvo);
			if (!tourtestvo2.isEmpty()) {
	        	
				/* mv.addObject("tourtestvo", tourtestvo); */
	            
	            List<CommentVO> comment_list = kpostService.rcommentList(path_post_idx);
				mv.addObject("comment_list", comment_list);
	            
	            List<Double> mapyList = new ArrayList<>();
	            List<Double> mapxList = new ArrayList<>();
	            for (int i = 0; i < tourtestvo2.size(); i++) {
	                TourtestVO tourtestVO = tourtestvo2.get(i);
	                mv.addObject("mapy" + (i + 1), tourtestVO.getMapy());
	                mv.addObject("mapx" + (i + 1), tourtestVO.getMapx());
	                mapyList.add(tourtestVO.getMapy());
	                mapxList.add(tourtestVO.getMapx());
	            }
	            mv.addObject("mapyList", mapyList);
	            mv.addObject("mapxList", mapxList);
	            return mv;
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return new ModelAndView("kim_view/pathDetail");
	}
	
	
	// 수정 
	
	// 삭제 
	
	
	
	
	// 댓글
	
	
	
	
	
	@PostMapping("rcommentInsert")
	public ModelAndView CommentInsert(CommentVO commentvo, KpostVO kpostvo,
			@ModelAttribute("past_post_idx")String past_post_idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:pathReviewDetail");
		kpostvo.setPath_post_idx(past_post_idx);;
		HttpSession session = request.getSession();
		MemberVO membervo = (MemberVO) session.getAttribute("memberUser");
		mv.addObject("membervo", membervo);
		commentvo.setU_idx(membervo.getU_idx());
		commentvo.setU_nickname(membervo.getU_nickname());
		int result = kpostService.rcommentInsert(commentvo);
		if (result > 0) {
			mv.addObject("commentvo", commentvo);
			return mv;
		}
		return mv;
	}
	
	@PostMapping("rcommentDelete")
	public ModelAndView getCommentDelete(String comment_idx, @ModelAttribute("past_post_idx")String past_post_idx) {
		ModelAndView mv = new ModelAndView("redirect:pathReviewDetail");
		int result = kpostService.rcommentDelete(comment_idx);
		return mv;
	}

	
	
	
	
	
	
	
	
}
