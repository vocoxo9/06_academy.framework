package com.kh.todo.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Todo {
	
	private int todoNo;
	private String content;
	private String completion;
	private Date registDate;
	private Date deadline;
	private Date dDay;
	private int memberNo;
}
