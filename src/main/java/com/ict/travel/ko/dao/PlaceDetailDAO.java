package com.ict.travel.ko.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<KoPostVO> getPathList(String contentid) {
		return sqlSessionTemplate.selectList("ko_detail.path_list", contentid);
	}

	public ItemVO getPlaceDetail(String contentid) {
		return sqlSessionTemplate.selectOne("ko_detail.place_detail", contentid);
	}
}
