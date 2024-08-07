package com.example.server_9dokme.member.controller;

import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import com.example.server_9dokme.member.service.KakaoService;
import com.example.server_9dokme.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class MemberController {


    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/oauth")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 GET")
    public BaseResponse kakaoLogin(@RequestParam String code, HttpSession session) {
        String accessToken = kakaoService.getKakaoAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        //Service에서 로직구현 이메일 중복 체크 해서 만약 DB에 이메일이 있으면 저장 X 없으면 저장하는 로직으로 구현

        if(accessToken !=null){
            return ErrorResponse.of("로그인 실패");
        }

        session.setAttribute("email",userInfo.get("email"));
        session.setAttribute("accessToken",accessToken);
        session.setMaxInactiveInterval(60 * 60);

        kakaoService.registerMember(String.valueOf(userInfo.get("email")),String.valueOf(userInfo.get("nickname")));


        return SuccessResponse.success(String.valueOf(userInfo));
    }

    @GetMapping("/logout")
    public SuccessResponse<?> kakaoLogout(HttpSession session) {
        String accessToken = (String)session.getAttribute("accessToken");

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


    @GetMapping("/mainPage")
    public SuccessResponse<MainPageDto> mainPage(HttpSession session ,@RequestParam  String category){

        String socialId = (String) session.getAttribute("email");

        MainPageDto mainPageDto = memberService.getMainPage(socialId,category);

        return SuccessResponse.success("로그인 성공",mainPageDto);
    }



}
