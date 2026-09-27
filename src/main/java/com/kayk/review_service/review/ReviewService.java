package com.kayk.review_service.review;

import com.kayk.review_service.review.dtos.ReviewEventDto;
import com.kayk.review_service.review.dtos.ReviewRequestDTO;
import com.kayk.review_service.review.dtos.ReviewResponseDTO;
import com.kayk.review_service.review.enums.ReviewStatus;
import com.kayk.review_service.review.mapper.ReviewMapper;
import com.kayk.review_service.review.producer.ReviewProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReviewMapper mapper;

    @Autowired
    private ReviewProducer producer;

    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> findAll() {
        return repository.findAll().stream().map(review -> mapper.toDto(review)).toList();
    }

    @Transactional(readOnly = true)
    public ReviewResponseDTO findById(UUID id) {
        return repository.findById(id).stream().map(review -> mapper.toDto(review)).findFirst().orElseThrow(() -> new RuntimeException("Resource not found: " + id));
    }

    @Transactional
    public ReviewResponseDTO insert(ReviewRequestDTO dto) {
        Review entity = mapper.toEntity(dto);
        repository.save(entity);

        ReviewEventDto eventDto = new ReviewEventDto(entity.getId(), entity.getComment(), entity.getCreatedAt());
        producer.publishEvent("review-topic", eventDto);
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Transactional
    public void changeStatus (UUID id, ReviewStatus status) {
        Review entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found: " + id));
        entity.setStatus(status);
        repository.save(entity);
    }
}
