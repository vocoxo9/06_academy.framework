package com.kh.opendata.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.opendata.model.dto.AirResponse;
import com.kh.opendata.model.vo.AirVO;
import com.kh.opendata.service.APIService;

import lombok.RequiredArgsConstructor;

// @RestController => @Controller + @ResponseBody
@Controller
@RequiredArgsConstructor
public class APIController {

	private final APIService apiService;

	// [GET] /airPollution?location=선택된지역정보
	// @return List<AirVO> 대기오염 정보
	@GetMapping("airPollution")
	@ResponseBody
	public List<AirVO> getAirPollution(@RequestParam(value="location", defaultValue="서울") String sidoName) throws IOException {
		System.out.println("지역 정보 :: " + sidoName);
		return apiService.getAirPollution(sidoName);
	}

	// [GET] /airPollution/v2?location=지역정보&currPage=페이지번호
	// @return AirResponse { 대기오염 정보, 한 페이지 결과 수, 페이지 번호, 전체 결과 수 }
	@GetMapping("airPollution/v2")
	@ResponseBody
	public AirResponse getAirPollutionV2(@RequestParam(value="location", defaultValue="서울") String sidoName
			, @RequestParam(value="currPage", defaultValue="1") int pageNo) throws IOException {
		return apiService.getAirPollutionV2(sidoName, pageNo);
	}
}

