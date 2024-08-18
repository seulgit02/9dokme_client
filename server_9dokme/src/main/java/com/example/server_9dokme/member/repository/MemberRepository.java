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

    List<Member> findAll();

    Page<Member> findAll(Pageable pageable);

    //구독 결제일이 지난 member 조회
    @Query("SELECT m FROM Member m WHERE m.subscribe.expiredAt <= :currentDate")
    List<Member> findExpiredSubscriptions(@Param("currentDate") LocalDateTime currentDate);
}
