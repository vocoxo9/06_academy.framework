package com.kh.todo.post.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDTO {
	private String content;
	private String userId;
}
