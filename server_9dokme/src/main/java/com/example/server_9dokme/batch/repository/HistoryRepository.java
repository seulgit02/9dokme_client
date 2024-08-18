package com.example.server_9dokme.batch.repository;

import com.example.server_9dokme.batch.entity.SubscriptionUpdateHistory;
import com.example.server_9dokme.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<SubscriptionUpdateHistory, Long>{
//    List<SubscriptionUpdateHistory> findByMemberMemberId(Long memberId);
}
