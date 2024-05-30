package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ict.travel.common.PersonalAssistantsTools;
import com.ict.travel.jung.dao.GptDAO;
import com.ict.travel.jung.service.GptService;
import com.ict.travel.jung.vo.GptAwsVO;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class PersonalAssistantsAjaxController {
	@Autowired
	private PersonalAssistantsTools perTools;
	@Autowired
	private GptService gptService;
	
	@RequestMapping(value = "perbot", produces="application/json; charset=utf-8")
	@ResponseBody
	public String personalBot(HttpSession session) {
		String aws = (String) session.getAttribute("gptAws");
		String[] values = aws.split(",");
		List<GptAwsVO> value_list = new ArrayList<GptAwsVO>();
		
		for (String contentid : values) {
			GptAwsVO add = gptService.getAwsValue(contentid);
			if (add != null) {
				value_list.add(add);				
			}
		}
		if (!value_list.isEmpty()) {
			Gson gson = new Gson();
			String jsonString =gson.toJson(value_list);
			return jsonString;
		}	
		return "fail";
	}
	
	@RequestMapping(value = "re_recommend", produces="application/json; charset=utf-8")
	@ResponseBody
	public String re_recommend(HttpSession session) {
		MemberVO mvo2 = (MemberVO) session.getAttribute("memberUser");
		String message = "vector_storage(vs_PhohXJdcZlxuz5yNEzJIsK9m)에 저장되어있는 데이터에서 오늘 추천해준 장소를 제외하고 다른 5곳을 추천해줘.";
		perTools.perMessageAdd(mvo2.getU_per_thread_id(), message);
		perTools.perAnswerCreate(mvo2.getU_per_thread_id());
		String gptAws = perTools.perMessagesList(mvo2.getU_per_thread_id());
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
        // JSON 문자열을 JsonElement로 파싱
        JsonElement jsonElement = parser.parse(gptAws);

        // JsonElement를 JsonObject로 변환
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // data 필드에서 content 필드의 value 값을 추출
        List<String> values = new ArrayList<>();
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        for (JsonElement element : dataArray) {
            JsonObject dataObject = element.getAsJsonObject();
            JsonArray contentArray = dataObject.getAsJsonArray("content");
            for (JsonElement contentElement : contentArray) {
                JsonObject contentObject = contentElement.getAsJsonObject();
                JsonObject textObject = contentObject.getAsJsonObject("text");
                String value = textObject.get("value").getAsString();
                values.add(value);
            }
        }
        session.setAttribute("gptAws", values.get(0).replace(" ", ""));
		String[] value = values.get(0).replace(" ", "").split(",");
		List<GptAwsVO> value_list = new ArrayList<GptAwsVO>();
		
		for (String contentid : value) {
			GptAwsVO add = gptService.getAwsValue(contentid);
			System.out.println("2");
			if (add != null) {
				value_list.add(add);				
			}
		}
		if (!value_list.isEmpty()) {
			String jsonString =gson.toJson(value_list);
			return jsonString;
		}	
		return "fail";
	}
}