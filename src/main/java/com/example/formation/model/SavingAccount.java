package com.example.formation.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class SavingAccount extends Account {

    private BigDecimal interestRate = BigDecimal.valueOf(3.0);

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
