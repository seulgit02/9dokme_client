package com.example.server_9dokme.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Value("${iamport.merchant.code}")
    private String iamportMerchantCode;

    @Value("${iamport.pg.cid}")
    private String iamportPgCid;

    @PostMapping("/payments/complete")
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequest paymentRequest, @RequestParam String imp_uid) {
        try {
            paymentService.verifyAndSavePayment(paymentRequest, imp_uid);
            return ResponseEntity.ok("Payment verified and saved successfully");
        } catch (Exception e) {
            // 로그를 남기거나 추가적인 예외 처리를 할 수 있습니다.
            return ResponseEntity.status(400).body("Payment verification failed: " + e.getMessage());
        }
    }

    // 웹훅을 통해 결제 완료를 통보받는 경우를 위한 엔드포인트 (선택 사항)
    @PostMapping("/payments/webhook")
    public ResponseEntity<String> paymentWebhook(@RequestBody WebhookRequest webhookRequest) {
        String impUid = webhookRequest.impUid();
        try {
            // impUid를 사용하여 결제 정보를 조회하고 처리
            // 필요한 경우 추가 로직 구현
            return ResponseEntity.ok("Webhook received and processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Webhook processing failed: " + e.getMessage());
        }
    }
}
