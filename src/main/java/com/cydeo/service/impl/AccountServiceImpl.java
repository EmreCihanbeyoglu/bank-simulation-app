package com.cydeo.service.impl;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountNotFoundException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account createNewAccount(BigDecimal balance, LocalDate createDate, AccountType accountType, Long userId) {
        Account accountToBeCreated = Account.builder()
                .balance(balance)
                .createDate(createDate)
                .accountType(accountType)
                .userId(userId)
                .id(UUID.randomUUID())
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        return accountRepository.save(accountToBeCreated);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with accountId: " + id));
    }

    @Override
    public void deleteAccountById(UUID id) {
        accountRepository.deleteAccountById(id);
    }

    @Override
    public void activateAccountById(UUID id) {
        accountRepository.activateAccountById(id);
    }


}
