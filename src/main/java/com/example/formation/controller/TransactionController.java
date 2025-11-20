package com.example.formation.controller;

import com.example.formation.dto.TransactionDto;
import com.example.formation.dto.TransferDto;
import com.example.formation.mapper.TransactionMapper;
import com.example.formation.repository.TransactionRepository;
import com.example.formation.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService,
            TransactionRepository transactionRepository,
            TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDto transferDto) {
        transactionService.transfer(
                transferDto.getFromAccountId(),
                transferDto.getToAccountId(),
                transferDto.getAmount());
        return "Transfer successful";
    }

    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }
}
