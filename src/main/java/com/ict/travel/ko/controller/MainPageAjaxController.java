package com.ict.travel.ko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.service.MainPageService;

@RestController
public class MainPageAjaxController {

	@Autowired
	private MainPageService mainPageService;

	// 지역별 버튼 클릭 시 추천경로 리스트 가져오기(ajax)
	@RequestMapping(value = "main_ajax_area.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koAreaList(String r_areacode) {
		List<KoPostVO> area_list = mainPageService.getAreaList(r_areacode);

		Gson gson = new Gson();
		String area_json = gson.toJson(area_list);

		return area_json;
	}

	// 테마별 버튼 클릭 시 추천경로 리스트 가져오기(ajax)
	@RequestMapping(value = "main_ajax_tema.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koTemaList(String r_contenttypeid) {
		List<KoPostVO> tema_list = mainPageService.getTemaList(r_contenttypeid);

		Gson gson = new Gson();
		String tema_json = gson.toJson(tema_list);

		return tema_json;
	}
}
