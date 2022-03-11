package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;

import java.util.List;

public interface LoanService {
    public List<LoanDTO> getLoans();
    Loan findbyName (String loan);
    Loan saveLoan(Loan loan);
}
