package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.HealthFunctionalFood;

public class HealthFunctionalFoodInfoRun{

	private static final String SERVICE_KEY = "서비스키";
	
	public static void main(String[] args) throws Exception {
		
		String url = "https://apis.data.go.kr/1471000/HtfsInfoService03/getHtfsItem01";
		
		url += "?ServiceKey=" + SERVICE_KEY;
		
		url += "&returnType=json";
		
//		System.out.println(url);
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();
		
		urlConn.setRequestMethod("GET");
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		
		String line;
		String responseText = "";
		
		while((line = buf.readLine()) != null) {
			responseText += line;
		}
		
		buf.close();
		urlConn.disconnect();
		
		
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		
		//System.out.println(responseObj);
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		
		// System.out.println(bodyObj);
		
		int totalCount = bodyObj.get("totalCount").getAsInt();
		
		JsonArray items = bodyObj.getAsJsonArray("items");
		
		ArrayList<HealthFunctionalFood> list = new ArrayList<>();
		
		for(int i=0; i<items.size(); i++) {
			JsonObject item = items.get(i).getAsJsonObject();
			
			HealthFunctionalFood health = new HealthFunctionalFood();
			
			health.setEntrps( item.get("ENTRPS").getAsString() );
			health.setProduct( item.get("PRDUCT").getAsString() );
			health.setSttemnt_no( item.get("STTEMNT_NO").getAsString() );
			health.setRegist_dt( item.get("REGIST_DT").getAsString() );
			health.setDistb_pd( item.get("DISTB_PD").getAsString() );
			health.setSry_use( item.get("SRY_USE").getAsString() );
			health.setPrsry_pd( item.get("PRSRY_PD").getAsString() );
			
			list.add(health);
			
		}
		
		for(HealthFunctionalFood health : list) {
			System.out.println();
		}
	}
}
