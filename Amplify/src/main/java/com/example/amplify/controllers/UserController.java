package com.example.amplify.controllers;

import com.example.amplify.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
public class UserController {


    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            //repository.save(new User("dexaxi", "12345");
        }
    }


    @GetMapping("/{username}")
    public String userPage(Model model, @PathVariable String username) {


        model.addAttribute("username", "username");
        return "user-page-template";

    }

    @PostMapping("/user/registered")
    public String nuevoUsuario(Model model, User user) {

        //repository.save(user);

        return "user-registered-template";
    }


}
