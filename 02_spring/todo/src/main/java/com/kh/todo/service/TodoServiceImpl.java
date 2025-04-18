package com.kh.todo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.todo.member.model.vo.Member;
import com.kh.todo.model.dao.TodoDAO;
import com.kh.todo.model.vo.Todo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

	private final TodoDAO todoDAO;

	@Override
	public int insertTodo(Todo todo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTodo(int todoNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTodo(Todo todo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Todo> myTodoList(int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
