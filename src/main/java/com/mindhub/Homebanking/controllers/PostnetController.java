package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.LoanDTO;
import com.mindhub.Homebanking.dtos.PostnetApplicationDTO;
import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.CardService;
import com.mindhub.Homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostnetController {

    @Autowired
    CardService cardService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Transactional
    @PostMapping("/transactions/postnet")
    ResponseEntity<Object> postnetPago(@RequestBody PostnetApplicationDTO postnet) {
        Card card = cardService.finbyNumber(postnet.getNumber());
        Account account = accountService.findbyNumber(card.getAccount().getNumber());
        Account account1 = accountService.findbyNumber(postnet.getAccountDestiny());

        if(postnet.getAmount() <= 0){
            return new ResponseEntity<>("el valor es incorrecto", HttpStatus.FORBIDDEN);
        }

        if ( card.getCvv() != postnet.getCvv()|| postnet.getAmount().isNaN() || postnet.getDescription() == "") {
            return new ResponseEntity<>("los datos son incorrectos", HttpStatus.FORBIDDEN);
        }

        if(LocalDate.now().isAfter(card.getTrhuDate())){
            return new ResponseEntity<>("la tarjeta se encuentra vencida", HttpStatus.FORBIDDEN); }

        if(card.getAmount()<postnet.getAmount()) {
            return new ResponseEntity<>("la cuenta no posee el monto suficiente", HttpStatus.FORBIDDEN); }

        Transaction transaction = new Transaction(TransactionType.DEBIT, -postnet.getAmount(), postnet.getDescription(), LocalDateTime.now(),account);
        Transaction transaction1 = new Transaction(TransactionType.CREDIT, postnet.getAmount(), postnet.getDescription(), LocalDateTime.now(),account1);
         transactionService.saveTransaction(transaction);
         transactionService.saveTransaction(transaction1);


       account.setBalance(account.getBalance()-postnet.getAmount());
       account1.setBalance(account1.getBalance() + postnet.getAmount());

       accountService.saveAccount(account);
      accountService.saveAccount(account1);
       return new ResponseEntity<>("creado", HttpStatus.CREATED);

    }
}