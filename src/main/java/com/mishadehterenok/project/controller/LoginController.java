package com.mishadehterenok.project.controller;

import com.mishadehterenok.project.dto.LoginAccountDto;
import com.mishadehterenok.project.entity.Account;
import com.mishadehterenok.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("account")
public class LoginController {

    private final AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(ModelAndView model){
        model.addObject("loginAccountDto", new LoginAccountDto());
        model.setViewName("login");
        return model;
    }

    @PostMapping("/login/entry")
    public String loginEntry(LoginAccountDto accountDto, Model model){
        Account account = accountService.findByLogin(accountDto.getLogin());
        if (account != null && account.getPassword().equals(accountDto.getPassword())){
            model.addAttribute("accountId", account.getId());
            String url;
            switch (account.getRole()){
                case USER: url = "redirect:/catalog/category/All/1";
                    break;
                case MANAGER: url = "redirect:/manager";
                    break;
                case ADMIN: url = "redirect:/admin";
                    break;
                default: url = "redirect:/login";
            }
            return url;
        } return "redirect:/login";
    }
}
