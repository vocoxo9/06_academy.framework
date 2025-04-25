package com.kh.apitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.apitest.exercise.model.dao.ExerciseDAO;
import com.kh.apitest.exercise.model.vo.Exercise;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
public class ApiTest {
	
	
	private final ExerciseDAO exerciseDAO;
	private final SqlSession sqlSession;
	
	@Test
	public void test() throws IOException {
        // 1. 인코딩되지 않은 인증키를 준비
        String originalKey = "6ts24t1asdIdIcsFCu+LKytanC50N5KMlMorvtTVVJre1sD6hwAPbkYt1BXjteTdaooJb0vB0ocyXk1BicepqA==";

        // 2. 인증키를 UTF-8 방식으로 URL 인코딩
        String encodedKey = URLEncoder.encode(originalKey, "UTF-8");    	
    	
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15068730/v1/uddi:12fe14fb-c8ca-47b1-9e53-97a93cb214ed"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + encodedKey); /*Service Key*/
        
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        String jsonData = sb.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonData);
        JsonNode dataArray = root.path("data");

        if (dataArray.isArray()) {
            for (JsonNode node : dataArray) {
                Exercise ex = new Exercise();
                ex.setName(node.path("운동명").asText());
                ex.setMet(node.path("MET 계수").asText());

                // DB 저장
                exerciseDAO.insertExercise(sqlSession, ex);
            }
        }

        System.out.println("데이터 저장 완료");
	}

}
