package com.example.server_9dokme.member.repository;

import com.example.server_9dokme.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
