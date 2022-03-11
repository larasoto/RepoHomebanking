package com.mindhub.Homebanking.services.implementaciones;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import com.mindhub.Homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

}
