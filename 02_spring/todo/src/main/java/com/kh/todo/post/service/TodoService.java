package com.kh.todo.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.todo.post.model.dao.TodoMapper;
import com.kh.todo.post.model.dto.TodoDTO;
import com.kh.todo.post.model.vo.Todo;

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
	public Todo addTodo(TodoDTO todoDto) {
		Todo newTodo = null;
		
		int result = todoMapper.insert(todoDto);
		if (result > 0) {
			newTodo = todoMapper.selectByMaxNo(todoDto.getUserId());
		}
		
		return newTodo;
	}
	
}



