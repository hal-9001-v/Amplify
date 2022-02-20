package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @Autowired
    UserRepository userRepo;

    @GetMapping("/login")
    public String logIn(Model model){

        //model.addAttribute("username", "username");
        return "login_template";

    }

    @GetMapping("/addUser")
    public String addUser(Model model, @RequestParam String username, @RequestParam String password){

        userRepo.save(new User(username, password));

        return "main_template";
    }

}
