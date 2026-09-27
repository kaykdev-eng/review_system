package com.kayk.review_service.review.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlingConfig {
    @Bean
    public DefaultErrorHandler errorHandler (KafkaTemplate<String, Object> kafkaTemplate) {
        var recover  = new DeadLetterPublishingRecoverer(kafkaTemplate);
        var backOff = new FixedBackOff(2000L, 3L);

        return new DefaultErrorHandler(recover, backOff);
    }
}
