package com.kh.todoAPI.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.todoAPI.post.model.dao.TodoMapper;
import com.kh.todoAPI.post.model.dto.TodoDTO;
import com.kh.todoAPI.post.model.vo.Todo;

@Service
public class TodoService {

	private final TodoMapper todoMapper;
	public TodoService(TodoMapper todoMapper) {
		this.todoMapper = todoMapper;
	}
	
	// 할일 목록 조회
	public List<Todo> getTodoList(String userId) {
		return todoMapper.findAll(userId);
	}
	
	// 할일 추가 후 조회
	public Todo insertTodo(TodoDTO todoDTO) {
		Todo newTodo = null;
		
		int result = todoMapper.insertTodo(todoDTO);
		
		if(result > 0) {
			newTodo = todoMapper.selectByMaxNo(todoDTO.getUserId());
		}
		
		return newTodo;
	}
	
}
