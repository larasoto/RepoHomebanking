package com.mindhub.Homebanking.utils;

import java.util.Random;

public class CardUtils {
    public static int getCvv() {
        Random cvvRandom = new Random();
        int cvv = cvvRandom.nextInt(999)+100;
        return cvv;
    }

    public static String getString() {
        String cardNumber = (int)((Math.random()* (999 - 100))+ 100)
                +" "+ (int)((Math.random()* (999 - 100))+ 100)
                +" "+ (int)((Math.random()* (999 - 100))+ 100)
                +" "+ (int)((Math.random()* (999 - 100))+ 100);
        return cardNumber;
    }
    private CardUtils() {


    }



}
