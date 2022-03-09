package com.mindhub.Homebanking.models;

import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TransactionServices {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction>listAll(){
        return transactionRepository.findAll();

    }
}
