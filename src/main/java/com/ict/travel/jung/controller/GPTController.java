package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GPTController {
	
	
	@RequestMapping(value = "gpttest", produces="application/json; charset=utf-8")
	@ResponseBody
	// @RequestBody를 통해 요청의 Body부분에 있는 데이터를 받아서 사용 가능합니다.
	public  String memberChk(@RequestBody String messages) {
		try {
			String apiURL = "https://api.openai.com/v1/chat/completions";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			System.out.println(messages);
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			
			BufferedWriter bw = 
					new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(messages);
			bw.flush();
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
	@RequestMapping(value = "assitest", produces="application/json; charset=utf-8")
	@ResponseBody
	// @RequestBody를 통해 요청의 Body부분에 있는 데이터를 받아서 사용 가능합니다.
	public  String assitest() {
		try {
			String apiURL = "https://api.openai.com/v1/assistants";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        String messages = "{\n" +
	                "    \"instructions\": \"You are an HR bot, and you have access to files to answer employee questions about company policies.\",\n" +
	                "    \"tools\": [{\"type\": \"file_search\"}],\n" +
	                "    \"tool_resources\": {\"file_search\": {\"vector_store_ids\": [\"vs_oZkAlBMikSAYzdA5cuZWeOCM\"]}},\n" +
	                "    \"model\": \"gpt-4-turbo\"\n" +
	                "}";
			System.out.println(messages);
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
			
			BufferedWriter bw = 
					new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(messages);
			bw.flush();
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
	@RequestMapping(value = "assitest", produces="application/json; charset=utf-8")
	@ResponseBody
	// @RequestBody를 통해 요청의 Body부분에 있는 데이터를 받아서 사용 가능합니다.
	public  String assitest() {
		try {
			String apiURL = "https://api.openai.com/v1 /threads/thread_rhKkhMf6Np7b8e7F5kRpR0OG/messages";
			String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String messages = "{\n" +
					"    \"instructions\": \"You are an HR bot, and you have access to files to answer employee questions about company policies.\",\n" +
					"    \"tools\": [{\"type\": \"file_search\"}],\n" +
					"    \"tool_resources\": {\"file_search\": {\"vector_store_ids\": [\"vs_oZkAlBMikSAYzdA5cuZWeOCM\"]}},\n" +
					"    \"model\": \"gpt-4-turbo\"\n" +
					"}";
			System.out.println(messages);
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+api_key);
			conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
			
			BufferedWriter bw = 
					new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(messages);
			bw.flush();
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