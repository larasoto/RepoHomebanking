package com.mindhub.Homebanking.controllers;

import java.io.IOException;
import com.lowagie.text.DocumentException;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
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
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/clients")
    public List<ClienDTO> getClients() {
        List<ClienDTO> clients = clientRepository.findAll().stream().map(client -> new ClienDTO(client)).collect(Collectors.toList());
        return clients;
    }

    @RequestMapping("clients/{id}")
    public ClienDTO getClient(@PathVariable Long id) {
        ClienDTO client = new ClienDTO(clientRepository.findById(id).orElse(null));
        return client;
    }


    @PostMapping( "/api/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Random numberRandom = new Random();
        int accountRandom = numberRandom.nextInt(1000);
        System.out.println(accountRandom);

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        Account account = new Account("VIN000"+accountRandom, LocalDateTime.now(),0.0,client,true, AccountType.CAJA_AHORRO);

        clientRepository.save(client);
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping("/clients/current")
    public ClienDTO getAll(Authentication authentication) {
        return new ClienDTO(clientRepository.findByEmail(authentication.getName()));
    }


}






