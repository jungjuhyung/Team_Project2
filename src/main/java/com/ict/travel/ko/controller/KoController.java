package com.ict.travel.ko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.ko.dao.KoVO;
import com.ict.travel.ko.service.KoService;

@Controller
public class KoController {
	
	@Autowired
	private KoService koService;
	
	@RequestMapping("ko.do")
	public ModelAndView getKo(String areacode, String contenttypeid) {
		ModelAndView mv = new ModelAndView("ko_view/ko");
		System.out.println("지역코드 : " + areacode);
		System.out.println("테마종류 : " + contenttypeid);
		
		List<KoVO> area_list = koService.getAreaList(areacode);
		List<KoVO> tema_list = koService.getTemaList(contenttypeid);
		
		System.out.println(area_list);
		System.out.println(tema_list);
		
		mv.addObject("area_list", area_list);
		mv.addObject("tema_list", tema_list);
		
		return mv;
	}
	
	@RequestMapping("ko_detail.do")
	public ModelAndView getKoDetail(@ModelAttribute("contentid") String contentid, 
			@ModelAttribute("contenttypeid") String contenttypeid) {
		ModelAndView mv = new ModelAndView("ko_view/detail");
		System.out.println("contentid : " + contentid);
		System.out.println("contenttypeid : " + contenttypeid);
		return mv;
	}
	
	
}
