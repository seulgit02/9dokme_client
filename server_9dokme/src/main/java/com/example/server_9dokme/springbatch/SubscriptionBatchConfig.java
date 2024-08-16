package com.example.server_9dokme.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SubscriptionBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SubscriptionTasklet subscriptionTasklet;

    public SubscriptionBatchConfig(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   SubscriptionTasklet subscriptionTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.subscriptionTasklet = subscriptionTasklet;
    }

    @Bean
    public Job subscriptionJob() {
        return jobBuilderFactory.get("subscriptionJob")
                .start(subscriptionStep())
                .build();
    }

    @Bean
    public Step subscriptionStep() {
        return stepBuilderFactory.get("subscriptionStep")
                .tasklet(subscriptionTasklet)
                .build();
    }


}
