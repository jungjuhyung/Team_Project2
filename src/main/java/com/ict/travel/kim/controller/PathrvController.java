package com.ict.travel.kim.controller;


import java.util.ArrayList;
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
		
	@PostMapping("pathReviewDetail")
	public ModelAndView pathReviewDetail(@RequestParam("path_marker_idx") String path_marker_idx,
	                                     @RequestParam("path_post_idx") String path_post_idx) {
	    try {
	        ModelAndView mv = new ModelAndView("kim_view/pathDetail");
	        TourtestVO tourtestvo = tourtestService.tourDetail(path_marker_idx);
	        KpostVO kpostvo = kpostService.kpostDetail(path_post_idx);
	        List<TourtestVO> tourtestvo2 = tourtestService.tourMaps(path_post_idx);

	        if (tourtestvo != null && !tourtestvo2.isEmpty()) {
	            mv.addObject("tourtestvo", tourtestvo);
	            mv.addObject("kpostvo", kpostvo);

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
}
