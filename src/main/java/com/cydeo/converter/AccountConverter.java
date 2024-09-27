package com.cydeo.converter;

import com.cydeo.dto.AccountDTO;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class AccountConverter implements Converter<String, AccountDTO> {

    private final AccountService accountService;

    @Autowired
    public AccountConverter(@Lazy AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public AccountDTO convert(String source) {

        if(source == null || source.isEmpty()) {
            return null;
        }

        return accountService.getAccountById(Long.parseLong(source));
    }
}
