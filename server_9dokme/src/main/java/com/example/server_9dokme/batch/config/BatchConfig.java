package com.example.server_9dokme.batch.config;

import com.example.server_9dokme.batch.entity.SubscriptionUpdateHistory;
import com.example.server_9dokme.batch.repository.HistoryRepository;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.lang.Math.log;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public BatchConfig(MemberRepository memberRepository, HistoryRepository historyRepository, JobLauncher jobLauncher, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.memberRepository = memberRepository;
        this.historyRepository = historyRepository;
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    //테스트용으로 6초마다 갱신되도록 설정
    @Scheduled(fixedRate = 6000) // 60000ms = 1분
    public void performJob() throws Exception {
        jobLauncher.run(updateSubscriptionJob(), new JobParameters());
    }

    @Bean
    public Job updateSubscriptionJob() {
        return new JobBuilder("updateSubscriptionJob", jobRepository)
                .start(updateSubscriptionStep())
                .build();
    }

    @Bean
    public Step updateSubscriptionStep() {
        return new StepBuilder("updateSubscriptionStep", jobRepository)
                .<Member, Member>chunk(10, transactionManager)
                .reader(memberItemReader())
                .processor(memberItemProcessor())
                .writer(memberItemWriter())
                .build();
    }

    //batch 처리 대상을 Read, 테스트용으로 findAll로 설정
//    @Bean
//    public ItemReader<Member> memberItemReader() {
//        return new RepositoryItemReaderBuilder<Member>()
//                .repository(memberRepository)
//                .methodName("findAll")
////                .methodName("findExpiredSubscriptions")
//                .arguments(LocalDateTime.now())
//                .pageSize(10)
//                .sorts(Collections.singletonMap("memberId", Sort.Direction.ASC))
//                .name("memberItemReader")
//                .build();
//    }

    @Bean
    public ItemReader<Member> memberItemReader() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("memberId")));

        return new RepositoryItemReaderBuilder<Member>()
                .repository(memberRepository)
                .methodName("findAll") // "findAll(Pageable pageable)" 메소드 호출
//                .arguments(pageable)
                .pageSize(10)
                .sorts(Collections.singletonMap("memberId", Sort.Direction.ASC))
                .name("memberItemReader")
                .build();
    }

    //batch 실행부분, 결제 구현 필요
    @Bean
    public ItemProcessor<Member, Member> memberItemProcessor() {
        return member -> {
            LocalDateTime now = LocalDateTime.now();
            member.getSubscribe().setCreatedAt(now);
            member.getSubscribe().setExpiredAt(now.plusDays(30));
            return member;
        };
    }

    //결제 후 DB에 기록
    @Bean
    public ItemWriter<Member> memberItemWriter() {
        return members -> {
            for (Member member : members) {
                LocalDateTime oldCreatedAt = member.getSubscribe().getCreatedAt();
                LocalDateTime oldExpiredAt = member.getSubscribe().getExpiredAt();

                // 갱신 후 데이터
                memberRepository.save(member);

                // 기록 저장
                SubscriptionUpdateHistory history = new SubscriptionUpdateHistory();
                history.setMemberId(member.getMemberId());
                history.setOldCreatedAt(oldCreatedAt);
                history.setNewCreatedAt(member.getSubscribe().getCreatedAt());
                history.setOldExpiredAt(oldExpiredAt);
                history.setNewExpiredAt(member.getSubscribe().getExpiredAt());

                historyRepository.save(history);
            }
        };
    }
}
