package com.kh.todo.todoList.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.todo.todoList.model.vo.Todo;
import com.kh.todo.todoList.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping("/regist")
	public String insertTodo(Todo todo, Model model, @RequestParam(required=true) int memberNo) {
		int result = todoService.insertTodo(todo);
		
		if (result > 0) {
			ArrayList<Todo> todoList = todoService.myTodoList(memberNo);
			model.addAttribute("todoList", todoList);
			return "todo";
		} else {
			model.addAttribute("error", "TODO LIST 등록에 실패하였습니다.");
			return "common/error";			
		}
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateTodo(Todo todo) {
		System.out.println(todo);
		
		int result = todoService.updateTodo(todo);
		
		if(result > 0) {
			return "success";
		} else {
			return "fail";			
		}
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public String deleteTodo(@RequestParam(required=true) int todoNo) {
		
		int result = todoService.deleteTodo(todoNo);
		
		if(result > 0) {
			return "success";
		} else {
			return "fail";			
		}
	}
}
