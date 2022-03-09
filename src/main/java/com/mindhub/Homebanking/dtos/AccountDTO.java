package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.AccountType;
import com.mindhub.Homebanking.models.Transaction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime createdData;
    private Double balance;
    Set<TransactionDTO> transactions = new HashSet<>();
    private Boolean accountStatus;
    private AccountType accountType;
    Set<CardDTO> cards = new HashSet<>();

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.createdData = account.getCreatedData();
        this.balance = account.getBalance();
        this.transactions= account.getTransaction().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
        this.accountStatus = account.getAccountStatus();
        this.accountType = account.getAccountType();
        this.cards = account.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }

    public long getId() { return id;}

    public String getNumber() {  return number; }

    public void setNumber(String number) {   this.number = number; }

    public LocalDateTime getCreatedData() {
        return createdData;
    }

    public void setCreatedData(LocalDateTime createdData) {   this.createdData = createdData; }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransaction() {
        return transactions;
    }

    public void setTransaction(Set<TransactionDTO> transaction) {
        this.transactions = transaction;
    }

    public Boolean getAccountStatus() { return accountStatus;}

    public void setAccountStatus(Boolean accountStatus) { this.accountStatus = accountStatus;}

    public AccountType getAccountType() { return accountType;}

    public void setAccountType(AccountType accountType) {  this.accountType = accountType;}

    public Set<CardDTO> getCards() {   return cards;}

    public void setCards(Set<CardDTO> cards) {   this.cards = cards; }


}
