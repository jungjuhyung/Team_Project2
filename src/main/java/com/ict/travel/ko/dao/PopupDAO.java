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

@Repository
public class PopupDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int popupInsert(PopupVO popvo) {
		return sqlSessionTemplate.insert("ko_popup.insert", popvo);
	}

	public List<PopupVO> popupList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("ko_popup.list", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public int popupUpdate(String popup_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("ko_popup.update_off");
			result += sqlSessionTemplate.update("ko_popup.update_on", popup_idx);
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
		return sqlSessionTemplate.delete("ko_popup.delete", popup_idx);
	}

	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("ko_popup.count");
	}

}
