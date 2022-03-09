package com.mindhub.Homebanking.controllers;

import com.lowagie.text.DocumentException;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransacctionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServices transactionServices;

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> transaction(Authentication authentication,
                                              @RequestParam Double amount, @RequestParam String description,
                                              @RequestParam String accountOrigen, @RequestParam String accountDestiny) {

        Client client = clientRepository.findByEmail(authentication.getName());
        Account accountorigen = accountRepository.findByNumber(accountOrigen);
        Account accountdestino = accountRepository.findByNumber(accountDestiny);

        if (amount == null || description.isEmpty() || accountOrigen == null || accountDestiny == null) {
            return new ResponseEntity<>("Data is empty", HttpStatus.FORBIDDEN);
        }
        if (accountOrigen == accountDestiny) {
            return new ResponseEntity<>("you cannot transfer to the same account", HttpStatus.FORBIDDEN);
        }

        if (accountRepository.findByNumber(accountOrigen) == null) {
            return new ResponseEntity<>("the source account does not exist", HttpStatus.FORBIDDEN);
        }
        if (client.getAccounts().stream().filter(account -> account.getNumber().equals(accountOrigen)) == null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }
        if (accountRepository.findByNumber(accountDestiny) == null) {
            return new ResponseEntity<>("the destination account does not exist", HttpStatus.FORBIDDEN);
        }
        if (amount > accountorigen.getBalance()) {
            return new ResponseEntity<>("the amount is not available", HttpStatus.FORBIDDEN);
        }

        Transaction transaction1 = new Transaction(TransactionType.DEBIT, -amount,description, LocalDateTime.now(),accountorigen);
        Transaction transaction2 = new Transaction(TransactionType.CREDIT,amount,description,LocalDateTime.now(),accountdestino);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        accountorigen.setBalance(accountorigen.getBalance() - amount);
        accountdestino.setBalance(accountdestino.getBalance() + amount);

        accountRepository.save(accountorigen);
        accountRepository.save(accountdestino);
        return new ResponseEntity<>("201 creada",HttpStatus.CREATED);

    }
    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Transaction> listTransaction = transactionServices.listAll();

        Ejemplo exporter = new Ejemplo(listTransaction);
        exporter.export(response);

    }
/*
    @RequestMapping("/clients/current/cards")
    public List<TransactionDTO> getTransaction(Authentication authentication) {
        Account accountorigen = accountRepository.findByNumber(accountOrigen);
        return client.getAccounts().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }
}*/


}