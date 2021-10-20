package com.mishadehterenok.project.controller;

import com.mishadehterenok.project.entity.Account;
import com.mishadehterenok.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("account")
public class RegistrationController {

    private final AccountService accountService;

    @Autowired
    public RegistrationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(ModelAndView model){
        model.addObject("account", new Account());
        model.setViewName("registration");
        return model;
    }

    //TODO: добавить валидацию
    @PostMapping("/registration/save")
    public String saveAccount(@ModelAttribute("account") Account account, Model model){
        accountService.save(account);
        model.addAttribute("accountId", account.getId());
        return "redirect:/catalog?category=All&page=1";
    }

}
