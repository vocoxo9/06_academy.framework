package com.kh.todo.todoList.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.todo.member.model.vo.Member;
import com.kh.todo.todoList.model.dao.TodoDAO;
import com.kh.todo.todoList.model.vo.Todo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

	private final TodoDAO todoDAO;

	@Override
	public int insertTodo(Todo todo) {
		return todoDAO.insertTodo(todo);
	}

	@Override
	public int deleteTodo(int todoNo) {
		return todoDAO.deleteTodo(todoNo);
	}

	@Override
	public int updateTodo(Todo todo) {
		return todoDAO.updateTodo(todo);
	}

	@Override
	public ArrayList<Todo> myTodoList(int memberNo) {
		return todoDAO.myTodoList(memberNo);
	}

}
