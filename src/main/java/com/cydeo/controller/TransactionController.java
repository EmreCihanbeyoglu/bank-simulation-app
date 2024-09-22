package com.cydeo.controller;

import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

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
        model.addAttribute("accountList", accountService.getAllAccounts());
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("transactionList", transactionService.findLast10Transactions());
        return "/transaction/make-transfer";
    }

    @PostMapping("/perform-transfer")
    public String makeTransfer(@ModelAttribute("transaction") Transaction transaction) {
        transactionService.makeTransaction(transaction.getSender(), transaction.getReceiver(), transaction.getAmount(), LocalDate.now(), transaction.getMessage());
        return "redirect:/make-transfer";
    }
}
