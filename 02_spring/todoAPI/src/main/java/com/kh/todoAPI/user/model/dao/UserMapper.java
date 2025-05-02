package com.kh.todoAPI.user.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.todoAPI.user.model.dto.UserDTO;

@Repository
public class UserMapper {
	// * class => 빈 등록: @Repository, DB연동(작업): SqlSession 주입
	
	// * interface => 빈 등록: @Mapper, DB연동(작업): 추상메소드 정의
	//            mapper.xml 파일 namespace에 해당 인터페이스 전체 경로 설정
	private final SqlSession sqlSession;
	public UserMapper(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 전달된 아이디와 일치하는 개수 조회 (DML, SELECT, 단일 행)
	public int countById(String userId) {
		return sqlSession.selectOne("userMapper.countById", userId);
	}

	// 전달된 회원 정보를 DB에 추가 (DML, INSERT)
	public int signupUser(UserDTO userDto) {
		int result = sqlSession.insert("userMapper.insertUser", userDto);
		return result;
	}
}
