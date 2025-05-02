package com.kh.todoAPI.user.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.todoAPI.user.model.dto.UserDTO;

@Repository
public class UserMapper {
	
	private final SqlSession sqlSession;
	public UserMapper(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// class => 빈 등록 : @Repository, DB연동(작업) : SqlSession
	// interface => 빈 등록 : @Mapper, DB연동(작업) : 추상메소드 정의
	//					mapper.xml 파일 namespace에 해당 인터페이스 전체 경로 설정
	
	
	// 전달된 아이디와 일치하는 개수 조회
	public int countById(String id) {
		return sqlSession.selectOne("userMapper.countById", id);
	}
	
	// 전달된 회원 정보를 DB에 추가
	public int signupUser(UserDTO userDTO) {
		return sqlSession.insert("userMapper.signupUser", userDTO);
	}
}
