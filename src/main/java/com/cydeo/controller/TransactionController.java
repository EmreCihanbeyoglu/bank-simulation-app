package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }


    @GetMapping("/make-transfer")
    public String getMakeTransferPage(Model model) {
        model.addAttribute("accountList", accountService.getAllActiveAccounts());
        model.addAttribute("transaction", new TransactionDTO());
        model.addAttribute("transactionList", transactionService.findLast10Transactions());
        return "/transaction/make-transfer";
    }

    @PostMapping("/perform-transfer2")
    public String makeTransfer(@ModelAttribute("transaction") TransactionDTO transaction) {
        AccountDTO sender = accountService.getAccountById(transaction.getSender().getId());
        AccountDTO receiver = accountService.getAccountById(transaction.getReceiver().getId());

        transactionService.makeTransaction(sender, receiver, transaction.getAmount(), LocalDate.now(), transaction.getMessage());
        return "redirect:/make-transfer";
    }

    @GetMapping("/account-transaction")
    public String getAccountTransactionsPage(@RequestParam("accountId") Long accountId, Model model) {
        model.addAttribute("transactionList", transactionService.findTransactionsByAccountId(accountId));
        return "transaction/transactions";
    }
}
