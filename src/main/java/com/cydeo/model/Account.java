package com.cydeo.model;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private LocalDate createDate;
    private Long userId;
    private AccountStatus accountStatus;
}
