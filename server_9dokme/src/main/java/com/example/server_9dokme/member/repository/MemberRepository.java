package com.example.server_9dokme.member.repository;

import com.example.server_9dokme.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findBySocialId(String email);
    Member findByMemberId(int id);
    Member findByCustomerKey(String customerKey);
    List<Member> findAll();
    Page<Member> findAll(Pageable pageable);
}
