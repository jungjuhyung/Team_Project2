package com.ict.travel.kim.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TourtestDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<TourtestVO> tourDetail(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectList("tourtest_t.tourDetail",path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<TourtestVO> tourMaps(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectList("tourtest_t.tourMaps", path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<TourtestVO> tourImg(String path_post_idx) {
		try {
			return sqlSessionTemplate.selectList("tourtest_t.tourImg",path_post_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<TourtestVO> getImageListByMarkerId(String path_marker_idx) {
		try {
			return sqlSessionTemplate.selectList("tourtest_t.markerImgList",path_marker_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	
	
}
