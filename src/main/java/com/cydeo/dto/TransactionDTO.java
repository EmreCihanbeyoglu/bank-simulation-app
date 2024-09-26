package com.cydeo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {


    private AccountDTO sender;
    private AccountDTO receiver;
    private BigDecimal amount;
    private String message;
    private LocalDate createDate;
}
