package com.kayk.review_service;

import com.kayk.review_service.review.Review;
import com.kayk.review_service.review.ReviewRepository;
import com.kayk.review_service.review.ReviewService;
import com.kayk.review_service.review.dtos.ReviewRequestDTO;
import com.kayk.review_service.review.dtos.ReviewResponseDTO;
import com.kayk.review_service.review.enums.ReviewStatus;
import com.kayk.review_service.review.mapper.ReviewMapper;
import com.kayk.review_service.review.producer.ReviewProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository repository;

    @Mock
    private ReviewMapper mapper;

    @Mock
    private ReviewProducer producer;

    @InjectMocks
    private ReviewService service;

    @Test
    public void shouldSaveWithPendingStatus() {
        LocalDateTime date = LocalDateTime.of(2026, 9, 27, 10, 0, 0);
        UUID id = UUID.randomUUID();
        ReviewRequestDTO dtoIn = new ReviewRequestDTO("Iphone", "Kayk", 10, "Celular muito bom");

        Review reviewNotId = new Review();
        reviewNotId.setProduct("Iphone");
        reviewNotId.setClient("Kayk");
        reviewNotId.setRating(10);
        reviewNotId.setComment("Celular muito bom");
        reviewNotId.setStatus(ReviewStatus.PENDING);
        reviewNotId.setCreatedAt(date);

        Review entitySave = new Review();
        entitySave.setId(id);
        entitySave.setProduct("Iphone");
        entitySave.setClient("Kayk");
        entitySave.setRating(10);
        entitySave.setComment("Celular muito bom");
        entitySave.setStatus(ReviewStatus.PENDING);
        entitySave.setCreatedAt(date);

        ReviewResponseDTO dtoOut = new ReviewResponseDTO(
                id,
                "Iphone",
                "Kayk",
                10,
                "Celular muito bom",
                ReviewStatus.PENDING,
                date
        );

        Mockito.when(mapper.toEntity(Mockito.any(ReviewRequestDTO.class))).thenReturn(reviewNotId);
        Mockito.when(repository.save(Mockito.any(Review.class))).thenReturn(entitySave);
        Mockito.when(mapper.toDto(Mockito.any(Review.class))).thenReturn(dtoOut);

        ReviewResponseDTO result = service.insert(dtoIn);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(dtoOut);

        Mockito.verify(producer, Mockito.times(1)).publishEvent(Mockito.anyString(), Mockito.any());
    }
}
