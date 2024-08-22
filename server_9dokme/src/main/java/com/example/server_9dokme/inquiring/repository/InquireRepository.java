package com.example.server_9dokme.inquiring.repository;


import com.example.server_9dokme.inquiring.entity.Inquire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquireRepository extends JpaRepository<Inquire, Long> {
    Page<Inquire> findAll(Pageable pageable);
}
