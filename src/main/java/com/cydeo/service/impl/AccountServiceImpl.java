package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountNotFoundException;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        return accountRepository.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with accountId: " + id));
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteAccountById(id);
    }

    @Override
    public void activateAccountById(Long id) {
        accountRepository.activateAccountById(id);
    }


}
