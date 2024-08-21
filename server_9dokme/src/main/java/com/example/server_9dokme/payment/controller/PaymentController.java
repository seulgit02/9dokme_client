package com.example.server_9dokme.payment.controller;

import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.service.MemberService;
import com.example.server_9dokme.payment.dto.PaymentRequest;
import com.example.server_9dokme.payment.entity.PaymentType;
import com.example.server_9dokme.payment.service.PaymentService;
import com.example.server_9dokme.payment.dto.WebhookRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final MemberService memberService;

    public PaymentController(PaymentService paymentService, MemberService memberService) {
        this.paymentService = paymentService;
        this.memberService = memberService;
    }

    @Value("${iamport.merchant.code}")
    private String iamportMerchantCode;

    @Value("${iamport.pg.cid}")
    private String iamportPgCid;


    @GetMapping("/payments")
    public String paymentPage(Model model) {

        Member currentMember = memberService.getCurrentMember();

        PaymentRequest paymentRequest = new PaymentRequest(
                "kakaopay",
                "card",
                "order_no_" + new Date().getTime(),
                "9dokme 정기 결제",
                9900,
                currentMember.getSocialId(), // 사용자 이메일
                currentMember.getNickName(), // 사용자 이름
                "010-5511-0021",
                "서울특별시",
                "123-456",
                "bln_ZaQg06E4eOA",
                PaymentType.KAKAO
        );

        // 사용자 정보를 모델에 추가
        model.addAttribute("userEmail", currentMember.getSocialId());
        model.addAttribute("userName", currentMember.getNickName());

        model.addAttribute("paymentRequest", paymentRequest);

        return "payment";
    }

    @PostMapping("/payments/complete")
    @ResponseBody
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequest paymentRequest, @RequestParam(required = false) String imp_uid) {
        try {
            paymentService.verifyAndSavePayment(paymentRequest, imp_uid);
            return ResponseEntity.ok("Payment verified and saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Payment verification failed: " + e.getMessage());
        }
    }

    // 웹훅을 통해 결제 완료를 통보받는 경우를 위한 엔드포인트
    @PostMapping("/payments/webhook")
    @ResponseBody
    public ResponseEntity<String> paymentWebhook(@RequestBody WebhookRequest webhookRequest) {
        String impUid = webhookRequest.impUid();
        try {
            // impUid를 사용하여 결제 정보를 조회하고 처리
            return ResponseEntity.ok("Webhook received and processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Webhook processing failed: " + e.getMessage());
        }
    }
}
