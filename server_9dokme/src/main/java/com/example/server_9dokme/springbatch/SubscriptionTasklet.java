package com.example.server_9dokme.springbatch;

import com.example.server_9dokme.subscribe.entity.PaymentStatus;
import com.example.server_9dokme.subscribe.entity.Subscribe;
import com.example.server_9dokme.subscribe.repository.SubscribeRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SubscriptionTasklet implements Tasklet {

    @Autowired
    private SubscribeRepository subscribeRepository;
    @Autowired
    private final PaymentService paymentService;

    public SubscriptionTasklet(SubscribeRepository subscribeRepository,
                               PaymentService paymentService) {
        this.subscribeRepository = subscribeRepository;
        this.paymentService = paymentService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        List<Subscribe> subscriptions = subscribeRepository.findAllByExpiredDateBeforeAndPaymentStatus(now, PaymentStatus.PAID);

        for (Subscribe subscription : subscriptions) {
            try {
                // 결제 요청 처리
                paymentService.processSubscriptionPayment(subscription);

                // 성공 시 구독 정보 갱신
                subscription.setExpiredDate(now.plusMonths(1));
                subscription.setCreatedAt(now);
                subscribeRepository.save(subscription);

            } catch (Exception e) {
                // 실패 시 예외 처리
                subscription.setPaymentStatus(PaymentStatus.NONPAID);
                subscribeRepository.save(subscription);
                // 추가적으로 로그를 남기거나 재시도 로직을 구현할 수 있습니다.
            }
        }

        return RepeatStatus.FINISHED;
    }



}
