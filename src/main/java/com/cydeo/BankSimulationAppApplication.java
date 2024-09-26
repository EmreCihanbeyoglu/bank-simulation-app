package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.dto.AccountDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);
//
//        AccountDTO sender = accountService.createNewAccount(BigDecimal.valueOf(70), LocalDate.now(), AccountType.CHECKING, 1L);
//        AccountDTO receiver = accountService.createNewAccount(BigDecimal.valueOf(30), LocalDate.now(), AccountType.CHECKING, 2L);
//
//        accountService.getAllAccounts().forEach(System.out::println);
//
//        transactionService.makeTransaction(sender, receiver, new BigDecimal(10), LocalDate.now(), "Transaction 1");
//
//        accountService.getAllAccounts().forEach(System.out::println);

    }

}
