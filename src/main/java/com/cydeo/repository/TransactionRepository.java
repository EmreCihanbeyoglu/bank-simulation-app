package com.cydeo.repository;

import com.cydeo.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionRepository {

    public static List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();

    public TransactionDTO save(TransactionDTO transactionDTO) {
        transactionDTOList.add(transactionDTO);
        return transactionDTO;
    }

    public List<TransactionDTO> findAll() {
        return transactionDTOList;
    }

    public List<TransactionDTO> findTransactionsByAccountId(UUID accountId) {
        return findAll().stream()
                .filter(transactionDTO -> transactionDTO.getSender().equals(accountId) || transactionDTO.getReceiver().equals(accountId))
                .toList();
    }

}
