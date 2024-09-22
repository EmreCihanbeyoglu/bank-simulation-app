package com.cydeo.converter;

import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConfigurationPropertiesBinding
public class AccountConverter implements Converter<String, Account> {

    private final AccountService accountService;

    @Autowired
    public AccountConverter(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Account convert(String source) {
        return accountService.getAccountById(UUID.fromString(source));
    }
}
