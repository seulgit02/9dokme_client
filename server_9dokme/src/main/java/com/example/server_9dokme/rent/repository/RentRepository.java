package com.example.server_9dokme.rent.repository;

import com.example.server_9dokme.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Boolean existsByBookIdAndMemberId(Long bookId, Long memberId);

    Rent findByBookId(Long bookId);

    Rent findByBookIdAndMemberId(Long bookId, Long userId);
}
