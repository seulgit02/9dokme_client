package com.example.server_9dokme.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PaymentService {

    @Value("${iamport.api.key}")
    private String apiKey;

    @Value("${iamport.api.secret}")
    private String apiSecret;

    @Value("${iamport.merchant.code}")
    private String merchantCode;

    @Autowired
    private PaymentRepository paymentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1. 액세스 토큰 발급 받기
    private String getAccessToken() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 생성
        ObjectNode body = objectMapper.createObjectNode();
        body.put("imp_key", apiKey);
        body.put("imp_secret", apiSecret);

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://api.iamport.kr/users/getToken",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode root = objectMapper.readTree(response.getBody());
            String responseStatus = root.path("code").asText();
            if ("0".equals(responseStatus)) {
                String accessToken = root.path("response").path("access_token").asText();
                return accessToken;
            } else {
                String message = root.path("message").asText();
                throw new Exception("Failed to get access token: " + message);
            }
        } else {
            throw new Exception("Failed to get access token: HTTP error code " + response.getStatusCode());
        }
    }

    // 2. imp_uid로 결제 정보 조회
    public JsonNode getPaymentData(String impUid) throws Exception {
        String accessToken = getAccessToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.iamport.kr/payments/" + impUid,
                HttpMethod.GET,
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode root = objectMapper.readTree(response.getBody());
            String responseStatus = root.path("code").asText();
            if ("0".equals(responseStatus)) {
                JsonNode paymentData = root.path("response");
                return paymentData;
            } else {
                String message = root.path("message").asText();
                throw new Exception("Failed to get payment data: " + message);
            }
        } else {
            throw new Exception("Failed to get payment data: HTTP error code " + response.getStatusCode());
        }
    }

    // 3. 결제 금액 검증 및 결제 내역 저장
    public void verifyAndSavePayment(PaymentRequest paymentRequest, String impUid) throws Exception {
        JsonNode paymentData = getPaymentData(impUid);

        // 결제 금액 검증
        int amountFromRequest = paymentRequest.getAmount();
        int amountFromIamport = paymentData.path("amount").asInt();

        if (amountFromRequest != amountFromIamport) {
            throw new Exception("Payment amount mismatch");
        }

        // 결제 내역 저장
        Payment payment = new Payment();
        payment.setImpUid(impUid);
        payment.setMerchantUid(paymentRequest.getMerchantUid());
        payment.setAmount(amountFromIamport);
        payment.setBuyerEmail(paymentData.path("buyer_email").asText());
        payment.setBuyerName(paymentData.path("buyer_name").asText());
        payment.setBuyerTel(paymentData.path("buyer_tel").asText());
        payment.setStatus(paymentData.path("status").asText());

        paymentRepository.save(payment);
    }
}
