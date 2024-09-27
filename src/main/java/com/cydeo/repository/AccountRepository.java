package com.cydeo.repository;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    public static List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
//
//    public AccountDTO save(AccountDTO accountDTO) {
//        accountDTOList.add(accountDTO);
//        return accountDTO;
//    }
//
//    public List<AccountDTO> findAllAccounts() {
//        return accountDTOList;
//    }
//
//    public Optional<AccountDTO> findAccountById(Long id) {
//        return accountDTOList
//                .stream()
//                .filter(account -> account.getId().equals(id))
//                .findFirst();
//    }
//    public void deleteAccountById(Long id) {
//        accountDTOList.stream()
//                .filter(account -> account.getId().equals(id))
//                .findFirst()
//                .ifPresent(account -> account.setAccountStatus(AccountStatus.DELETED));
//    }
//
//    public void activateAccountById(Long id) {
//        accountDTOList.stream()
//                .filter(account -> account.getId().equals(id))
//                .findFirst()
//                .ifPresent(account -> account.setAccountStatus(AccountStatus.ACTIVE));
//    }


    List<Account> findAllByAccountStatus(AccountStatus status);

}
