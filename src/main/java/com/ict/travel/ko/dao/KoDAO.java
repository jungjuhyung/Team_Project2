package com.ict.travel.ko.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public String getPlaceWish(ItemVO itemVO) {
		int result = Integer.parseInt(sqlSessionTemplate.selectOne("ko.wish_check", itemVO.getContentid()));
		System.out.println("wish_check : " + result);
		if (result > 0) {
			int res = 0;
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(def);
			try {
				res += sqlSessionTemplate.delete("ko.wish_delete", itemVO.getContentid());
				res += sqlSessionTemplate.update("ko.tour_like_minus", itemVO.getContentid());
				transactionManager.commit(status);
				System.out.println("찜해제성공");
				return "0";
			} catch (Exception e) {
				transactionManager.rollback(status);
				System.out.println("찜해제실패");
			}
		} else {
			int res = 0;
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = transactionManager.getTransaction(def);
			try {
				res += sqlSessionTemplate.insert("ko.wish_insert", itemVO);
				res += sqlSessionTemplate.update("ko.tour_like_plus", itemVO.getContentid());
				transactionManager.commit(status);
				System.out.println("찜하기성공");
				return "1";
			} catch (Exception e) {
				transactionManager.rollback(status);
				System.out.println("찜하기실패");
			}
		}
		return null;
	}

}
