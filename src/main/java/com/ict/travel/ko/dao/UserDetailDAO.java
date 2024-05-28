package com.ict.travel.ko.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;

@Repository
public class UserDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public UserVO getUserDetail(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user_one.detail", u_idx);
	}

	public int getBoardCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user_one.board_count", u_idx);
	}

	public List<BoardVO> getBoardList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko_user_one.board_list", map);
	}

	public int getReportCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user_one.report_count", u_idx);
	}

	public List<ReportVO> getReportList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko_user_one.report_list", map);
	}

	public int getPathCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user_one.path_count", u_idx);
	}

	public List<KpostVO> getPathList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko_user_one.path_list2", map);
	}

	public int getCommentCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user_one.comment_count", u_idx);
	}

	public List<CommentVO> getCommentList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko_user_one.comment_list", map);
	}

}
