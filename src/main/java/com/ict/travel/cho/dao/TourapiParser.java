package com.ict.travel.cho.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourapiParser {

    public List<TourapiVO> parseJsonToVO(String jsonData) {
        List<TourapiVO> voList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            // JSON 문자열을 JSONArray로 변환
            JSONArray jsonArray = (JSONArray) parser.parse(jsonData);

            // JSONArray에서 JSONObject를 하나씩 추출하여 TourapiVO 객체로 변환
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                TourapiVO vo = new TourapiVO();

                // JSONObject에서 필요한 데이터 추출하여 TourapiVO에 설정
                vo.setAddr1(jsonObject.get("addr1").toString());
                vo.setAddr2(jsonObject.get("addr2").toString());
                vo.setAreacode(jsonObject.get("areacode").toString());
                vo.setCat1(jsonObject.get("cat1").toString());
                vo.setCat2(jsonObject.get("cat2").toString());
                vo.setCat3(jsonObject.get("cat3").toString());
                vo.setContentid(jsonObject.get("contentid").toString());
                vo.setContenttypeid(jsonObject.get("contenttypeid").toString());
                vo.setCreatedtime(jsonObject.get("createdtime").toString());
                vo.setFirstimage(jsonObject.get("firstimage").toString());
                vo.setMapx(jsonObject.get("mapx").toString());
                vo.setMapy(jsonObject.get("mapy").toString());
                vo.setModifiedtime(jsonObject.get("modifiedtime").toString());
                vo.setSigungucode(jsonObject.get("sigungucode").toString());
                vo.setTel(jsonObject.get("tel").toString());
                vo.setTitle(jsonObject.get("title").toString());
                vo.setZipcode(jsonObject.get("zipcode").toString());
                
                // 변환된 TourapiVO 객체를 리스트에 추가
                voList.add(vo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return voList;
    }
}