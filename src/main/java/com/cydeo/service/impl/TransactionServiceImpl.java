package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.UnderConstructionException;
import com.cydeo.mapper.EntityMapper;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final EntityMapper entityMapper;
    @Value("${under_construction}")
    private boolean underConstruction;

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, EntityMapper entityMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.entityMapper = entityMapper;
    }

    @Override
    public void makeTransaction(AccountDTO sender, AccountDTO receiver, BigDecimal amount, LocalDate createDate, String message) {
        /*
            - if sender or receiver is null?
            - if sender and receiver is the same account?
            - if sender has enough balance to make transfer?
            - if both accounts are checking, if not, one of them saving, it needs to be same userId
         */
        if(!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setCreateDate(createDate);
            transaction.setMessage(message);
            transaction.setSender(entityMapper.map(sender, Account.class));
            transaction.setReceiver(entityMapper.map(receiver, Account.class));


            transactionRepository.save(transaction);
        } else {
            throw new UnderConstructionException("App is under construction, please try again later!");
        }
    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) throws BadRequestException {
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

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        if(
                (sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
                && !(sender.getUserId().equals(receiver.getUserId()))
        ) {
            throw new AccountOwnershipException("Not allowed to transfer money between saving and checking account for different users");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(sender.getBalance().compareTo(amount) > 0) {

            // to avoid update unrelated parts, you can fetch the database record and do manpulation on it directly
            AccountDTO senderFromDB = accountService.getAccountById(sender.getId());
            AccountDTO receiverFromDB = accountService.getAccountById(receiver.getId());

            senderFromDB.setBalance(senderFromDB.getBalance().subtract(amount));
            receiverFromDB.setBalance(receiverFromDB.getBalance().add(amount));

            accountService.updateAccount(senderFromDB);
            accountService.updateAccount(receiverFromDB);


        } else {
            throw new BalanceNotSufficientException("Sender balance is not sufficient for given amount");
        }
    }


    @Override
    public List<TransactionDTO> findAllTransactions() {
        return transactionRepository
                .findAll()
                .stream()
                .map(transaction -> entityMapper.map(transaction, TransactionDTO.class))
                .toList();
    }

    @Override
    public List<TransactionDTO> findLast10Transactions() {
        return findAllTransactions()
                .stream()
                .sorted(Comparator.comparing(TransactionDTO::getCreateDate).reversed())
                .limit(10).toList();
    }

    @Override
    public List<TransactionDTO> findTransactionsByAccountId(Long accountId) {
        return transactionRepository.fetchTransactionListByAccountId(accountId)
                .stream()
                .map(transaction -> entityMapper.map(transaction, TransactionDTO.class))
                .toList();

    }


}
