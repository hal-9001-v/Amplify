package com.example.amplifyinternalservice.services;

import com.example.amplifyinternalservice.objects.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailServices {

    @Autowired
    private JavaMailSender sender;
    public void send(Mail m){
        SimpleMailMessage message = new SimpleMailMessage();
        String body = "hello";

        message.setTo(m.getAddressee());
        message.setFrom("dexaxi12@gmail.com");
        message.setText(m.getBody());
        message.setSubject(m.getSubject());

        sender.send(message);
        System.out.println("Sent Message " + body+  " to " + m.getAddressee());
    }

    }


