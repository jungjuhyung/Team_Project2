package com.ict.travel.ko.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KoDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public List<KoVO> getAreaList(String areacode) {
		return sqlSessionTemplate.selectList("ko.area_list", areacode);
	}

	public List<KoVO> getTemaList(String contenttypeid) {
		return sqlSessionTemplate.selectList("ko.tema_list", contenttypeid);
	}

	public List<KoVO> getPathList(String contentid) {
		return sqlSessionTemplate.selectList("ko.path_list", contentid);
	}
	/*
	 * public List<KoPathVO> getPathList(String contentid) { return
	 * sqlSessionTemplate.selectList("ko.path_list", contentid); }
	 */

	public ItemVO getPlaceDetail(String contentid) {
		return sqlSessionTemplate.selectOne("ko.place_detail", contentid);
	}


}
