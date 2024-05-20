package com.ict.travel.ko.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.travel.lee.dao.MemberVO;


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
	
	public int popupInsert(PopupVO popvo) {
		return sqlSessionTemplate.insert("ko.popup_insert", popvo);
	}

	public List<PopupVO> popupList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("ko.popup_list", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public PopupVO popupOne() {
		return sqlSessionTemplate.selectOne("ko.popup_one");
	}

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public int popupUpdate(String popup_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("ko.popup_update_off");
			result += sqlSessionTemplate.update("ko.popup_update_on", popup_idx);
			transactionManager.commit(status);
			System.out.println("팝업이미지 변경 성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("팝업이미지 변경 실패");
		}
		return -1;
	}
	
	public int popupDelete(String popup_idx) {
		return sqlSessionTemplate.delete("ko.popup_delete", popup_idx);
	}
	
	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("ko.popup_count");
	}
	
	
	public int getTotalUser() {
		return sqlSessionTemplate.selectOne("ko.user_count");
	}
	
	public List<MemberVO> getUserList() {
		return sqlSessionTemplate.selectList("ko.user_total");
	}
	
	public List<MemberVO> getUserList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("ko.user_list", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public int getStopUpdate(String u_idx) {
		return sqlSessionTemplate.update("ko.stop_update", u_idx);
	}
	
}







