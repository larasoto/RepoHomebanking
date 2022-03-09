package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.ClientLoan;
import com.mindhub.Homebanking.models.Loan;

import java.util.HashSet;
import java.util.Set;

public class ClientLoanDTO {
    private long id ;
    private long loanId;
    private String name;
    private Double amount;
    private int payments;


    public ClientLoanDTO() {

    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId =clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();

    }

    public long getId() {
        return id;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }


}
