package com.kh.opendata.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.dto.AirResponse;
import com.kh.opendata.model.vo.AirVO;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class APIService {

	@Value("${opendata.apiKey}")
	private String serviceKey;

	/**
	 * 공공 데이터 API를 사용하여 대기오염 정보 조회
	 * @param sidoName 지역명
	 * @return 대기오염 조회 결과
	 * @throws IOException
	 */
	public List<AirVO> getAirPollution(String sidoName) throws IOException {
		// * 반환 데이터
		List<AirVO> list = new ArrayList<>();

		// * 요청 URL + 요청 파라미터
		/*
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
				   + "?serviceKey=" + serviceKey
				   + "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8")
				   + "&returnType=json";
		*/
		String url = makeAirPollutionURL(sidoName);

		// * 요청 전송
		/*
		URL requestUrl = new URL(url);
		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();

		BufferedReader buf;
		// * 통신 성공 여부 체크
		if (urlConn.getResponseCode() == HttpServletResponse.SC_OK) {
			// * 응답 데이터 읽어오기
			buf = new BufferedReader(
									new InputStreamReader(urlConn.getInputStream()));
			StringBuilder responseData = new StringBuilder();
			String line = "";
			while((line = buf.readLine()) != null) {
				responseData.append(line);
			}

			// * 추출 및 가공 처리 --> JsonObject, JsonParser, ...
			JsonObject responseObj = JsonParser.parseString(responseData.toString()).getAsJsonObject();

			JsonArray items = responseObj.getAsJsonObject("response")
										 .getAsJsonObject("body")
										 .getAsJsonArray("items");

			for(int i=0; i<items.size(); i++) {
				JsonObject item = items.get(i).getAsJsonObject();

				AirVO air = new AirVO();
				// ----------------
				air.setStationName( item.get("stationName").getAsString() );
				air.setDataTime( item.get("dataTime").getAsString() );
				air.setKhaiValue( item.get("khaiValue").getAsString() );

				air.setPm10Value( item.get("pm10Value").getAsString() );
				air.setCoValue( item.get("coValue").getAsString() );
				air.setNo2Value( item.get("no2Value").getAsString() );
				air.setO3Value( item.get("o3Value").getAsString() );
				air.setSo2Value( item.get("so2Value").getAsString() );
				// ----------------
				air.setStationName( getValue(item, "stationName") );
				air.setDataTime( getValue(item, "dataTime") );
				air.setKhaiValue( getValue(item, "khaiValue") );

				air.setPm10Value( getValue(item, "pm10Value") );
				air.setCoValue( getValue(item, "coValue") );
				air.setNo2Value( getValue(item, "no2Value") );
				air.setO3Value( getValue(item, "o3Value") );
				air.setSo2Value( getValue(item, "so2Value") );

				list.add(air);
			}
		} else {
			// * 통신 실패 시 (오류)
			buf = new BufferedReader(new InputStreamReader(urlConn.getErrorStream()));

			StringBuilder responseData = new StringBuilder();
			String line = "";
			while((line = buf.readLine()) != null) {
				responseData.append(line);
			}

			System.out.println(responseData);
		}

		// * 자원 반납
		buf.close();
		urlConn.disconnect();
		*/
		String responseData = getAirPollutionResponse(url);
		// 응답데이터가 null인 경우 메소드 종료
		if (responseData == null) {
			return list;
		}

		// * 추출 및 가공 처리 --> JsonObject, JsonParser, ...
		JsonObject responseObj = JsonParser.parseString(responseData).getAsJsonObject();

		JsonArray items = responseObj.getAsJsonObject("response")
				.getAsJsonObject("body")
				.getAsJsonArray("items");
		/*
		for(int i=0; i<items.size(); i++) {
			JsonObject item = items.get(i).getAsJsonObject();

			AirVO air = new AirVO();
			// ----------------
			air.setStationName( item.get("stationName").getAsString() );
			air.setDataTime( item.get("dataTime").getAsString() );
			air.setKhaiValue( item.get("khaiValue").getAsString() );

			air.setPm10Value( item.get("pm10Value").getAsString() );
			air.setCoValue( item.get("coValue").getAsString() );
			air.setNo2Value( item.get("no2Value").getAsString() );
			air.setO3Value( item.get("o3Value").getAsString() );
			air.setSo2Value( item.get("so2Value").getAsString() );
			//---
			air.setStationName( getValue(item, "stationName") );
			air.setDataTime( getValue(item, "dataTime") );
			air.setKhaiValue( getValue(item, "khaiValue") );

			air.setPm10Value( getValue(item, "pm10Value") );
			air.setCoValue( getValue(item, "coValue") );
			air.setNo2Value( getValue(item, "no2Value") );
			air.setO3Value( getValue(item, "o3Value") );
			air.setSo2Value( getValue(item, "so2Value") );

			list.add(air);
		}
		*/
		list = convertJsonArrayToList(items);
		return list;
	}

	/**
	 * 공공 데이터 API를 사용하여 대기오염 정보 조회 + 페이지 정보
	 * @param sidoName 지역정보
	 * @param pageNo   페이지 번호
	 * @return 조회된 결과(목록, 페이지 관련 정보)
	 * @throws IOException
	 */
	public AirResponse getAirPollutionV2(String sidoName, int pageNo) throws IOException {

		// * 요청 URL
		String url = makeAirPollutionURL(sidoName, pageNo);

		// * 응답 데이터
		String responseData = getAirPollutionResponse(url);

		// * 추출 및 가공 처리
		JsonObject bodyObj = JsonParser.parseString(responseData).getAsJsonObject()
				.getAsJsonObject("response")
				.getAsJsonObject("body");
		// bodyObj ==> { items: [..], totalCount: , numOfRows: , pageNo: }
		AirResponse airResponse = new AirResponse();
		airResponse.setTotalCount( bodyObj.get("totalCount").getAsInt() );
		airResponse.setNumOfRows( bodyObj.get("numOfRows").getAsInt() );
		airResponse.setPageNo( bodyObj.get("pageNo").getAsInt() );

		JsonArray items = bodyObj.getAsJsonArray("items");
		List<AirVO> list = convertJsonArrayToList(items);

		airResponse.setItems(list);

		return airResponse;
	}

	/**
	 * 시도별 실시간 대기오염 요청 URL 생성
	 * @param sidoName 지역 정보
	 * @param pageNo   페이지 번호
	 * @return 요청 URL
	 * @throws UnsupportedEncodingException
	 */
	private String makeAirPollutionURL(String sidoName, int pageNo) throws UnsupportedEncodingException {
		return "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
				+ "?serviceKey=" + serviceKey
				+ "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8")
				+ "&pageNo=" + pageNo
				+ "&returnType=json";
	}
	private String makeAirPollutionURL(String sidoName) throws UnsupportedEncodingException {
		return makeAirPollutionURL(sidoName, 1);
	}

	/**
	 * 요청 후 응답 데이터 반환
	 * @param url		요청 주소(url)
	 * @return	성공 시 응답 데이터 문자열, 실패 시 null
	 * @throws IOException
	 */
	private String getAirPollutionResponse(String url) throws IOException {


		// * 요청 전송 URL, HttpURLConnection
		URL requestUrl = new URL(url);
		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();

		// * 응답 결과 읽어오기
		BufferedReader buf;
		if (urlConn.getResponseCode() == HttpServletResponse.SC_OK) {
			// * 요청 성공 시
			buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		} else {
			// * 요청 실패 시
			buf = new BufferedReader(new InputStreamReader(urlConn.getErrorStream()));
		}

		// * 응답 결과를 변수에 저장
		StringBuilder responseData = new StringBuilder();
		String line = "";
		while ((line = buf.readLine()) != null) {
			responseData.append(line);
		}

		// * 자원 반납
		buf.close();
		urlConn.disconnect();

		if (urlConn.getResponseCode() == HttpServletResponse.SC_OK) {
			return responseData.toString();
		} else {
//			System.out.println(responseData);		// 오류 내용 출력. log.error(..)
			return null;
		}
	}

	/**
	 * 객체 내 특정 값에 대하여 안전하게 추출
	 * @param obj Json 객체
	 * @param key 추출하고자 하는 데이터의 키값
	 * @return 키값에 해당하는 값
	 */
	private String getValue(JsonObject obj, String key) {
		return obj.has(key) && obj.get(key) != null && !obj.get(key).isJsonNull() ?
				obj.get(key).getAsString() : "";
	}

	/**
	 * JsonArray 타입을 List<AirVO> 타입으로 변경
	 * @param items JsonArray 타입의 데이터
	 * @return list List<AirVO> 타입의 변경된 데이터
	 */
	private List<AirVO> convertJsonArrayToList(JsonArray items) {
		List<AirVO> list = new ArrayList<>();

		for(int i=0; i<items.size(); i++) {
			JsonObject item = items.get(i).getAsJsonObject();

			AirVO air = new AirVO();
			air.setStationName( getValue(item, "stationName") );
			air.setDataTime( getValue(item, "dataTime") );
			air.setKhaiValue( getValue(item, "khaiValue") );

			air.setPm10Value( getValue(item, "pm10Value") );
			air.setCoValue( getValue(item, "coValue") );
			air.setNo2Value( getValue(item, "no2Value") );
			air.setO3Value( getValue(item, "o3Value") );
			air.setSo2Value( getValue(item, "so2Value") );

			list.add(air);
		}

		return list;
	}

}

