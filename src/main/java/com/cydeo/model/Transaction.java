package com.cydeo.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    private Account sender;
    private Account receiver;
    private BigDecimal amount;
    private String message;
    private LocalDate createDate;
}
