package com.mindhub.Homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity //crea una tabla en la base de datos
public class Account {
    @Id //propiedad primaria de la clase
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    private LocalDateTime createdData;
    private Double balance;
    private Boolean accountStatus;
    private AccountType accountType;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    public Account() {
    }


    public Account( String number, LocalDateTime createdData, Double balance, Client client, Boolean accountStatus,AccountType accountType) {
        this.number = number;
        this.createdData = createdData;
        this.balance = balance;
        this.client = client;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
    }


    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreatedData() {
        return createdData;
    }

    public void setCreatedData(LocalDateTime createdData) {
        this.createdData = createdData;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {  this.client = client; }

    public Set<Transaction> getTransaction() {
        return transactions;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transactions = transaction;
    }
    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Set<Card> getCards() {return cards;}

    public void setCards(Set<Card> cards) {


        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", createdData=" + createdData +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}
