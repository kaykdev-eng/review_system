package com.kayk.review_service.review.dtos;

import com.kayk.review_service.review.enums.ReviewStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponseDTO(
        UUID id,
        String product,
        String client,
        Integer rating,
        String comment,
        ReviewStatus status,
        LocalDateTime createdAt
) {
}
