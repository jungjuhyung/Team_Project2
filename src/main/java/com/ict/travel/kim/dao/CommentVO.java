package com.ict.travel.kim.dao;

public class CommentVO {
	private String comment_idx, board_idx, u_nickname, content, regdate, u_idx, path_post_idx, u_lev, u_id;

	public String getComment_idx() {
		return comment_idx;
	}


	public String getU_id() {
		return u_id;
	}


	public void setU_id(String u_id) {
		this.u_id = u_id;
	}


	public String getU_lev() {
		return u_lev;
	}


	public void setU_lev(String u_lev) {
		this.u_lev = u_lev;
	}


	public String getPath_post_idx() {
		return path_post_idx;
	}


	public void setPath_post_idx(String path_post_idx) {
		this.path_post_idx = path_post_idx;
	}


	public void setComment_idx(String comment_idx) {
		this.comment_idx = comment_idx;
	}

	public String getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}

	public String getU_nickname() {
		return u_nickname;
	}

	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}
	
}
