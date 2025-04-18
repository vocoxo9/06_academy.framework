package com.kh.todo.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.todo.model.vo.Todo;

@Mapper
public interface TodoDAO {
	
	int insertTodo(Todo todo);
	
	int updateTodo(Todo todo);
	
	int deleteTodo(int todoNo);
	
	ArrayList<Todo> myTodoList(int memberNo);
}
