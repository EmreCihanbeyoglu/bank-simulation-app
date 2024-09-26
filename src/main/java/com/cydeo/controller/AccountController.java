package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountType;
import com.cydeo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/index")
    public String getIndexPage(Model model) {
        List<AccountDTO> accountDTOList = accountService.getAllAccounts();
        model.addAttribute("accountList", accountDTOList);
        return "/account/index";
    }

    @GetMapping("/create-account")
    public String getCreateAccountPage(Model model) {
        model.addAttribute("accountTypeList", AccountType.values());
        model.addAttribute("account", new AccountDTO());
        return "/account/create-account";
    }


    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute("account") AccountDTO accountDTO) {
        accountService.createNewAccount(accountDTO);
        return "redirect:/index";
    }

    @GetMapping("/delete-account")
    public String deleteAccount(@RequestParam("accountId") Long accountId) {
        accountService.deleteAccountById(accountId);
        return "redirect:/index";
    }

    @GetMapping("/activate-account")
    public String activateAccount(@RequestParam("accountId") Long accountId) {
        accountService.activateAccountById(accountId);
        return "redirect:/index";
    }

}
