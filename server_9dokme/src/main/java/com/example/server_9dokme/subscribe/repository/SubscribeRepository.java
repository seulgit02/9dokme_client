package com.example.server_9dokme.subscribe.repository;

import com.example.server_9dokme.subscribe.entity.PaymentStatus;
import com.example.server_9dokme.subscribe.entity.Subscribe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Query("SELECT s FROM Subscribe s WHERE s.expiredAt <= :date AND s.paymentStatus = :status")
    Page<Subscribe> findExpiredSubscriptions(@Param("date") LocalDate date, @Param("status") PaymentStatus status, Pageable pageable);

}
