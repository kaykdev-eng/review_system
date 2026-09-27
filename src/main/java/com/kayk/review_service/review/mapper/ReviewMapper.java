package com.kayk.review_service.review.mapper;

import com.kayk.review_service.review.Review;
import com.kayk.review_service.review.dtos.ReviewRequestDTO;
import com.kayk.review_service.review.dtos.ReviewResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    Review toEntity(ReviewRequestDTO dto);
    ReviewResponseDTO toDto(Review entity);
}
