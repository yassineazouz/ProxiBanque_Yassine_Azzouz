package com.example.formation.mapper;

import com.example.formation.dto.CardDto;
import com.example.formation.model.Card;

public class CardMapper {

    public static CardDto toDto(Card card) {
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setCardNumber(card.getCardNumber());
        dto.setType(card.getType());
        dto.setActive(card.isActive());
        dto.setIssuedAt(card.getIssuedAt());
        dto.setClientId(card.getClient() != null ? card.getClient().getId() : null);
        return dto;
    }
}
