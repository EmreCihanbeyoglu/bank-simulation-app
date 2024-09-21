package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, LocalDate createDate, String message) {
        /*
            - if sender or receiver is null?
            - if sender and receiver is the same account?
            - if sender has enough balance to make transfer?
            - if both accounts are checking, if not, one of them saving, it needs to be same userId
         */

        validateAccount(sender, receiver);
        checkAccountOwnership(sender, receiver);
        executeBalanceAndUpdateIfRequired(amount, sender, receiver);

        Transaction transaction = Transaction.builder()
                .sender(sender.getId())
                .receiver(receiver.getId())
                .amount(amount)
                .createDate(createDate)
                .message(message)

                .build();


        return transactionRepository.save(transaction);
    }

    private void validateAccount(Account sender, Account receiver) throws BadRequestException {
        /*
            - if sender or receiver is null?
            - if sender and receiver is the same account?
            - if the account exists
         */

        if(sender == null || receiver == null) {
            throw new BadRequestException("Sender and Receiver cannot be null");
        }

        if(sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender and Receiver cannot be the same");
        }

        accountService.getAccountById(sender.getId());
        accountService.getAccountById(receiver.getId());


    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        if(
                (sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
                && !(sender.getUserId().equals(receiver.getUserId()))
        ) {
            throw new AccountOwnershipException("Not allowed to transfer money between saving and checking account for different users");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(sender.getBalance().compareTo(amount) > 0) {
            sender.setBalance(receiver.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Sender balance is not sufficient for given amount");
        }
    }


    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }




}
