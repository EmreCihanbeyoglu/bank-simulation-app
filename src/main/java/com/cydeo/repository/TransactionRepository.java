package com.cydeo.repository;

import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    public static List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();
//
//    public TransactionDTO save(TransactionDTO transactionDTO) {
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }
//
//    public List<TransactionDTO> findAll() {
//        return transactionDTOList;
//    }
//
//    public List<TransactionDTO> findTransactionsByAccountId(UUID accountId) {
//        return findAll().stream()
//                .filter(transactionDTO -> transactionDTO.getSender().equals(accountId) || transactionDTO.getReceiver().equals(accountId))
//                .toList();
//    }

    @Query("SELECT t FROM Transaction t WHERE t.sender.id = ?1 OR t.receiver.id = ?1")
    List<Transaction> fetchTransactionListByAccountId(Long transactionId);



//    List<Transaction> findTop10ByOrderByCreateDateDesc();
}
