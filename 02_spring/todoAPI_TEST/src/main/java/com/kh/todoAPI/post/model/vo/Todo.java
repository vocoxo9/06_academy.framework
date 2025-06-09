package com.kh.todoAPI.post.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
	private int todoNo;
	private String content;
	private int status;
	private Date createdAt;
	private String userId;
}
