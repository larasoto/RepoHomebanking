package com.mindhub.Homebanking;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

//@DataJpaTest //hace los tets del jpa
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {


        @Autowired
        LoanRepository loanRepository;

        @Autowired
        AccountRepository accountRepository;

        @Autowired
        CardRepository cardRepository;

        @Autowired
        ClientRepository clientRepository;

        @Autowired
        TransactionRepository transactionRepository;

        @Test
        public void existLoans(){
            List<Loan> loans = loanRepository.findAll();
            assertThat(loans,is(not(empty())));
        }


        @Test
        public void existPersonalLoan(){
            List<Loan> loans = loanRepository.findAll();
            assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

        }
    @Test
    public void existAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,is(not(empty())));
    }


    @Test
    public void existPersonalAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("number", is("VIN001"))));
    }
    @Test
    public void existCard(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }

    @Test
    public void existPersonalCard(){
        List<Card> cards= cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("type", is(CardType.DEBIT))));
    }
    @Test
    public void existClient(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }

    @Test
    public void existPersonalClient(){
        List<Client> clients=clientRepository.findAll();
        assertThat(clients, hasItem(hasProperty("email", is("melba@mindhub.com"))));
    }

    @Test
    public void existTransaction(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions,is(not(empty())));
    }

    @Test
    public void existPersonalTransaction(){
        List<Transaction> transactions=transactionRepository.findAll();
        assertThat(transactions, hasItem(hasProperty("type", is(TransactionType.DEBIT))));
    }

}

