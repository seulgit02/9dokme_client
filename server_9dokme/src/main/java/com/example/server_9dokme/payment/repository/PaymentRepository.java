package com.example.server_9dokme.payment.repository;

import com.example.server_9dokme.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByImpUid(String impUid);
    @Query("SELECT p FROM Payment p WHERE p.status = :status")
    List<Payment> findAllByStatus(String status);
}

