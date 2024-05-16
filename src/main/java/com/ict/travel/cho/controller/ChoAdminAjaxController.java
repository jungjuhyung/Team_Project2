package com.ict.travel.cho.controller;

import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.cho.dao.DataFetcher;
import com.ict.travel.cho.dao.TourapiParser;
import com.ict.travel.cho.dao.TourapiVO;
import com.ict.travel.cho.service.ChoService;

@RestController
public class ChoAdminAjaxController {
	
	@Autowired
	private ChoService choService;
	@Autowired
	private TourapiParser tourapiParser;
	@Autowired
	private DataFetcher dataFetcher;
	
	// 디비 최신화
	@RequestMapping(value = "updateTest", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String updateDB() throws Exception{
	
        String result1 = dataFetcher.fetchData("12");
        String result2 = dataFetcher.fetchData("15");
        String result3 = dataFetcher.fetchData("39");
        
        // JSON 데이터를 JSONObject로 파싱
        JSONObject obj1 = (JSONObject) JSONValue.parse(result1);
        JSONObject response1 = (JSONObject) obj1.get("response");
        JSONObject body1 = (JSONObject) response1.get("body");
        JSONObject items1 = (JSONObject) body1.get("items");
        JSONArray itemArray1 = (JSONArray) items1.get("item");
        
        JSONObject obj2 = (JSONObject) JSONValue.parse(result2);
        JSONObject response2 = (JSONObject) obj2.get("response");
        JSONObject body2 = (JSONObject) response2.get("body");
        JSONObject items2 = (JSONObject) body2.get("items");
        JSONArray itemArray2 = (JSONArray) items2.get("item");
        
        JSONObject obj3 = (JSONObject) JSONValue.parse(result3);
        JSONObject response3 = (JSONObject) obj3.get("response");
        JSONObject body3 = (JSONObject) response3.get("body");
        JSONObject items3 = (JSONObject) body3.get("items");
        JSONArray itemArray3 = (JSONArray) items3.get("item");
        
    
        // itemArray를 TourapiParser를 사용하여 List<TourapiVO>로 파싱
        List<TourapiVO> touristVoList = tourapiParser.parseJsonToVO(itemArray2.toString());
        List<TourapiVO> PartyVoList = tourapiParser.parseJsonToVO(itemArray1.toString());
        List<TourapiVO> restaurantVoList = tourapiParser.parseJsonToVO(itemArray3.toString());

        choService.dataUpdate(touristVoList);
		choService.dataUpdate(PartyVoList);
		choService.dataUpdate(restaurantVoList);
		
        return "Success";
	}
		

	
}