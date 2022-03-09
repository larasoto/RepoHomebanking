package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Loan;

import java.util.List;

public class LoanDTO {

    private long id;
    private String name;
    private Double MaxAmount;
    private List<Integer> Payments;


    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.MaxAmount = loan.getMaxAmount();
        this.Payments = loan.getPayments();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        MaxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return Payments;
    }

    public void setPayments(List<Integer> payments) { Payments = payments;}


}
