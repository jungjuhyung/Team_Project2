package com.ict.travel.kim.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.kim.dao.PlaceRWishVO;
import com.ict.travel.kim.dao.TourtestVO;
import com.ict.travel.kim.service.PlaceRWishService;
import com.ict.travel.kim.service.TourtestService;

@Controller
public class PathrvController {

	@Autowired
	private TourtestService tourtestService;
	
	@Autowired
	private PlaceRWishService placerwishService;
	
	@PostMapping("pathReviewDetail")
	public ModelAndView pathReviewDetail(@RequestParam("path_maker_idx") String path_maker_idx,
			@RequestParam("place_idx") String place_idx) {
		System.out.println("아이디x" + path_maker_idx);
		System.out.println("아이디2 : " + place_idx);
		try {
			ModelAndView mv = new ModelAndView("kim_view/pathDetail");
			TourtestVO tourtestvo = tourtestService.tourDetail(path_maker_idx);
			System.out.println("x좌표"+tourtestvo.getMapx());
			System.out.println("y좌표"+tourtestvo.getMapy());
			PlaceRWishVO placerwishvo = placerwishService.placeRDetail(place_idx);
			System.out.println(placerwishvo);
			System.out.println("타이틀 : " + placerwishvo.getPlace_title());
			
			if(tourtestvo != null) {
				mv.addObject("tourtestvo", tourtestvo);
				mv.addObject("placerwishvo", placerwishvo);
				
				// 위도와 경도 값을 전달
	            mv.addObject("latitude", tourtestvo.getMapx());
	            mv.addObject("longitude", tourtestvo.getMapy());
				
				return mv;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("kim_view/pathDetail");
	}
	
}
