package com.kayk.review_service.review;

import com.kayk.review_service.review.dtos.ReviewRequestDTO;
import com.kayk.review_service.review.dtos.ReviewResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findAll() {
        List<ReviewResponseDTO> reviews = service.findAll();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable UUID id) {
        ReviewResponseDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> insert(@Valid @RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO newDto = service.insert(dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
