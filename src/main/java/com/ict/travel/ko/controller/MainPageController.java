package com.ict.travel.ko.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.ko.service.MainPageService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class MainPageController {

	@Autowired
	private MainPageService mainPageService;

	@RequestMapping("main_page.do")
	public ModelAndView getMainPage(HttpSession session) {
		ModelAndView mv = new ModelAndView("ko_view/main_page");

		// 로그인 여부 체크
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if (uvo != null) {
			mv.addObject("userLogin", "ok");
		}
		
		// 메인 이미지 텍스트 표시해주기 위한 정보보내기
		Map<String, String> text_list = new LinkedHashMap<String, String>();
		text_list.put("남산", "서울타워와 벚꽃");
		text_list.put("고창", "해바라기 밭");
		text_list.put("북한산", "가을단풍 풍경");
		text_list.put("안동", "하회마을 설경");
		text_list.put("평창", "대관령 양떼목장");
		text_list.put("광화문", "경복궁의 정문");
		text_list.put("아산", "노란 은행나무길");
		text_list.put("광양", "섬진강 매화 축제");
		text_list.put("한강", "동작대교 야경");
		text_list.put("서울", "아름다운 풍경");
		mv.addObject("text_list", text_list);

		// 지역별 추천경로 가져오기 (처음 서울로 지정)
		String r_areacode = "1";
		List<KoPostVO> area_list = mainPageService.getAreaList(r_areacode);
		mv.addObject("area_list", area_list);

		// 테마별 추천경로 가져오기 (처음 관광지로 지정)
		String r_contenttypeid = "12";
		List<KoPostVO> tema_list = mainPageService.getTemaList(r_contenttypeid);
		mv.addObject("tema_list", tema_list);

		// 지역별 버튼 표시해주기 위한 정보보내기
		Map<String, String> area = new LinkedHashMap<String, String>();
		area.put("1", "서울");
		area.put("2", "인천");
		area.put("3", "대전");
		area.put("4", "대구");
		area.put("5", "광주");
		area.put("6", "부산");
		area.put("7", "울산");
		area.put("8", "세종");
		area.put("31", "경기");
		area.put("32", "강원");
		area.put("33", "충북");
		area.put("34", "충남");
		area.put("35", "경북");
		area.put("36", "경남");
		area.put("37", "전북");
		area.put("38", "전남");
		area.put("39", "제주");
		mv.addObject("area", area);

		// 테마별 버튼 표시해주기 위한 정보보내기
		Map<String, String> tema = new LinkedHashMap<String, String>();
		tema.put("12", "관광지");
		tema.put("15", "행사/공연/축제");
		tema.put("39", "음식점");
		mv.addObject("tema", tema);

		//	팝업정보 가져오기
		PopupVO popvo = mainPageService.popupOne();
		mv.addObject("popvo", popvo);

		return mv;
	}
}
