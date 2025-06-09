package com.kh.todoAPI.post.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.todoAPI.post.model.dto.TodoDTO;
import com.kh.todoAPI.post.model.vo.Todo;

@Repository
public class TodoMapper {
	private final SqlSession sqlSession;
	public TodoMapper(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 할일 목록 조회
	public List<Todo> findAll(String userId) {
		return sqlSession.selectList("todoMapper.findAll", userId);
	}
	
	// 할일 추가
	public int insertTodo(TodoDTO todoDTO) {
		return sqlSession.insert("todoMapper.insertTodo", todoDTO);
	}
	
	public Todo selectByMaxNo(String userId) {
		return sqlSession.selectOne("todoMapper.selectByMaxNo", userId);
	}
}
