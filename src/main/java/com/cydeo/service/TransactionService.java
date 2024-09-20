package com.cydeo.service;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, LocalDate createDate, String message);

    List<Transaction> findAllTransactions();
}
