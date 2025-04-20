package com.kh.todo.todoList.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.todo.todoList.model.vo.Todo;

@Mapper
public interface TodoDAO {
	
	int insertTodo(Todo todo);
	
	int updateTodo(Todo todo);
	
	int deleteTodo(int todoNo);
	
	ArrayList<Todo> myTodoList(int memberNo);
}
