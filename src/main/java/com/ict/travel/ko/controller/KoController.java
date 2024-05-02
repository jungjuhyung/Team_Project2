package com.ict.travel.ko.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoVO;
import com.ict.travel.ko.service.KoService;

@Controller
public class KoController {

	@Autowired
	private KoService koService;

	@RequestMapping("main_page.do")
	public ModelAndView getKo(String areacode, String contenttypeid) {
		ModelAndView mv = new ModelAndView("ko_view/main_page");
		System.out.println("areacode : " + areacode);
		System.out.println("contenttypeid : " + contenttypeid);

		List<KoVO> area_list = koService.getAreaList(areacode);
		List<KoVO> tema_list = koService.getTemaList(contenttypeid);
		
		mv.addObject("area_list", area_list);
		mv.addObject("tema_list", tema_list);
		
		Map<String, String> area = new HashMap<String, String>();
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
		
		Map<String, String> tema = new HashMap<String, String>();
		tema.put("12", "관광지");
		tema.put("15", "행사/공연/축제");
		tema.put("39", "음식점");
		mv.addObject("tema", tema);
		
		return mv;
	}

	@RequestMapping("ko_detail.do")
	public ModelAndView getKoDetail(@ModelAttribute("contentid") String contentid,
			@ModelAttribute("contenttypeid") String contenttypeid) {
		ModelAndView mv = new ModelAndView("ko_view/detail");
		System.out.println("contentid : " + contentid);
		System.out.println("contenttypeid : " + contenttypeid);
		

		try {
			// 공통 정보 조회
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailCommon1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentid);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeid);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 소개 정보 조회
			StringBuilder urlBuilder2 = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailIntro1");
			urlBuilder2.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder2.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentid);
			urlBuilder2.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeid);
			urlBuilder2.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder2.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder2.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			URL url2 = new URL(urlBuilder2.toString());

			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			conn2.setRequestProperty("Content-type", "application/json");

			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			int responseCode2 = conn.getResponseCode();
			System.out.println(responseCode);
			System.out.println(responseCode2);

			if (responseCode == HttpURLConnection.HTTP_OK && responseCode2 == 200) {
				// 공통 정보 조회
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println(result);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(result);
				JSONObject response = (JSONObject) obj.get("response");
				JSONObject body = (JSONObject) response.get("body");
				JSONObject items = (JSONObject) body.get("items");
				JSONArray item = (JSONArray) items.get("item");
				JSONObject item_list = (JSONObject) item.get(0);
				
				// 소개 정보 조회
				BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String line2 = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line2 = br2.readLine()) != null) {
					sb2.append(line2);
				}
				String result2 = sb2.toString();
				System.out.println(result2);

				JSONParser parser2 = new JSONParser();
				JSONObject obj2 = (JSONObject) parser2.parse(result2);
				JSONObject response2 = (JSONObject) obj2.get("response");
				JSONObject body2 = (JSONObject) response2.get("body");
				JSONObject items2 = (JSONObject) body2.get("items");
				JSONArray item2 = (JSONArray) items2.get("item");
				JSONObject item_list2 = (JSONObject) item2.get(0);

				ItemVO itemVO = new ItemVO();
				itemVO.setContentid(item_list.get("contentid").toString());
				itemVO.setContenttypeid(item_list.get("contenttypeid").toString());
				itemVO.setTitle(item_list.get("title").toString());
				itemVO.setAddr1(item_list.get("addr1").toString());
				itemVO.setMapx(item_list.get("mapx").toString());
				itemVO.setMapy(item_list.get("mapy").toString());
				itemVO.setHomepage(item_list.get("homepage").toString());
				itemVO.setOverview(item_list.get("overview").toString());
				itemVO.setFirstimage(item_list.get("firstimage").toString());

				switch (item_list.get("contenttypeid").toString()) {
				case "12":
					itemVO.setExpguide(item_list2.get("expguide").toString());
					itemVO.setInfocenter(item_list2.get("infocenter").toString());
					itemVO.setParking(item_list2.get("parking").toString());
					itemVO.setRestdate(item_list2.get("restdate").toString());
					itemVO.setUsetime(item_list2.get("usetime").toString());
					break;
				case "15":
					itemVO.setEventplace(item_list2.get("eventplace").toString());
					itemVO.setEventstartdate(item_list2.get("eventstartdate").toString());
					itemVO.setEventenddate(item_list2.get("eventenddate").toString());
					itemVO.setPlaytime(item_list2.get("playtime").toString());
					itemVO.setSponsor1(item_list2.get("sponsor1").toString());
					itemVO.setSponsor1tel(item_list2.get("sponsor1tel").toString());
					itemVO.setUsetimefestival(item_list2.get("usetimefestival").toString());
					break;
				case "39":
					itemVO.setFirstmenu(item_list2.get("firstmenu").toString());
					itemVO.setTreatmenu(item_list2.get("treatmenu").toString());
					itemVO.setInfocenterfood(item_list2.get("infocenterfood").toString());
					itemVO.setOpentimefood(item_list2.get("opentimefood").toString());
					itemVO.setRestdatefood(item_list2.get("restdatefood").toString());
					break;
				}
				mv.addObject("itemVO", itemVO);
				return mv;
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
