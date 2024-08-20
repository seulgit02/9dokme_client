package com.example.server_9dokme.member.controller;

import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.inquiring.dto.response.InquireDto;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import com.example.server_9dokme.member.dto.response.MemberDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.member.service.KakaoService;
import com.example.server_9dokme.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/oauth")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 GET")
    public BaseResponse kakaoLogin(@RequestParam String code, HttpSession session) {
        String accessToken = kakaoService.getKakaoAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        //Service에서 로직구현 이메일 중복 체크 해서 만약 DB에 이메일이 있으면 저장 X 없으면 저장하는 로직으로 구현

        if(accessToken ==null){
            return ErrorResponse.of("로그인 실패", HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("email",userInfo.get("email"));
        session.setAttribute("accessToken",accessToken);
        Member member = memberRepository.findBySocialId((String)userInfo.get("email"));
        session.setAttribute("memberId",member.getMemberId());
        session.setMaxInactiveInterval(60 * 60);

        kakaoService.registerMember(String.valueOf(userInfo.get("email")),String.valueOf(userInfo.get("nickname")));


        return SuccessResponse.success(String.valueOf(userInfo));
    }

    @GetMapping("/logout")
    @Operation(summary = "카카오 로그아웃", description = "카카오 로그아웃")
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
    @Operation(summary = "메인 페이지", description = "메인페이지, 페이지 네이션 적용")
    public SuccessResponse<MainPageDto> mainPage(HttpSession session ,
                                                 @RequestParam(required = false, defaultValue = "", value = "category")  String category,
                                                 @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){

        String socialId = (String) session.getAttribute("email");
        String accessToken = (String) session.getAttribute("accessToken");



        MainPageDto mainPageDto = memberService.getMainPage(category,pageNo);

        return SuccessResponse.success("메인 페이지",mainPageDto);
    }

    @GetMapping("/admin/memberlist/{pageNo}")
    public Page<MemberDto> getMemberList(@PathVariable int pageNo){
        return memberService.getMemberList(pageNo);
    }

    @DeleteMapping("/admin/member/delete/{memberId}")
    public ResponseEntity<Void> deleteInquire(@PathVariable Long memberId) {
        try {
            memberService.deleteMember(memberId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            log.error("Error deleting member with ID " + memberId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
