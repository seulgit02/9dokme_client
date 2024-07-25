package com.example.server_9dokme.rent.repository;

import com.example.server_9dokme.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
