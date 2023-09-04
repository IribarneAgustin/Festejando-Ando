package com.microservice.festejandoando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailService ;
    
    public void sendEmail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cliente@festejandoando.com");
        message.setTo("agusiri96@gmail.com");
        message.setText("test");

        mailService.send(message);


    }

    
}
