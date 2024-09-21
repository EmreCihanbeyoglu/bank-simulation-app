package com.cydeo.repository;

import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<Account> accountList = new ArrayList<Account>();

    public Account save(Account account) {
        accountList.add(account);
        return account;
    }

    public List<Account> findAllAccounts() {
        return accountList;
    }

    public Optional<Account> findAccountById(UUID id) {
        return accountList
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }
}
