package com.ict.travel.ko.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.service.KoService;

@RestController
public class KoAjaxController {
	
	@Autowired
	private KoService koService;

	// 메인 페이지 지역별
	@RequestMapping(value = "ko_ajax_area.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koAreaList(String r_areacode) {
		List<KoPostVO> area_list = koService.getAreaList(r_areacode);

		Gson gson = new Gson();
		String area_json = gson.toJson(area_list);

		return area_json;
	}

	// 메인 페이지 테마별
	@RequestMapping(value = "ko_ajax_tema.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koTemaList(String r_contenttypeid) {
		List<KoPostVO> tema_list = koService.getTemaList(r_contenttypeid);

		Gson gson = new Gson();
		String tema_json = gson.toJson(tema_list);

		return tema_json;
	}

	@Autowired
	private ChoService choService;

	// 찜 추가 & 좋아요 증가
	@RequestMapping(value = "placeWishAdd2", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert2(String contentid, HttpSession session) {
		// System.out.println("contentid : " + contentid);
		String u_idx = (String) session.getAttribute("u_idx");

		int result = choService.getPlaceWishAdd(contentid, u_idx);
		if (result > 0) {
			ItemVO ivo = koService.getPlaceDetail(contentid);
			String like = ivo.getHeart();
			// System.out.println(like);
			return like;
		}
		return null;
	}

	// 찜 삭제 & 좋아요 감소
	@RequestMapping(value = "placeWishRemove2", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete2(String contentid, HttpSession session) {
		// System.out.println("contentid : " + contentid);
		String u_idx = (String) session.getAttribute("u_idx");

		int result = choService.getPlaceWishAdd(contentid, u_idx);
		if (result > 0) {
			ItemVO ivo = koService.getPlaceDetail(contentid);
			String like = ivo.getHeart();
			// System.out.println(like);
			return like;
		}
		return null;
	}
	
	// ==========================================================================================
	
	@RequestMapping(value = "ko_detailCommon1.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String koDetailCommon1(String contentId, String contenttypeId) {
		try {
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailCommon1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentId);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeId);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				// System.out.println(result);
				return result;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@RequestMapping(value = "ko_detailIntro1.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String koDetailIntro1(String contentId, String contenttypeId) {
		try {
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailIntro1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentId);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeId);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				// System.out.println(result);
				return result;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
