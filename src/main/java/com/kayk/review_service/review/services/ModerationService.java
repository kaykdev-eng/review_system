package com.kayk.review_service.review.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ModerationService {
    private static final String regex_block = "(?i)(" +
            "\\b(caralho|porra|merda|puta|pqp|foder|foda|cu|viado|corno|bofe|bicha|" +
            "vagabundo|vagabunda|otario|otaria|arrombado|arrombada|filho da puta|fdp|" +
            "cacete|buceta|bastardo|imbecil|idiota|trouxa|chupa|bunda|retardado|retardada|" +
            "pinto|penis|cuzao|cusaum)\\b|" +
            "https?://\\S+|www\\.\\S+|" +
            "\\b(ganhe|dinheiro|fácil|promocao|clique|compre|lucro|vaga de emprego|" +
            "trabalhe em casa|renda extra|investimento garantido|urubu do pix|ganhos rápidos)\\b|" +
            "\\b\\w*(\\w)\\4{3,}\\w*\\b" +
            ")";
    private static final Pattern PATTERN = Pattern.compile(regex_block);

    public boolean moderationMethod (String message) {
        if(message == null || message.isBlank()) {
            return true;
        }

        Matcher matcher = PATTERN.matcher(message);
        return !matcher.find();
    }
}
