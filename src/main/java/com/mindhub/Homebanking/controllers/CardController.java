package com.mindhub.Homebanking.controllers;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.dtos.CardDTO;
import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.utils.CardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CardRepository cardRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> registerCard(Authentication authentication,
            @RequestParam CardType type, @RequestParam CardColor color, @RequestParam String account) {


        Client client = clientRepository.findByEmail(authentication.getName());
        Account account1 = accountRepository.findByNumber(account);


        if ( client.getCards().stream().filter(card -> card.getStatus() == true).collect(Collectors.toList()).size() >= 6 ) {
            return new ResponseEntity<>("403 prohibido", HttpStatus.FORBIDDEN);
        }

        int cvv = CardUtils.getCvv();

        String cardNumber =CardUtils.getString();


        Card card = new Card(client.getFirtName()+" "+ client.getLastName(),type,color,cardNumber,cvv,LocalDate.now().plusYears(5),LocalDate.now(),client, true,account1, account1.getBalance());
        cardRepository.save(card);
        return new ResponseEntity<>("201 creada",HttpStatus.CREATED);
    }

    @PatchMapping("/clients/current/cards/{id}")
    public ResponseEntity<Object> PatchCard(
      @PathVariable long id, @RequestParam Boolean status){
            Card cards = cardRepository.findById(id).orElse(null);
            cards.setStatus(status);
            cardRepository.save(cards);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping("/clients/current/cards")
    public List<CardDTO> getCards(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return client.getCards().stream().map(CardDTO::new).collect(Collectors.toList());
    }
    }



