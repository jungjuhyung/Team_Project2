package com.ict.travel.ko.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class KoDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public List<KoPostVO> getAreaList(String r_areacode) {
		return sqlSessionTemplate.selectList("ko.area_list", r_areacode);
	}

	public List<KoPostVO> getTemaList(String r_contenttypeid) {
		return sqlSessionTemplate.selectList("ko.tema_list", r_contenttypeid);
	}

	public List<KoPostVO> getPathList(String contentid) {
		return sqlSessionTemplate.selectList("ko.path_list", contentid);
	}

	public ItemVO getPlaceDetail(String contentid) {
		return sqlSessionTemplate.selectOne("ko.place_detail", contentid);
	}


}
