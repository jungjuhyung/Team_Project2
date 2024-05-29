package com.ict.travel.lee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	// Controller에서 호출
	public void sendEmail(String randomNumber, String toMail) {
		try {
			MailHandler sendMail = new MailHandler(mailSender);
			// 메일 제목
			sendMail.setSubject("[본인확인 인증.]");

			// 메일 내용
			// 내용
			sendMail.setText("" 
					+ "<table><tbody>" 
					+ "<tr><td><h2>Five Guys 메일 인증</h2></td></tr>"
					+ "<tr><td></td></tr>" 
					+ "<tr><td></td></tr>"
					+ "<tr><td><font size='10px'>임시 비밀번호 : " + randomNumber + "</font></td></tr>" 
					+ "</tbody></table>");

			// 보내는 이
			sendMail.setFrom("wol02095@gmail.com", "FIVEGYES");

			// 받는 이
			sendMail.setTo(toMail);
			sendMail.send();

		} catch (Exception e) {
			System.out.println(e);
		}
		

	}

//	public void sendEmailId(String u_id, String email) {
//		try {
//			MailHandler sendMail = new MailHandler(mailSender);
//			
//			sendMail.setSubject("[본인확인 인증번호입니다.]");
//			
//			sendMail.setText("" 
//					+ "<table><tbody>" 
//					+ "<tr><td><h2>Five Guys 메일 인증</h2></td></tr>"
//					+ "<tr><td><h3>Five Guys</h3></td></tr>" 
//					+ "<tr><td><font size='20px'>인증번호 안내입니다.</font></td></tr>"
//					+ "<tr><td></td></tr>" 
//					+ "<tr><td>안녕하세요 인증번호 생성되었습니다.</td></tr>" 
//					+ "<tr><td></td></tr>"
//					+ "<tr><td><font size='20px'>확인번호 : " + u_id + "</font></td></tr>" 
//					+ "</tbody></table>");
//			
//			sendMail.setFrom("wol02095@gmail.com", "FIVEGYES");
//			
//			sendMail.setTo2(email);
//			sendMail.send();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//	}
	
	
}
