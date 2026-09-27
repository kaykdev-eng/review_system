package com.kayk.review_service.review.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewEventDto(
        UUID id,
        String comment,
        LocalDateTime createdAt
) {
}
