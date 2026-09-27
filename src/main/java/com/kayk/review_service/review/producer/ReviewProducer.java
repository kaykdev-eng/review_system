package com.kayk.review_service.review.producer;

import com.kayk.review_service.review.dtos.ReviewEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReviewProducer {
    @Autowired
    private KafkaTemplate<String, ReviewEventDto> kafkaTemplate;

    public void publishEvent(String topic, ReviewEventDto dto) {
        kafkaTemplate.send(topic, dto);
    }
}
