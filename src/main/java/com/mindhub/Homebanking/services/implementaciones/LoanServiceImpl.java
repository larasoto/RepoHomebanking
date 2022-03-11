package com.mindhub.Homebanking.services.implementaciones;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.models.Loan;
import com.mindhub.Homebanking.repositories.LoanRepository;
import com.mindhub.Homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public Loan findbyName(String loan) {
        return loanRepository.findByName(loan);
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }
}
