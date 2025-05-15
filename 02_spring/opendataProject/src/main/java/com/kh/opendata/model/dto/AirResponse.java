package com.kh.opendata.model.dto;

import com.kh.opendata.model.vo.AirVO;
import lombok.Data;

import java.util.List;

@Data
public class AirResponse {

    private List<AirVO> items;  // 조회 결과 목록
    private int numOfRows;      // 한 페이지 결과 수
    private int pageNo;         // 페이지 번호 (현재 페이지)
    private int totalCount;     // 전체 결과 수 (총 개수)
}
