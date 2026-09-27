package com.kayk.review_service;

import com.kayk.review_service.review.services.ModerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ModerationServiceTest {

    @InjectMocks
    private ModerationService service;

    @Test
    public void shouldReturnTrue() {
        String message = "Celular otimo!";
        boolean result = service.moderationMethod(message);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalse() {
        String message = "Celular é uma merda";
        boolean result = service.moderationMethod(message);
        assertThat(result).isFalse();
    }
}
