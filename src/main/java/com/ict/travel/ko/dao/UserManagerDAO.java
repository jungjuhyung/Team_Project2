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
public class UserManagerDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int getTotalUser() {
		return sqlSessionTemplate.selectOne("ko_user.count");
	}

	public int getSearchTotal(String search) {
		return sqlSessionTemplate.selectOne("ko_user.search_count", search);
	}

	public List<UserVO> getStopUser() {
		return sqlSessionTemplate.selectList("ko_user.stop_user");
	}

	public List<UserVO> getUserList(int offset, int limit) {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("ko_user.list", map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public UserStopVO getStopDetail(String u_idx) {
		return sqlSessionTemplate.selectOne("ko_user.stop_detail", u_idx);
	}

	public List<UserVO> getSearchUser(PageVO pvo) {
		return sqlSessionTemplate.selectList("ko_user.search", pvo);
	}
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public int getStopState(String u_idx, String ustop_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("ko_user.stop_state", u_idx);
			result += sqlSessionTemplate.delete("ko_user.stop_delete", ustop_idx);
			transactionManager.commit(status);
			System.out.println("정지 해제 성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("정지 해제 실패");
		}
		return -1;
	}

	public int getStopUpdate(String stop_days, String u_idx, String stop_note, String admin_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", Integer.parseInt(u_idx));
		map.put("stop_days", stop_days);
		map.put("stop_note", stop_note);
		map.put("admin_id", admin_id);
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("ko_user.stop_update", map);
			result += sqlSessionTemplate.insert("ko_user.stop", map);
			transactionManager.commit(status);
			System.out.println("유저 정지 성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("유저 정지 실패");
		}
		return -1;
	}

}
