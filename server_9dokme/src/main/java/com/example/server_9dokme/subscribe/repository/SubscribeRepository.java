package com.example.server_9dokme.subscribe.repository;

import com.example.server_9dokme.subscribe.entity.PaymentStatus;
import com.example.server_9dokme.subscribe.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    List<Subscribe> findAllByExpiredDateBeforeAndPaymentStatus(LocalDateTime now, PaymentStatus paymentStatus);
}
