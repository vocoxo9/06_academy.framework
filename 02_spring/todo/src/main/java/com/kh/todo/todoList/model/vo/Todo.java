package com.kh.todo.todoList.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
	
	private int todoNo;
	private String content;
	private String completion;
	private String registDate;
	private String deadline;
	private String dDay;
	private int memberNo;
}
