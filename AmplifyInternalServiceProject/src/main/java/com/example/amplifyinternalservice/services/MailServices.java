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

        message.setTo(m.getAddressee());
        message.setText(m.getBody());
        message.setSubject(m.getSubject());
        System.out.println("Sent message: " + m.getBody() + " to " + m.getAddressee());
        sender.send(message);
    }

    }


