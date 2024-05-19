package com.ict.travel.cho.dao;

public class AdminVO {
	private String admin_idx, admin_id, admin_pwd, admin_grade, admin_state, admin_note, regdate, updatetime;
	
	public String getAdmin_state() {
		return admin_state;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}


	public String getAdmin_note() {
		return admin_note;
	}

	public void setAdmin_note(String admin_note) {
		this.admin_note = admin_note;
	}

	public String getAdmin_idx() {
		return admin_idx;
	}

	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pwd() {
		return admin_pwd;
	}

	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}

	public String getAdmin_grade() {
		return admin_grade;
	}

	public void setAdmin_grade(String admin_grade) {
		this.admin_grade = admin_grade;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
