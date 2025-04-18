package com.kh.thymeleaf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

	private String name;
	private int age;
	private String birth;
	private String[] hobby;
	
	public PersonDTO(String name, int age, String birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	
}
