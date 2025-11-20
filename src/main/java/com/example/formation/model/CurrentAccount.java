package com.example.formation.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CurrentAccount extends Account {

    private BigDecimal overdraft = BigDecimal.valueOf(1000);

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }
}
