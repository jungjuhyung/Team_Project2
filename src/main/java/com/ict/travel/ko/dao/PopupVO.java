package com.ict.travel.ko.dao;

import org.springframework.web.multipart.MultipartFile;

public class PopupVO {
	private String popup_idx, admin_id, subject, f_name, popup_state, regdate;
	private MultipartFile file;
	public String getPopup_idx() {
		return popup_idx;
	}
	public void setPopup_idx(String popup_idx) {
		this.popup_idx = popup_idx;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getPopup_state() {
		return popup_state;
	}
	public void setPopup_state(String popup_state) {
		this.popup_state = popup_state;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
