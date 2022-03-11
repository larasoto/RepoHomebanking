package com.mindhub.Homebanking.services.implementaciones;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account) ;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getCurrentAccountDTO(Long id) {
       AccountDTO accountDTO = new AccountDTO(accountRepository.findById(id).orElse(null));
        return accountDTO;
    }

    @Override
    public Account getCurrentaccount(Long id){
        return  accountRepository.findById(id).orElse(null);

    }

    @Override
    public Account findbyNumber(String account){
    return  accountRepository.findByNumber(account);
    }
}
