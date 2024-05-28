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

import com.ict.travel.kim.dao.BoardVO;
import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.kim.dao.ReportVO;


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

	public PopupVO popupOne() {
		return sqlSessionTemplate.selectOne("ko.popup_one");
	}
	// ==========================================================================

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

	// =====================================================================================

	
	public int getTotalUser() {
		return sqlSessionTemplate.selectOne("ko.user_count");
	}
	
	public int getSearchTotal(String search) {
		return sqlSessionTemplate.selectOne("ko.search_count", search);
	}

	public List<UserVO> getStopUser() {
		return sqlSessionTemplate.selectList("ko.stop_user");
	}

	public List<UserVO> getUserList(int offset, int limit) {
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
	
	public UserStopVO getStopDetail(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.stop_detail", u_idx);
	}

	public List<UserVO> getSearchUser(PageVO pvo) {
		return sqlSessionTemplate.selectList("ko.user_search", pvo);
	}

	public int getStopState(String u_idx, String ustop_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.update("ko.stop_state", u_idx);
			result += sqlSessionTemplate.delete("ko.stop_delete", ustop_idx);
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
			result += sqlSessionTemplate.update("ko.stop_update", map);
			result += sqlSessionTemplate.insert("ko.user_stop", map);
			transactionManager.commit(status);
			System.out.println("유저 정지 성공");
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("유저 정지 실패");
		}
		return -1;
	}
	
	//================================================================

	public UserVO getUserDetail(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.user_detail", u_idx);
	}
	
	public int getBoardCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.board_count", u_idx);
	}
	
	public List<BoardVO> getBoardList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko.board_list", map);
	}
	
	public int getReportCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.report_count", u_idx);
	}
	
	public List<ReportVO> getReportList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko.report_list", map);
	}
	
	public int getPathCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.path_count", u_idx);
	}
	
	public List<KpostVO> getPathList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko.path_list2", map);
	}
	
	public int getCommentCount(String u_idx) {
		return sqlSessionTemplate.selectOne("ko.comment_count", u_idx);
	}
	
	public List<CommentVO> getCommentList(String u_idx, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_idx", u_idx);
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("ko.comment_list", map);
	}
	
}






















