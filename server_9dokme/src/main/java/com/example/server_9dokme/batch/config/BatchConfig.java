package com.example.server_9dokme.batch.config;

import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
//    @Autowired
//    private JobBuilder jobBuilder;
//    @Autowired
//    private StepBuilder stepBuilder;
    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public Job updateSubscriptionJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("updateSubscriptionJob", jobRepository)
                .start(updateSubscriptionStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step updateSubscriptionStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("updateSubscriptionStep", jobRepository)
                .<Member, Member>chunk(10, transactionManager)
                .reader(memberItemReader())
                .processor(memberItemProcessor())
                .writer(memberItemWriter())
                .build();
    }

    //갱신 대상 member Read 로직
    @Bean
    public ItemReader<Member> memberItemReader() {
        return new RepositoryItemReaderBuilder<Member>()
                .repository(memberRepository)
                .methodName("findExpiredSubscriptions")
                .arguments(LocalDateTime.now())
                .pageSize(10)
                .sorts(Collections.singletonMap("memberId", Sort.Direction.ASC))
                .name("memberItemReader")
                .build();
    }

    //createdAt, expiredAt 갱신 로직
    @Bean
    public ItemProcessor<Member, Member> memberItemProcessor() {
        return member -> {
            LocalDateTime now = LocalDateTime.now();
            member.getSubscribe().setCreatedAt(now);
            member.getSubscribe().setExpiredAt(now.plusDays(30));
            return member;
        };
    }

    //갱신된 데이터를 DB에 저장하는 로직
    @Bean
    public ItemWriter<Member> memberItemWriter() {
        return members -> {
            for (Member member : members) {
                memberRepository.save(member);
            }
        };
    }
}
