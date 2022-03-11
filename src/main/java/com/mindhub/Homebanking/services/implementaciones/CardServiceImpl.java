package com.mindhub.Homebanking.services.implementaciones;

import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.repositories.CardRepository;
import com.mindhub.Homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card finbyID(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card finbyNumber(String number) {
        return cardRepository.findByNumber(number);
    }
}
