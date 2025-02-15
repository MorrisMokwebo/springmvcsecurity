package com.mainsteam.code.springmvcsecurity.controller;

import com.mainsteam.code.springmvcsecurity.model.User;
import com.mainsteam.code.springmvcsecurity.service.EmailService;
import com.mainsteam.code.springmvcsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService, EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){

        model.addAttribute("user", new User());

        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String registerUser(@ModelAttribute @Valid User user, BindingResult bindingResult){
        System.out.println("User -> "+user.toString());

        if(bindingResult.hasErrors()){
            return "/showRegistrationForm";
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            System.out.println("password does match");
            return "/showRegistrationForm";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword( passwordEncoder.encode(user.getConfirmPassword()));
        user.setEnabled(false);
        user.setVerificationCode(String.valueOf(UUID.randomUUID()));

        userService.save(user);
        emailService.sendVerificationEmail(user);

        return "redirect:/showPreLogin";
    }

    @GetMapping("/showPreLogin")
    public String showPreLogin(){
        return "pre-login";
    }

}
