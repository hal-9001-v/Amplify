package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class LoginController {


    @Autowired
    UserRepository userRepo;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 1; i++) {
            // userRepo.save(new User("AmplifyDefaultUserFromDB", "12345"));
        }
    }

    @GetMapping("/login")
    public String logIn(Model model) {

        //model.addAttribute("username", "username");
        return "login_template";

    }

    @GetMapping("/addUser")
    public String addUser(Model model, @RequestParam String username, @RequestParam String password) {

        userRepo.save(new User(username, password));
        model.addAttribute("username", password);
        return "main_template";
    }

}
