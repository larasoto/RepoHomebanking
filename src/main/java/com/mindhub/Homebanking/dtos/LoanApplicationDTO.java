package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.ClientLoan;

import java.util.List;

public class LoanApplicationDTO {

    private Double amount;
    private Integer payments;
    private String accountDestiny;
    private String name;
    private Double maxAmount;
    private List<Integer> payment;


    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO( Double amount, Integer payments, String accountDestiny, String name, Double maxAmount,List<Integer> payment) {
        this.amount = amount;
        this.payments = payments;
        this.accountDestiny = accountDestiny;
        this.name = name;
        this.maxAmount = maxAmount;
        this.payment = payment;

    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public String getAccountDestiny() {
        return accountDestiny;
    }

    public void setAccountDestiny(String accountDestiny) {
        this.accountDestiny = accountDestiny;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {this.payment = payment; }


}