package com.kh.todo.post.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
	private int number;
	private String userId;
	private String content;
	private int status;
	private Date createDate;
}
