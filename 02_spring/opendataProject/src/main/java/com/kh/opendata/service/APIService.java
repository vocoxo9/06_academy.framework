package com.kh.opendata.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

@Service
public class APIService {

	private static final String SERVICE_KEY = "서비스키";
	
	/**
	 * 공공데이터 API를 사용하여 대기오염 정보 조회
	 * @param sidoName 지역명
	 * @return 대기오염 조회 결과
	 */
	public List<AirVO> getAirPollution(String sidoName) throws Exception{
		
		List<AirVO> list = new ArrayList<>();
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode(sidoName,"UTF-8");
		url += "&returnType=json";
		url += "&numOfRows=100";
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		BufferedReader buf = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream()));

		String line;
		String responseText = "";
		
		while((line = buf.readLine()) != null) {
//			System.out.println(line);
			responseText += line;
		}
		
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
//		System.out.println(bodyObj);
		
		JsonArray items = bodyObj.getAsJsonArray("items");
		
		for(int i=0; i<items.size(); i++) {
			
			try {

			JsonObject item = items.get(i).getAsJsonObject();
			
			System.out.println(item);
			
			AirVO air = new AirVO(
					item.get("stationName").getAsString(),
					item.get("dataTime").getAsString(),
					item.get("khaiValue").getAsString(),
					item.get("pm10Value").getAsString(),
					item.get("so2Value").getAsString(),
					item.get("coValue").getAsString(),
					item.get("no2Value").getAsString(),
					item.get("o3Value").getAsString());
			
			if(air != null) {
				list.add(air);				
			}
			
			} catch(UnsupportedOperationException e){
				e.printStackTrace();
			}
		}
		
		for(AirVO air : list) {
			System.out.println(air);
		}
		
		return list;
	}
}
