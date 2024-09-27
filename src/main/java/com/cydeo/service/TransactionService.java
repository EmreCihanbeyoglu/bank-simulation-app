package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    void makeTransaction(AccountDTO sender, AccountDTO receiver, BigDecimal amount, LocalDate createDate, String message);

    List<TransactionDTO> findAllTransactions();

    List<TransactionDTO> findLast10Transactions();

    List<TransactionDTO> findTransactionsByAccountId(Long accountId);
}
