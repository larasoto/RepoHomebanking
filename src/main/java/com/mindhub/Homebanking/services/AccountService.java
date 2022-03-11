package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.Account;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    public List<AccountDTO> getAccounts();
    public AccountDTO getCurrentAccountDTO(Long id);
    public Account getCurrentaccount(Long id);
    Account findbyNumber(String account);
}
