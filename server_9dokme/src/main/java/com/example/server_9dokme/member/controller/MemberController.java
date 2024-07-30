package com.example.server_9dokme.member.controller;

import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.service.KakaoService;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
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

        return SuccessResponse.success(String.valueOf(userInfo));
    }
}
