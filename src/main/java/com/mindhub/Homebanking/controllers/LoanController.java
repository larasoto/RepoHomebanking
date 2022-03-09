package com.mindhub.Homebanking.controllers;



import com.mindhub.Homebanking.dtos.*;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Autowired
    LoanRepository loanRepository;


    @Autowired
    TransactionRepository transactionRepository;


    @RequestMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }
    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> addLoan (@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

       Client client = clientRepository.findByEmail(authentication.getName());
       Account account = accountRepository.findByNumber(loanApplicationDTO.getAccountDestiny());
       Loan loan = loanRepository.findByName(loanApplicationDTO.getName());

        if(loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getAmount() == null || loanApplicationDTO.getPayments() == null ){
        return new ResponseEntity<>("invalid data",HttpStatus.FORBIDDEN);}

        if(loanRepository.findByName(loan.getName())== null){
        return new ResponseEntity<>("the loan does not exist",HttpStatus.FORBIDDEN);}

        if(loanApplicationDTO.getAmount()>loan.getMaxAmount()){
            return new ResponseEntity<>("invalid amount", HttpStatus.FORBIDDEN);
        }
        if(loan.getPayments().stream().filter(payment -> payment.equals(loanApplicationDTO.getPayments())).collect(Collectors.toList()).size()==0){
            return new ResponseEntity<>("you cannot choose that number of installments",HttpStatus.FORBIDDEN); //verificar si la cantidad de cuotas se encuentre entre las disponibles de prestamo
        }

        if(client.getAccounts().contains(loanApplicationDTO.getAccountDestiny())){
            return new ResponseEntity<>("the account does not exist", HttpStatus.FORBIDDEN);
        }
        if(accountRepository.findByNumber(loanApplicationDTO.getAccountDestiny())== null){
          return new ResponseEntity<>("the account does not belong to the client",HttpStatus.FORBIDDEN);

    }


        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount()+(loanApplicationDTO.getAmount()*0.2),loanApplicationDTO.getPayments(),loan,client);
        Transaction transaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + ""+ "loan aproved", LocalDateTime.now(),account);


        clientLoanRepository.save(clientLoan);
        transactionRepository.save(transaction);

        account.setBalance(account.getBalance()+loanApplicationDTO.getAmount());
        accountRepository.save(account);
        return new ResponseEntity<>("created loan",HttpStatus.CREATED);
    }

    @PostMapping("/loans/admin")
    public ResponseEntity <Object>CreationLoan(@RequestBody LoanApplicationDTO loanApplicationDTO){

        if(loanApplicationDTO.getMaxAmount()== 0 || loanApplicationDTO.getPayment().isEmpty()|| loanApplicationDTO.getMaxAmount() == null || loanApplicationDTO.getPayment() == null ){
            return new ResponseEntity<>("invalid data",HttpStatus.FORBIDDEN);}

        Loan loan = new Loan(loanApplicationDTO.getName(),loanApplicationDTO.getMaxAmount(),loanApplicationDTO.getPayment());
        loanRepository.save(loan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

