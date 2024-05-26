package com.ict.travel.lee.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jcraft.jsch.UserInfo;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 아이디 중복 체크
	public String getIdChk(String u_id) {
		try {
			int result = sqlSessionTemplate.selectOne("lee-mapper.idchk", u_id);
			
			if(result > 0) {
				return "0";
				
			}
			return "1";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public String getNickChk(String u_nickname) {
		try {
			int result = sqlSessionTemplate.selectOne("lee-mapper.nickchk", u_nickname);
			
			if(result > 0) {
				return "0";
			}
			return "1";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 회원가입
	public int getSignUp(MemberVO mvo) {
		try {
			return sqlSessionTemplate.insert("lee-mapper.insert", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	
	
	// 로그인
	public MemberVO getLoginOK(MemberVO mvo) {
		try {
			return sqlSessionTemplate.selectOne("lee-mapper.login", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// 비밀번호 찾기(이메일로 전송)
	public MemberVO getFindPW(String u_id, String email) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("u_id", u_id);
			map.put("email", email);
			return sqlSessionTemplate.selectOne("lee-mapper.findPwd", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	// 비밀번호 변경
	public int PassUpdate(MemberVO memberVO) {
		try {
			return sqlSessionTemplate.update("lee-mapper.pass_update", memberVO);
		} catch (Exception e) {
			System.out.println("err : "+e);
		}
		return -1;
	}
	
	// 아이디 찾기
	public List<MemberVO> getFindId(MemberVO mvo) {
		try {
			return sqlSessionTemplate.selectList("lee-mapper.findId", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// 카카오 정보조회
	public MemberVO findkakao(HashMap<String, Object> userInfo) {
		try {
			int result = sqlSessionTemplate.selectOne("lee-mapper.findkakao", userInfo);
			if(result > 0) {
				result = sqlSessionTemplate.update("lee-mapper.kakaoUp", userInfo);
				MemberVO mvo2 = sqlSessionTemplate.selectOne("lee-mapper.select2", userInfo);
				return mvo2;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 카카오 로그인시 DB 저장
	public int kakaoinsert(HashMap<String, Object> userInfo) {
		try {
			System.out.println("RN:"+userInfo.get("nickname"));
			System.out.println("RE:"+userInfo.get("email"));
			return sqlSessionTemplate.insert("lee-mapper.kakao_insert", userInfo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
		
	}

	// 네이버 정보조회
	public MemberVO findnaver(HashMap<String, Object> userInfo2) {
		try {
			System.out.println("1");
			int result = sqlSessionTemplate.selectOne("lee-mapper.findnaver", userInfo2);
			if(result > 0) {
				result = sqlSessionTemplate.update("lee-mapper.naverUp", userInfo2);
				MemberVO mvo = sqlSessionTemplate.selectOne("lee-mapper.select1", userInfo2);
				return mvo;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 네이버 로그인시 DB 저장
	public int naverinsert(HashMap<String, Object> userInfo2) {
		try {
			System.out.println("2");
			return sqlSessionTemplate.insert("lee-mapper.naver_insert", userInfo2);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
		
	}
	public MemberVO getMemberUpDetail(String u_idx) {
		try {
			return sqlSessionTemplate.selectOne("lee-mapper.memdetail", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getMemberUpOk(MemberVO mvo) {
		try {
			return sqlSessionTemplate.update("lee-mapper.member_up", mvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
}
