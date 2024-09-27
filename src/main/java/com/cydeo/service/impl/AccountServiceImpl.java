package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountNotFoundException;
import com.cydeo.mapper.EntityMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, EntityMapper entityMapper) {
        this.accountRepository = accountRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        Account account = entityMapper.map(accountDTO, Account.class);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCreateDate(LocalDate.now());
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(account -> entityMapper.map(account, AccountDTO.class))
                .toList();
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with accountId: " + id));
        return entityMapper.map(account, AccountDTO.class);
    }

    @Override
    public void deleteAccountById(Long id) {
//        accountRepository.deleteAccountById(id);
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with accountId: " + id));
        account.setAccountStatus(AccountStatus.DELETED);
        accountRepository.save(account);
    }

    @Override
    public void activateAccountById(Long id) {
//        accountRepository.activateAccountById(id);
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with accountId: " + id));
        account.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAllActiveAccounts() {
        return accountRepository
                .findAllByAccountStatus(AccountStatus.ACTIVE)
                .stream()
                .map(account -> entityMapper.map(account, AccountDTO.class))
                .toList();
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        accountRepository.save(entityMapper.map(accountDTO, Account.class));

    }


}
