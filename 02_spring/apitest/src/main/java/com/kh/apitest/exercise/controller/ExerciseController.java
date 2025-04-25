package com.kh.apitest.exercise.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.apitest.exercise.service.ExerciseService;

@RestController
@RequestMapping("/api")
public class ExerciseController {
	
	@Autowired
	private ExerciseService exerciseService;
	
	// 운동데이터 가져오기
	@GetMapping("/fetch-and-save")
	public String fetchAndSave() throws IOException {
		exerciseService.fetchAndSaveData();
		return "운동데이터 저장 완료";
	}
}
