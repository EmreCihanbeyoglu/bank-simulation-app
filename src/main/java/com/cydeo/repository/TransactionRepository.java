package com.cydeo.repository;

import com.cydeo.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<Transaction>();

    public Transaction save(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return transactionList;
    }

    public List<Transaction> findTransactionsByAccountId(UUID accountId) {
        return findAll().stream()
                .filter(transaction -> transaction.getSender().equals(accountId) || transaction.getReceiver().equals(accountId))
                .toList();
    }

}
