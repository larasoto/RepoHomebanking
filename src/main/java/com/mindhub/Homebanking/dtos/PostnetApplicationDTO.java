package com.mindhub.Homebanking.dtos;

import com.mindhub.Homebanking.models.Account;

public class PostnetApplicationDTO {


    private String number;
    private int cvv;
    private Double amount;
    private String description;
    private String accountDestiny;




    public PostnetApplicationDTO() {
    }

    public PostnetApplicationDTO(String number, int cvv, Double amount, String description,String accountDestiny) {
        this.number = number;
        this.cvv = cvv;
        this.amount = amount;
        this.description = description;
        this.accountDestiny = accountDestiny;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountDestiny() {
        return accountDestiny;
    }

    public void setAccountDestiny(String accountDestiny) {
        this.accountDestiny = accountDestiny;
    }

    
}
