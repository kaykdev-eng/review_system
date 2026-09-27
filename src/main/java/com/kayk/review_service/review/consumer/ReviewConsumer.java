package com.kayk.review_service.review.consumer;

import com.kayk.review_service.review.ReviewService;
import com.kayk.review_service.review.dtos.ReviewEventDto;
import com.kayk.review_service.review.enums.ReviewStatus;
import com.kayk.review_service.review.services.ModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewConsumer {
    @Autowired
    private ReviewService service;

    @Autowired
    private ModerationService moderationService;

    @KafkaListener(topics = "review-topic", groupId = "review-group-id")
    public void consumerListener(ReviewEventDto dto) {
        boolean approved = moderationService.moderationMethod(dto.comment());
        ReviewStatus status = approved ? ReviewStatus.APPROVED : ReviewStatus.REJECTED;
        service.changeStatus(dto.id(), status);
    }

    @KafkaListener(topics = "review-topic-dlt", groupId = "dlq-debug-group-v4")
    public void ListenerDlq(ReviewEventDto message) {
        System.out.println("DLQ: " + message);
    }
}
