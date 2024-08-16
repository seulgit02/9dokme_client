package com.example.server_9dokme.payment.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentRetryScheduler {

    private final JobLauncher jobLauncher;
    private final Job retryPaymentJob;

    public PaymentRetryScheduler(JobLauncher jobLauncher, Job retryPaymentJob) {
        this.jobLauncher = jobLauncher;
        this.retryPaymentJob = retryPaymentJob;
    }

    @Scheduled(fixedDelay = 60000) // 1분마다 실행
    public void runPaymentRetryJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(retryPaymentJob, jobParameters);
            System.out.println("Payment retry job executed at: " + new Date()); // 로그 출력
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

