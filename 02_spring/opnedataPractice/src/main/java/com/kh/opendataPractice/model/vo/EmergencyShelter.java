package com.kh.opendataPractice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyShelter {

    private String arcd;                        // 지역코드
    private String shntPlaceNm;               // 대피장소명
    private String psblNmpr;                   // 수용인원
    private String rnDtlAdres;                // 도로명 상세 주소
    private String shntPlaceDtlPotision;     // 대피 장소 상세 위치
    private String erthqkShuntAt;             // 내진설계 적용 여부
}
