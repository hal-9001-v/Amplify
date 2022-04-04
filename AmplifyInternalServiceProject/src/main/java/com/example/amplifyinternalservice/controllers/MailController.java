package com.example.amplifyinternalservice.controllers;


import com.example.amplifyinternalservice.objects.Mail;
import com.example.amplifyinternalservice.services.MailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    MailServices mailServices;

    @PostMapping(value = {"/recomendaciones","estadisticas"})
    public void sendMail(@RequestBody List<String> mailContent) {
        //Mail (content, subject, addressee)
        Mail m = new Mail(mailContent.get(0), mailContent.get(1), mailContent.get(2));
        mailServices.send(m);
    }

}
