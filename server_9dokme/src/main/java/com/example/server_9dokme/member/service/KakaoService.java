package com.example.server_9dokme.member.service;

import com.example.server_9dokme.member.dto.response.KakaoTokenResponseDto;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class KakaoService {

    @Value("${kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.redirect-url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API = "https://kapi.kakao.com";

    public String getAccessToken(String code) {
        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAKAO_AUTH_URI).post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", KAKAO_CLIENT_ID)
                        .queryParam("redirect_uri", KAKAO_REDIRECT_URL)
                        .queryParam("code", code)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        return kakaoTokenResponseDto.getAccessToken();
    }

    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = KAKAO_API + "/v2/user/me";
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = conn.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder responseSb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseSb.append(line);
            }

            String result = responseSb.toString();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.get("nickname").getAsString();
            String email = kakaoAccount.get("email").getAsString();

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
