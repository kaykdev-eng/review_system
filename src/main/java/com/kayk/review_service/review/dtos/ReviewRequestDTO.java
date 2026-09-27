package com.kayk.review_service.review.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ReviewRequestDTO(
        @NotBlank
        String product,
        @NotBlank
        String client,
        @Max(value = 10, message = "The maximum rating is a score of 10.")
        @Min(value = 1, message = "The minimum rating is a score of 10.")
        Integer rating,
        @NotBlank
        String comment
) {
}
