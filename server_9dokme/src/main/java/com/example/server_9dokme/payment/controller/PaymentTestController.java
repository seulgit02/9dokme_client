package com.example.server_9dokme.payment.controller;

import com.example.server_9dokme.payment.entity.Payment;
import com.example.server_9dokme.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments/test")
public class PaymentTestController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/set-status")
    public ResponseEntity<String> setPaymentStatus(@RequestParam String impUid, @RequestParam String status) {
        Payment payment = paymentRepository.findByImpUid(impUid);
        if (payment != null) {
            payment.setStatus(status);
            paymentRepository.save(payment);
            return ResponseEntity.ok("Payment status updated to: " + status);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found for imp_uid: " + impUid);
        }
    }
}

