package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GPTController {
	
	@RequestMapping(value = "gpttest", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String memberChk() {
	   // access_token를 가지고 사용자 정보를 가져올 수 있다.
		System.out.println("오냐");
		String apiURL = "https://api.openai.com/v1/chat/completions";
        String jsonData = "{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"system\",\n" +
                "        \"content\": \"You are a helpful assistant.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "        \"content\": \"Hello!\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 헤더 요청
			conn.setRequestProperty("Content-Type", "application/json");
			
			
			OutputStream outputStream = conn.getOutputStream();
	        outputStream.write(jsonData.getBytes());
	        outputStream.flush();
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