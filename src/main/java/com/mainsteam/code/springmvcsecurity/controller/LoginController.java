package com.mainsteam.code.springmvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginForm")
    public String showLoginForm(){
        return "login-form";
    }

}
