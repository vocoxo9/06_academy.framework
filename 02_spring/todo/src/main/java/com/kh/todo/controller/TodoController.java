package com.kh.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.todo.service.TodoService;

@Controller
public class TodoController {

	private final TodoService todoService;
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

}
