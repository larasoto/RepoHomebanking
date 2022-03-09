package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.models.AccountType;
import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
   private AccountRepository accountRepository;

    @Autowired
   private ClientRepository clientRepository;


    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        List<AccountDTO> accounts = accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        return accounts;
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        AccountDTO account = new AccountDTO(accountRepository.findById(id).orElse(null));
        return account;
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> registerAccount(Authentication authentication,
    @RequestParam AccountType accountType){


        Client client = clientRepository.findByEmail(authentication.getName());

        if ( client.getAccounts().stream().filter(account ->account.getAccountStatus() == true).collect(Collectors.toList()).size() >= 3 ) {
            return new ResponseEntity<>("403 prohibido", HttpStatus.FORBIDDEN);
        }

        Random numberRandom = new Random();
        int accountRandom = numberRandom.nextInt(1000);

        Account account = new Account("VIN000"+accountRandom, LocalDateTime.now(),0.0,client,true, accountType);
        accountRepository.save(account);
        return new ResponseEntity<>("201 creada",HttpStatus.CREATED);
    }
    @PatchMapping("/clients/current/accounts/{id}")
    public ResponseEntity<Object> PatchAccount(
            @PathVariable long id, @RequestParam Boolean accountStatus){
        Account accounts =accountRepository.findById(id).orElse(null);
        accounts.setAccountStatus(accountStatus);
        accountRepository.save(accounts);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping("/clients/current/accounts")
    public List<AccountDTO> getAccount(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    }


