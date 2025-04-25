package com.kh.apitest.exercise.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Exercise {

	private int exerciseId;
	private String name;
	private String met;
	private String category; 
}
