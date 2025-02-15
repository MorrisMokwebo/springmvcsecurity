package com.mainsteam.code.springmvcsecurity.service;

import com.mainsteam.code.springmvcsecurity.model.User;
import com.mainsteam.code.springmvcsecurity.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailServiceImpl implements EmailService{

    @Value("${email.from.address}")
    private String fromAddress;
    @Value("${email.sender.name}")
    private String senderName;
    @Value("${email.subject.text}")
    private String subject;
    @Value("${email.site.url}")
    private String siteUrl;

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void sendVerificationEmail(User user) {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom(fromAddress,senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            helper.setText(emailContent(user),true);

            mailSender.send(message);


        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println(e);
        }

    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if(user == null || user.isEnabled()){
            return false;
        }

        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepository.save(user);

        return true;
    }

    private String emailContent(User user){

     String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "mainstream code.";

     content = content.replace("[[name]]",user.getFullName()).
             replace("[[URL]]",verifyUrl(user.getVerificationCode(),siteUrl));

        return content;
    }

    private String verifyUrl(String verificationCode, String siteUrl){
        return siteUrl + "/verify?code="+verificationCode;
    }
}
