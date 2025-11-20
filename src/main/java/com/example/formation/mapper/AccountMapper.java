package com.example.formation.mapper;

import com.example.formation.dto.AccountDto;
import com.example.formation.model.Account;
import com.example.formation.model.CurrentAccount;
import com.example.formation.model.SavingAccount;

public class AccountMapper {

    public static AccountDto toDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setOpenedAt(account.getOpenedAt());
        dto.setClientId(account.getClient() != null ? account.getClient().getId() : null);
        if (account instanceof CurrentAccount ca) {
            dto.setOverdraft(ca.getOverdraft());
        } else if (account instanceof SavingAccount sa) {
            dto.setInterestRate(sa.getInterestRate());
        }
        return dto;
    }
}
