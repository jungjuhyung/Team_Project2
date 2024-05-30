package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.common.MapConverter;

@RestController
public class JungAjaxController {
	@Autowired
	private MapConverter mapConverter;
	
	@RequestMapping(value = "weather", produces="application/json; charset=utf-8")
	@ResponseBody
	public String weather(String ajax_x, String ajax_y) {
		try {
	        // 현재 시간을 가져옵니다.
	        LocalDateTime now = LocalDateTime.now();
	        
	        // 연월일을 "YYYYMMDD" 형식으로 포맷팅합니다.
	        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	        // 시간을 "hh00" 형식으로 포맷팅합니다.
	        int hour = now.getHour();
	        String time;
	        if ((0 <= hour && hour < 2) || hour == 23) {
				time = "2300";
			}else if (2 <= hour && hour < 5) {
				time = "0200";
			}else if (5<=hour&& hour < 8) {
				time = "0500";
			}else if (8<=hour&& hour < 11) {
				time = "0800";
			}else if (11<=hour&& hour < 14) {
				time = "1100";
			}else if (14<=hour&& hour < 17) {
				time = "1400";
			}else if (17<=hour&& hour < 20) {
				time = "1700";
			}else if (20<=hour&& hour < 23) {
				time = "2000";
			}else {
				time = "0800";
			}
	        System.out.println(hour);
	        System.out.println(time);
	        // api 좌표를 격자 좌표로 변환
        	double lon = Double.parseDouble(ajax_x);
        	double lat = Double.parseDouble(ajax_y);
        	double[] grid = mapConverter.mapConv(lon, lat,1);
        	String x = String.valueOf((int)grid[0]);
        	String y = String.valueOf((int)grid[1]);
        	System.out.println(x);
        	System.out.println(y);
        	
        	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=GX4kjX1Z1eB5QSo187qeWIlnJyW7fxq04WHUQmTuGNpcNFvSaN8rxGhj5pOkfgHRYmXiKnpZxasyxkIWBTm2aw=="); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*‘21년 6월 28일발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*05시 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); /*예보지점의 Y 좌표값*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
			return sb.toString();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return "error";
	}
	// 관리자용 스레드 및 파일 삭제용 ajax
	@RequestMapping(value = "thread_del", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String thread_del() {
		try {		
			String del_thread = "thread_1d0SArXCsuv11MYVSILxiaR7";
			String apiURL = "https://api.openai.com/v1/threads/"+del_thread;
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// GET 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			conn.setRequestMethod("DELETE");
			
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
			
	
			int responeseCode = conn.getResponseCode();
			System.out.println("1"+responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				return result;
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
	
	// 관리자용 ChatBot 스레드 생성 ajax
	@RequestMapping(value = "thread_create", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String thread_create() {
		try {
			String apiURL = "https://api.openai.com/v1/threads";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");

			int responeseCode = conn.getResponseCode();
			System.out.println(responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				return result;
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
	// Thread Message 저장소 내용 확인
	@RequestMapping(value = "thread_message", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String thread_message() {
		try {
			String apiURL = "https://api.openai.com/v1/threads/thread_XqJH8EvWtG9rRIWCoci5RW1r/messages";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// GET 요청
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
			
			int responeseCode = conn.getResponseCode();
			System.out.println(responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				return result;
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
	// assistant 설정 확인
	@RequestMapping(value = "assi_chk", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String assi_chk() {
		try {
			String apiURL = "https://api.openai.com/v1/assistants?order=desc&limit=20";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// GET 요청
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
			
			int responeseCode = conn.getResponseCode();
			System.out.println(responeseCode);
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line ="";
				StringBuffer sb2 = new StringBuffer();
				while((line=br.readLine()) !=null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				return result;
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}
}
