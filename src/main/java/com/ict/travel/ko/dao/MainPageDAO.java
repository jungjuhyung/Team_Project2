package com.ict.travel.ko.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainPageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<KoPostVO> getAreaList(String r_areacode) {
		return sqlSessionTemplate.selectList("ko_main.area_list", r_areacode);
	}

	public List<KoPostVO> getTemaList(String r_contenttypeid) {
		return sqlSessionTemplate.selectList("ko_main.tema_list", r_contenttypeid);
	}

	public PopupVO popupOne() {
		return sqlSessionTemplate.selectOne("ko_main.popup_one");
	}

}
