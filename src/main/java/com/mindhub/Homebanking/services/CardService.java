package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.models.Card;

public interface CardService {
    Card saveCard (Card card);
    Card finbyID (Long id);
    Card finbyNumber( String number);

}
