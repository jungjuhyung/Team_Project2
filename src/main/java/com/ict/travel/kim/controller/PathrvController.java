package com.ict.travel.kim.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.TourtestVO;
import com.ict.travel.kim.service.KpostService;
import com.ict.travel.kim.service.TourtestService;

@Controller
public class PathrvController {

	@Autowired
	private TourtestService tourtestService;
	
	@Autowired
	private KpostService kpostService;
	
	/*
	 * @PostMapping("pathReviewDetail") public ModelAndView
	 * pathReviewDetail(@RequestParam("path_maker_idx") String path_maker_idx,
	 * 
	 * @RequestParam("path_post_idx") String path_post_idx) { try { ModelAndView mv
	 * = new ModelAndView("kim_view/pathDetail"); TourtestVO tourtestvo =
	 * tourtestService.tourDetail(path_maker_idx); KpostVO kpostvo =
	 * kpostService.kpostDetail(path_post_idx); List<TourtestVO> tourtestvo2 =
	 * tourtestService.tourMaps(path_post_idx); TourtestVO tourtestVO1 =
	 * tourtestvo2.get(0); // 첫 번째 객체 String mapy1 = tourtestVO1.getMapy(); String
	 * mapx1 = tourtestVO1.getMapx(); TourtestVO tourtestVO2 = tourtestvo2.get(1);
	 * // 두 번째 객체 String mapy2 = tourtestVO2.getMapy(); String mapx2 =
	 * tourtestVO2.getMapx(); TourtestVO tourtestVO3 = tourtestvo2.get(2); // 세 번째
	 * 객체 String mapy3 = tourtestVO3.getMapy(); String mapx3 =
	 * tourtestVO3.getMapx(); if(tourtestvo != null) { mv.addObject("tourtestvo",
	 * tourtestvo); mv.addObject("kpostvo", kpostvo); mv.addObject("mapy1", mapy1);
	 * mv.addObject("mapx1", mapx1); mv.addObject("mapy2", mapy2);
	 * mv.addObject("mapx2", mapx2); mv.addObject("mapy3", mapy3);
	 * mv.addObject("mapx3", mapx3);
	 * 
	 * 
	 * return mv; }
	 * 
	 * } catch (Exception e) { System.out.println(e); } return new
	 * ModelAndView("kim_view/pathDetail"); }
	 */
	
	@PostMapping("pathReviewDetail")
	public ModelAndView pathReviewDetail(@RequestParam("path_maker_idx") String path_maker_idx,
	                                     @RequestParam("path_post_idx") String path_post_idx) {
	    try {
	        ModelAndView mv = new ModelAndView("kim_view/pathDetail");
	        TourtestVO tourtestvo = tourtestService.tourDetail(path_maker_idx);
	        KpostVO kpostvo = kpostService.kpostDetail(path_post_idx);
	        List<TourtestVO> tourtestvo2 = tourtestService.tourMaps(path_post_idx);

	        if (tourtestvo != null && !tourtestvo2.isEmpty()) {
	            mv.addObject("tourtestvo", tourtestvo);
	            mv.addObject("kpostvo", kpostvo);

	            for (int i = 0; i < tourtestvo2.size(); i++) {
	                TourtestVO tourtestVO = tourtestvo2.get(i);
	                mv.addObject("mapy" + (i + 1), tourtestVO.getMapy());
	                mv.addObject("mapx" + (i + 1), tourtestVO.getMapx());
	            }
	            return mv;
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return new ModelAndView("kim_view/pathDetail");
	}
	
}
