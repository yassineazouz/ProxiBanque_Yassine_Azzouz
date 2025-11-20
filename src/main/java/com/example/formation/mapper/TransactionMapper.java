package com.example.formation.mapper;

import com.example.formation.dto.TransactionDto;
import com.example.formation.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setFromAccountId(transaction.getFromAccount().getId());
        dto.setToAccountId(transaction.getToAccount().getId());
        dto.setAmount(transaction.getAmount());
        dto.setTimestamp(transaction.getTimestamp());
        return dto;
    }
}
