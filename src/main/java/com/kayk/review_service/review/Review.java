package com.kayk.review_service.review;

import com.kayk.review_service.review.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String product;
    private String client;
    private Integer rating;
    private String comment;
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    private LocalDateTime createdAt;

    public Review(String product, String client, Integer rating, String comment) {
        this.product = product;
        this.client = client;
        this.rating = rating;
        this.comment = comment;
    }

    @PrePersist
    public void prePersist() {
        if(status == null) {
            this.status = ReviewStatus.PENDING;
        }
        this.createdAt = LocalDateTime.now();
    }
}
