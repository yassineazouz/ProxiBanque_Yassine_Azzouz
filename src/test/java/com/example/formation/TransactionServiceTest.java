package com.example.formation;

import com.example.formation.model.*;
import com.example.formation.repository.AccountRepository;
import com.example.formation.repository.TransactionRepository;
import com.example.formation.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private TransactionService transactionService;

    private CurrentAccount fromAccount;
    private CurrentAccount toAccount;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(accountRepository, transactionRepository);

        fromAccount = new CurrentAccount();
        fromAccount.setBalance(BigDecimal.valueOf(500));

        toAccount = new CurrentAccount();
        toAccount.setBalance(BigDecimal.valueOf(200));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));
    }

    @Test
    void testTransferAndHistory() {
        BigDecimal transferAmount = BigDecimal.valueOf(300);

        transactionService.transfer(1L, 2L, transferAmount);

        assertEquals(BigDecimal.valueOf(200), fromAccount.getBalance());
        assertEquals(BigDecimal.valueOf(500), toAccount.getBalance());

        // Vérifier que les comptes ont été sauvegardés
        verify(accountRepository, times(1)).save(fromAccount);
        verify(accountRepository, times(1)).save(toAccount);

        // Vérifier que la transaction a été sauvegardée
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testTransferInsufficientFunds() {
        BigDecimal transferAmount = BigDecimal.valueOf(2000); // dépasse l'overdraft

        Exception exception = assertThrows(RuntimeException.class, () -> {
            transactionService.transfer(1L, 2L, transferAmount);
        });

        assertEquals("Insufficient funds", exception.getMessage());
    }
}
