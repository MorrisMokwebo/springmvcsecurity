package com.mainsteam.code.springmvcsecurity.controller;

import com.mainsteam.code.springmvcsecurity.service.EmailService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final EmailService emailService;

    public LoginController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/showLoginForm")
    public String showLoginForm(){
        return "login-form";
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code){

        if(emailService.verify(code)){
            return "verify-success";
        }

        return "verify-fail";
    }

}
