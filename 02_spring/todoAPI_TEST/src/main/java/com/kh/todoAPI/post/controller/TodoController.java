package com.kh.todoAPI.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.todoAPI.post.model.dto.TodoDTO;
import com.kh.todoAPI.post.model.vo.Todo;
import com.kh.todoAPI.post.service.TodoService;
import com.kh.todoAPI.user.model.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("todo")
public class TodoController {

	private final TodoService todoService;
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	/**
	 * 할일 목록 조회
	 * [GET] /todo
	 * @return List<Todo> 할일 목록(전체)
	 */
	@GetMapping("")
	public List<Todo> getTodoList(HttpSession session){
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");

		List<Todo> list = todoService.getTodoList(loginUser.getUserId());		// TODO
		
		System.out.println(loginUser);
		System.out.println(list);
		
		return list;
	}
	
	/**
	 * 할일 추가
	 * [POST[ /todo
	 * @param { content : 할일 내용 }
	 * @return Todo 추가된 할일 정보 
	 */
	@PostMapping("")
	public ResponseEntity<Object> addTodo(@RequestBody TodoDTO todoDTO, HttpSession session) {
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		todoDTO.setUserId(loginUser.getUserId());
		
		Todo todo = todoService.insertTodo(todoDTO);
		if( todo != null) {
			// 추가 성공 시 추가된 할일 정보(Todo)
			return ResponseEntity.status(HttpStatus.OK).body(todo);
		} else {
			// 추가 실패 시 메시지 전달(String)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("할일 추가에 실패했습니다.");
		}
	}
}
