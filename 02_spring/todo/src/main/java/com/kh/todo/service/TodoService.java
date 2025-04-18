package com.kh.todo.service;

import java.util.ArrayList;

import com.kh.todo.member.model.vo.Member;
import com.kh.todo.model.vo.Todo;

public interface TodoService {
	
	public int insertTodo(Todo todo);
	
	public int deleteTodo(int todoNo);
	
	public int updateTodo(Todo todo);
	
	public ArrayList<Todo> myTodoList(int memberNo);
}
