package com.kh.todo.post.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.todo.post.model.dto.TodoDTO;
import com.kh.todo.post.model.vo.Todo;

@Repository
public class TodoMapper {
	
	private final SqlSession sqlSession;
	public TodoMapper(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<Todo> findAll(String userId) {
		return sqlSession.selectList("todoMapper.findAll", userId);
	}

	public int insert(TodoDTO todoDto) {
		return sqlSession.insert("todoMapper.insert", todoDto);
	}

	public Todo selectByMaxNo(String userId) {
		return sqlSession.selectOne("todoMapper.selectByMaxNo", userId);
	}

}
