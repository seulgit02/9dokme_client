package com.example.server_9dokme.member.controller;

import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {


    @Autowired
    private KakaoService kakaoService;

    @GetMapping("/oauth")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 GET")
    public SuccessResponse<?> kakaoLogin(@RequestParam String code) {
        String accessToken = kakaoService.getKakaoAccessToken(code).toString();
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        //Service에서 로직구현 이메일 중복 체크 해서 만약 DB에 이메일이 있으면 저장 X 없으면 저장하는 로직으로 구현

        if(userInfo != null){
            session.setAttribute("email",userInfo.get("email"));
            session.setAttribute("accessToken",accessToken);
        }

        return SuccessResponse.success(String.valueOf(userInfo));
    }

    @GetMapping("/kakao/logout")
    public SuccessResponse<?> kakaoLogout(HttpSession session) {
        String accessToken = (String) session.getAttribute("accessToken");

        if(accessToken != null && !"".equals(accessToken)){
            try {
                kakaoService.kakaoDisconnect(accessToken);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            session.removeAttribute("accessToken");
            session.removeAttribute("email");
        }else{
            System.out.println("accessToken is null");
        }

        return SuccessResponse.success("로그아웃 성공");
    }





}
