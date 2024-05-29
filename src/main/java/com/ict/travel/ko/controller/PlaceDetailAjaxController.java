package com.ict.travel.ko.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.cho.service.ChoService;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.service.PlaceDetailService;

@RestController
public class PlaceDetailAjaxController {

	@Autowired
	private PlaceDetailService placeDetailService;

	// 찜 추가 & 좋아요 증가
	@RequestMapping(value = "detailPlaceWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert2(String contentid, HttpSession session) {
		// System.out.println("contentid : " + contentid);
		String u_idx = (String) session.getAttribute("u_idx");

		int result = placeDetailService.getPlaceWishAdd(contentid, u_idx);
		if (result > 0) {
			ItemVO ivo = placeDetailService.getPlaceDetail(contentid);
			String like = ivo.getHeart();
			// System.out.println(like);
			return like;
		}
		return null;
	}

	// 찜 삭제 & 좋아요 감소
	@RequestMapping(value = "detailPlaceWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete2(String contentid, HttpSession session) {
		// System.out.println("contentid : " + contentid);
		String u_idx = (String) session.getAttribute("u_idx");

		int result = placeDetailService.getPlaceWishRemove(contentid, u_idx);
		if (result > 0) {
			ItemVO ivo = placeDetailService.getPlaceDetail(contentid);
			String like = ivo.getHeart();
			// System.out.println(like);
			return like;
		}
		return null;
	}

}
