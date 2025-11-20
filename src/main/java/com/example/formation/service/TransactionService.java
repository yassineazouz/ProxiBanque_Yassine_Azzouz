package com.example.formation.service;

import com.example.formation.model.Account;
import com.example.formation.model.CurrentAccount;
import com.example.formation.model.Transaction;
import com.example.formation.repository.AccountRepository;
import com.example.formation.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        // Solde disponible
        BigDecimal availableBalance = fromAccount.getBalance();
        if (fromAccount instanceof CurrentAccount) {
            availableBalance = availableBalance.add(((CurrentAccount) fromAccount).getOverdraft());
        }

        if (availableBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Débiter et créditer
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Enregistrer transaction
        Transaction transaction = new Transaction(fromAccount, toAccount, amount, LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    // Méthode pour récupérer l'historique d'un compte
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}
