package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import com.example.amplify.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.List;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static String UserSessionKey = "userInfo";

    private String nameFormKey;
    private String passwordFormKey;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserServices userServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String logInPage(Model model, HttpSession session) {
        return "login_template";
    }

    @GetMapping("/errorLogin")
    public String errorLogInPage(Model model, HttpSession session) {
        return "errorLogin_template";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "signIn")
    public String SignInUser(Model model, HttpSession session, @RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {
        User user = new User(username, passwordEncoder.encode(password), "USER");
        session.setAttribute(UserSessionKey, user);
        userRepo.save(user);
        logger.info("Signed successfully!");
        return "/inicio";
    }
}
