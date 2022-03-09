package com.mindhub.Homebanking;

import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HomebankingApplication.class, args);
    }
    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository) {
        return (args) -> {
            // save a couple of

            Client client1 = new Client( "melba", "morel", "melba@mindhub.com", passwordEncoder.encode("llll"));
            Client client2 = new Client( "lara", "soto", "larasoto@mindhub.com", passwordEncoder.encode("uuuu"));
            Client client3 = new Client("Agustin","Romero","agustinRomero@gmail.com", passwordEncoder.encode("1111"));
            Client client4 = new Client("Lara","Soto","sotolara31@gmail.com", passwordEncoder.encode("lara"));


            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);
            clientRepository.save(client4);


            Account account1= new Account("VIN001", LocalDateTime.now(), 5000., client1,true,AccountType.CUENTA_CORRIENTE);
            Account account2 = new Account( "VIN002", LocalDateTime.now().plusDays(1), 7000., client1,true,AccountType.CUENTA_CORRIENTE);
            Account account3= new Account( "VIN003", LocalDateTime.now(), 2000., client2,true,AccountType.CUENTA_CORRIENTE);
            Account account4=new Account("VIN004", LocalDateTime.now(), 1000., client2,true,AccountType.CAJA_AHORRO);
            Account account5=new Account("VIN005", LocalDateTime.now(), 1067., client3,true,AccountType.CAJA_AHORRO);

            accountRepository.save(account1);
            accountRepository.save(account2);
            accountRepository.save(account3);
            accountRepository.save(account4);
            accountRepository.save(account5);

            Transaction transaction1 = new Transaction(TransactionType.DEBIT,-1500.,"sale",LocalDateTime.now(),account1);
            Transaction transaction2 = new Transaction(TransactionType.DEBIT,-3500.,"sale",LocalDateTime.now(),account1);
            Transaction transaction3 = new Transaction(TransactionType.CREDIT,2600.,"sale",LocalDateTime.now(),account1);

            Transaction transaction4 = new Transaction(TransactionType.CREDIT,2600.,"sale",LocalDateTime.now(),account2);
            Transaction transaction5 = new Transaction(TransactionType.CREDIT,2400.,"sale",LocalDateTime.now(),account2);
            Transaction transaction6 = new Transaction(TransactionType.DEBIT,-3600.,"sale",LocalDateTime.now(),account2);

            Transaction transaction7 = new Transaction(TransactionType.CREDIT,2600.,"sale",LocalDateTime.now(),account3);
            Transaction transaction8 = new Transaction(TransactionType.CREDIT,2600.,"sale",LocalDateTime.now(),account3);
            Transaction transaction9 = new Transaction(TransactionType.DEBIT,-3600.,"sale",LocalDateTime.now(),account3);

            Transaction transaction10 = new Transaction(TransactionType.CREDIT,6600.,"sale",LocalDateTime.now(),account4);
            Transaction transaction11 = new Transaction(TransactionType.CREDIT,1300.,"sale",LocalDateTime.now(),account4);
            Transaction transaction12 = new Transaction(TransactionType.DEBIT,-3600.,"sale",LocalDateTime.now(),account4);

            Transaction transaction13 = new Transaction(TransactionType.CREDIT,6600.,"sale",LocalDateTime.now(),account5);
            Transaction transaction14 = new Transaction(TransactionType.CREDIT,1300.,"sale",LocalDateTime.now(),account5);
            Transaction transaction15 = new Transaction(TransactionType.DEBIT,-3600.,"sale",LocalDateTime.now(),account5);


            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);
            transactionRepository.save(transaction3);
            transactionRepository.save(transaction4);
            transactionRepository.save(transaction5);
            transactionRepository.save(transaction6);
            transactionRepository.save(transaction7);
            transactionRepository.save(transaction8);
            transactionRepository.save(transaction9);
            transactionRepository.save(transaction10);
            transactionRepository.save(transaction11);
            transactionRepository.save(transaction12);
            transactionRepository.save(transaction13);
            transactionRepository.save(transaction14);
            transactionRepository.save(transaction15);

            Loan loan1 = new Loan( "Hipotecario", 500000.0, Arrays.asList(12,24,36,48,60));
            Loan loan2 = new Loan("Personal", 100000.0,Arrays.asList(6,12,24));
            Loan loan3 = new Loan("Automotriz", 300000.0,Arrays.asList(6,12,24,36));


            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);


            ClientLoan clientLoan1 = new ClientLoan(400000.,60,loan1,client1);
            ClientLoan clientLoan2 = new ClientLoan(50000.,12,loan2,client1);

            ClientLoan clientLoan3 = new ClientLoan(100000.,24,loan2,client2);

            clientLoanRepository.save(clientLoan1);
            clientLoanRepository.save(clientLoan2);
            clientLoanRepository.save(clientLoan3);


           Card card1 = new Card(client1.getFirtName() +" "+ client1.getLastName(),CardType.DEBIT,CardColor.GOLD,"344 888 765 876",344,LocalDate.now().plusYears(5),LocalDate.now(),client1, true,account1, account1.getBalance());
           Card card2 = new Card(client1.getFirtName() +" "+ client1.getLastName(),CardType.CREDIT,CardColor.TITANIUM,"344 555 765 876",144,LocalDate.now().plusYears(5),LocalDate.now(),client1, true,account1, account1.getBalance());
           Card card3 = new Card(client2.getFirtName() +" "+ client2.getLastName(),CardType.CREDIT,CardColor.SILVER,"224 555 765 906",824,LocalDate.now().plusYears(5),LocalDate.now(),client2, true,account3, account3.getBalance());

           cardRepository.save(card1);
           cardRepository.save(card2);
           cardRepository.save(card3);

        };
    }

}
