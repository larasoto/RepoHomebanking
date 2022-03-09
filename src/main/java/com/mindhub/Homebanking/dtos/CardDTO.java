package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.CardColor;
import com.mindhub.Homebanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private long id;

    private String cardholder;
    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;
    private LocalDate trhuDate;
    private LocalDate fromDate;
    private Boolean status;
    private Double amount;



    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id=card.getId();
        this.cardholder = card.getCardholder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number=card.getNumber();
        this.cvv =card.getCvv();
        this.trhuDate = card.getTrhuDate();
        this.fromDate =card.getFromDate();
        this.status = card.getStatus();
        this.amount =card.getAmount();


    }

    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getTrhuDate() {
        return trhuDate;
    }

    public void setTrhuDate(LocalDate trhuDate) {
        this.trhuDate = trhuDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getAmount() { return amount;  }

    public void setAmount(Double amount) {    this.amount = amount; }


}
