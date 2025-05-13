package com.kh.opendata.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.opendata.model.vo.AirVO;
import com.kh.opendata.service.APIService;

@RestController
public class APIController {

	private final APIService apiService;
	public APIController(APIService apiService) {
		this.apiService = apiService;
	}
	
	// [GET] /airPollution?location=선택된지역정보
	// @return List<AirVO> 대기오염 정보
	@GetMapping("airPollution")
	public List<AirVO> getAirPollution(@RequestParam(value="location", defaultValue="서울") String sidoName) throws Exception{
//		System.out.println(sidoName);
		
		List<AirVO> list = apiService.getAirPollution(sidoName);
		
		return list;
	}
}
