package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import com.example.amplify.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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

    @GetMapping("/register")
    public String signInPage(Model model, HttpSession session) {
        return "login_register_template";
    }

    @GetMapping("/errorLogin")
    public String errorLogInPage(Model model, HttpSession session) {
        return "errorLogin_template";
    }

    @PostMapping(value = "/registerUser")
    public String SignInUser(Model model, HttpSession session, @RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {

        Optional<User> checkUser = userServices.findByUsername(username);

        if (checkUser.isPresent() || username.isEmpty() || password.isEmpty()) {
            logger.info("Failed to SignIn!");
            return "errorRegister_template";
        } else {
            User user = new User(username, passwordEncoder.encode(password), "USER");
            userRepo.save(user);
        }
        logger.info("Signed successfully!");
        return "login_template";
    }
}
