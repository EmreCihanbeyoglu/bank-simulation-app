package com.cydeo.service;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, LocalDate createDate, AccountType accountType, Long userId);

    List<Account> getAllAccounts();

}
