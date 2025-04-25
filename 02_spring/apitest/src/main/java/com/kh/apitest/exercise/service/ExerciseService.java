package com.kh.apitest.exercise.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.apitest.exercise.model.dao.ExerciseDAO;
import com.kh.apitest.exercise.model.vo.Exercise;


@Service
public class ExerciseService {

    @Autowired
    private ExerciseDAO exerciseDAO;

    @Autowired
    private SqlSession sqlSession;

    public void fetchAndSaveData() throws IOException {
        // 1. 인코딩되지 않은 인증키를 준비
        String originalKey = "6ts24t1asdIdIcsFCu+LKytanC50N5KMlMorvtTVVJre1sD6hwAPbkYt1BXjteTdaooJb0vB0ocyXk1BicepqA==";

        // 2. 인증키를 UTF-8 방식으로 URL 인코딩
        String encodedKey = URLEncoder.encode(originalKey, "UTF-8");    	
    	
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15068730/v1/uddi:12fe14fb-c8ca-47b1-9e53-97a93cb214ed"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + encodedKey); /*Service Key*/
        urlBuilder.append("&perPage=259");
        
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
            	Exercise exercise = new Exercise();
            	String metValue = node.path("MET 계수").asText();
           
            	String category = null;
            	String exerciseNameWithCategory = node.path("운동명").asText();
            	String name = exerciseNameWithCategory;
            	
            	// 하이픈 기준 카테고리 나누기
            	if(exerciseNameWithCategory.contains("-")) {
            		String[] parts = exerciseNameWithCategory.split("-");
            		name = parts[0].trim();
            		category = parts[1].trim();
//            		if(category.contains("(")) {
//            			category = category.substring(0, category.indexOf("(")).trim();
//            		}
            	}
            	
            	// met 계수 기준 카테고리 나누기
            	if(category == null || category.isEmpty()) {
            		double met = Double.parseDouble(metValue);
            		if(met < 3) {
            			category = "저강도";
            		} else if ( 3 <= met && met < 6) {
            			category = "중강도";
            		} else {
            			category = "고강도";
            		}
            	}
            	
                exercise.setName(name);
                exercise.setCategory(category);
                exercise.setMet(metValue);

                // DB 저장
                exerciseDAO.insertExercise(sqlSession, exercise);
            }
        }

        System.out.println("데이터 저장 완료");
    }
    
    /*
    public void fetchAndSaveData() {
        try {
            // 1. 인코딩되지 않은 인증키를 준비
            String originalKey = "6ts24t1asdIdIcsFCu+LKytanC50N5KMlMorvtTVVJre1sD6hwAPbkYt1BXjteTdaooJb0vB0ocyXk1BicepqA==";

            // 2. 인증키를 UTF-8 방식으로 URL 인코딩
            String encodedKey = URLEncoder.encode(originalKey, "UTF-8");

            // 3. API 호출할 전체 URL 구성
            String url = "https://api.odcloud.kr/api/15068730/v1/uddi:12fe14fb-c8ca-47b1-9e53-97a93cb214ed"
                       + "?serviceKey=" + encodedKey;

            // 4. RestTemplate을 이용해 GET 요청 (데이터 가져오기)
            RestTemplate restTemplate = new RestTemplate();
            String jsonData = restTemplate.getForObject(url, String.class);

            // 5. 가져온 JSON 문자열 출력 (추후 DB 저장 로직으로 확장 가능)
            System.out.println("받아온 데이터: \n" + jsonData);

            // TODO: JSON 파싱 후 Oracle DB에 저장하는 로직 추가 예정
            // Jackson으로 파싱
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

            System.out.println("✅ 데이터 저장 완료!");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리 (인코딩 오류)
            e.printStackTrace();
        } catch (Exception e) {
            // 그 외 모든 예외 처리
            e.printStackTrace();
        }
    }
    */
}
