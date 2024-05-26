package com.ict.travel.jung.gpttools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.lee.dao.MemberVO;

@Component
public class PersonalAssistantsTools {
		// PerGPT 스레드 생성 메서드(회원별 생성)
		public String perThreadCreate() {
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
		// PerGPT Thread에 메세지 추가 메서드
		public String perMessageAdd(String perThread_id, String contents) {
			try {
				String apiURL = "https://api.openai.com/v1/threads/"+perThread_id+"/messages";
				String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
				URL url = new URL(apiURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				String messages = "{\n" +
						"      \"role\": \"user\",\n" +
						"      \"content\": \""+contents+"\"\n" +
						"}";
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
		// PerGPT Thread 저장소 안에 메세지를 기반으로 답변 생성 메서드
		public String perAnswerCreate(String perThread_id) {
			try {
				String apiURL = "https://api.openai.com/v1/threads/"+perThread_id+"/runs";
				System.out.println(apiURL);
				String api_key = "sk-proj-yEkSRF1dONAgQbeCrVazT3BlbkFJvbZYLevgHSgz0Icexd0c";
				URL url = new URL(apiURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				String assistant_set = "{\n" +
						"      \"assistant_id\": \"asst_l4zXwA0L5kgtX4YNr0uPCyec\"\n" +
						"}";
				// POST 요청
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				// 헤더 요청
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Authorization", "Bearer "+api_key);
				conn.setRequestProperty("OpenAI-Beta", "assistants=v2");
				
				BufferedWriter bw = 
						new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				bw.write(assistant_set);
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
		// PerGPT Thread 저장소 안에 있는 메세지 반환 메서드
		public String perMessagesList(String perThread_id) {
			try {
				String apiURL = "https://api.openai.com/v1/threads/"+perThread_id+"/messages";
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