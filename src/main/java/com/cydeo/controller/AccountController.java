package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/index")
    public String getIndexPage(Model model) {
        List<Account> accountList = accountService.getAllAccounts();
        model.addAttribute("accountList", accountList);
        return "/account/index";
    }

    @GetMapping("/create-account")
    public String getCreateAccountPage(Model model) {
        model.addAttribute("accountTypeList", AccountType.values());
        model.addAttribute("account", Account.builder().build());
        return "/account/create-account";
    }


    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute("account") Account account) {
        accountService.createNewAccount(account.getBalance(), LocalDate.now(), account.getAccountType(), account.getUserId());
        return "redirect:/index";
    }

}
