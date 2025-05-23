package com.kh.opendataPractice.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendataPractice.model.vo.EmergencyShelter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyShelterService {

    @Value("${apiServiceKey}")
    private String serviceKey;

    public List<EmergencyShelter> getEmergencyShelterList() throws Exception {
        List<EmergencyShelter> list = new ArrayList<EmergencyShelter>();

        String url = "https://www.safetydata.go.kr/V2/api/DSSP-IF-10944"
                + "?serviceKey=" + serviceKey
                + "&pageNo=1"
                + "&numOfRows=30"
                + "&returnType=json";

        URL requestUrl = new URL(url);

        System.out.println(url);

        HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();

        BufferedReader buf;
        if(urlConnection.getResponseCode() == 200){
            buf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        } else {
            buf = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
        }

        String responseData = "";
        String line = "";

        while ((line = buf.readLine()) != null) {
            responseData += line;
        }

        System.out.println("responseData ::: " + responseData);

        buf.close();
        urlConnection.disconnect();

        JsonObject responseObject = new JsonParser().parseString(responseData).getAsJsonObject();
        JsonArray bodyArray = responseObject.getAsJsonArray("body");

        for(int i=0; i<bodyArray.size(); i++){
            JsonObject item = bodyArray.get(i).getAsJsonObject();

            EmergencyShelter emergencyShelter = new EmergencyShelter();

            emergencyShelter.setArcd(item.has("ARCD") ? item.get("ARCD").getAsString() : "---");
            emergencyShelter.setShntPlaceNm(item.has("SHNT_PLACE_NM") ? item.get("SHNT_PLACE_NM").getAsString() : "---");
            emergencyShelter.setPsblNmpr(item.has("PSBL_NMPR") ? item.get("PSBL_NMPR").getAsString() : "---");
            emergencyShelter.setRnDtlAdres(item.has("RN_DTL_ADRES") && !item.get("RN_DTL_ADRES").isJsonNull()
                    ? item.get("RN_DTL_ADRES").getAsString() : "---");
            emergencyShelter.setShntPlaceDtlPotision(item.has("SHNT_PLACE_DTL_POSITION") ? item.get("SHNT_PLACE_DTL_POSITION").getAsString() : "---");
            emergencyShelter.setErthqkShuntAt(item.has("ERTHQK_SHUNT_AT") ? item.get("ERTHQK_SHUNT_AT").getAsString() : "---");

            list.add(emergencyShelter);
        }

        return list;
    }
}
