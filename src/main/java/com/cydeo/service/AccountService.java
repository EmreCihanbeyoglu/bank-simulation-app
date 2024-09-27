package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> getAllAccounts();

    AccountDTO getAccountById(Long id);

    void deleteAccountById(Long id);

    void activateAccountById(Long id);

    List<AccountDTO> getAllActiveAccounts();

    void updateAccount(AccountDTO accountDTO);

}
