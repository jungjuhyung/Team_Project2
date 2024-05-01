package com.ict.travel.kim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("report_t.reportCount");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public List<ReportVO> reportList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("report_t.reportList", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	public List<ReportVO> reportList() {
		try {
			return sqlSessionTemplate.selectList("report_t.reportlist");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
