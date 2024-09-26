package com.cydeo.dto;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private BigDecimal balance;
    private AccountType accountType;
    private LocalDate createDate;
    private Long userId;
    private AccountStatus accountStatus;
}
