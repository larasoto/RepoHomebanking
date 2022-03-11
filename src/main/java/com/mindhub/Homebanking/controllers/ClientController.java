package com.mindhub.Homebanking.controllers;

import java.io.IOException;
import com.lowagie.text.DocumentException;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.AccountService;
import com.mindhub.Homebanking.services.ClientService;
import org.apache.catalina.User;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientService clientService;

    @RequestMapping("/clients")
    public List<ClienDTO> getClients() {
        return clientService.getClients();
    }

    @RequestMapping("clients/{id}")
    public ClienDTO getClient(@PathVariable Long id) {
      return clientService.getClient(id);
    }


    @PostMapping( "/api/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByClientEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Random numberRandom = new Random();
        int accountRandom = numberRandom.nextInt(1000);
        System.out.println(accountRandom);

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        Account account = new Account("VIN000"+accountRandom, LocalDateTime.now(),0.0,client,true, AccountType.CAJA_AHORRO);

        //clientRepository.save(client);
        clientService.saveClient(client);
        accountService.saveAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping("/clients/current")
    public ClienDTO getAll(Authentication authentication) {
        return clientService.findByEmail(authentication.getName());
    }


}






