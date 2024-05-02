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

	public int reportWrite(ReportVO reportvo) {
		try {
			return sqlSessionTemplate.insert("report_t.reportWrite", reportvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public ReportVO reportDetail(String report_idx) {
		try {
			return sqlSessionTemplate.selectOne("report_t.reportDetail", report_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int reportDelete(String report_idx) {
		try {
			return sqlSessionTemplate.delete("report_t.reportDelete", report_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int reportUpdate(ReportVO reportvo) {
		try {
			return sqlSessionTemplate.update("report_t.reportUpdate", reportvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
