package com.mindhub.Homebanking.controllers;



import com.mindhub.Homebanking.dtos.*;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import com.mindhub.Homebanking.services.*;
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
    TransactionService transactionService;

    @Autowired
    ClientLoanService clientLoanService;

    @Autowired
    LoanService loanService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    CardRepository cardRepository;



    @RequestMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> addLoan (@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

       Client client = clientService.findByClientEmail(authentication.getName());
       Account account = accountService.findbyNumber(loanApplicationDTO.getAccountDestiny());
       Loan loan = loanService.findbyName(loanApplicationDTO.getName());

       if(loanApplicationDTO.getAmount()<=0){
           return new ResponseEntity<>("el valor es menor",HttpStatus.FORBIDDEN);
       }
        if(loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getAmount() == null || loanApplicationDTO.getPayments() == null ){
        return new ResponseEntity<>("invalid data",HttpStatus.FORBIDDEN);}

        if( loanService.findbyName(loan.getName())== null){
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
        if(accountService.findbyNumber(loanApplicationDTO.getAccountDestiny())== null){
          return new ResponseEntity<>("the account does not belong to the client",HttpStatus.FORBIDDEN);

    }


        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount()+(loanApplicationDTO.getAmount()*0.2),loanApplicationDTO.getPayments(),loan,client);
        Transaction transaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + ""+ "loan aproved", LocalDateTime.now(),account);

        clientLoanService.saveClientLoan(clientLoan);
        transactionService.saveTransaction(transaction);

        account.setBalance(account.getBalance()+loanApplicationDTO.getAmount());
        accountService.saveAccount(account);
        return new ResponseEntity<>("created loan",HttpStatus.CREATED);
    }

    @PostMapping("/loans/admin")
    public ResponseEntity <Object>CreationLoan(@RequestBody LoanApplicationDTO loanApplicationDTO){

        if(loanApplicationDTO.getMaxAmount()== 0 || loanApplicationDTO.getPayment().isEmpty()|| loanApplicationDTO.getMaxAmount() == null || loanApplicationDTO.getPayment() == null ){
            return new ResponseEntity<>("invalid data",HttpStatus.FORBIDDEN);}

        Loan loan = new Loan(loanApplicationDTO.getName(),loanApplicationDTO.getMaxAmount(),loanApplicationDTO.getPayment());
         loanService.saveLoan(loan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

