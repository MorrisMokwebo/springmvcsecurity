package com.mainsteam.code.springmvcsecurity.service;

import com.mainsteam.code.springmvcsecurity.model.User;

public interface EmailService {
    void sendVerificationEmail(User user);
    boolean verify(String verificationCode);
}
